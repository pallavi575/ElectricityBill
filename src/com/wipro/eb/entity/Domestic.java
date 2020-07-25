package com.wipro.eb.entity;

public class Domestic extends Connection{

	public Domestic(int previousReading, int currentReading, float[] slabs)
	{
		super(previousReading, currentReading, slabs);
	}

	public float computeBill()
	{
		int k=currentReading-previousReading;
		if(k<=50)
			return (float)slabs[0]*k;
		else if(k>50 && k<=100)
			return (float)((slabs[0]*50)+(slabs[1]*(k-50)));
		else
			return (float)((slabs[0]*50)+(slabs[1]*50)+(slabs[2]*(k-100)));
	}
}