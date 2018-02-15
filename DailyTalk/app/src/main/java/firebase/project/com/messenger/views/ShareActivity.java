package firebase.project.com.messenger.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Iterator;

import butterknife.BindView;
import butterknife.ButterKnife;
import firebase.project.com.messenger.R;
import firebase.project.com.messenger.adapters.FriendListAdapter;
import firebase.project.com.messenger.models.User;

public class ShareActivity extends AppCompatActivity {




    @BindView(R.id.shareRecyclerView)
    RecyclerView mRecyclerView;
    private FriendListAdapter shareListAdapter;
    private FirebaseUser mFirebaseUser;

    private FirebaseAuth mFirebaseAuth;

    private FirebaseDatabase mFirebaseDb;

    private DatabaseReference mFriendsDBRef;
    private DatabaseReference mUserDBRef;
    private DatabaseReference mShareDBRef;
    private String name;
    private String[] temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        name = getIntent().getStringExtra("name");

        ButterKnife.bind(this);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mFirebaseDb = FirebaseDatabase.getInstance();

        mFriendsDBRef = mFirebaseDb.getReference("users").child(mFirebaseUser.getUid()).child("friends");
        mShareDBRef = mFirebaseDb.getReference("chat_members").child(name);
        mUserDBRef = mFirebaseDb.getReference("users");




        mShareDBRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Iterator<DataSnapshot> share = dataSnapshot.getChildren().iterator();
                int i = 0;
                while(share.hasNext()){
                    final User user = share.next().getValue(User.class);
                    temp = new String[5];
                    temp[i] = user.getUid();
                    i++;
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


/*
        int i = 0;

        shareListAdapter = new FriendListAdapter(i);
        mRecyclerView.setAdapter(shareListAdapter);
        addShareListener();
*/


    }
/*
    private void addShareListener(){

        mShareDBRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //User friend = dataSnapshot.getValue(User.class);
                Schedule schedule = dataSnapshot.getValue(Schedule.class);
                // 2. 가져온 데이터를 통해서 recyclerview의 아답터에 아이템을 추가 시켜줍니다. (UI)갱신
                drawUI(schedule);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private void drawUI(Schedule friend){
        shareListAdapter.addItem2(friend);

    }
*/
}
