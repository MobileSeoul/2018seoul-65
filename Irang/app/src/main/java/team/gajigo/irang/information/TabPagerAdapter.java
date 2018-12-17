package team.gajigo.irang.information;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private int tabCount;

    public TabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                TabFragment01 tabFragment1 = new TabFragment01();
                return tabFragment1;

            case 1:
                TabFragment02 tabFragment2 = new TabFragment02();
                return tabFragment2;

            case 2:
                TabFragment03 tabFragment3 = new TabFragment03();
                return tabFragment3;

            case 3:
                TabFragment04 tabFragment4 = new TabFragment04();
                return tabFragment4;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}

