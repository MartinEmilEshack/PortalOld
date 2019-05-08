package Portal.FileManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Explorer {

    private Explorer(){}

    public static File[] openFolder(String path) throws FileNotFoundException {
        File file = new File(path);
        if(!file.exists())
            throw new FileNotFoundException("recheck the file path");
        if(file.isFile())
            return null;
        return file.listFiles();
    }

    public static ArrayList<String> updateStack (String path){
        String[] address = path.split("\\\\");
        return new ArrayList<>(Arrays.asList(address));
    }

    public static String back(ArrayList<String> stack,String path){
        StringBuilder pathBuilder = new StringBuilder(path);
        if(stack.get(0).equals(path.split("\\\\")[path.split("\\\\").length]))
            return null;
        else
            pathBuilder.delete(pathBuilder.lastIndexOf("\\\\"),pathBuilder.length());
        return pathBuilder.toString();
    }

    public static String forward(ArrayList<String> stack,String path){
        String string = path.split("\\\\")[path.split("\\\\").length];
        StringBuilder pathBuilder = new StringBuilder(path);
        try {
            pathBuilder.append("\\\\").append(stack.get(stack.indexOf(string)+1));
        }catch (IndexOutOfBoundsException IOOBE){
            return null;
        }
        return pathBuilder.toString();
    }



}
