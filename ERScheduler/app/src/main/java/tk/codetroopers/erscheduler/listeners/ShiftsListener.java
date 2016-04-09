package tk.codetroopers.erscheduler.listeners;

import java.util.List;

import tk.codetroopers.erscheduler.models.Shift;

public interface ShiftsListener {
    void onSuccess(List<Shift> shifts);
    void onFailure();
}
