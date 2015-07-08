/*
 *
    COPYRIGHT LICENSE: This information contains sample code provided in source code form. You may copy, modify, and distribute
    these sample programs in any form without payment to IBM® for the purposes of developing, using, marketing or distributing
    application programs conforming to the application programming interface for the operating platform for which the sample code is written.
    Notwithstanding anything to the contrary, IBM PROVIDES THE SAMPLE SOURCE CODE ON AN "AS IS" BASIS AND IBM DISCLAIMS ALL WARRANTIES,
    EXPRESS OR IMPLIED, INCLUDING, BUT NOT LIMITED TO, ANY IMPLIED WARRANTIES OR CONDITIONS OF MERCHANTABILITY, SATISFACTORY QUALITY,
    FITNESS FOR A PARTICULAR PURPOSE, TITLE, AND ANY WARRANTY OR CONDITION OF NON-INFRINGEMENT. IBM SHALL NOT BE LIABLE FOR ANY DIRECT,
    INDIRECT, INCIDENTAL, SPECIAL OR CONSEQUENTIAL DAMAGES ARISING OUT OF THE USE OR OPERATION OF THE SAMPLE SOURCE CODE.
    IBM HAS NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS OR MODIFICATIONS TO THE SAMPLE SOURCE CODE.

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
