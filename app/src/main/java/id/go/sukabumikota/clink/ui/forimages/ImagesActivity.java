package id.go.sukabumikota.clink.ui.forimages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

import java.util.ArrayList;
import java.util.List;

import id.go.sukabumikota.clink.R;
import id.go.sukabumikota.clink.adapter.boulevard.AdapterBoulevard;
import id.go.sukabumikota.clink.adapter.ccraft.AdapterFotoCCraftDetail_detail;
import id.go.sukabumikota.clink.adapter.education.AdapterFotoEducationDetail;
import id.go.sukabumikota.clink.adapter.education.AdapterFotoEducationDetail_detail;
import id.go.sukabumikota.clink.adapter.rczone.AdapterFotoRcDetail;
import id.go.sukabumikota.clink.adapter.rczone.AdapterFotoRcDetail_detail;
import id.go.sukabumikota.clink.adapter.satgas.AdapterFotoSatgasDetail;
import id.go.sukabumikota.clink.adapter.satgas.AdapterFotoSatgasDetail_detail;
import id.go.sukabumikota.clink.api.BaseApiService;
import id.go.sukabumikota.clink.api.koneksi;
import id.go.sukabumikota.clink.model.boulevard.AllResponseBoulevard;
import id.go.sukabumikota.clink.model.boulevard.ResponseBoulevard;
import id.go.sukabumikota.clink.model.ccraft.fotoccraftdetail.AllFotoCCraftDetail;
import id.go.sukabumikota.clink.model.ccraft.fotoccraftdetail.ResponseFotoCCRaftDetail;
import id.go.sukabumikota.clink.model.education.fotoeducationdetail.AllFotoEducationDetail;
import id.go.sukabumikota.clink.model.education.fotoeducationdetail.ResponseFotoEducationDetail;
import id.go.sukabumikota.clink.model.rczone.fotorczonedetail.AllFotoRcDetail;
import id.go.sukabumikota.clink.model.rczone.fotorczonedetail.ResponseFotoRcDetail;
import id.go.sukabumikota.clink.model.satgas.fotosatgas.AllFotoSatgasDetail;
import id.go.sukabumikota.clink.model.satgas.fotosatgas.ResponseFotoSatgasDetail;
import id.go.sukabumikota.clink.ui.creative.DetailCCraftActivity;
import id.go.sukabumikota.clink.ui.education.DetailEducationActivity;
import id.go.sukabumikota.clink.ui.satgas.DetailSatgasActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImagesActivity extends AppCompatActivity {
    Toolbar toolbar;

    ProgressDialog loading;

    Context mContext;
    List<AllFotoRcDetail> allFotoRcDetailList = new ArrayList<>();
    List<AllFotoCCraftDetail> allFotoCCraftDetailsList = new ArrayList<>();
    List<AllFotoSatgasDetail> allFotoSatgasDetails = new ArrayList<>();
    List<AllFotoEducationDetail> allFotoEducationDetailList = new ArrayList<>();
    AdapterFotoRcDetail_detail adapterFotoRcDetail;
    AdapterFotoCCraftDetail_detail adapterFotoCCraftDetail_detail;
    AdapterFotoSatgasDetail_detail adapterFotoSatgasDetail_detail;
    AdapterFotoEducationDetail_detail adapterFotoEducationDetail_detail;
    BaseApiService mApiService;
    private RecyclerView recyclerView;

    String id, activity;

    public static final String EXTRA_DATA= "extra_data";
    public static final String EXTRA_DATA2= "extra_data2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

        toolbar = findViewById(R.id.toolbar_fotodetails);
        recyclerView = findViewById(R.id.rv_fotodetails);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mContext = this;
        mApiService = koneksi.getAPIService();

        id = getIntent().getStringExtra(EXTRA_DATA);
        activity = getIntent().getStringExtra(EXTRA_DATA2);

        assert activity != null;
        if (activity.equals("rczone")) {
            adapterFotoRcDetail = new AdapterFotoRcDetail_detail(this, allFotoRcDetailList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            getFotoRc_detail();
        } else if (activity.equals("ccraft")) {
            adapterFotoCCraftDetail_detail = new AdapterFotoCCraftDetail_detail(this, allFotoCCraftDetailsList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            getFotoCCraft_detail();
        } else if (activity.equals("satgas")) {
            adapterFotoSatgasDetail_detail = new AdapterFotoSatgasDetail_detail(this, allFotoSatgasDetails);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            getFotoSatgas_detail();
        } else if (activity.equals("education")) {
            adapterFotoEducationDetail_detail = new AdapterFotoEducationDetail_detail(this, allFotoEducationDetailList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            getFotoEducation_detail();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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
                        "Klik Refresh untuk memuat kembali.")
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
                        "Klik Refresh untuk memuat kembali.")
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
                .setDescription("Tidak ada data yang dapat dimuat.\n" +
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

    private void onNullData(String message) {
        new MaterialStyledDialog.Builder(mContext)
                .setTitle(message)
                .setDescription("Data bermasalah.\n\n" +
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

    private void getFotoRc_detail(){
        loading = ProgressDialog.show(ImagesActivity.this, null, "Memuat...",
                true, false);

        mApiService.getFotoRecycler(id).enqueue(new Callback<ResponseFotoRcDetail>() {
            @Override
            public void onResponse(Call<ResponseFotoRcDetail> call, Response<ResponseFotoRcDetail> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    Boolean error = response.body().isError();
                    allFotoRcDetailList = response.body().getAllFotoRcDetailList();

                    if (error.equals(true)) {
                        String msg = "Tidak ada data Foto!";
                        onNothingData(msg);
                    } else {
                        if (allFotoRcDetailList == null) {
                            String msg = "Kesalahan Data Foto!";
                            onNullData(msg);
                        } else {
                            recyclerView.setAdapter(new AdapterFotoRcDetail_detail(mContext, allFotoRcDetailList));
                            adapterFotoRcDetail.notifyDataSetChanged();
                        }
                    }


                } else {
                    loading.dismiss();
                    String msg = "Gagal mengambil data Foto";
                    onErrorServer(msg);
                }
            }
            @Override
            public void onFailure(Call<ResponseFotoRcDetail> call, Throwable t) {
                loading.dismiss();
                onErrorConnection();
            }
        });
    }

    private void getFotoCCraft_detail(){
        loading = ProgressDialog.show(ImagesActivity.this, null, "Memuat...",
                true, false);

        mApiService.getFotoCreative(id).enqueue(new Callback<ResponseFotoCCRaftDetail>() {
            @Override
            public void onResponse(Call<ResponseFotoCCRaftDetail> call, Response<ResponseFotoCCRaftDetail> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    Boolean error = response.body().isError();
                    allFotoCCraftDetailsList = response.body().getAllfotocreativecraft();

                    if (error.equals(true)) {
                        String msg = "Tidak ada data Foto!";
                        onNothingData(msg);
                    } else {
                        if (allFotoCCraftDetailsList == null) {
                            String msg = "Kesalahan Data Foto!";
                            onNullData(msg);
                        } else {
                            recyclerView.setAdapter(new AdapterFotoCCraftDetail_detail(mContext, allFotoCCraftDetailsList));
                            adapterFotoCCraftDetail_detail.notifyDataSetChanged();
                        }
                    }


                } else {
                    loading.dismiss();
                    String msg = "Gagal mengambil data Foto";
                    onErrorServer(msg);
                }
            }
            @Override
            public void onFailure(Call<ResponseFotoCCRaftDetail> call, Throwable t) {
                loading.dismiss();
                onErrorConnection();
            }
        });
    }

    private void getFotoSatgas_detail(){
        loading = ProgressDialog.show(ImagesActivity.this, null, "Memuat...",
                true, false);

        mApiService.getFotoSatgas(id).enqueue(new Callback<ResponseFotoSatgasDetail>() {
            @Override
            public void onResponse(Call<ResponseFotoSatgasDetail> call, Response<ResponseFotoSatgasDetail> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    Boolean error = response.body().isError();
                    allFotoSatgasDetails = response.body().getAllfotosatgas();

                    if (error.equals(true)) {
                        String msg = "Tidak ada data Foto!";
                        onNothingData(msg);
                    } else {
                        if (allFotoSatgasDetails == null) {
                            String msg = "Kesalahan Data Foto!";
                            onNullData(msg);
                        } else {
                            recyclerView.setAdapter(new AdapterFotoSatgasDetail_detail(mContext, allFotoSatgasDetails));
                            adapterFotoSatgasDetail_detail.notifyDataSetChanged();
                        }
                    }


                } else {
                    loading.dismiss();
                    String msg = "Gagal mengambil data Foto";
                    onErrorServer(msg);
                }
            }
            @Override
            public void onFailure(Call<ResponseFotoSatgasDetail> call, Throwable t) {
                loading.dismiss();
                onErrorConnection();
            }
        });
    }

    private void getFotoEducation_detail(){
        loading = ProgressDialog.show(ImagesActivity.this, null, "Memuat...",
                true, false);

        mApiService.getFotoEducation(id).enqueue(new Callback<ResponseFotoEducationDetail>() {
            @Override
            public void onResponse(Call<ResponseFotoEducationDetail> call, Response<ResponseFotoEducationDetail> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    Boolean error = response.body().isError();
                    allFotoEducationDetailList = response.body().getAllfotoeducation();

                    if (error.equals(true)) {
                        String msg = "Tidak ada data foto!";
                        onNothingData(msg);
                    } else {
                        if (allFotoEducationDetailList == null) {
                            String msg = "Kesalahan Data Foto!";
                            onNullData(msg);
                        } else {
                            recyclerView.setAdapter(new AdapterFotoEducationDetail_detail(mContext, allFotoEducationDetailList));
                            adapterFotoEducationDetail_detail.notifyDataSetChanged();
                        }
                    }


                } else {
                    loading.dismiss();
                    String msg = "Gagal mengambil data Foto";
                    onErrorServer(msg);
                }
            }
            @Override
            public void onFailure(Call<ResponseFotoEducationDetail> call, Throwable t) {
                loading.dismiss();
                onErrorConnection();
            }
        });
    }
}
