package firebase.project.com.messenger.Scehdule;

import android.os.Bundle;

import firebase.project.com.messenger.R;


import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;

public class ScheduleActivity extends FragmentActivity{

    //fragment////////////////
    android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
    //android.support.v4.app.FragmentTransaction fT = fm.beginTransaction();
    android.support.v4.app.Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        //BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //calendarView = findViewById(R.id.main_calendar);

        fragment = new MainFragment();
        final android.support.v4.app.FragmentTransaction fT = fm.beginTransaction();
        fT.replace(R.id.container, fragment).commit();

    }

    /*private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new MainFragment();

                    break;
                case R.id.navigation_dashboard:

                    return true;
                case R.id.navigation_notifications:

                    return true;
            }
            final android.support.v4.app.FragmentTransaction fT = fm.beginTransaction();
            fT.replace(R.id.container, fragment).commit();
            return true;
        }
    };

    public void changeFrag(int i){
        switch (i){
            case 1:
                fragment = new MainFragment();
                break;
            case 2:
                fragment = new StoreFragment();

        }

    }*/

}