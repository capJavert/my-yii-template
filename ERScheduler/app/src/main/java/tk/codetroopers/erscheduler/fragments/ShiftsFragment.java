package tk.codetroopers.erscheduler.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import tk.codetroopers.erscheduler.R;
import tk.codetroopers.erscheduler.adapters.ShiftAdapter;
import tk.codetroopers.erscheduler.models.Shift;
import tk.codetroopers.erscheduler.mvp.view.ShiftsView;

public class ShiftsFragment extends BaseFragment implements ShiftsView {

    ;

    ListView listView;

    public ShiftsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.shifts_fragment, container, false);
        listView = (ListView) rootView.findViewById(R.id.list_of_shifts);

        refreshShifts();

        return rootView;
    }

    @Override
    public void refreshShifts() {
        //if (user.getHouses().size() > 0)
        //    linearLayoutEmpty.setVisibility(View.INVISIBLE);
        //else
        //    linearLayoutEmpty.setVisibility(View.VISIBLE);

        //TEST
        /*
        {
            for (int i = 0; i < 4; i++) {
                Shift shift = new Shift(new Date(), "Centrala");
                TeamMate teamMate = new TeamMate(shift, "Ivan", "Sušec");
                TeamMate teamMate2 = new TeamMate(shift, "Ante", "Barić");
            }
        }
        */


    }
}
