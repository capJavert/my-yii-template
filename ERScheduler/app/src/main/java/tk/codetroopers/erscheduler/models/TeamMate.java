package tk.codetroopers.erscheduler.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Table(name = "TeamMates")
public class TeamMate extends Model implements Serializable{

    public TeamMate(){
        super();
    }

    public TeamMate(Shift shift, String name, String surname) {
        super();
        this.shift = shift;
        this.name = name;
        this.surname = surname;
        save();
    }

    @Column(name = "Shift",onDelete = Column.ForeignKeyAction.CASCADE,onUpdate = Column.ForeignKeyAction.CASCADE)
    private Shift shift;

    @Column(name = "name")
    @SerializedName("name")
    private String name;

    @Column(name = "surname")
    @SerializedName("surname")
    private String surname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        save();
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
        save();
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
        save();
    }
}
