package tk.codetroopers.erscheduler.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import tk.codetroopers.erscheduler.R;
import tk.codetroopers.erscheduler.models.Shift;
import tk.codetroopers.erscheduler.mvp.view.ShiftsView;

public class HistoryFragment extends BaseFragment  implements ShiftsView {

    LinearLayout linearLayoutEmpty;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.history_fragment, container, false);
        linearLayoutEmpty = (LinearLayout) rootView.findViewById(R.id.empty_list);
        return rootView;
    }

    @Override
    public void refreshShifts() {
        if (Shift.getAllShifts().size() > 0)
            linearLayoutEmpty.setVisibility(View.INVISIBLE);
        else
            linearLayoutEmpty.setVisibility(View.VISIBLE);
    }
}
