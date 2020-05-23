package id.go.sukabumikota.clink.model.berita.fotoberita;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseFotoBeritaDetail {

    @SerializedName("allfotoberita")
    private List<AllFotoBeritaDetail> allfotoberita;

    @SerializedName("error")
    private boolean error;

    @SerializedName("success")
    private String success;

    @SerializedName("message")
    private String message;


    public List<AllFotoBeritaDetail> getAllfotoberita() {
        return allfotoberita;
    }

    public void setAllfotoberita(List<AllFotoBeritaDetail> allfotoberita) {
        this.allfotoberita = allfotoberita;
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
                "ResponseFotoBeritaDetail{" +
                        "allfotoberita = '" + allfotoberita + '\'' +
                        "error = '" +error + '\'' +
                        "success = '" +success + '\'' +
                        "message = '" + message + '\'' +
                        "}";
    }
}
