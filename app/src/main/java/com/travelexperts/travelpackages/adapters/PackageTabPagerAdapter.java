package com.travelexperts.travelpackages.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.travelexperts.travelpackages.fragments.NewPackagesFragment;
import com.travelexperts.travelpackages.fragments.PopularPackagesFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 468364 on 4/5/2017.
 */

public class PackageTabPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments = new ArrayList<>(Arrays.asList(new PopularPackagesFragment(), new NewPackagesFragment(), new PopularPackagesFragment()));

    private List<String> mTitles = new ArrayList<>(Arrays.asList("Popular", "New", "All"));

    public PackageTabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
