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
package org.polarsys.capella.common.tig.ef;

/**
 * Implementors can store an execution manager id to get the related instance afterwards.
 */
public interface IExecutionManagerUser {
  /**
   * Set the execution manager id.
   * @param executionManagerId_p
   */
  public void setExecutionManagerId(String executionManagerId_p);
}
