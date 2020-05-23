package id.go.sukabumikota.clink.ui.home;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

import id.go.sukabumikota.clink.LoginActivity;
import id.go.sukabumikota.clink.MainActivity;
import id.go.sukabumikota.clink.R;
import id.go.sukabumikota.clink.SupportLayout.StaggeredItemDecoration;
import id.go.sukabumikota.clink.adapter.berita.AdapterBeritaDash;
import id.go.sukabumikota.clink.adapter.boulevard.AdapterBoulevardDash;
import id.go.sukabumikota.clink.adapter.ccraft.AdapterCCraftDash;
import id.go.sukabumikota.clink.adapter.education.AdapterEducationDash;
import id.go.sukabumikota.clink.adapter.rczone.AdapterRecyclerZoneDash;
import id.go.sukabumikota.clink.adapter.satgas.AdapterSatgasDash;
import id.go.sukabumikota.clink.api.BaseApiService;
import id.go.sukabumikota.clink.api.koneksi;
import id.go.sukabumikota.clink.model.berita.AllResponseBerita;
import id.go.sukabumikota.clink.model.berita.ResponseBerita;
import id.go.sukabumikota.clink.model.boulevard.AllResponseBoulevard;
import id.go.sukabumikota.clink.model.boulevard.ResponseBoulevard;
import id.go.sukabumikota.clink.model.ccraft.AllResponseCCraft;
import id.go.sukabumikota.clink.model.ccraft.ResponseCCraft;
import id.go.sukabumikota.clink.model.education.AllResponseEducation;
import id.go.sukabumikota.clink.model.education.ResponseEducation;
import id.go.sukabumikota.clink.model.rczone.AllResponseRecyclerZone;
import id.go.sukabumikota.clink.model.rczone.ResponseRecyclerZone;
import id.go.sukabumikota.clink.model.satgas.AllResponseSatgas;
import id.go.sukabumikota.clink.model.satgas.ResponseSatgas;
import id.go.sukabumikota.clink.ui.banksampah.BankSampahActivity;
import id.go.sukabumikota.clink.ui.berita.BeritaActivity;
import id.go.sukabumikota.clink.ui.boulevard.BoulevardActivity;
import id.go.sukabumikota.clink.ui.creative.CCraftActivity;
import id.go.sukabumikota.clink.ui.education.EducationActivity;
import id.go.sukabumikota.clink.ui.formkelolasampah.FormKelolaSampahActivity;
import id.go.sukabumikota.clink.ui.profile.ProfileActivity;
import id.go.sukabumikota.clink.ui.recyclerzone.RecyclerZoneActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import id.go.sukabumikota.clink.ui.satgas.SatgasActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    CardView cv_trash, cv_creative, cv_education, cv_satgas, cv_kelolasampah,
            cv_profile, cv_banksampah, cv_berita, cv_boulevard;
    TextView tv_head_kelurahan, tv_head_namauser, tv_rczone_all_dash, tv_ccraft_all_dash, tv_education_all_dash,
            tv_satgas_all_dash, tv_boulevard_all_dash;
