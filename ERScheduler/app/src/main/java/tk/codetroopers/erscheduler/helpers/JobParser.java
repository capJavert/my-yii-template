package tk.codetroopers.erscheduler.helpers;

public class JobParser {

    public static final String DOCTOR = "M";
    public static final String TECHNICIAN = "T";
    public static final String DRIVER = "V";
    public static final String DISPACHER = "D";

    public static String getJobFullName(String shortJobName){
        switch (shortJobName){
            case DOCTOR:
                return "Doktor";
            case TECHNICIAN:
                return "Tehničar";
            case DRIVER:
                return "Vozač";
            case DISPACHER:
                return "Dispečer";
            default:
                return "Nedefinirani posao";
        }
    }
}
