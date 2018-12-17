package team.gajigo.irang;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.maps.android.clustering.ClusterManager;

import java.io.InputStream;

import jxl.Sheet;
import jxl.Workbook;

public class MapsActivity3 extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnMapClickListener  {

    private GoogleMap mMap;
    private DBAdapter dbAdapter;    //추가
    // 권한 체크 요청 코드 정의
    public static final int REQUEST_CODE_PERMISSIONS = 1000;
    // GoogleMap 실행 정의
    private GoogleApiClient mGoogleApiClient;
    // 위치 정보 얻는 객체
    private FusedLocationProviderClient mFusedLocationClient;
    private ClusterManager<MyItem> mClusterManager;

    ///추가
    private TextView map_sname;
    private TextView map_saddress;
    private TextView map_stel;
    private TextView map_scatg;
    private ImageView map_marketImg;
    private LinearLayout map_layout;

    ///추가

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(Color.parseColor("#ff6e63"));
        }
        setContentView(R.layout.map_layout);    ///추가: activity_main에서 수정

        map_layout= (LinearLayout) findViewById(R.id.map_layout);
        map_marketImg = (ImageView) findViewById(R.id.map_marketImg);
        map_sname = (TextView) findViewById(R.id.map_sname);
        map_saddress = (TextView) findViewById(R.id.map_saddress);
        map_stel = (TextView) findViewById(R.id.map_stel);
        map_scatg = (TextView) findViewById(R.id.map_scatg);

        this.dbAdapter = new DBAdapter(this);       //추가
        dbAdapter.reset();

        // GoogleAPIClient의 인스턴스 생성
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        map_layout.setVisibility(View.GONE);
    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng eunpyeong = new LatLng(37.602783, 126.929237);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(eunpyeong));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));

        this.dbAdapter = new DBAdapter(this);
        copyExcelDataToDatabase();

        // 권한 체크
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE_PERMISSIONS);
            return;
        }

        mClusterManager = new ClusterManager<MyItem>(this, mMap);
        mMap.setOnCameraIdleListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);
        mMap.setMyLocationEnabled(true);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                map_layout.setVisibility(View.GONE);
            }
        });

        mClusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<MyItem>() {

            @Override
            public boolean onClusterItemClick(MyItem myItem) {

                String temp = myItem.getImg();
                int marketImg = getResources().getIdentifier(temp, "drawable", getPackageName());

                Glide.with(MapsActivity3.this).load(marketImg).thumbnail(01.f).fitCenter().into(map_marketImg);

                map_sname.setText(myItem.getSname());
                map_saddress.setText("주소: " + myItem.getSaddress());
                map_stel.setText("전화번호: " + myItem.getStel());
                map_scatg.setText("카테고리: " + myItem.getScatg());

                map_layout.setVisibility(View.VISIBLE);

                CustomInfoWindowAdapter adapter = new CustomInfoWindowAdapter(MapsActivity3.this);
                mMap.setInfoWindowAdapter(adapter);

                MarkerOptions markerOptions = new MarkerOptions()
                        .position(myItem.getPosition())
                        .title(myItem.getTitle());

                mMap.addMarker(markerOptions).showInfoWindow();
                return true;
            }
        });
        addMarkers();

    }

    @Override
    public void onMapClick(LatLng latLng) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMap.clear();
    }

    //GPS 권한설정
    @Override
    public void onRequestPermissionsResult
    (int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_CODE_PERMISSIONS:
                if (ActivityCompat.checkSelfPermission
                        (this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission
                        (this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "권한 체크 거부 됨", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
    }
    @Override
    public void onConnectionSuspended(int i) {
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    //마커 추가
    private void addMarkers() {

        Intent intent = getIntent();
        dbAdapter.open();
        Cursor result = dbAdapter.fetchAllNotes(intent.getStringExtra("category"));
        result.moveToFirst();
        while (!result.isAfterLast()) {
            String sname = result.getString(4);
            String saddress = result.getString(5);
            String stel = result.getString(6);
            String scatg = result.getString(7);
            String slat = result.getString(8);
            String slong = result.getString(9);
            String img = result.getString(10);

            MyItem offsetItem = new MyItem(Double.parseDouble(slat), Double.parseDouble(slong), sname, saddress, stel, scatg, img);
            mClusterManager.addItem(offsetItem);

            result.moveToNext();
        }

        result.close();
        dbAdapter.close();
    }

    //엑셀 불러오기
    private void copyExcelDataToDatabase() {
        Log.w("ExcelToDatabase", "copyExcelDataToDatabase()");

        Workbook workbook = null;
        Sheet sheet = null;

        try {
            InputStream is = getBaseContext().getResources().getAssets().open("notes.xls");
            workbook = Workbook.getWorkbook(is);

            if (workbook != null) {
                sheet = workbook.getSheet(2);

                if (sheet != null) {

                    int nMaxColumn = 10;
                    int nRowStartIndex = 0;
                    int nRowEndIndex = sheet.getColumn(nMaxColumn - 1).length - 1;
                    int nColumnStartIndex = 0;
                    int nColumnEndIndex = sheet.getRow(10).length - 1;

                    dbAdapter.open();
                    for (int nRow = nRowStartIndex; nRow <= nRowEndIndex; nRow++) {
                        String tno = sheet.getCell(nColumnStartIndex, nRow).getContents();
                        String tname = sheet.getCell(nColumnStartIndex + 1, nRow).getContents();
                        String sno = sheet.getCell(nColumnStartIndex + 2, nRow).getContents();
                        String sname = sheet.getCell(nColumnStartIndex + 3, nRow).getContents();
                        String saddress = sheet.getCell(nColumnStartIndex + 4, nRow).getContents();
                        String stel = sheet.getCell(nColumnStartIndex + 5, nRow).getContents();
                        String scatg = sheet.getCell(nColumnStartIndex + 6, nRow).getContents();
                        String slat = sheet.getCell(nColumnStartIndex + 7, nRow).getContents();
                        String slong = sheet.getCell(nColumnStartIndex + 8, nRow).getContents();
                        String img = sheet.getCell(nColumnStartIndex + 9, nRow).getContents();

                        dbAdapter.createNote(tno, tname, sno, sname, saddress, stel, scatg,
                                slat, slong, img);
                    }
                    dbAdapter.close();
                } else {
                    System.out.println("Sheet is null!!");
                }
            } else {
                System.out.println("WorkBook is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
    }
}

