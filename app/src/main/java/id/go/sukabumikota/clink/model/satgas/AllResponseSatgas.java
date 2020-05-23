package id.go.sukabumikota.clink.model.satgas;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class AllResponseSatgas implements Parcelable {

    @SerializedName("tm_satgas_id")
    private String tm_satgas_id;

    @SerializedName("tm_satgas_judul")
    private String tm_satgas_judul;

    @SerializedName("tm_satgas_konten")
    private String tm_satgas_konten;

    @SerializedName("tm_satgas_cover_nama")
    private String tm_satgas_cover_nama;

    @SerializedName("tm_satgas_cover_tipe")
    private String tm_satgas_cover_tipe;

    @SerializedName("tm_satgas_cover_ukuran")
    private String tm_satgas_cover_ukuran;

    @SerializedName("tm_satgas_cover_folder")
    private String tm_satgas_cover_folder;

    @SerializedName("tm_satgas_cover_besar")
    private String tm_satgas_cover_besar;

    @SerializedName("tm_satgas_telp")
    private String tm_satgas_telp;


    public String getTm_satgas_id() {
        return tm_satgas_id;
    }

    public void setTm_satgas_id(String tm_satgas_id) {
        this.tm_satgas_id = tm_satgas_id;
    }

    public String getTm_satgas_judul() {
        return tm_satgas_judul;
    }

    public void setTm_satgas_judul(String tm_satgas_judul) {
        this.tm_satgas_judul = tm_satgas_judul;
    }

    public String getTm_satgas_konten() {
        return tm_satgas_konten;
    }

    public void setTm_satgas_konten(String tm_satgas_konten) {
        this.tm_satgas_konten = tm_satgas_konten;
    }

    public String getTm_satgas_cover_nama() {
        return tm_satgas_cover_nama;
    }

    public void setTm_satgas_cover_nama(String tm_satgas_cover_nama) {
        this.tm_satgas_cover_nama = tm_satgas_cover_nama;
    }

    public String getTm_satgas_cover_tipe() {
        return tm_satgas_cover_tipe;
    }

    public void setTm_satgas_cover_tipe(String tm_satgas_cover_tipe) {
        this.tm_satgas_cover_tipe = tm_satgas_cover_tipe;
    }

    public String getTm_satgas_cover_ukuran() {
        return tm_satgas_cover_ukuran;
    }

    public void setTm_satgas_cover_ukuran(String tm_satgas_cover_ukuran) {
        this.tm_satgas_cover_ukuran = tm_satgas_cover_ukuran;
    }

    public String getTm_satgas_cover_folder() {
        return tm_satgas_cover_folder;
    }

    public void setTm_satgas_cover_folder(String tm_satgas_cover_folder) {
        this.tm_satgas_cover_folder = tm_satgas_cover_folder;
    }

    public String getTm_satgas_cover_besar() {
        return tm_satgas_cover_besar;
    }

    public void setTm_satgas_cover_besar(String tm_satgas_cover_besar) {
        this.tm_satgas_cover_besar = tm_satgas_cover_besar;
    }

    public String getTm_satgas_telp() {
        return tm_satgas_telp;
    }

    public void setTm_satgas_telp(String tm_satgas_telp) {
        this.tm_satgas_telp = tm_satgas_telp;
    }

    @NonNull
    @Override
    public String toString() {
        return
                "ResponseSatgas{" +
                        "tm_satgas_id = '" + tm_satgas_id + '\'' +
                        "tm_satgas_judul = '" + tm_satgas_judul + '\'' +
                        "tm_satgas_konten = '" +tm_satgas_konten + '\'' +
                        "tm_satgas_cover_nama = '" + tm_satgas_cover_nama + '\'' +
                        "tm_satgas_cover_tipe = '" + tm_satgas_cover_tipe + '\'' +
                        "tm_satgas_cover_ukuran = '" + tm_satgas_cover_ukuran + '\'' +
                        "tm_satgas_cover_folder = '" + tm_satgas_cover_folder + '\'' +
                        "tm_satgas_cover_besar = '" + tm_satgas_cover_besar + '\'' +
                        "tm_satgas_telp = '" + tm_satgas_telp + '\'' +
                        "}";
    }


    protected AllResponseSatgas(Parcel in) {
        this.tm_satgas_id = in.readString();
        this.tm_satgas_judul = in.readString();
        this.tm_satgas_konten = in.readString();
        this.tm_satgas_cover_nama = in.readString();
        this.tm_satgas_cover_tipe = in.readString();
        this.tm_satgas_cover_ukuran = in.readString();
        this.tm_satgas_cover_folder = in.readString();
        this.tm_satgas_cover_besar = in.readString();
        this.tm_satgas_telp = in.readString();
    }

    public static final Creator<AllResponseSatgas> CREATOR = new Creator<AllResponseSatgas>() {
        @Override
        public AllResponseSatgas createFromParcel(Parcel in) {
            return new AllResponseSatgas(in);
        }

        @Override
        public AllResponseSatgas[] newArray(int size) {
            return new AllResponseSatgas[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tm_satgas_id);
        dest.writeString(this.tm_satgas_judul);
        dest.writeString(this.tm_satgas_konten);
        dest.writeString(this.tm_satgas_cover_nama);
        dest.writeString(this.tm_satgas_cover_tipe);
        dest.writeString(this.tm_satgas_cover_ukuran);
        dest.writeString(this.tm_satgas_cover_folder);
        dest.writeString(this.tm_satgas_cover_besar);
        dest.writeString(this.tm_satgas_telp);
    }
}
