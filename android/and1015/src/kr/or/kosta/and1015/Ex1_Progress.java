package kr.or.kosta.and1015;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class Ex1_Progress extends Activity {
	private Button btn1;
	private ProgressDialog progressBar;//���α׷����� ��ü ����
	private int progressBarStatus; // ���α׷������� ���°� �ʱ�ȭ
	private Handler progressBarHandler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ex1_handler);
		btn1 = (Button) findViewById(R.id.ex1_btn);
		//�ڵ鷯 ��ü�� ����
		progressBarHandler = new Handler();
		//��ư�� Ŭ������ �� ���α׷����ٸ� �������Ѽ� ������ ��Ų��.
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//���α׷����ٸ� ����
				progressBar = new ProgressDialog(v.getContext());
				progressBar.setCancelable(true);
				progressBar.setMessage("Loading");
				progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				progressBar.setProgress(0); // �ʱⰪ
				progressBar.setMax(100); //���ᰪ
				progressBar.show();
				progressBarStatus = 0;
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						while(progressBarStatus<100){
							progressBarStatus = doSomeTask();
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							//�ǽð� �׷���� �Ѵ�
							//�ݵ�� Runnable���� ���ڰ��� �����ؾ� �ϸ�,
							//�ڵ鷯���� �����带 �����ϴ� ����߿� �ϳ��̴�.
							//post�� �޼��带 ����ؼ� �����尡 �����ϴ� 
							//Status���� UI�� �����ϴ� ���� ���ش�.
							progressBarHandler.post(new Runnable() {
								
								@Override
								public void run() {
									progressBar.setProgress(progressBarStatus);
									
								}
							});
						}						
					}
				}).start();
			}
		});
			
	}
	//���α׷����ٸ� � �����ִ� ���� �ϴ� �޼��� - �����尡 �����ؼ� ȣ��
	public int doSomeTask(){
		//���α׷������� ���°� ����
		progressBarStatus++;
		//���α׷��� ���� MAX���� ���� ����,
		if(progressBarStatus<100){
			return progressBarStatus;
		}
		try {
			Thread.sleep(1000);
			progressBar.dismiss();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 100;
	}
}