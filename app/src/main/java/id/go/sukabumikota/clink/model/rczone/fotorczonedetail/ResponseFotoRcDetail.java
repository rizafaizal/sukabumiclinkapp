package id.go.sukabumikota.clink.model.rczone.fotorczonedetail;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseFotoRcDetail {

    @SerializedName("allfotorecyclerzone")
    private List<AllFotoRcDetail> allFotoRcDetailList;

    @SerializedName("error")
    private boolean error;

    @SerializedName("success")
    private String success;

    @SerializedName("message")
    private String message;

    public List<AllFotoRcDetail> getAllFotoRcDetailList() {
        return allFotoRcDetailList;
    }

    public void setAllFotoRcDetailList(List<AllFotoRcDetail> allFotoRcDetailList) {
        this.allFotoRcDetailList = allFotoRcDetailList;
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
                "ResponseFotoRcDetail{" +
                        "allrecyclerzone = '" + allFotoRcDetailList + '\'' +
                        "error = '" +error + '\'' +
                        "success = '" +success + '\'' +
                        "message = '" + message + '\'' +
                        "}";
    }
}
