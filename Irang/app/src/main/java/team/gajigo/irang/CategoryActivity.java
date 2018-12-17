package team.gajigo.irang;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class CategoryActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        Intent intent = getIntent();
        if (intent.getStringExtra("category").equals("f")) {
            Button btn1 = (Button) findViewById(R.id.nowon);
            btn1.setOnClickListener(
                    new Button.OnClickListener() {
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                            intent.putExtra("category", "1");
                            startActivity(intent);
                        }
                    }
            );

            Button btn2 = (Button) findViewById(R.id.mapo);
            btn2.setOnClickListener(
                    new Button.OnClickListener() {
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), MapsActivity2.class);
                            intent.putExtra("category", "1");
                            startActivity(intent);
                        }
                    }
            );

            Button btn3 = (Button) findViewById(R.id.eunp);
            btn3.setOnClickListener(
                    new Button.OnClickListener() {
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), MapsActivity3.class);
                            intent.putExtra("category", "1");
                            startActivity(intent);
                        }
                    }
            );

            Button btn4 = (Button) findViewById(R.id.onnuri);
            btn4.setOnClickListener(
                    new Button.OnClickListener() {
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), MapsActivity4.class);
                            intent.putExtra("category", "1");
                            startActivity(intent);
                        }
                    }
            );
        } else if (intent.getStringExtra("category").equals("l")) {
            Button btn1 = (Button) findViewById(R.id.nowon);
            btn1.setOnClickListener(
                    new Button.OnClickListener() {
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                            intent.putExtra("category", "2");
                            startActivity(intent);
                        }
                    }
            );

            Button btn2 = (Button) findViewById(R.id.mapo);
            btn2.setOnClickListener(
                    new Button.OnClickListener() {
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), MapsActivity2.class);
                            intent.putExtra("category", "2");
                            startActivity(intent);
                        }
                    }
            );

            Button btn3 = (Button) findViewById(R.id.eunp);
            btn3.setOnClickListener(
                    new Button.OnClickListener() {
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), MapsActivity3.class);
                            intent.putExtra("category", "2");
                            startActivity(intent);
                        }
                    }
            );

            Button btn4 = (Button) findViewById(R.id.onnuri);
            btn4.setOnClickListener(
                    new Button.OnClickListener() {
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), MapsActivity4.class);
                            intent.putExtra("category", "2");
                            startActivity(intent);
                        }
                    }
            );
        } else if (intent.getStringExtra("category").equals("e")) {
            Button btn1 = (Button) findViewById(R.id.nowon);
            btn1.setOnClickListener(
                    new Button.OnClickListener() {
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                            intent.putExtra("category", "3");
                            startActivity(intent);
                        }
                    }
            );

            Button btn2 = (Button) findViewById(R.id.mapo);
            btn2.setOnClickListener(
                    new Button.OnClickListener() {
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), MapsActivity2.class);
                            intent.putExtra("category", "3");
                            startActivity(intent);
                        }
                    }
            );

            Button btn3 = (Button) findViewById(R.id.eunp);
            btn3.setOnClickListener(
                    new Button.OnClickListener() {
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), MapsActivity3.class);
                            intent.putExtra("category", "3");
                            startActivity(intent);
                        }
                    }
            );

            Button btn4 = (Button) findViewById(R.id.onnuri);
            btn4.setOnClickListener(
                    new Button.OnClickListener() {
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), MapsActivity4.class);
                            intent.putExtra("category", "3");
                            startActivity(intent);
                        }
                    }
            );
        }


        Button btn6 = (Button) findViewById(R.id.home);
        btn6.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }
        );


    }
}
