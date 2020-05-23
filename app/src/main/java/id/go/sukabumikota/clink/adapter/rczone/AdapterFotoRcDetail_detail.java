package id.go.sukabumikota.clink.adapter.rczone;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import id.go.sukabumikota.clink.R;
import id.go.sukabumikota.clink.api.koneksi;
import id.go.sukabumikota.clink.model.rczone.fotorczonedetail.AllFotoRcDetail;
import id.go.sukabumikota.clink.ui.forimages.ImagesActivity;
import id.go.sukabumikota.clink.ui.recyclerzone.DetailRecyclerZoneActivity;

public class AdapterFotoRcDetail_detail extends RecyclerView.Adapter<AdapterFotoRcDetail_detail.AdapterFotoRcDetail_detailHolder> {

    List<AllFotoRcDetail> allFotoRcDetailList;
    Context mContext;

    String id;

    public AdapterFotoRcDetail_detail(Context mContext, List<AllFotoRcDetail> allFotoRcDetails) {
        this.mContext = mContext;
        allFotoRcDetailList = allFotoRcDetails;
    }

    @NonNull
    @Override
    public AdapterFotoRcDetail_detail.AdapterFotoRcDetail_detailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_foto_detail, parent, false);
        return new AdapterFotoRcDetail_detailHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFotoRcDetail_detail.AdapterFotoRcDetail_detailHolder holder, int position) {
        final AllFotoRcDetail allFotoRcDetail = allFotoRcDetailList.get(position);

        Glide.with(holder.itemView.getContext())
                .load(koneksi.BASE_URL_API
                        + "storage/images/recyclerzone/"
                        + allFotoRcDetail.getTm_kategori_subolah_foto_nama())
                .into(holder.img_foto);
    }

    @Override
    public int getItemCount() {
        return allFotoRcDetailList.size();
    }

    public class AdapterFotoRcDetail_detailHolder extends RecyclerView.ViewHolder {
        ImageView img_foto;

        public AdapterFotoRcDetail_detailHolder(@NonNull View itemView) {
            super(itemView);
            img_foto = itemView.findViewById(R.id.img_fotodetails);
        }
    }
}
