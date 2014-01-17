Android-FlowImageView
=====================

Zaker style flow image view, it's image horizontal scrolling slowly, using as Zaker's main screen background.

Features
========

+ Android SDK **level 4+**.
+ Image horizontal scrolling slowly (flowing).
+ Options settings: flow velocity, delay at edge.

Snapshots
=========

[flowing]: https://github.com/zzhouj/Android-FlowImageView/raw/master/snapshot/flowing.png "flowing"

![flowing snapshot][flowing]

Usage
=====
1. Add layout xml fragment like below:

		<com.coco.flowimageview.FlowImageView
		    android:id="@+id/flow_image"
		    android:layout_width="match_parent"
		    android:layout_height="match_parent"
		    android:src="@drawable/rootblock_default_bg" />

2. Options settings:

		// Flow velocity (pixels per second, min: 0.3333 dips/s)
		mFlowImageView.setFlowVelocity(flowVelocity);
		
		// Edge delay (ms)
		mFlowImageView.setEdgeDelay(edgeDelay);

License
=======

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
