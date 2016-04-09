package tk.codetroopers.erscheduler.mvp.presenter.impl;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import tk.codetroopers.erscheduler.R;
import tk.codetroopers.erscheduler.SchedulerApp;
import tk.codetroopers.erscheduler.adapters.ShiftAdapter;
import tk.codetroopers.erscheduler.fragments.BaseFragment;
import tk.codetroopers.erscheduler.listeners.ShiftsListener;
import tk.codetroopers.erscheduler.models.Shift;
import tk.codetroopers.erscheduler.mvp.interactor.ShiftsInteractor;
import tk.codetroopers.erscheduler.mvp.interactor.TemplateInteractor;
import tk.codetroopers.erscheduler.mvp.interactor.impl.ShiftsInteractorImpl;
import tk.codetroopers.erscheduler.mvp.interactor.impl.TemplateInteractorImpl;
import tk.codetroopers.erscheduler.mvp.presenter.ShiftsPresenter;
import tk.codetroopers.erscheduler.mvp.view.ActivityView;
import tk.codetroopers.erscheduler.mvp.view.ShiftsView;
import tk.codetroopers.erscheduler.mvp.view.TemplateView;

public class ShiftsPresenterImpl implements ShiftsPresenter {

    private ShiftsView view;
    private ShiftsInteractor interactor;
    private ShiftAdapter adapter;
    private ListView listViewShifts;
    private LinearLayout linearLayoutEmpty;

    public ShiftsPresenterImpl(ShiftsView view) {
        this.view = view;
        this.interactor = new ShiftsInteractorImpl();
    }

    @Override
    public void refreshShifts(ListView listViewShifts, LinearLayout linearLayoutEmpty) {
        this.listViewShifts = listViewShifts;
        this.linearLayoutEmpty = linearLayoutEmpty;
        if (SchedulerApp.isRefreshClicked) {
            SchedulerApp.isRefreshClicked = false;
            interactor.getNewShifts(shiftsListener);
        } else {
            List<Shift> shifts = Shift.getAllShifts();
            adapter = new ShiftAdapter(((Fragment) view).getContext(), new ArrayList<Shift>());
            adapter.addAll(shifts);
            listViewShifts.setAdapter(adapter);
        }
        handleEmptyLayout();
    }

    @Override
    public void handleEmptyLayout() {
        if (linearLayoutEmpty != null) {
            if (Shift.getAllShifts().size() > 0)
                linearLayoutEmpty.setVisibility(View.INVISIBLE);
            else
                linearLayoutEmpty.setVisibility(View.VISIBLE);
        }
    }

    ShiftsListener shiftsListener = new ShiftsListener() {
        @Override
        public void onSuccess(List<Shift> shifts) {
            handleEmptyLayout();
            adapter = new ShiftAdapter(((Fragment) view).getContext(), new ArrayList<Shift>());
            adapter.addAll(shifts);
            listViewShifts.setAdapter(adapter);
            ((ActivityView) ((BaseFragment) view).getContext()).showToast(
                    SchedulerApp.getInstance().getContexter().getStringValue(R.string.shifts_refreshed));
        }

        @Override
        public void onFailure() {
            ((ActivityView) ((BaseFragment) view).getContext()).showToast(
                    SchedulerApp.getInstance().getContexter().getStringValue(R.string.shifts_refresh_failure));
        }
    };
}
