package com.csc.TrainStation;

public class SaveOutput {
	private int consumed,consumed2,consumed3;
	private Frame frame;
	public int getConsumed() {
		return consumed;
		
			}

	public void setConsumed(int consumed) {
		this.consumed = this.consumed+consumed;
	}

	public int getConsumed2() {
		return consumed2;
	}

	public void setConsumed2(int consumed2) {
		this.consumed2 = this.consumed2+consumed2;
	}

	public int getConsumed3() {
		return consumed3;
	}

	public void setConsumed3(int consumed3) {
		this.consumed3 = this.consumed3+consumed3;
	}

	public SaveOutput(Frame frame){
		this.frame = frame;
		this.consumed = 0;
		this.consumed2 = 0;
		this.consumed3 = 0;
	}
	
}
