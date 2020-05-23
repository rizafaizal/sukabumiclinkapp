package id.go.sukabumikota.clink.model.banksampah;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class AllResponseBankSampah implements Parcelable {

    @SerializedName("tm_banksampah_id")
    private String tm_banksampah_id;

    @SerializedName("tm_kelurahan_id")
    private String tm_kelurahan_id;

    @SerializedName("tm_banksampah_nama")
    private String tm_banksampah_nama;

    @SerializedName("tm_banksampah_alamat")
    private String tm_banksampah_alamat;

    @SerializedName("tm_banksampah_latitude")
    private String tm_banksampah_latitude;

    @SerializedName("tm_banksampah_longitude")
    private String tm_banksampah_longitude;

    @SerializedName("tm_banksampah_foto")
    private String tm_banksampah_foto;

    @SerializedName("tm_kelurahan_nama")
    private String tm_kelurahan_nama;


    public String getTm_banksampah_id() {
        return tm_banksampah_id;
    }

    public void setTm_banksampah_id(String tm_banksampah_id) {
        this.tm_banksampah_id = tm_banksampah_id;
    }

    public String getTm_kelurahan_id() {
        return tm_kelurahan_id;
    }

    public void setTm_kelurahan_id(String tm_kelurahan_id) {
        this.tm_kelurahan_id = tm_kelurahan_id;
    }

    public String getTm_banksampah_nama() {
        return tm_banksampah_nama;
    }

    public void setTm_banksampah_nama(String tm_banksampah_nama) {
        this.tm_banksampah_nama = tm_banksampah_nama;
    }

    public String getTm_banksampah_alamat() {
        return tm_banksampah_alamat;
    }

    public void setTm_banksampah_alamat(String tm_banksampah_alamat) {
        this.tm_banksampah_alamat = tm_banksampah_alamat;
    }

    public String getTm_banksampah_latitude() {
        return tm_banksampah_latitude;
    }

    public void setTm_banksampah_latitude(String tm_banksampah_latitude) {
        this.tm_banksampah_latitude = tm_banksampah_latitude;
    }

    public String getTm_banksampah_longitude() {
        return tm_banksampah_longitude;
    }

    public void setTm_banksampah_longitude(String tm_banksampah_longitude) {
        this.tm_banksampah_longitude = tm_banksampah_longitude;
    }

    public String getTm_banksampah_foto() {
        return tm_banksampah_foto;
    }

    public void setTm_banksampah_foto(String tm_banksampah_foto) {
        this.tm_banksampah_foto = tm_banksampah_foto;
    }

    public String getTm_kelurahan_nama() {
        return tm_kelurahan_nama;
    }

    public void setTm_kelurahan_nama(String tm_kelurahan_nama) {
        this.tm_kelurahan_nama = tm_kelurahan_nama;
    }


    public AllResponseBankSampah() {
    }


    protected AllResponseBankSampah(Parcel in) {
        this.tm_banksampah_id = in.readString();
        this.tm_kelurahan_id = in.readString();
        this.tm_banksampah_nama = in.readString();
        this.tm_banksampah_alamat = in.readString();
        this.tm_banksampah_latitude = in.readString();
        this.tm_banksampah_longitude = in.readString();
        this.tm_banksampah_foto = in.readString();
        this.tm_kelurahan_nama = in.readString();
    }

    public static final Creator<AllResponseBankSampah> CREATOR = new Creator<AllResponseBankSampah>() {
        @Override
        public AllResponseBankSampah createFromParcel(Parcel in) {
            return new AllResponseBankSampah(in);
        }

        @Override
        public AllResponseBankSampah[] newArray(int size) {
            return new AllResponseBankSampah[size];
        }
    };

    @NonNull
    @Override
    public String toString() {
        return
                "ResponseBankSampah{" +
                        "tm_banksampah_id = '" + tm_banksampah_id + '\'' +
                        "tm_kelurahan_id = '" + tm_kelurahan_id + '\'' +
                        "tm_banksampah_nama = '" +tm_banksampah_nama + '\'' +
                        "tm_banksampah_alamat = '" + tm_banksampah_alamat + '\'' +
                        "tm_banksampah_latitude = '" + tm_banksampah_latitude + '\'' +
                        "tm_banksampah_longitude = '" + tm_banksampah_longitude + '\'' +
                        "tm_banksampah_foto = '" + tm_banksampah_foto + '\'' +
                        "tm_kelurahan_nama = '" + tm_kelurahan_nama + '\'' +
                        "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tm_banksampah_id);
        dest.writeString(this.tm_kelurahan_id);
        dest.writeString(this.tm_banksampah_nama);
        dest.writeString(this.tm_banksampah_alamat);
        dest.writeString(this.tm_banksampah_latitude);
        dest.writeString(this.tm_banksampah_longitude);
        dest.writeString(this.tm_banksampah_foto);
        dest.writeString(this.tm_kelurahan_nama);
    }
}
