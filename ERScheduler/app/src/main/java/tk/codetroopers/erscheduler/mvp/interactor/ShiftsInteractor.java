package tk.codetroopers.erscheduler.mvp.interactor;

import tk.codetroopers.erscheduler.listeners.ShiftsListener;

public interface ShiftsInteractor {
    void getNewShifts(ShiftsListener listener);
}
