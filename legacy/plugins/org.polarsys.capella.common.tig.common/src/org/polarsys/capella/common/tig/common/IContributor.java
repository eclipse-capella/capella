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
public interface IContributor {
  /**
   * Set given id.
   * @param id_p
   */
  void setId(String id_p);

  /**
   * Get id.
   * @return a not <code>null</code> id.
   */
  String getId();
}
