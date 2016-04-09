package tk.codetroopers.erscheduler.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BaseReponse implements Serializable {

    @SerializedName("token")
    private String token;

    @SerializedName("success")
    private String success;

    @SerializedName("errors")
    private Error errors;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Error getErrors() {
        return errors;
    }

    public void setErrors(Error errors) {
        this.errors = errors;
    }
}
