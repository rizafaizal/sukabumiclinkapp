package id.go.sukabumikota.clink.adapter.berita;

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
import id.go.sukabumikota.clink.model.berita.fotoberita.AllFotoBeritaDetail;

public class AdapterFotoBeritaDetail extends RecyclerView.Adapter<AdapterFotoBeritaDetail.AdapterFotoBeritaDetailHolder> {

    List<AllFotoBeritaDetail> allFotoBeritaDetailList;
    Context mContext;

    public AdapterFotoBeritaDetail(Context mContext, List<AllFotoBeritaDetail> allFotoBeritaDetails) {
        this.mContext = mContext;
        allFotoBeritaDetailList = allFotoBeritaDetails;
    }


    @NonNull
    @Override
    public AdapterFotoBeritaDetail.AdapterFotoBeritaDetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_foto_2, parent, false);
        return new AdapterFotoBeritaDetailHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFotoBeritaDetail.AdapterFotoBeritaDetailHolder holder, int position) {
        final AllFotoBeritaDetail allFotoBeritaDetail = allFotoBeritaDetailList.get(position);

        Glide.with(holder.itemView.getContext())
                .load(koneksi.BASE_URL_API
                        + "storage/images/berita/"
                        + allFotoBeritaDetail.getTm_foto_berita_nama())
                .into(holder.img_foto);
    }

    @Override
    public int getItemCount() {
        return allFotoBeritaDetailList.size();
    }

    public class AdapterFotoBeritaDetailHolder extends RecyclerView.ViewHolder {
        ImageView img_foto;

        public AdapterFotoBeritaDetailHolder(@NonNull View itemView) {
            super(itemView);
            img_foto = itemView.findViewById(R.id.img_foto2_detail);
        }
    }
}
