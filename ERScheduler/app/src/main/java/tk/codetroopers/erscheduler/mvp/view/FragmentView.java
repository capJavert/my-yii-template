package tk.codetroopers.erscheduler.mvp.view;

import tk.codetroopers.erscheduler.enums.FragmentEnum;

public interface FragmentView {
    void refresh();

    void showMessage(String message);

    void showError(String errorMessage);

    void showFragment(FragmentEnum fragmentEnum, boolean addToBackStack);

    void showToast(String message);
}
