package tk.codetroopers.erscheduler.mvp.presenter.impl;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import tk.codetroopers.erscheduler.adapters.ShiftAdapter;
import tk.codetroopers.erscheduler.listeners.ShiftsListener;
import tk.codetroopers.erscheduler.models.Shift;
import tk.codetroopers.erscheduler.mvp.interactor.ShiftsInteractor;
import tk.codetroopers.erscheduler.mvp.interactor.TemplateInteractor;
import tk.codetroopers.erscheduler.mvp.interactor.impl.ShiftsInteractorImpl;
import tk.codetroopers.erscheduler.mvp.interactor.impl.TemplateInteractorImpl;
import tk.codetroopers.erscheduler.mvp.presenter.ShiftsPresenter;
import tk.codetroopers.erscheduler.mvp.view.ShiftsView;
import tk.codetroopers.erscheduler.mvp.view.TemplateView;

public class ShiftsPresenterImpl implements ShiftsPresenter {

    private ShiftsView view;
    private ShiftsInteractor interactor;
    private ShiftAdapter adapter;
    private ListView listViewShifts;

    public ShiftsPresenterImpl(ShiftsView view) {
        this.view = view;
        this.interactor = new ShiftsInteractorImpl();
    }

    @Override
    public void refreshShifts(ListView listViewShifts) {
        this.listViewShifts = listViewShifts;
        interactor.getNewShifts(shiftsListener);
    }

    ShiftsListener shiftsListener = new ShiftsListener() {
        @Override
        public void onSuccess(List<Shift> shifts) {
            adapter = new ShiftAdapter(((Fragment) view).getContext(), new ArrayList<Shift>());
            //List<Shift> shifts = Shift.getAllShifts();
            adapter.addAll(shifts);
            listViewShifts.setAdapter(adapter);
        }

        @Override
        public void onFailure() {

        }
    };
}
