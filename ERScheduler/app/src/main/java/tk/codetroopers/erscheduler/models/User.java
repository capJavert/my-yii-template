package tk.codetroopers.erscheduler.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name = "Users")
public class User extends Model implements Serializable {

    public User() {
        super();
    }

    public User(String name, String surname, String username, String password, String token, String oib,
                Date birthDate, String address, String place, String phoneNumber, String mobileNumber,
                String remark, String central, Integer numberOfHours) {
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
    private Date birthDate;

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

    @SerializedName("poslovi")
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
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
                .where("DbUser = ?", this.getId())
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
}
