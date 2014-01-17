package com.coco.flowimageview;

/*
 The MIT License (MIT)

 Copyright (c) 2014 justin

 Permission is hereby granted, free of charge, to any person obtaining a copy of
 this software and associated documentation files (the "Software"), to deal in
 the Software without restriction, including without limitation the rights to
 use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 the Software, and to permit persons to whom the Software is furnished to do so,
 subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.Scroller;

/**
 * Zaker style flow image view, it's image horizontal scrolling slowly, using as Zaker's main screen background.
 */
public class FlowImageView extends ImageView {
	private static final String TAG = "FlowImageView";
	private static final boolean DEBUG = true;

	private static void DEBUG_LOG(String msg) {
		if (DEBUG) {
			Log.v(TAG, msg);
		}
	}

	private static final float MIN_FLOW_VELOCITY = 1; // dips per second
	private static final float DEFAULT_FLOW_VELOCITY = 1.5f; // dips per second
	private static final int DEFAULT_EDGE_DELAY = 2000; // ms

	private float mMinFlowVelocity;
	private float mFlowVelocity;
	private int mEdgeDelay = DEFAULT_EDGE_DELAY;

	private Scroller mScroller;
	private boolean mIsFlowPositive = true;

	private final Runnable mReverseFlowRunnable = new Runnable() {
		public void run() {
			mIsFlowPositive = !mIsFlowPositive;
			DEBUG_LOG("mReverseFlowRunnable mIsFlowPositive=" + mIsFlowPositive);
			flow();
		}
	};

	public FlowImageView(Context context) {
		super(context);
		initFlowImageView();
	}

	public FlowImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initFlowImageView();
	}

	public FlowImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initFlowImageView();
	}

	private void initFlowImageView() {
		setScaleType(ImageView.ScaleType.MATRIX);

		final Context context = getContext();
		final float density = context.getResources().getDisplayMetrics().density;

		mMinFlowVelocity = MIN_FLOW_VELOCITY * density;
		mFlowVelocity = DEFAULT_FLOW_VELOCITY * density;

		mScroller = new Scroller(context, new LinearInterpolator());
	}

	public float getFlowVelocity() {
		return mFlowVelocity;
	}

	public void setFlowVelocity(float flowVelocity) {
		if (flowVelocity < mMinFlowVelocity) {
			flowVelocity = mMinFlowVelocity;
		}
		mFlowVelocity = flowVelocity;
		flow();
	}

	public int getEdgeDelay() {
		return mEdgeDelay;
	}

	public void setEdgeDelay(int edgeDelay) {
		if (edgeDelay < 0) {
			edgeDelay = 0;
		}
		mEdgeDelay = edgeDelay;
		flow();
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		flow();
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		getHandler().removeCallbacks(mReverseFlowRunnable);
	}

	private void flow() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		DEBUG_LOG("onLayout");
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		DEBUG_LOG("onSizeChanged");
	}

}
