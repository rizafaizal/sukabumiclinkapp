package id.go.sukabumikota.clink.model.berita;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class AllResponseBerita implements Parcelable {

    @SerializedName("tm_berita_id")
    private String tm_berita_id;

    @SerializedName("tm_kategori_id")
    private String tm_kategori_id;

    @SerializedName("tm_berita_judul")
    private String tm_berita_judul;

    @SerializedName("tm_berita_isi")
    private String tm_berita_isi;

    @SerializedName("tm_berita_tag")
    private String tm_berita_tag;

    @SerializedName("tm_berita_status")
    private String tm_berita_status;

    @SerializedName("tm_kategori_nama")
    private String tm_kategori_nama;

    @SerializedName("tm_kategori_keterangan")
    private String tm_kategori_keterangan;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("tm_berita_cover_nama")
    private String tm_berita_cover_nama;

    @SerializedName("tm_berita_cover_tipe")
    private String tm_berita_cover_tipe;

    @SerializedName("tm_berita_cover_ukuran")
    private String tm_berita_cover_ukuran;

    @SerializedName("tm_berita_cover_folder")
    private String tm_berita_cover_folder;

    @SerializedName("tm_berita_cover_besar")
    private String tm_berita_cover_besar;


    public String getTm_berita_id() {
        return tm_berita_id;
    }

    public void setTm_berita_id(String tm_berita_id) {
        this.tm_berita_id = tm_berita_id;
    }

    public String getTm_kategori_id() {
        return tm_kategori_id;
    }

    public void setTm_kategori_id(String tm_kategori_id) {
        this.tm_kategori_id = tm_kategori_id;
    }

    public String getTm_berita_judul() {
        return tm_berita_judul;
    }

    public void setTm_berita_judul(String tm_berita_judul) {
        this.tm_berita_judul = tm_berita_judul;
    }

    public String getTm_berita_isi() {
        return tm_berita_isi;
    }

    public void setTm_berita_isi(String tm_berita_isi) {
        this.tm_berita_isi = tm_berita_isi;
    }

    public String getTm_berita_tag() {
        return tm_berita_tag;
    }

    public void setTm_berita_tag(String tm_berita_tag) {
        this.tm_berita_tag = tm_berita_tag;
    }

    public String getTm_berita_status() {
        return tm_berita_status;
    }

    public void setTm_berita_status(String tm_berita_status) {
        this.tm_berita_status = tm_berita_status;
    }

    public String getTm_kategori_nama() {
        return tm_kategori_nama;
    }

    public void setTm_kategori_nama(String tm_kategori_nama) {
        this.tm_kategori_nama = tm_kategori_nama;
    }

    public String getTm_kategori_keterangan() {
        return tm_kategori_keterangan;
    }

    public void setTm_kategori_keterangan(String tm_kategori_keterangan) {
        this.tm_kategori_keterangan = tm_kategori_keterangan;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getTm_berita_cover_nama() {
        return tm_berita_cover_nama;
    }

    public void setTm_berita_cover_nama(String tm_berita_cover_nama) {
        this.tm_berita_cover_nama = tm_berita_cover_nama;
    }

    public String getTm_berita_cover_tipe() {
        return tm_berita_cover_tipe;
    }

    public void setTm_berita_cover_tipe(String tm_berita_cover_tipe) {
        this.tm_berita_cover_tipe = tm_berita_cover_tipe;
    }

    public String getTm_berita_cover_ukuran() {
        return tm_berita_cover_ukuran;
    }

    public void setTm_berita_cover_ukuran(String tm_berita_cover_ukuran) {
        this.tm_berita_cover_ukuran = tm_berita_cover_ukuran;
    }

    public String getTm_berita_cover_folder() {
        return tm_berita_cover_folder;
    }

    public void setTm_berita_cover_folder(String tm_berita_cover_folder) {
        this.tm_berita_cover_folder = tm_berita_cover_folder;
    }

    public String getTm_berita_cover_besar() {
        return tm_berita_cover_besar;
    }

    public void setTm_berita_cover_besar(String tm_berita_cover_besar) {
        this.tm_berita_cover_besar = tm_berita_cover_besar;
    }


    @NonNull
    @Override
    public String toString() {
        return
                "ResponseBerita{" +
                        "tm_berita_id = '" + tm_berita_id + '\'' +
                        "tm_kategori_id = '" + tm_kategori_id + '\'' +
                        "tm_berita_judul = '" +tm_berita_judul + '\'' +
                        "tm_berita_isi = '" + tm_berita_isi + '\'' +
                        "tm_berita_tag = '" + tm_berita_tag + '\'' +
                        "tm_berita_status = '" + tm_berita_status + '\'' +
                        "tm_kategori_nama = '" + tm_kategori_nama + '\'' +
                        "tm_kategori_keterangan = '" + tm_kategori_keterangan + '\'' +
                        "created_at = '" + created_at + '\'' +
                        "tm_berita_cover_nama = '" + tm_berita_cover_nama + '\'' +
                        "tm_berita_cover_tipe = '" + tm_berita_cover_tipe + '\'' +
                        "tm_berita_cover_ukuran = '" + tm_berita_cover_ukuran + '\'' +
                        "tm_berita_cover_folder = '" +tm_berita_cover_folder + '\'' +
                        "tm_berita_cover_besar = '" + tm_berita_cover_besar + '\'' +
                        "}";
    }


    protected AllResponseBerita(Parcel in) {
        this.tm_berita_id = in.readString();
        this.tm_kategori_id = in.readString();
        this.tm_berita_judul = in.readString();
        this.tm_berita_isi = in.readString();
        this.tm_berita_tag = in.readString();
        this.tm_berita_status = in.readString();
        this.tm_kategori_nama = in.readString();
        this.tm_kategori_keterangan = in.readString();
        this.created_at = in.readString();
        this.tm_berita_cover_nama = in.readString();
        this.tm_berita_cover_tipe = in.readString();
        this.tm_berita_cover_ukuran = in.readString();
        this.tm_berita_cover_folder = in.readString();
        this.tm_berita_cover_besar = in.readString();
    }

    public static final Creator<AllResponseBerita> CREATOR = new Creator<AllResponseBerita>() {
        @Override
        public AllResponseBerita createFromParcel(Parcel in) {
            return new AllResponseBerita(in);
        }

        @Override
        public AllResponseBerita[] newArray(int size) {
            return new AllResponseBerita[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tm_berita_id);
        dest.writeString(this.tm_kategori_id);
        dest.writeString(this.tm_berita_judul);
        dest.writeString(this.tm_berita_isi);
        dest.writeString(this.tm_berita_tag);
        dest.writeString(this.tm_berita_status);
        dest.writeString(this.tm_kategori_nama);
        dest.writeString(this.tm_kategori_keterangan);
        dest.writeString(this.created_at);
        dest.writeString(this.tm_berita_cover_nama);
        dest.writeString(this.tm_berita_cover_tipe);
        dest.writeString(this.tm_berita_cover_ukuran);
        dest.writeString(this.tm_berita_cover_folder);
        dest.writeString(this.tm_berita_cover_besar);
    }
}
