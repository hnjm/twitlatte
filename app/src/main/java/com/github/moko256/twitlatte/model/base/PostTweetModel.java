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

package com.github.moko256.twitlatte.model.base;

import android.net.Uri;

import java.util.List;

import io.reactivex.Completable;
import twitter4j.GeoLocation;

/**
 * Created by moko256 on 2017/07/22.
 *
 * @author moko256
 */

public interface PostTweetModel {

    long getInReplyToStatusId();
    void setInReplyToStatusId(long inReplyToStatusId);

    boolean isPossiblySensitive();
    void setPossiblySensitive(boolean possiblySensitive);

    String getTweetText();
    void setTweetText(String tweetText);

    String getContentWarning();
    void setContentWarning(String contentWarning);

    int getTweetLength();
    int getMaxTweetLength();

    boolean isReply();
    boolean isValidTweet();

    List<Uri> getUriList();
    int getUriListSizeLimit();

    GeoLocation getLocation();
    void setLocation(GeoLocation location);

    String getVisibility();
    void setVisibility(String visibility);

    Completable postTweet();
}
