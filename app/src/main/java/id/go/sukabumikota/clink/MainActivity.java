package id.go.sukabumikota.clink;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.google.android.material.navigation.NavigationView;
import id.go.sukabumikota.clink.R;
import id.go.sukabumikota.clink.ui.banksampah.BankSampahActivity;
import id.go.sukabumikota.clink.ui.berita.BeritaActivity;
import id.go.sukabumikota.clink.ui.boulevard.BoulevardActivity;
import id.go.sukabumikota.clink.ui.creative.CCraftActivity;
import id.go.sukabumikota.clink.ui.education.EducationActivity;
import id.go.sukabumikota.clink.ui.formkelolasampah.FormKelolaSampahActivity;
import id.go.sukabumikota.clink.ui.profile.ProfileActivity;
import id.go.sukabumikota.clink.ui.recyclerzone.RecyclerZoneActivity;
import id.go.sukabumikota.clink.ui.satgas.SatgasActivity;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    TextView tv_name;
    private AppBarConfiguration mAppBarConfiguration;

    private long exitTime = 0;

    SharedPreferences sharedpreferences;
    String id, nama, username, password, id_kelurahan, nama_kelurahan,
            id_kecamatan, nama_kecamatan, id_rw, nama_rw, id_rt, nama_rt;
    Boolean session = false;
    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";

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
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        inisialisasi();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        MenuItem navRecyclerZone = navigationView.getMenu().findItem(R.id.nav_recycler_zone);
        navRecyclerZone.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent rczone = new Intent(MainActivity.this, RecyclerZoneActivity.class);
                startActivity(rczone);

                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

                return false;
            }
        });

        MenuItem creativeCraft = navigationView.getMenu().findItem(R.id.nav_creative);
        creativeCraft.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent ccraft = new Intent(MainActivity.this, CCraftActivity.class);
                startActivity(ccraft);

                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

                return false;
            }
        });

        MenuItem edukasi = navigationView.getMenu().findItem(R.id.nav_education);
        edukasi.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent education = new Intent(MainActivity.this, EducationActivity.class);
                startActivity(education);

                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

                return false;
            }
        });

        MenuItem satgas = navigationView.getMenu().findItem(R.id.nav_satgas);
        satgas.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent satgas = new Intent(MainActivity.this, SatgasActivity.class);
                startActivity(satgas);

                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

                return false;
            }
        });

        MenuItem banksampah = navigationView.getMenu().findItem(R.id.nav_banksampah);
        banksampah.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent banksampah = new Intent(MainActivity.this, BankSampahActivity.class);
                startActivity(banksampah);

                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

                return false;
            }
        });

        MenuItem berita = navigationView.getMenu().findItem(R.id.nav_berita);
        berita.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent berita = new Intent(MainActivity.this, BeritaActivity.class);
                startActivity(berita);

                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

                return false;
            }
        });

        MenuItem boulevard = navigationView.getMenu().findItem(R.id.nav_boulevard);
        boulevard.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent boulevard = new Intent(MainActivity.this, BoulevardActivity.class);
                startActivity(boulevard);

                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

                return false;
            }
        });

        MenuItem navFormKelolaSampah = navigationView.getMenu().findItem(R.id.nav_formkelolasampah);
        navFormKelolaSampah.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent formkelolasampah = new Intent(MainActivity.this, FormKelolaSampahActivity.class);
                formkelolasampah.putExtra(TAG_ID, id);
                formkelolasampah.putExtra(TAG_NAMA, nama);
                formkelolasampah.putExtra(TAG_USERNAME, username);
                formkelolasampah.putExtra(TAG_PASSWORD, password);
                formkelolasampah.putExtra(TAG_ID_KELURAHAN, id_kelurahan);
                formkelolasampah.putExtra(TAG_NAMA_KELURAHAN, nama_kelurahan);
                formkelolasampah.putExtra(TAG_ID_KECAMATAN, id_kecamatan);
                formkelolasampah.putExtra(TAG_NAMA_KECAMATAN, nama_kecamatan);
                formkelolasampah.putExtra(TAG_ID_RW, id_rw);
                formkelolasampah.putExtra(TAG_NAMA_RW, nama_rw);
                formkelolasampah.putExtra(TAG_ID_RT, id_rt);
                formkelolasampah.putExtra(TAG_NAMA_RT, nama_rt);
                startActivity(formkelolasampah);

                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

                return false;
            }
        });

        MenuItem profile = navigationView.getMenu().findItem(R.id.nav_profile);
        profile.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
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

                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

                return false;
            }
        });

        MenuItem navLogout = navigationView.getMenu().findItem(R.id.nav_logout);
        navLogout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                verifyExit();

                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

                return false;
            }
        });

        MenuItem navLogin = navigationView.getMenu().findItem(R.id.nav_login);
        navLogin.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                endSession();

                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

                return false;
            }
        });

        ambilSession();

        if (id == null) {
            tv_name.setText(getResources().getString(R.string.app_name));
            hideItem();
        } else {
            tv_name.setText("Kec. "+nama_kecamatan);
            hideLogin();
        }
    }

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, "Tekan sekali lagi untuk keluar", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    private void inisialisasi() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        tv_name = header.findViewById(R.id.tv_nama_header);
    }

    public void ambilSession() {
        sharedpreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
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
    }

    public void endSession() {
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

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void hideItem() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_profile).setVisible(false);
        nav_Menu.findItem(R.id.nav_formkelolasampah).setVisible(false);
        nav_Menu.findItem(R.id.nav_logout).setVisible(false);
    }

    private void hideLogin() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_login).setVisible(false);
    }

    void onRefresh() {
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }

    private void verifyExit() {
        new MaterialStyledDialog.Builder(this)
                .setTitle("Yakin Logout?")
                .setDescription("Anda akan keluar dari sesi.\n" +
                        "Klik Ya untuk logout!")
                .setIcon(R.drawable.icon_warning_red)
                .setHeaderDrawable(R.drawable.bg_gradient_orange)
                .withDialogAnimation(true)
                .withIconAnimation(true)
                .setCancelable(false)
                .setPositiveText("Ya")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        endSession();
                    }
                })
                .setNegativeText("Kembali")
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionrefresh:
                Toast.makeText(MainActivity.this, "Refresh", Toast.LENGTH_SHORT).show();
                onRefresh();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public String getId_MA() {
        return id;
    }

    public String getNama_MA() {
        return nama;
    }

    public String getUsername_MA() {
        return username;
    }

    public String getPassword_MA() {
        return password;
    }

    public String getIdKelurahan_MA() {
        return id_kelurahan;
    }

    public String getNama_kelurahan_MA() {
        return nama_kelurahan;
    }

    public String getIdKecamatan_MA() {
        return id_kecamatan;
    }

    public String getNama_Kecamatan_MA() {
        return nama_kecamatan;
    }

    public String getIdRW_MA() {
        return id_rw;
    }

    public String getNama_RW_MA() {
        return nama_rw;
    }

    public String getIdRt_MA() {
        return id_rt;
    }

    public String getNama_RT_MA() {
        return nama_rt;
    }
}
