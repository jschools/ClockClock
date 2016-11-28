package com.schoovello.clockclock;

import processing.core.PApplet;

import static processing.core.PConstants.PI;

public class ClockDigit implements Drawable {

	private Clock[] mClocks;
	private int mDigit;

	public ClockDigit(Clock... clocks) {
		if (clocks.length != 6) {
			throw new IllegalArgumentException("need exactly 8 clocks");
		}
		mClocks = clocks;
	}

	public void setDigit(int digit) {
		if (digit < 0 || digit > 9) {
			throw new IllegalArgumentException(digit + " is not a single digit");
		}
		mDigit = digit;

		final float[][] digitAngles = DIGITS[digit];
		for (int c = 0; c < 6; c++) {
			final float[] digitAngle = digitAngles[c];
			mClocks[c].setAngles(digitAngle[0], digitAngle[1]);
		}
	}

	@Override
	public void draw(PApplet pApplet) {
		for (Clock clock : mClocks) {
			clock.draw(pApplet);
		}
	}

	private static final float A_0 = PI / 2f;
	private static final float A_15 = 0f;
	private static final float A_30 = -PI / 2f;
	private static final float A_37 = 5f / 4f * PI;
	private static final float A_45 = PI;

	private static final float[] C_0_0 = {A_0, A_0};     // ╵
	private static final float[] C_30_30 = {A_30, A_30}; // ╷
	private static final float[] C_15_15 = {A_15, A_15}; // ╶
	private static final float[] C_45_45 = {A_45, A_45}; // ╴
	private static final float[] C_0_15 = {A_0, A_15};   // └
	private static final float[] C_0_45 = {A_0, A_45};   // ┘
	private static final float[] C_15_30 = {A_15, A_30}; // ┌
	private static final float[] C_30_45 = {A_30, A_45}; // ┐
	private static final float[] C_15_45 = {A_15, A_45}; // ─
	private static final float[] C_0_30 = {A_0, A_30};   // │
	private static final float[] C_37_37 = {A_37, A_37}; // ,

	private static final float[][] D_0 = { C_15_30, C_0_30, C_0_15, C_30_45, C_0_30, C_0_45 };
	private static final float[][] D_1 = { C_37_37, C_37_37, C_37_37, C_30_30, C_0_30, C_0_0 };
	private static final float[][] D_2 = { C_15_15, C_15_30, C_0_15, C_30_45, C_0_45, C_45_45 };
	private static final float[][] D_3 = { C_15_15, C_15_15, C_15_15, C_30_45, C_0_45, C_0_45 };
	private static final float[][] D_4 = { C_30_30, C_0_15, C_37_37, C_30_30, C_0_45, C_0_0 };
	private static final float[][] D_5 = { C_15_30, C_0_15, C_15_15, C_45_45, C_30_45, C_0_45 };
	private static final float[][] D_6 = { C_15_30, C_0_30, C_0_15, C_45_45, C_30_45, C_0_45 };
	private static final float[][] D_7 = { C_15_15, C_37_37, C_37_37, C_30_45, C_0_30, C_0_0 };
	private static final float[][] D_8 = { C_15_30, C_0_15, C_0_15, C_30_45, C_0_45, C_0_45 };
	private static final float[][] D_9 = { C_15_30, C_0_15, C_15_15, C_30_45, C_0_30, C_0_45 };

	private static final float[][][] DIGITS = { D_0, D_1, D_2, D_3, D_4, D_5, D_6, D_7, D_8, D_9 };

}
