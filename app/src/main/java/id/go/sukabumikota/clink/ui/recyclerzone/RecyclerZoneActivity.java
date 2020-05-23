package id.go.sukabumikota.clink.ui.recyclerzone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import id.go.sukabumikota.clink.R;
import id.go.sukabumikota.clink.pager.PagerAdapterRcZone;

public class RecyclerZoneActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_zone);

        inisialisasi();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.menu_recycler_zone));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        PagerAdapterRcZone sectionsPagerAdapter = new PagerAdapterRcZone(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.vpgr_rcZone);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tab_rcZone);
        tabs.setupWithViewPager(viewPager);
    }

    private void inisialisasi() {
        toolbar = findViewById(R.id.toolbar_recyclerzone);
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
