package tk.codetroopers.erscheduler;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import tk.codetroopers.erscheduler.enums.AppStateEnum;
import tk.codetroopers.erscheduler.helpers.Contexter;
import tk.codetroopers.erscheduler.models.User;

public class SchedulerApp {

    protected static SchedulerApp instance;
    protected static AppStateEnum appState;
    protected static User loggedUser;
    protected Contexter contexter;

    private SchedulerApp() {

    }

    public static SchedulerApp getInstance() {
        if (instance == null)
            instance = new SchedulerApp();
        return instance;
    }

    public static void setInstance(SchedulerApp instance) {
        SchedulerApp.instance = instance;
    }

    public SharedPreferences getPreferences() {
        return getPreferences();
    }

    public AppStateEnum getAppState() {
        return SchedulerApp.appState;
    }

    public void setAppState(AppStateEnum appState) {
        SchedulerApp.appState = appState;
    }

    public static User getLoggedUser() {
        return loggedUser;
    }

    public static void setLoggedUser(User loggedUser) {
        SchedulerApp.loggedUser = loggedUser;
    }

    public Contexter getContexter() {
        return contexter;
    }

    public void setContexter(Contexter contexter) {
        this.contexter = contexter;
    }

    public static boolean isRefreshClicked = false;
}
