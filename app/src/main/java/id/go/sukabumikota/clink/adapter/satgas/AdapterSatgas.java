package id.go.sukabumikota.clink.adapter.satgas;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import id.go.sukabumikota.clink.R;
import id.go.sukabumikota.clink.api.koneksi;
import id.go.sukabumikota.clink.model.satgas.AllResponseSatgas;
import id.go.sukabumikota.clink.ui.satgas.DetailSatgasActivity;
import jp.wasabeef.glide.transformations.gpu.VignetteFilterTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class AdapterSatgas extends RecyclerView.Adapter<AdapterSatgas.AdapterSatgasHolder> {

    List<AllResponseSatgas> allResponseSatgasList;
    Context mContext;

    String isi;

    public AdapterSatgas(Context mContext, List<AllResponseSatgas> allResponseSatgases) {
        this.mContext = mContext;
        allResponseSatgasList = allResponseSatgases;
    }


    @NonNull
    @Override
    public AdapterSatgas.AdapterSatgasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_educationdash, parent, false);
        return new AdapterSatgasHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSatgasHolder holder, int position) {
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
                .into(holder.img_satgas);

        holder.cv_satgas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToDetail = new Intent(mContext, DetailSatgasActivity.class);
                moveToDetail.putExtra(DetailSatgasActivity.EXTRA_DATA, allResponseSatgas);
                mContext.startActivity(moveToDetail);
            }
        });

        holder.view_bg_satgas.setBackgroundColor(getColorWithAlpha(Color.BLACK, 0.6f));
    }

    @Override
    public int getItemCount() {
        return allResponseSatgasList.size();
    }

    public class AdapterSatgasHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_isi;
        ImageView img_satgas;
        LinearLayout view_bg_satgas;
        CardView cv_satgas;

        public AdapterSatgasHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.tv_title_education_dash);
            tv_isi = itemView.findViewById(R.id.tv_isi_education_dash);
            img_satgas = itemView.findViewById(R.id.img_education_dash);
            view_bg_satgas = itemView.findViewById(R.id.view_bg_education_dash);
            cv_satgas = itemView.findViewById(R.id.cv_educationdash);
        }
    }

    public static int getColorWithAlpha(int color, float ratio) {
        int newColor = 0;
        int alpha = Math.round(Color.alpha(color) * ratio);
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
        newColor = Color.argb(alpha, r, g, b);
        return newColor;
    }
}
