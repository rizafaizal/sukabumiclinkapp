package id.go.sukabumikota.clink.model.satgas.fotosatgas;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseFotoSatgasDetail {

    @SerializedName("allfotosatgas")
    private List<AllFotoSatgasDetail> allfotosatgas;

    @SerializedName("error")
    private boolean error;

    @SerializedName("success")
    private String success;

    @SerializedName("message")
    private String message;


    public List<AllFotoSatgasDetail> getAllfotosatgas() {
        return allfotosatgas;
    }

    public void setAllfotosatgas(List<AllFotoSatgasDetail> allfotosatgas) {
        this.allfotosatgas = allfotosatgas;
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
                "ResponseFotoSatgasDetail{" +
                        "allfotosatgas = '" + allfotosatgas + '\'' +
                        "error = '" +error + '\'' +
                        "success = '" +success + '\'' +
                        "message = '" + message + '\'' +
                        "}";
    }
}
