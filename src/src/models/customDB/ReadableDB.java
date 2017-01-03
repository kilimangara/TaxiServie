package models.customDB;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class ReadableDB extends FileInputStream {
    private Gson gson;

    public ReadableDB(String name, Gson gson) throws FileNotFoundException {
        super(name);
        this.gson =gson;
    }

    public ReadableDB(File file, Gson gson) throws FileNotFoundException {
        super(file);
        this.gson = gson;
    }

    public List<History> readAll() throws IOException {
        byte[] buffer = new byte[available()];
        read(buffer, 0, available());
        String json = Utils.bytesToStringUTFCustom(buffer);
        close();
        System.out.println(json);
        return gson.fromJson(json, new TypeToken<List<History>>(){}.getType());

    }
}
