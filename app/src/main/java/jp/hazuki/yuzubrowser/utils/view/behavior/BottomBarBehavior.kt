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
import android.widget.LinearLayout

import jp.hazuki.yuzubrowser.R


class BottomBarBehavior(context: Context, attrs: AttributeSet) : CoordinatorLayout.Behavior<LinearLayout>(context, attrs) {

    private lateinit var topToolbar: View
    private lateinit var bottomToolbar: View

    override fun layoutDependsOn(parent: CoordinatorLayout?, bottomBar: LinearLayout?, dependency: View?): Boolean {
        if (dependency is AppBarLayout) {
            topToolbar = dependency.findViewById(R.id.topToolbarLayout)
            bottomToolbar = bottomBar!!.findViewById(R.id.bottomToolbarLayout)
            return true
        }
        return false
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout?, bottomBar: LinearLayout, dependency: View?): Boolean {
        val bottomBarHeight = bottomToolbar.height

        if (topToolbar.height != 0) {
            val height = -dependency!!.top * bottomBarHeight / dependency.height

            bottomBar.translationY = Math.min(height, bottomBarHeight).toFloat()
        }
        return true
    }
}
