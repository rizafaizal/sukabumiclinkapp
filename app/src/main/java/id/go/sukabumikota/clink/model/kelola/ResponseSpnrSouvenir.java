package id.go.sukabumikota.clink.model.kelola;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseSpnrSouvenir {

    @SerializedName("allspnrsouvenir")
    private List<AllResponseSpnrSouvenir> allspnrsouvenir;

    @SerializedName("error")
    private boolean error;

    @SerializedName("success")
    private String success;

    @SerializedName("message")
    private String message;

    public List<AllResponseSpnrSouvenir> getAllspnrsouvenir() {
        return allspnrsouvenir;
    }

    public void setAllspnrsouvenir(List<AllResponseSpnrSouvenir> allspnrsouvenir) {
        this.allspnrsouvenir = allspnrsouvenir;
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
                "ResponseKecamatan{" +
                        "allspnrsouvenir = '" + allspnrsouvenir + '\'' +
                        "error = '" +error + '\'' +
                        "success = '" +success + '\'' +
                        "message = '" + message + '\'' +
                        "}";
    }
}
