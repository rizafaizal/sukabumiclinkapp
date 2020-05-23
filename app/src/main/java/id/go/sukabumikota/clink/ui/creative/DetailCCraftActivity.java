package id.go.sukabumikota.clink.ui.creative;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.google.android.material.snackbar.Snackbar;
import id.go.sukabumikota.clink.R;
import id.go.sukabumikota.clink.adapter.ccraft.AdapterFotoCCraftDetail;
import id.go.sukabumikota.clink.api.BaseApiService;
import id.go.sukabumikota.clink.api.koneksi;
import id.go.sukabumikota.clink.model.ccraft.AllResponseCCraft;
import id.go.sukabumikota.clink.model.ccraft.fotoccraftdetail.AllFotoCCraftDetail;
import id.go.sukabumikota.clink.model.ccraft.fotoccraftdetail.ResponseFotoCCRaftDetail;

import java.util.ArrayList;
import java.util.List;

import id.go.sukabumikota.clink.ui.forimages.ImagesActivity;
import jp.wasabeef.glide.transformations.gpu.VignetteFilterTransformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class DetailCCraftActivity extends AppCompatActivity {

    ImageView img;
    TextView tv_jenis, tv_dibuatoleh, tv_penjawab, tv_alamat, tv_telp, tv_email, tv_keterangan,
            tv_rt, tv_rw;
    LinearLayout view_bg_ccraft_detail;
    CardView cv_ccraft_fotodetails;

    String id_kelola_sampah, no_telpon, latitude, longitude, keterangan;

    ProgressDialog loading;

    Context mContext;
    List<AllFotoCCraftDetail> allFotoCCraftDetailList = new ArrayList<>();
    AdapterFotoCCraftDetail adapterFotoCCraftDetail;
    BaseApiService mApiService;
    private RecyclerView recyclerView;

    public static final String EXTRA_DATA= "extra_data";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ccraft);

        tv_jenis = findViewById(R.id.tv_jenis_ccraft_detail);
        tv_dibuatoleh = findViewById(R.id.tv_dibuatoleh_ccraft_detail);
        tv_penjawab = findViewById(R.id.tv_penjawab_ccraft_detail);
        tv_alamat = findViewById(R.id.tv_alamat_ccraft_detail);
        tv_rw = findViewById(R.id.tv_rws_ccraft_detail);
        tv_rt = findViewById(R.id.tv_rt_ccraft_detail);
        tv_telp = findViewById(R.id.tv_telp_ccraft_detail);
        tv_email = findViewById(R.id.tv_email_ccraft_detail);
        tv_keterangan = findViewById(R.id.tv_keterangan_ccraft_detail);
        img = findViewById(R.id.img_detailCCraft);
        recyclerView = findViewById(R.id.rv_foto_detail);
        view_bg_ccraft_detail = findViewById(R.id.view_bg_ccraft_detail);
        cv_ccraft_fotodetails = findViewById(R.id.cv_ccraft_fotodetails);

        mContext = this;
        mApiService = koneksi.getAPIService();

        Toolbar toolbar = findViewById(R.id.toolbar_detailCCraft);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        AllResponseCCraft allResponseCCraft = getIntent().getParcelableExtra(EXTRA_DATA);
        assert allResponseCCraft != null;
        getSupportActionBar().setTitle(allResponseCCraft.getTm_kelola_sampah_nama());
        id_kelola_sampah = allResponseCCraft.getTm_kelola_sampah_id();
        no_telpon = allResponseCCraft.getTm_kelola_sampah_telp();
        latitude = allResponseCCraft.getTm_kelola_sampah_latitude();
        longitude = allResponseCCraft.getTm_kelola_sampah_longitude();
        keterangan = allResponseCCraft.getTm_kelola_sampah_keterangan();

        tv_jenis.setText(allResponseCCraft.getTm_kategori_souvenir_nama());
        tv_dibuatoleh.setText(allResponseCCraft.getTm_kelola_sampah_nama());
        tv_penjawab.setText(allResponseCCraft.getTm_kelola_sampah_penanggungjawab());
        tv_alamat.setText(allResponseCCraft.getTm_kelola_sampah_alamatlengkap());
        tv_rw.setText("RW : "+allResponseCCraft.getTm_kelola_sampah_rw());
        tv_rt.setText("RT : "+allResponseCCraft.getTm_kelola_sampah_rt());
        tv_telp.setText(allResponseCCraft.getTm_kelola_sampah_telp());
        tv_email.setText(allResponseCCraft.getTm_kelola_sampah_email());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tv_keterangan.setText(Html.fromHtml(keterangan, Html.FROM_HTML_MODE_LEGACY));
        } else {
            tv_keterangan.setText(keterangan);
        }

        Glide.with(getApplicationContext())
                .load(koneksi.BASE_URL_API
                        + "storage/images/creativecraft/"
                        + allResponseCCraft.getTm_kelola_sampah_cover_nama())
                .apply(bitmapTransform(new VignetteFilterTransformation(new PointF(0.5f, 0.5f),
                        new float[]{0.0f, 0.0f, 0.0f}, 0.0f, 0.75f)).dontAnimate())
                .into(img);

