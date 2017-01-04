package models.customDB;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


public class WritableDB extends FileOutputStream {
    private Gson gson;

    public WritableDB(String name, Gson gson) throws FileNotFoundException {
        super(name);
        this.gson =gson;
    }

    public WritableDB(File file, Gson gson) throws FileNotFoundException {
        super(file);
        this.gson = gson;
    }

    public void writeHistory( List<History> histories) throws IOException {
        String json = gson.toJson(histories);
        write(Utils.stringToBytesUTFCustom(json));
        close();
    }

}
