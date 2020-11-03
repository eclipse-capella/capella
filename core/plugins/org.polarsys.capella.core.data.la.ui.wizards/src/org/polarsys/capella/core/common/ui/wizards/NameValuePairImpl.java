/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.common.ui.wizards;

import java.util.Set;
import java.util.TreeSet;

import org.polarsys.capella.core.ui.toolkit.decomposition.DecompositionComponent;
import org.polarsys.capella.core.ui.toolkit.decomposition.DecompositionItem;
import org.polarsys.capella.core.ui.toolkit.decomposition.NameValuePair;

/**
 * @deprecated Use {@link DecompositionItem} instead
 */
public class NameValuePairImpl implements NameValuePair {
  private String name;
  private Object value;
  private int status;
  private String statusMessage;
  private Set<String> decompositionNames;
  private DecompositionComponent parentComponent;
  private boolean alreadyDecomposed;

  private boolean used;

  /**
   * Default constructor
   */
  public NameValuePairImpl() {
    this(null, null, -1, false);
  }

  /**
   * Constructor
   * @param name
   *          the name
   * @param value
   *          the value
   * @param status
   *          the status
   */
  public NameValuePairImpl(String name, Object value, int status) {
    setName(name);
    setValue(value);
    setStatus(status);
    setStatusMessage(""); //$NON-NLS-1$
    decompositionNames = new TreeSet<String>();
  }

  /**
   * Constructor
   * @param name
   *          the name
   * @param value
   *          the value
   * @param status
   *          the status
   * @param used
   *          flag to indicate whether the interface is used or implemented
   */
  public NameValuePairImpl(String name, Object value, int status, boolean used) {
    this(name, value, status);
    setUsed(used);
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
   * @see org.polarsys.capella.common.ui.toolkit.viewers.decomposition.NameValuePair#getStatus()
   */
  public int getStatus() {
    return status;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.decomposition.NameValuePair#setStatus(int)
   */
  public void setStatus(int status) {
    this.status = status;
  }

  /**
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object object) {
    if (object instanceof NameValuePair) {
      return getValue().equals(((NameValuePair) object).getValue());
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
   * @see org.polarsys.capella.common.ui.toolkit.viewers.decomposition.NameValuePair#addDecompositionNames(java.lang.String)
   */
  public void addDecompositionNames(String decompositionName) {
    decompositionNames.add(decompositionName);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.decomposition.NameValuePair#getDecompositionNames()
   */
  public Set<String> getDecompositionNames() {
    return decompositionNames;
  }

  /**
   * @return the used
   */
  public boolean isUsed() {
    return used;
  }

  /**
   * @param used
   *          the used to set
   */
  public void setUsed(boolean used) {
    this.used = used;
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
   * @see org.polarsys.capella.common.ui.toolkit.viewers.decomposition.NameValuePair#getCopy()
   */
  public NameValuePair getCopy() {
    NameValuePairImpl copy = new NameValuePairImpl(this.getName(), this.getValue(), this.getStatus(), this.isUsed());
    copy.decompositionNames = this.getDecompositionNames();
    return copy;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.decomposition.NameValuePair#isAlreadyDecomposed()
   */
  public boolean isAlreadyDecomposed() {
    return alreadyDecomposed;
  }

  /**
   * @param alreadyDecomposed the alreadyDecomposed to set
   */
  public void setAlreadyDecomposed(boolean alreadyDecomposed) {
    this.alreadyDecomposed = alreadyDecomposed;
  }
}
