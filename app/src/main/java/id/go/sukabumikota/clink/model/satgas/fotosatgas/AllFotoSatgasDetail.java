package id.go.sukabumikota.clink.model.satgas.fotosatgas;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class AllFotoSatgasDetail implements Parcelable {

    @SerializedName("tm_satgas_id")
    private String tm_satgas_id;

    @SerializedName("tm_foto_satgas_id")
    private String tm_foto_satgas_id;

    @SerializedName("tm_foto_satgas_nama")
    private String tm_foto_satgas_nama;

    @SerializedName("tm_foto_satgas_tipe")
    private String tm_foto_satgas_tipe;

    @SerializedName("tm_foto_satgas_ukuran")
    private String tm_foto_satgas_ukuran;

    @SerializedName("tm_foto_satgas_folder")
    private String tm_foto_satgas_folder;


    public String getTm_satgas_id() {
        return tm_satgas_id;
    }

    public void setTm_satgas_id(String tm_satgas_id) {
        this.tm_satgas_id = tm_satgas_id;
    }

    public String getTm_foto_satgas_id() {
        return tm_foto_satgas_id;
    }

    public void setTm_foto_satgas_id(String tm_foto_satgas_id) {
        this.tm_foto_satgas_id = tm_foto_satgas_id;
    }

    public String getTm_foto_satgas_nama() {
        return tm_foto_satgas_nama;
    }

    public void setTm_foto_satgas_nama(String tm_foto_satgas_nama) {
        this.tm_foto_satgas_nama = tm_foto_satgas_nama;
    }

    public String getTm_foto_satgas_tipe() {
        return tm_foto_satgas_tipe;
    }

    public void setTm_foto_satgas_tipe(String tm_foto_satgas_tipe) {
        this.tm_foto_satgas_tipe = tm_foto_satgas_tipe;
    }

    public String getTm_foto_satgas_ukuran() {
        return tm_foto_satgas_ukuran;
    }

    public void setTm_foto_satgas_ukuran(String tm_foto_satgas_ukuran) {
        this.tm_foto_satgas_ukuran = tm_foto_satgas_ukuran;
    }

    public String getTm_foto_satgas_folder() {
        return tm_foto_satgas_folder;
    }

    public void setTm_foto_satgas_folder(String tm_foto_satgas_folder) {
        this.tm_foto_satgas_folder = tm_foto_satgas_folder;
    }

    @NonNull
    @Override
    public String toString() {
        return
                "ResponseFotoSatgasDetail{" +
                        "tm_satgas_id = '" + tm_satgas_id + '\'' +
                        "tm_foto_satgas_id = '" + tm_foto_satgas_id + '\'' +
                        "tm_foto_satgas_nama = '" + tm_foto_satgas_nama + '\'' +
                        "tm_foto_satgas_tipe = '" + tm_foto_satgas_tipe + '\'' +
                        "tm_foto_satgas_ukuran = '" + tm_foto_satgas_ukuran + '\'' +
                        "tm_foto_satgas_folder = '" + tm_foto_satgas_folder + '\'' +
                        "}";
    }


    protected AllFotoSatgasDetail(Parcel in) {
        this.tm_satgas_id = in.readString();
        this.tm_foto_satgas_id = in.readString();
        this.tm_foto_satgas_nama = in.readString();
        this.tm_foto_satgas_tipe = in.readString();
        this.tm_foto_satgas_ukuran = in.readString();
        this.tm_foto_satgas_folder = in.readString();
    }

    public static final Creator<AllFotoSatgasDetail> CREATOR = new Creator<AllFotoSatgasDetail>() {
        @Override
        public AllFotoSatgasDetail createFromParcel(Parcel in) {
            return new AllFotoSatgasDetail(in);
        }

        @Override
        public AllFotoSatgasDetail[] newArray(int size) {
            return new AllFotoSatgasDetail[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tm_satgas_id);
        dest.writeString(this.tm_foto_satgas_id);
        dest.writeString(this.tm_foto_satgas_nama);
        dest.writeString(this.tm_foto_satgas_tipe);
        dest.writeString(this.tm_foto_satgas_ukuran);
        dest.writeString(this.tm_foto_satgas_folder);
    }
}
