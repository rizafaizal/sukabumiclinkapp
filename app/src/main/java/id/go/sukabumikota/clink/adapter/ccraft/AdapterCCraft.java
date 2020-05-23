package id.go.sukabumikota.clink.adapter.ccraft;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class AdapterCCraft extends RecyclerView.Adapter<AdapterCCraft.AdapterCCraftsHolder> {

    List<AllResponseCCraft> allResponseCCraftslist;
    Context mContext;

    String ket;

    public AdapterCCraft(Context mContext, List<AllResponseCCraft> allResponseCCrafts) {
        this.mContext = mContext;
        allResponseCCraftslist = allResponseCCrafts;
    }


    @NonNull
    @Override
    public AdapterCCraft.AdapterCCraftsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_ccraft, parent, false);
        return new AdapterCCraftsHolder(itemview);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AdapterCCraftsHolder holder, int position) {
        final AllResponseCCraft allResponseCCraft = allResponseCCraftslist.get(position);

        ket = allResponseCCraft.getTm_kelola_sampah_keterangan();

        holder.tv_title.setText(allResponseCCraft.getTm_kategori_souvenir_nama());
        holder.tv_oleh.setText("Dari : "+allResponseCCraft.getTm_kelola_sampah_nama());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.tv_isi.setText(Html.fromHtml(ket, Html.FROM_HTML_MODE_LEGACY));
        } else {
            holder.tv_isi.setText(ket);
        }

        Glide.with(holder.itemView.getContext())
                .load(koneksi.BASE_URL_API
                        + "storage/images/creativecraft/"
                        + allResponseCCraft.getTm_kelola_sampah_cover_nama())
                .apply(bitmapTransform(new VignetteFilterTransformation(new PointF(0.5f, 0.5f),
                        new float[]{0.0f, 0.0f, 0.0f}, 0.4f, 1.0f)).dontAnimate())
                .into(holder.img_ccraft);

        holder.ln_ccraft.setOnClickListener(new View.OnClickListener() {
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

    public class AdapterCCraftsHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_isi, tv_oleh;
        ImageView img_ccraft;
        LinearLayout ln_ccraft;

        public AdapterCCraftsHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.tv_title_ccraft);
            tv_isi = itemView.findViewById(R.id.tv_isi_ccraft);
            tv_oleh = itemView.findViewById(R.id.tv_oleh_ccraft);
            img_ccraft = itemView.findViewById(R.id.img_thumb_ccraft);
            ln_ccraft = itemView.findViewById(R.id.ln_ccraft);
        }
    }
}
