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
                week = "Ovaj tjedan";
            else
                week = (dayOrder+7)/7 + ". tjedan";

            String dayName = getDayNameWeek(correction) + " ("+ week + ")";
            return dayName;
        }
    }

    private static String getDayNameWeek(int dayOfWeek) {
        switch (dayOfWeek){
            case 1: return "Nedjelja";
            case 2: return "Ponedjeljak";
            case 3: return "Utorak";
            case 4: return "Srijeda";
            case 5: return "Četvrtak";
            case 6: return "Petak";
            case 7: return "Subota";
            default: return "NONE";
        }
    }

    private static String getString(int resId){
        return SchedulerApp.getInstance().getContexter().getStringValue(resId);
    }
}
