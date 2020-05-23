package id.go.sukabumikota.clink.model.rczone;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class AllResponseRecyclerZone implements Parcelable {

    @SerializedName("tm_kategori_subolah_id")
    private String tm_kategori_subolah_id;

    @SerializedName("tm_kategori_subolah_nama")
    private String tm_kategori_subolah_nama;

    @SerializedName("tm_kategori_subolah_keterangan")
    private String tm_kategori_subolah_keterangan;

    @SerializedName("tm_kategori_subolah_isi")
    private String tm_kategori_subolah_isi;

    @SerializedName("tm_kategori_subolah_jenis")
    private String tm_kategori_subolah_jenis;

    @SerializedName("tm_kategori_subolah_cover_nama")
    private String tm_kategori_subolah_cover_nama;

    @SerializedName("tm_kategori_subolah_cover_tipe")
    private String tm_kategori_subolah_cover_tipe;

    @SerializedName("tm_kategori_subolah_cover_ukuran")
    private String tm_kategori_subolah_cover_ukuran;

    @SerializedName("tm_kategori_subolah_cover_folder")
    private String tm_kategori_subolah_cover_folder;


    public String getTm_kategori_subolah_id() {
        return tm_kategori_subolah_id;
    }

    public void setTm_kategori_subolah_id(String tm_kategori_subolah_id) {
        this.tm_kategori_subolah_id = tm_kategori_subolah_id;
    }

    public String getTm_kategori_subolah_nama() {
        return tm_kategori_subolah_nama;
    }

    public void setTm_kategori_subolah_nama(String tm_kategori_subolah_nama) {
        this.tm_kategori_subolah_nama = tm_kategori_subolah_nama;
    }

    public String getTm_kategori_subolah_keterangan() {
        return tm_kategori_subolah_keterangan;
    }

    public void setTm_kategori_subolah_keterangan(String tm_kategori_subolah_keterangan) {
        this.tm_kategori_subolah_keterangan = tm_kategori_subolah_keterangan;
    }

    public String getTm_kategori_subolah_isi() {
        return tm_kategori_subolah_isi;
    }

    public void setTm_kategori_subolah_isi(String tm_kategori_subolah_isi) {
        this.tm_kategori_subolah_isi = tm_kategori_subolah_isi;
    }

    public String getTm_kategori_subolah_jenis() {
        return tm_kategori_subolah_jenis;
    }

    public void setTm_kategori_subolah_jenis(String tm_kategori_subolah_jenis) {
        this.tm_kategori_subolah_jenis = tm_kategori_subolah_jenis;
    }

    public String getTm_kategori_subolah_cover_nama() {
        return tm_kategori_subolah_cover_nama;
    }

    public void setTm_kategori_subolah_cover_nama(String tm_kategori_subolah_cover_nama) {
        this.tm_kategori_subolah_cover_nama = tm_kategori_subolah_cover_nama;
    }

    public String getTm_kategori_subolah_cover_tipe() {
        return tm_kategori_subolah_cover_tipe;
    }

    public void setTm_kategori_subolah_cover_tipe(String tm_kategori_subolah_cover_tipe) {
        this.tm_kategori_subolah_cover_tipe = tm_kategori_subolah_cover_tipe;
    }

    public String getTm_kategori_subolah_cover_ukuran() {
        return tm_kategori_subolah_cover_ukuran;
    }

    public void setTm_kategori_subolah_cover_ukuran(String tm_kategori_subolah_cover_ukuran) {
        this.tm_kategori_subolah_cover_ukuran = tm_kategori_subolah_cover_ukuran;
    }

    public String getTm_kategori_subolah_cover_folder() {
        return tm_kategori_subolah_cover_folder;
    }

    public void setTm_kategori_subolah_cover_folder(String tm_kategori_subolah_cover_folder) {
        this.tm_kategori_subolah_cover_folder = tm_kategori_subolah_cover_folder;
    }

    @NonNull
    @Override
    public String toString() {
        return
                "ResponseRecyclerZone{" +
                        "tm_kategori_subolah_id = '" + tm_kategori_subolah_id + '\'' +
                        "tm_kategori_subolah_nama = '" + tm_kategori_subolah_nama + '\'' +
                        "tm_kategori_subolah_keterangan = '" +tm_kategori_subolah_keterangan + '\'' +
                        "tm_kategori_subolah_isi = '" + tm_kategori_subolah_isi + '\'' +
                        "tm_kategori_subolah_jenis = '" + tm_kategori_subolah_jenis + '\'' +
                        "tm_kategori_subolah_cover_nama = '" + tm_kategori_subolah_cover_nama + '\'' +
                        "tm_kategori_subolah_cover_tipe = '" + tm_kategori_subolah_cover_tipe + '\'' +
                        "tm_kategori_subolah_cover_ukuran = '" + tm_kategori_subolah_cover_ukuran + '\'' +
                        "tm_kategori_subolah_cover_folder = '" + tm_kategori_subolah_cover_folder + '\'' +
                        "}";
    }

    public AllResponseRecyclerZone() {
    }

    protected AllResponseRecyclerZone(Parcel in) {
        this.tm_kategori_subolah_id = in.readString();
        this.tm_kategori_subolah_nama = in.readString();
        this.tm_kategori_subolah_keterangan = in.readString();
        this.tm_kategori_subolah_isi = in.readString();
        this.tm_kategori_subolah_jenis = in.readString();
        this.tm_kategori_subolah_cover_nama = in.readString();
        this.tm_kategori_subolah_cover_tipe = in.readString();
        this.tm_kategori_subolah_cover_ukuran = in.readString();
        this.tm_kategori_subolah_cover_folder = in.readString();
    }

    public static final Creator<AllResponseRecyclerZone> CREATOR = new Creator<AllResponseRecyclerZone>() {
        @Override
        public AllResponseRecyclerZone createFromParcel(Parcel in) {
            return new AllResponseRecyclerZone(in);
        }

        @Override
        public AllResponseRecyclerZone[] newArray(int size) {
            return new AllResponseRecyclerZone[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tm_kategori_subolah_id);
        dest.writeString(this.tm_kategori_subolah_nama);
        dest.writeString(this.tm_kategori_subolah_keterangan);
        dest.writeString(this.tm_kategori_subolah_isi);
        dest.writeString(this.tm_kategori_subolah_jenis);
        dest.writeString(this.tm_kategori_subolah_cover_nama);
        dest.writeString(this.tm_kategori_subolah_cover_tipe);
        dest.writeString(this.tm_kategori_subolah_cover_ukuran);
        dest.writeString(this.tm_kategori_subolah_cover_folder);
    }
}
