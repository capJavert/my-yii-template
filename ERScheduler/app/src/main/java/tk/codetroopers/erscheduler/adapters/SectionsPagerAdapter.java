package tk.codetroopers.erscheduler.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import tk.codetroopers.erscheduler.R;
import tk.codetroopers.erscheduler.SchedulerApp;
import tk.codetroopers.erscheduler.fragments.HistoryFragment;
import tk.codetroopers.erscheduler.fragments.ShiftsFragment;
import tk.codetroopers.erscheduler.fragments.UserFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public static final int USER_INFO = 0;
    public static final int SHIFTS_LIST = 1;
    public static final int HISTORY_OF_SHIFTS = 2;

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case USER_INFO:
                return new UserFragment();
            case SHIFTS_LIST:
                return new ShiftsFragment();
            case HISTORY_OF_SHIFTS:
                return new HistoryFragment();
            default:
                return new ShiftsFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case USER_INFO:
                return SchedulerApp.getInstance().getContexter().getStringValue(R.string.tab_user);
            case SHIFTS_LIST:
                return SchedulerApp.getInstance().getContexter().getStringValue(R.string.tab_shifts);
            case HISTORY_OF_SHIFTS:
                return SchedulerApp.getInstance().getContexter().getStringValue(R.string.tab_history);
        }
        return null;
    }
}