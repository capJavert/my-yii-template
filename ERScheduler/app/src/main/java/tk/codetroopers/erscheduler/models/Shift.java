package tk.codetroopers.erscheduler.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name = "Shifts")
public class Shift extends Model implements Serializable {
    public Shift() {
        super();
    }

    public Shift(Date date, String central) {
        super();
        this.date = date;
        this.central = central;
        save();
    }

    @Column(name = "date")
    @SerializedName("datum")
    private Date date;

    @Column(name = "central")
    @SerializedName("ispostava")
    private String central;

    @SerializedName("clanovi_tima")
    private List<TeamMate> teamMates;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
        save();
    }

    public String getCentral() {
        return central;
    }

    public void setCentral(String central) {
        this.central = central;
        save();
    }

    public List<TeamMate> getTeamMates() {
        return new Select()
                .from(TeamMate.class)
                .where("Shift = ?", this.getId())
                .execute();
    }

    public void setTeamMates(List<TeamMate> teamMates) {
        this.teamMates = teamMates;
        List<TeamMate> oldTeamMates = getTeamMates();
        for (TeamMate teamMate : oldTeamMates)
            teamMate.delete();
        for (TeamMate teamMate : teamMates) {
            TeamMate newTeamMate = new TeamMate(this, teamMate.getName(), teamMate.getSurname());
        }
    }

    public static List<Shift> getAllShifts() {
        return new Select().from(Shift.class).execute();
    }
}