//        tv_alamat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                PopupMenu dropDownMenu = new PopupMenu(getApplicationContext(), tv_alamat);
//                dropDownMenu.getMenuInflater().inflate(R.menu.menu_ccraftdetail_alamat, dropDownMenu.getMenu());
//                dropDownMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//
//                    @Override
//                    public boolean onMenuItemClick(MenuItem menuItem) {
////                        Uri location = Uri.parse("geo:106.8746102,-6.9077593?z=14"); // z param is zoom level
////                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
////                        startActivity(mapIntent);
//                        return true;
//                    }
//                });
//                dropDownMenu.show();
//            }
//        });

        tv_telp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu dropDownMenu = new PopupMenu(getApplicationContext(), tv_telp);
                dropDownMenu.getMenuInflater().inflate(R.menu.menu_ccraftdetail_telp, dropDownMenu.getMenu());
                dropDownMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        String phoneNumber = no_telpon;
                        Intent dialPhoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNumber));
                        startActivity(dialPhoneIntent);
                        return true;
                    }
                });
                dropDownMenu.show();
            }
        });

        tv_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu dropDownMenu = new PopupMenu(getApplicationContext(), tv_email);
                dropDownMenu.getMenuInflater().inflate(R.menu.menu_ccraftdetail_email, dropDownMenu.getMenu());
                dropDownMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        String emailcopy = tv_email.getText().toString();
                        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        if (clipboardManager != null) {
                            clipboardManager.setText(emailcopy);
                        }

                        Snackbar.make(findViewById(R.id.tv_email_ccraft_detail), "Email: "+emailcopy+", sudah di Copy", Snackbar.LENGTH_SHORT).show();
                        return true;
                    }
                });
                dropDownMenu.show();
            }
        });

        adapterFotoCCraftDetail = new AdapterFotoCCraftDetail(this, allFotoCCraftDetailList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        getFotoCCraft_detail();

        view_bg_ccraft_detail.setBackgroundColor(getColorWithAlpha(Color.BLACK, 0.3f));

        cv_ccraft_fotodetails.setBackgroundColor(getColorWithAlpha(Color.BLACK, 0.5f));
        cv_ccraft_fotodetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String activity = "ccraft";

                Intent moveToDetail = new Intent(mContext, ImagesActivity.class);
                moveToDetail.putExtra(ImagesActivity.EXTRA_DATA, id_kelola_sampah);
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
        cv_ccraft_fotodetails.setVisibility(View.GONE);
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

    private void getFotoCCraft_detail(){
        loading = ProgressDialog.show(DetailCCraftActivity.this, null, "Memuat...",
                true, false);

        mApiService.getFotoCreative(id_kelola_sampah).enqueue(new Callback<ResponseFotoCCRaftDetail>() {
            @Override
            public void onResponse(Call<ResponseFotoCCRaftDetail> call, Response<ResponseFotoCCRaftDetail> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    Boolean error = response.body().isError();
                    allFotoCCraftDetailList = response.body().getAllfotocreativecraft();

                    if (error.equals(true)) {
                        Toast.makeText(mContext, "Tidak ada foto ilustrasi!", Toast.LENGTH_SHORT).show();
//                        String msg = "Tidak ada data Foto!";
//                        onNothingData(msg);
                        goneItem();
                    } else {
                        if (allFotoCCraftDetailList == null) {
                            Toast.makeText(mContext, "Tidak ada foto ilustrasi!", Toast.LENGTH_SHORT).show();
//                            String msg = "Kesalahan Data Foto!";
//                            onNullData(msg);
                            goneItem();
                        } else {
                            recyclerView.setAdapter(new AdapterFotoCCraftDetail(mContext, allFotoCCraftDetailList));
                            adapterFotoCCraftDetail.notifyDataSetChanged();
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
            public void onFailure(Call<ResponseFotoCCRaftDetail> call, Throwable t) {
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
