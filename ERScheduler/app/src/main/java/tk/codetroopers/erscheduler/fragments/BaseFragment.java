package tk.codetroopers.erscheduler.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import tk.codetroopers.erscheduler.R;
import tk.codetroopers.erscheduler.enums.FragmentEnum;
import tk.codetroopers.erscheduler.mvp.view.ActivityView;
import tk.codetroopers.erscheduler.mvp.view.FragmentView;

public class BaseFragment extends Fragment implements FragmentView {

    private OnFragmentInteractionListener mListener;

    public BaseFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base, container, false);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void refresh() {
    }

    @Override
    public void showMessage(String message) {
    }

    @Override
    public void showError(String errorMessage) {
    }

    @Override
    public void showFragment(FragmentEnum fragmentEnum, boolean addToBackStack) {
        ((ActivityView) getContext()).showFragment(fragmentEnum, addToBackStack);
    }

    @Override
    public void showToast(String message) {
        ((ActivityView) getContext()).showToast(message);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    protected void setupParent(View view) {
        //Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard();
                    return false;
                }
            });
        }
        //If a layout container, iterate over children
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupParent(innerView);
            }
        }
    }

    private void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);

        try {
            inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        } catch (Exception ex){
            Log.d(getClass().toString(),"InputMethodManager error: " + ex.getMessage());
        }

    }
}
