package id.go.sukabumikota.clink.adapter.ccraft;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import id.go.sukabumikota.clink.R;
import id.go.sukabumikota.clink.api.koneksi;
import id.go.sukabumikota.clink.model.ccraft.fotoccraftdetail.AllFotoCCraftDetail;

import java.util.List;

public class AdapterFotoCCraftDetail extends RecyclerView.Adapter<AdapterFotoCCraftDetail.AdapterFotoDetailCCraftHolder> {

    List<AllFotoCCraftDetail> allFotoCCraftDetailList;
    Context mContext;

    public AdapterFotoCCraftDetail(Context mContext, List<AllFotoCCraftDetail> allFotoCCraftDetails) {
        this.mContext = mContext;
        allFotoCCraftDetailList = allFotoCCraftDetails;
    }


    @NonNull
    @Override
    public AdapterFotoCCraftDetail.AdapterFotoDetailCCraftHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_foto, parent, false);
        return new AdapterFotoDetailCCraftHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterFotoCCraftDetail.AdapterFotoDetailCCraftHolder holder, int position) {
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

    public class AdapterFotoDetailCCraftHolder extends RecyclerView.ViewHolder {
        ImageView img_foto;

        public AdapterFotoDetailCCraftHolder(@NonNull View itemView) {
            super(itemView);
            img_foto = itemView.findViewById(R.id.img_foto_detail);
        }
    }
}
