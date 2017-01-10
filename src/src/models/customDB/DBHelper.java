package models.customDB;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DBHelper {
    private final String PATH = "statistics.db";

    private static DBHelper instance;

    private WritableDB writableDB;
    private ReadableDB readableDB;
    private Gson gson;

    private Timer timer;

    private Info info;

    private DBHelper(){}

    public static DBHelper getInstance(){
        if(instance == null){
            instance = new DBHelper();
        }
        return instance;
    }

    public void init(){
        gson =  new GsonBuilder().setPrettyPrinting().create();
        info = getHistories();
        if (info == null){
            info = new Info();
        }
        timer = new Timer(1000, e -> {
            addTime(1);});
        timer.start();
    }

    public List<History> getActualHistories(){
        return info.getHistories();
    }

    public Info getHistories(){
        try {
            readableDB = new ReadableDB(PATH,gson);
            return readableDB.readAll();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    public Info getInfo(){
        return info;}

    public void writeHistory(){
        try {
            writableDB = new WritableDB(PATH, gson);
            writableDB.writeHistory(info);
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
        timer.stop();
    }

    public void addHistory(History history){
        info.getHistories().add(history);
    }

    public void addTime(long time){
        info.incrementTime(time);
    }

}
