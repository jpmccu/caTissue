package de_entity.service.globus;

import de_entity.service.Catissue_DEImpl;

import java.rmi.RemoteException;

/** 
 * DO NOT EDIT:  This class is autogenerated!
 *
 * This class implements each method in the portType of the service.  Each method call represented
 * in the port type will be then mapped into the unwrapped implementation which the user provides
 * in the Catissue_DEImpl class.  This class handles the boxing and unboxing of each method call
 * so that it can be correctly mapped in the unboxed interface that the developer has designed and 
 * has implemented.  Authorization callbacks are automatically made for each method based
 * on each methods authorization requirements.
 * 
 * @created by Introduce Toolkit version 1.4
 * 
 */
public class Catissue_DEProviderImpl{
	
	Catissue_DEImpl impl;
	
	public Catissue_DEProviderImpl() throws RemoteException {
		impl = new Catissue_DEImpl();
	}
	

}
