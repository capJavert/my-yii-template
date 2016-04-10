package tk.codetroopers.erscheduler.mvp.presenter.impl;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import tk.codetroopers.erscheduler.R;
import tk.codetroopers.erscheduler.SchedulerApp;
import tk.codetroopers.erscheduler.adapters.SectionsPagerAdapter;
import tk.codetroopers.erscheduler.listeners.Listener;
import tk.codetroopers.erscheduler.mvp.interactor.MainInteractor;
import tk.codetroopers.erscheduler.mvp.interactor.TemplateInteractor;
import tk.codetroopers.erscheduler.mvp.interactor.impl.MainInteractorImpl;
import tk.codetroopers.erscheduler.mvp.interactor.impl.TemplateInteractorImpl;
import tk.codetroopers.erscheduler.mvp.presenter.MainPresenter;
import tk.codetroopers.erscheduler.mvp.presenter.TemplatePresenter;
import tk.codetroopers.erscheduler.mvp.view.ActivityView;
import tk.codetroopers.erscheduler.mvp.view.MainView;
import tk.codetroopers.erscheduler.mvp.view.TemplateView;

public class MainPresenterImpl implements MainPresenter {

    private MainView view;
    private MainInteractor interactor;

    public MainPresenterImpl(MainView view) {
        this.view = view;
        this.interactor = new MainInteractorImpl();
    }

    @Override
    public void logout() {
        interactor.logout(listener);
    }

    @Override
    public void setupView() {

    }

    @Override
    public void refreshView() {

    }

    private Listener listener = new Listener() {
        @Override
        public void onSuccess() {

            ((ActivityView)view).showToast(SchedulerApp.getInstance().
                    getContexter().getStringValue(R.string.logout_successful));
        }

        @Override
        public void onFailure() {
            ((ActivityView)view).showToast(SchedulerApp.getInstance().
                    getContexter().getStringValue(R.string.logout_error));
        }
    };
}
