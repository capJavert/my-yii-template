package tk.codetroopers.erscheduler.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Error implements Serializable {

    @SerializedName("username")
    ArrayList<String> usernameErrors;

    @SerializedName("password")
    ArrayList<String> passwordErrors;

    @SerializedName("email")
    ArrayList<String> emailErrors;

    public ArrayList<String> getUsernameErrors() {
        return usernameErrors;
    }

    public void setUsernameErrors(ArrayList<String> usernameErrors) {
        this.usernameErrors = usernameErrors;
    }

    public ArrayList<String> getPasswordErrors() {
        return passwordErrors;
    }

    public void setPasswordErrors(ArrayList<String> passwordErrors) {
        this.passwordErrors = passwordErrors;
    }

    public ArrayList<String> getEmailErrors() {
        return emailErrors;
    }

    public void setEmailErrors(ArrayList<String> emailErrors) {
        this.emailErrors = emailErrors;
    }
}
