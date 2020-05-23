package id.go.sukabumikota.clink.model.education;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class AllResponseEducation implements Parcelable {

    @SerializedName("tm_edukasi_id")
    private String tm_edukasi_id;

    @SerializedName("tm_edukasi_judul")
    private String tm_edukasi_judul;

    @SerializedName("tm_edukasi_konten")
    private String tm_edukasi_konten;

    @SerializedName("tm_edukasi_cover_nama")
    private String tm_edukasi_cover_nama;

    @SerializedName("tm_edukasi_cover_tipe")
    private String tm_edukasi_cover_tipe;

    @SerializedName("tm_edukasi_cover_ukuran")
    private String tm_edukasi_cover_ukuran;

    @SerializedName("tm_edukasi_cover_folder")
    private String tm_edukasi_cover_folder;

    @SerializedName("tm_edukasi_cover_besar")
    private String tm_edukasi_cover_besar;



    public String getTm_edukasi_id() {
        return tm_edukasi_id;
    }

    public void setTm_edukasi_id(String tm_edukasi_id) {
        this.tm_edukasi_id = tm_edukasi_id;
    }

    public String getTm_edukasi_judul() {
        return tm_edukasi_judul;
    }

    public void setTm_edukasi_judul(String tm_edukasi_judul) {
        this.tm_edukasi_judul = tm_edukasi_judul;
    }

    public String getTm_edukasi_konten() {
        return tm_edukasi_konten;
    }

    public void setTm_edukasi_konten(String tm_edukasi_konten) {
        this.tm_edukasi_konten = tm_edukasi_konten;
    }

    public String getTm_edukasi_cover_nama() {
        return tm_edukasi_cover_nama;
    }

    public void setTm_edukasi_cover_nama(String tm_edukasi_cover_nama) {
        this.tm_edukasi_cover_nama = tm_edukasi_cover_nama;
    }

    public String getTm_edukasi_cover_tipe() {
        return tm_edukasi_cover_tipe;
    }

    public void setTm_edukasi_cover_tipe(String tm_edukasi_cover_tipe) {
        this.tm_edukasi_cover_tipe = tm_edukasi_cover_tipe;
    }

    public String getTm_edukasi_cover_ukuran() {
        return tm_edukasi_cover_ukuran;
    }

    public void setTm_edukasi_cover_ukuran(String tm_edukasi_cover_ukuran) {
        this.tm_edukasi_cover_ukuran = tm_edukasi_cover_ukuran;
    }

    public String getTm_edukasi_cover_folder() {
        return tm_edukasi_cover_folder;
    }

    public void setTm_edukasi_cover_folder(String tm_edukasi_cover_folder) {
        this.tm_edukasi_cover_folder = tm_edukasi_cover_folder;
    }

    public String getTm_edukasi_cover_besar() {
        return tm_edukasi_cover_besar;
    }

    public void setTm_edukasi_cover_besar(String tm_edukasi_cover_besar) {
        this.tm_edukasi_cover_besar = tm_edukasi_cover_besar;
    }


    @NonNull
    @Override
    public String toString() {
        return
                "ResponseEducation{" +
                        "tm_edukasi_id = '" + tm_edukasi_id + '\'' +
                        "tm_edukasi_judul = '" + tm_edukasi_judul + '\'' +
                        "tm_edukasi_konten = '" +tm_edukasi_konten + '\'' +
                        "tm_edukasi_cover_nama = '" + tm_edukasi_cover_nama + '\'' +
                        "tm_edukasi_cover_tipe = '" + tm_edukasi_cover_tipe + '\'' +
                        "tm_edukasi_cover_ukuran = '" + tm_edukasi_cover_ukuran + '\'' +
                        "tm_edukasi_cover_folder = '" + tm_edukasi_cover_folder + '\'' +
                        "tm_edukasi_cover_besar = '" + tm_edukasi_cover_besar + '\'' +
                        "}";
    }


    public AllResponseEducation() {
    }

    protected AllResponseEducation(Parcel in) {
        this.tm_edukasi_id = in.readString();
        this.tm_edukasi_judul = in.readString();
        this.tm_edukasi_konten = in.readString();
        this.tm_edukasi_cover_nama = in.readString();
        this.tm_edukasi_cover_tipe = in.readString();
        this.tm_edukasi_cover_ukuran = in.readString();
        this.tm_edukasi_cover_folder = in.readString();
        this.tm_edukasi_cover_besar = in.readString();
    }

    public static final Creator<AllResponseEducation> CREATOR = new Creator<AllResponseEducation>() {
        @Override
        public AllResponseEducation createFromParcel(Parcel in) {
            return new AllResponseEducation(in);
        }

        @Override
        public AllResponseEducation[] newArray(int size) {
            return new AllResponseEducation[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tm_edukasi_id);
        dest.writeString(this.tm_edukasi_judul);
        dest.writeString(this.tm_edukasi_konten);
        dest.writeString(this.tm_edukasi_cover_nama);
        dest.writeString(this.tm_edukasi_cover_tipe);
        dest.writeString(this.tm_edukasi_cover_ukuran);
        dest.writeString(this.tm_edukasi_cover_folder);
        dest.writeString(this.tm_edukasi_cover_besar);
    }
}