//    SwipeRefreshLayout swp_dashboard;
    ImageView img_refresh_dash;
    Button btn_seeAll_berita;

    SharedPreferences sharedpreferences;
    String id, nama, username, password, id_kelurahan, nama_kelurahan,
            id_kecamatan, nama_kecamatan, id_rw, nama_rw, id_rt, nama_rt;

    ProgressDialog loading;

    Context mContext;
    List<AllResponseRecyclerZone> allResponseRecyclerZones = new ArrayList<>();
    List<AllResponseCCraft> allResponseCCrafts = new ArrayList<>();
    List<AllResponseEducation> allResponseEducations = new ArrayList<>();
    List<AllResponseSatgas> allResponseSatgases = new ArrayList<>();
    List<AllResponseBerita> allResponseBeritas = new ArrayList<>();
    List<AllResponseBoulevard> allResponseBoulevardList = new ArrayList<>();

    AdapterRecyclerZoneDash adapterRecyclerZoneDash;
    AdapterCCraftDash adapterCCraftDash;
    AdapterEducationDash adapterEducationDash;
    AdapterSatgasDash adapterSatgasDash;
    AdapterBeritaDash adapterBeritaDash;
    AdapterBoulevardDash adapterBoulevardDash;

    BaseApiService mApiService;
    private RecyclerView recyclerView, recyclerViewCCraft, recyclerViewEducation,
            recyclerViewSatgas, recyclerviewBeritas, recyclerviewBoulevard;

    private StaggeredItemDecoration mStaggeredItemDecoration;

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


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv_head_kelurahan = view.findViewById(R.id.tv_head_kelurahan_dash);
        tv_head_namauser = view.findViewById(R.id.tv_head_namauser_dash);
        tv_rczone_all_dash = view.findViewById(R.id.tv_rczone_all_dash);
        tv_ccraft_all_dash = view.findViewById(R.id.tv_rcccraft_all_dash);
        tv_education_all_dash = view.findViewById(R.id.tv_education_all_dash);
        tv_satgas_all_dash = view.findViewById(R.id.tv_satgas_all_dash);
        tv_boulevard_all_dash = view.findViewById(R.id.tv_boulevard_all_dash);
        btn_seeAll_berita = view.findViewById(R.id.btn_all_beritas_dash);
