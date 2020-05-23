package id.go.sukabumikota.clink.model.kelola;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class ResponseAddKelola {

    @SerializedName("success")
    private String success;

    @SerializedName("message")
    private String message;

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
                "ResponseAddKelola{" +
                        "success = '" +success + '\'' +
                        "message = '" + message + '\'' +
                        "}";
    }
}
