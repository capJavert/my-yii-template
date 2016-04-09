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
import tk.codetroopers.erscheduler.mvp.view.ActivityView;
import tk.codetroopers.erscheduler.mvp.view.MainView;
import tk.codetroopers.erscheduler.network.ApiModule;
import tk.codetroopers.erscheduler.network.ApiService;

public class MainActivity extends BaseActivity implements MainView, ActivityView {

    boolean doubleBackToLogoutPressedOnce = false;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SchedulerApp.getInstance().setContexter(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        showTitle(getString(R.string.toolbar_app_title));

        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        mViewPager.setCurrentItem(1);

        boolean gcmRecieved = true;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String action = bundle.getString("action");
            if (action != null) {
                switch (action) {
                    case "home":
                        //showFragment(FragmentEnum.HomeFragment, true);
                        break;
                    default:
                        //showFragment(FragmentEnum.HomeFragment, true);
                }
            } else {
                gcmRecieved = false;
            }
        } else {
            gcmRecieved = false;
        }
        if (gcmRecieved == false) {
            //showFragment(FragmentEnum.HomeFragment, true);
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

        ApiService apiService = ApiModule.createService(ApiService.class,
                SchedulerApp.getLoggedUser().getToken());

        Call<BaseReponse> call = apiService.logout(SchedulerApp.getLoggedUser().getToken());
        call.enqueue(new Callback<BaseReponse>() {
            @Override
            public void onResponse(Call<BaseReponse> call, Response<BaseReponse> response) {
                if (response != null && response.body() != null && response.body().getSuccess() != null) {
                    showToast(getString(R.string.logout_successful));
                    User user = SchedulerApp.getLoggedUser();
                    user.setUsername("");
                    user.setPassword("");
                    user.setToken("");
                    User.clearUsers();
                    finish();
                } else {
                    showToast(getString(R.string.logout_token_error));
                }
                User.clearUsers();
                finish();
            }

            @Override
            public void onFailure(Call<BaseReponse> call, Throwable t) {
                showToast(getString(R.string.logout_error));
                User.clearUsers();
                finish();
            }
        });

    }

    private void doubleBackPress() {
        if (doubleBackToLogoutPressedOnce) {
            logout();
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
}
