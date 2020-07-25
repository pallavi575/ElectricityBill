package com.wipro.eb.entity;

public class Commercial extends Connection{

	public Commercial(int previousReading, int currentReading, float[] slabs) {
		super(previousReading, currentReading, slabs);
		// TODO Auto-generated constructor stub
	}

	public float computeBill()
	{
		int k=currentReading-previousReading;
		double billAmount;
		double res;
		if(k<=50)
			billAmount=slabs[0]*k;
		else if(k>50 && k<=100)
			billAmount=(slabs[0]*50)+(slabs[1]*(k-50));
		else
			billAmount=(slabs[0]*50)+(slabs[1]*50)+(slabs[2]*(k-100));
		if(billAmount<5000)
			res=billAmount+(0.02*billAmount);
		else if(billAmount>=5000)
			res=billAmount+(0.06*billAmount);
		else 
			res=billAmount+(0.09*billAmount);
		return (float)res;
	}
}