/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.toolkit.decomposition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.helpers.information.services.CommunicationLinkExt;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.LogicalComponentExt;
import org.polarsys.capella.core.model.helpers.RefinementLinkExt;

/**
 */
public class DecompositionItem {
  public static final int ASSIGNED = 1;
  public static final int UNASSIGNED = 2;
  public static final int AMBIGUOUS = 3;

  private String _name;
  private Object _value;
  private int _status;
  private String _statusMessage;
  private DecompositionComponent   _parentComponent;
  private List<DecompositionItemService> _serviceItems= new ArrayList<DecompositionItemService>();
  private boolean _alreadyDecomposed;
  private String _path;
  private List<Object> _originInterfaces;

  private boolean _IsInterfaceUsage;

  private Map<Integer, Set<String>> _statusDecompositionMap;

  private Set<String> _messages;

  /**
   * Default constructor
   */
  public DecompositionItem() {
    this(null, null, -1, false, ""); //$NON-NLS-1$
  }

  /**
   * Constructor
   * @param name_p
   *          the name
   * @param value_p
   *          the value
   * @param status_p
   *          the status
   */
  public DecompositionItem(String name_p, Object value_p, int status_p) {
    setName(name_p);
    setValue(value_p);
    setStatus(status_p);
    setStatusMessage(""); //$NON-NLS-1$
    _statusDecompositionMap = new HashMap<Integer, Set<String>>(1);
    setMessages(new TreeSet<String>());
    _originInterfaces = new ArrayList<Object>();
  }

  /**
   * Constructor
   * @param name_p
   *          the name
   * @param value_p
   *          the value
   * @param status_p
   *          the status
   * @param used_p
   *          flag to indicate whether the interface is used or implemented
   */
  public DecompositionItem(String name_p, Object value_p, int status_p, boolean used_p, String path_p) {
    this(name_p, value_p, status_p);
    setInterfaceUsage(used_p);
    setPath(path_p);
  }

  /**
   * @return the name
   */
  public String getName() {
    return _name;
  }

  /**
   * @param name_p
   *          the name to set
   */
  public void setName(String name_p) {
    _name = name_p;
  }

  /**
   * @return the value
   */
  public Object getValue() {
    return _value;
  }

  /**
   * @param value_p
   *          the value to set
   */
  public void setValue(Object value_p) {
    _value = value_p;
  }

  /**
   * @return the status to change the color of the node
   */
  public int getStatus() {
    return _status;
  }

  /**
   * Sets the status
   * @param status_p
   *          the status
   */
  public void setStatus(int status_p) {
    _status = status_p;
  }

  /**
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object object_p) {
  	if (object_p instanceof DecompositionItem) {
		  if (this == object_p) {
		  	return true;		  	
		  } else if (getValue() == null) {
		  	return false;		  	
		  } else {
		  	DecompositionItem item = (DecompositionItem) object_p;
		  	Object value1 = getValue();
		  	Object value2 = item.getValue();
		  	if (value1 instanceof Interface && value2 instanceof Interface) {
		  		return value1.equals(value2)
		  				&& isInterfaceUsage() == item.isInterfaceUsage();		  		
		  	} else if (value1 instanceof CommunicationLink && value2 instanceof CommunicationLink) {
		  		return CommunicationLinkExt.isSameCommunication((CommunicationLink) value1, (CommunicationLink) value2);
		  	}
		  }
	  }
	  return false;
  }

  /**
   * @return the statusMessage
   */
  public String getStatusMessage() {
    return _statusMessage;
  }

  /**
   * @param statusMessage_p
   *          the statusMessage to set
   */
  public void setStatusMessage(String statusMessage_p) {
    _statusMessage = statusMessage_p;
  }

  /**
   * @return the used
   */
  public boolean isInterfaceUsage() {
    return _IsInterfaceUsage;
  }

  /**
   * @param used_p
   *          the used to set
   */
  public void setInterfaceUsage(boolean used_p) {
    _IsInterfaceUsage = used_p;
  }

  /**
   * @return the parentComponent
   */
  public DecompositionComponent getParentComponent() {
    return _parentComponent;
  }

