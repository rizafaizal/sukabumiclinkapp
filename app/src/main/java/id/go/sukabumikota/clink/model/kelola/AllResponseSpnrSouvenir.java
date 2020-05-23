package id.go.sukabumikota.clink.model.kelola;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class AllResponseSpnrSouvenir {

    @SerializedName("tm_kategori_souvenir_id")
    private String tm_kategori_souvenir_id;

    @SerializedName("tm_kategori_souvenir_nama")
    private String tm_kategori_souvenir_nama;

    public String getTm_kategori_souvenir_id() {
        return tm_kategori_souvenir_id;
    }

    public void setTm_kategori_souvenir_id(String tm_kategori_souvenir_id) {
        this.tm_kategori_souvenir_id = tm_kategori_souvenir_id;
    }

    public String getTm_kategori_souvenir_nama() {
        return tm_kategori_souvenir_nama;
    }

    public void setTm_kategori_souvenir_nama(String tm_kategori_souvenir_nama) {
        this.tm_kategori_souvenir_nama = tm_kategori_souvenir_nama;
    }

    @NonNull
    @Override
    public String toString() {
        return
                "ResponseSpnrSouvenir{" +
                        "tm_kategori_souvenir_id = '" + tm_kategori_souvenir_id + '\'' +
                        "tm_kategori_souvenir_nama = '" + tm_kategori_souvenir_nama + '\'' +
                        "}";
    }
}
