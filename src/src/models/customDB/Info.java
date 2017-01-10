package models.customDB;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class Info {
    @SerializedName("time")
    @Expose
    private long time;
    @SerializedName("histories")
    @Expose
    private List<History> histories;

    public Info(){
        time=0;
        histories = new CopyOnWriteArrayList<>();
    }

    public void incrementTime(long time){
        this.time+=time;
    }

    public long getTime() {
        System.out.println("time"+time);
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public List<History> getHistories() {
        return histories;
    }

    public void setHistories(List<History> histories) {
        this.histories = histories;
    }
}
