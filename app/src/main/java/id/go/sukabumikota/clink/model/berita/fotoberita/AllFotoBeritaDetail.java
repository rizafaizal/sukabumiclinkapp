package id.go.sukabumikota.clink.model.berita.fotoberita;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class AllFotoBeritaDetail implements Parcelable {

    @SerializedName("tm_berita_id")
    private String tm_berita_id;

    @SerializedName("tm_foto_berita_id")
    private String tm_foto_berita_id;

    @SerializedName("tm_foto_berita_nama")
    private String tm_foto_berita_nama;

    @SerializedName("tm_foto_berita_tipe")
    private String tm_foto_berita_tipe;

    @SerializedName("tm_foto_berita_ukuran")
    private String tm_foto_berita_ukuran;

    @SerializedName("tm_foto_berita_folder")
    private String tm_foto_berita_folder;


    public String getTm_berita_id() {
        return tm_berita_id;
    }

    public void setTm_berita_id(String tm_berita_id) {
        this.tm_berita_id = tm_berita_id;
    }

    public String getTm_foto_berita_id() {
        return tm_foto_berita_id;
    }

    public void setTm_foto_berita_id(String tm_foto_berita_id) {
        this.tm_foto_berita_id = tm_foto_berita_id;
    }

    public String getTm_foto_berita_nama() {
        return tm_foto_berita_nama;
    }

    public void setTm_foto_berita_nama(String tm_foto_berita_nama) {
        this.tm_foto_berita_nama = tm_foto_berita_nama;
    }

    public String getTm_foto_berita_tipe() {
        return tm_foto_berita_tipe;
    }

    public void setTm_foto_berita_tipe(String tm_foto_berita_tipe) {
        this.tm_foto_berita_tipe = tm_foto_berita_tipe;
    }

    public String getTm_foto_berita_ukuran() {
        return tm_foto_berita_ukuran;
    }

    public void setTm_foto_berita_ukuran(String tm_foto_berita_ukuran) {
        this.tm_foto_berita_ukuran = tm_foto_berita_ukuran;
    }

    public String getTm_foto_berita_folder() {
        return tm_foto_berita_folder;
    }

    public void setTm_foto_berita_folder(String tm_foto_berita_folder) {
        this.tm_foto_berita_folder = tm_foto_berita_folder;
    }


    @NonNull
    @Override
    public String toString() {
        return
                "ResponseFotoBeritaDetail{" +
                        "tm_berita_id = '" + tm_berita_id + '\'' +
                        "tm_foto_berita_id = '" + tm_foto_berita_id + '\'' +
                        "tm_foto_berita_nama = '" + tm_foto_berita_nama + '\'' +
                        "tm_foto_berita_tipe = '" + tm_foto_berita_tipe + '\'' +
                        "tm_foto_berita_ukuran = '" + tm_foto_berita_ukuran + '\'' +
                        "tm_foto_berita_folder = '" + tm_foto_berita_folder + '\'' +
                        "}";
    }


    protected AllFotoBeritaDetail(Parcel in) {
        this.tm_berita_id = in.readString();
        this.tm_foto_berita_id = in.readString();
        this.tm_foto_berita_nama = in.readString();
        this.tm_foto_berita_tipe = in.readString();
        this.tm_foto_berita_ukuran = in.readString();
        this.tm_foto_berita_folder = in.readString();
    }

    public static final Creator<AllFotoBeritaDetail> CREATOR = new Creator<AllFotoBeritaDetail>() {
        @Override
        public AllFotoBeritaDetail createFromParcel(Parcel in) {
            return new AllFotoBeritaDetail(in);
        }

        @Override
        public AllFotoBeritaDetail[] newArray(int size) {
            return new AllFotoBeritaDetail[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tm_berita_id);
        dest.writeString(this.tm_foto_berita_id);
        dest.writeString(this.tm_foto_berita_nama);
        dest.writeString(this.tm_foto_berita_tipe);
        dest.writeString(this.tm_foto_berita_ukuran);
        dest.writeString(this.tm_foto_berita_folder);
    }
}
