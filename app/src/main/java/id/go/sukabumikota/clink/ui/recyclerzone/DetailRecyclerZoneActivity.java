package id.go.sukabumikota.clink.ui.recyclerzone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

import id.go.sukabumikota.clink.MainActivity;
import id.go.sukabumikota.clink.R;

import id.go.sukabumikota.clink.adapter.rczone.AdapterFotoRcDetail;
import id.go.sukabumikota.clink.api.BaseApiService;
import id.go.sukabumikota.clink.api.koneksi;
import id.go.sukabumikota.clink.model.rczone.fotorczonedetail.AllFotoRcDetail;
import id.go.sukabumikota.clink.model.rczone.fotorczonedetail.ResponseFotoRcDetail;
import id.go.sukabumikota.clink.model.rczone.AllResponseRecyclerZone;

import java.util.ArrayList;
import java.util.List;

import id.go.sukabumikota.clink.ui.forimages.ImagesActivity;
import jp.wasabeef.glide.transformations.gpu.VignetteFilterTransformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class DetailRecyclerZoneActivity extends AppCompatActivity {

    ImageView img;
    TextView tv_isi, tv_nama, tv_jenis;
    LinearLayout view_bg_rczone_detail;
    CardView cv_rczone_fotodetails;

    String id, isi;

    ProgressDialog loading;

    Context mContext;
    List<AllFotoRcDetail> allFotoRcDetailList = new ArrayList<>();
    AdapterFotoRcDetail adapterFotoRcDetail;
    BaseApiService mApiService;
    private RecyclerView recyclerView;

    public static final String EXTRA_DATA= "extra_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_recycler_zone);

        tv_isi = findViewById(R.id.tv_isi_recyclerzone_detail);
        tv_nama = findViewById(R.id.tv_nama_recyclerzone_detail);
        tv_jenis = findViewById(R.id.tv_jenis_recyclerzone_detail);
        img = findViewById(R.id.img_detailRecyclerZone);
        recyclerView = findViewById(R.id.rv_foto_detail);
        view_bg_rczone_detail = findViewById(R.id.view_bg_rczone_detail);
        cv_rczone_fotodetails = findViewById(R.id.cv_rczone_fotodetails);

        mContext = this;
        mApiService = koneksi.getAPIService();

        Toolbar toolbar = findViewById(R.id.toolbar_detailRecyclerZone);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        AllResponseRecyclerZone allResponseRecyclerZone = getIntent().getParcelableExtra(EXTRA_DATA);
        assert allResponseRecyclerZone != null;
        getSupportActionBar().setTitle(allResponseRecyclerZone.getTm_kategori_subolah_nama());
        id = allResponseRecyclerZone.getTm_kategori_subolah_id();
        isi = allResponseRecyclerZone.getTm_kategori_subolah_isi();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tv_isi.setText(Html.fromHtml(isi, Html.FROM_HTML_MODE_LEGACY));
        } else {
            tv_isi.setText(isi);
        }

        tv_nama.setText(allResponseRecyclerZone.getTm_kategori_subolah_nama());
        tv_jenis.setText(allResponseRecyclerZone.getTm_kategori_subolah_jenis());

        Glide.with(getApplicationContext())
                .load(koneksi.BASE_URL_API
                        + "storage/images/recyclerzone/"
                        + allResponseRecyclerZone.getTm_kategori_subolah_cover_nama())
                .apply(bitmapTransform(new VignetteFilterTransformation(new PointF(0.5f, 0.5f),
                        new float[]{0.0f, 0.0f, 0.0f}, 0.0f, 0.75f)).dontAnimate())
                .into(img);

        adapterFotoRcDetail = new AdapterFotoRcDetail(this, allFotoRcDetailList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        getFotoRc_detail();

        view_bg_rczone_detail.setBackgroundColor(getColorWithAlpha(Color.BLACK, 0.3f));

//        CarouselView carouselView = findViewById(R.id.carousel_rczone_detail);
//        final int[] sampleImages = {R.drawable.icon_close, R.drawable.icon_creative,
//                R.drawable.icon_education, R.drawable.icon_home, R.drawable.icon_profile};
//        carouselView.setPageCount(sampleImages.length);
//        carouselView.setImageListener(new ImageListener() {
//            @Override
//            public void setImageForPosition(int position, ImageView imageView) {
//                imageView.setImageResource(sampleImages[position]);
//            }
//        });

        cv_rczone_fotodetails.setBackgroundColor(getColorWithAlpha(Color.BLACK, 0.5f));
        cv_rczone_fotodetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String activity = "rczone";

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
        cv_rczone_fotodetails.setVisibility(View.GONE);
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
        loading = ProgressDialog.show(DetailRecyclerZoneActivity.this, null, "Memuat...",
                true, false);

        mApiService.getFotoRecycler(id).enqueue(new Callback<ResponseFotoRcDetail>() {
            @Override
            public void onResponse(Call<ResponseFotoRcDetail> call, Response<ResponseFotoRcDetail> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    Boolean error = response.body().isError();
                    allFotoRcDetailList = response.body().getAllFotoRcDetailList();

                    if (error.equals(true)) {
                        Toast.makeText(mContext, "Tidak ada foto ilustrasi!", Toast.LENGTH_SHORT).show();
//                        String msg = "Tidak ada data Foto!";
//                        onNothingData(msg);
                        goneItem();
                    } else {
                        if (allFotoRcDetailList == null) {
                            Toast.makeText(mContext, "Tidak ada foto ilustrasi!", Toast.LENGTH_SHORT).show();
//                            String msg = "Kesalahan Data Foto!";
//                            onNullData(msg);
                            goneItem();
                        } else {
                            recyclerView.setAdapter(new AdapterFotoRcDetail(mContext, allFotoRcDetailList));
                            adapterFotoRcDetail.notifyDataSetChanged();
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
            public void onFailure(Call<ResponseFotoRcDetail> call, Throwable t) {
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
