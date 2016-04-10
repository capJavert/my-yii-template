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
import tk.codetroopers.erscheduler.helpers.JobParser;
import tk.codetroopers.erscheduler.models.Shift;
import tk.codetroopers.erscheduler.models.TeamMate;


public class ShiftAdapter extends ArrayAdapter<Shift> {
    public ShiftAdapter(Context context, ArrayList<Shift> shifts) {
        super(context, 0, shifts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Shift shift = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.shift_item, parent, false);
        }

        TextView tvDate = (TextView) convertView.findViewById(R.id.shiftDate);
        TextView tvTime = (TextView) convertView.findViewById(R.id.shiftTime);
        TextView tvCentral = (TextView) convertView.findViewById(R.id.shiftCentral);
        TextView tvJob = (TextView) convertView.findViewById(R.id.shiftJob);

        String smjena = "";
        if(shift.getType() == Shift.DNEVNA_SMJENA)
            smjena = SchedulerApp.getInstance().getContexter().getStringValue(R.string.day_shift);
        else
            smjena = SchedulerApp.getInstance().getContexter().getStringValue(R.string.night_shift);

        tvDate.setText(DayParser.getDayName(shift.getDay()));
        tvTime.setText(smjena);
        tvCentral.setText(shift.getCentral());
        tvJob.setText(JobParser.getJobFullName(shift.getJob()));

        return convertView;
    }
}

