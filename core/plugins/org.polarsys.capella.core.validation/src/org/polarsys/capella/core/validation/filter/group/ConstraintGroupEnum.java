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
package org.polarsys.capella.core.validation.filter.group;

import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.eclipse.emf.validation.service.IParameterizedConstraintDescriptor;


/**
 * Definition for group filtering on constraints
 *
 */
public enum ConstraintGroupEnum {

  MERGE ( 0, "MERGE" ); //$NON-NLS-1$
  
  private int _id;
  private String _literal;
  
  /** Constructor */
  ConstraintGroupEnum(int id, String literal) {
    _literal = literal;
  }
  
  /** Accessor on the id value */
  public int getId() {
    return _id;
  }
  
  /** Accessor on the literal value */
  public String getLiteral() {
    return _literal;
  }
  
  /**
   * Check whether a given constraints is member of a given group
   * @param constraint_p the constraint to check 
   * @return <code>true</code> if yes, <code>false</code> otherwise
   */
  public boolean isConstraintContainedInto(IConstraintDescriptor constraint_p) {
   
    boolean result = false;
    
    if ( constraint_p instanceof IParameterizedConstraintDescriptor ) { // it should be...
    
      IParameterizedConstraintDescriptor xcd = (IParameterizedConstraintDescriptor) constraint_p;
      String value = xcd.getParameterValue(IConstraintGroupConstants.GROUP_PARAM_VALUE);
        
      result =  ( null != value && value.contains(getLiteral()) );
    }
    
    return result;
  }
  
}
