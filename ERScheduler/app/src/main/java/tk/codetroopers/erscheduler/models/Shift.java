package tk.codetroopers.erscheduler.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import tk.codetroopers.erscheduler.SchedulerApp;

@Table(name = "Shifts")
public class Shift extends Model implements Serializable {

    public static final int DNEVNA_SMJENA = 1;
    public static final int NOCNA_SMJENA = 0;

    public Shift() {
        super();
    }

    public Shift(User user, int day, String central, String job, int type) {
        super();
        this.user = user;
        this.day = day;
        this.central = central;
        this.job = job;
        this.type = type;
        save();
    }

    @Column(name = "User")
    private User user;

    @Column(name = "day")
    @SerializedName("dan")
    private int day;

    @Column(name = "central")
    @SerializedName("ispostava")
    private String central;

    @Column(name = "job")
    @SerializedName("posao")
    private String job;

    @Column(name = "type")
    @SerializedName("smjena")
    private int type;

    //@SerializedName("clanovi_tima")
    private List<TeamMate> teamMates;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
        save();
    }

    public String getCentral() {
        return central;
    }

    public void setCentral(String central) {
        this.central = central;
        save();
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
        save();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
        save();
    }

    public List<TeamMate> getTeamMates() {
        return new Select()
                .from(TeamMate.class)
                .where("Shift = ?", this.getId())
                .execute();
    }

    public void setTeamMates(List<TeamMate> teamMates) {
        /*this.teamMates = teamMates;
        List<TeamMate> oldTeamMates = getTeamMates();
        for (TeamMate teamMate : oldTeamMates)
            teamMate.delete();
        for (TeamMate teamMate : teamMates) {
            TeamMate newTeamMate = new TeamMate(this, teamMate.getName(), teamMate.getSurname());
        }
        */
    }

    public static List<Shift> getAllShifts() {
        return new Select().from(Shift.class).execute();
    }

    public static void clearAllShifts() {
        new Delete().from(Shift.class).execute();
    }

    public static void saveAllShifts(List<Shift> shifts) {
        for (Shift shift : shifts) {
            Shift newShift = new Shift(SchedulerApp.getLoggedUser(), shift.getDay(), shift.getCentral(), shift.getJob(), shift.getType());
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        save();
    }
}
