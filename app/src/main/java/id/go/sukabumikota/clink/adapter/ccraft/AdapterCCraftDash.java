package id.go.sukabumikota.clink.adapter.ccraft;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
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
import id.go.sukabumikota.clink.model.ccraft.AllResponseCCraft;
import id.go.sukabumikota.clink.ui.creative.DetailCCraftActivity;

import java.util.List;

import jp.wasabeef.glide.transformations.gpu.VignetteFilterTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class AdapterCCraftDash extends RecyclerView.Adapter<AdapterCCraftDash.AdapterCCraftHolder> {

    List<AllResponseCCraft> allResponseCCraftslist;
    Context mContext;

    public AdapterCCraftDash(Context mContext, List<AllResponseCCraft> allResponseCCrafts) {
        this.mContext = mContext;
        allResponseCCraftslist = allResponseCCrafts;
    }

    @NonNull
    @Override
    public AdapterCCraftDash.AdapterCCraftHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_recyclerzonedash, parent, false);
        return new AdapterCCraftHolder(itemview);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final AdapterCCraftDash.AdapterCCraftHolder holder, int position) {
        final AllResponseCCraft allResponseCCraft = allResponseCCraftslist.get(position);

        holder.tv_title.setText(allResponseCCraft.getTm_kategori_souvenir_nama());
        holder.tv_isi.setText("Dari : "+allResponseCCraft.getTm_kelola_sampah_nama());

        Glide.with(holder.itemView.getContext())
                .load(koneksi.BASE_URL_API
                        + "storage/images/creativecraft/"
                        + allResponseCCraft.getTm_kelola_sampah_cover_nama())
                .apply(bitmapTransform(new VignetteFilterTransformation(new PointF(0.5f, 0.5f),
                        new float[]{0.0f, 0.0f, 0.0f}, 0.4f, 1.0f)).dontAnimate())
                .into(holder.img_recyclerzone);

        holder.btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToDetail = new Intent(mContext, DetailCCraftActivity.class);
                moveToDetail.putExtra(DetailCCraftActivity.EXTRA_DATA, allResponseCCraft);
                mContext.startActivity(moveToDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allResponseCCraftslist.size();
    }

    public class AdapterCCraftHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_isi;
        ImageView img_recyclerzone;
        Button btn_detail;

        public AdapterCCraftHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.tv_title_recyclerzone_dash);
            tv_isi = itemView.findViewById(R.id.tv_isi_recyclerzone_dash);
            img_recyclerzone = itemView.findViewById(R.id.img_recyclerzone_dash);
            btn_detail = itemView.findViewById(R.id.btn_detail_recyclerzone_dash);
        }
    }
}
