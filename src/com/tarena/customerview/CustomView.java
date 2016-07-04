package com.tarena.customerview;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class CustomView extends View {
	int viewHeight;
	int viewWidth;
	ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	int gap;
	int maxPrice;
	int touchY;

	public CustomView(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// y坐标位置
		touchY = (int) event.getY();
		// x坐标位置
		int x = (int) event.getX();
		int position = x / gap;
		Toast.makeText(getContext(), "position=" + position, Toast.LENGTH_SHORT)
				.show();
		return super.onTouchEvent(event);
	}

	public void setData(ArrayList<HashMap<String, String>> list) {
		this.list = list;
		for (int i = 0; i < list.size(); i++) {
			HashMap<String, String> map = list.get(i);
			int price = Integer.parseInt(map.get("price"));
			if (price > maxPrice) {
				maxPrice = price;
			}
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		try {
			Paint paint = new Paint();

			paint.setColor(Color.BLACK);
			Rect rect = new Rect(0, 0, viewWidth, viewHeight);
			// canvas画矩形，文字，图
			canvas.drawRect(rect, paint);

			paint.setColor(Color.RED);
			if (touchY >= 1) {
				canvas.drawLine(0, touchY, viewWidth, touchY, paint);
			}

			paint.setColor(Color.WHITE);
			paint.setTextSize(50);
			// 得到最后一个时间的宽度
			String strLastTime = list.get(list.size() - 1).get("time");
			// 用矩形得到宽度
			Rect sizeRect = new Rect();
			paint.getTextBounds(strLastTime, 0, strLastTime.length(), sizeRect);
			int lastTimeWidth = sizeRect.width();
			gap = (viewWidth - 50) / (list.size() - 1);
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = list.get(i);
				int timeX = i * gap;
				String time = map.get("time");
				canvas.drawText(time, timeX, viewHeight, paint);
				// 畫價格
				String strPrice = map.get("price");
				int intPrice = Integer.parseInt(strPrice);
				int priceHeight = viewHeight - sizeRect.height();
				int priceY = intPrice * priceHeight / maxPrice;

				// 最大值，显示在下面，应该显示在上面
				priceY = priceHeight - priceY;
				// 最大值看不到，畫到view的最上面，y要叫上畫的高度
				priceY = priceY + sizeRect.height();
				canvas.drawText(strPrice, timeX, priceY, paint);
				// 画线
				if (i < list.size() - 1) {
					float startX = timeX;
					float startY = priceY;
					float stopX = (i + 1) * gap;
					float stopY = 0;
					int nextPrice = Integer.parseInt(list.get(i + 1).get(
							"price"));
					stopY = nextPrice * priceHeight / maxPrice;
					stopY = priceHeight - stopY;
					stopY = stopY + sizeRect.height();
					canvas.drawLine(startX, startY, stopX, stopY, paint);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		viewHeight = h;
		viewWidth = w;
	}
}
