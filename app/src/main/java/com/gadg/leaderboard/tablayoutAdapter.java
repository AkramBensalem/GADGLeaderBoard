package com.gadg.leaderboard;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.gadg.leaderboard.leaders.LearningFragment;
import com.gadg.leaderboard.skills.SkillIqFragment;

public class tablayoutAdapter extends FragmentPagerAdapter {
    // The number of the fragments is just 2
    private static final int NUMBER_OF_FRAGMENTS = 2;
    private Context context;

    public tablayoutAdapter(Context context, @NonNull FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new LearningFragment();
            case 1:
                return new SkillIqFragment();
            default: return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        // title each tab item by t namme
        switch (position){
            case 0: return context.getString(R.string.learning_leaders);
            case 1: return context.getString(R.string.skills_iq_leaders);
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return NUMBER_OF_FRAGMENTS;
    }
}
