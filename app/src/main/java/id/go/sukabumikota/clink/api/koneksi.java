package id.go.sukabumikota.clink.api;

public class koneksi {

    public static final String BASE_URL_API = "http://sukabumiclink.jatayu.web.id/";
//    public static final String BASE_URL_API = "http://192.168.43.66/sukabumiclink/public/";

    public static BaseApiService getAPIService() {
        return RetrofitClient.getClient(BASE_URL_API)
                .create(BaseApiService.class);
    }

}
