package com.schoovello.clockclock;

import processing.core.PApplet;

import static processing.core.PConstants.PI;
import static processing.core.PConstants.TWO_PI;

public class Clock implements Drawable {

	private static final int COLOR_HOUR = 0xaaff0000;
	private static final int COLOR_MINUTE = 0xaa0000ff;
	private static final int COLOR_CIRCLE = 0xff000000;

	private static final float STROKE_WIDTH = 30f;

	private float mHourAngleRad = 0f;
	private float mMinuteAngleRad = 0f;

	private float mX = 0f;
	private float mY = 0f;

	public Clock(float x, float y) {
		mX = x;
		mY = y;
	}

	public void setTime(int hour, int min, int sec) {
		float mins = min + (sec / 60f);
		float hours = hour + (mins / 60);
		float hourAngle = hours * TWO_PI / 12f;
		float minAngle = mins * TWO_PI / 60f;
		setAngles(-hourAngle + PI / 2, -minAngle + PI / 2);
	}

	public void setAngles(float hour, float min) {
		mHourAngleRad = hour;
		mMinuteAngleRad = min;
	}

	@Override
	public void draw(PApplet pApplet) {
		pApplet.pushMatrix();

		pApplet.translate(mX, mY);

		pApplet.strokeCap(PApplet.PROJECT);

//		pApplet.strokeWeight(0.5f);
//		pApplet.ellipseMode(PApplet.CENTER);
//		pApplet.noFill();
//		pApplet.stroke(COLOR_CIRCLE);
//		pApplet.ellipse(0, 0, 100, 100);

		pApplet.strokeWeight(STROKE_WIDTH);

		pApplet.colorMode(PApplet.HSB);

		float hourHue = mHourAngleRad / TWO_PI * 255;
		if (hourHue < 0) {
			hourHue += 255;
		}
		pApplet.stroke(hourHue, 255, 255, ((float) 0xaa));
		pApplet.rotate(-mHourAngleRad);
		pApplet.line(0, 0, 50f - STROKE_WIDTH / 2, 0);

		float minHue = mMinuteAngleRad / TWO_PI * 255;
		if (minHue < 0) {
			minHue += 255;
		}
		pApplet.stroke(minHue, 255, 255, ((float) 0xaa));
		pApplet.rotate(mHourAngleRad - mMinuteAngleRad);
		pApplet.line(0, 0, 50f - STROKE_WIDTH / 2, 0);

		pApplet.popMatrix();
	}



}
