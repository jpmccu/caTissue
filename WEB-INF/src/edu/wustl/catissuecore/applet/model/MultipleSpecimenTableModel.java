
package edu.wustl.catissuecore.applet.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.wustl.catissuecore.applet.AppletConstants;
import edu.wustl.catissuecore.util.global.Constants;

/**
 * This is table model for multiple specimen functionality.
 * 
 * @author  Rahul Ner
 * @version 1.1
 *
 */
public class MultipleSpecimenTableModel extends BaseTabelModel
{

	/**
	 * Default Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * attributes of the specimen for which user can specify the values. 
	 */
	String[] specimenAttribute = {"SpecimenCollectionGroup_name", "parentSpecimen", "label",
			"barcode", "class", "type", "SpecimenCharacteristics_tissueSite",
			"SpecimenCharacteristics_tissueSide", "pathologicalStatus", "Quantity_value",
			"concentrationInMicrogramPerMicroliter", "StorageContainer_temp", "comments",
			"specimenEventCollection", "externalIdentifierCollection", "biohazardCollection",
			"derive"};

	/**
	 * Row headers for the attributes. This corrosponds to display value for each of the  specimenAttribute in that order.
	 */
	private static final String[]  rowHeaders = {"Specimen Group Name", "Parent", "Label", "Barcode", "Class", "Type",
			"Tissue Site", "Tissue Side", "Pathological Status", "Quantity", "Concentration",
			"Storage Position", "Comments", "Events", "External Identifier(s)", "Biohazards",
			"Derive"};

	/**
	 * Data structure maintianed by the model. Its key format is as follows:
	 * 
	 * key = Specimen:[ColumnNo]_[SpecimenAttribute]
	 * e.g for specimen in column 3 if user enter "my specimen" value for specimen label
	 * then this map will contain value as "my specimen" for the key "Specimen:3_label"
	 * 
	 */
	Map specimenMap;

	int columnCount;

	/** This is a map that holds options to be displayed for various attributes of the specimen
	 *
	 * It contains 
	 * 
	 * 1. MAP - specimen class ->Array of values for specimen Type
	 * 1. Array of values for  Specimen class  
	 * 2. Array of values for Tissue site
	 * 3. Array of values for Tissue side
	 * 4. Array of values for Pathological
	 * */
	Map specimenAttributeOptions;

	/**
	 * set default map. 
	 * @param specimenAttributeOptions  initialzation map.
	 */
	public MultipleSpecimenTableModel(int initialColumnCount, Map specimenAttributeOptions)
	{
		specimenMap = new HashMap();
		this.columnCount = initialColumnCount;
		this.specimenAttributeOptions = specimenAttributeOptions;
	}

	/**
	 * @see javax.swing.table.DefaultTableModel#getValueAt(int, int)
	 */
	public Object getValueAt(int row, int column)
	{
/*		if (column == 0)
		{
			return rowHeaders[row];
		}
      System.out.println("getValueAt " + row + " " + column + ": " + getKey(row,column) + "  " + specimenMap.get(getKey(row,column)));
*/      
		return specimenMap.get(getKey(row,column));
	}

	/**
	 * @see javax.swing.table.DefaultTableModel#setValueAt(java.lang.Object, int, int)
	 */
	public void setValueAt(Object value, int row, int column)
	{
/*		if (column != 0)
		{
			specimenMap.put(getKey(row,column), value);
		}
       System.out.println("setValueAt " + row + " " + column + ": " + specimenMap.get(getKey(row,column)) + value);
*/       
		specimenMap.put(getKey(row,column), value);

	}

	/**
	 * @see javax.swing.table.DefaultTableModel#getColumnCount()
	 */
	public int getColumnCount()
	{
		return columnCount;
	}

	/** 
	 * @see javax.swing.table.DefaultTableModel#getRowCount()
	 */
	public int getRowCount()
	{
		return specimenAttribute.length;
	}

	public Map getMap()
	{
		return specimenMap;
	}

	/**
	 * @see javax.swing.table.DefaultTableModel#getColumnName(int)
	 */
	public String getColumnName(int columnNo)
	{
			return "Specimen " + (columnNo+1) ;
	}

	/**
	 * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
	 */
	public Class getColumnClass(int colNo)
	{

		if (colNo == 0)
		{
			return String.class;
		}
		else
		{
			return SpecimenColumnModel.class;
		}
	}

	/**
	 * This method return row headers.
	 * @return 
	 */
	public static  Object[] getRowHeaders()
	{
		return rowHeaders;
	}

	/**
	 * This method initialize data lists 
	 */
/*	private Map initDataLists()
	{
		BaseAppletModel appletModel = new BaseAppletModel();
		appletModel.setData(new HashMap());
		try
		{
			appletModel = (BaseAppletModel) AppletServerCommunicator.doAppletServerCommunication(
					"http://localhost:8080/catissuecore/MultipleSpecimenAppletAction.do?method=initData", appletModel);

			Map tempMap = appletModel.getData();
			 System.out.println(tempMap.get(Constants.SPECIMEN_TYPE_MAP));
			 System.out.println(tempMap.get(Constants.SPECIMEN_CLASS_LIST));
			 System.out.println(tempMap.get(Constants.TISSUE_SITE_LIST));
			 System.out.println(tempMap.get(Constants.TISSUE_SIDE_LIST));
			 System.out.println(tempMap.get(Constants.PATHOLOGICAL_STATUS_LIST));
			 

			return appletModel.getData();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception");
		}

		return null;
	}

*/	/**
	 * returns specimen type list for given specimen class.
	 * 
	 * @param specimenClass
	 * @return
	 */
	public List getSpecimenTypeList(String specimenClass)
	{
//		Map specimenTypeMap = (Map) specimenAttributeOptions.get(Constants.SPECIMEN_TYPE_MAP);
//		return (List) specimenTypeMap.get(specimenClass);
		ArrayList aList = new ArrayList();
		for(int i=1;i<5;i++)
			aList.add(specimenClass + "_"+i);
		
		return aList;
	}

