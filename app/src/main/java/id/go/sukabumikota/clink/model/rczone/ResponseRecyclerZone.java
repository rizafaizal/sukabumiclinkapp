package id.go.sukabumikota.clink.model.rczone;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseRecyclerZone {

    @SerializedName("allrecyclerzone")
    private List<AllResponseRecyclerZone> allrecyclerzone;

    @SerializedName("error")
    private boolean error;

    @SerializedName("success")
    private String success;

    @SerializedName("message")
    private String message;

    public List<AllResponseRecyclerZone> getAllrecyclerzone() {
        return allrecyclerzone;
    }

    public void setAllrecyclerzone(List<AllResponseRecyclerZone> allrecyclerzone) {
        this.allrecyclerzone = allrecyclerzone;
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
                "ResponseRecyclerZone{" +
                        "allrecyclerzone = '" + allrecyclerzone + '\'' +
                        "error = '" +error + '\'' +
                        "success = '" +success + '\'' +
                        "message = '" + message + '\'' +
                        "}";
    }
}
