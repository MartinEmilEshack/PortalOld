package Portal.UI;

import Portal.FileManagement.Explorer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;


public class MainWindowController {

    @FXML
    public TextField address;
    @FXML
    public VBox raws;
    @FXML
    GridPane folders;

    @FXML
    public void onComputer1Pressed(){

        final String path = "E:\\";
        Explorer.ExplorerData explorerData = null;
        try{
            explorerData = Explorer.getExplorerData(path,Explorer.OPEN_FOLDER);
        }catch (FileNotFoundException FNFE){
            System.out.println("Folder not found");
        }
        if(explorerData!=null)
            setExplorerData(explorerData);

    }

    private void onFolderPressed(String path){

        Explorer.ExplorerData explorerData = null;
        try{
            explorerData = Explorer.getExplorerData(path,Explorer.OPEN_FOLDER);
        }catch (FileNotFoundException FNFE){
            System.out.println("Folder not found");
        }
        if(explorerData!=null)
            setExplorerData(explorerData);

    }

    public void onBackPressed(){

        Explorer.ExplorerData explorerData = null;
        try{
            explorerData = Explorer.getExplorerData(address.getText(),Explorer.BACK);
        }catch (FileNotFoundException FNFE){
            System.out.println("Folder not found");
        }
        if(explorerData!=null)
            setExplorerData(explorerData);

    }

    public void onForwardPressed(){

        Explorer.ExplorerData explorerData = null;
        try{
            explorerData = Explorer.getExplorerData(address.getText(),Explorer.FORWARD);
        }catch (FileNotFoundException FNFE){
            System.out.println("Folder not found");
        }
        if(explorerData!=null)
            setExplorerData(explorerData);

    }

    private void setExplorerData(Explorer.ExplorerData data){
        address.setText(data.path);
        folders.getChildren().removeAll();
        ArrayList<Button> buttons = new ArrayList<>();
        for (Portal.FileManagement.File file:data.files){
            Button button = new Button(file.name);
            button.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
            button.setOnAction(EventHandler -> {
                onFolderPressed(file.path);
            });
            buttons.add(button);
        }
        Collections.reverse(buttons);
        int fileNumber = 0;
        int rows = 0;
        int columns = 0;
        while(fileNumber < buttons.size()){
            if(columns == 5){
                columns = 0;
                rows++;
            }
            folders.add(buttons.get(fileNumber),columns,rows,1,1);
            fileNumber++;
            columns++;
        }

    }

//    private void setFolder(File[] files,String path){
//        address.setText(path);
//        ArrayList<Button> buttons = new ArrayList<>();
//        for (File file:files){
//            Button button = new Button(file.getName());
//            button.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
//            button.setOnAction(EventHandler -> {
//                onFolderPressed(file.getPath());
//            });
//            buttons.add(button);
//        }
//        folders.getChildren().removeAll(buttons);
//        Collections.reverse(buttons);
//        int fileNumber = 0;
//        int rows = 0;
//        int columns = 0;
//        while(fileNumber < buttons.size()){
//            if(columns == 5){
//                columns = 0;
//                rows++;
//            }
//            folders.add(buttons.get(fileNumber),columns,rows,1,1);
//            fileNumber++;
//            columns++;
//        }
//    }



}

//path = address.getCharacters().toString();
//        try{
//        files = Explorer.openFolder(path);
//        stack = Explorer.updateStack(path);
//        }catch (FileNotFoundException FNFE){
//        System.out.println("Folder not found");
//        }
//
//        if((files!=null)&&(stack!=null)){
//        for (File file : files)
//        System.out.println(file.getName());
//        for (String string: stack)
//        System.out.print(string + "->");
//        }
