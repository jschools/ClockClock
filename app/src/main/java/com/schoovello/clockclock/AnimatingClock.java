package com.schoovello.clockclock;

import processing.core.PApplet;

public class AnimatingClock extends Clock {

	private static final Interpolator INTERPOLATOR = new AccelerateDecelerateInterpolator();

	private long mAnimationDurationMs;
	private long mAnimationStartMs;
	private float mPrevHr;
	private float mPrevMin;
	private float mCurrHr;
	private float mCurrMin;
	private float mRefHr;
	private float mRefMin;

	public AnimatingClock(float x, float y) {
		this(x, y, 7000);
	}

	public AnimatingClock(float x, float y, long animationDurationMs) {
		super(x, y);

		mAnimationDurationMs = animationDurationMs;
	}

	@Override
	public void setAngles(float hour, float min) {
		mPrevHr = mCurrHr;
		mPrevMin = mCurrMin;
		mAnimationStartMs = System.currentTimeMillis();
		mRefHr = hour;
		mRefMin = min;
	}

	@Override
	public void draw(PApplet pApplet) {
		final long timeSinceStart = System.currentTimeMillis() - mAnimationStartMs;
		if (timeSinceStart >= mAnimationDurationMs) {
			mCurrHr = mRefHr;
			mCurrMin = mRefMin;
		} else {
			float f = INTERPOLATOR.getInterpolation((float) timeSinceStart / (float) mAnimationDurationMs);
			mCurrHr = f * mRefHr + (1 - f) * mPrevHr;
			mCurrMin = f * mRefMin + (1 - f) * mPrevMin;
		}

		super.setAngles(mCurrHr, mCurrMin);
		super.draw(pApplet);
	}


}
