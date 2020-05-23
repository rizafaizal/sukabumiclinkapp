package id.go.sukabumikota.clink.ui.formkelolasampah;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import id.go.sukabumikota.clink.R;
import id.go.sukabumikota.clink.adapter.ccraft.AdapterCCraft;
import id.go.sukabumikota.clink.adapter.kelolasampah.AdapterKelola;
import id.go.sukabumikota.clink.api.BaseApiService;
import id.go.sukabumikota.clink.api.koneksi;
import id.go.sukabumikota.clink.model.ccraft.AllResponseCCraft;
import id.go.sukabumikota.clink.model.ccraft.ResponseCCraft;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormKelolaSampahActivity extends AppCompatActivity {

    Toolbar toolbar;
    FloatingActionButton fab_add;
    Spinner spnr_status;
    ImageView img_refresh;

    ProgressDialog loading;

    String id, nama, username, password, id_kelurahan, nama_kelurahan,
            id_kecamatan, nama_kecamatan, id_rw, nama_rw, id_rt, nama_rt;

    Context mContext;
    List<AllResponseCCraft> allResponseCCrafts = new ArrayList<>();
    AdapterKelola adapterKelola;
    BaseApiService mApiService;
    private RecyclerView recyclerView;

    public final static String TAG_ID = "tm_user_id";
    public final static String TAG_NAMA = "tm_user_nama";
    public final static String TAG_USERNAME = "tm_user_username";
    public final static String TAG_PASSWORD = "tm_user_password";
    public final static String TAG_ID_KELURAHAN = "tm_kelurahan_id";
    public final static String TAG_NAMA_KELURAHAN = "tm_kelurahan_nama";
    public final static String TAG_ID_KECAMATAN = "tm_kecamatan_id";
    public final static String TAG_NAMA_KECAMATAN = "tm_kecamatan_nama";
    public final static String TAG_ID_RW = "tm_rw_id";
    public final static String TAG_NAMA_RW = "tm_rw_nama";
    public final static String TAG_ID_RT = "tm_rt_id";
    public final static String TAG_NAMA_RT = "tm_rt_nama";


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_kelola_sampah);

        inisialisasi();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.menu_formkelolasampah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView = findViewById(R.id.rv_kelola);

        mContext = this;
        mApiService = koneksi.getAPIService();

        adapterKelola = new AdapterKelola(this, allResponseCCrafts);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        id = getIntent().getStringExtra(TAG_ID);
        nama = getIntent().getStringExtra(TAG_NAMA);
        username = getIntent().getStringExtra(TAG_USERNAME);
        password = getIntent().getStringExtra(TAG_PASSWORD);
        id_kelurahan = getIntent().getStringExtra(TAG_ID_KELURAHAN);
        nama_kelurahan = getIntent().getStringExtra(TAG_NAMA_KELURAHAN);
        id_kecamatan = getIntent().getStringExtra(TAG_ID_KECAMATAN);
        nama_kecamatan = getIntent().getStringExtra(TAG_NAMA_KECAMATAN);
        id_rw = getIntent().getStringExtra(TAG_ID_RW);
        nama_rw = getIntent().getStringExtra(TAG_NAMA_RW);
        id_rt = getIntent().getStringExtra(TAG_ID_RT);
        nama_rt = getIntent().getStringExtra(TAG_NAMA_RT);

        getSpnrStatus();

        img_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add = new Intent(FormKelolaSampahActivity.this, AddKelolaSampahActivity.class);
                add.putExtra(TAG_ID, id);
                add.putExtra(TAG_NAMA, nama);
                add.putExtra(TAG_USERNAME, username);
                add.putExtra(TAG_PASSWORD, password);
                add.putExtra(TAG_ID_KELURAHAN, id_kelurahan);
                add.putExtra(TAG_NAMA_KELURAHAN, nama_kelurahan);
                add.putExtra(TAG_ID_KECAMATAN, id_kecamatan);
                add.putExtra(TAG_NAMA_KECAMATAN, nama_kecamatan);
                add.putExtra(TAG_ID_RW, id_rw);
                add.putExtra(TAG_NAMA_RW, nama_rw);
                add.putExtra(TAG_ID_RT, id_rt);
                add.putExtra(TAG_NAMA_RT, nama_rt);
                startActivity(add);
            }
        });
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

    void getSpnrStatus() {
        spnr_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();
                getResultListCCraftActivity(selectedName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void inisialisasi() {
        toolbar = findViewById(R.id.toolbar_formkelola);
        fab_add = findViewById(R.id.fab_add_kelola);
        spnr_status = findViewById(R.id.spnr_status_kelola);
        img_refresh = findViewById(R.id.img_refresh_kelola);
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

    private void getResultListCCraftActivity(String n){
        loading = ProgressDialog.show(this, null, "Memuat...", true, false);

        mApiService.getAllKelolaSampah(n).enqueue(new Callback<ResponseCCraft>() {
            @Override
            public void onResponse(Call<ResponseCCraft> call, Response<ResponseCCraft> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    Boolean error = response.body().isError();

                    allResponseCCrafts = response.body().getAllcreativedata();

                    if (error.equals(true)) {
                        String msg = "Tidak ada data Creative Craft!";
                        onNothingData(msg);
                    } else {
                        if (allResponseCCrafts == null) {
                            String msg = "Kesalahan Data Creative Craft!";
                            onNullData(msg);
                        } else {
                            recyclerView.setAdapter(new AdapterKelola(mContext, allResponseCCrafts));
                            adapterKelola.notifyDataSetChanged();
                        }
                    }

                } else {
                    loading.dismiss();
                    String msg = "Gagal mengambil data Creative Craft";
                    onErrorServer(msg);
                }
            }

            @Override
            public void onFailure(Call<ResponseCCraft> call, Throwable t) {
                loading.dismiss();
                onErrorConnection();
            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_kelola, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menu_cari_kelola:
//                Toast.makeText(FormKelolaSampahActivity.this, "Cari", Toast.LENGTH_SHORT).show();
//                break;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//        return true;
//    }
}
