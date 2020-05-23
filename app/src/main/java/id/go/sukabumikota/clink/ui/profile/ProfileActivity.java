package id.go.sukabumikota.clink.ui.profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import id.go.sukabumikota.clink.LoginActivity;
import id.go.sukabumikota.clink.MainActivity;
import id.go.sukabumikota.clink.R;
import id.go.sukabumikota.clink.api.BaseApiService;
import id.go.sukabumikota.clink.api.koneksi;
import jp.wasabeef.glide.transformations.gpu.VignetteFilterTransformation;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class ProfileActivity extends AppCompatActivity {

    ImageView img;
    TextView tv_nama, tv_username, tv_kecamatan, tv_kelurahan, tv_rw, tv_rt;
    EditText edt_nama, edt_username, edt_password, edt_newpassword, edt_repeatpassword;
    Button btn_ubah, btn_batal;
    CardView cv_editItem;
    FloatingActionButton fab1, fab2;

    String id, nama, username, password, id_kelurahan, nama_kelurahan,
            id_kecamatan, nama_kecamatan, id_rw, nama_rw, id_rt, nama_rt;

    ProgressDialog loading;
    Context mContext;
    BaseApiService mApiService;
    SharedPreferences sharedpreferences;

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

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tv_nama = findViewById(R.id.tv_nama_profile);
        tv_username = findViewById(R.id.tv_username_profile);
        edt_nama = findViewById(R.id.edt_nama_profile);
        edt_username = findViewById(R.id.edt_username_profile);
        edt_password = findViewById(R.id.edt_password_profile);
        edt_newpassword = findViewById(R.id.edt_newpassword_profile);
        edt_repeatpassword = findViewById(R.id.edt_repeatpassword_profile);
        tv_kecamatan = findViewById(R.id.tv_kecamatan_profile);
        tv_kelurahan = findViewById(R.id.tv_kelurahan_profile);
        tv_rw  = findViewById(R.id.tv_rw_profile);
        tv_rt = findViewById(R.id.tv_rt_profile);
        img = findViewById(R.id.img_profile);
        btn_ubah = findViewById(R.id.btn_ubah_profile);
        btn_batal = findViewById(R.id.btn_batal_profile);
        cv_editItem = findViewById(R.id.cv_editItem_profile);
        fab1 = findViewById(R.id.fab_profile);
        fab2 = findViewById(R.id.fab2_profile);

        mContext = this;
        mApiService = koneksi.getAPIService();
        sharedpreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);

        Toolbar toolbar = findViewById(R.id.toolbar_profile);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.menu_profile));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Glide.with(getApplicationContext())
                .load("https://www.sastra.edu/protocol/img/background.png")
                .apply(bitmapTransform(new VignetteFilterTransformation(new PointF(0.5f, 0.5f),
                        new float[]{0.0f, 0.0f, 0.0f}, 0.0f, 0.75f)).dontAnimate())
                .into(img);

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

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proccessItem();
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goneItem();
                fab1.setVisibility(View.VISIBLE);
            }
        });

        btn_batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goneItem();
                fab1.setVisibility(View.VISIBLE);
            }
        });

        btn_ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = edt_nama.getText().toString();
                String username = edt_username.getText().toString();
                String password = edt_password.getText().toString();
                String newpassword = edt_newpassword.getText().toString();
                String repeatpassword = edt_repeatpassword.getText().toString();

                if (nama.length() == 0) {
                    edt_nama.setError("Isi Nama");
                } else if (username.length() == 0) {
                    edt_username.setError("Isi Username");
                } else if (password.length() == 0) {
                    edt_password.setError("Isi Password");
                } else if (newpassword.length() == 0) {
                    edt_newpassword.setError("Isi Password Baru");
                } else if (repeatpassword.length() == 0) {
                    edt_repeatpassword.setError("Masukan ulang password baru");
                } else {
                    if (username.length() < 5) {
                        Toast.makeText(getApplicationContext(), "Username minimal 5 karakter!", Toast.LENGTH_SHORT).show();
                    } else if (newpassword.length() < 6) {
                        Toast.makeText(getApplicationContext(), "Password minimal 6 karakter!", Toast.LENGTH_SHORT).show();
                    }else {
                        if (newpassword.equals(repeatpassword)) {
                            dialogUpdate();
                        } else {
                            Toast.makeText(getApplicationContext(), "Password baru tidak sesuai!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        setItem();
        goneItem();
        disabledItem();

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

    private void setItem() {
        tv_nama.setText(nama);
        tv_username.setText(username);
        edt_nama.setText(nama);
        edt_username.setText(username);
        edt_password.setText(password);
        tv_kecamatan.setText(nama_kecamatan);
        tv_kelurahan.setText(nama_kelurahan);
        tv_rw.setText(nama_rw);
        tv_rt.setText(nama_rt);
    }

    private void proccessItem() {
        fab1.setVisibility(View.GONE);
        fab2.setVisibility(View.VISIBLE);
        cv_editItem.setVisibility(View.VISIBLE);
    }

    private void goneItem() {
        cv_editItem.setVisibility(View.GONE);
        fab2.setVisibility(View.GONE);
    }

    private void disabledItem() {
        edt_password.setEnabled(false);
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
                .setDescription("Pastikan koneksi internet anda berjalan dengan baik.")
                .setIcon(R.drawable.icon_warning_yellow)
                .setHeaderDrawable(R.drawable.bg_gradient_red)
                .withDialogAnimation(true)
                .withIconAnimation(true)
                .setCancelable(true)
                .setNegativeText("OK")
                .show();
    }

    private void onErrorServer(String message) {
        new MaterialStyledDialog.Builder(mContext)
                .setTitle(message)
                .setDescription("Gagal terhubung ke server.")
                .setIcon(R.drawable.icon_warning_red)
                .setHeaderDrawable(R.drawable.bg_gradient_orange)
                .withDialogAnimation(true)
                .withIconAnimation(true)
                .setCancelable(true)
                .setNegativeText("OK")
                .show();
    }

    private void onCheckUsername(String message) {
        new MaterialStyledDialog.Builder(mContext)
                .setTitle(message)
                .setDescription("Username sudah tersedia, Silahkan ganti.")
                .setIcon(R.drawable.icon_warning_red)
                .setHeaderDrawable(R.drawable.bg_gradient_orange)
                .withDialogAnimation(true)
                .withIconAnimation(true)
                .setCancelable(true)
                .setNegativeText("OK")
                .show();
    }

    private void onNothingData(String message) {
        new MaterialStyledDialog.Builder(mContext)
                .setTitle(message)
                .setDescription("Kesalahan.")
                .setIcon(R.drawable.icon_warning_red)
                .setHeaderDrawable(R.drawable.bg_gradient_orange)
                .withDialogAnimation(true)
                .withIconAnimation(true)
                .setCancelable(true)
                .setNegativeText("OK")
                .show();
    }

    private void dialogUpdate() {
        new MaterialStyledDialog.Builder(this)
                .setTitle("Ubah profile?")
                .setDescription("Nama : "+edt_nama.getText().toString() +
                        "\nUsername : "+edt_username.getText().toString() +
                        "\nPassword baru : "+edt_newpassword.getText().toString() +
                        "\n\nKlik Ya untuk mengubah.\n" +
                        "Anda akan otomatis kembali ke halaman Login!")
                .setIcon(R.drawable.icon_home)
                .setHeaderDrawable(R.drawable.bg_gradient_green_muda)
                .withDialogAnimation(true)
                .withIconAnimation(true)
                .setCancelable(false)
                .setNegativeText("Batal")
                .setPositiveText("Ya")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        loading = ProgressDialog.show(mContext, null,
                                "Harap Tunggu...", true, false);
                        checkUsernameProfile();
                    }
                })
                .show();
    }

    private void checkUsernameProfile(){
        mApiService.getCheckUsername(edt_username.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();

                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());

                                if (jsonRESULTS.getString("success").equals("1")){
                                    String msg = "Peringatan!";
                                    onCheckUsername(msg);
                                } else {
                                    loading = ProgressDialog.show(mContext, null,
                                            "Mengubah...", true, false);
                                    updateUserProfile();
                                }
                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                                String msg = "Peringatan!";
                                onNothingData(msg);
                            }
                        } else {
                            loading.dismiss();
                            String msg = "Peringatan";
                            onErrorServer(msg);
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        loading.dismiss();
                        onErrorConnection();
                    }
                });
    }

    private void updateUserProfile(){
        mApiService.getUpdateUserProfile(id, edt_nama.getText().toString(),
                edt_username.getText().toString(), edt_newpassword.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();

                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());

                                if (jsonRESULTS.getString("success").equals("1")){
                                    Toast.makeText(mContext, "Data berhasil di ubah!",
                                            Toast.LENGTH_SHORT).show();

                                    SharedPreferences.Editor editor = sharedpreferences.edit();
                                    editor.putBoolean(LoginActivity.session_status, false);
                                    editor.putString(TAG_ID, null);
                                    editor.putString(TAG_NAMA, null);
                                    editor.putString(TAG_USERNAME, null);
                                    editor.putString(TAG_PASSWORD, null);
                                    editor.putString(TAG_ID_KELURAHAN, null);
                                    editor.putString(TAG_NAMA_KELURAHAN, null);
                                    editor.putString(TAG_ID_KECAMATAN, null);
                                    editor.putString(TAG_NAMA_KECAMATAN, null);
                                    editor.putString(TAG_ID_RW, null);
                                    editor.putString(TAG_NAMA_RW, null);
                                    editor.putString(TAG_ID_RT, null);
                                    editor.putString(TAG_NAMA_RT, null);
                                    editor.apply();

                                    Intent backToLoginPage = new Intent(getApplicationContext(), LoginActivity.class);
                                    backToLoginPage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(backToLoginPage);
                                    ProfileActivity.this.finish();
                                } else {
                                    String msg = "Data Gagal Diubah!";
                                    onNothingData(msg);
                                }
                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                                String msg = "Peringatan!";
                                onNothingData(msg);
                            }
                        } else {
                            loading.dismiss();
                            String msg = "Peringatan";
                            onErrorServer(msg);
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        loading.dismiss();
                        onErrorConnection();
                    }
                });
    }
}
