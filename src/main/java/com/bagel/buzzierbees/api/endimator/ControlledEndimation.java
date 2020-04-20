package com.bagel.buzzierbees.api.endimator;

import com.bagel.buzzierbees.api.BuzzierBeesAPI.ClientInfo;

import net.minecraft.util.math.MathHelper;

/**
 * @author - SmellyModder(Luke Tonon)
 */
public class ControlledEndimation {
	private int currentValue, prevValue;
	public int tickDuration;
	public boolean shouldDecrement;
	public boolean shouldTimerRun;
	
	public ControlledEndimation(int tickDuration, int startingValue) {
		this.currentValue = this.prevValue = startingValue;
		this.tickDuration = tickDuration;
	}
	
	public void update() {
		this.prevValue = this.currentValue;
	}
	
	public void updateTimerValues() {
		if(!this.shouldTimerRun) return;
		
		if(this.shouldDecrement) {
			if(this.currentValue > 0) {
				this.currentValue--;
			}
		} else {
			if(this.currentValue < this.tickDuration) {
				this.currentValue++;
			}
		}
	}
	
	public void manipulateTimer(boolean shouldDecrement) {
		this.shouldDecrement = shouldDecrement;
	}
	
	public float getCurrentValue() {
		return this.currentValue;
	}
	
	public void setValue(int amount) {
		this.currentValue = amount;
	}
	
	public void addValue(int amount) {
		this.currentValue += amount;
	}
	
	public void setTimerToStop(boolean stop) {
		this.shouldTimerRun = stop;
	}
	
	public float getAnimationProgress() {
		return MathHelper.lerp(ClientInfo.getPartialTicks(), this.prevValue, this.currentValue) / tickDuration;
	}
}