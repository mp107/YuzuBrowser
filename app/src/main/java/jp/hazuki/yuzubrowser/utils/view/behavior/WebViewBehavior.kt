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

package jp.hazuki.yuzubrowser.utils.view.behavior

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.View

import jp.hazuki.yuzubrowser.webkit.CustomWebView

class WebViewBehavior(context: Context, attrs: AttributeSet) : AppBarLayout.ScrollingViewBehavior(context, attrs) {

    private var webView: CustomWebView? = null
    private var prevY: Int = 0

    override fun onDependentViewChanged(parent: CoordinatorLayout?, child: View?, dependency: View): Boolean {
        val bottom = dependency.bottom

        if (webView != null && !webView!!.isTouching) {
            webView!!.scrollBy(0, bottom - prevY)
            if (bottom == 0) {
                webView!!.setSwipeable(false)
            }
        }

        prevY = bottom

        //Disable due to freeze webView
        //        int toolbarHeight = topToolBar.getHeight();

        //        if (toolbarHeight != 0 && dependency.getHeight() == dependency.getBottom()) {
        //            webFrame.setPadding(0, 0, 0, toolbarHeight);
        //        } else {
        //            webFrame.setPadding(0, 0, 0, 0);
        //        }

        return super.onDependentViewChanged(parent, child, dependency)
    }

    fun setWebView(webView: CustomWebView?) {
        this.webView = webView
    }
}
