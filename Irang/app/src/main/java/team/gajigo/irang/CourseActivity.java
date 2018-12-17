package team.gajigo.irang;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class CourseActivity extends AppCompatActivity {

    private TabLayout coursetabLayout;
    private ViewPager courseviewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        Button btn1 = (Button) findViewById(R.id.home);
        btn1.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }
        );

        // Initializing the TabLayout
        coursetabLayout = (TabLayout) findViewById(R.id.coursetabLayout);

        coursetabLayout.addTab(coursetabLayout.newTab().setText("노원"));
        coursetabLayout.addTab(coursetabLayout.newTab().setText("마포"));
        coursetabLayout.addTab(coursetabLayout.newTab().setText("은평"));
        coursetabLayout.addTab(coursetabLayout.newTab().setText("온누리"));
        coursetabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // Initializing ViewPager
        courseviewPager = (ViewPager) findViewById(R.id.coursepager);

        // Creating TabPagerAdapter adapter
        CourseTabPagerAdapter courseTabPagerAdapter = new CourseTabPagerAdapter(getSupportFragmentManager(),coursetabLayout.getTabCount());
        courseviewPager.setAdapter(courseTabPagerAdapter);
        courseviewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(coursetabLayout));


        // Set TabSelectedListener
        coursetabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                courseviewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
}