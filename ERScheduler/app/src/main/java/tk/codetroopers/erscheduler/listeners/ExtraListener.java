package tk.codetroopers.erscheduler.listeners;

public interface ExtraListener {
    void onSuccess();
    void onFailure();
    void onFailure(String error);
}
