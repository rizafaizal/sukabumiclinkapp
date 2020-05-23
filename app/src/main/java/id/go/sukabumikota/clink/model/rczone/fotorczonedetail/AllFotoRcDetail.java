package id.go.sukabumikota.clink.model.rczone.fotorczonedetail;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class AllFotoRcDetail implements Parcelable {

    @SerializedName("tm_kategori_subolah_id")
    private String tm_kategori_subolah_id;

    @SerializedName("tm_kategori_subolah_foto_id")
    private String tm_kategori_subolah_foto_id;

    @SerializedName("tm_kategori_subolah_foto_nama")
    private String tm_kategori_subolah_foto_nama;

    @SerializedName("tm_kategori_subolah_foto_tipe")
    private String tm_kategori_subolah_foto_tipe;

    @SerializedName("tm_kategori_subolah_foto_ukuran")
    private String tm_kategori_subolah_foto_ukuran;

    @SerializedName("tm_kategori_subolah_foto_folder")
    private String tm_kategori_subolah_foto_folder;

    public String getTm_kategori_subolah_id() {
        return tm_kategori_subolah_id;
    }

    public void setTm_kategori_subolah_id(String tm_kategori_subolah_id) {
        this.tm_kategori_subolah_id = tm_kategori_subolah_id;
    }

    public String getTm_kategori_subolah_foto_id() {
        return tm_kategori_subolah_foto_id;
    }

    public void setTm_kategori_subolah_foto_id(String tm_kategori_subolah_foto_id) {
        this.tm_kategori_subolah_foto_id = tm_kategori_subolah_foto_id;
    }

    public String getTm_kategori_subolah_foto_nama() {
        return tm_kategori_subolah_foto_nama;
    }

    public void setTm_kategori_subolah_foto_nama(String tm_kategori_subolah_foto_nama) {
        this.tm_kategori_subolah_foto_nama = tm_kategori_subolah_foto_nama;
    }

    public String getTm_kategori_subolah_foto_tipe() {
        return tm_kategori_subolah_foto_tipe;
    }

    public void setTm_kategori_subolah_foto_tipe(String tm_kategori_subolah_foto_tipe) {
        this.tm_kategori_subolah_foto_tipe = tm_kategori_subolah_foto_tipe;
    }

    public String getTm_kategori_subolah_foto_ukuran() {
        return tm_kategori_subolah_foto_ukuran;
    }

    public void setTm_kategori_subolah_foto_ukuran(String tm_kategori_subolah_foto_ukuran) {
        this.tm_kategori_subolah_foto_ukuran = tm_kategori_subolah_foto_ukuran;
    }

    public String getTm_kategori_subolah_foto_folder() {
        return tm_kategori_subolah_foto_folder;
    }

    public void setTm_kategori_subolah_foto_folder(String tm_kategori_subolah_foto_folder) {
        this.tm_kategori_subolah_foto_folder = tm_kategori_subolah_foto_folder;
    }

    @NonNull
    @Override
    public String toString() {
        return
                "ResponseFotoRcDetail{" +
                        "tm_kategori_subolah_id = '" + tm_kategori_subolah_id + '\'' +
                        "tm_kategori_subolah_foto_id = '" + tm_kategori_subolah_foto_id + '\'' +
                        "tm_kategori_subolah_foto_nama = '" + tm_kategori_subolah_foto_nama + '\'' +
                        "tm_kategori_subolah_foto_tipe = '" + tm_kategori_subolah_foto_tipe + '\'' +
                        "tm_kategori_subolah_foto_ukuran = '" + tm_kategori_subolah_foto_ukuran + '\'' +
                        "tm_kategori_subolah_foto_folder = '" + tm_kategori_subolah_foto_folder + '\'' +
                        "}";
    }

    public AllFotoRcDetail() {
    }

    protected AllFotoRcDetail(Parcel in) {
        this.tm_kategori_subolah_id = in.readString();
        this.tm_kategori_subolah_foto_id = in.readString();
        this.tm_kategori_subolah_foto_nama = in.readString();
        this.tm_kategori_subolah_foto_tipe = in.readString();
        this.tm_kategori_subolah_foto_ukuran = in.readString();
        this.tm_kategori_subolah_foto_folder = in.readString();
    }

    public static final Creator<AllFotoRcDetail> CREATOR = new Creator<AllFotoRcDetail>() {
        @Override
        public AllFotoRcDetail createFromParcel(Parcel in) {
            return new AllFotoRcDetail(in);
        }

        @Override
        public AllFotoRcDetail[] newArray(int size) {
            return new AllFotoRcDetail[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tm_kategori_subolah_id);
        dest.writeString(this.tm_kategori_subolah_foto_id);
        dest.writeString(this.tm_kategori_subolah_foto_nama);
        dest.writeString(this.tm_kategori_subolah_foto_tipe);
        dest.writeString(this.tm_kategori_subolah_foto_ukuran);
        dest.writeString(this.tm_kategori_subolah_foto_folder);
    }
}