	public Object[] getSpecimenTypeValues(String specimenClass)
	{
		System.out.println("get type values called");
		/*String specimenClass = (String) getValueAt(AppletConstants.SPECIMEN_CLASS_ROW_NO, column);*/
		if (specimenClass == null ) {
			specimenClass = Constants.SELECT_OPTION; 
	    }
		Map specimenTypeMap = (Map) specimenAttributeOptions.get(Constants.SPECIMEN_TYPE_MAP);
		return (Object[]) specimenTypeMap.get(specimenClass);
	} 

	/**
	 * returns specimen class list
	 * @return
	 * 
	 */
	public Object[] getSpecimenClassValues()
	{
		return (Object[]) specimenAttributeOptions.get(Constants.SPECIMEN_CLASS_LIST);
	}

	/**
	 * @return tissue site list
	 */
	public Object[] getTissueSiteValues()
	{
		return (Object[]) specimenAttributeOptions.get(Constants.TISSUE_SITE_LIST);
	}

	/**
	 * @return tissue side list
	 */
	public Object[] getTissueSideValues()
	{
		return (Object[]) specimenAttributeOptions.get(Constants.TISSUE_SIDE_LIST);
	}

	/**
	 * @return PATHOLOGICAL STATUS LIST
	 */
	public Object[] getPathologicalStatusValues()
	{
		return (Object[]) specimenAttributeOptions.get(Constants.PATHOLOGICAL_STATUS_LIST);
	}

	/**
	 * returns quantity unit for given specimen 
	 * 
	 * @param colNo spcimen column no
	 * @return unit
	 */
	public String getQuantityUnit(int colNo)
	{

		String specimenType = (String) getValueAt(AppletConstants.SPECIMEN_TYPE_ROW_NO, colNo);
		String subTypeValue = (String) getValueAt(AppletConstants.SPECIMEN_CLASS_ROW_NO, colNo);

		String unit = "";

		if (specimenType.equals("Fluid"))
		{
			unit = Constants.UNIT_ML;
		}
		else if (specimenType.equals("Cell"))
		{
			unit = Constants.UNIT_CC;

		}
		else if (specimenType.equals("Molecular"))
		{
			unit = Constants.UNIT_MG;
		}
		else if (specimenType.equals("Tissue"))
		{
			if (subTypeValue.equals(Constants.MICRODISSECTED))
			{
				unit = Constants.UNIT_CL;
			}
			else if (subTypeValue.equals(Constants.FROZEN_TISSUE_SLIDE)
					|| subTypeValue.equals(Constants.FIXED_TISSUE_BLOCK)
					|| subTypeValue.equals(Constants.FROZEN_TISSUE_BLOCK)
					|| subTypeValue.equals(Constants.NOT_SPECIFIED)
					|| subTypeValue.equals(Constants.FIXED_TISSUE_SLIDE))
			{
				unit = Constants.UNIT_CN;
			}
			else
			{
				unit = Constants.UNIT_GM;
			}
		}

		return unit;

	}
	
	public void specimenClassUpdated(int columnNo)
	{
		//---
		//this.fireTableStructureChanged() ;
		//---
/*		fireTableCellUpdated(AppletConstants.SPECIMEN_BARCODE_ROW_NO,columnNo);
		fireTableCellUpdated(AppletConstants.SPECIMEN_TYPE_ROW_NO,columnNo);
	
		
		System.out.println("updating type " + AppletConstants.SPECIMEN_TYPE_ROW_NO +  " "+ columnNo);
*/	}
	
	public String getKey(int row, int column) {
		String specimenKey = AppletConstants.SPECIMEN_PREFIX + String.valueOf(column+1) + "_"
		+ specimenAttribute[row];
		
		return specimenKey;
	}

	/**
	 * Concentration is enabled only in case of Molecular class.
	 * so this method returns true if Molecular class is selected for the given column.
	 * 
	 * @param column
	 * @return
	 */
	public boolean getConcentrationStatus(int column)
	{	
		String specimenClass = (String) getValueAt(AppletConstants.SPECIMEN_CLASS_ROW_NO, column);
		
		if(specimenClass.equalsIgnoreCase(Constants.MOLECULAR)) {
			return true;
		} 
		
		return false;
	}
	
	public void setStorageDetails(String specimenMapKey, String storageId,String storageLabel,String xPos,String yPos) {
		int colNo = Integer.parseInt(specimenMapKey);
		specimenMap.put(AppletConstants.SPECIMEN_PREFIX + (colNo+1) + "_" + "StorageContainer_id" ,new Long(storageId));
		specimenMap.put(AppletConstants.SPECIMEN_PREFIX + (colNo+1) + "_" + "positionDimensionOne", xPos );
		specimenMap.put(AppletConstants.SPECIMEN_PREFIX + (colNo+1) + "_" + "positionDimensionTwo", yPos );
		
		String storageInfo = storageLabel + "," + xPos + "," + yPos;
		
		setValueAt(storageInfo, AppletConstants.SPECIMEN_STORAGE_LOCATION_ROW_NO, colNo);
		
		System.out.println("setting " + storageInfo + "to " +  AppletConstants.SPECIMEN_STORAGE_LOCATION_ROW_NO + "  " +  colNo);
	}
	
	public void addColumn() {
		columnCount++;
	}


}