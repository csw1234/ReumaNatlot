/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alonz.reumanatlot.Natlot;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.alonz.reumanatlot.Natlot.NatlotFragment;


/**
 * Provides the appropriate {@link Fragment} for a view pager.
 */
public class SimpleFragmentPagerAdapter extends FragmentStatePagerAdapter {
    private Context mContext;
    public SimpleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        Log.e("detach", "simplefragmentadapter");

    }
    public SimpleFragmentPagerAdapter(Context context, FragmentManager fm){super(fm); mContext=context;

    }

    @Override
    public Fragment getItem(int position) {
        Bundle args = new Bundle();
        args.putInt("mPosition",position);
        NatlotFragment NatlotFragment =new NatlotFragment();
        Log.e("detach", "natlotfragment create");
        NatlotFragment.setArguments(args);

   return NatlotFragment;
        }

    @Override
    public int getCount() {
        return 9;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return ("אדום");
            case 1:
                return ("טורקיז");
            case 2:
                return ("סגול");
            case 3:
                return ("כתום");
            case 4:
                return ("לבן");
            case 5:
                return ("טורקיז בהיר");
            case 6:
                return ("צהוב");
            case 7:
                return ("כחול בהיר");
            default:
                return ("ירוק בהיר");
        }
    }




}




