package kr.or.kosta.and1014;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class Ex3_MyView extends View {
	private int width, height; // 화면의 넓이, 높이
	private int bottleW, bottleH; // 맥주병이미지 넓이, 높이
	private int centerX, centerY; // 화면의 중심
	private Bitmap bottle; // 비트맵 이미지 객체를 선언
	private int angle;
	private boolean flag=true;
	private int clickcount;

	public Ex3_MyView(Context context) {
		super(context);

		// getSystemService - Class.forName()과 동일한 역할
		// newInstance() 메서드를 내부적으로 제공해줌.
		WindowManager manager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		// activity에 있는 윈도우 매니저를 WINDOW_SERVICE로 생성한다.
		Display display = manager.getDefaultDisplay();
		width = display.getWidth();
		height = display.getHeight();
		// 화면의 중심점 구하기
		centerX = width / 2;
		centerY = width / 2;
		// 1. 이미지 읽어 오기 - Resource로 읽어와서 비트맵 객체로 생성
		bottle = BitmapFactory
				.decodeResource(getResources(), R.drawable.bottle);
		// 2. 이미지 크기를 조정
		bottleW = bottle.getWidth() / 2;
		bottleH = bottle.getHeight() / 2;

		bottle = Bitmap.createScaledBitmap(bottle, bottleW, bottleH, false);

		handler.sendEmptyMessageDelayed(0, 1);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		// 반복분을 돌려서 캔버스를 회전
		canvas.rotate(angle, centerX, centerY);
		canvas.drawBitmap(bottle, centerX - bottleW / 2, centerY - bottleH/2,
				null);
	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if(flag){
				angle+=10;
				invalidate();
				handler.sendEmptyMessageDelayed(0, 1);
			}else{
				for(int j=10; j>=0 ; j--){
					angle+=j;
					invalidate();
					handler.sendEmptyMessageDelayed(0, 10);
				}
				handler.removeMessages(0);
			}
		}
	};

	public boolean onTouchEvent(android.view.MotionEvent event) {
		clickcount++;
		
		if(clickcount%2==0){
			flag=true;
			handler.sendEmptyMessageDelayed(0, 1);
		}else if(clickcount%2==1){
			flag=false;
		}
				
		return false;
	};
}