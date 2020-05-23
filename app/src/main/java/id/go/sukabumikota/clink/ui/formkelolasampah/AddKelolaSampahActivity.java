package id.go.sukabumikota.clink.ui.formkelolasampah;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import id.go.sukabumikota.clink.R;
import id.go.sukabumikota.clink.api.BaseApiService;
import id.go.sukabumikota.clink.api.koneksi;
import id.go.sukabumikota.clink.model.kelola.AllResponseSpnrSouvenir;
import id.go.sukabumikota.clink.model.kelola.ResponseSpnrSouvenir;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class AddKelolaSampahActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageView img_add1, img_add2, img_add3;
    Button btn_back, btn_submit, btn_latlon;
    Spinner spnr_jenissouvenir;
    TextView tv_add_namakelurahan;
    EditText edt_namaorganisasi, edt_keterangan, edt_penjawab, edt_alamat, edt_telp, edt_email,
        edt_rw, edt_rt, edt_idkelurahan, edt_id_souvenir, edt_lat, edt_lon, edt_useradd;
    ProgressDialog loading;

    Context mContext;
    BaseApiService mApiService;

    List<AllResponseSpnrSouvenir> allResponseSpnrSouvenirList;

    String id, nama, username, password, id_kelurahan, nama_kelurahan,
            id_kecamatan, nama_kecamatan, id_rw, nama_rw, id_rt, nama_rt;

    private final int code = 100, code2 = 200, code3 = 300;
    private final int GALLERY = 1;
    private final int CAMERA = 2;
    private final int GALLERY2 = 3;
    private final int CAMERA2 = 4;
    private final int GALLERY3 = 5;
    private final int CAMERA3 = 6;

    Bitmap bitmap1, bitmap2, bitmap3;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_kelola_sampah);

        inisialisasi();
        requestPermissions();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.menu_addkelolasampah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

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

        mContext = this;
        mApiService = koneksi.getAPIService();
        callSpnrSouvenir();
        getSelectedItemSpnrSouvenir();
        setItemToEdiitext();
        disabledItem();

        img_add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihDialog(code);
            }
        });

        img_add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihDialog(code2);
            }
        });

        img_add3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihDialog(code3);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_latlon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLatLon();
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namaorganisasi = edt_namaorganisasi.getText().toString();
                String keterangan = edt_keterangan.getText().toString();
                String penjawab = edt_penjawab.getText().toString();
                String alamat = edt_alamat.getText().toString();
                String telpon = edt_telp.getText().toString();
                String email = edt_email.getText().toString();
                String rw = edt_rw.getText().toString();
                String rt = edt_rt.getText().toString();
                String idkelurahan = edt_idkelurahan.getText().toString();
                String idsouvenir = edt_id_souvenir.getText().toString();
                String lat = edt_lat.getText().toString();
                String lon = edt_lon.getText().toString();
                String useradd = edt_useradd.getText().toString();

                if (namaorganisasi.length() == 0) {
                    edt_namaorganisasi.setError("Isi Nama Organisasi");
                } else if (keterangan.length() == 0) {
                    edt_keterangan.setError("Isi Keterangan Produk");
                } else if (penjawab.length() == 0) {
                    edt_penjawab.setError("Isi Nama Penanggung Jawab");
                } else if (alamat.length() == 0) {
                    edt_alamat.setError("Isi Alamat");
                } else if (telpon.length() == 0) {
                    edt_telp.setError("Isi Nomor Telepon");
                } else if (email.length() == 0) {
                    edt_email.setError("Isi Email");
                } else if (rw.length() == 0) {
                    edt_rw.setError("Isi RW");
                } else if (rt.length() == 0) {
                    edt_rt.setError("Isi RT");
                } else if (idkelurahan.length() == 0) {
                    edt_idkelurahan.setError("Tidak boleh kosong");
                } else if (idsouvenir.length() == 0) {
                    edt_id_souvenir.setError("Tidak boleh kosong");
                } else if (lat.length() == 0) {
                    edt_lat.setError("Tidak boleh kosong");
                } else if (lon.length() == 0) {
                    edt_keterangan.setError("Tidak boleh kosong");
                } else if (useradd.length() == 0) {
                    edt_useradd.setError("Tidak boleh kosong");
                } else if (img_add1.getDrawable() == null) {
                    Toast.makeText(AddKelolaSampahActivity.this, "Masukan Foto 1!", Toast.LENGTH_SHORT).show();
                } else if (img_add2.getDrawable() == null) {
                    Toast.makeText(AddKelolaSampahActivity.this, "Masukan Foto 2!", Toast.LENGTH_SHORT).show();
                } else if (img_add3.getDrawable() == null) {
                    Toast.makeText(AddKelolaSampahActivity.this, "Masukan Foto 3!", Toast.LENGTH_SHORT).show();
                } else {
                    dialogSubmit();
                }
            }
        });
    }

    private void inisialisasi() {
        toolbar = findViewById(R.id.toolbar_add_kelola);
        img_add1 = findViewById(R.id.img_1_add_kelola);
        img_add2 = findViewById(R.id.img_2_add_kelola);
        img_add3 = findViewById(R.id.img_3_add_kelola);
        btn_back = findViewById(R.id.btn_add_back_kelola);
        btn_submit = findViewById(R.id.btn_add_submit_kelola);
        btn_latlon = findViewById(R.id.btn_add_latlon_kelola);
        spnr_jenissouvenir = findViewById(R.id.spnr_jenissouvenir_kelola);
        edt_namaorganisasi = findViewById(R.id.edt_add_namaorganisasi_kelola);
        edt_keterangan = findViewById(R.id.edt_add_keterangan_kelola);
        edt_penjawab = findViewById(R.id.edt_add_penjawab_kelola);
        edt_alamat = findViewById(R.id.edt_add_alamat_kelola);
        edt_telp = findViewById(R.id.edt_add_telpon_kelola);
        edt_email = findViewById(R.id.edt_add_email_kelola);
        edt_rw = findViewById(R.id.edt_add_rw_kelola);
        edt_rt = findViewById(R.id.edt_add_rt_kelola);
        edt_idkelurahan = findViewById(R.id.edt_add_idkelurahan_kelola);
        edt_id_souvenir = findViewById(R.id.edt_add_idsouvenir_kelola);
        edt_lat = findViewById(R.id.edt_add_latitude_kelola);
        edt_lon = findViewById(R.id.edt_add_longitude_kelola);
        edt_useradd = findViewById(R.id.edt_add_useradd_kelola);
        tv_add_namakelurahan = findViewById(R.id.tv_add_namakelurahan_kelola);
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

    void setItemToEdiitext() {
        tv_add_namakelurahan.setText(nama_kelurahan);
        edt_idkelurahan.setText(id_kelurahan);
        edt_useradd.setText(nama);
    }

    void disabledItem() {
        edt_idkelurahan.setEnabled(false);
        edt_id_souvenir.setEnabled(false);
//        edt_lat.setEnabled(false);
//        edt_lon.setEnabled(false);
        edt_useradd.setEnabled(false);
    }

    void disabledifnopermission() {
        img_add1.setEnabled(false);
        img_add2.setEnabled(false);
        img_add3.setEnabled(false);
        btn_latlon.setEnabled(false);
        btn_submit.setEnabled(false);

    }

    void disabledifyespermission() {
        img_add1.setEnabled(true);
        img_add2.setEnabled(true);
        img_add3.setEnabled(true);
        btn_latlon.setEnabled(true);
        btn_submit.setEnabled(true);

    }

    private void dialogSubmit() {
        new MaterialStyledDialog.Builder(this)
                .setTitle("Tambah Kerajinan?")
                .setDescription("Kerajinan yang anda tambahkan akan diseleksi terlebih dahulu oleh admin, kemudian akan ditampilkan.\n\n" +
                        "Pilih Ya untuk menambahkan!")
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
                        submitCCraft2(bitmap1, bitmap2, bitmap3);
                    }
                })
                .show();
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

    void getSelectedItemSpnrSouvenir() {
        spnr_jenissouvenir.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String selectedName = parent.getItemAtPosition(position).toString();
//                Toast.makeText(mContext, "Pilih : "+selectedName, Toast.LENGTH_SHORT).show();
                String id_souvenir = allResponseSpnrSouvenirList.get(position).getTm_kategori_souvenir_id();
                edt_id_souvenir.setText(id_souvenir);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void callSpnrSouvenir() {
        loading = ProgressDialog.show(this, null, "Memuat...", true, false);

        mApiService.getListSpnrSouvenir().enqueue(new Callback<ResponseSpnrSouvenir>() {
            @Override
            public void onResponse(Call<ResponseSpnrSouvenir> call, Response<ResponseSpnrSouvenir> response) {
                if (response.isSuccessful()) {
                    loading.dismiss();
                    Boolean error = response.body().isError();

                    allResponseSpnrSouvenirList = response.body().getAllspnrsouvenir();
                    List<String> listSpinner = new ArrayList<String>();

                    if (error.equals(true)) {
                        String msg = "Tidak ada data Souvenir!";
                        onNothingData(msg);
                    } else {
                        if (allResponseSpnrSouvenirList == null) {
                            String msg = "Kesalahan Data Souvenir!";
                            onNullData(msg);
                        } else {
                            for (int i = 0; i < allResponseSpnrSouvenirList.size(); i++){
                                listSpinner.add(allResponseSpnrSouvenirList.get(i).getTm_kategori_souvenir_nama());
                            }
                            // Set hasil result json ke dalam adapter spinner
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                                    android.R.layout.simple_spinner_item, listSpinner);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spnr_jenissouvenir.setAdapter(adapter);
                        }
                    }


                } else {
                    loading.dismiss();
                    String msg = "Gagal mengambil data Souvenir";
                    onErrorServer(msg);
                }
            }

            @Override
            public void onFailure(Call<ResponseSpnrSouvenir> call, Throwable t) {
                loading.dismiss();
                onErrorConnection();
            }
        });
    }

    private void pilihDialog(int codePilih) {

        if (codePilih == 100) {
            new MaterialStyledDialog.Builder(this)
                    .setTitle("Buka gambar!")
                    .setDescription("Disarankan pilih dari Gallery untuk kualitas lebih baik.\n\nPilih :")
                    .setIcon(R.drawable.icon_home)
                    .setHeaderColor(R.color.colorPrimary)
                    .withDialogAnimation(true)
                    .withIconAnimation(true)
                    .setCancelable(true)
                    .setPositiveText("Gallery")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(galleryIntent, GALLERY);
                        }
                    })
                    .setNegativeText("Camera")
                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intentCamera, CAMERA);
                        }
                    })
                    .show();
        } else if (codePilih == 200) {
            new MaterialStyledDialog.Builder(this)
                    .setTitle("Buka gambar!")
                    .setDescription("Disarankan pilih dari Gallery untuk kualitas lebih baik.\n\nPilih :")
                    .setIcon(R.drawable.icon_home)
                    .setHeaderColor(R.color.colorPrimary)
                    .withDialogAnimation(true)
                    .withIconAnimation(true)
                    .setCancelable(true)
                    .setPositiveText("Gallery")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(galleryIntent, GALLERY2);
                        }
                    })
                    .setNegativeText("Camera")
                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intentCamera, CAMERA2);
                        }
                    })
                    .show();
        } else if (codePilih == 300) {
            new MaterialStyledDialog.Builder(this)
                    .setTitle("Buka gambar!")
                    .setDescription("Disarankan pilih dari Gallery untuk kualitas lebih baik.\n\nPilih :")
                    .setIcon(R.drawable.icon_home)
                    .setHeaderColor(R.color.colorPrimary)
                    .withDialogAnimation(true)
                    .withIconAnimation(true)
                    .setCancelable(true)
                    .setPositiveText("Gallery")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(galleryIntent, GALLERY3);
                        }
                    })
                    .setNegativeText("Camera")
                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intentCamera, CAMERA3);
                        }
                    })
                    .show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {

                    bitmap1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    img_add1.setImageBitmap(bitmap1);


                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(AddKelolaSampahActivity.this, "Gagal!", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (requestCode == CAMERA) {
            if (data != null) {
                bitmap1 = (Bitmap) data.getExtras().get("data");
                img_add1.setImageBitmap(bitmap1);
            }
        } else if (requestCode == GALLERY2) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {

                    bitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    img_add2.setImageBitmap(bitmap2);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(AddKelolaSampahActivity.this, "Gagal!", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (requestCode == CAMERA2) {
            if (data != null) {
                bitmap2 = (Bitmap) data.getExtras().get("data");
                img_add2.setImageBitmap(bitmap2);
            }
        } else if (requestCode == GALLERY3) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {

                    bitmap3 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    img_add3.setImageBitmap(bitmap3);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(AddKelolaSampahActivity.this, "Gagal!", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (requestCode == CAMERA3) {
            if (data != null) {
                bitmap3 = (Bitmap) data.getExtras().get("data");
                img_add3.setImageBitmap(bitmap3);
            }
        }
    }

    private void  requestPermissions(){
        Dexter.withActivity(this)
                .withPermissions(

                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(getApplicationContext(), "Semua permission disetujui!", Toast.LENGTH_SHORT).show();
                            disabledifyespermission();
                        } else {
                            Toast.makeText(getApplicationContext(), "Belum semua permission disetujui!", Toast.LENGTH_SHORT).show();
                            disabledifnopermission();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            Toast.makeText(getApplicationContext(), "Ditolak semua!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Sesuatu yang salah! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

    void getLatLon() {
        FusedLocationProviderClient mFusedLocation = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocation.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {

            @Override
            public void onSuccess(Location location) {
                if (location != null){
                    Log.d("Lokasi saya ", "Lat : " + location.getLatitude() + " Long : " + location.getLongitude());
                    edt_lat.setText(String.valueOf(location.getLatitude()));
                    edt_lon.setText(String.valueOf(location.getLongitude()));
                    String msg = "Lokasi ditemukan.\nLat: "+location.getLatitude()+" Lon: "+location.getLongitude();
                    Toast.makeText(AddKelolaSampahActivity.this,msg, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void submitCCraft(Bitmap bitmap1, Bitmap bitmap2, Bitmap bitmap3){
//        loading = ProgressDialog.show(this, null,
//                "Sedang memperoses...", true, false);

        String namaorganisasi = edt_namaorganisasi.getText().toString();
        String keterangan = edt_keterangan.getText().toString();
        String penjawab = edt_penjawab.getText().toString();
        String alamat = edt_alamat.getText().toString();
        String telpon = edt_telp.getText().toString();
        String email = edt_email.getText().toString();
        String lat = edt_lat.getText().toString();
        String lon = edt_lon.getText().toString();
        String rt = edt_rt.getText().toString();
        String rw = edt_rw.getText().toString();
        String idkelurahan = edt_idkelurahan.getText().toString();
        String idsouvenir = edt_id_souvenir.getText().toString();
        String useradd = edt_useradd.getText().toString();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        String encodedImage1 = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
        String imgname1 = String.valueOf(Calendar.getInstance().getTimeInMillis());

        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        bitmap2.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream2);
        String encodedImage2 = Base64.encodeToString(byteArrayOutputStream2.toByteArray(), Base64.DEFAULT);
        String imgname2 = String.valueOf(Calendar.getInstance().getTimeInMillis()+1);

        ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
        bitmap3.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream3);
        String encodedImage3 = Base64.encodeToString(byteArrayOutputStream3.toByteArray(), Base64.DEFAULT);
        String imgname3 = String.valueOf(Calendar.getInstance().getTimeInMillis()+2);

        //Resize image bitmap
        Bitmap bitmap1_245;
        bitmap1_245 = bitmap1;
        float aspectRatio = bitmap1_245.getWidth() /
                (float) bitmap1_245.getHeight();
        int width = 245;
        int height = Math.round(width / aspectRatio);
        Bitmap resized_bitmap1 = Bitmap.createScaledBitmap(bitmap1_245, width, height, false);
        ByteArrayOutputStream byteArrayOutputStream_resize_bitmap1 = new ByteArrayOutputStream();
        resized_bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream_resize_bitmap1);
        String encodedImage_resize_1 = Base64.encodeToString(byteArrayOutputStream_resize_bitmap1.toByteArray(), Base64.DEFAULT);

        Bitmap bitmap2_245;
        bitmap2_245 = bitmap2;
        float aspectRatio2 = bitmap2_245.getWidth() /
                (float) bitmap2_245.getHeight();
        int width2 = 245;
        int height2 = Math.round(width2 / aspectRatio2);
        Bitmap resized_bitmap2 = Bitmap.createScaledBitmap(bitmap2_245, width2, height2, false);
        ByteArrayOutputStream byteArrayOutputStream_resize_bitmap2 = new ByteArrayOutputStream();
        resized_bitmap2.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream_resize_bitmap2);
        String encodedImage_resize_2 = Base64.encodeToString(byteArrayOutputStream_resize_bitmap2.toByteArray(), Base64.DEFAULT);

        Bitmap bitmap3_245;
        bitmap3_245 = bitmap3;
        float aspectRatio3 = bitmap3_245.getWidth() /
                (float) bitmap3_245.getHeight();
        int width3 = 245;
        int height3 = Math.round(width3 / aspectRatio3);
        Bitmap resized_bitmap3 = Bitmap.createScaledBitmap(bitmap3_245, width3, height3, false);
        ByteArrayOutputStream byteArrayOutputStream_resize_bitmap3 = new ByteArrayOutputStream();
        resized_bitmap3.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream_resize_bitmap3);
        String encodedImage_resize_3 = Base64.encodeToString(byteArrayOutputStream_resize_bitmap3.toByteArray(), Base64.DEFAULT);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(koneksi.BASE_URL_API)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        BaseApiService api = retrofit.create(BaseApiService.class);

        Log.d("helloo",encodedImage1 +"   >>"+imgname1);
        Log.d("imggggg","   >>"+imgname1);

        Call<String> call = api.getSubmitKelolaSampah(
                namaorganisasi, keterangan, penjawab, alamat, telpon, email,
                lat, lon, rt, rw, idkelurahan, idsouvenir, useradd,
                imgname1, imgname2, imgname3, encodedImage1, encodedImage2, encodedImage3,
                encodedImage_resize_1, encodedImage_resize_2, encodedImage_resize_3
        );

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Responsestring", response.body().toString());
                if (response.isSuccessful()) {
                    loading.dismiss();
                    Boolean error = response.body().isEmpty();

                    if (error.equals(true)) {
                        String msg = "Terjadi kesalahan!";
                        onNothingData(msg);
                    } else {
                        Toast.makeText(AddKelolaSampahActivity.this, "Berhasil menambahkan!", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                } else {
                    loading.dismiss();
                    String msg = "Gagal terhubung ke server";
                    onErrorServer(msg);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                loading.dismiss();
                onErrorConnection();
            }
        });
    }

    private void submitCCraft2(Bitmap bitmap1, Bitmap bitmap2, Bitmap bitmap3){
        String namaorganisasi = edt_namaorganisasi.getText().toString();
        String keterangan = edt_keterangan.getText().toString();
        String penjawab = edt_penjawab.getText().toString();
        String alamat = edt_alamat.getText().toString();
        String telpon = edt_telp.getText().toString();
        String email = edt_email.getText().toString();
        String lat = edt_lat.getText().toString();
        String lon = edt_lon.getText().toString();
        String rt = edt_rt.getText().toString();
        String rw = edt_rw.getText().toString();
        String idkelurahan = edt_idkelurahan.getText().toString();
        String idsouvenir = edt_id_souvenir.getText().toString();
        String useradd = edt_useradd.getText().toString();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        String encodedImage1 = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
        String imgname1 = String.valueOf(Calendar.getInstance().getTimeInMillis());

        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        bitmap2.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream2);
        String encodedImage2 = Base64.encodeToString(byteArrayOutputStream2.toByteArray(), Base64.DEFAULT);
        String imgname2 = String.valueOf(Calendar.getInstance().getTimeInMillis()+1);

        ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
        bitmap3.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream3);
        String encodedImage3 = Base64.encodeToString(byteArrayOutputStream3.toByteArray(), Base64.DEFAULT);
        String imgname3 = String.valueOf(Calendar.getInstance().getTimeInMillis()+2);

        //Resize image bitmap
        Bitmap bitmap1_245;
        bitmap1_245 = bitmap1;
        float aspectRatio = bitmap1_245.getWidth() /
                (float) bitmap1_245.getHeight();
        int width = 245;
        int height = Math.round(width / aspectRatio);
        Bitmap resized_bitmap1 = Bitmap.createScaledBitmap(bitmap1_245, width, height, false);
        ByteArrayOutputStream byteArrayOutputStream_resize_bitmap1 = new ByteArrayOutputStream();
        resized_bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream_resize_bitmap1);
        String encodedImage_resize_1 = Base64.encodeToString(byteArrayOutputStream_resize_bitmap1.toByteArray(), Base64.DEFAULT);

        Bitmap bitmap2_245;
        bitmap2_245 = bitmap2;
        float aspectRatio2 = bitmap2_245.getWidth() /
                (float) bitmap2_245.getHeight();
        int width2 = 245;
        int height2 = Math.round(width2 / aspectRatio2);
        Bitmap resized_bitmap2 = Bitmap.createScaledBitmap(bitmap2_245, width2, height2, false);
        ByteArrayOutputStream byteArrayOutputStream_resize_bitmap2 = new ByteArrayOutputStream();
        resized_bitmap2.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream_resize_bitmap2);
        String encodedImage_resize_2 = Base64.encodeToString(byteArrayOutputStream_resize_bitmap2.toByteArray(), Base64.DEFAULT);

        Bitmap bitmap3_245;
        bitmap3_245 = bitmap3;
        float aspectRatio3 = bitmap3_245.getWidth() /
                (float) bitmap3_245.getHeight();
        int width3 = 245;
        int height3 = Math.round(width3 / aspectRatio3);
        Bitmap resized_bitmap3 = Bitmap.createScaledBitmap(bitmap3_245, width3, height3, false);
        ByteArrayOutputStream byteArrayOutputStream_resize_bitmap3 = new ByteArrayOutputStream();
        resized_bitmap3.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream_resize_bitmap3);
        String encodedImage_resize_3 = Base64.encodeToString(byteArrayOutputStream_resize_bitmap3.toByteArray(), Base64.DEFAULT);

        mApiService.getSubmitKelolaSampah(
                namaorganisasi, keterangan, penjawab, alamat, telpon, email,
                lat, lon, rt, rw, idkelurahan, idsouvenir, useradd,
                imgname1, imgname2, imgname3, encodedImage1, encodedImage2, encodedImage3,
                encodedImage_resize_1, encodedImage_resize_2, encodedImage_resize_3
        ).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()){
                    loading.dismiss();

                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().toString());

                        if (jsonRESULTS.getString("success").equals(1)){
                            Toast.makeText(mContext, "Data berhasil di tambahkan!",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            String msg = "Data Gagal Di tambahkan!";
                            onNothingData(msg);
                        }
                    } catch (JSONException e) {
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
            public void onFailure(Call<String> call, Throwable t) {
                loading.dismiss();
//                onErrorConnection();
                Toast.makeText(mContext, "Data berhasil di tambahkan!",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
