package Portal.UI;

import Portal.FileManagement.Explorer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;


public class MainWindowController {

    @FXML
    public TextField address;
    @FXML
    GridPane folders;
    private ArrayList<String> stack;

    @FXML
    public void onComputer1Pressed(){

        final String path = "E:\\";
        File[] files = null;
        try{
            files = Explorer.openFolder(path);
            stack = Explorer.updateStack(path);
        }catch (FileNotFoundException FNFE){
            System.out.println("Folder not found");
        }
        if(files!=null)
            setFolder(files,path);
    }

    public void onFolderPressed(String path){
        File[] files = null;
        try{
            files = Explorer.openFolder(path);
            stack = Explorer.updateStack(path);
        }catch (FileNotFoundException FNFE){
            System.out.println("Folder not found");
        }
        if(files!=null)
            setFolder(files,path);
    }

    private void setFolder(File[] files,String path){
        address.setText(path);
        ArrayList<Button> buttons = new ArrayList<>();
        for (File file:files){
            Button button = new Button(file.getName());
            button.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
            button.setOnAction(EventHandler -> {
                folders.getChildren().removeAll(buttons);
                onFolderPressed(file.getPath());
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
