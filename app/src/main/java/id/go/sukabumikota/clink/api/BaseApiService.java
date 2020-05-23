package id.go.sukabumikota.clink.api;

import id.go.sukabumikota.clink.model.banksampah.ResponseBankSampah;
import id.go.sukabumikota.clink.model.berita.ResponseBerita;
import id.go.sukabumikota.clink.model.berita.fotoberita.ResponseFotoBeritaDetail;
import id.go.sukabumikota.clink.model.boulevard.ResponseBoulevard;
import id.go.sukabumikota.clink.model.ccraft.ResponseCCraft;
import id.go.sukabumikota.clink.model.ccraft.fotoccraftdetail.ResponseFotoCCRaftDetail;
import id.go.sukabumikota.clink.model.education.ResponseEducation;
import id.go.sukabumikota.clink.model.education.fotoeducationdetail.ResponseFotoEducationDetail;
import id.go.sukabumikota.clink.model.kelola.ResponseSpnrSouvenir;
import id.go.sukabumikota.clink.model.rczone.fotorczonedetail.ResponseFotoRcDetail;
import id.go.sukabumikota.clink.model.rczone.ResponseRecyclerZone;

import id.go.sukabumikota.clink.model.satgas.ResponseSatgas;
import id.go.sukabumikota.clink.model.satgas.fotosatgas.ResponseFotoSatgasDetail;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BaseApiService {

    @FormUrlEncoded
    @POST("api/login")
    Call<ResponseBody> login(
            @Field("tm_user_username") String tm_user_username,
            @Field("tm_user_password") String tm_user_password
    );


    //Recycler Zone
    @GET("api/getAllRecyclerZone")
    Call<ResponseRecyclerZone> getAllRecyclerZone();

    @FormUrlEncoded
    @POST("api/getAllRecyclerZoneSelect")
    Call<ResponseRecyclerZone> getAllRecyclerZoneSelect(@Field("jenis_rczone") String jenis_rczone);

    @FormUrlEncoded
    @POST("api/getFotoRecycler")
    Call<ResponseFotoRcDetail> getFotoRecycler(@Field("id_subolah") String id_subolah);


    //Creative Craft
    @GET("api/getAllCreative")
    Call<ResponseCCraft> getAllCreative();

    @GET("api/getAllCreativeActivity")
    Call<ResponseCCraft> getAllCreativeActivity();

    @FormUrlEncoded
    @POST("api/getAllCreativeSelect")
    Call<ResponseCCraft> getAllCreativeSelect(@Field("nama_souvenir") String nama_souvenir);

    @GET("api/getListSpinnerCreative")
    Call<ResponseCCraft> getListSpinnerCreative();

    @FormUrlEncoded
    @POST("api/getFotoCreative")
    Call<ResponseFotoCCRaftDetail> getFotoCreative(@Field("id_kelolasampah") String id_kelolasampah);


    //Education
    @GET("api/getAllEducationActivity")
    Call<ResponseEducation> getAllEducationActivity();

    @GET("api/getAllEducation")
    Call<ResponseEducation> getAllEducation();

    @FormUrlEncoded
    @POST("api/getFotoEducation")
    Call<ResponseFotoEducationDetail> getFotoEducation(@Field("id_edukasi") String id_edukasi);


    //Satgas
    @GET("api/getAllSatgas")
    Call<ResponseSatgas> getAllSatgas();

    @GET("api/getAllSatgasActivity")
    Call<ResponseSatgas> getAllSatgasActivity();

    @FormUrlEncoded
    @POST("api/getFotoSatgas")
    Call<ResponseFotoSatgasDetail> getFotoSatgas(@Field("id_satgas") String id_satgas);


    //Berita
    @GET("api/getAllBerita")
    Call<ResponseBerita> getAllBerita();

    @GET("api/getAllBeritaActivity")
    Call<ResponseBerita> getAllBeritaActivity();

    @FormUrlEncoded
    @POST("api/getFotoBerita")
    Call<ResponseFotoBeritaDetail> getFotoBerita(@Field("id_berita") String id_berita);


    //Boulevard
    @GET("api/getAllBoulevard")
    Call<ResponseBoulevard> getAllBoulevard();

    @GET("api/getAllBoulevardActivity")
    Call<ResponseBoulevard> getAllBoulevardActivity();


    //Bank Sampah
    @GET("api/getAllBankSampah")
    Call<ResponseBankSampah> getAllBankSampah();


    //Profile
    @FormUrlEncoded
    @POST("api/getCheckUsername")
    Call<ResponseBody> getCheckUsername(
            @Field("check_username") String check_username);

    @FormUrlEncoded
    @POST("api/getUpdateUserProfile")
    Call<ResponseBody> getUpdateUserProfile(
            @Field("tm_user_id") String tm_user_id,
            @Field("tm_user_nama") String tm_user_nama,
            @Field("tm_user_username") String tm_user_username,
            @Field("tm_user_password") String tm_user_password
    );


    //Kelola Sampah
    @FormUrlEncoded
    @POST("api/getAllKelolaSampah")
    Call<ResponseCCraft> getAllKelolaSampah(@Field("status") String status);

    @FormUrlEncoded
    @POST("api/getSubmitKelolaSampah")
    Call<String> getSubmitKelolaSampah(
            @Field("tm_kelola_sampah_nama") String tm_kelola_sampah_nama,
            @Field("tm_kelola_sampah_keterangan") String tm_kelola_sampah_keterangan,
            @Field("tm_kelola_sampah_penanggungjawab") String tm_kelola_sampah_penanggungjawab,
            @Field("tm_kelola_sampah_alamatlengkap") String tm_kelola_sampah_alamatlengkap,
            @Field("tm_kelola_sampah_telp") String tm_kelola_sampah_telp,
            @Field("tm_kelola_sampah_email") String tm_kelola_sampah_email,
            @Field("tm_kelola_sampah_latitude") String tm_kelola_sampah_latitude,
            @Field("tm_kelola_sampah_longitude") String tm_kelola_sampah_longitude,
            @Field("tm_kelola_sampah_rt") String tm_kelola_sampah_rt,
            @Field("tm_kelola_sampah_rw") String tm_kelola_sampah_rw,
            @Field("tm_kelurahan_id") String tm_kelurahan_id,
            @Field("tm_kategori_souvenir_id") String tm_kategori_souvenir_id,
            @Field("user_add") String user_add,
            @Field("tm_kelola_sampah_cover_nama") String tm_kelola_sampah_cover_nama,
            @Field("tm_foto_kelola_sampah_nama1") String tm_foto_kelola_sampah_nama1,
            @Field("tm_foto_kelola_sampah_nama2") String tm_foto_kelola_sampah_nama2,
            @Field("image1") String image1,
            @Field("image2") String image2,
            @Field("image3") String image3,
            @Field("image_resize_1") String image_resize_1,
            @Field("image_resize_2") String image_resize_2,
            @Field("image_resize_3") String image_resize_3
    );

    @GET("api/getListSpnrSouvenir")
    Call<ResponseSpnrSouvenir> getListSpnrSouvenir();

}
