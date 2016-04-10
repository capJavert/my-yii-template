package tk.codetroopers.erscheduler.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import tk.codetroopers.erscheduler.R;
import tk.codetroopers.erscheduler.SchedulerApp;

@Table(name = "Users")
public class User extends Model implements Serializable {

    public User() {
        super();
    }

    public User(String name, String surname, String username, String password, String token, String oib,
                String birthDate, String address, String place, String phoneNumber, String mobileNumber,
                String remark, String central, Integer numberOfHours, Date lastRefresh) {
        super();
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.token = token;
        this.oib = oib;
        this.birthDate = birthDate;
        this.address = address;
        this.place = place;
        this.phoneNumber = phoneNumber;
        this.mobileNumber = mobileNumber;
        this.remark = remark;
        this.central = central;
        this.numberOfHours = numberOfHours;
        this.lastRefresh = lastRefresh;
        save();
    }

    @Column(name = "name")
    @SerializedName("ime")
    private String name;

    @Column(name = "surname")
    @SerializedName("prezime")
    private String surname;

    @Column(name = "username")
    @SerializedName("username")
    private String username;

    @Column(name = "password")
    @SerializedName("password")
    private String password;

    @Column(name = "token")
    @SerializedName("token")
    private String token;

    @Column(name = "oib")
    @SerializedName("oib")
    private String oib;

    @Column(name = "birthDate")
    @SerializedName("dat_rod")
    private String birthDate;

    @Column(name = "address")
    @SerializedName("adresa_stanovanja")
    private String address;

    @Column(name = "place")
    @SerializedName("mjesto_stanovanja")
    private String place;

    @Column(name = "phoneNumber")
    @SerializedName("broj_tel")
    private String phoneNumber;

    @Column(name = "mobileNumber")
    @SerializedName("mob")
    private String mobileNumber;

    @Column(name = "remark")
    @SerializedName("napomena")
    private String remark;

    @Column(name = "central")
    @SerializedName("ispostava")
    private String central;

    @Column(name = "numberOfHours")
    @SerializedName("broj_sati")
    private Integer numberOfHours;

    @Column(name = "lastRefresh")
    private Date lastRefresh;

    @SerializedName("jobs")
    private List<Job> jobs;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        save();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        save();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        save();
    }

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
        save();
    }

    public String getBirthDate() {
        return birthDate;
    }
    public String getBirthDateCro(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(getBirthDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(date != null){
            SimpleDateFormat formatCro = new SimpleDateFormat("dd.MM.yyyy.");
            return formatCro.format(date);
        } else {
            return getBirthDate();
        }
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
        save();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        save();
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
        save();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        save();
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        save();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
        save();
    }

    public String getCentral() {
        return central;
    }

    public void setCentral(String central) {
        this.central = central;
        save();
    }

    public Integer getNumberOfHours() {
        return numberOfHours;
    }

    public void setNumberOfHours(Integer numberOfHours) {
        this.numberOfHours = numberOfHours;
        save();
    }

    public List<Job> getJobs() {
        return new Select()
                .from(Job.class)
                .execute();
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
        List<Job> oldJobs = getJobs();
        for (Job job : oldJobs)
            job.delete();
        for (Job job : jobs) {
            Job newJob = new Job(this, job.getName(), job.isPriority());
        }
    }

    public String getJobsString() {
        String jobs = "";
        int counter = 0;
        String comma = ", ";
        if(getJobs() != null) {
            for (Job job : getJobs()) {
                jobs += job.getName();
                if (getJobs().size() > counter + 1)
                    jobs += comma;
            }
        }
        if(jobs == "")
            jobs = SchedulerApp.getInstance().getContexter().getStringValue(R.string.no_jobs_error);
        return jobs;
    }

    public static void clearUsers() {
        new Delete().from(User.class).execute();
        Shift.clearAllShifts();
    }

    public static User getLoggedUser() {
        List<User> users = new Select().from(User.class).limit(1).execute();
        if (users.isEmpty() == false)
            return users.get(0);
        return null;
    }

    public Date getLastRefresh() {
        if(lastRefresh == null)
            lastRefresh = new Date();
        return lastRefresh;
    }

    public String getLastRefreshString(){
        SimpleDateFormat formatCro = new SimpleDateFormat("hh:mm dd.MM.yyyy.");
        return SchedulerApp.getInstance().getContexter().getStringValue(R.string.last_refreshed) + formatCro.format(getLastRefresh());
    }

    public void setLastRefresh(Date lastRefresh) {
        this.lastRefresh = lastRefresh;
        save();
    }
}
