package tk.codetroopers.erscheduler.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tk.codetroopers.erscheduler.R;
import tk.codetroopers.erscheduler.SchedulerApp;
import tk.codetroopers.erscheduler.models.User;

public class UserFragment extends BaseFragment {

    TextView tvName;
    TextView tvSurname;
    TextView tvOIB;
    TextView tvBirth;
    TextView tvAddress;
    TextView tvPlace;
    TextView tvPhoneNumber;
    TextView tvMobileNumber;
    TextView tvCentral;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.user_fragment, container, false);

        initializeViews(rootView);
        fillViews();

        return rootView;
    }

    private void initializeViews(View view) {
        tvName = (TextView) view.findViewById(R.id.name_value);
        tvSurname= (TextView) view.findViewById(R.id.surname_value);
        tvOIB= (TextView) view.findViewById(R.id.oib_value);
        tvBirth= (TextView) view.findViewById(R.id.birth_value);
        tvAddress= (TextView) view.findViewById(R.id.address_value);
        tvPlace= (TextView) view.findViewById(R.id.place_value);
        tvPhoneNumber= (TextView) view.findViewById(R.id.phone_number_value);
        tvMobileNumber= (TextView) view.findViewById(R.id.mobile_number_value);
        tvCentral= (TextView) view.findViewById(R.id.central_value);
    }

    private void fillViews() {
        User user = SchedulerApp.getLoggedUser();
        tvName.setText(user.getName());
        tvSurname.setText(user.getSurname());
        tvOIB.setText(user.getOib());
        tvBirth.setText(user.getBirthDate());
        tvAddress.setText(user.getAddress());
        tvPlace.setText(user.getPlace());
        tvPhoneNumber.setText(user.getPhoneNumber());
        tvMobileNumber.setText(user.getMobileNumber());
        tvCentral.setText(user.getCentral());
    }
}
