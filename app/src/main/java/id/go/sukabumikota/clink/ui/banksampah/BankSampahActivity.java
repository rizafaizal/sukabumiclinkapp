package id.go.sukabumikota.clink.ui.banksampah;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import id.go.sukabumikota.clink.R;
import id.go.sukabumikota.clink.api.BaseApiService;
import id.go.sukabumikota.clink.api.koneksi;
import id.go.sukabumikota.clink.model.banksampah.AllResponseBankSampah;
import id.go.sukabumikota.clink.model.banksampah.ResponseBankSampah;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BankSampahActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<AllResponseBankSampah> mListMarker = new ArrayList<>();

    Context mContext;
    BaseApiService mApiService;

    private UiSettings mUiSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_sampah);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mContext = this;
        mApiService = koneksi.getAPIService();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mUiSettings = mMap.getUiSettings();

//        // Add a marker in Sydney and move the camera
//        LatLng home = new LatLng(-6.9038397, 106.8768418);
//        mMap.addMarker(new MarkerOptions().position(home).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(home));
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-6.9038397, 106.8768418), 11.0f));
        getAllDataLocationLatLng();
    }

    void onRefresh() {
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }

    private void onErrorConnection() {
        new MaterialStyledDialog.Builder(mContext)
                .setTitle("Koneksi internet bermasalah")
                .setDescription("Pastikan koneksi internet anda berjalan dengan baik.\n" +
                        "Klik Refresh untuk memuat kembali!")
                .setIcon(R.drawable.icon_warning_yellow)
                .setHeaderDrawable(R.drawable.bg_gradient_red)
                .withDialogAnimation(true)
                .withIconAnimation(true)
                .setCancelable(false)
                .setPositiveText("Refresh")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        onRefresh();
                    }
                })
                .setNegativeText("Kembali")
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        finish();
                    }
                })
                .show();
    }

    private void onErrorServer(String message) {
        new MaterialStyledDialog.Builder(mContext)
                .setTitle(message)
                .setDescription("Gagal terhubung ke server.\n" +
                        "Klik Refresh untuk memuat kembali!")
                .setIcon(R.drawable.icon_warning_red)
                .setHeaderDrawable(R.drawable.bg_gradient_orange)
                .withDialogAnimation(true)
                .withIconAnimation(true)
                .setCancelable(false)
                .setPositiveText("Refresh")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        onRefresh();
                    }
                })
                .setNegativeText("Kembali")
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        finish();
                    }
                })
                .show();
    }

    private void onNothingData(String message) {
        new MaterialStyledDialog.Builder(mContext)
                .setTitle(message)
                .setDescription("Tidak ada data yang dapat dimuat.\n\n" +
                        "Klik Refresh untuk memuat kembali.")
                .setIcon(R.drawable.icon_warning_red)
                .setHeaderDrawable(R.drawable.bg_gradient_orange)
                .withDialogAnimation(true)
                .withIconAnimation(true)
                .setCancelable(false)
                .setNegativeText("Kembali")
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        finish();
                    }
                })
                .setPositiveText("Refresh")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        onRefresh();
                    }
                })
                .show();
    }

    private void onNullData(String message) {
        new MaterialStyledDialog.Builder(mContext)
                .setTitle(message)
                .setDescription("Data bermasalah, atau Data kosong\n" +
                        "Klik Refresh untuk memuat kembali.")
                .setIcon(R.drawable.icon_warning_red)
                .setHeaderDrawable(R.drawable.bg_gradient_orange)
                .withDialogAnimation(true)
                .withIconAnimation(true)
                .setCancelable(true)
                .setNegativeText("OK")
                .setPositiveText("Refresh")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        onRefresh();
                    }
                })
                .show();
    }

    private void getAllDataLocationLatLng(){
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Menampilkan data marker ..");
        dialog.show();

        mApiService.getAllBankSampah().enqueue(new Callback<ResponseBankSampah>() {
            @Override
            public void onResponse(Call<ResponseBankSampah> call, Response<ResponseBankSampah> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    Boolean error = response.body().isError();

                    mListMarker = response.body().getAllbanksampahdata();

                    if (error.equals(true)) {
                        String msg = "Tidak ada data Bank Sampah!";
                        onNothingData(msg);
                    } else {
                        if (mListMarker == null) {
                            String msg = "Kesalahan Data Bank Sampah!";
                            onNullData(msg);
                        } else {
                            initMarker(mListMarker);
                        }

                    }

                } else {
                    dialog.dismiss();
                    String msg = "Gagal mengambil data Bank Sampah";
                    onErrorServer(msg);
                }

            }

            @Override
            public void onFailure(Call<ResponseBankSampah> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(BankSampahActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                onErrorConnection();
            }
        });

    }

    private void initMarker(List<AllResponseBankSampah> listData){
        for (int i=0; i<mListMarker.size(); i++){
            LatLng location = new LatLng(Double.parseDouble(mListMarker.get(i).getTm_banksampah_latitude()), Double.parseDouble(mListMarker.get(i).getTm_banksampah_longitude()));
            mMap.addMarker(new MarkerOptions()
                    .position(location)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_location_64))
                    .title(mListMarker.get(i).getTm_banksampah_nama())
                    .snippet(mListMarker.get(i).getTm_banksampah_alamat()));
            LatLng latLng = new LatLng(Double.parseDouble(mListMarker.get(0).getTm_banksampah_latitude()), Double.parseDouble(mListMarker.get(0).getTm_banksampah_longitude()));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latLng.latitude,latLng.longitude), 13.0f));
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            mUiSettings.setZoomControlsEnabled(true);
            mUiSettings.setCompassEnabled(true);
            mUiSettings.setMapToolbarEnabled(true);
        }
    }
}
