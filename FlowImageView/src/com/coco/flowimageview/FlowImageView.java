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
import android.widget.ImageView;

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
		// TODO Auto-generated method stub

	}

}
