package firebase.project.com.messenger.Scehdule;

/**
 * Created by 경남 on 2017-12-12.
 */

public class Schedule {
    private int _id;
    public String date;
    public String title;
    public String content;
    public String time;

    public Schedule() {
        // Default constructor required for calls to DataSnapshot.getValue(Comment.class)
    }

    public Schedule(String title) {
        this.title = title;
    }

    public int get_id() {
        return _id;
    }
    public void set_id(int _id) {
        this._id = _id;
    }

    public String getDate(){return date;}
    public void setDate(String date){this.date = date;}

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }

    public String getTime(){return time;}
    public void setTime(String time){this.time = time;}


}