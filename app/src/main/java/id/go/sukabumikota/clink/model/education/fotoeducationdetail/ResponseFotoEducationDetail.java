package id.go.sukabumikota.clink.model.education.fotoeducationdetail;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseFotoEducationDetail {

    @SerializedName("allfotoeducation")
    private List<AllFotoEducationDetail> allfotoeducation;

    @SerializedName("error")
    private boolean error;

    @SerializedName("success")
    private String success;

    @SerializedName("message")
    private String message;


    public List<AllFotoEducationDetail> getAllfotoeducation() {
        return allfotoeducation;
    }

    public void setAllfotoeducation(List<AllFotoEducationDetail> allfotoeducation) {
        this.allfotoeducation = allfotoeducation;
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
                "ResponseFotoEducationDetail{" +
                        "allfotoeducation = '" + allfotoeducation + '\'' +
                        "error = '" +error + '\'' +
                        "success = '" +success + '\'' +
                        "message = '" + message + '\'' +
                        "}";
    }
}
