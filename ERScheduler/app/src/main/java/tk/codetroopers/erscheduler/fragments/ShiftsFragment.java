package tk.codetroopers.erscheduler.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tk.codetroopers.erscheduler.R;
import tk.codetroopers.erscheduler.adapters.ShiftAdapter;
import tk.codetroopers.erscheduler.models.Shift;
import tk.codetroopers.erscheduler.models.TeamMate;

public class ShiftsFragment extends BaseFragment {

    ShiftAdapter adapter;

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

    private void refreshShifts() {
        //if (user.getHouses().size() > 0)
        //    linearLayoutEmpty.setVisibility(View.INVISIBLE);
        //else
        //    linearLayoutEmpty.setVisibility(View.VISIBLE);

        //TEST
        /*
        {
            for (int i = 0; i < 4; i++) {
                Shift shift = new Shift(new Date(), "Danas");
                TeamMate teamMate = new TeamMate(shift, "Ivan", "Sušec");
                TeamMate teamMate2 = new TeamMate(shift, "Ante", "Barić");
            }
        }
        */

        adapter = new ShiftAdapter(getActivity(), new ArrayList<Shift>());
        List<Shift> shifts = Shift.getAllShifts();
        adapter.addAll(shifts);
        listView.setAdapter(adapter);
    }
}