//        swp_dashboard = view.findViewById(R.id.swp_dash);
        img_refresh_dash = view.findViewById(R.id.img_refresh_dash);
        cv_satgas = view.findViewById(R.id.cv_satgas_dash);
        cv_trash = view.findViewById(R.id.cv_trash_dash);
        cv_creative = view.findViewById(R.id.cv_craetive_dash);
        cv_education = view.findViewById(R.id.cv_education_dash);
        cv_kelolasampah = view.findViewById(R.id.cv_kelolasampah_dash);
        cv_profile = view.findViewById(R.id.cv_profile_dash);
        cv_banksampah = view.findViewById(R.id.cv_banksampah_dash);
        cv_berita = view.findViewById(R.id.cv_berita_dash);
        cv_boulevard = view.findViewById(R.id.cv_boulevard_dash);
        recyclerView = view.findViewById(R.id.rv_recyclerzone_dash);
        recyclerViewCCraft = view.findViewById(R.id.rv_ccraft_dash);
        recyclerViewEducation = view.findViewById(R.id.rv_education_dash);
        recyclerViewSatgas = view.findViewById(R.id.rv_satgas_dash);
        recyclerviewBeritas = view.findViewById(R.id.rv_berita_dash);
        recyclerviewBoulevard = view.findViewById(R.id.rv_boulevard_dash);

        mContext = getContext();
        mApiService = koneksi.getAPIService();

        //adapter RCZone
        adapterRecyclerZoneDash = new AdapterRecyclerZoneDash(getContext(), allResponseRecyclerZones);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //adapter CCraft
        adapterCCraftDash = new AdapterCCraftDash(getContext(), allResponseCCrafts);
        RecyclerView.LayoutManager mLayoutManagerCCraft = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCCraft.setLayoutManager(mLayoutManagerCCraft);
        recyclerViewCCraft.setItemAnimator(new DefaultItemAnimator());

        //adapter Education
        adapterEducationDash = new AdapterEducationDash(getContext(), allResponseEducations);
        RecyclerView.LayoutManager mLayoutManagerEducation = new GridLayoutManager(mContext, 2);
        recyclerViewEducation.setLayoutManager(mLayoutManagerEducation);
        recyclerViewEducation.setItemAnimator(new DefaultItemAnimator());

        //adapter Satgas
        adapterSatgasDash = new AdapterSatgasDash(getContext(), allResponseSatgases);
        RecyclerView.LayoutManager mLayoutManagerSatgas = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewSatgas.setLayoutManager(mLayoutManagerSatgas);
        recyclerViewSatgas.setItemAnimator(new DefaultItemAnimator());

        //adapter Berita
        adapterBeritaDash = new AdapterBeritaDash(getContext(), allResponseBeritas);
        RecyclerView.LayoutManager mLayoutManagerBerita = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerviewBeritas.setLayoutManager(mLayoutManagerBerita);
        recyclerviewBeritas.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration itemDecoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(mContext, R.drawable.line_recycler)));
        recyclerviewBeritas.addItemDecoration(itemDecoration);

        //adapter Boulevard
        adapterBoulevardDash = new AdapterBoulevardDash(getContext(), allResponseBoulevardList);
        RecyclerView.LayoutManager mLayoutManagerBoulevard = new GridLayoutManager(mContext, 2);
        recyclerViewEducation.setLayoutManager(mLayoutManagerBoulevard);
        recyclerViewEducation.setItemAnimator(new DefaultItemAnimator());

        getResultListRecyclerZoneDash();

        MainActivity mainActivity = (MainActivity) getActivity();
        id = mainActivity.getId_MA();
        nama = mainActivity.getNama_MA();
        username = mainActivity.getUsername_MA();
        password = mainActivity.getPassword_MA();
        id_kelurahan = mainActivity.getIdKelurahan_MA();
        nama_kelurahan = mainActivity.getNama_kelurahan_MA();
        id_kecamatan = mainActivity.getIdKecamatan_MA();
        nama_kecamatan = mainActivity.getNama_Kecamatan_MA();
        id_rw = mainActivity.getIdRW_MA();
        nama_rw = mainActivity.getNama_RW_MA();
        id_rt = mainActivity.getIdRt_MA();
        nama_rt = mainActivity.getNama_RT_MA();

        if (id == null) {
            tv_head_kelurahan.setText(getResources().getString(R.string.app_name));
        } else {
            tv_head_kelurahan.setText("Desa "+mainActivity.getNama_kelurahan_MA());
            tv_head_namauser.setText(mainActivity.getNama_MA());
            cv_kelolasampah.setVisibility(View.VISIBLE);
            cv_profile.setVisibility(View.VISIBLE);
        }

        img_refresh_dash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });

        cv_satgas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent satgas = new Intent(getContext(), SatgasActivity.class);
                startActivity(satgas);
            }
        });

        cv_trash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rczone = new Intent(getContext(), RecyclerZoneActivity.class);
                startActivity(rczone);
            }
        });

        cv_creative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ccraft = new Intent(getContext(), CCraftActivity.class);
                startActivity(ccraft);
            }
        });

        cv_education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent education = new Intent(getContext(), EducationActivity.class);
                startActivity(education);
            }
        });

        cv_banksampah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent banksampah = new Intent(getContext(), BankSampahActivity.class);
                startActivity(banksampah);
            }
        });

        cv_berita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent berita = new Intent(getContext(), BeritaActivity.class);
                startActivity(berita);
            }
        });

        cv_boulevard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent boulevard = new Intent(getContext(), BoulevardActivity.class);
                startActivity(boulevard);
            }
        });

        cv_kelolasampah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent formkelolasampah = new Intent(mContext, FormKelolaSampahActivity.class);
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
            }
        });

        cv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ProfileActivity.class);
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
            }
        });

        tv_rczone_all_dash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rczone = new Intent(getContext(), RecyclerZoneActivity.class);
                startActivity(rczone);
            }
        });

        tv_ccraft_all_dash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ccraft = new Intent(getContext(), CCraftActivity.class);
                startActivity(ccraft);
            }
        });

        tv_education_all_dash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent education = new Intent(getContext(), EducationActivity.class);
                startActivity(education);
            }
        });

        tv_satgas_all_dash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent satgas = new Intent(getContext(), SatgasActivity.class);
                startActivity(satgas);
            }
        });

        btn_seeAll_berita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent berita = new Intent(getContext(), BeritaActivity.class);
                startActivity(berita);
            }
        });

        tv_boulevard_all_dash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent boulevard = new Intent(getContext(), BoulevardActivity.class);
                startActivity(boulevard);
            }
        });

//        onSwipeRefresh();
    }

