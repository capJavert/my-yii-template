package tk.codetroopers.erscheduler.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GcmMessage implements Serializable {

    @SerializedName("title")
    private String title;

    @SerializedName("message")
    private String message;

    @SerializedName("action")
    private String action;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
