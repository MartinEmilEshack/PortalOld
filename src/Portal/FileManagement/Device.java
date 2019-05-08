package Portal.FileManagement;

import java.io.File;
import java.util.ArrayList;

public class Device {

    public String userName;
    public ArrayList<File> files;

    public Device(String userName,ArrayList<File> files){
        this.userName = userName;
        this.files = files;
    }

}
