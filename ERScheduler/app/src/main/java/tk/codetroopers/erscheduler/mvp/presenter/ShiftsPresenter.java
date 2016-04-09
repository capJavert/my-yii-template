package tk.codetroopers.erscheduler.mvp.presenter;

import android.widget.LinearLayout;
import android.widget.ListView;

public interface ShiftsPresenter {
    void refreshShifts(ListView listViewShifts, LinearLayout linearLayoutEmpty);
    void handleEmptyLayout();
}
