package kr.or.kosta.and1014;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Ex1_Thread extends Activity {
	private TextView text1, text2, text3;
	private Button btn1, btn2, btn3;
	private int fnum, bnum; // ��ư�� Ŭ�� �� ������ ������ ��

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.ex1_thread);
		// text1 = (TextView) findViewById(R.id.ex1_text1);
		text2 = (TextView) findViewById(R.id.ex1_text2);
		text3 = (TextView) findViewById(R.id.ex1_text3);
		btn1 = (Button) findViewById(R.id.ex1_btn);
		btn2 = (Button) findViewById(R.id.ex1_btn2);
		btn3 = (Button) findViewById(R.id.ex1_btn3);

		btn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				text2.setText("For : " + ++fnum);
				// text3.setText("Back : " + ++bnum);

			}
		});
		// stop : handler�� ����(removeMessages(what);)
		btn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				handler.sendEmptyMessage(1);
				// handler.removeMessages(0);
				btn2.setVisibility(View.GONE);
				btn3.setVisibility(View.VISIBLE);
				// ��ư ��� handler���� �������ش�. 1�� �޼����� ������ ����ڰ� 
				//stop��ư�� ���� �Ͱ� ���� ���̱�
				// ������ handler ���� else if(msg.what == 1)�� �޾Ƽ� ��ư �ڵ鸵�� ���ش�.
			}
		});
		// start
		btn3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// INVISIBLE : ��ư�� �ڸ��� ������ä �Ⱥ������� ����
				// GONE : ��ư�� �ڸ��� �������鼭 �Ⱥ������� ����
				// btn3.setVisibility(View.INVISIBLE);
				btn3.setVisibility(View.GONE);
				btn2.setVisibility(View.VISIBLE);
				handler.sendEmptyMessageDelayed(0, 1000);

			}
		});

		// MyThread mt = new MyThread();
		// mt.setDaemon(true); //�θ�(����)�� ����� �� ���������� �����嵵 ����ȴ�.
		// mt.start();
		// �ڵ鷯 ��ü�� �޼����� ������
		// UI�� �ֱ����� �ݺ��۾��� �ʿ��� �� �����带 ����ؼ� ���
		// �����尣�� �޼��� ������ �ʿ��� ������ ���
		// ���������� �ڵ鷯�� �ϳ��� ������� ������
		//1�ʸ��� �ѹ��� ����ǰ� 0������ ������.
		handler.sendEmptyMessageDelayed(0, 1000);

	}

	// ���� Ŭ���� ����
	public class MyThread extends Thread {

		@Override
		public void run() {
			while (true) {
				// Thread �ȿ����� UI�� ���� ȣ�� �� �� ����.
				try {
					Thread.sleep(1000);
					bnum++;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// Handler ����
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0) {
				bnum++;
				text3.setText("Back : " + bnum);
				// ���ȣ���� ���ؼ� �������� �ݺ��� ���� �Ѵ�.
				handler.sendEmptyMessageDelayed(0, 1000);
				// what : ���ư��� �ִ� �������� identity id!!
			}
		};

	};
}