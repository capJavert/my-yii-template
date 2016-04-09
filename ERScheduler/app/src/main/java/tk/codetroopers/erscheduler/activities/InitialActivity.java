package tk.codetroopers.erscheduler.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import tk.codetroopers.erscheduler.R;
import tk.codetroopers.erscheduler.SchedulerApp;
import tk.codetroopers.erscheduler.enums.FragmentEnum;

public class InitialActivity extends BaseActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SchedulerApp.getInstance().setContexter(this);

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }/*
            if(SaveSharedPreferences.checkSavedUser())
            {
                DbUser user = DbUser.checkExistingUser(SaveSharedPreferences.getUserName(Globals.getInstance().getContext()));
                Globals.getInstance().setDbUser(user);
                showActivity(ActivityEnum.MainActivity);
            }
            else
            {*/
                showFragment(FragmentEnum.LoginFragment, true);
            //}
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_inital, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1 ) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
            super.onBackPressed();
        }
    }
}
