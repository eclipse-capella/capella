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
package org.polarsys.capella.core.libraries.model;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.core.data.capellamodeller.Project;

/**
 * A capella model have a project (root element)
 */
public interface ICapellaModel extends IModel {

  /**
   * Returns the root project of this model, in the given editing domain.
   * (we need to provide an editing domain to retrieve the correct project loaded)
   */
  public Project getProject(TransactionalEditingDomain domain_p);

  /** 
   * @return true if this model is a library.
   */
  public boolean isLibrary();
  
}
