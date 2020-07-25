package com.wipro.eb.service;

import com.wipro.eb.entity.Commercial;
import com.wipro.eb.entity.Domestic;
import com.wipro.eb.exception.InvalidConnectionException;
import com.wipro.eb.exception.InvalidReadingException;

public class ConnectionService {

	public boolean validate(int currentReading,int previousReading,String type)throws InvalidReadingException,InvalidConnectionException
	{
		if(currentReading<previousReading || currentReading<0 || previousReading<0)
			throw new InvalidReadingException();
		if(!(type.equals("Domestic") || type.equals("Commercial")))
			throw new InvalidConnectionException();
		return true;
	}
	public float calculateBillAmt(int currentReading,int previousReading,String type)
	{
		try 
		{
			boolean valid=validate(currentReading,previousReading,type);
			if(valid)
			{
				if(type.equals("Domestic"))
				{
					float slabs[]= {2.3f,4.2f,5.5f};
					Domestic d=new Domestic(previousReading, currentReading, slabs);
					return d.computeBill();
				}
				else if(type.equals("Commercial"))
				{
					float slabs[]= {5.2f,6.8f,8.3f};
					Commercial c=new Commercial(previousReading, currentReading, slabs);
					return c.computeBill();
				}
			}
		}
		catch(InvalidReadingException e)
		{
			return -1;
		}
		catch(InvalidConnectionException e)
		{
			return -2;
		}
		return previousReading;
		
	}
	public String generateBill(int currentReading,int previousReading,String type)
	{
		float i=calculateBillAmt(currentReading,previousReading,type);
		if((int)i==-1)
			return "Incorrect Reading";
		else if((int)i==-2)
			return "Invalid Connection Type";
		else
			return "Amount to be paid: "+ i;
	}
}