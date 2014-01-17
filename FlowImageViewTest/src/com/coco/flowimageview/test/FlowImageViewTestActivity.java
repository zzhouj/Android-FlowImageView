package com.coco.flowimageview.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.coco.flowimageview.FlowImageView;

public class FlowImageViewTestActivity extends Activity {

	private FlowImageView mFlowImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flow_image_view_test);

		mFlowImageView = (FlowImageView) findViewById(R.id.flow_image);
		mFlowImageView.setFlowVelocity(200);
	}

}
