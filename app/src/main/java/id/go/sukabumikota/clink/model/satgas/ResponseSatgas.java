package id.go.sukabumikota.clink.model.satgas;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseSatgas {

    @SerializedName("allsatgasdata")
    private List<AllResponseSatgas> allsatgasdata;

    @SerializedName("error")
    private boolean error;

    @SerializedName("success")
    private String success;

    @SerializedName("message")
    private String message;


    public List<AllResponseSatgas> getAllsatgasdata() {
        return allsatgasdata;
    }

    public void setAllsatgasdata(List<AllResponseSatgas> allsatgasdata) {
        this.allsatgasdata = allsatgasdata;
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
                "ResponseSatgas{" +
                        "allsatgasdata = '" + allsatgasdata + '\'' +
                        "error = '" +error + '\'' +
                        "success = '" +success + '\'' +
                        "message = '" + message + '\'' +
                        "}";
    }
}
