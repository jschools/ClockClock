package com.schoovello.clockclock;

import java.util.Calendar;

import processing.core.PApplet;

public class ClockApplet extends PApplet {

	private Calendar mCalendar;

	private long mStartTime;

	private ClockDigit[] mDigits;

	@Override
	public void settings() {
		size(1024, 768);
		smooth(4);
	}

	@Override
	public void setup() {
		mCalendar = Calendar.getInstance();

		mStartTime = System.currentTimeMillis();

		int x = 50;
		int y = 50;

		mDigits = new ClockDigit[10];
		for (int i = 0; i < 10; i++) {
			Clock c0 = new Clock(x, y + 100);
			Clock c1 = new Clock(x, y + 200);
			Clock c2 = new Clock(x, y + 300);
			Clock c3 = new Clock(x + 100, y + 100);
			Clock c4 = new Clock(x + 100, y + 200);
			Clock c5 = new Clock(x + 100, y + 300);
			Clock[] clocks = { c0, c1, c2, c3, c4, c5 };

			ClockDigit digit = new ClockDigit(clocks);
			digit.setDigit(i);

			mDigits[i] = digit;

			x += 200;
			if (i == 4) {
				x = 50;
				y = 350;
			}
		}
	}

	@Override
	public void draw() {
		background(0xffffffff);

		for (ClockDigit digit : mDigits) {
			digit.draw(this);
		}
	}

	public long getRealTime() {
		return System.currentTimeMillis();
	}

	public Calendar getNowCalendar() {
		mCalendar.setTimeInMillis(getRealTime());
		return mCalendar;
	}

	public long getDrawingTime() {
		return getRealTime() - mStartTime;
	}

}
