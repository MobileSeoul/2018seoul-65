package team.gajigo.irang;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class CourseTabPagerAdapter extends FragmentStatePagerAdapter {

    private int tabCount;

    public CourseTabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                CourseTabFragment01 coursetabFragment1 = new CourseTabFragment01();
                return coursetabFragment1;

            case 1:
                CourseTabFragment02 coursetabFragment2 = new CourseTabFragment02();
                return coursetabFragment2;

            case 2:
                CourseTabFragment03 coursetabFragment3 = new CourseTabFragment03();
                return coursetabFragment3;

            case 3:
                CourseTabFragment04 coursetabFragment4 = new CourseTabFragment04();
                return coursetabFragment4;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
