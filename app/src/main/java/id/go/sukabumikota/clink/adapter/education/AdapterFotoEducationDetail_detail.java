package id.go.sukabumikota.clink.adapter.education;

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
import id.go.sukabumikota.clink.model.education.fotoeducationdetail.AllFotoEducationDetail;

public class AdapterFotoEducationDetail_detail extends RecyclerView.Adapter<AdapterFotoEducationDetail_detail.AdapterFotoEducationDetail_detailHolder> {

    List<AllFotoEducationDetail> allFotoEducationDetailList;
    Context mContext;

    public AdapterFotoEducationDetail_detail(Context mContext, List<AllFotoEducationDetail> allFotoEducationDetails) {
        this.mContext = mContext;
        allFotoEducationDetailList = allFotoEducationDetails;
    }

    @NonNull
    @Override
    public AdapterFotoEducationDetail_detail.AdapterFotoEducationDetail_detailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_foto_detail, parent, false);
        return new AdapterFotoEducationDetail_detailHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFotoEducationDetail_detail.AdapterFotoEducationDetail_detailHolder holder, int position) {
        final AllFotoEducationDetail allFotoEducationDetail = allFotoEducationDetailList.get(position);

        Glide.with(holder.itemView.getContext())
                .load(koneksi.BASE_URL_API
                        + "storage/images/education/"
                        + allFotoEducationDetail.getTm_foto_edukasi_nama())
                .into(holder.img_foto);
    }

    @Override
    public int getItemCount() {
        return allFotoEducationDetailList.size();
    }

    public class AdapterFotoEducationDetail_detailHolder extends RecyclerView.ViewHolder {
        ImageView img_foto;

        public AdapterFotoEducationDetail_detailHolder(@NonNull View itemView) {
            super(itemView);
            img_foto = itemView.findViewById(R.id.img_fotodetails);
        }
    }
}
