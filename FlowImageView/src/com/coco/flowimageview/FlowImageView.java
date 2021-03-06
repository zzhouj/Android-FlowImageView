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
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
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
	private static final boolean DEBUG = false;

	private static void DEBUG_LOG(String msg) {
		if (DEBUG) {
			Log.v(TAG, msg);
		}
	}

	private static final float MIN_FLOW_VELOCITY = 0.3333f; // dips per second
	private static final float DEFAULT_FLOW_VELOCITY = 0.6667f; // dips per second
	private static final int DEFAULT_EDGE_DELAY = 2000; // ms

	private float mMinFlowVelocity;
	private float mFlowVelocity;
	private int mEdgeDelay = DEFAULT_EDGE_DELAY;

	private Scroller mScroller;

	private boolean mIsFlowInited;
	private boolean mIsFlowPositive = true;
	private boolean mFlowStarted;

	private Matrix mImageMatrix = new Matrix();
	private float mScale;
	private float mTranslateX;
	private float mTranslateXEnd;

	private final Runnable mReverseFlowRunnable = new Runnable() {
		public void run() {
			mIsFlowPositive = !mIsFlowPositive;
			DEBUG_LOG("mReverseFlowRunnable mIsFlowPositive=" + mIsFlowPositive);
			startFlow();
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

	@Override
	public void setEnabled(boolean enabled) {
		if (enabled == isEnabled()) {
			return;
		}
		super.setEnabled(enabled);
		if (enabled) {
			startFlow();
		} else {
			stopFlow();
		}
	}

	public float getFlowVelocity() {
		return mFlowVelocity;
	}

	/**
	 * Setting flow velocity.
	 * 
	 * @param flowVelocity
	 *            pixels per second
	 */
	public void setFlowVelocity(float flowVelocity) {
		if (flowVelocity < mMinFlowVelocity) {
			flowVelocity = mMinFlowVelocity;
		}
		mFlowVelocity = flowVelocity;
		restartFlow();
	}

	public int getEdgeDelay() {
		return mEdgeDelay;
	}

	public void setEdgeDelay(int edgeDelay) {
		if (edgeDelay < 0) {
			edgeDelay = 0;
		}
		mEdgeDelay = edgeDelay;
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		startFlow();
	}

	@Override
	protected void onDetachedFromWindow() {
		stopFlow();
		super.onDetachedFromWindow();
	}

	@Override
	public void onWindowFocusChanged(boolean hasWindowFocus) {
		super.onWindowFocusChanged(hasWindowFocus);
		DEBUG_LOG("onWindowFocusChanged hasWindowFocus=" + hasWindowFocus);
		if (hasWindowFocus) {
			startFlow();
		} else {
			stopFlow();
		}
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		DEBUG_LOG("onLayout changed=" + changed +
				", left=" + left + ", top=" + top +
				", right=" + right + ", bottom=" + bottom);
		initFlow();
		startFlow();
	}

	private void initFlow() {
		Drawable drawable = getDrawable();
		if (drawable != null) {
			final float viewWidth = getWidth();
			final float viewHeight = getHeight();
			final float imgWidth = drawable.getIntrinsicWidth();
			final float imgHeight = drawable.getIntrinsicHeight();
			mScale = viewHeight / imgHeight;
			mTranslateXEnd = viewWidth - imgWidth * mScale;
			mIsFlowInited = true;
			setImageMatrix();
		} else {
			mIsFlowInited = false;
		}
	}

	private void restartFlow() {
		if (mFlowStarted) {
			startFlow();
		}
	}

	private void startFlow() {
		if (!isEnabled() || !mIsFlowInited) {
			return;
		}
		final int sx = (int) mTranslateX;
		final int dx = (int) ((mIsFlowPositive ? mTranslateXEnd : 0) - mTranslateX);
		if (dx == 0) {
			getHandler().removeCallbacks(mReverseFlowRunnable);
			getHandler().post(mReverseFlowRunnable);
			return;
		}
		final int duration = (int) Math.abs(dx / mFlowVelocity * 1000);
		DEBUG_LOG("startFlow mIsFlowPositive=" + mIsFlowPositive +
				", mTranslateX=" + mTranslateX + ", mTranslateXEnd=" + mTranslateXEnd +
				", sx=" + sx + ", dx=" + dx + ", duration=" + duration);
		mScroller.abortAnimation();
		mScroller.startScroll(sx * 100, 0, dx * 100, 0, duration);
		mFlowStarted = true;
		ViewCompat.postInvalidateOnAnimation(this);
	}

	private void stopFlow() {
		mScroller.abortAnimation();
		if (getHandler() != null) {
			getHandler().removeCallbacks(mReverseFlowRunnable);
		}
		mFlowStarted = false;
	}

	@Override
	public void computeScroll() {
		if (!isEnabled() || !mIsFlowInited || !mFlowStarted) {
			return;
		}
		if (!mScroller.isFinished() && mScroller.computeScrollOffset()) {
			mTranslateX = mScroller.getCurrX() / 100f;
			DEBUG_LOG("computeScroll mTranslateX=" + mTranslateX);
			setImageMatrix();

			// Keep on drawing until the animation has finished.
			ViewCompat.postInvalidateOnAnimation(this);
			return;
		}

		// Done with scroll, clean up state.
		stopFlow();
		getHandler().postDelayed(mReverseFlowRunnable, mEdgeDelay);
	}

	private void setImageMatrix() {
		mImageMatrix.reset();
		mImageMatrix.postScale(mScale, mScale);
		mImageMatrix.postTranslate(mTranslateX, 0);
		setImageMatrix(mImageMatrix);
	}

}
