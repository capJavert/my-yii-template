package tk.codetroopers.erscheduler.helpers;

import java.util.Calendar;

import tk.codetroopers.erscheduler.R;
import tk.codetroopers.erscheduler.SchedulerApp;

public class DayParser {
    private static final int TODAY = 1;
    private static final int TOMORROW = 2;

    public static String getDayName(int dayOrder){
        String day = "";
        if(dayOrder ==TODAY)
            return getString(R.string.day_today);
        else if(dayOrder == TOMORROW)
            return getString(R.string.day_tomorrow);
        else {
            int weekDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
            int correction = (weekDay + dayOrder - 1) % 8;
            if(correction == 0)
                correction = 1;
            String week = "";
            if((dayOrder+7)/7 == 1)
                week = getString(R.string.this_week);
            else
                week = (dayOrder+7)/7 + getString(R.string.dot_week);

            String dayName = getDayNameWeek(correction) + " ("+ week + ")";
            return dayName;
        }
    }

    private static String getDayNameWeek(int dayOfWeek) {
        switch (dayOfWeek){
            case 1: return getString(R.string.day_sunday);
            case 2: return getString(R.string.day_monday);
            case 3: return getString(R.string.day_tuesday);
            case 4: return getString(R.string.day_wendesday);
            case 5: return getString(R.string.day_thursday);
            case 6: return getString(R.string.day_friday);
            case 7: return getString(R.string.day_saturday);
            default: return getString(R.string.day_undefined);
        }
    }

    private static String getString(int resId){
        return SchedulerApp.getInstance().getContexter().getStringValue(resId);
    }
}
