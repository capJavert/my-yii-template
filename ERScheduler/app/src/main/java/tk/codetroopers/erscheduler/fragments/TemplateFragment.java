package tk.codetroopers.erscheduler.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tk.codetroopers.erscheduler.R;
import tk.codetroopers.erscheduler.SchedulerApp;
import tk.codetroopers.erscheduler.enums.AppStateEnum;
import tk.codetroopers.erscheduler.mvp.presenter.TemplatePresenter;
import tk.codetroopers.erscheduler.mvp.presenter.impl.TemplatePresenterImpl;
import tk.codetroopers.erscheduler.mvp.view.ActivityView;
import tk.codetroopers.erscheduler.mvp.view.TemplateView;

public class TemplateFragment extends BaseFragment implements TemplateView {

    TextView TVMessage;

    private TemplatePresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewInflater = inflater.inflate(R.layout.template_layout, container, false);

        presenter = new TemplatePresenterImpl(this);

        TVMessage = (TextView) viewInflater.findViewById(R.id.TVMessage);
        TVMessage.setText("");

        ((ActivityView) getContext()).showTitle(getContext().getString(R.string.base_fragment_title));
        SchedulerApp.getInstance().setAppState(AppStateEnum.NotSignedIn);

        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion < android.os.Build.VERSION_CODES.LOLLIPOP) {
            //btn.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
        }

        setupParent(viewInflater);
        initilizeEvents();
        return viewInflater;
    }

    @Override
    public void showError(String error) {
        showMessage(error);
    }

    @Override
    public void showMessage(String message) {
        TVMessage.setText(message);
    }

    private void initilizeEvents() {

    }
}