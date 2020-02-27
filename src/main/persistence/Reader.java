package persistence;

import com.google.gson.*;
import model.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.ArrayList;


// A reader that can read account data from a file
public class Reader {

    public static StoredData read() throws IOException {
        Gson gson = new Gson();
        File jsonFile = new File("datasaver.json");
        BufferedReader br = new BufferedReader(new FileReader(jsonFile));
        StoredData models = gson.fromJson(br, StoredData.class);
        return models;
    }
}
