package com.example.noti;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

	private static final int MYNOTIFICATION = 1;
	private static final int MYNOTIFICATION1 = 100;
	private static final String CHANNEL_ID = "10";

	private int myCount =0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void doNotification(View v) {
		boolean useIndeterminateProgressBar = true;
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		Notification noti = new Notification.Builder(this)
		.setContentTitle(getString(R.string.app_name))
		.setContentText("Just a Notice")
		.setSmallIcon(R.drawable.ic_launcher)
		.setOngoing(true)						//true only dismissable by app
		.setProgress(100,50,useIndeterminateProgressBar )				//show a progress bar
		.build();

		noti.flags |= Notification.FLAG_INSISTENT;
		notificationManager.notify(MYNOTIFICATION, noti);
	}

	public void doCancelNotification(View v) {
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notificationManager.cancel(MYNOTIFICATION);
	}

	public void doIncrementNotification(View view) {
		myCount++;
		NotificationManager notificationManager = (NotificationManager) this.getSystemService(this.NOTIFICATION_SERVICE);

		Notification noti = new Notification.Builder(this)
				.setContentTitle("Notification")
				.setContentText("with user set content, number =" + Integer.toString(myCount))
				.setSmallIcon(R.drawable.ic_launcher)
				.setOngoing(false)
				.build();
		noti.flags |= Notification.FLAG_INSISTENT;
		//the following will modify an existing notification over and over
		notificationManager.notify(MYNOTIFICATION1, noti);

		//the following creates a new notification
//		notificationManager.notify(myCount, noti);
	}
}
