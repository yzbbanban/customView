package com.tarena.customerview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {
	CustomView customView;
	Random random = new Random();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		customView = (CustomView) findViewById(R.id.cv);
		customView.setData(getData());
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				customView.setData(getData());
				Log.i("更新", "获取时间" + System.currentTimeMillis());

				// invalidate 让屏幕无效-->重新绘制
				customView.invalidate();
				handler.postDelayed(this, 1000);
			}
		}, 1000);
	}

	public ArrayList<HashMap<String, String>> getData() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("time", "9");
		map.put("price", String.valueOf(random.nextInt(5000)));
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		list.add(map);
		map = new HashMap<String, String>();
		map.put("time", "10");
		map.put("price", String.valueOf(random.nextInt(5000)));
		list.add(map);

		map = new HashMap<String, String>();
		map.put("time", "11");
		map.put("price", String.valueOf(random.nextInt(5000)));
		list.add(map);

		map = new HashMap<String, String>();
		map.put("time", "13");
		map.put("price", String.valueOf(random.nextInt(5000)));
		list.add(map);
		Log.i("", "");
		return list;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
