package id.go.sukabumikota.clink.model.banksampah;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseBankSampah {


    @SerializedName("allbanksampahdata")
    private List<AllResponseBankSampah> allbanksampahdata;

    @SerializedName("error")
    private boolean error;

    @SerializedName("success")
    private String success;

    @SerializedName("message")
    private String message;

    public List<AllResponseBankSampah> getAllbanksampahdata() {
        return allbanksampahdata;
    }

    public void setAllbanksampahdata(List<AllResponseBankSampah> allbanksampahdata) {
        this.allbanksampahdata = allbanksampahdata;
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
                "ResponseBankSampah{" +
                        "allbanksampahdata = '" + allbanksampahdata + '\'' +
                        "error = '" +error + '\'' +
                        "success = '" +success + '\'' +
                        "message = '" + message + '\'' +
                        "}";
    }
}
