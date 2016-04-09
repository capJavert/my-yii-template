package tk.codetroopers.erscheduler.mvp.presenter.impl;

import tk.codetroopers.erscheduler.mvp.interactor.TemplateInteractor;
import tk.codetroopers.erscheduler.mvp.interactor.impl.TemplateInteractorImpl;
import tk.codetroopers.erscheduler.mvp.presenter.TemplatePresenter;
import tk.codetroopers.erscheduler.mvp.view.TemplateView;

public class TemplatePresenterImpl implements TemplatePresenter {

    private TemplateView view;
    private TemplateInteractor interactor;

    public TemplatePresenterImpl(TemplateView view) {
        this.view = view;
        this.interactor = new TemplateInteractorImpl();
    }
}
