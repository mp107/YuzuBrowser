/*
 * Copyright (C) 2017 Hazuki
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jp.hazuki.yuzubrowser.adblock;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import jp.hazuki.yuzubrowser.R;
import jp.hazuki.yuzubrowser.adblock.fragment.AdBlockFragment;
import jp.hazuki.yuzubrowser.adblock.fragment.AdBlockImportFragment;
import jp.hazuki.yuzubrowser.adblock.fragment.AdBlockMainFragment;

public class AdBlockActivity extends AppCompatActivity implements AdBlockMainFragment.OnAdBlockMainListener, AdBlockFragment.AdBlockFragmentListener, AdBlockImportFragment.OnImportListener {
    private static final String TAG_LIST = "list";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_base);

        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new AdBlockMainFragment())
                    .commit();
    }

    @Override
    public void openBlackList() {
        openList(AdBlockManager.TYPE_BLACK_TABLE);
    }

    @Override
    public void openWhiteList() {
        openList(AdBlockManager.TYPE_WHITE_TABLE);
    }

    @Override
    public void openWhitePageList() {
        openList(AdBlockManager.TYPE_WHITE_PAGE_TABLE);
    }

    private void openList(int type) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, AdBlockFragment.newInstance(type), TAG_LIST)
                .addToBackStack("type:" + type)
                .commit();
    }

    @Override
    public void onImport(List<AdBlock> adBlocks) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG_LIST);
        if (fragment instanceof AdBlockFragment) {
            ((AdBlockFragment) fragment).addAll(adBlocks);
        }
    }


    @Override
    public void setFragmentTitle(int type) {
        switch (type) {
            case AdBlockManager.TYPE_BLACK_TABLE:
                setTitle(R.string.pref_ad_block_black);
                break;
            case AdBlockManager.TYPE_WHITE_TABLE:
                setTitle(R.string.pref_ad_block_white);
                break;
            case AdBlockManager.TYPE_WHITE_PAGE_TABLE:
                setTitle(R.string.pref_ad_block_white_page);
                break;
        }
    }

    @Override
    public void requestImport(Uri uri) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, AdBlockImportFragment.newInstance(uri))
                .addToBackStack("")
                .commit();
    }
}
