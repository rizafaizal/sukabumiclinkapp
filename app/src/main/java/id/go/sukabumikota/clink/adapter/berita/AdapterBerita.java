package id.go.sukabumikota.clink.adapter.berita;

import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import id.go.sukabumikota.clink.R;
import id.go.sukabumikota.clink.api.koneksi;
import id.go.sukabumikota.clink.model.berita.AllResponseBerita;
import id.go.sukabumikota.clink.ui.berita.DetailBeritaActivity;
import jp.wasabeef.glide.transformations.gpu.VignetteFilterTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class AdapterBerita extends RecyclerView.Adapter<AdapterBerita.AdapterBeritaHolder> {

    List<AllResponseBerita> allResponseBeritaList;
    Context mContext;

    private Date oneWayTripDate;
    String date;

    public AdapterBerita(Context mContext, List<AllResponseBerita> allResponseBeritas) {
        this.mContext = mContext;
        allResponseBeritaList = allResponseBeritas;
    }


    @NonNull
    @Override
    public AdapterBerita.AdapterBeritaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_berita, parent, false);
        return new AdapterBeritaHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBerita.AdapterBeritaHolder holder, int position) {
        final AllResponseBerita allResponseBerita = allResponseBeritaList.get(position);

        date = allResponseBerita.getCreated_at();
        String dates = date;
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("MMMM dd,yyyy hh:mm:ss aa");
        try {
            oneWayTripDate = input.parse(dates);  // parse input
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        holder.tv_date.setText(output.format(oneWayTripDate));

        if (oneWayTripDate == null) {
            holder.tv_date.setText("_");
        } else {
            assert oneWayTripDate != null;
            holder.tv_date.setText(output.format(oneWayTripDate));
        }

        holder.tv_judul.setText(allResponseBerita.getTm_berita_judul());
        holder.tv_tag.setText(allResponseBerita.getTm_berita_tag());
//        holder.tv_date.setText(allResponseBerita.getCreated_at());

        Glide.with(holder.itemView.getContext())
                .load(koneksi.BASE_URL_API
                        + "storage/images/berita/"
                        + allResponseBerita.getTm_berita_cover_nama())
                .apply(bitmapTransform(new VignetteFilterTransformation(new PointF(0.5f, 0.5f),
                        new float[]{0.0f, 0.0f, 0.0f}, 0.4f, 1.0f)).dontAnimate())
                .into(holder.img_berita);

        holder.cv_berita_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToDetail = new Intent(mContext, DetailBeritaActivity.class);
                moveToDetail.putExtra(DetailBeritaActivity.EXTRA_DATA, allResponseBerita);
                mContext.startActivity(moveToDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allResponseBeritaList.size();
    }

    public class AdapterBeritaHolder extends RecyclerView.ViewHolder {
        TextView tv_judul, tv_tag, tv_date;
        ImageView img_berita;
        CardView cv_berita_activity;

        public AdapterBeritaHolder(@NonNull View itemView) {
            super(itemView);

            tv_judul = itemView.findViewById(R.id.tv_list_judul_berita_activity);
            tv_tag = itemView.findViewById(R.id.tv_list_tag_berita_activity);
            tv_date = itemView.findViewById(R.id.tv_list_date_berita_activity);
            img_berita = itemView.findViewById(R.id.img_list_berita_activty);
            cv_berita_activity = itemView.findViewById(R.id.cv_berita_activity);
        }
    }
}
