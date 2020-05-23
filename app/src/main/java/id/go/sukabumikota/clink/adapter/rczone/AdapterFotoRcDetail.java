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
import id.go.sukabumikota.clink.R;
import id.go.sukabumikota.clink.api.koneksi;
import id.go.sukabumikota.clink.model.rczone.fotorczonedetail.AllFotoRcDetail;
import id.go.sukabumikota.clink.ui.forimages.ImagesActivity;
import id.go.sukabumikota.clink.ui.recyclerzone.DetailRecyclerZoneActivity;

import java.util.List;

public class AdapterFotoRcDetail extends RecyclerView.Adapter<AdapterFotoRcDetail.AdapterFotoDetailHolder> {

    List<AllFotoRcDetail> allFotoRcDetailList;
    Context mContext;

    String id;

    public AdapterFotoRcDetail(Context mContext, List<AllFotoRcDetail> allFotoRcDetails) {
        this.mContext = mContext;
        allFotoRcDetailList = allFotoRcDetails;
    }


    @NonNull
    @Override
    public AdapterFotoRcDetail.AdapterFotoDetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_foto, parent, false);
        return new AdapterFotoDetailHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterFotoRcDetail.AdapterFotoDetailHolder holder, int position) {
        final AllFotoRcDetail allFotoRcDetail = allFotoRcDetailList.get(position);

        Glide.with(holder.itemView.getContext())
                .load(koneksi.BASE_URL_API
                        + "storage/images/recyclerzone/"
                        + allFotoRcDetail.getTm_kategori_subolah_foto_nama())
//                .apply(new RequestOptions().override(350, 550))
                .into(holder.img_foto);

//        holder.img_foto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent moveToDetail = new Intent(mContext, ImagesActivity.class);
//                moveToDetail.putExtra(ImagesActivity.EXTRA_DATA, allFotoRcDetail);
//                mContext.startActivity(moveToDetail);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return allFotoRcDetailList.size();
    }

    public class AdapterFotoDetailHolder extends RecyclerView.ViewHolder {
        ImageView img_foto;

        public AdapterFotoDetailHolder(@NonNull View itemView) {
            super(itemView);
            img_foto = itemView.findViewById(R.id.img_foto_detail);
        }
    }
}
