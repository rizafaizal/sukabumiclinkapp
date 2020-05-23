package id.go.sukabumikota.clink.model.education.fotoeducationdetail;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class AllFotoEducationDetail implements Parcelable {

    @SerializedName("tm_edukasi_id")
    private String tm_edukasi_id;

    @SerializedName("tm_foto_edukasi_id")
    private String tm_foto_edukasi_id;

    @SerializedName("tm_foto_edukasi_nama")
    private String tm_foto_edukasi_nama;

    @SerializedName("tm_foto_edukasi_tipe")
    private String tm_foto_edukasi_tipe;

    @SerializedName("tm_foto_edukasi_ukuran")
    private String tm_foto_edukasi_ukuran;

    @SerializedName("tm_foto_edukasi_folder")
    private String tm_foto_edukasi_folder;



    public String getTm_edukasi_id() {
        return tm_edukasi_id;
    }

    public void setTm_edukasi_id(String tm_edukasi_id) {
        this.tm_edukasi_id = tm_edukasi_id;
    }

    public String getTm_foto_edukasi_id() {
        return tm_foto_edukasi_id;
    }

    public void setTm_foto_edukasi_id(String tm_foto_edukasi_id) {
        this.tm_foto_edukasi_id = tm_foto_edukasi_id;
    }

    public String getTm_foto_edukasi_nama() {
        return tm_foto_edukasi_nama;
    }

    public void setTm_foto_edukasi_nama(String tm_foto_edukasi_nama) {
        this.tm_foto_edukasi_nama = tm_foto_edukasi_nama;
    }

    public String getTm_foto_edukasi_tipe() {
        return tm_foto_edukasi_tipe;
    }

    public void setTm_foto_edukasi_tipe(String tm_foto_edukasi_tipe) {
        this.tm_foto_edukasi_tipe = tm_foto_edukasi_tipe;
    }

    public String getTm_foto_edukasi_ukuran() {
        return tm_foto_edukasi_ukuran;
    }

    public void setTm_foto_edukasi_ukuran(String tm_foto_edukasi_ukuran) {
        this.tm_foto_edukasi_ukuran = tm_foto_edukasi_ukuran;
    }

    public String getTm_foto_edukasi_folder() {
        return tm_foto_edukasi_folder;
    }

    public void setTm_foto_edukasi_folder(String tm_foto_edukasi_folder) {
        this.tm_foto_edukasi_folder = tm_foto_edukasi_folder;
    }


    @NonNull
    @Override
    public String toString() {
        return
                "ResponseFotoEducationDetail{" +
                        "tm_edukasi_id = '" + tm_edukasi_id + '\'' +
                        "tm_foto_edukasi_id = '" + tm_foto_edukasi_id + '\'' +
                        "tm_foto_edukasi_nama = '" + tm_foto_edukasi_nama + '\'' +
                        "tm_foto_edukasi_tipe = '" + tm_foto_edukasi_tipe + '\'' +
                        "tm_foto_edukasi_ukuran = '" + tm_foto_edukasi_ukuran + '\'' +
                        "tm_foto_edukasi_folder = '" + tm_foto_edukasi_folder + '\'' +
                        "}";
    }

    public AllFotoEducationDetail() {
    }

    protected AllFotoEducationDetail(Parcel in) {
        this.tm_edukasi_id = in.readString();
        this.tm_foto_edukasi_id = in.readString();
        this.tm_foto_edukasi_nama = in.readString();
        this.tm_foto_edukasi_tipe = in.readString();
        this.tm_foto_edukasi_ukuran = in.readString();
        this.tm_foto_edukasi_folder = in.readString();
    }

    public static final Creator<AllFotoEducationDetail> CREATOR = new Creator<AllFotoEducationDetail>() {
        @Override
        public AllFotoEducationDetail createFromParcel(Parcel in) {
            return new AllFotoEducationDetail(in);
        }

        @Override
        public AllFotoEducationDetail[] newArray(int size) {
            return new AllFotoEducationDetail[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tm_edukasi_id);
        dest.writeString(this.tm_foto_edukasi_id);
        dest.writeString(this.tm_foto_edukasi_nama);
        dest.writeString(this.tm_foto_edukasi_tipe);
        dest.writeString(this.tm_foto_edukasi_ukuran);
        dest.writeString(this.tm_foto_edukasi_folder);
    }
}
