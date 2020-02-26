package persistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import model.StoredData;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

// A reader that can read account data from a file
public class Reader {

    public static void read() throws IOException {
        Gson gson = new Gson();
        JFileChooser choose = new JFileChooser();
        FileNameExtensionFilter extFilter = new FileNameExtensionFilter("JSON File", "json");
        choose.setFileFilter(extFilter);
        int returnVal = choose.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You have chosen to open this file: "
                    + choose.getSelectedFile().getName());
        }
        File jsonFile = choose.getSelectedFile();
        BufferedReader br = new BufferedReader(new FileReader(jsonFile));
        Type type = new TypeToken<List<StoredData>>(){}.getType();
        List<StoredData> models = gson.fromJson(br, type);
    }
}
