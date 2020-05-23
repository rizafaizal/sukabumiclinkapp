package id.go.sukabumikota.clink.ui.creative;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.google.android.material.snackbar.Snackbar;
import id.go.sukabumikota.clink.R;
import id.go.sukabumikota.clink.SupportLayout.StaggeredItemDecoration;
import id.go.sukabumikota.clink.adapter.ccraft.AdapterCCraft;
import id.go.sukabumikota.clink.api.BaseApiService;
import id.go.sukabumikota.clink.api.koneksi;
import id.go.sukabumikota.clink.model.ccraft.AllResponseCCraft;
import id.go.sukabumikota.clink.model.ccraft.ResponseCCraft;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CCraftActivity extends AppCompatActivity {

    Toolbar toolbar;
    Spinner spnr_ccraft;
    CheckBox chk_ccraft;
    ImageView img_refresh;

    ProgressDialog loading, loadingSspinner;

    Context mContext;
    List<AllResponseCCraft> allResponseCCrafts = new ArrayList<>();
    AdapterCCraft adapterCCraft;
    BaseApiService mApiService;
    private RecyclerView recyclerView;

    String selectedName;

    private StaggeredItemDecoration mStaggeredItemDecoration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ccraft);

        inisialisasi();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.menu_creative));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView = findViewById(R.id.rv_ccraft);

        mContext = this;
        mApiService = koneksi.getAPIService();

        adapterCCraft = new AdapterCCraft(this, allResponseCCrafts);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        img_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });

        callListNamaSouvenir();

        setSpinner();
        setCheckbox();
    }

    private void inisialisasi() {
        toolbar = findViewById(R.id.toolbar_ccraft);
        spnr_ccraft = findViewById(R.id.spnr_pilih_ccraft);
        chk_ccraft = findViewById(R.id.chk_pilih_ccraft);
        img_refresh = findViewById(R.id.img_refresh_ccraft);
    }

    private void setSpinner () {
        spnr_ccraft.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedName = parent.getItemAtPosition(position).toString();
                getResultListCCraftSelected(selectedName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setCheckbox () {
        chk_ccraft.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    getResultListCCraftActivity();
                    spnr_ccraft.setVisibility(View.GONE);
                } else {
                    spnr_ccraft.setSelection(0);
                    spnr_ccraft.setVisibility(View.VISIBLE);
                    onRefresh();
                }
            }
        });
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

    private void callListNamaSouvenir() {
        loadingSspinner = ProgressDialog.show(this, null, "Memuat2...", true, false);

        mApiService.getListSpinnerCreative().enqueue(new Callback<ResponseCCraft>() {
            @Override
            public void onResponse(Call<ResponseCCraft> call, Response<ResponseCCraft> response) {
                if (response.isSuccessful()) {
                    loadingSspinner.dismiss();
                    Boolean error = response.body().isError();

                    allResponseCCrafts = response.body().getAllcreativedata();
                    List<String> listSpinner = new ArrayList<String>();

                    if (error.equals(true)) {
                        String msg = "Tidak ada data Souvenir!";
                        onNothingData(msg);
                    } else {
                        if (allResponseCCrafts == null) {
                            String msg = "Kesalahan Data Souvenir!";
                            onNullData(msg);
                        } else {
                            for (int i = 0; i < allResponseCCrafts.size(); i++){
                                listSpinner.add(allResponseCCrafts.get(i).getTm_kategori_souvenir_nama());
                            }
                            // Set hasil result json ke dalam adapter spinner
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                                    android.R.layout.simple_spinner_item, listSpinner);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spnr_ccraft.setAdapter(adapter);
                        }
                    }

                } else {
                    loadingSspinner.dismiss();
                    String msg = "Gagal mengambil data Nama Souvenir";
                    onErrorServer(msg);
                }
            }

            @Override
            public void onFailure(Call<ResponseCCraft> call, Throwable t) {
                loadingSspinner.dismiss();
                onErrorConnection();
            }
        });
    }

    private void getResultListCCraftActivity(){
        loading = ProgressDialog.show(this, null, "Memuat...", true, false);

        mApiService.getAllCreativeActivity().enqueue(new Callback<ResponseCCraft>() {
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
                            recyclerView.setAdapter(new AdapterCCraft(mContext, allResponseCCrafts));
                            adapterCCraft.notifyDataSetChanged();
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

    private void getResultListCCraftSelected(final String n) {
        loading = ProgressDialog.show(this, null, "Memuat...", true, false);

        mApiService.getAllCreativeSelect(n).enqueue(new Callback<ResponseCCraft>() {
            @Override
            public void onResponse(Call<ResponseCCraft> call, Response<ResponseCCraft> response) {
                if (response.isSuccessful()){
                    loading.dismiss();

                    String success = response.body().getSuccess();
                    final List<AllResponseCCraft> allResponseCCrafts = response.body().getAllcreativedata();

                    if (success.equals("0")) {
                        Snackbar.make(findViewById(R.id.spnr_pilih_ccraft), "Tidak ada data dari : "+n, Snackbar.LENGTH_SHORT).show();
                    } else {
                        if (allResponseCCrafts == null) {
                            String msg = "Kesalahan Data Creative Craft : "+n;
                            onNullData(msg);
                        } else {
                            recyclerView.setAdapter(new AdapterCCraft(mContext, allResponseCCrafts));
                            adapterCCraft.notifyDataSetChanged();
                        }
                    }
                } else {
                    String msg = "Gagal mengambil data Creative Craft : "+n;
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
}
