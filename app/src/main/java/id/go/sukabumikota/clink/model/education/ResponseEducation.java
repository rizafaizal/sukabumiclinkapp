package id.go.sukabumikota.clink.model.education;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseEducation {

    @SerializedName("alleducationdata")
    private List<AllResponseEducation> alleducationdata;

    @SerializedName("error")
    private boolean error;

    @SerializedName("success")
    private String success;

    @SerializedName("message")
    private String message;


    public List<AllResponseEducation> getAlleducationdata() {
        return alleducationdata;
    }

    public void setAlleducationdata(List<AllResponseEducation> alleducationdata) {
        this.alleducationdata = alleducationdata;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @NonNull
    @Override
    public String toString() {
        return
                "ResponseEducation{" +
                        "alleducationdata = '" + alleducationdata + '\'' +
                        "error = '" +error + '\'' +
                        "success = '" +success + '\'' +
                        "message = '" + message + '\'' +
                        "}";
    }
}
