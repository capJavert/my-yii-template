package tk.codetroopers.erscheduler.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tk.codetroopers.erscheduler.R;
import tk.codetroopers.erscheduler.SchedulerApp;
import tk.codetroopers.erscheduler.adapters.SectionsPagerAdapter;
import tk.codetroopers.erscheduler.enums.ActivityEnum;
import tk.codetroopers.erscheduler.gcm.GcmRegistrationIntentService;
import tk.codetroopers.erscheduler.models.BaseReponse;
import tk.codetroopers.erscheduler.models.User;
import tk.codetroopers.erscheduler.mvp.presenter.MainPresenter;
import tk.codetroopers.erscheduler.mvp.presenter.impl.MainPresenterImpl;
import tk.codetroopers.erscheduler.mvp.view.ActivityView;
import tk.codetroopers.erscheduler.mvp.view.MainView;
import tk.codetroopers.erscheduler.network.ApiModule;
import tk.codetroopers.erscheduler.network.ApiService;

public class MainActivity extends BaseActivity implements MainView, ActivityView {

    boolean doubleBackToLogoutPressedOnce = false;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    Toolbar toolbar;
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SchedulerApp.getInstance().setContexter(this);
        mainPresenter = new MainPresenterImpl(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        showTitle(getString(R.string.toolbar_app_title));
        toolbar.setNavigationIcon(R.drawable.ic_local_hospital_white_48dp);
        mViewPager = (ViewPager) findViewById(R.id.container);
        refreshShifts();
        mainPresenter.setupView();
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(SectionsPagerAdapter.SHIFTS_LIST);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String action = bundle.getString("action");
            if(SchedulerApp.getLoggedUser() != null) {
                //mViewPager.setCurrentItem(SectionsPagerAdapter.SHIFTS_LIST);
            }
        }

        startGcm();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id== R.id.sync_shifts) {
            SchedulerApp.isRefreshClicked = true;
            refreshShifts();
            mViewPager.setCurrentItem(1);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            doubleBackPress();
        }
    }

    @Override
    public void logout() {
        showActivity(ActivityEnum.InitialActivity);
        mainPresenter.logout();
    }

    private void doubleBackPress() {
        if (doubleBackToLogoutPressedOnce) {
            logout();
            User.clearUsers();
            finish();
            return;
        }

        this.doubleBackToLogoutPressedOnce = true;
        Toast.makeText(this, R.string.press_back_again_logout, Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToLogoutPressedOnce = false;
            }
        }, 2000);
    }

    private void startGcm() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String gcmToken = preferences.getString(GcmRegistrationIntentService.GCM_TOKEN, "");
        if (gcmToken.isEmpty()) {
            Intent intent = new Intent(this, GcmRegistrationIntentService.class);
            startService(intent);
            Log.i("GCM: ", "Starting...");
        } else {
            Log.i("GCM: ", "Already registered. Token: " + gcmToken);
        }
    }

    private void refreshShifts(){
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }
}
