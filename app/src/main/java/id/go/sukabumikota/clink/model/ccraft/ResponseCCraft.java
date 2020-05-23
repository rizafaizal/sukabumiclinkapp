package id.go.sukabumikota.clink.model.ccraft;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseCCraft {

    @SerializedName("allcreativedata")
    private List<AllResponseCCraft> allcreativedata;

    @SerializedName("error")
    private boolean error;

    @SerializedName("success")
    private String success;

    @SerializedName("message")
    private String message;

    public List<AllResponseCCraft> getAllcreativedata() {
        return allcreativedata;
    }

    public void setAllcreativedata(List<AllResponseCCraft> allcreativedata) {
        this.allcreativedata = allcreativedata;
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
                "ResponseCCraft{" +
                        "allcreativedata = '" + allcreativedata + '\'' +
                        "error = '" +error + '\'' +
                        "success = '" +success + '\'' +
                        "message = '" + message + '\'' +
                        "}";
    }
}
