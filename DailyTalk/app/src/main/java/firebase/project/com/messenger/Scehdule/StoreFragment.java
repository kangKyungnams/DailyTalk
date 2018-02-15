package firebase.project.com.messenger.Scehdule;


import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Hashtable;

import firebase.project.com.messenger.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StoreFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoreFragment extends Fragment {

    EditText et_title;
    EditText et_content;
    //Button bt_select_time;
    Button bt_store;
    Button bt_cancel;
    TimePicker timePicker;
    String time;
    String hour;
    String min;
    String date;
    int count;
    Fragment f;
    private FirebaseUser mFirebaseUser;

    private FirebaseAuth mFirebaseAuth;


    private FirebaseDatabase mFirebaseDb;

    private DatabaseReference mFriendsDBRef;
    private DatabaseReference mUserDBRef;


    DBHelper helper;
    FirebaseDatabase database;
    DatabaseReference myRef;

    public StoreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store, null);

        database = FirebaseDatabase.getInstance();
        date = getArguments().getString("date");
        Toast.makeText(getActivity(), date, Toast.LENGTH_SHORT).show();

        et_title = (EditText)view.findViewById(R.id.frag_store_title);
        et_content = (EditText) view.findViewById(R.id.frag_store_content);
        //bt_select_time = (Button) view.findViewById(R.id.frag_store_select_time);
        bt_store = (Button) view.findViewById(R.id.frag_store_store);
        bt_cancel = (Button)view.findViewById(R.id.frag_store_cancel);

        timePicker = (TimePicker)view.findViewById(R.id.frag_store_timePicker);
        timePicker.setIs24HourView(true);
        int h = timePicker.getHour();
        int m = timePicker.getMinute();
        if (h < 10) hour = "0" + Integer.toString(h);
        else hour = Integer.toString(h);
        if (m < 10) min = "0" + Integer.toString(m);
        else min = Integer.toString(m);
        time = hour + min;

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDb = FirebaseDatabase.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mUserDBRef = mFirebaseDb.getReference("users");

        count = 0;
        f = new MainFragment();


        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                if (i < 10) hour = "0" + Integer.toString(i);
                else hour = Integer.toString(i);

                if (i1 < 10) min = "0" + Integer.toString(i1);
                else min = Integer.toString(i1);

                time = hour + min;
            }
        });
        /*bt_select_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                CustomTimePickerDialog timePickerDialog = new CustomTimePickerDialog(getActivity(), listener, 13, 0, true);
                timePickerDialog.show();
            }
        });*/

        bt_store.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (et_title.getText().toString().equals(""))
                    Toast.makeText(getActivity(), "제목을 입력하세요", Toast.LENGTH_SHORT).show();
                else if (et_content.getText().toString().equals(""))
                    Toast.makeText(getActivity(), "내용을 입력하세요", Toast.LENGTH_SHORT).show();
                else{
                    if (helper == null)
                        helper = new DBHelper(getActivity(), "SCHEDULE", null, 1);
                    Schedule sch = new Schedule();
                    sch.setDate(date);
                    sch.setTime(time);
                    sch.setTitle(et_title.getText().toString());
                    sch.setContent(et_content.getText().toString());
                    helper.addSchedule(sch);

                    String childId = date; //+ et_title.getText().toString();
                    myRef = mUserDBRef.child(mFirebaseUser.getUid()).child("schedule").child(childId);


                    Hashtable<String, String> table = new Hashtable<String, String>();
                    table.put("date", date);
                    table.put("time", time);
                    table.put("title", et_title.getText().toString());
                    table.put("content", et_content.getText().toString());
                    myRef.setValue(table);


                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, f).commit();

                }
            }
        });

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, f).commit();
            }
        });



        // Inflate the layout for this fragment
        return view;
    }

    /*private TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int h, int m) {
            time = Integer.toString(h)+Integer.toString(m);
            Toast.makeText(getActivity(), time, Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    public void onTimeChanged(TimePicker timePicker, int i, int i1) {
        time = Integer.toString(i)+Integer.toString(i1);
    }*/
}