  /**
   * @param parentComponent_p
   *          the parentComponent to set
   */
  public void setParentComponent(DecompositionComponent parentComponent_p) {
    _parentComponent = parentComponent_p;
  }

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return getName();
  }

  /**
   * Clones the current {@link DecompositionItem}
   * @return copy of the current {@link DecompositionItem}
   */
  public DecompositionItem getCopy() {
    DecompositionItem copy = new DecompositionItem(this.getName(), this.getValue(), this.getStatus(), this.isInterfaceUsage(), this.getPath());
    // Add copy for all DecompositionItemService owned by current DecompositionItem
    for (DecompositionItemService itemSce : this.getServiceItems()) {
    	DecompositionItemService itemSceCopy = itemSce.getCopy();
    	itemSceCopy.setParentDecompositionItem(copy);
		copy.addServiceItems(itemSceCopy);
	}
    return copy;
  }

  /**
   * @return true if the item is already decomposed, false otherwise
   */
  public boolean isAlreadyDecomposed() {
    return _alreadyDecomposed;
  }

  /**
   * @param alreadyDecomposed_p
   *          the alreadyDecomposed to set
   */
  public void setAlreadyDecomposed(boolean alreadyDecomposed_p) {
    _alreadyDecomposed = alreadyDecomposed_p;
  }

  /**
   * Adds status message to the item
   * @param status_p
   *          the status (one of UNASSIGNED, ASSIGNED, or AMBIGUOUS)
   * @param decompositionName_p
   *          the message for the status
   */
  @SuppressWarnings("boxing")
  public void addDecompositionNameForStatus(int status_p, String decompositionName_p) {
    Set<String> tmp = null;
    if (_statusDecompositionMap.containsKey(status_p)) {
      tmp = _statusDecompositionMap.get(status_p);
      tmp.add(decompositionName_p);
      return;
    }
    tmp = new TreeSet<String>();
    tmp.add(decompositionName_p);
    _statusDecompositionMap.put(status_p, tmp);
  }

  /**
   * Removes the status message
   * @param message_p
   *          the message to be removed
   */
  @SuppressWarnings("boxing")
  public void removeStatus(String message_p) {
    for (int key : _statusDecompositionMap.keySet()) {
      Set<String> tmp = _statusDecompositionMap.get(key);
      if (tmp.remove(message_p)) {
        return;
      }
    }
  }

  //Check if the current 'DecompositionItem' (Interface) is internal for the parent Component in the model
  public boolean isInternal() {
	  if (this.getValue() instanceof Interface) {
	  	if (this.getValue() == null) return true; // Case new Interface
	  	else if (this.getParentComponent().getValue() != null) {
	  		Interface currentItf = (Interface) this.getValue();
	  		// Check if the Interface have a Refinement link toward another Interface
	  		if (RefinementLinkExt.getRefinementRelatedTargetElements(currentItf, CsPackage.Literals.INTERFACE).size() != 0) {
	  			// Check if the Interface is under the LogicalComponent in model
	  			LogicalComponent lc = (LogicalComponent) this.getParentComponent().getValue();
	  			for (CapellaElement meloElt : LogicalComponentExt.getAllInterfacesInLogicalComponent(lc)) {
	  				Interface itf = (Interface) meloElt;
	  				if (currentItf.equals(itf)) return true;
	  			}
	  		}
	  	}	  	
	  }
	  return false;
  }
  
  
  /**
   * Adds status message to get displayed for synthesis check
   * @param message_p
   *          the message to be added
   */
  public void addStatusMessage(String message_p) {
    _messages.add(message_p);
  }

  /**
   * Gets the key set of the status map
   * @return Set of status
   */
  public Set<Integer> getStatusKeys() {
    return _statusDecompositionMap.keySet();
  }

  /**
   * Gets the decomposition names associated with a particular status
   * @param key
   *          the status
   * @return Set of decomposition names
   */
  @SuppressWarnings("boxing")
  public Set<String> getDecompositionNames(int key) {
    return _statusDecompositionMap.get(key);
  }

  /**
   * @return the assignments
   */
  public Set<String> getMessages() {
    return _messages;
  }

  /**
   * @param assignments_p
   *          the assignments to set
   */
  public void setMessages(Set<String> assignments_p) {
    _messages = assignments_p;
  }

  /**
   * Clears all the messages
   */
  public void clearMessages() {
    _statusDecompositionMap.clear();
    setMessages(new TreeSet<String>());
  }

  /**
   * @return the path
   */
  public String getPath() {
    return _path;
  }

  /**
   * @param path_p the path to set
   */
  public void setPath(String path_p) {
    _path = path_p;
  }

public List<DecompositionItemService> getServiceItems() {
	return _serviceItems;
}

public void setServiceItems(List<DecompositionItemService> items_p) {
	_serviceItems = items_p;
}

public void addServiceItems(DecompositionItemService items_p) {
	_serviceItems.add(items_p);
}

public void removeItem(DecompositionItemService itemSce_p) {
	_serviceItems.remove(itemSce_p);
	
}

/**
 * @return the originInterfaces
 */
public List<Object> getOriginInterfaces() {
  return _originInterfaces;
}

/**
 * @param originInterface_p the originInterface to add
 */
public void addOriginInterfaces(Object originInterface_p) {
  _originInterfaces.add(originInterface_p);
}

}
