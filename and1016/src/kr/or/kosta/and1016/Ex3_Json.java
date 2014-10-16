package kr.or.kosta.and1016;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Ex3_Json extends Activity {
	private Button btn1;
	private ListView listv;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ex3_jsondata);
		// 퍼미션 StrictMode
		// android.os.Build.VERSION.SDK_INT 기기 버전 정보
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		btn1 = (Button) findViewById(R.id.ex3_btn);
		listv = (ListView) findViewById(R.id.ex3_listview);
		btn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				searchData();

			}
		});

	}

	private void searchData() {
		StringBuffer jsonQuery = new StringBuffer();

		jsonQuery
				.append("[{'CustomerID':'C001','Name':'Win Weerachai','Email':'win.weerachai@thaicreate.com' ,'CountryCode':'TH','Budget':'1000000','Used':'600000'},");
		jsonQuery
				.append("{'CustomerID':'C002','Name':'John Smith','Email':'john.smith@thaicreate.com' ,'CountryCode':'EN','Budget':'2000000','Used':'800000'},");
		jsonQuery
				.append("{'CustomerID':'C003','Name':'Jame Born','Email':'jame.born@thaicreate.com' ,'CountryCode':'US','Budget':'3000000','Used':'600000'},");
		jsonQuery
				.append("{'CustomerID':'C004','Name':'Chalee Angel','Email':'chalee.angel@thaicreate.com' ,'CountryCode':'US','Budget':'4000000','Used':'100000'}]");
		// json 데이터를 읽어들이는 동작
		// [ {key:value}, {}, {} ] => JSONArray 로 json데이터를 읽어와야
		// 반복문으로 하나 씩 JSONObject로 읽어 들일 수 있다.
		JSONArray data;
		ArrayList<HashMap<String, String>> myArrlist = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map;
		try {
			data = new JSONArray(jsonQuery.toString());
			for (int i = 0; i < data.length(); i++) {
				JSONObject c = data.getJSONObject(i);
				map = new HashMap<String, String>();
				map.put("CustomerID", c.getString("CustomerID"));
				map.put("Name", c.getString("Name"));
				map.put("Email", c.getString("Email"));
				map.put("CountryCode", c.getString("CountryCode"));
				map.put("Budget", c.getString("Budget"));
				map.put("Used", c.getString("Used"));
				myArrlist.add(map);
			}
			SimpleAdapter sAdapter;
			sAdapter = new SimpleAdapter(Ex3_Json.this, myArrlist,
					R.layout.ex3_colum, new String[] { "CustomerID", "Name",
							"Email" }, new int[] { R.id.colCustomerID,
							R.id.colName, R.id.colEmail });
			
			listv.setAdapter(sAdapter);
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}
	//url로 받아서 안드로이드에 뿌리는게 1단계!
	//2단계 : andmem 테이블에 group을 추가 5명은 A그룹 5명은 B그룹 나머지(10명)는 C그룹
	//검색폼(스피너)  -> 내가 A를 누르면 A에 해당하는 애들만 뽑아서 json으로 파싱
	//
	
}
