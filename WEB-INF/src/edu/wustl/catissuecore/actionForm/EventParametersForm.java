/**
 * <p>Title: EventParametersForm Class</p>
 * <p>Description:  This Class will be the super class for all Event Parameter classes.
 * <p> It contains the common attributes of the Event Parameter classes.   
 * Copyright:    Copyright (c) 2005
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Mandar Deshmukh
 * @version 1.00
 * Created on July 28th, 2005
 */

package edu.wustl.catissuecore.actionForm;

import edu.wustl.catissuecore.domain.AbstractDomainObject;

/**
 * @author mandar_deshmukh
 *  This Class will be the super class for all Event Parameter classes.
 */
public abstract class EventParametersForm extends AbstractActionForm
{
	/**
     * System generated unique identifier.
     * */
    protected long systemIdentifier;
    
    /**
     * Time in hours for the Event Parameter.
     * */
    protected String timeInHours;

    /**
     * Time in minutes for the Event Parameter.
     * */
    protected String timeInMinutes;

    /**
     * Date of the Event Parameter.
     * */
    protected String dateOfEvent;

    
    /**
     * Id of the User.   
     */
    protected long userId;

    /**
     * Comments on the event parameter.   
     */
    protected String comments;
    
// ----- variable declaration end
    
    
// ------ GET SET methods
    

	/**
	 * @return Returns the comments.
	 */
	public String getComments()
	{
		return comments;
	}
	
	/**
	 * @param comments The comments to set.
	 */
	public void setComments(String comments)
	{
		this.comments = comments;
	}
		
	/**
	 * @return Returns the dateOfEvent.
	 */
	public String getDateOfEvent()
	{
		return dateOfEvent;
	}
	
	/**
	 * @param dateOfEvent The dateOfEvent to set.
	 */
	public void setDateOfEvent(String dateOfEvent)
	{
		this.dateOfEvent = dateOfEvent;
	}
	
	/**
	 * @return Returns the time_InMinutes.
	 */
	public String getTimeInMinutes()
	{
		return timeInMinutes;
	}
	
	/**
	 * @param time_InMinutes The time_InMinutes to set.
	 */
	public void setTimeInMinutes(String time_InMinutes)
	{
		this.timeInMinutes = time_InMinutes;
	}
		
	/**
	 * @return Returns the timeStamp.
	 */
	public String getTimeInHours()
	{
		return timeInHours;
	}
	
	/**
	 * @param timeStamp The timeStamp to set.
	 */
	public void setTimeInHours(String timeStamp)
	{
		this.timeInHours = timeStamp;
	}
	
	/**
	 * @return Returns the userId.
	 */
	public long getUserId()
	{
		return userId;
	}
	
	/**
	 * @param userId The userId to set.
	 */
	public void setUserId(long userId)
	{
		this.userId = userId;
	}    
	
	
//--------  Super class Methods
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.wustl.catissuecore.actionForm.AbstractActionForm#getSystemIdentifier()
	 */
	public long getSystemIdentifier()
	{
		return this.systemIdentifier ;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.wustl.catissuecore.actionForm.AbstractActionForm#setSystemIdentifier(long)
	 */
	public void setSystemIdentifier(long systemIdentifier)
	{
		this.systemIdentifier = systemIdentifier ; 
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.wustl.catissuecore.actionForm.AbstractActionForm#getActivityStatus()
	 */
	public String getActivityStatus()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.wustl.catissuecore.actionForm.AbstractActionForm#setActivityStatus(java.lang.String)
	 */
	public void setActivityStatus(String activityStatus)
	{
		// TODO Auto-generated method stub
	}

	
	
	

} // class
