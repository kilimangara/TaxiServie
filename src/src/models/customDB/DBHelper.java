package models.customDB;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DBHelper {
    private final String PATH = "statistics.db";

    private static DBHelper instance;

    private WritableDB writableDB;
    private ReadableDB readableDB;
    private Gson gson;

    private List<History> histories;

    private DBHelper(){}

    public static DBHelper getInstance(){
        if(instance == null){
            instance = new DBHelper();
        }
        return instance;
    }

    public void init(){
        gson =  new GsonBuilder().setPrettyPrinting().create();
        histories = getHistories();
        if (histories == null){
            histories = new CopyOnWriteArrayList<>();
        }
        System.out.println(histories);
    }

    public List<History> getActualHistories(){
        return histories;
    }

    public List<History> getHistories(){
        try {
            readableDB = new ReadableDB(PATH,gson);
            return readableDB.readAll();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }
    public void writeHistory(){
        try {
            writableDB = new WritableDB(PATH, gson);
            writableDB.writeHistory(histories);
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }


    }
    public void addHistory(History history){
        histories.add(history);
    }

}
