package id.go.sukabumikota.clink.model.boulevard;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class AllResponseBoulevard implements Parcelable {

    @SerializedName("tm_boulevard_id")
    private String tm_boulevard_id;

    @SerializedName("tm_boulevard_jalan")
    private String tm_boulevard_jalan;

    @SerializedName("tm_boulevard_cover_folder")
    private String tm_boulevard_cover_folder;

    @SerializedName("tm_boulevard_cover_nama")
    private String tm_boulevard_cover_nama;


    public String getTm_boulevard_id() {
        return tm_boulevard_id;
    }

    public void setTm_boulevard_id(String tm_boulevard_id) {
        this.tm_boulevard_id = tm_boulevard_id;
    }

    public String getTm_boulevard_jalan() {
        return tm_boulevard_jalan;
    }

    public void setTm_boulevard_jalan(String tm_boulevard_jalan) {
        this.tm_boulevard_jalan = tm_boulevard_jalan;
    }

    public String getTm_boulevard_cover_folder() {
        return tm_boulevard_cover_folder;
    }

    public void setTm_boulevard_cover_folder(String tm_boulevard_cover_folder) {
        this.tm_boulevard_cover_folder = tm_boulevard_cover_folder;
    }

    public String getTm_boulevard_cover_nama() {
        return tm_boulevard_cover_nama;
    }

    public void setTm_boulevard_cover_nama(String tm_boulevard_cover_nama) {
        this.tm_boulevard_cover_nama = tm_boulevard_cover_nama;
    }

    @NonNull
    @Override
    public String toString() {
        return
                "ResponseBoulevard{" +
                        "tm_boulevard_id = '" + tm_boulevard_id + '\'' +
                        "tm_boulevard_jalan = '" + tm_boulevard_jalan + '\'' +
                        "tm_boulevard_cover_folder = '" +tm_boulevard_cover_folder + '\'' +
                        "tm_boulevard_cover_nama = '" + tm_boulevard_cover_nama + '\'' +
                        "}";
    }

    protected AllResponseBoulevard(Parcel in) {
        this.tm_boulevard_id = in.readString();
        this.tm_boulevard_jalan = in.readString();
        this.tm_boulevard_cover_folder = in.readString();
        this.tm_boulevard_cover_nama = in.readString();
    }

    public static final Creator<AllResponseBoulevard> CREATOR = new Creator<AllResponseBoulevard>() {
        @Override
        public AllResponseBoulevard createFromParcel(Parcel in) {
            return new AllResponseBoulevard(in);
        }

        @Override
        public AllResponseBoulevard[] newArray(int size) {
            return new AllResponseBoulevard[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tm_boulevard_id);
        dest.writeString(this.tm_boulevard_jalan);
        dest.writeString(this.tm_boulevard_cover_folder);
        dest.writeString(this.tm_boulevard_cover_nama);
    }
}
