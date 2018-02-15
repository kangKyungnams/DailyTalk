package firebase.project.com.messenger.Scehdule;

/**
 * Created by 경남 on 2017-12-12.
 */


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ScheduleList extends BaseAdapter {

    DBHelper helper;

    private List schedule;
    private Context context;
    String t;
    int t1, t2;

    public ScheduleList(List schedule) {
        this.schedule = schedule;
    }
    public ScheduleList(Context context) {
        this.context = context;
    }
    public ScheduleList(List schedule, Context context) {
        this.schedule = schedule;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.schedule.size();
    }
    @Override
    public Object getItem(int i) {
        return this.schedule.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Holder holder = null;
        //View view2 = view;
        if(view==null){

            Schedule schedule = (Schedule) getItem(i);

            t = schedule.getTime();
            t1 = Integer.parseInt(t.substring(0,2));
            t2 = Integer.parseInt(t.substring(2,4));


            //////////////////////
            view = new LinearLayout(context);

            ((LinearLayout)view).setOrientation(LinearLayout.HORIZONTAL);
            //((LinearLayout)view2).setOrientation(LinearLayout.VERTICAL);
            //((LinearLayout)view2).addView(view);

            //TextView tvId = new TextView(context);
            //tvId.setPadding(10,0,20,0);

            TextView tvTitle = new TextView(context);
            tvTitle.setPadding(0,20,0,20);
            tvTitle.setTextSize(20);

            //TextView tvAge = new TextView(context);
            //tvId.setPadding(20,0,20,0);


            TextView tvContent = new TextView(context);
            //tvContent.setPadding(420,0,20,0);

            //TextView tvDate = new TextView(context);
            //tvId.setPadding(20,0,20,0);

            TextView tvTime = new TextView(context);
            tvTime.setPadding(100,20,0,20);
            tvTime.setWidth(500);
            tvTime.setTextSize(20);

            ((LinearLayout)view).addView(tvTime);
            //((LinearLayout)view).addView(tvId);
            ((LinearLayout)view).addView(tvTitle);
            //((LinearLayout)view).addView(tvAge);
            //((LinearLayout) view).setOrientation(LinearLayout.HORIZONTAL);
            //((LinearLayout)view).addView(tvContent);
            //((LinearLayout)view).addView(tvDate);

            //((LinearLayout)view).addView(bt);    */

            holder = new Holder();
            //holder.tvId = tvId;
            holder.tvTitle = tvTitle;
            //holder.tvAge = tvAge;
            holder.tvContent = tvContent;
            //holder.tvDate = tvDate;
            holder.tvTime = tvTime;

            view.setTag(holder);
        }
        else{
            holder = (Holder) view.getTag();
        }

        Schedule schedule = (Schedule) getItem(i);
        //holder.tvId.setText(schedule.get_id() + " ");
        holder.tvTitle.setText(schedule.getTitle());
        //holder.tvAge.setText(schedule.getAge()+"");
        //holder.tvContent.setText(schedule.getContent()+" ");
        //holder.tvDate.setText(schedule.getDate()+" ");
        holder.tvTime.setText(t1+" : "+t2);


        return view;
    }
    private class Holder{
        public TextView tvId;
        public TextView tvTitle;
        //public TextView tvAge;
        public TextView tvContent;
        public TextView tvDate;
        public TextView tvTime;
    }
}