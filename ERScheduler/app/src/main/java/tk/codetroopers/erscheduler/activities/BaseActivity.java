package tk.codetroopers.erscheduler.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import tk.codetroopers.erscheduler.R;
import tk.codetroopers.erscheduler.SchedulerApp;
import tk.codetroopers.erscheduler.enums.ActivityEnum;
import tk.codetroopers.erscheduler.enums.AppStateEnum;
import tk.codetroopers.erscheduler.enums.FragmentEnum;
import tk.codetroopers.erscheduler.fragments.BaseFragment;
import tk.codetroopers.erscheduler.fragments.HomeFragment;
import tk.codetroopers.erscheduler.helpers.Creator;
import tk.codetroopers.erscheduler.mvp.view.ActivityView;

public class BaseActivity extends AppCompatActivity implements ActivityView, BaseFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onFragmentInteraction(Uri uri) { }

    @Override
    public void showActivity(ActivityEnum activityEnum)
    {
        if(SchedulerApp.getInstance().getAppState() != AppStateEnum.NotSignedIn) {
            Class activityClass = Creator.getActivityFromEnum(activityEnum);
            Intent intent = new Intent(this, activityClass);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void showFragment(FragmentEnum fragmentEnum, boolean addToBackStack) {
        AppStateEnum appStateEnum = SchedulerApp.getInstance().getAppState();
        Fragment fragment = Creator.getFragmentFromEnum(fragmentEnum);

        if (fragment != null) {
            if(fragment.getClass() == HomeFragment.class && appStateEnum == AppStateEnum.SignedIn)
                return;

            fragment.setArguments(getIntent().getExtras());

            if (addToBackStack) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment,fragment.getClass().toString())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack(fragment.getClass().toString())
                        .commit();
            }
            else {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment,fragment.getClass().toString())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }
        }
    }

    @Override
    public void showToast(String toastMessage) {
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void pressBack() {
        onBackPressed();
    }

    @Override
    public void clearBackStack() {
        for (int i = 1; i < getSupportFragmentManager().getBackStackEntryCount(); i++) {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void clearBackStack(int levelsIgnored) {
        for (int i = 1 + levelsIgnored; i < getSupportFragmentManager().getBackStackEntryCount(); i++) {
            getSupportFragmentManager().popBackStack();
        }
    }
}
