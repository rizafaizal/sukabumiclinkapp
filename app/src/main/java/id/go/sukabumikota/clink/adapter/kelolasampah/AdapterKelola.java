package id.go.sukabumikota.clink.adapter.kelolasampah;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import id.go.sukabumikota.clink.R;
import id.go.sukabumikota.clink.api.koneksi;
import id.go.sukabumikota.clink.model.ccraft.AllResponseCCraft;
import id.go.sukabumikota.clink.ui.creative.DetailCCraftActivity;
import jp.wasabeef.glide.transformations.gpu.VignetteFilterTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class AdapterKelola extends RecyclerView.Adapter<AdapterKelola.AdapterKelolasHolder> {

    List<AllResponseCCraft> allResponseCCraftslist;
    Context mContext;

    public AdapterKelola(Context mContext, List<AllResponseCCraft> allResponseCCrafts) {
        this.mContext = mContext;
        allResponseCCraftslist = allResponseCCrafts;
    }


    @NonNull
    @Override
    public AdapterKelola.AdapterKelolasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_kelola, parent, false);
        return new AdapterKelolasHolder(itemview);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AdapterKelola.AdapterKelolasHolder holder, int position) {
        final AllResponseCCraft allResponseCCraft = allResponseCCraftslist.get(position);

        holder.tv_jenis.setText(allResponseCCraft.getTm_kategori_souvenir_nama());
        holder.tv_oleh.setText("Dari : "+allResponseCCraft.getTm_kelola_sampah_nama());
        holder.tv_alamat.setText("Alamat : "+allResponseCCraft.getTm_kelola_sampah_alamatlengkap());

        Glide.with(holder.itemView.getContext())
                .load(koneksi.BASE_URL_API
                        + "storage/images/creativecraft/"
                        + allResponseCCraft.getTm_kelola_sampah_cover_nama())
//                .apply(bitmapTransform(new VignetteFilterTransformation(new PointF(0.5f, 0.5f),
//                        new float[]{0.0f, 0.0f, 0.0f}, 0.4f, 1.0f)).dontAnimate())
                .into(holder.img_kelola);

        holder.cv_kelola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToDetail = new Intent(mContext, DetailCCraftActivity.class);
                moveToDetail.putExtra(DetailCCraftActivity.EXTRA_DATA, allResponseCCraft);
                mContext.startActivity(moveToDetail);
            }
        });

        holder.img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Edit", Toast.LENGTH_SHORT).show();
            }
        });

        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Delete", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return allResponseCCraftslist.size();
    }

    public class AdapterKelolasHolder extends RecyclerView.ViewHolder {
        TextView tv_jenis, tv_oleh, tv_alamat;
        ImageView img_kelola, img_edit, img_delete;
        CardView cv_kelola;

        public AdapterKelolasHolder(@NonNull View itemView) {
            super(itemView);

            tv_jenis = itemView.findViewById(R.id.tv_jenis_kelola);
            tv_oleh = itemView.findViewById(R.id.tv_oleh_kelola);
            tv_alamat = itemView.findViewById(R.id.tv_alamat_kelola);
            img_kelola = itemView.findViewById(R.id.img_kelola);
            img_edit = itemView.findViewById(R.id.img_edit_kelola);
            img_delete = itemView.findViewById(R.id.img_delete_kelola);
            cv_kelola = itemView.findViewById(R.id.cv_kelola_sampah_activity);
        }
    }
}
