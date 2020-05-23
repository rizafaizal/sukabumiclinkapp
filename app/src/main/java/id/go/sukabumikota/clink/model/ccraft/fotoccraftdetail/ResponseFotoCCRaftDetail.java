package id.go.sukabumikota.clink.model.ccraft.fotoccraftdetail;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseFotoCCRaftDetail {

    @SerializedName("allfotocreativecraft")
    private List<AllFotoCCraftDetail> allfotocreativecraft;

    @SerializedName("error")
    private boolean error;

    @SerializedName("success")
    private String success;

    @SerializedName("message")
    private String message;


    public List<AllFotoCCraftDetail> getAllfotocreativecraft() {
        return allfotocreativecraft;
    }

    public void setAllfotocreativecraft(List<AllFotoCCraftDetail> allfotocreativecraft) {
        this.allfotocreativecraft = allfotocreativecraft;
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
                "ResponseFotoCCRaftDetail{" +
                        "allfotocreativecraft = '" + allfotocreativecraft + '\'' +
                        "error = '" +error + '\'' +
                        "success = '" +success + '\'' +
                        "message = '" + message + '\'' +
                        "}";
    }
}