//    private void onSwipeRefresh() {
//        swp_dashboard.setColorSchemeResources(R.color.colorAccent,R.color.colorPrimary);
//        swp_dashboard.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                new Handler().postDelayed(new Runnable() {
//                    @Override public void run() {
//                        swp_dashboard.setRefreshing(false);
//
//                        getResultListRecyclerZoneDash();
//                        getResultListRCCraftDash();
//                        getResultListEducation();
//                    }
//                }, 2000);
//            }
//        });
//    }

    private void onRefresh() {
        Toast.makeText(mContext, "Refresh", Toast.LENGTH_SHORT).show();
        getActivity().finish();
        getActivity().overridePendingTransition(0, 0);
        startActivity(getActivity().getIntent());
        getActivity().overridePendingTransition(0, 0);
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
                .setNegativeText("Keluar")
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        getActivity().finish();
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
                .setCancelable(true)
                .setPositiveText("Refresh")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        onRefresh();
                    }
                })
                .setNegativeText("OK")
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

    private void getResultListRecyclerZoneDash(){
        loading = ProgressDialog.show(getContext(), null, "Memuat Data.", true, false);

        mApiService.getAllRecyclerZone().enqueue(new Callback<ResponseRecyclerZone>() {
            @Override
            public void onResponse(Call<ResponseRecyclerZone> call, Response<ResponseRecyclerZone> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    Boolean error = response.body().isError();

                    allResponseRecyclerZones = response.body().getAllrecyclerzone();

                    if (error.equals(true)) {
                        String msg = "Tidak ada data Recycler Zone!";
                        onNothingData(msg);
                        getResultListBerita();
                    } else {
                        if (allResponseRecyclerZones == null) {
                            String msg = "Kesalahan Data Recycler Zone!";
                            onNullData(msg);
                            getResultListBerita();
                        } else {
                            recyclerView.setAdapter(new AdapterRecyclerZoneDash(mContext, allResponseRecyclerZones));
                            adapterRecyclerZoneDash.notifyDataSetChanged();
                            getResultListBerita();
                        }
                    }

                } else {
                    loading.dismiss();
                    String msg = "Gagal mengambil data Recycler Zone";
                    onErrorServer(msg);
                    getResultListBerita();
                }
            }

            @Override
            public void onFailure(Call<ResponseRecyclerZone> call, Throwable t) {
                loading.dismiss();
                onErrorConnection();
            }
        });
    }

    private void getResultListBerita(){
        loading = ProgressDialog.show(getContext(), null, "Memuat Data.....", true, false);

        mApiService.getAllBerita().enqueue(new Callback<ResponseBerita>() {
            @Override
            public void onResponse(Call<ResponseBerita> call, Response<ResponseBerita> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    Boolean error = response.body().isError();

                    allResponseBeritas = response.body().getAllberitadata();

                    if (error.equals(true)) {
                        String msg = "Tidak ada data Berita!";
                        onNothingData(msg);
                        getResultListRCCraftDash();
                    } else {
                        if (allResponseBeritas == null) {
                            String msg = "Kesalahan Data Berita!";
                            onNullData(msg);
                            getResultListRCCraftDash();
                        } else {
                            recyclerviewBeritas.setAdapter(new AdapterBeritaDash(mContext, allResponseBeritas));
                            adapterBeritaDash.notifyDataSetChanged();
                            getResultListRCCraftDash();
                        }
                    }

                } else {
                    loading.dismiss();
                    String msg = "Gagal mengambil data Berita";
                    onErrorServer(msg);
                    getResultListRCCraftDash();
                }
            }

            @Override
            public void onFailure(Call<ResponseBerita> call, Throwable t) {
                loading.dismiss();
                onErrorConnection();
            }
        });
    }

    private void getResultListRCCraftDash(){
        loading = ProgressDialog.show(getContext(), null, "Memuat Data..", true, false);

        mApiService.getAllCreative().enqueue(new Callback<ResponseCCraft>() {
            @Override
            public void onResponse(Call<ResponseCCraft> call, Response<ResponseCCraft> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    Boolean error = response.body().isError();

                    allResponseCCrafts = response.body().getAllcreativedata();

                    if (error.equals(true)) {
                        String msg = "Tidak ada data Creative Craft!";
                        onNothingData(msg);
                        getResultListSatgas();
                    } else {
                        if (allResponseCCrafts == null) {
                            String msg = "Kesalahan Data Creative Craft!";
                            onNullData(msg);
                            getResultListSatgas();
                        } else {
                            recyclerViewCCraft.setAdapter(new AdapterCCraftDash(mContext, allResponseCCrafts));
                            adapterCCraftDash.notifyDataSetChanged();
                            getResultListSatgas();
                        }
                    }

                } else {
                    loading.dismiss();
                    String msg = "Gagal mengambil data Creative Craft";
                    onErrorServer(msg);
                    getResultListSatgas();
                }
            }

            @Override
            public void onFailure(Call<ResponseCCraft> call, Throwable t) {
                loading.dismiss();
                onErrorConnection();
            }
        });
    }

    private void getResultListSatgas(){
        loading = ProgressDialog.show(getContext(), null, "Memuat Data....", true, false);

        mApiService.getAllSatgas().enqueue(new Callback<ResponseSatgas>() {
            @Override
            public void onResponse(Call<ResponseSatgas> call, Response<ResponseSatgas> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    Boolean error = response.body().isError();

                    allResponseSatgases = response.body().getAllsatgasdata();

                    if (error.equals(true)) {
                        String msg = "Tidak ada data Satgas!";
                        onNothingData(msg);
                        getResultListEducation();
                    } else {
                        if (allResponseSatgases == null) {
                            String msg = "Kesalahan Data Satgas!";
                            onNullData(msg);
                            getResultListEducation();
                        } else {
                            recyclerViewSatgas.setAdapter(new AdapterSatgasDash(mContext, allResponseSatgases));
                            adapterSatgasDash.notifyDataSetChanged();
                            getResultListEducation();
                        }

                    }

                } else {
                    loading.dismiss();
                    String msg = "Gagal mengambil data Satgas";
                    onErrorServer(msg);
                    getResultListEducation();
                }
            }

            @Override
            public void onFailure(Call<ResponseSatgas> call, Throwable t) {
                loading.dismiss();
                onErrorConnection();
            }
        });
    }

    private void getResultListEducation(){
        loading = ProgressDialog.show(getContext(), null, "Memuat Data...", true, false);

        mApiService.getAllEducation().enqueue(new Callback<ResponseEducation>() {
            @Override
            public void onResponse(Call<ResponseEducation> call, Response<ResponseEducation> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    Boolean error = response.body().isError();

                    allResponseEducations = response.body().getAlleducationdata();

                    if (error.equals(true)) {
                        String msg = "Tidak ada data Education!";
                        onNothingData(msg);
                        getResultListBoulevard();
                    } else {
                        if (allResponseEducations == null) {
                            String msg = "Kesalahan Data Education!";
                            onNullData(msg);
                            getResultListBoulevard();
                        } else {
                            recyclerViewEducation.setAdapter(new AdapterEducationDash(mContext, allResponseEducations));
                            adapterEducationDash.notifyDataSetChanged();
                            getResultListBoulevard();
                        }
                    }

                } else {
                    loading.dismiss();
                    String msg = "Gagal mengambil data Education";
                    onErrorServer(msg);
                    getResultListBoulevard();
                }
            }

            @Override
            public void onFailure(Call<ResponseEducation> call, Throwable t) {
                loading.dismiss();
                onErrorConnection();
            }
        });
    }

    private void getResultListBoulevard(){
        loading = ProgressDialog.show(getContext(), null, "Memuat Data...", true, false);

        mApiService.getAllBoulevard().enqueue(new Callback<ResponseBoulevard>() {
            @Override
            public void onResponse(Call<ResponseBoulevard> call, Response<ResponseBoulevard> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    Boolean error = response.body().isError();

                    allResponseBoulevardList = response.body().getAllboulevarddata();

                    if (error.equals(true)) {
                        String msg = "Tidak ada data Boulevard!";
                        onNothingData(msg);
                    } else {
                        if (allResponseBoulevardList == null) {
                            String msg = "Kesalahan Data Boulevard!";
                            onNullData(msg);
                        } else {
                            recyclerviewBoulevard.setAdapter(new AdapterBoulevardDash(mContext, allResponseBoulevardList));
                            adapterBoulevardDash.notifyDataSetChanged();
                        }
                    }

                } else {
                    loading.dismiss();
                    String msg = "Gagal mengambil data Boulevard";
                    onErrorServer(msg);
                }
            }

            @Override
            public void onFailure(Call<ResponseBoulevard> call, Throwable t) {
                loading.dismiss();
                onErrorConnection();
            }
        });
    }
}
