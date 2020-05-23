package id.go.sukabumikota.clink.adapter.education;

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
import id.go.sukabumikota.clink.model.education.AllResponseEducation;
import id.go.sukabumikota.clink.ui.education.DetailEducationActivity;
import jp.wasabeef.glide.transformations.gpu.VignetteFilterTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class AdapterEducationDash extends RecyclerView.Adapter<AdapterEducationDash.AdapterEducationHolder> {

    List<AllResponseEducation> allResponseEducationList;
    Context mContext;

    String isi;

    public AdapterEducationDash(Context mContext, List<AllResponseEducation> allResponseEducations) {
        this.mContext = mContext;
        allResponseEducationList = allResponseEducations;
    }


    @NonNull
    @Override
    public AdapterEducationDash.AdapterEducationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_educationdash, parent, false);
        return new AdapterEducationHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterEducationHolder holder, int position) {
        final AllResponseEducation allResponseEducation = allResponseEducationList.get(position);

        isi = allResponseEducation.getTm_edukasi_konten();

        holder.tv_title.setText(allResponseEducation.getTm_edukasi_judul());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.tv_isi.setText(Html.fromHtml(isi, Html.FROM_HTML_MODE_LEGACY));
        } else {
            holder.tv_isi.setText(isi);
        }

        Glide.with(holder.itemView.getContext())
                .load(koneksi.BASE_URL_API
                        + "storage/images/education/"
                        + allResponseEducation.getTm_edukasi_cover_nama())
                .apply(bitmapTransform(new VignetteFilterTransformation(new PointF(0.5f, 0.5f),
                        new float[]{0.0f, 0.0f, 0.0f}, 0.4f, 1.0f)).dontAnimate())
                .into(holder.img_educationdash);

        holder.cv_edeucationdash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToDetail = new Intent(mContext, DetailEducationActivity.class);
                moveToDetail.putExtra(DetailEducationActivity.EXTRA_DATA, allResponseEducation);
                mContext.startActivity(moveToDetail);
            }
        });

        holder.view_bg_educationdash.setBackgroundColor(getColorWithAlpha(Color.BLACK, 0.6f));
    }

    @Override
    public int getItemCount() {
        return allResponseEducationList.size();
    }

    public class AdapterEducationHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_isi;
        ImageView img_educationdash;
        LinearLayout view_bg_educationdash;
        CardView cv_edeucationdash;

        public AdapterEducationHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.tv_title_education_dash);
            tv_isi = itemView.findViewById(R.id.tv_isi_education_dash);
            img_educationdash = itemView.findViewById(R.id.img_education_dash);
            view_bg_educationdash = itemView.findViewById(R.id.view_bg_education_dash);
            cv_edeucationdash = itemView.findViewById(R.id.cv_educationdash);
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
