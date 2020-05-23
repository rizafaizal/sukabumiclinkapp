package id.go.sukabumikota.clink.ui.berita;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import id.go.sukabumikota.clink.R;
import id.go.sukabumikota.clink.adapter.berita.AdapterFotoBeritaDetail;
import id.go.sukabumikota.clink.api.BaseApiService;
import id.go.sukabumikota.clink.api.koneksi;
import id.go.sukabumikota.clink.model.berita.AllResponseBerita;
import id.go.sukabumikota.clink.model.berita.fotoberita.AllFotoBeritaDetail;
import id.go.sukabumikota.clink.model.berita.fotoberita.ResponseFotoBeritaDetail;
import jp.wasabeef.glide.transformations.gpu.VignetteFilterTransformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class DetailBeritaActivity extends AppCompatActivity {

    ImageView img;
    TextView tv_isi, tv_judul, tv_tag, tv_date;
    LinearLayout view_bg_berita_detail;
    Button btn_back;

    String id, date, isi;
    private Date oneWayTripDate;

    ProgressDialog loading;

    Context mContext;
    List<AllFotoBeritaDetail> allFotoBeritaDetailList = new ArrayList<>();
    AdapterFotoBeritaDetail adapterFotoBeritaDetail;
    BaseApiService mApiService;
    private RecyclerView recyclerView;

    public static final String EXTRA_DATA= "extra_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_berita);

        tv_isi = findViewById(R.id.tv_isi_berita_detail);
        tv_judul = findViewById(R.id.tv_judul_berita_detail);
        tv_tag = findViewById(R.id.tv_tag_berita_detail);
        tv_date = findViewById(R.id.tv_date_berita_detail);
        img = findViewById(R.id.img_detailBerita);
        recyclerView = findViewById(R.id.rv_foto2_detail);
        view_bg_berita_detail = findViewById(R.id.view_bg_berita_detail);
        btn_back = findViewById(R.id.btn_back_berita_detail);

        mContext = this;
        mApiService = koneksi.getAPIService();

        Toolbar toolbar = findViewById(R.id.toolbar_detailBerita);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        AllResponseBerita allResponseBerita = getIntent().getParcelableExtra(EXTRA_DATA);
        assert allResponseBerita != null;
        getSupportActionBar().setTitle(allResponseBerita.getTm_berita_judul());
        id = allResponseBerita.getTm_berita_id();
        date = allResponseBerita.getCreated_at();
        isi = allResponseBerita.getTm_berita_isi();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tv_isi.setText(Html.fromHtml(isi, Html.FROM_HTML_MODE_LEGACY));
        } else {
            tv_isi.setText(isi);
        }
        tv_judul.setText(allResponseBerita.getTm_berita_judul());
        tv_tag.setText(allResponseBerita.getTm_berita_tag());

        String dates = date;
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("MMMM dd,yyyy hh:mm:ss aa");
        try {
            oneWayTripDate = input.parse(dates);  // parse input
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        tv_date.setText(output.format(oneWayTripDate));

        if (oneWayTripDate == null) {
            tv_date.setText("_");
        } else {
            assert oneWayTripDate != null;
            tv_date.setText(output.format(oneWayTripDate));
        }

        Glide.with(getApplicationContext())
                .load(koneksi.BASE_URL_API
                        + "storage/images/berita/"
                        + allResponseBerita.getTm_berita_cover_nama())
                .apply(bitmapTransform(new VignetteFilterTransformation(new PointF(0.5f, 0.5f),
                        new float[]{0.0f, 0.0f, 0.0f}, 0.0f, 0.75f)).dontAnimate())
                .into(img);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        adapterFotoBeritaDetail = new AdapterFotoBeritaDetail(this, allFotoBeritaDetailList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        getFotoBerita_detail();

        view_bg_berita_detail.setBackgroundColor(getColorWithAlpha(Color.BLACK, 0.3f));
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

    private void getFotoBerita_detail(){
        loading = ProgressDialog.show(DetailBeritaActivity.this, null, "Memuat...",
                true, false);

        mApiService.getFotoBerita(id).enqueue(new Callback<ResponseFotoBeritaDetail>() {
            @Override
            public void onResponse(Call<ResponseFotoBeritaDetail> call, Response<ResponseFotoBeritaDetail> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    Boolean error = response.body().isError();
                    allFotoBeritaDetailList = response.body().getAllfotoberita();

                    if (error.equals(true)) {
                        Toast.makeText(mContext, "Tidak ada foto ilustrasi!", Toast.LENGTH_SHORT).show();
//                        String msg = "Tidak ada data foto!";
//                        onNothingData(msg);
                    } else {
                        if (allFotoBeritaDetailList == null) {
                            Toast.makeText(mContext, "Tidak ada foto ilustrasi!", Toast.LENGTH_SHORT).show();
//                            String msg = "Kesalahan Data Foto!";
//                            onNullData(msg);
                        } else {
                            recyclerView.setAdapter(new AdapterFotoBeritaDetail(mContext, allFotoBeritaDetailList));
                            adapterFotoBeritaDetail.notifyDataSetChanged();
                        }
                    }


                } else {
                    loading.dismiss();
                    String msg = "Gagal mengambil data Foto";
                    onErrorServer(msg);
                }
            }
            @Override
            public void onFailure(Call<ResponseFotoBeritaDetail> call, Throwable t) {
                loading.dismiss();
                onErrorConnection();
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
