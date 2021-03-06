package kr.or.kosta.game_exam;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class GG extends Activity implements OnClickListener {
	private OddGame og;
	private Button hBtn, jBtn, endBtn;
	private TextView userText, cpuText, totalCount, winCount, rateText,
			giftText;
	private int total = 0, win = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_layout);
		og = new OddGame();
		hBtn = (Button) findViewById(R.id.hBtn);
		jBtn = (Button) findViewById(R.id.jBtn);
		endBtn = (Button) findViewById(R.id.endBtn);

		userText = (TextView) findViewById(R.id.userText);
		cpuText = (TextView) findViewById(R.id.cpuText);
		totalCount = (TextView) findViewById(R.id.totalCount);
		winCount = (TextView) findViewById(R.id.winCount);
		rateText = (TextView) findViewById(R.id.rateText);
		giftText = (TextView) findViewById(R.id.giftText);

		hBtn.setOnClickListener(this);
		jBtn.setOnClickListener(this);
		endBtn.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		String user = null;
		String cpu = null;
		int rate = 0;
		int cpunum = og.cpuRandom();
		if (cpunum == 1) {
			cpu = "Hol";
		} else {
			cpu = "JJak";
		}

		if (v == hBtn) {
			user = "Hol";
			if (cpunum == 1) {
				win++;
			}
		} else if (v == jBtn) {
			user = "JJak";
			if (cpunum == 2) {
				win++;
			}
		} else if (v == endBtn) {
			
		}
		total++;
		rate = og.calRate(win,	total);
		userText.setText(user);
		cpuText.setText(cpu);
		totalCount.setText("Total :" + total);
		winCount.setText("Win : "+win);
		rateText.setText("Rate : "+rate);
		giftText.setText(og.printGift(rate));

	}

}
