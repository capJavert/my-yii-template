package tk.codetroopers.erscheduler.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Table(name = "Jobs")
public class Job extends Model implements Serializable {

    public Job() {
        super();
    }

    public Job(User user, String name, boolean priority) {
        super();
        this.user = user;
        this.name = name;
        this.priority = priority;
        save();
    }

    @Column(name = "User",onDelete = Column.ForeignKeyAction.CASCADE,onUpdate = Column.ForeignKeyAction.CASCADE)
    private User user;

    @SerializedName("posao")
    @Column(name = "name")
    private String name;

    @SerializedName("prioritet")
    @Column(name = "priority")
    private boolean priority;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        save();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        save();
    }

    public boolean isPriority() {
        return priority;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
        save();
    }
}
