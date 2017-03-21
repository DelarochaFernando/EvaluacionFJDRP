package com.example.jmata.evaluacionfjdrp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.jmata.evaluacionfjdrp.imagesFragment;
import com.example.jmata.evaluacionfjdrp.priceFragment;


/**
 * Created by jmata on 17/03/2017.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new imagesFragment();
            case 1:
                return new priceFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0:
                return "Images";
            case 1:
                return "Prices";
        }
        return super.getPageTitle(position);
    }
}
