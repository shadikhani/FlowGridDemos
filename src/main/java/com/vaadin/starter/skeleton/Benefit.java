package com.vaadin.starter.skeleton;

public class Benefit {
	
	private int year;
	private int quarter1;
	private int quarter2;
	private int quarter3;
	private int quarter4;

	

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getQuarter1() {
		return quarter1;
	}

	public void setQuarter1(int quarter1) {
		this.quarter1 = quarter1;
	}

	public int getQuarter2() {
		return quarter2;
	}

	public void setQuarter2(int quarter2) {
		this.quarter2 = quarter2;
	}

	public int getQuarter3() {
		return quarter3;
	}

	public void setQuarter3(int quarter3) {
		this.quarter3 = quarter3;
	}

	public int getQuarter4() {
		return quarter4;
	}

	public void setQuarter4(int quarter4) {
		this.quarter4 = quarter4;
	}

	public Benefit(int year, int quarter1, int quarter2, int quarter3, int quarter4) {
		
		this.year = year;
		this.quarter1 = quarter1;
		this.quarter2 = quarter2;
		this.quarter3 = quarter3;
		this.quarter4 = quarter4;
	}

}
