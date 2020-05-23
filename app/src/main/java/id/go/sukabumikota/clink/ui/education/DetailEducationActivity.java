package id.go.sukabumikota.clink.ui.education;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

import java.util.ArrayList;
import java.util.List;

import id.go.sukabumikota.clink.R;
import id.go.sukabumikota.clink.adapter.education.AdapterFotoEducationDetail;
import id.go.sukabumikota.clink.api.BaseApiService;
import id.go.sukabumikota.clink.api.koneksi;
import id.go.sukabumikota.clink.model.education.AllResponseEducation;
import id.go.sukabumikota.clink.model.education.fotoeducationdetail.AllFotoEducationDetail;
import id.go.sukabumikota.clink.model.education.fotoeducationdetail.ResponseFotoEducationDetail;
import id.go.sukabumikota.clink.ui.forimages.ImagesActivity;
import jp.wasabeef.glide.transformations.gpu.VignetteFilterTransformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class DetailEducationActivity extends AppCompatActivity {

    ImageView img;
    TextView tv_isi, tv_judul;
    LinearLayout view_bg_education_detail;
    CardView cv_education_fotodetails;

    String id, isi;

    ProgressDialog loading;

    Context mContext;
    List<AllFotoEducationDetail> allFotoEducationDetailList = new ArrayList<>();
    AdapterFotoEducationDetail adapterFotoEducationDetail;
    BaseApiService mApiService;
    private RecyclerView recyclerView;

    public static final String EXTRA_DATA= "extra_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_education);

        tv_isi = findViewById(R.id.tv_isi_education_detail);
        tv_judul = findViewById(R.id.tv_judul_education_detail);
        img = findViewById(R.id.img_detailEducation);
        recyclerView = findViewById(R.id.rv_foto_detail);
        view_bg_education_detail = findViewById(R.id.view_bg_education_detail);
        cv_education_fotodetails = findViewById(R.id.cv_education_fotodetails);

        mContext = this;
        mApiService = koneksi.getAPIService();

        Toolbar toolbar = findViewById(R.id.toolbar_detailEducation);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        AllResponseEducation allResponseEducation = getIntent().getParcelableExtra(EXTRA_DATA);
        assert allResponseEducation != null;
        getSupportActionBar().setTitle(allResponseEducation.getTm_edukasi_judul());
        id = allResponseEducation.getTm_edukasi_id();
        isi = allResponseEducation.getTm_edukasi_konten();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tv_isi.setText(Html.fromHtml(isi, Html.FROM_HTML_MODE_LEGACY));
        } else {
            tv_isi.setText(isi);
        }

        tv_judul.setText(allResponseEducation.getTm_edukasi_judul());

        Glide.with(getApplicationContext())
                .load(koneksi.BASE_URL_API
                        + "storage/images/education/"
                        + allResponseEducation.getTm_edukasi_cover_nama())
                .apply(bitmapTransform(new VignetteFilterTransformation(new PointF(0.5f, 0.5f),
                        new float[]{0.0f, 0.0f, 0.0f}, 0.0f, 0.75f)).dontAnimate())
                .into(img);

        adapterFotoEducationDetail = new AdapterFotoEducationDetail(this, allFotoEducationDetailList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        getFotoEducation_detail();

        view_bg_education_detail.setBackgroundColor(getColorWithAlpha(Color.BLACK, 0.3f));

        cv_education_fotodetails.setBackgroundColor(getColorWithAlpha(Color.BLACK, 0.5f));
        cv_education_fotodetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String activity = "education";

                Intent moveToDetail = new Intent(mContext, ImagesActivity.class);
                moveToDetail.putExtra(ImagesActivity.EXTRA_DATA, id);
                moveToDetail.putExtra(ImagesActivity.EXTRA_DATA2, activity);
                mContext.startActivity(moveToDetail);
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

    void goneItem() {
        cv_education_fotodetails.setVisibility(View.GONE);
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

    private void getFotoEducation_detail(){
        loading = ProgressDialog.show(DetailEducationActivity.this, null, "Memuat...",
                true, false);

        mApiService.getFotoEducation(id).enqueue(new Callback<ResponseFotoEducationDetail>() {
            @Override
            public void onResponse(Call<ResponseFotoEducationDetail> call, Response<ResponseFotoEducationDetail> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    Boolean error = response.body().isError();
                    allFotoEducationDetailList = response.body().getAllfotoeducation();

                    if (error.equals(true)) {
                        Toast.makeText(mContext, "Tidak ada foto ilustrasi!", Toast.LENGTH_SHORT).show();
//                        String msg = "Tidak ada data foto!";
//                        onNothingData(msg);
                        goneItem();
                    } else {
                        if (allFotoEducationDetailList == null) {
                            Toast.makeText(mContext, "Tidak ada foto ilustrasi!", Toast.LENGTH_SHORT).show();
//                            String msg = "Kesalahan Data Foto!";
//                            onNullData(msg);
                            goneItem();
                        } else {
                            recyclerView.setAdapter(new AdapterFotoEducationDetail(mContext, allFotoEducationDetailList));
                            adapterFotoEducationDetail.notifyDataSetChanged();
                        }
                    }


                } else {
                    loading.dismiss();
                    String msg = "Gagal mengambil data Foto";
                    onErrorServer(msg);
                    goneItem();
                }
            }
            @Override
            public void onFailure(Call<ResponseFotoEducationDetail> call, Throwable t) {
                loading.dismiss();
                onErrorConnection();
                goneItem();
            }
        });
    }

    public static int getColorWithAlpha(int color, float ratio) {
        int newColor = 0;
        int alpha = Math.round(Color.alpha(color) * ratio);
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
        newColor = Color.argb(alpha, r, g, b);
        return newColor;
    }
}
