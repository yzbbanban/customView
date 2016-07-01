package com.tarena.customerview;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {
	CustomView customView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		customView = (CustomView) findViewById(R.id.cv);
		customView.setData(getData());
	}

	public ArrayList<HashMap<String, String>> getData() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("time", "9");
		map.put("price", "1000");
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		list.add(map);
		map = new HashMap<String, String>();
		map.put("time", "10");
		map.put("price", "2500");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("time", "11");
		map.put("price", "3500");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("time", "13");
		map.put("price", "5000");
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
