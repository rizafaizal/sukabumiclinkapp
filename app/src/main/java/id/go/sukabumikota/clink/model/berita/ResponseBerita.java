package id.go.sukabumikota.clink.model.berita;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseBerita {

    @SerializedName("allberitadata")
    private List<AllResponseBerita> allberitadata;

    @SerializedName("error")
    private boolean error;

    @SerializedName("success")
    private String success;

    @SerializedName("message")
    private String message;


    public List<AllResponseBerita> getAllberitadata() {
        return allberitadata;
    }

    public void setAllberitadata(List<AllResponseBerita> allberitadata) {
        this.allberitadata = allberitadata;
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
                "ResponseBerita{" +
                        "allberitadata = '" + allberitadata + '\'' +
                        "error = '" +error + '\'' +
                        "success = '" +success + '\'' +
                        "message = '" + message + '\'' +
                        "}";
    }
}
