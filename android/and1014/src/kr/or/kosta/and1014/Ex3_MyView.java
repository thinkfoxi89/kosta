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
	private int width, height; // ȭ���� ����, ����
	private int bottleW, bottleH; // ���ֺ��̹��� ����, ����
	private int centerX, centerY; // ȭ���� �߽�
	private Bitmap bottle; // ��Ʈ�� �̹��� ��ü�� ����
	private int angle;
	private boolean flag=true;
	private int clickcount;

	public Ex3_MyView(Context context) {
		super(context);

		// getSystemService - Class.forName()�� ������ ����
		// newInstance() �޼��带 ���������� ��������.
		WindowManager manager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		// activity�� �ִ� ������ �Ŵ����� WINDOW_SERVICE�� �����Ѵ�.
		Display display = manager.getDefaultDisplay();
		width = display.getWidth();
		height = display.getHeight();
		// ȭ���� �߽��� ���ϱ�
		centerX = width / 2;
		centerY = width / 2;
		// 1. �̹��� �о� ���� - Resource�� �о�ͼ� ��Ʈ�� ��ü�� ����
		bottle = BitmapFactory
				.decodeResource(getResources(), R.drawable.bottle);
		// 2. �̹��� ũ�⸦ ����
		bottleW = bottle.getWidth() / 2;
		bottleH = bottle.getHeight() / 2;

		bottle = Bitmap.createScaledBitmap(bottle, bottleW, bottleH, false);

		handler.sendEmptyMessageDelayed(0, 1);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		// �ݺ����� ������ ĵ������ ȸ��
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