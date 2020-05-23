package id.go.sukabumikota.clink.adapter.rczone;

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
import id.go.sukabumikota.clink.R;
import id.go.sukabumikota.clink.api.koneksi;
import id.go.sukabumikota.clink.model.rczone.AllResponseRecyclerZone;
import id.go.sukabumikota.clink.ui.recyclerzone.DetailRecyclerZoneActivity;

import java.util.List;

import jp.wasabeef.glide.transformations.gpu.VignetteFilterTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class AdapterRecyclerZone extends RecyclerView.Adapter<AdapterRecyclerZone.AdapterRecyclerZoneHolder>  {

    List<AllResponseRecyclerZone> allResponseRecyclerZones;
    Context mContext;

    String isi;

    public AdapterRecyclerZone(Context mContext, List<AllResponseRecyclerZone> allResponseRecyclerZoneList) {
        this.mContext = mContext;
        allResponseRecyclerZones = allResponseRecyclerZoneList;
    }


    @NonNull
    @Override
    public AdapterRecyclerZone.AdapterRecyclerZoneHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_recyclerzone, parent, false);
        return new AdapterRecyclerZoneHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterRecyclerZoneHolder holder, int position) {
        final AllResponseRecyclerZone allResponseRecyclerZone = allResponseRecyclerZones.get(position);

        isi = allResponseRecyclerZone.getTm_kategori_subolah_isi();

        holder.tv_title.setText(allResponseRecyclerZone.getTm_kategori_subolah_nama());
        holder.tv_titlebody.setText(allResponseRecyclerZone.getTm_kategori_subolah_nama());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.tv_isi.setText(Html.fromHtml(isi, Html.FROM_HTML_MODE_LEGACY));
        } else {
            holder.tv_isi.setText(isi);
        }

        Glide.with(holder.itemView.getContext())
                .load(koneksi.BASE_URL_API
                        + "storage/images/recyclerzone/"
                        + allResponseRecyclerZone.getTm_kategori_subolah_cover_nama())
                .apply(bitmapTransform(new VignetteFilterTransformation(new PointF(0.5f, 0.5f),
                        new float[]{0.0f, 0.0f, 0.0f}, 0.0f, 0.75f)).dontAnimate())
                .into(holder.img_recyclerzone);

        holder.cv_recyclerzone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToDetail = new Intent(mContext, DetailRecyclerZoneActivity.class);
                moveToDetail.putExtra(DetailRecyclerZoneActivity.EXTRA_DATA, allResponseRecyclerZone);
                mContext.startActivity(moveToDetail);
            }
        });

        holder.view_rczone.setBackgroundColor(getColorWithAlpha(Color.BLACK, 0.3f));
    }

    @Override
    public int getItemCount() {
        return allResponseRecyclerZones.size();
    }

    public class AdapterRecyclerZoneHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_titlebody, tv_isi;
        ImageView img_recyclerzone;
        CardView cv_recyclerzone;
        LinearLayout view_rczone;

        public AdapterRecyclerZoneHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.tv_title_rczone);
            tv_titlebody = itemView.findViewById(R.id.tv_titlebody_rczone);
            tv_isi = itemView.findViewById(R.id.tv_isi_rczone);
            img_recyclerzone = itemView.findViewById(R.id.img_thumb_rczone);
            cv_recyclerzone = itemView.findViewById(R.id.cv_recyclerzone);
            view_rczone = itemView.findViewById(R.id.view_bg_rczone);
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
