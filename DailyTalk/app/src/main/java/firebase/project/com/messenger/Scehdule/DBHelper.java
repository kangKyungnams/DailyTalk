package firebase.project.com.messenger.Scehdule;

/**
 * Created by 경남 on 2017-12-12.
 */


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper{
    private Context context;

    public DBHelper(Context context){
        super(context, "schedule.db", null, 1);
    }
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuffer sb = new StringBuffer();
        sb.append(" CREATE TABLE SCHEDULE ( ");
        sb.append(" _ID INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb.append(" SCHDATE TEXT, ");
        sb.append(" SCHTIME TEXT, ");
        sb.append(" TITLE TEXT, ");
        sb.append(" CONTENT TEXT )");

        sqLiteDatabase.execSQL(sb.toString());

        //Toast.makeText(context, "Table 생성완료", Toast.LENGTH_SHORT).show();

        //String sql = "CREATE TABLE SCHEDULE (_id INTEGER PRIMARY KEY AUTOINCREMENT,"+"diaryDate TEXT, content TEXT);";
        //sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Toast.makeText(context, "버전이 올라갔습니다.", Toast.LENGTH_SHORT).show();
    }

    public void addSchedule(Schedule schedule){
        SQLiteDatabase db = getReadableDatabase();
        StringBuffer sb = new StringBuffer();

        sb.append(" INSERT INTO SCHEDULE ( ");
        sb.append(" SCHDATE, SCHTIME, TITLE, CONTENT ) ");
        sb.append(" VALUES ( ?, ?, ?, ? ) ");

        db.execSQL(sb.toString(), new Object[]{
                schedule.getDate(),
                schedule.getTime(),
                schedule.getTitle(),
                schedule.getContent()
        });

        //Toast.makeText(context, "INSERT 완료", Toast.LENGTH_SHORT).show();
    }

    public List getSchedule(String s){
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT _ID, SCHDATE, SCHTIME, TITLE, CONTENT FROM SCHEDULE WHERE SCHDATE = ");
        sb.append(s);

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(sb.toString(),null);
        List schedules = new ArrayList();
        Schedule schedule = null;

        while(cursor.moveToNext()){
            schedule = new Schedule();
            schedule.set_id(cursor.getInt(0));
            schedule.setDate(cursor.getString(1));
            schedule.setTime(cursor.getString(2));
            schedule.setTitle(cursor.getString(3));
            schedule.setContent(cursor.getString(4));

            schedules.add(schedule);
        }

        return schedules;
    }

    public Schedule getSchedule_id(int ids){
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT _ID, SCHDATE, SCHTIME, TITLE, CONTENT FROM SCHEDULE WHERE _ID = " + ids);


        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(sb.toString(),null);
        //List schedules = new ArrayList();
        Schedule schedule = null;

        while(cursor.moveToNext()){
            schedule = new Schedule();
            schedule.set_id(cursor.getInt(0));
            schedule.setDate(cursor.getString(1));
            schedule.setTime(cursor.getString(2));
            schedule.setTitle(cursor.getString(3));
            schedule.setContent(cursor.getString(4));

            //schedules.add(schedule);
        }

        return schedule;
    }

    public void delete(int id){
        StringBuffer sb = new StringBuffer();
        sb.append(" DELETE FROM SCHEDULE WHERE _ID = " + id);

        SQLiteDatabase db = getReadableDatabase();
        db.execSQL(sb.toString());


    }



}