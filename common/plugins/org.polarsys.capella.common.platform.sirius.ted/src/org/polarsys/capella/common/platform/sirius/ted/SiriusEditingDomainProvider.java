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

package org.polarsys.capella.common.platform.sirius.ted;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.common.tools.api.editing.IEditingDomainFactory;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;


/**
 * Provides Sirius with the semantic {@link TransactionalEditingDomain}.<br>
 * An {@link ExecutionManager} is used to retrieve it.
 */
public class SiriusEditingDomainProvider implements IEditingDomainFactory {

  /**
   * {@inheritDoc}
   */
  public TransactionalEditingDomain createEditingDomain(ResourceSet arg0) {
    return createEditingDomain();
  }

  /**
   * {@inheritDoc}
   */
  public TransactionalEditingDomain getEditingDomain(ResourceSet arg0) {
    TransactionalEditingDomain result = null;
    // Get the execution manager from its id.
    ExecutionManager capellaExecutionManager = ExecutionManagerRegistry.getInstance().getExecutionManager(TransactionUtil.getEditingDomain(arg0));
    if (null != capellaExecutionManager) {
      // Get the editing domain.
      result = capellaExecutionManager.getEditingDomain();
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  public TransactionalEditingDomain createEditingDomain() {
    TransactionalEditingDomain result = null;
    // Get the execution manager from its id.
    ExecutionManager capellaExecutionManager = ExecutionManagerRegistry.getInstance().addNewManager();
    if (null != capellaExecutionManager) {
      // Get the editing domain.
      result = capellaExecutionManager.getEditingDomain();
    }
    return result;
  }
}
