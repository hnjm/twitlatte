/*
 * Copyright 2016 The twicalico authors
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

package com.github.moko256.twicalico.model.impl.twitter

import com.github.moko256.twicalico.model.base.OAuthModel
import rx.Single
import twitter4j.TwitterException
import twitter4j.auth.AccessToken
import twitter4j.auth.OAuthAuthorization
import twitter4j.auth.RequestToken
import twitter4j.conf.ConfigurationContext

/**
 * Created by moko256 on 2017/10/29.
 *
 * @author moko256
 */
class OAuthModelImpl : OAuthModel {
    var req: RequestToken? = null

    override fun getCallbackAuthUrl(url: String, consumerKey: String, consumerSecret: String, callbackUrl: String): Single<String> {
        val conf = ConfigurationContext.getInstance()
        val oauth = OAuthAuthorization(conf)

        oauth.setOAuthConsumer(consumerKey, consumerSecret)

        return Single.create {
            try {
                req = oauth.getOAuthRequestToken(callbackUrl + "://OAuthActivity")
                it.onSuccess(req?.authorizationURL)
            } catch (e: TwitterException) {
                it.onError(e)
            }
        }
    }

    override fun getPinAuthUrl(url: String, consumerKey: String, consumerSecret: String): Single<String> {
        val conf = ConfigurationContext.getInstance()
        val oauth = OAuthAuthorization(conf)
        oauth.setOAuthConsumer(consumerKey, consumerSecret)

        return Single.create {
            try {
                req = oauth.getOAuthRequestToken("oob")
                it.onSuccess(req?.authorizationURL)
            } catch (e: TwitterException) {
                it.onError(e)
            }
        }
    }

    override fun initToken(pin: String): Single<AccessToken> {
        val oauth: OAuthAuthorization

        val conf = ConfigurationContext.getInstance()
        oauth = OAuthAuthorization(conf)

        return Single.create {
            try {
                it.onSuccess(oauth.getOAuthAccessToken(req, pin))
            } catch (e: TwitterException) {
                it.onError(e)
            }
        }
    }

}