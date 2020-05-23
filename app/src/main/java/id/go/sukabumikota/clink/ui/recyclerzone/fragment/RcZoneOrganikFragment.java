package id.go.sukabumikota.clink.ui.recyclerzone.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

import id.go.sukabumikota.clink.MainActivity;
import id.go.sukabumikota.clink.R;
import id.go.sukabumikota.clink.SupportLayout.StaggeredItemDecoration;
import id.go.sukabumikota.clink.adapter.rczone.AdapterRecyclerZone;
import id.go.sukabumikota.clink.api.BaseApiService;
import id.go.sukabumikota.clink.api.koneksi;
import id.go.sukabumikota.clink.model.rczone.AllResponseRecyclerZone;
import id.go.sukabumikota.clink.model.rczone.ResponseRecyclerZone;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RcZoneOrganikFragment extends Fragment {

    ProgressDialog loading;

    Context mContext;
    List<AllResponseRecyclerZone> allResponseRecyclerZones = new ArrayList<>();
    AdapterRecyclerZone adapterRecyclerZone;
    BaseApiService mApiService;
    private RecyclerView recyclerView;

    private StaggeredItemDecoration mStaggeredItemDecoration;


    public RcZoneOrganikFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rc_zone_organik, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rv_recyclerzone);

        mContext = getContext();
        mApiService = koneksi.getAPIService();

        adapterRecyclerZone = new AdapterRecyclerZone(getContext(), allResponseRecyclerZones);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(mContext, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

//        mStaggeredItemDecoration = new StaggeredItemDecoration(getContext(), 2);
//        adapterRecyclerZone = new AdapterRecyclerZone(getContext(), allResponseRecyclerZones);
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
//        recyclerView.addItemDecoration(mStaggeredItemDecoration);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());

        getResultListRecyclerZone();
    }

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
                .setDescription("Pastikan koneksi internet anda berjalan dengan baik.\n\n" +
                        "Klik Refresh untuk memuat kembali\n" +
                        "Klik Kembali untuk kembali ke halaman utama.")
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
                        getActivity().finish();
                    }
                })
                .show();
    }

    private void onErrorServer(String message) {
        new MaterialStyledDialog.Builder(mContext)
                .setTitle(message)
                .setDescription("Gagal terhubung ke server.\n\n" +
                        "Klik Refresh untuk memuat kembali\n" +
                        "Klik Kembali untuk kembali ke halaman utama.")
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
                        getActivity().finish();
                    }
                })
                .show();
    }

    private void onNothingData(String message) {
        new MaterialStyledDialog.Builder(mContext)
                .setTitle(message)
                .setDescription("Tidak ada data yang dapat dimuat.\n\n" +
                        "Klik Refresh untuk memuat kembali\n" +
                        "Klik Kembali untuk kembali ke halaman utama.")
                .setIcon(R.drawable.icon_warning_red)
                .setHeaderDrawable(R.drawable.bg_gradient_orange)
                .withDialogAnimation(true)
                .withIconAnimation(true)
                .setCancelable(false)
                .setNegativeText("Kembali")
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        getActivity().finish();
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

    private void getResultListRecyclerZone(){
        loading = ProgressDialog.show(getContext(), null, "Memuat...", true, false);

        mApiService.getAllRecyclerZoneSelect("Organik").enqueue(new Callback<ResponseRecyclerZone>() {
            @Override
            public void onResponse(Call<ResponseRecyclerZone> call, Response<ResponseRecyclerZone> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    Boolean error = response.body().isError();
                    allResponseRecyclerZones = response.body().getAllrecyclerzone();

                    if (error.equals(true)) {
                        String msg = "Tidak ada data Recycler Zone Organik!";
                        onNothingData(msg);
                    } else {
                        if (allResponseRecyclerZones == null) {
                            String msg = "Kesalahan Data Recycler Zone Organik!";
                            onNullData(msg);
                        } else {
                            recyclerView.setAdapter(new AdapterRecyclerZone(mContext, allResponseRecyclerZones));
                            adapterRecyclerZone.notifyDataSetChanged();
                        }
                    }

                } else {
                    loading.dismiss();
                    String msg = "Gagal mengambil data Recycler Zone Organik";
                    onErrorServer(msg);
                }
            }

            @Override
            public void onFailure(Call<ResponseRecyclerZone> call, Throwable t) {
                loading.dismiss();
                onErrorConnection();
            }
        });
    }
}
