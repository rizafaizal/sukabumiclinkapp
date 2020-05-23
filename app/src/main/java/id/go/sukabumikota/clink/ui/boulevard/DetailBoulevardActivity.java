package id.go.sukabumikota.clink.ui.boulevard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.PointF;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import id.go.sukabumikota.clink.R;
import id.go.sukabumikota.clink.api.koneksi;
import id.go.sukabumikota.clink.model.boulevard.AllResponseBoulevard;
import jp.wasabeef.glide.transformations.gpu.VignetteFilterTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class DetailBoulevardActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageView img_boulevard_detail;
    TextView tv_jalan_boulevard;

    public static final String EXTRA_DATA= "extra_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_boulevard);

        toolbar = findViewById(R.id.toolbar_boulevard_detail);
        img_boulevard_detail = findViewById(R.id.img_boulevard_detail);
        tv_jalan_boulevard = findViewById(R.id.tv_title_boulevard_detail);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        AllResponseBoulevard allResponseBoulevard = getIntent().getParcelableExtra(EXTRA_DATA);
        assert allResponseBoulevard != null;
        getSupportActionBar().setTitle(allResponseBoulevard.getTm_boulevard_jalan());

        Glide.with(getApplicationContext())
                .load(koneksi.BASE_URL_API
                        + "storage/images/boulevard/"
                        + allResponseBoulevard.getTm_boulevard_cover_nama())
//                .apply(bitmapTransform(new VignetteFilterTransformation(new PointF(0.5f, 0.5f),
//                        new float[]{0.0f, 0.0f, 0.0f}, 0.0f, 0.75f)).dontAnimate())
                .into(img_boulevard_detail);

        tv_jalan_boulevard.setText(allResponseBoulevard.getTm_boulevard_jalan());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
