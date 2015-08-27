package com.storware.storwaretest;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new
                    MainFragment()).commit();
        }
    }

}
