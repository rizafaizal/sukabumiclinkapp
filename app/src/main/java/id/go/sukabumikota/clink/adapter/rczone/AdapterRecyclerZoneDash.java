package id.go.sukabumikota.clink.adapter.rczone;

import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import id.go.sukabumikota.clink.R;
import id.go.sukabumikota.clink.api.koneksi;
import id.go.sukabumikota.clink.model.rczone.AllResponseRecyclerZone;
import id.go.sukabumikota.clink.ui.recyclerzone.DetailRecyclerZoneActivity;

import java.util.List;

import jp.wasabeef.glide.transformations.gpu.VignetteFilterTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class AdapterRecyclerZoneDash extends RecyclerView.Adapter<AdapterRecyclerZoneDash.AdapterRecyclerHolder> {

    List<AllResponseRecyclerZone> allResponseRecyclerZones;
    Context mContext;

    String ket;

    public AdapterRecyclerZoneDash(Context mContext, List<AllResponseRecyclerZone> allResponseRecyclerZoneList) {
        this.mContext = mContext;
        allResponseRecyclerZones = allResponseRecyclerZoneList;
    }


    @NonNull
    @Override
    public AdapterRecyclerZoneDash.AdapterRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_recyclerzonedash, parent, false);
        return new AdapterRecyclerHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterRecyclerHolder holder, int position) {
        final AllResponseRecyclerZone allResponseRecyclerZone = allResponseRecyclerZones.get(position);

        ket = allResponseRecyclerZone.getTm_kategori_subolah_keterangan();

        holder.tv_title.setText(allResponseRecyclerZone.getTm_kategori_subolah_nama());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.tv_isi.setText(Html.fromHtml(ket, Html.FROM_HTML_MODE_LEGACY));
        } else {
            holder.tv_isi.setText(ket);
        }

        Glide.with(holder.itemView.getContext())
                .load(koneksi.BASE_URL_API
                        + "storage/images/recyclerzone/"
                        + allResponseRecyclerZone.getTm_kategori_subolah_cover_nama())
                .apply(bitmapTransform(new VignetteFilterTransformation(new PointF(0.5f, 0.5f),
                        new float[]{0.0f, 0.0f, 0.0f}, 0.4f, 1.0f)).dontAnimate())
                .into(holder.img_recyclerzone);

        holder.btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(holder.itemView.getContext(), "Dipilih : " +
//                        allResponseRecyclerZones.get(holder.getAdapterPosition()).getTm_kategori_subolah_nama(), Toast.LENGTH_SHORT).show();
                Intent moveToDetail = new Intent(mContext, DetailRecyclerZoneActivity.class);
                moveToDetail.putExtra(DetailRecyclerZoneActivity.EXTRA_DATA, allResponseRecyclerZone);
                mContext.startActivity(moveToDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allResponseRecyclerZones.size();
    }

    public class AdapterRecyclerHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_isi;
        ImageView img_recyclerzone;
        Button btn_detail;

        public AdapterRecyclerHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title_recyclerzone_dash);
            tv_isi = itemView.findViewById(R.id.tv_isi_recyclerzone_dash);
            img_recyclerzone = itemView.findViewById(R.id.img_recyclerzone_dash);
            btn_detail = itemView.findViewById(R.id.btn_detail_recyclerzone_dash);
        }
    }
}
