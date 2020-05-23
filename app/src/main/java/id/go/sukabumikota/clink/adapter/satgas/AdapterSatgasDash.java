package id.go.sukabumikota.clink.adapter.satgas;

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

import java.util.List;

import id.go.sukabumikota.clink.R;
import id.go.sukabumikota.clink.api.koneksi;
import id.go.sukabumikota.clink.model.satgas.AllResponseSatgas;
import id.go.sukabumikota.clink.ui.satgas.DetailSatgasActivity;
import jp.wasabeef.glide.transformations.gpu.VignetteFilterTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class AdapterSatgasDash extends RecyclerView.Adapter<AdapterSatgasDash.AdapterSatgasDashHolder> {

    List<AllResponseSatgas> allResponseSatgasList;
    Context mContext;

    String isi;

    public AdapterSatgasDash(Context mContext, List<AllResponseSatgas> allResponseCCraftslist) {
        this.mContext = mContext;
        allResponseSatgasList = allResponseCCraftslist;
    }


    @NonNull
    @Override
    public AdapterSatgasDash.AdapterSatgasDashHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_recyclerzonedash, parent, false);
        return new AdapterSatgasDashHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSatgasDashHolder holder, int position) {
        final AllResponseSatgas allResponseSatgas = allResponseSatgasList.get(position);

        isi = allResponseSatgas.getTm_satgas_konten();

        holder.tv_title.setText(allResponseSatgas.getTm_satgas_judul());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.tv_isi.setText(Html.fromHtml(isi, Html.FROM_HTML_MODE_LEGACY));
        } else {
            holder.tv_isi.setText(isi);
        }

        Glide.with(holder.itemView.getContext())
                .load(koneksi.BASE_URL_API
                        + "storage/images/satgas/"
                        + allResponseSatgas.getTm_satgas_cover_nama())
                .apply(bitmapTransform(new VignetteFilterTransformation(new PointF(0.5f, 0.5f),
                        new float[]{0.0f, 0.0f, 0.0f}, 0.4f, 1.0f)).dontAnimate())
                .into(holder.img_recyclerzone);

        holder.btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToDetail = new Intent(mContext, DetailSatgasActivity.class);
                moveToDetail.putExtra(DetailSatgasActivity.EXTRA_DATA, allResponseSatgas);
                mContext.startActivity(moveToDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allResponseSatgasList.size();
    }

    public class AdapterSatgasDashHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_isi;
        ImageView img_recyclerzone;
        Button btn_detail;

        public AdapterSatgasDashHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.tv_title_recyclerzone_dash);
            tv_isi = itemView.findViewById(R.id.tv_isi_recyclerzone_dash);
            img_recyclerzone = itemView.findViewById(R.id.img_recyclerzone_dash);
            btn_detail = itemView.findViewById(R.id.btn_detail_recyclerzone_dash);
        }
    }
}
