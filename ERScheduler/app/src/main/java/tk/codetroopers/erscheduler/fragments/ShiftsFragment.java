package tk.codetroopers.erscheduler.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tk.codetroopers.erscheduler.R;
import tk.codetroopers.erscheduler.SchedulerApp;
import tk.codetroopers.erscheduler.adapters.ShiftAdapter;
import tk.codetroopers.erscheduler.models.Shift;
import tk.codetroopers.erscheduler.models.User;
import tk.codetroopers.erscheduler.mvp.presenter.ShiftsPresenter;
import tk.codetroopers.erscheduler.mvp.presenter.impl.ShiftsPresenterImpl;
import tk.codetroopers.erscheduler.mvp.view.ShiftsView;

public class ShiftsFragment extends BaseFragment implements ShiftsView {
    ListView listView;
    LinearLayout linearLayoutEmpty;

    ShiftsPresenter presenter;
    TextView tvLastRefresh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.shifts_fragment, container, false);
        listView = (ListView) rootView.findViewById(R.id.list_of_shifts);
        linearLayoutEmpty = (LinearLayout) rootView.findViewById(R.id.empty_list);
        tvLastRefresh = (TextView) rootView.findViewById(R.id.tvLastRefresh);

        tvLastRefresh.setText(SchedulerApp.getLoggedUser().getLastRefreshString());

        presenter = new ShiftsPresenterImpl(this);

        refreshShifts();

        return rootView;
    }

    @Override
    public void refreshShifts() {
        presenter.refreshShifts(listView, linearLayoutEmpty);

    }
}
