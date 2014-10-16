package kr.or.kosta.and1016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Ex2_PostLogin extends Activity {
	private EditText idtext, pwdtext;
	private TextView restext;
	private Button btn1;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.ex2_post);
		// 퍼미션 StrictMode
		// android.os.Build.VERSION.SDK_INT 기기 버전 정보
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		idtext = (EditText) findViewById(R.id.ex2_idtext);
		pwdtext = (EditText) findViewById(R.id.ex2_pwdtext);
		restext = (TextView) findViewById(R.id.ex2_restext);
		btn1 = (Button) findViewById(R.id.ex2_btn1);

		btn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String url = "http://192.168.7.225:8084/server1016/login.jsp";
				// Parameter를 전송하기 위한 담당객체를 생성 후
				// Parameter를 대입을 해야한다.
				// NameValuePair : 파라미터를 관리해주는 담당 객체
				ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("id", idtext.getText()
						.toString().trim()));
				params.add(new BasicNameValuePair("pwd", pwdtext.getText()
						.toString().trim()));

				restext.setText(getHttpPost(url, params));
			}
		});
	}

	protected String getHttpPost(String url, ArrayList<NameValuePair> params) {
		HttpClient client = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		StringBuffer str = new StringBuffer();
		try {
			//post 방식으로 파라미터값들을 가진 객체를 세팅!
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			HttpResponse response = client.execute(httpPost);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if(statusCode == 200){//200번 코드!! 외우기!! 성공할때!!
				InputStream content = response.getEntity().getContent();
				//Scanner 버젼
				Scanner reader = new Scanner(content);
				
				String line="";
				while(reader.hasNext()){
					line = reader.nextLine();
					
					str.append(line);
				}
				String er = str.toString();
				Log.d("error", er);
				/*
				BufferedReader br = new BufferedReader(new InputStreamReader(content));
				String line="";
				while((line=br.readLine()) != null){
					str.append(line);
				}
				*/
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str.toString();
	}
}
