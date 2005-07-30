/**
 * <p>Title: CollectionProtocol Class</p>
 * <p>Description:  A set of written procedures that describe how a biospecimen is prospectively collected.</p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Mandar Deshmukh
 * @version 1.00
 * Created on July 12, 2005
 */

package edu.wustl.catissuecore.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import edu.wustl.catissuecore.actionForm.AbstractActionForm;
import edu.wustl.catissuecore.actionForm.CollectionProtocolForm;
import edu.wustl.common.util.MapDataParser;

/**
 * A set of written procedures that describe how a biospecimen is prospectively collected.
 * @hibernate.joined-subclass table="CATISSUE_COLLECTION_PROTOCOL"
 * @hibernate.joined-subclass-key column="IDENTIFIER" 
 * @author Mandar Deshmukh
 */
public class CollectionProtocol extends SpecimenProtocol implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;
	
	/**
	 * Collection of studies associated with the CollectionProtocol.
	 */
	protected Collection distributionProtocolCollection = new HashSet();
	
	/**
	 * Collection of users associated with the CollectionProtocol.
	 */
	protected Collection userCollection = new HashSet();
	
	/**
	 * Collection of CollectionProtocolEvents associated with the CollectionProtocol.
	 */
	protected Collection collectionProtocolEventCollection = new HashSet();
	
	/**
	 * NOTE: Do not delete this constructor. Hibernet uses this by reflection API.
	 * */
	public CollectionProtocol()
	{
		super();
	}
	
	public CollectionProtocol(AbstractActionForm form)
	{
		setAllValues(form);
	}
	
	/**
	 * Returns the collection of Studies for this Protocol.
	 * @hibernate.set name="distributionProtocolCollection" table="CATISSUE_COLLECTION_DISTRIBUTION_RELATION" 
	 * cascade="save-update" inverse="false" lazy="false"
	 * @hibernate.collection-key column="COLLECTION_PROTOCOL_ID"
	 * @hibernate.collection-many-to-many class="edu.wustl.catissuecore.domain.DistributionProtocol" column="DISTRIBUTION_PROTOCOL_ID"
	 * @return Returns the collection of Studies for this Protocol.
	 */
	public Collection getDistributionProtocolCollection()
	{
		return distributionProtocolCollection;
	}

	/**
	 * @param studyCollection The studyCollection to set.
	 */
	public void setDistributionProtocolCollection(Collection distributionProtocolCollection)
	{
		this.distributionProtocolCollection = distributionProtocolCollection;
	}

	/**
	 * Returns the collection of Users(ProtocolCoordinators) for this Protocol.
	 * @hibernate.set name="userCollection" table="CATISSUE_COLLECTION_COORDINATORS" 
	 * cascade="save-update" inverse="false" lazy="false"
	 * @hibernate.collection-key column="COLLECTION_PROTOCOL_ID"
	 * @hibernate.collection-many-to-many class="edu.wustl.catissuecore.domain.User" column="USER_ID"
	 * @return The collection of Users(ProtocolCoordinators) for this Protocol.
	 */
	public Collection getUserCollection()
	{

		return userCollection;
	}

	/**
	 * @param userCollection The userCollection to set.
	 */
	public void setUserCollection(Collection userCollection)
	{
		this.userCollection = userCollection;
	}

	/**
	 * Returns the collection of CollectionProtocolEvents for this Protocol.
	 * @hibernate.set name="collectionProtocolEventCollection"
	 * table="CATISSUE_COLLECTION_PROTOCOL_EVENT" cascade="save-update"
	 * inverse="true" lazy="false"
	 * @hibernate.collection-key column="COLLECTION_PROTOCOL_ID"
	 * @hibernate.collection-one-to-many class="edu.wustl.catissuecore.domain.CollectionProtocolEvent"
	 * @return The collection of CollectionProtocolEvents for this Protocol.
	 */
	public Collection getCollectionProtocolEventCollection()
	{
		return collectionProtocolEventCollection;
	}

	/**
	 * @param collectionProtocolEventCollection
	 * The collectionProtocolEventCollection to set.
	 */
	public void setCollectionProtocolEventCollection(Collection collectionProtocolEventCollection)
	{
		this.collectionProtocolEventCollection = collectionProtocolEventCollection;
	}
	
	   /**
     * This function Copies the data from an CollectionProtocolForm object to a CollectionProtocol object.
     * @param CollectionProtocol An CollectionProtocolForm object containing the information about the CollectionProtocol.  
     * */
    public void setAllValues(AbstractActionForm abstractForm)
    {
    	System.out.println("setAllValues ");
        try
        {
        	CollectionProtocolForm cpForm = (CollectionProtocolForm) abstractForm;
        	this.systemIdentifier = new Long(cpForm.getSystemIdentifier());
        	this.title = cpForm.getTitle();
        	this.shortTitle = cpForm.getShortTitle();
        	this.irbIdentifier = cpForm.getIrbID();
        	
//        	this.startDate = Utility.parseDate(cpForm.getStartDate(),"MM-DD-YYYY");
//        	this.endDate = Utility.parseDate(cpForm.getEndDate(),"MM-DD-YYYY");

        	System.out.println("cpForm.getStartDate() "+startDate);
        	System.out.println("cpForm.getEndDate() "+endDate);
        	
        	this.enrollment = new Integer(cpForm.getEnrollment());
        	this.descriptionURL = cpForm.getDescriptionURL();
        	
        	System.out.println("cpForm.getPrincipalInvestigatorId() "+cpForm.getPrincipalInvestigatorId());
        	principalInvestigator  =new User();
        	this.principalInvestigator.setSystemIdentifier(new Long(cpForm.getPrincipalInvestigatorId()));
        	
        	long [] coordinatorsArr = cpForm.getProtocolCoordinatorIds();
        	for (int i = 0; i < coordinatorsArr.length; i++)
			{
        		User coordinators = new User();
        		coordinators.setSystemIdentifier(new Long(coordinatorsArr[i]));
        		userCollection.add(coordinators);
			}
        	
	        Map map = cpForm.getValues();
	        map = fixMap(map);
	        System.out.println("MAP "+map);
	        MapDataParser parser = new MapDataParser("edu.wustl.catissuecore.domain");
	        this.collectionProtocolEventCollection = parser.generateData(map);
	        System.out.println("storageContainerDetailsCollection "+this.collectionProtocolEventCollection);
        }
        catch (Exception excp)
        {
        	excp.printStackTrace();
            //Logger.out.error(excp.getMessage());
        }
    }
    
    
	//SpecimenRequirement#FluidSpecimenRequirement:1.specimenType", "Blood");
	private Map fixMap(Map orgMap)
	{
		Map replaceMap = new HashMap();
		Map unitMap = new HashMap();
		unitMap.put("Cell","CellCount");
		unitMap.put("Fluid","MiliLiter");
		unitMap.put("Tissue","Gram");
		unitMap.put("Molecular","MicroGram");
		
		Iterator it = orgMap.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String)it.next();
			if(key.indexOf("specimenClass")!=-1)
			{
				String value = (String)orgMap.get(key);
				String replaceWith = "SpecimenRequirement"+"#"+value+"SpecimenRequirement";
				
				key = key.substring(0,key.lastIndexOf("_"));
				String newKey = key.replaceFirst("SpecimenRequirement",replaceWith);
				
				replaceMap.put(key,newKey);
			}
		}
		
		Map newMap = new HashMap();
		it = orgMap.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String)it.next();
			String value = (String)orgMap.get(key);
			if(key.indexOf("SpecimenRequirement")==-1)
			{
				newMap.put(key,value);
			}
			else
			{
				if(key.indexOf("specimenClass")==-1)
				{
					String keyPart, newKeyPart;
					if(key.indexOf("quantityIn")!=-1)
					{
						keyPart = "quantityIn";
						
						String searchKey = key.substring(0,key.lastIndexOf("_"))+"_specimenClass";
						String specimenClass = (String)orgMap.get(searchKey);
						String unit = (String)unitMap.get(specimenClass);
						newKeyPart = keyPart + unit;
						
						key = key.replaceFirst(keyPart,newKeyPart);
					}
					//Replace # and class name and FIX for abstract class
					keyPart = key.substring(0,key.lastIndexOf("_"));
					newKeyPart = (String)replaceMap.get(keyPart);
					key = key.replaceFirst(keyPart,newKeyPart);
					newMap.put(key,value);
				}
			}
		}		
		return newMap;
	}
}