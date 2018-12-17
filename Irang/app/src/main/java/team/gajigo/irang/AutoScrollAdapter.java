package team.gajigo.irang;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class AutoScrollAdapter extends PagerAdapter {
    Context context;
    ArrayList<Integer> data;
    private PagerAdapter adapter;

    public AutoScrollAdapter(Context context, ArrayList<Integer> data) {
        this.context = context;
        this.data = data;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        //뷰페이지 슬라이딩 레이아웃 인플레이션
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.auto_viewpager,null);
        ImageView image_container = (ImageView) v.findViewById(R.id.image_container);
        Glide.with(context).load(data.get(position)).into(image_container);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

         container.removeView((View)object);

    }

    @Override
    public int getCount() {
        return data.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    
}