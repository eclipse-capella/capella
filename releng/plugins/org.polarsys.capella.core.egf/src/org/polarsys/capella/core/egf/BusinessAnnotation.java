/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.egf;

/**
 * Generation internal structure for a business annotation.
 *
 */
public class BusinessAnnotation {
  private String _namingAttribute;
  private String _idAttribute;
  
  /**
   * 
   */
  public BusinessAnnotation() {
  }

  /**
   * @return the namingAttribute
   */
  public String getNamingAttribute() {
    return _namingAttribute;
  }

  /**
   * @param namingAttribute_p the namingAttribute to set
   */
  public void setNamingAttribute(String namingAttribute_p) {
    _namingAttribute = namingAttribute_p;
  }

  /**
   * @return the idAttribute
   */
  public String getIdAttribute() {
    return _idAttribute;
  }

  /**
   * @param idAttribute_p the idAttribute to set
   */
  public void setIdAttribute(String idAttribute_p) {
    _idAttribute = idAttribute_p;
  }
  
  
  

}
