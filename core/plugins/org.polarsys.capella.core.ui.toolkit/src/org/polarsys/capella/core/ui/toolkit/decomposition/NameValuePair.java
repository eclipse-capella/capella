/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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

import java.util.Set;

/**
 * Wrapper for Interface
 * @deprecated Use {@link DecompositionItem} instead
 */
public interface NameValuePair {

  public static final int ASSIGNED = 1;
  public static final int UNASSIGNED = 2;
  public static final int AMBIGUOUS = 3;

  /**
   * @return the name of the object
   */
  public String getName();

  /**
   * @return the value
   */
  public Object getValue();

  /**
   * @return the status to change the color of the node
   */
  public int getStatus();

  /**
   * Sets the status
   * @param status_p
   *          the status
   */
  public void setStatus(int status_p);

  /**
   * @return the statusMessage
   */
  public String getStatusMessage();

  /**
   * Sets the status message
   * @param message_p
   *          the message
   */
  public void setStatusMessage(String message_p);

  /**
   * Adds a decomposition name to the {@link NameValuePair}
   * @param decompositionName_p
   *          the decomposition name
   */
  public void addDecompositionNames(String decompositionName_p);

  /**
   * Gets the names of all the decompositions the {@link NameValuePair} is appearing
   * @return {@link Set} of all decomposition names
   */
  public Set<String> getDecompositionNames();

  /**
   * Flag to check whether this component is used or implemented
   * @return true if the interface is used by the component
   */
  public boolean isUsed();

  /**
   * @return the parent component of the name value pair
   */
  public DecompositionComponent getParentComponent();

  /**
   * Sets the parent component
   * @param parentComponent_p
   *          the decomposition component
   */
  public void setParentComponent(DecompositionComponent parentComponent_p);

  /**
   * Clones the current {@link NameValuePair}
   * @return copy of the current {@link NameValuePair}
   */
  public NameValuePair getCopy();
  
  public boolean isAlreadyDecomposed();
  public void setAlreadyDecomposed(boolean alreadyDecomposed_p);
}
