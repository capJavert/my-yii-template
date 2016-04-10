package tk.codetroopers.erscheduler.helpers;

import tk.codetroopers.erscheduler.R;
import tk.codetroopers.erscheduler.SchedulerApp;

public class JobParser {

    public static final String DOCTOR = "M";
    public static final String TECHNICIAN = "T";
    public static final String DRIVER = "V";
    public static final String DISPACHER = "D";

    public static String getJobFullName(String shortJobName){
        if(shortJobName != null) {
            switch (shortJobName) {
                case DOCTOR:
                    return SchedulerApp.getInstance().getContexter().getStringValue(R.string.job_doctor);
                case TECHNICIAN:
                    return SchedulerApp.getInstance().getContexter().getStringValue(R.string.job_technician);
                case DRIVER:
                    return SchedulerApp.getInstance().getContexter().getStringValue(R.string.job_driver);
                case DISPACHER:
                    return SchedulerApp.getInstance().getContexter().getStringValue(R.string.job_dispacher);
                default:
                    return SchedulerApp.getInstance().getContexter().getStringValue(R.string.job_undefined);
            }
        }
        return SchedulerApp.getInstance().getContexter().getStringValue(R.string.job_undefined);
    }
}
