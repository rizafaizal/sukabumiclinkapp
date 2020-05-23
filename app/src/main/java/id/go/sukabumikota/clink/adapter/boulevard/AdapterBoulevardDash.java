package id.go.sukabumikota.clink.adapter.boulevard;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PointF;
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
import id.go.sukabumikota.clink.model.boulevard.AllResponseBoulevard;
import id.go.sukabumikota.clink.ui.boulevard.DetailBoulevardActivity;
import id.go.sukabumikota.clink.ui.forimages.ImagesActivity;
import jp.wasabeef.glide.transformations.gpu.VignetteFilterTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class AdapterBoulevardDash extends RecyclerView.Adapter<AdapterBoulevardDash.AdapterBoulevardDashHolder> {

    List<AllResponseBoulevard> allResponseBoulevardList;
    Context mContext;

    String isi;

    public AdapterBoulevardDash(Context mContext, List<AllResponseBoulevard> allResponseBoulevards) {
        this.mContext = mContext;
        allResponseBoulevardList = allResponseBoulevards;
    }


    @NonNull
    @Override
    public AdapterBoulevardDash.AdapterBoulevardDashHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_boulevard, parent, false);
        return new AdapterBoulevardDashHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBoulevardDash.AdapterBoulevardDashHolder holder, int position) {
        final AllResponseBoulevard allResponseBoulevard = allResponseBoulevardList.get(position);

        holder.tv_title.setText(allResponseBoulevard.getTm_boulevard_jalan());

        Glide.with(holder.itemView.getContext())
                .load(koneksi.BASE_URL_API
                        + "storage/images/boulevard/"
                        + allResponseBoulevard.getTm_boulevard_cover_nama())
                .apply(bitmapTransform(new VignetteFilterTransformation(new PointF(0.5f, 0.5f),
                        new float[]{0.0f, 0.0f, 0.0f}, 0.4f, 1.0f)).dontAnimate())
                .into(holder.img_boulevard_dash);

        holder.cv_boulevarddash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToDetail = new Intent(mContext, DetailBoulevardActivity.class);
                moveToDetail.putExtra(DetailBoulevardActivity.EXTRA_DATA, allResponseBoulevard);
                mContext.startActivity(moveToDetail);
            }
        });

        holder.view_bg_boulevard_dash.setBackgroundColor(getColorWithAlpha(Color.BLACK, 0.6f));
    }

    @Override
    public int getItemCount() {
        return allResponseBoulevardList.size();
    }

    public class AdapterBoulevardDashHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        ImageView img_boulevard_dash;
        LinearLayout view_bg_boulevard_dash;
        CardView cv_boulevarddash;

        public AdapterBoulevardDashHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.tv_title_boulevard_dash);
            img_boulevard_dash = itemView.findViewById(R.id.img_boulevard_dash);
            view_bg_boulevard_dash = itemView.findViewById(R.id.view_bg_boulevard_dash);
            cv_boulevarddash = itemView.findViewById(R.id.cv_boulevarddash);
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
