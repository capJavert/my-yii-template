package tk.codetroopers.erscheduler.mvp.view;

import tk.codetroopers.erscheduler.enums.ActivityEnum;
import tk.codetroopers.erscheduler.enums.FragmentEnum;

public interface ActivityView {
    void showFragment(FragmentEnum fragmentEnum, boolean addToBackStack);

    void showActivity(ActivityEnum activityEnum);

    void showToast(String toastMessage);

    void showTitle(String title);

    void showMessage(String message);

    void pressBack();

    void clearBackStack();

    void clearBackStack(int levelsIgnored);
}
