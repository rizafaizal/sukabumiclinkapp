package id.go.sukabumikota.clink.adapter.satgas;

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
import id.go.sukabumikota.clink.model.satgas.fotosatgas.AllFotoSatgasDetail;

public class AdapterFotoSatgasDetail_detail extends RecyclerView.Adapter<AdapterFotoSatgasDetail_detail.AdapterFotoSatgasDetail_detailHolder> {

    List<AllFotoSatgasDetail> allFotoSatgasDetailList;
    Context mContext;

    public AdapterFotoSatgasDetail_detail(Context mContext, List<AllFotoSatgasDetail> allFotoSatgasDetails) {
        this.mContext = mContext;
        allFotoSatgasDetailList = allFotoSatgasDetails;
    }

    @NonNull
    @Override
    public AdapterFotoSatgasDetail_detail.AdapterFotoSatgasDetail_detailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_foto_detail, parent, false);
        return new AdapterFotoSatgasDetail_detailHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFotoSatgasDetail_detail.AdapterFotoSatgasDetail_detailHolder holder, int position) {
        final AllFotoSatgasDetail allFotoSatgasDetail = allFotoSatgasDetailList.get(position);

        Glide.with(holder.itemView.getContext())
                .load(koneksi.BASE_URL_API
                        + "storage/images/satgas/"
                        + allFotoSatgasDetail.getTm_foto_satgas_nama())
                .into(holder.img_foto);
    }

    @Override
    public int getItemCount() {
        return allFotoSatgasDetailList.size();
    }

    public class AdapterFotoSatgasDetail_detailHolder extends RecyclerView.ViewHolder {
        ImageView img_foto;

        public AdapterFotoSatgasDetail_detailHolder(@NonNull View itemView) {
            super(itemView);
            img_foto = itemView.findViewById(R.id.img_fotodetails);
        }
    }
}
