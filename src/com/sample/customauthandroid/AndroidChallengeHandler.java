/**
* Copyright 2015 IBM Corp.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.sample.customauthandroid;

import java.util.HashMap;

import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;

import com.worklight.wlclient.api.WLFailResponse;
import com.worklight.wlclient.api.WLResponse;
import com.worklight.wlclient.api.challengehandler.ChallengeHandler;

public class AndroidChallengeHandler extends ChallengeHandler{
	private Activity parentActivity;
	private WLResponse cachedResponse;


	public AndroidChallengeHandler(Activity activity, String realm) {
		super(realm);
		parentActivity = activity;
	}

	@Override
	public void onFailure(WLFailResponse response) {
		submitFailure(response);
	}

	@Override
	public void onSuccess(WLResponse response) {
		submitSuccess(response);
	}

	@Override
	public boolean isCustomResponse(WLResponse response) {
		if (response == null || response.getResponseJSON() == null) {
			return false;
		}
		if(response.toString().indexOf("authStatus") > -1){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public void handleChallenge(WLResponse response){
		try {
			if(response.getResponseJSON().getString("authStatus") == "complete"){
				submitSuccess(response);
			}
			else {
				cachedResponse = response;
				Intent login = new Intent(parentActivity, LoginCustomLoginModule.class);
				parentActivity.startActivityForResult(login, 1);
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void submitLogin(int resultCode, String userName, String password, boolean back){
		if (resultCode != Activity.RESULT_OK || back) {
			submitFailure(cachedResponse);
		} else {
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("username", userName);
			params.put("password", password);
			submitLoginForm("/my_custom_auth_request_url", params, null, 0, "post");
		}
	}




}
