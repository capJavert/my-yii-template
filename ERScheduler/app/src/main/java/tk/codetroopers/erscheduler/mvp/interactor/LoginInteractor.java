package tk.codetroopers.erscheduler.mvp.interactor;

import tk.codetroopers.erscheduler.listeners.ExtraListener;
import tk.codetroopers.erscheduler.listeners.Listener;
import tk.codetroopers.erscheduler.models.User;

public interface LoginInteractor {
    void login(String username, String password, ExtraListener listener);
    void getUserData(Listener listener, User user);
}
