package team.gajigo.irang;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import team.gajigo.irang.information.SubActivity;

public class MainActivity extends Activity {

    AutoScrollViewPager autoViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        ArrayList<Integer> data = new ArrayList<>();
        data.add(R.drawable.info_img2);
        data.add(R.drawable.info_img1);
        data.add(R.drawable.info_img3);

        autoViewPager = (AutoScrollViewPager)findViewById(R.id.info);
        AutoScrollAdapter scrollAdapter = new AutoScrollAdapter(this, data);
        autoViewPager.setAdapter(scrollAdapter); //Auto Viewpager에 Adapter 장착
        autoViewPager.setInterval(3000); // 페이지 넘어갈 시간 간격 설정
        autoViewPager.startAutoScroll(); //Auto Scroll 시작



        Button btn = (Button)findViewById(R.id.infobutton);
        btn.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent = new Intent(getApplicationContext(),SubActivity.class);
                        intent.putExtra("sub","s");
                        startActivity(intent);
                    }
                }
        );

        Button btn1 = (Button) findViewById(R.id.fun);
        btn1.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent = new Intent(getApplicationContext(),CategoryActivity.class);
                        intent.putExtra("category", "f");
                        startActivity(intent);
                    }
                }
        );

        Button btn2 = (Button) findViewById(R.id.learn);
        btn2.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent = new Intent(getApplicationContext(),CategoryActivity.class);
                        intent.putExtra("category", "l");
                        startActivity(intent);
                    }
                }
        );

        Button btn3 = (Button) findViewById(R.id.eat);
        btn3.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent = new Intent(getApplicationContext(),CategoryActivity.class);
                        intent.putExtra("category", "e");
                        startActivity(intent);
                    }
                }
        );

        Button btn4 = (Button) findViewById(R.id.course);
        btn4.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent = new Intent(getApplicationContext(),CourseActivity.class);
                        startActivity(intent);
                    }
                }
        );

        Button btn5 = (Button) findViewById(R.id.home);
        btn5.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }
        );

    }



}
