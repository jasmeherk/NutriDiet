package persistence;

import java.io.*;


import com.google.gson.*;
import model.*;


// A writer that can write account data to a file
public class Writer {
    // MODIFIES : file
    // EFFECTS : Stores data in file
    public static void write(StoredData s) throws IOException {
        FileWriter writer = new FileWriter("/data/datasaver.json");
        Gson gson = new Gson();
        gson.toJson(s, writer);
        writer.close();
    }

}
