package tk.codetroopers.erscheduler.helpers;

import android.support.v4.app.Fragment;

import tk.codetroopers.erscheduler.activities.InitialActivity;
import tk.codetroopers.erscheduler.activities.MainActivity;
import tk.codetroopers.erscheduler.enums.ActivityEnum;
import tk.codetroopers.erscheduler.enums.FragmentEnum;
import tk.codetroopers.erscheduler.fragments.HomeFragment;
import tk.codetroopers.erscheduler.fragments.LoginFragment;

public class Creator {

    public static Fragment getFragmentFromEnum(FragmentEnum fragmentEnum){
        switch (fragmentEnum){
            case LoginFragment: return new LoginFragment();
            case HomeFragment: return new HomeFragment();
            default: return new LoginFragment();
        }
    }

    public static Class getActivityFromEnum(ActivityEnum activityEnum){
        switch (activityEnum){
            case InitialActivity: return InitialActivity.class;
            case MainActivity: return MainActivity.class;
            default: return InitialActivity.class;
        }
    }
}

