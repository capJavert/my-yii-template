package tk.codetroopers.erscheduler.mvp.presenter.impl;

import android.app.Activity;

import tk.codetroopers.erscheduler.enums.ActivityEnum;
import tk.codetroopers.erscheduler.enums.FragmentEnum;
import tk.codetroopers.erscheduler.fragments.HomeFragment;
import tk.codetroopers.erscheduler.mvp.interactor.HomeInteractor;
import tk.codetroopers.erscheduler.mvp.interactor.impl.HomeInteractorImpl;
import tk.codetroopers.erscheduler.mvp.presenter.HomePresenter;
import tk.codetroopers.erscheduler.mvp.view.ActivityView;
import tk.codetroopers.erscheduler.mvp.view.HomeView;

public class HomePresenterImpl implements HomePresenter {

    private HomeView view;
    private HomeInteractor interactor;

    public HomePresenterImpl(HomeView view) {
        this.view = view;
        this.interactor = new HomeInteractorImpl();
    }

    @Override
    public void goToHistory() {
        //((HomeFragment)view).showFragment(FragmentEnum.HomeFragment, true);
    }
}
