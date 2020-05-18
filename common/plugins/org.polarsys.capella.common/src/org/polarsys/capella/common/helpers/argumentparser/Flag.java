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
package org.polarsys.capella.common.helpers.argumentparser;

/**
 *
 */
public final class Flag {

  private final String _name;
  private final boolean _isMandatory;
  private final int _numberOfExpectedData; 

  /** Accessor on flag Id */
  public String getName() {return _name;}
  /** Is this flag mandatory */
  public boolean isMandatory() {return _isMandatory;}
  /** Accessor on number of expected data linked to this flag */
  public int getNumberOfData() {return _numberOfExpectedData;}
  
  /**
   * Constructor
   * @param name of the flag
   * @param isMandatory is this flag mandatory
   * @param nbData number of expected data linked to this flag
   */
  public Flag(String name, boolean isMandatory, int nbData) {
    
    _name = name;
    _isMandatory = isMandatory;
    _numberOfExpectedData = nbData;

  }
  
}
