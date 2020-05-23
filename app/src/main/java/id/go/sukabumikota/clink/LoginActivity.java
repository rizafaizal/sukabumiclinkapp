package id.go.sukabumikota.clink;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

import id.go.sukabumikota.clink.R;
import id.go.sukabumikota.clink.api.BaseApiService;
import id.go.sukabumikota.clink.api.koneksi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText edt_usrnm, edt_pwd;
    Button btn_sbmt, btn_sbmt_guest;
    ImageView img_close, img_logo;

    ProgressDialog loading;
    Context mContext;
    BaseApiService mApiService;

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

    SharedPreferences sharedpreferences;
    Boolean session = false;
    String id, nama, username, password, id_kelurahan, nama_kelurahan,
            id_kecamatan, nama_kecamatan, id_rw, nama_rw, id_rt, nama_rt;
    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inisialisasi_widget();

        mContext = this;
        mApiService = koneksi.getAPIService();

        cekSession();

        btn_sbmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usrnm = edt_usrnm.getText().toString().trim();
                String pwd = edt_pwd.getText().toString().trim();

                if (TextUtils.isEmpty(usrnm)) {
                    edt_usrnm.setError("Tidak boleh kosong");
                } else if (TextUtils.isEmpty(pwd)) {
                    edt_pwd.setError("Tidak boleh kosong");
                } else {
                    loading = ProgressDialog.show(mContext, null,
                            "Harap Tunggu...", true, false);
                    requestLogin();
                }
            }
        });

        btn_sbmt_guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean(session_status, true);
                editor.apply();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Glide.with(getApplicationContext())
                .load(R.drawable.logo_sukabumi_clink)
                .fitCenter()
                .into(img_logo);

        welcomeDialog();
    }

    private void inisialisasi_widget() {
        edt_usrnm = findViewById(R.id.edt_username_login);
        edt_pwd = findViewById(R.id.edt_password_login);
        btn_sbmt = findViewById(R.id.btn_submit_login);
        btn_sbmt_guest = findViewById(R.id.btn_submit_login_guest);
        img_close = findViewById(R.id.img_close);
        img_logo = findViewById(R.id.img_logo_login);
    }

    public void cekSession() {
        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        id = sharedpreferences.getString(TAG_ID, null);
        nama = sharedpreferences.getString(TAG_NAMA, null);
        username = sharedpreferences.getString(TAG_USERNAME, null);
        password = sharedpreferences.getString(TAG_PASSWORD, null);
        id_kelurahan = sharedpreferences.getString(TAG_ID_KELURAHAN, null);
        nama_kelurahan = sharedpreferences.getString(TAG_NAMA_KELURAHAN, null);
        id_kecamatan = sharedpreferences.getString(TAG_ID_KECAMATAN, null);
        nama_kecamatan = sharedpreferences.getString(TAG_NAMA_KECAMATAN, null);
        id_rw = sharedpreferences.getString(TAG_ID_RW, null);
        nama_rw = sharedpreferences.getString(TAG_NAMA_RW, null);
        id_rt = sharedpreferences.getString(TAG_ID_RT, null);
        nama_rt = sharedpreferences.getString(TAG_NAMA_RT, null);

        if (session) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra(TAG_ID, id);
            intent.putExtra(TAG_NAMA, nama);
            intent.putExtra(TAG_USERNAME, username);
            intent.putExtra(TAG_PASSWORD, password);
            intent.putExtra(TAG_ID_KELURAHAN, id_kelurahan);
            intent.putExtra(TAG_NAMA_KELURAHAN, nama_kelurahan);
            intent.putExtra(TAG_ID_KECAMATAN, id_kecamatan);
            intent.putExtra(TAG_NAMA_KECAMATAN, nama_kecamatan);
            intent.putExtra(TAG_ID_RW, id_rw);
            intent.putExtra(TAG_NAMA_RW, nama_rw);
            intent.putExtra(TAG_ID_RT, id_rt);
            intent.putExtra(TAG_NAMA_RT, nama_rt);
            startActivity(intent);
            finish();
        }

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
                        "Klik Refresh untuk memulai kembali!")
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
                .show();
    }

    private void onErrorServer(String message) {
        new MaterialStyledDialog.Builder(mContext)
                .setTitle(message)
                .setDescription("Gagal terhubung ke server.\n" +
                        "Klik Refresh untuk memulai kembali!")
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
                .show();
    }

    private void onNothingData(String message) {
        new MaterialStyledDialog.Builder(mContext)
                .setTitle(message)
                .setDescription("Pastikan Username atau Password sudah benar.")
                .setIcon(R.drawable.icon_warning_red)
                .setHeaderDrawable(R.drawable.bg_gradient_orange)
                .withDialogAnimation(true)
                .withIconAnimation(true)
                .setCancelable(false)
                .setNegativeText("OK")
                .show();
    }

    private void requestLogin(){
        mApiService.login(edt_usrnm.getText().toString(), edt_pwd.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();

                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                JSONArray jsonArray = jsonRESULTS.getJSONArray("logindata");

                                if (jsonRESULTS.getString("success").equals("1")){
                                    Toast.makeText(mContext, "Login berhasil!",
                                            Toast.LENGTH_SHORT).show();

                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jObj = jsonArray.getJSONObject(i);

                                        String id = jObj.getString(TAG_ID);
                                        String nama = jObj.getString(TAG_NAMA);
                                        String username = jObj.getString(TAG_USERNAME);
                                        String password = jObj.getString(TAG_PASSWORD);
                                        String id_kelurahan = jObj.getString(TAG_ID_KELURAHAN);
                                        String nama_kelurahan = jObj.getString(TAG_NAMA_KELURAHAN);
                                        String id_kecamatan = jObj.getString(TAG_ID_KECAMATAN);
                                        String nama_kecamatan = jObj.getString(TAG_NAMA_KECAMATAN);
                                        String id_rw = jObj.getString(TAG_ID_RW);
                                        String nama_rw = jObj.getString(TAG_NAMA_RW);
                                        String id_rt = jObj.getString(TAG_ID_RT);
                                        String nama_rt = jObj.getString(TAG_NAMA_RT);

                                        SharedPreferences.Editor editor = sharedpreferences.edit();
                                        editor.putBoolean(session_status, true);
                                        editor.putString(TAG_ID, id);
                                        editor.putString(TAG_NAMA, nama);
                                        editor.putString(TAG_USERNAME, username);
                                        editor.putString(TAG_PASSWORD, password);
                                        editor.putString(TAG_ID_KELURAHAN, id_kelurahan);
                                        editor.putString(TAG_NAMA_KELURAHAN, nama_kelurahan);
                                        editor.putString(TAG_ID_KECAMATAN, id_kecamatan);
                                        editor.putString(TAG_NAMA_KECAMATAN, nama_kecamatan);
                                        editor.putString(TAG_ID_RW, id_rw);
                                        editor.putString(TAG_NAMA_RW, nama_rw);
                                        editor.putString(TAG_ID_RT, id_rt);
                                        editor.putString(TAG_NAMA_RT, nama_rt);
                                        editor.apply();

                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        intent.putExtra(TAG_ID, id);
                                        intent.putExtra(TAG_NAMA, nama);
                                        intent.putExtra(TAG_USERNAME, username);
                                        intent.putExtra(TAG_PASSWORD, password);
                                        intent.putExtra(TAG_ID_KELURAHAN, id_kelurahan);
                                        intent.putExtra(TAG_NAMA_KELURAHAN, nama_kelurahan);
                                        intent.putExtra(TAG_ID_KECAMATAN, id_kecamatan);
                                        intent.putExtra(TAG_NAMA_KECAMATAN, nama_kecamatan);
                                        intent.putExtra(TAG_ID_RW, id_rw);
                                        intent.putExtra(TAG_NAMA_RW, nama_rw);
                                        intent.putExtra(TAG_ID_RT, id_rt);
                                        intent.putExtra(TAG_NAMA_RT, nama_rt);
                                        startActivity(intent);
                                        finish();
                                    }
                                } else {
                                    String msg = "Username atau Password salah";
                                    onNothingData(msg);
                                }
                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                                String msg = "Terjadi kesalahan pada server";
                                onErrorServer(msg);
                            }
                        } else {
                            loading.dismiss();
                            String msg = "Gagal";
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

    private void welcomeDialog() {
        new MaterialStyledDialog.Builder(this)
                .setTitle("Selamat Datang!")
                .setDescription("Di aplikasi Sukabumi Clink.\nAplikasi informasi sampah di Kota Sukabumi.")
                .setIcon(R.drawable.icon_home)
                .setHeaderDrawable(R.drawable.bg_gradient_green_muda)
                .withDialogAnimation(true)
                .withIconAnimation(true)
                .setCancelable(false)
                .setNegativeText("Lanjutkan")
//                .withDarkerOverlay(true)
//                .setHeaderColor(R.color.colorPrimary)
//                .setPositiveText("Referensi")
//                .onPositive(new MaterialDialog.SingleButtonCallback() {
//                    @Override
//                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/rizafaizal")));
//                    }
//                })
                .show();
    }
}
