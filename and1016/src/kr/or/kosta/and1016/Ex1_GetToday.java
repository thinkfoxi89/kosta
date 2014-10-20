package kr.or.kosta.and1016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
/* 
 *  GET ������� HTTP ���� �ϱ�
 *  1. ǥ�� HttpClient ��ü�� �����Ѵ�. ( DefaultHttpClient  ��ü)
 *  2. ���۹�Ŀ� ���� HttpGet/HttpPost�� ����
 *     Get����̶�� HttpGet(������url�ּ�) ��ü�� ����
 *  3. url�� Get ����, Response ��ü�� ���ؼ� Ŭ���̾�Ʈ�� �޵��� ����
 *  4. ���۹��� Response�� ���ؼ� ������ ���°��� ������ �� �ִ�.(�񱳺м� ó��)
 *  5. ���䰴ü(Response)�κ��� Entity : ������ ��� �ڿ��� ������ �ִ�.
 *  6. �����͸� ���� ���ؼ� Entity�� �������ִ� InputStream�� �޾Ƽ� ����� �� �ִ�. 
 */
public class Ex1_GetToday extends Activity{
	private TextView text;
	private Button btn1;
	@SuppressLint("NewApi") 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ex1_http);
		//�۹̼� StrictMode
		//android.os.Build.VERSION.SDK_INT ��� ���� ����
		if(android.os.Build.VERSION.SDK_INT > 9){
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		text = (TextView) findViewById(R.id.ex1_textRes);
		btn1 = (Button) findViewById(R.id.ex1_btn1);
		
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//������ url
				String url = "http://192.168.7.225:8084/server1015/ex1/ex1_hello.jsp";
				text.setText(getHttpGet(url));
			}

			//get ����� ó���ϱ� ���� �޼���
			public String getHttpGet(String url){
				//1. HttpClient ����
				HttpClient client = new DefaultHttpClient();
				StringBuffer str = new StringBuffer();
				String c = null;
				HttpGet httpGet = new HttpGet(url);
				try{
					HttpResponse response = client.execute(httpGet);
					StatusLine statusLine = response.getStatusLine();
					int statusCode = statusLine.getStatusCode();
					//�������� ������, 404, 500 ��
					if(statusCode == 200){
						HttpEntity entity = response.getEntity();
						InputStream content = entity.getContent();
						//������ 2�� ��Ʈ���� ����ؼ� ������ ��� StringBuffer�� �����Ѵ�.
						BufferedReader br = new BufferedReader(new InputStreamReader(content));
						while((c=br.readLine()) != null){
							str.append(c).append("\n");
						}
					}
				}catch (ClientProtocolException e){
					e.printStackTrace();
				}catch(IOException e){
					e.printStackTrace();
				}
				return str.toString();
			}
		});
		
	}
}