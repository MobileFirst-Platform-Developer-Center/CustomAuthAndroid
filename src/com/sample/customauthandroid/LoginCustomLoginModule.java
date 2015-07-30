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


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginCustomLoginModule extends Activity {

	private EditText userNameEditText, passwordEditText;
	private Button loginBtn, backBtn;
	private Intent result;

	public static final String Back = "back";
	public static final String UserNameExtra = "username";
	public static final String PasswordExtra = "password";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		getActionBar().setTitle("Custom Authentication");

		userNameEditText = (EditText) findViewById(R.id.enterName);
		passwordEditText = (EditText) findViewById(R.id.enterPass);

		loginBtn = (Button) findViewById(R.id.login);
		backBtn = (Button) findViewById(R.id.back);

		result = new Intent();

		//ClickListener
		loginBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String userName = userNameEditText.getText().toString();
				String password = passwordEditText.getText().toString();

				result.putExtra(UserNameExtra, userName);
				result.putExtra(PasswordExtra, password);
				result.putExtra(Back, false);
				setResult(RESULT_OK, result);
				finish();
			}
		});

		backBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				result.putExtra(Back, true);
				setResult(RESULT_OK, result);
				finish();
			}
		});
	}

	@Override
    public void onBackPressed() {
		result.putExtra(Back, true);
		setResult(RESULT_OK, result);
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
