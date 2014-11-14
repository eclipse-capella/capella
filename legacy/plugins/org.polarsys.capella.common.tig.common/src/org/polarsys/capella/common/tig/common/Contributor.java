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
package org.polarsys.capella.common.tig.common;

/**
 */
public class Contributor implements IContributor {
  /**
   * Id value.
   */
  private String _id;

  /**
   * @see org.polarsys.capella.common.tig.common.IContributor#getId()
   */
  public String getId() {
    return _id;
  }

  /**
   * @see org.polarsys.capella.common.tig.common.IContributor#setId(java.lang.String)
   */
  public void setId(String id_p) {
    _id = id_p;
  }
}
