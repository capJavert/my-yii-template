package tk.codetroopers.erscheduler.mvp.interactor;

import tk.codetroopers.erscheduler.listeners.ExtraListener;

public interface LoginInteractor {
    void login(String username, String password, ExtraListener listener);
}
