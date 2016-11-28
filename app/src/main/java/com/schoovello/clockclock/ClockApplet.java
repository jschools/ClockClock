package com.schoovello.clockclock;

import processing.core.PApplet;

public class ClockApplet extends PApplet {

	private long mStartTime;

	private int mOffset;
	private ClockDigit[] mDigits;

	@Override
	public void settings() {
		size(1100, 768);
		smooth();
	}

	@Override
	public void setup() {
		mStartTime = System.currentTimeMillis();

		int x = 100;
		int y = 0;

		mDigits = new ClockDigit[10];
		for (int i = 0; i < 10; i++) {
			Clock c0 = new AnimatingClock(x, y + 100);
			Clock c1 = new AnimatingClock(x, y + 200);
			Clock c2 = new AnimatingClock(x, y + 300);
			Clock c3 = new AnimatingClock(x + 100, y + 100);
			Clock c4 = new AnimatingClock(x + 100, y + 200);
			Clock c5 = new AnimatingClock(x + 100, y + 300);
			Clock[] clocks = { c0, c1, c2, c3, c4, c5 };

			ClockDigit digit = new ClockDigit(clocks);
			digit.setDigit(i);

			mDigits[i] = digit;

			x += 200;
			if (i == 4) {
				x = 100;
				y = 350;
			}
		}
	}

	@Override
	public void draw() {
		background(0xff000000);

		long time = System.currentTimeMillis() - mStartTime;
		if (time > 8500) {
			mOffset++;

			for (int i = 0; i < mDigits.length; i++) {
				ClockDigit digit = mDigits[i];
				digit.setDigit((i + mOffset) % 10);
			}

			mStartTime = System.currentTimeMillis();
		}

		for (ClockDigit digit : mDigits) {
			digit.draw(this);
		}
	}

}
