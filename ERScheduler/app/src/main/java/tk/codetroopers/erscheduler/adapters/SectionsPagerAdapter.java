package tk.codetroopers.erscheduler.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import tk.codetroopers.erscheduler.R;
import tk.codetroopers.erscheduler.SchedulerApp;
import tk.codetroopers.erscheduler.fragments.HistoryFragment;
import tk.codetroopers.erscheduler.fragments.ShiftsFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ShiftsFragment();
            case 1:
                return new HistoryFragment();
            default:
                return new ShiftsFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return SchedulerApp.getInstance().getContexter().getStringValue(R.string.tab_shifts);
            case 1:
                return SchedulerApp.getInstance().getContexter().getStringValue(R.string.tab_history);
        }
        return null;
    }
}