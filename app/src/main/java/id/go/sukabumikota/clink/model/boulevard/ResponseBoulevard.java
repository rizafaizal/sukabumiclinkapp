package id.go.sukabumikota.clink.model.boulevard;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseBoulevard {

    @SerializedName("allboulevarddata")
    private List<AllResponseBoulevard> allboulevarddata;

    @SerializedName("error")
    private boolean error;

    @SerializedName("success")
    private String success;

    @SerializedName("message")
    private String message;

    public List<AllResponseBoulevard> getAllboulevarddata() {
        return allboulevarddata;
    }

    public void setAllboulevarddata(List<AllResponseBoulevard> allboulevarddata) {
        this.allboulevarddata = allboulevarddata;
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
                "ResponseBoulevard{" +
                        "allboulevarddata = '" + allboulevarddata + '\'' +
                        "error = '" +error + '\'' +
                        "success = '" +success + '\'' +
                        "message = '" + message + '\'' +
                        "}";
    }
}
