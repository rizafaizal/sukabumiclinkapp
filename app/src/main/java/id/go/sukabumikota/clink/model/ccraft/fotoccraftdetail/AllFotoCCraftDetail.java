package id.go.sukabumikota.clink.model.ccraft.fotoccraftdetail;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class AllFotoCCraftDetail implements Parcelable {

    @SerializedName("tm_kelola_sampah_id")
    private String tm_kelola_sampah_id;

    @SerializedName("tm_foto_kelola_sampah_id")
    private String tm_foto_kelola_sampah_id;

    @SerializedName("tm_foto_kelola_sampah_nama")
    private String tm_foto_kelola_sampah_nama;

    @SerializedName("tm_foto_kelola_sampah_tipe")
    private String tm_foto_kelola_sampah_tipe;

    @SerializedName("tm_foto_kelola_sampah_ukuran")
    private String tm_foto_kelola_sampah_ukuran;

    @SerializedName("tm_foto_kelola_sampah_folder")
    private String tm_foto_kelola_sampah_folder;


    public String getTm_kelola_sampah_id() {
        return tm_kelola_sampah_id;
    }

    public void setTm_kelola_sampah_id(String tm_kelola_sampah_id) {
        this.tm_kelola_sampah_id = tm_kelola_sampah_id;
    }

    public String getTm_foto_kelola_sampah_id() {
        return tm_foto_kelola_sampah_id;
    }

    public void setTm_foto_kelola_sampah_id(String tm_foto_kelola_sampah_id) {
        this.tm_foto_kelola_sampah_id = tm_foto_kelola_sampah_id;
    }

    public String getTm_foto_kelola_sampah_nama() {
        return tm_foto_kelola_sampah_nama;
    }

    public void setTm_foto_kelola_sampah_nama(String tm_foto_kelola_sampah_nama) {
        this.tm_foto_kelola_sampah_nama = tm_foto_kelola_sampah_nama;
    }

    public String getTm_foto_kelola_sampah_tipe() {
        return tm_foto_kelola_sampah_tipe ;
    }

    public void setTm_foto_kelola_sampah_tipe(String tm_foto_kelola_sampah_tipe) {
        this.tm_foto_kelola_sampah_tipe = tm_foto_kelola_sampah_tipe;
    }

    public String getTm_foto_kelola_sampah_ukuran() {
        return tm_foto_kelola_sampah_ukuran;
    }

    public void setTm_foto_kelola_sampah_ukuran(String tm_foto_kelola_sampah_ukuran) {
        this.tm_foto_kelola_sampah_ukuran = tm_foto_kelola_sampah_ukuran;
    }

    public String getTm_foto_kelola_sampah_folder() {
        return tm_foto_kelola_sampah_folder;
    }

    public void setTm_foto_kelola_sampah_folder(String tm_foto_kelola_sampah_folder) {
        this.tm_foto_kelola_sampah_folder = tm_foto_kelola_sampah_folder;
    }

    @NonNull
    @Override
    public String toString() {
        return
                "ResponseFotoCCRaftDetail{" +
                        "tm_kelola_sampah_id = '" + tm_kelola_sampah_id + '\'' +
                        "tm_foto_kelola_sampah_id = '" + tm_foto_kelola_sampah_id + '\'' +
                        "tm_foto_kelola_sampah_nama = '" + tm_foto_kelola_sampah_nama + '\'' +
                        "tm_foto_kelola_sampah_tipe = '" + tm_foto_kelola_sampah_tipe + '\'' +
                        "tm_foto_kelola_sampah_ukuran = '" + tm_foto_kelola_sampah_ukuran + '\'' +
                        "tm_foto_kelola_sampah_folder = '" + tm_foto_kelola_sampah_folder + '\'' +
                        "}";
    }


    public AllFotoCCraftDetail() {
    }

    protected AllFotoCCraftDetail(Parcel in) {
        this.tm_kelola_sampah_id = in.readString();
        this.tm_foto_kelola_sampah_id = in.readString();
        this.tm_foto_kelola_sampah_nama = in.readString();
        this.tm_foto_kelola_sampah_tipe = in.readString();
        this.tm_foto_kelola_sampah_ukuran = in.readString();
        this.tm_foto_kelola_sampah_folder = in.readString();
    }

    public static final Creator<AllFotoCCraftDetail> CREATOR = new Creator<AllFotoCCraftDetail>() {
        @Override
        public AllFotoCCraftDetail createFromParcel(Parcel in) {
            return new AllFotoCCraftDetail(in);
        }

        @Override
        public AllFotoCCraftDetail[] newArray(int size) {
            return new AllFotoCCraftDetail[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tm_kelola_sampah_id);
        dest.writeString(this.tm_foto_kelola_sampah_id);
        dest.writeString(this.tm_foto_kelola_sampah_nama);
        dest.writeString(this.tm_foto_kelola_sampah_tipe);
        dest.writeString(this.tm_foto_kelola_sampah_ukuran);
        dest.writeString(this.tm_foto_kelola_sampah_folder);
    }
}
