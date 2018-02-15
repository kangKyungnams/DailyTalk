package firebase.project.com.messenger.Scehdule;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import firebase.project.com.messenger.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {

    Fragment f;
    TextView tv_title;
    TextView tv_content;
    TextView tv_time;
    TextView tv_date;
    Button bt_delete;

    String title, content, time, date;
    ScheduleList list;

    int ids;
    DBHelper helper;
    Schedule schedule;

    FirebaseDatabase database;
    private DatabaseReference mChatRef;
    private FirebaseUser mFirebaseUser;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        ids = getArguments().getInt("ids");

        f = new MainFragment();
        if (helper == null)
            helper = new DBHelper(getActivity(), "SCHEDULE", null, 1);
        schedule = helper.getSchedule_id(ids);

        tv_title = (TextView) view.findViewById(R.id.frag_detail_title);
        tv_content = (TextView) view.findViewById(R.id.frag_detail_content);
        tv_time = (TextView) view.findViewById(R.id.frag_detail_time);
        tv_date = (TextView) view.findViewById(R.id.frag_detail_date);
        bt_delete = (Button) view.findViewById(R.id.frag_detail_delete);

        database = FirebaseDatabase.getInstance();
        mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mChatRef = database.getReference("users").child(mFirebaseUser.getUid()).child("schedule");

        title = schedule.getTitle();
        content = schedule.getContent();
        time = schedule.getTime();
        date = schedule.getDate();

        String date2 = date.substring(0,4);
        String date3 = date.substring(4,6);
        String date4 = date.substring(6,8);
        String time1 = time.substring(0,2);
        String time2 = time.substring(2,4);


        tv_title.setText(title);
        tv_date.setText(date2+"년 "+date3+"월 "+date4+"일");
        tv_time.setText(time1+"시 "+time2+"분");
        tv_content.setText(content);


        bt_delete.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (helper == null)
                    helper = new DBHelper(getActivity(), "SCHEDULE", null, 1);
                helper.delete(ids);

                //////////firebase에서 삭제
                mChatRef.child(date).removeValue();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, f).commit();
            }
        });


        // Inflate the layout for this fragment
        return view;
    }

}
