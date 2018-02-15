package firebase.project.com.messenger.Scehdule;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import firebase.project.com.messenger.R;


public class MainFragment extends Fragment{

    CalendarView c;
    Fragment f,ff;
    Bundle bundle;
    Bundle bundle2;
    ListView listview;
    List schedule, schedule2;
    ScheduleList list;

    Button add;

    DBHelper helper;
    FirebaseDatabase database;
    DatabaseReference myRef;

    int year, month, day;
    String m, d;
    String selectDate;
    private FirebaseUser mFirebaseUser;

    private FirebaseDatabase mFirebaseDatase;

    private DatabaseReference mChatRef;



    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_main, null);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();


        mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        //mFirebaseDatase = FirebaseDatabase.getInstance();
        mChatRef = database.getReference("users").child(mFirebaseUser.getUid()).child("schedule");



        f = new StoreFragment();

        bundle = new Bundle(1);
        bundle2 = new Bundle(1);

        c = view.findViewById(R.id.frag_main_calendar);
        listview = (ListView) view.findViewById(R.id.frag_main_listview);
        add = (Button) view.findViewById(R.id.frag_main_add);

        year = Calendar.getInstance().get(Calendar.YEAR);
        month = Calendar.getInstance().get(Calendar.MONTH);
        day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        if (month+1 < 10) m = "0" + Integer.toString(month+1);
        else m = Integer.toString(month+1);
        if (day < 10) d = "0"+Integer.toString(day);
        else d = Integer.toString(day);

        selectDate = Integer.toString(year)+m+d;

        schedule = new ArrayList();
        if (helper == null)
            helper = new DBHelper(getActivity(), "SCHEDULE", null, 1);


        mChatRef.child(selectDate).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Schedule sss = dataSnapshot.getValue(Schedule.class);
                schedule.add(sss);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        schedule2 = helper.getSchedule(selectDate);

        list = new ScheduleList(schedule2, getActivity());
        listview.setAdapter(list);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //ContactsContract.Data iid = (ContactsContract.Data) adapterView.getItemAtPosition(i);
                final Schedule a = (Schedule) list.getItem(i);
                int ids = a.get_id();
                /*if (helper == null)
                    helper = new DBHelper(getActivity(), "SCHEDULE", null, 1);
                helper.delete(ids);
                //Toast.makeText(getActivity(), String.valueOf(ids), Toast.LENGTH_SHORT).show();
                Snackbar.make(view, "일정을 삭제 하시겠습니까?", Snackbar.LENGTH_LONG).setAction("예", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mChatRef.child(a.getDate()).removeValue();
                    }
                }).show();*/

                bundle2.putInt("ids", ids);

                ff = new DetailFragment();
                ff.setArguments(bundle2);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, ff).addToBackStack("TEXT_VIEWER_BACKSTACK").commit();

            }
        });

        c.setOnDateChangeListener(new CalendarView.OnDateChangeListener()
        {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                if (i1+1 < 10) m = "0" + Integer.toString(i1+1);
                else m = Integer.toString(i1+1);
                if (i2 < 10) d = "0" + Integer.toString(i2);
                else d = Integer.toString(i2);
                selectDate = Integer.toString(i)+m+d;

                schedule = helper.getSchedule(selectDate);
                list = new ScheduleList(schedule, getActivity());
                listview.setAdapter(list);
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        //ContactsContract.Data iid = (ContactsContract.Data) adapterView.getItemAtPosition(i);
                        final Schedule a = (Schedule) list.getItem(i);
                        int ids = a.get_id();
                        /*if (helper == null)
                            helper = new DBHelper(getActivity(), "SCHEDULE", null, 1);
                        helper.delete(ids);

                        String childId = selectDate; //+ a.getTitle();
                        //myRef.child("uid").child("schedule")
                        //mChatRef.child(childId).setValue(null);
                        Snackbar.make(view, "일정을 삭제 하시겠습니까?", Snackbar.LENGTH_LONG).setAction("예", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mChatRef.child(a.getDate()).removeValue();
                            }
                        }).show();

                        //Toast.makeText(getActivity(), childId, Toast.LENGTH_SHORT).show();*/

                        bundle2.putInt("ids", ids);

                        ff = new DetailFragment();
                        ff.setArguments(bundle2);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, ff).addToBackStack("TEXT_VIEWER_BACKSTACK").commit();

                    }
                });

            }

        });

        add.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                bundle.putString("date", selectDate);
                f.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, f).commit();
            }
        });

        //addLister();

        return view;


    }
    /*private void addLister(){
        mChatRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                //notifyAll();
                //notifyDataSetChanged();
                Schedule item = dataSnapshot.getValue(Schedule.class);
                list.removeItem(item);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }*/
}