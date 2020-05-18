/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
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

  private String name;
  private Object value;
  private int status;
  private String statusMessage;
  private DecompositionComponent parentComponent;
  private List<DecompositionItemService> serviceItems = new ArrayList<>();
  private boolean alreadyDecomposed;
  private String path;
  private List<Object> originInterfaces;

  private boolean isInterfaceUsage;

  private Map<Integer, Set<String>> statusDecompositionMap;

  private Set<String> messages;

  /**
   * Default constructor
   */
  public DecompositionItem() {
    this(null, null, -1, false, ""); //$NON-NLS-1$
  }

  /**
   * Constructor
   * 
   * @param name
   *          the name
   * @param value
   *          the value
   * @param status
   *          the status
   */
  public DecompositionItem(String name, Object value, int status) {
    setName(name);
    setValue(value);
    setStatus(status);
    setStatusMessage(""); //$NON-NLS-1$
    statusDecompositionMap = new HashMap<>(1);
    setMessages(new TreeSet<String>());
    originInterfaces = new ArrayList<>();
  }

  /**
   * Constructor
   * 
   * @param name
   *          the name
   * @param value
   *          the value
   * @param status
   *          the status
   * @param used
   *          flag to indicate whether the interface is used or implemented
   */
  public DecompositionItem(String name, Object value, int status, boolean used, String path) {
    this(name, value, status);
    setInterfaceUsage(used);
    setPath(path);
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name
   *          the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the value
   */
  public Object getValue() {
    return value;
  }

  /**
   * @param value
   *          the value to set
   */
  public void setValue(Object value) {
    this.value = value;
  }

  /**
   * @return the status to change the color of the node
   */
  public int getStatus() {
    return status;
  }

  /**
   * Sets the status
   * 
   * @param status
   *          the status
   */
  public void setStatus(int status) {
    this.status = status;
  }

  /**
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object object) {
    if (object instanceof DecompositionItem) {
      if (this == object) {
        return true;
      } else if (getValue() == null) {
        return false;
      } else {
        DecompositionItem item = (DecompositionItem) object;
        Object value1 = getValue();
        Object value2 = item.getValue();
        if (value1 instanceof Interface && value2 instanceof Interface) {
          return value1.equals(value2) && isInterfaceUsage() == item.isInterfaceUsage();
        } else if (value1 instanceof CommunicationLink && value2 instanceof CommunicationLink) {
          return CommunicationLinkExt.isSameCommunication((CommunicationLink) value1, (CommunicationLink) value2);
        }
      }
    }
    return false;
  }
  
  @Override
  public int hashCode() {
	// To satisfy Sonar
	return super.hashCode();
  }

  /**
   * @return the statusMessage
   */
  public String getStatusMessage() {
    return statusMessage;
  }

  /**
   * @param statusMessage
   *          the statusMessage to set
   */
  public void setStatusMessage(String statusMessage) {
    this.statusMessage = statusMessage;
  }

  /**
   * @return the used
   */
  public boolean isInterfaceUsage() {
    return isInterfaceUsage;
  }

  /**
   * @param used
   *          the used to set
   */
  public void setInterfaceUsage(boolean used) {
    isInterfaceUsage = used;
  }

  /**
   * @return the parentComponent
   */
  public DecompositionComponent getParentComponent() {
    return parentComponent;
  }

  /**
   * @param parentComponent
   *          the parentComponent to set
   */
  public void setParentComponent(DecompositionComponent parentComponent) {
    this.parentComponent = parentComponent;
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
   * 
   * @return copy of the current {@link DecompositionItem}
   */
  public DecompositionItem getCopy() {
    DecompositionItem copy = new DecompositionItem(this.getName(), this.getValue(), this.getStatus(),
        this.isInterfaceUsage(), this.getPath());
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
    return alreadyDecomposed;
  }

  /**
   * @param alreadyDecomposed
   *          the alreadyDecomposed to set
   */
  public void setAlreadyDecomposed(boolean alreadyDecomposed) {
    this.alreadyDecomposed = alreadyDecomposed;
  }

  /**
   * Adds status message to the item
   * 
   * @param status
   *          the status (one of UNASSIGNED, ASSIGNED, or AMBIGUOUS)
   * @param decompositionName
   *          the message for the status
   */
  @SuppressWarnings("boxing")
  public void addDecompositionNameForStatus(int status, String decompositionName) {
    Set<String> tmp = null;
    if (statusDecompositionMap.containsKey(status)) {
      tmp = statusDecompositionMap.get(status);
      tmp.add(decompositionName);
      return;
    }
    tmp = new TreeSet<String>();
    tmp.add(decompositionName);
    statusDecompositionMap.put(status, tmp);
  }

  /**
   * Removes the status message
   * 
   * @param message
   *          the message to be removed
   */
  @SuppressWarnings("boxing")
  public void removeStatus(String message) {
    for (int key : statusDecompositionMap.keySet()) {
      Set<String> tmp = statusDecompositionMap.get(key);
      if (tmp.remove(message)) {
        return;
      }
    }
  }

  // Check if the current 'DecompositionItem' (Interface) is internal for the parent Component in the model
  public boolean isInternal() {
    if (this.getValue() == null)
      return true; // Case new Interface
    if (this.getValue() instanceof Interface && this.getParentComponent().getValue() != null) {
      Interface currentItf = (Interface) this.getValue();
      // Check if the Interface have a Refinement link toward another Interface
      if (!RefinementLinkExt.getRefinementRelatedTargetElements(currentItf, CsPackage.Literals.INTERFACE).isEmpty()) {
        // Check if the Interface is under the LogicalComponent in model
        LogicalComponent lc = (LogicalComponent) this.getParentComponent().getValue();
        for (CapellaElement meloElt : LogicalComponentExt.getAllInterfacesInLogicalComponent(lc)) {
          Interface itf = (Interface) meloElt;
          if (currentItf.equals(itf))
            return true;
        }
      }
    }

    return false;
  }

  /**
   * Adds status message to get displayed for synthesis check
   * 
   * @param message
   *          the message to be added
   */
  public void addStatusMessage(String message) {
    messages.add(message);
  }

  /**
   * Gets the key set of the status map
   * 
   * @return Set of status
   */
  public Set<Integer> getStatusKeys() {
    return statusDecompositionMap.keySet();
  }

  /**
   * Gets the decomposition names associated with a particular status
   * 
   * @param key
   *          the status
   * @return Set of decomposition names
   */
  @SuppressWarnings("boxing")
  public Set<String> getDecompositionNames(int key) {
    return statusDecompositionMap.get(key);
  }

  /**
   * @return the assignments
   */
  public Set<String> getMessages() {
    return messages;
  }

  /**
   * @param assignments
   *          the assignments to set
   */
  public void setMessages(Set<String> assignments) {
    this.messages = assignments;
  }

  /**
   * Clears all the messages
   */
  public void clearMessages() {
    statusDecompositionMap.clear();
    setMessages(new TreeSet<String>());
  }

  /**
   * @return the path
   */
  public String getPath() {
    return path;
  }

  /**
   * @param path
   *          the path to set
   */
  public void setPath(String path) {
    this.path = path;
  }

  public List<DecompositionItemService> getServiceItems() {
    return serviceItems;
  }

  public void setServiceItems(List<DecompositionItemService> items) {
    this.serviceItems = items;
  }

  public void addServiceItems(DecompositionItemService items) {
    serviceItems.add(items);
  }

  public void removeItem(DecompositionItemService itemSce) {
    serviceItems.remove(itemSce);

  }

  /**
   * @return the originInterfaces
   */
  public List<Object> getOriginInterfaces() {
    return originInterfaces;
  }

  /**
   * @param originInterface
   *          the originInterface to add
   */
  public void addOriginInterfaces(Object originInterface) {
    originInterfaces.add(originInterface);
  }

}
