package tk.codetroopers.erscheduler.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Shifts implements Serializable{
    @SerializedName("raspored")
    private List<Shift> shifts;

    public List<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(List<Shift> shifts) {
        this.shifts = shifts;
    }
}
