package tk.codetroopers.erscheduler.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import tk.codetroopers.erscheduler.R;
import tk.codetroopers.erscheduler.SchedulerApp;
import tk.codetroopers.erscheduler.enums.AppStateEnum;
import tk.codetroopers.erscheduler.mvp.presenter.HomePresenter;
import tk.codetroopers.erscheduler.mvp.presenter.impl.HomePresenterImpl;
import tk.codetroopers.erscheduler.mvp.view.ActivityView;
import tk.codetroopers.erscheduler.mvp.view.HomeView;

public class HomeFragment extends BaseFragment implements HomeView {

    TextView TVMessage;

    private HomePresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewInflater = inflater.inflate(R.layout.home_fragment, container, false);
        TVMessage = (TextView) viewInflater.findViewById(R.id.TVMessage);

        presenter = new HomePresenterImpl(this);

       // ((ActivityView) getContext()).showTitle(getContext().getString(R.string.home_fragment_title));
        SchedulerApp.getInstance().setAppState(AppStateEnum.SignedIn);

        //showMessage("Hello mate.");

        initilizeEvents();

        return viewInflater;
    }

    @Override
    public void showMessage(String message) {
        //TVMessage.setText(message);
    }

    private void initilizeEvents() {

    }
}