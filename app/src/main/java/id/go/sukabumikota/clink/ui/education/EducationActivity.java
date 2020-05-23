package id.go.sukabumikota.clink.ui.education;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

import java.util.ArrayList;
import java.util.List;

import id.go.sukabumikota.clink.R;
import id.go.sukabumikota.clink.SupportLayout.StaggeredItemDecoration;
import id.go.sukabumikota.clink.adapter.education.AdapterEducationDash;
import id.go.sukabumikota.clink.api.BaseApiService;
import id.go.sukabumikota.clink.api.koneksi;
import id.go.sukabumikota.clink.model.education.AllResponseEducation;
import id.go.sukabumikota.clink.model.education.ResponseEducation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EducationActivity extends AppCompatActivity {

    ProgressDialog loading;
    Toolbar toolbar;

    Context mContext;
    List<AllResponseEducation> allResponseEducations = new ArrayList<>();
    AdapterEducationDash adapterEducationDash;
    BaseApiService mApiService;
    private RecyclerView recyclerView;

    private StaggeredItemDecoration mStaggeredItemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

        recyclerView = findViewById(R.id.rv_education);
        toolbar = findViewById(R.id.toolbar_education);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.menu_education));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mContext = this;
        mApiService = koneksi.getAPIService();

        adapterEducationDash = new AdapterEducationDash(this, allResponseEducations);
        RecyclerView.LayoutManager mLayoutManagerEducation = new GridLayoutManager(mContext, 2);
        recyclerView.setLayoutManager(mLayoutManagerEducation);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        getResultListEducation();
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

    private void getResultListEducation(){
        loading = ProgressDialog.show(this, null, "Memuat Data...", true, false);

        mApiService.getAllEducationActivity().enqueue(new Callback<ResponseEducation>() {
            @Override
            public void onResponse(Call<ResponseEducation> call, Response<ResponseEducation> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    Boolean error = response.body().isError();
                    allResponseEducations = response.body().getAlleducationdata();

                    if (error.equals(true)) {
                        String msg = "Tidak ada data Education!";
                        onNothingData(msg);
                    } else {
                        if (allResponseEducations == null) {
                            String msg = "Kesalahan Data Education!";
                            onNullData(msg);
                        } else {
                            recyclerView.setAdapter(new AdapterEducationDash(mContext, allResponseEducations));
                            adapterEducationDash.notifyDataSetChanged();
                        }
                    }

                } else {
                    loading.dismiss();
                    String msg = "Gagal mengambil data Education";
                    onErrorServer(msg);
                }
            }

            @Override
            public void onFailure(Call<ResponseEducation> call, Throwable t) {
                loading.dismiss();
                onErrorConnection();
            }
        });
    }
}
