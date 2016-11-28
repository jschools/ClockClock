package com.schoovello.clockclock;

public class AnticipateOvershootInterpolator implements Interpolator {

	private static final float TENSION = 3.5f;

	private static float a(float t, float s) {
		return t * t * ((s + 1) * t - s);
	}

	private static float o(float t, float s) {
		return t * t * ((s + 1) * t + s);
	}

	@Override
	public float getInterpolation(float t) {
		if (t < 0.5f) {
			return 0.5f * a(t * 2.0f, TENSION);
		} else {
			return 0.5f * (o(t * 2.0f - 2.0f, TENSION) + 2.0f);
		}
	}
}
