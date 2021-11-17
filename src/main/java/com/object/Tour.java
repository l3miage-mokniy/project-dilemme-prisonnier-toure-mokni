package com.object;

import com.tools.Coup;

public class Tour {
	private Coup coupJ1;
	private Coup coupJ2;
	
	public Tour() {
		super();
		this.coupJ1 = null;
		this.coupJ2 = null;
	}
	
	public Tour(Coup coupJ1, Coup coupJ2) {
		super();
		this.coupJ1 = coupJ1;
		this.coupJ2 = coupJ2;
	}
	
	public Coup getCoupJ1() {
		return coupJ1;
	}
	public void setCoupJ1(Coup coupJ1) {
		this.coupJ1 = coupJ1;
	}
	public Coup getCoupJ2() {
		return coupJ2;
	}
	public void setCoupJ2(Coup coupJ2) {
		this.coupJ2 = coupJ2;
	}

}
