/*
 * Copyright 2015-2018 The twitlatte authors
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

package com.github.moko256.twitlatte.intent

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.github.moko256.twitlatte.GlobalApplication
import com.github.moko256.twitlatte.R

/**
 * Created by moko256 on 2018/01/21.
 *
 * @author moko256
 */

/**
 * Launch URL from context with Chrome Custom Tabs
 *
 * @param context context that launch uri
 * @param uri uri string
 */
fun launchChromeCustomTabs(context: Context, uri: String){
    val url = Uri.parse(uri)

    try {
        if (GlobalApplication.preferenceRepository.getBoolean(GlobalApplication.KEY_USE_CHROME_CUSTOM_TAB, true)) {
            CustomTabsIntent.Builder()
                    .setToolbarColor(ContextCompat.getColor(context, R.color.color_primary))
                    .setSecondaryToolbarColor(ContextCompat.getColor(context, R.color.color_primary_dark))
                    .setStartAnimations(context, R.anim.custom_tabs_slide_in_right, R.anim.custom_tabs_slide_out_left)
                    .setExitAnimations(context, R.anim.custom_tabs_slide_in_left, R.anim.custom_tabs_slide_out_right)
                    .addDefaultShareMenuItem()
                    .build()
                    .launchUrl(context, url)
        } else {
            context.startActivity(Intent(Intent.ACTION_VIEW, url))
        }
    } catch (e: ActivityNotFoundException) {
        e.printStackTrace()
        Toast.makeText(context, R.string.no_app_can_open, Toast.LENGTH_SHORT).show()
    }
}