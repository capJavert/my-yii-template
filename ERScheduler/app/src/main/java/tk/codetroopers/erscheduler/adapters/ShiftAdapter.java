package tk.codetroopers.erscheduler.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import tk.codetroopers.erscheduler.R;
import tk.codetroopers.erscheduler.SchedulerApp;
import tk.codetroopers.erscheduler.helpers.DayParser;
import tk.codetroopers.erscheduler.models.Shift;
import tk.codetroopers.erscheduler.models.TeamMate;


public class ShiftAdapter extends ArrayAdapter<Shift> {
    public ShiftAdapter(Context context, ArrayList<Shift> shifts) {
        super(context, 0, shifts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Shift shift = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.shift_item, parent, false);
        }

        TextView tvDate = (TextView) convertView.findViewById(R.id.shiftDate);
        TextView tvTime = (TextView) convertView.findViewById(R.id.shiftTime);
        TextView tvCentral = (TextView) convertView.findViewById(R.id.shiftCentral);
        TextView tvTeamMates = (TextView) convertView.findViewById(R.id.shiftTeamMates);

        String teamMates = "";
        /*
        int counter = 0;
        String comma = ", ";
        for (TeamMate teamMate : shift.getTeamMates()) {
            teamMates += teamMate.getName() + " " + teamMate.getSurname();
            if (shift.getTeamMates().size() > counter + 1)
                teamMates += comma;
            counter++;
        }
        */
        String smjena = "";
        if(shift.getType() == Shift.DNEVNA_SMJENA)
            smjena = SchedulerApp.getInstance().getContexter().getStringValue(R.string.day_shift);
        else
            smjena = SchedulerApp.getInstance().getContexter().getStringValue(R.string.night_shift);

        //tvDate.setText("Dan " + shift.getDay() + ".");
        tvDate.setText(DayParser.getDayName(shift.getDay()));
        tvTime.setText(smjena);
        tvCentral.setText(shift.getCentral());
        tvTeamMates.setText(teamMates);

        return convertView;
    }
}

