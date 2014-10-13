package kr.or.kosta.and1013;

import kr.or.kosta.and1013.R.layout;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class Ex4_Dialog extends Activity {
	private ImageButton ib;
	private AlertDialog.Builder builder;
	private View ly;
	private int[] col = {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.DKGRAY};
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.ex4_dialog);
		ib = (ImageButton) findViewById(R.id.ex4_imgBtn);
		// 다이얼로그는 생성자가 protected로 생성되어 있기 때문에 
		// 반드시 Builder로 생성해야 한다.
		builder = new AlertDialog.Builder(this);
		ly = findViewById(R.id.pp);
		ib.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//다이얼로그 선언
				builder.setTitle("My Dialog");
				builder.setMessage("HI, Hello");
				//builder.setIcon(R.drawable.sin1);
				//그냥 닫기 - null, 이벤트 콜백
				//builder.setPositiveButton("Close", null);
				
				builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						int num = (int) (Math.random()*5);
						ly.setBackgroundColor(col[num]);
						
					}
				});
				builder.show();
			}
		});
	}
}
