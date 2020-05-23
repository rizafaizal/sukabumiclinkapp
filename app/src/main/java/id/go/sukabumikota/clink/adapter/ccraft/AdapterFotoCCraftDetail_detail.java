package id.go.sukabumikota.clink.adapter.ccraft;

import android.content.Context;
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
import id.go.sukabumikota.clink.model.ccraft.fotoccraftdetail.AllFotoCCraftDetail;

public class AdapterFotoCCraftDetail_detail extends RecyclerView.Adapter<AdapterFotoCCraftDetail_detail.AdapterFotoCCraftDetail_detailHolder> {

    List<AllFotoCCraftDetail> allFotoCCraftDetailList;
    Context mContext;

    public AdapterFotoCCraftDetail_detail(Context mContext, List<AllFotoCCraftDetail> allFotoCCraftDetails) {
        this.mContext = mContext;
        allFotoCCraftDetailList = allFotoCCraftDetails;
    }


    @NonNull
    @Override
    public AdapterFotoCCraftDetail_detail.AdapterFotoCCraftDetail_detailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_foto_detail, parent, false);
        return new AdapterFotoCCraftDetail_detailHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFotoCCraftDetail_detail.AdapterFotoCCraftDetail_detailHolder holder, int position) {
        final AllFotoCCraftDetail allFotoCCraftDetail = allFotoCCraftDetailList.get(position);

        Glide.with(holder.itemView.getContext())
                .load(koneksi.BASE_URL_API
                        + "storage/images/creativecraft/"
                        + allFotoCCraftDetail.getTm_foto_kelola_sampah_nama())
                .into(holder.img_foto);
    }

    @Override
    public int getItemCount() {
        return allFotoCCraftDetailList.size();
    }

    public class AdapterFotoCCraftDetail_detailHolder extends RecyclerView.ViewHolder {
        ImageView img_foto;

        public AdapterFotoCCraftDetail_detailHolder(@NonNull View itemView) {
            super(itemView);
            img_foto = itemView.findViewById(R.id.img_fotodetails);
        }
    }
}
