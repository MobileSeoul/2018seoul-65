package team.gajigo.irang;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class MyItem implements ClusterItem {

    private final LatLng mPosition;
    private String sname;       ///추가: final 삭제
    private String saddress;
    private String stel;
    private String scatg;
    private String img;

    public MyItem(double lat, double lng, String sname, String saddress, String stel, String scatg, String img){
        this.sname = sname;
        this.saddress = saddress;
        this.stel = stel;
        this.scatg = scatg;
        this.img = img;
        this.mPosition = new LatLng(lat, lng);

    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    @Override
    public String getTitle() {
        return sname;
    }

    @Override
    public String getSnippet() {
        return saddress + "\n" + stel + "\n" + scatg;
    }

    ///추가
    public String getSname(){
        this.sname = sname;
        return sname;
    }

    public String getSaddress(){
        this.saddress = saddress;
        return saddress;

    }

    public String getStel(){
        this.stel = stel;
        return stel;

    }

    public String getScatg(){
        this.scatg = scatg;
        return scatg;
    }

    public String getImg(){
        this.img = img;
        return img;
    }
}

