package Portal.FileManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class Explorer {

    public final static int OPEN_FOLDER = 0;
    public final static int FORWARD = 1;
    public final static int BACK = 2;

    private static ArrayList<String> path;

    private Explorer(){}

    public static class ExplorerData {

        public String path;
        public Portal.FileManagement.File[] files;

        private ExplorerData(String path,ArrayList<Portal.FileManagement.File>files){
            this.path = path;
            this.files = Arrays.copyOf(files.toArray(),files.size(), Portal.FileManagement.File[].class);
        }

    }

    public static ExplorerData getExplorerData(String path,int COMMAND) throws FileNotFoundException{
        if(COMMAND == OPEN_FOLDER){
            if((new File(path)).isFile())
                return null;
            else
                Explorer.path = new ArrayList<>(Arrays.asList(path.split("\\\\")));
            return new ExplorerData(path,makeFilesArrayList(openFolder(path)));
        }else if(COMMAND == BACK){
            return new ExplorerData(path,makeFilesArrayList(openFolder(back(path))));
        }else if(COMMAND == FORWARD){
            return new ExplorerData(path,makeFilesArrayList(openFolder(forward(path))));
        }else return null;
    }

    private static File[] openFolder(String path) throws FileNotFoundException {
        File file = new File(path);
        if(!file.exists())
            throw new FileNotFoundException("recheck the file path");
        return file.listFiles();
    }

    private static String back(String path){
        StringBuilder pathBuilder = new StringBuilder(path);
        if(Explorer.path.get(0).equals(path.split("\\\\")[path.split("\\\\").length-1]))
            return path;
        else
            pathBuilder.delete(pathBuilder.lastIndexOf("\\"),pathBuilder.length());
        return pathBuilder.toString();
    }

    private static String forward(String path){
        String string = path.split("\\\\")[path.split("\\\\").length-1];
        StringBuilder pathBuilder = new StringBuilder(path);
        try {
            pathBuilder.append("\\\\");
            pathBuilder.append(Explorer.path.get(Explorer.path.indexOf(string)+1));
        }catch (IndexOutOfBoundsException IOOBE){
            return path;
        }
        return pathBuilder.toString();
    }

    private static ArrayList<Portal.FileManagement.File> makeFilesArrayList(File[] files){
        ArrayList<Portal.FileManagement.File> fileArrayList = new ArrayList<>();
        for(File file:files){
            Portal.FileManagement.File myFile = new Portal.FileManagement.File();
            myFile.name = file.getName();
            myFile.path = file.getPath();
            myFile.isFile = file.isFile();
            fileArrayList.add(myFile);
        }
        return fileArrayList;
    }

}