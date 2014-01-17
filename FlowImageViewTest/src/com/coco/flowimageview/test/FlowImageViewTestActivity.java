package com.coco.flowimageview.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.coco.flowimageview.FlowImageView;

public class FlowImageViewTestActivity extends Activity implements OnSeekBarChangeListener {

	private FlowImageView mFlowImageView;

	private TextView mFlowVelocityText;
	private TextView mEdgeDelayText;

	private SeekBar mFlowVelocitySeek;
	private SeekBar mEdgeDelaySeek;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flow_image_view_test);

		mFlowImageView = (FlowImageView) findViewById(R.id.flow_image);

		mFlowVelocityText = (TextView) findViewById(R.id.flow_velocity_text);
		mEdgeDelayText = (TextView) findViewById(R.id.edge_delay_text);

		mFlowVelocityText.setText(String.valueOf(mFlowImageView.getFlowVelocity()));
		mEdgeDelayText.setText(String.valueOf(mFlowImageView.getEdgeDelay()));

		mFlowVelocitySeek = (SeekBar) findViewById(R.id.flow_velocity_seek);
		mEdgeDelaySeek = (SeekBar) findViewById(R.id.edge_delay_seek);

		mFlowVelocitySeek.setProgress((int) (mFlowImageView.getFlowVelocity() * 100));
		mEdgeDelaySeek.setProgress((int) mFlowImageView.getEdgeDelay());

		mFlowVelocitySeek.setOnSeekBarChangeListener(this);
		mEdgeDelaySeek.setOnSeekBarChangeListener(this);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		if (fromUser) {
			if (seekBar == mFlowVelocitySeek) {
				mFlowImageView.setFlowVelocity(progress / 100f);
				mFlowVelocityText.setText(String.valueOf(mFlowImageView.getFlowVelocity()));
			} else if (seekBar == mEdgeDelaySeek) {
				mFlowImageView.setEdgeDelay(progress);
				mEdgeDelayText.setText(String.valueOf(mFlowImageView.getEdgeDelay()));
			}
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// do nothing
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// do nothing
	}

}
