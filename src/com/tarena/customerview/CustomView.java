package com.tarena.customerview;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class CustomView extends View {

	int viewHeight;
	int viewWidth;
	ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	int gap;
	public CustomView(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	public void setData(ArrayList<HashMap<String, String>> list) {
		this.list = list;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		Rect rect = new Rect(0, 0, viewWidth, viewHeight);
		// canvas»­¾ØÐÎ£¬ÎÄ×Ö£¬Í¼
		canvas.drawRect(rect, paint);
		
		paint.setColor(Color.WHITE);
		paint.setTextSize(120);
		gap=(viewWidth-120)/(list.size()-1);
		for (int i = 0; i < list.size(); i++) {
			HashMap<String, String> map=list.get(i);
			
			String time=map.get("time");
			canvas.drawText(time, i*gap, viewHeight, paint);
			
		}
		
		
	}	
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		viewHeight = h;
		viewWidth = w;
	}
}
