package kr.or.kosta.and1013;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Ex2_Result extends Activity {
	private TextView text_id, text_name, text_tel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ex2_result);
		
		text_id = (TextView) findViewById(R.id.ex2_result_id);
		text_name = (TextView) findViewById(R.id.ex2_result_name);
		text_tel = (TextView) findViewById(R.id.ex2_result_tel);
		
		Intent intent = getIntent();
		Bundle mybundle = intent.getExtras();
		String[] arr = mybundle.getStringArray("vo");
		text_id.setText(arr[0]);
		text_name.setText(arr[1]);
		text_tel.setText(arr[2]);
	}
}
