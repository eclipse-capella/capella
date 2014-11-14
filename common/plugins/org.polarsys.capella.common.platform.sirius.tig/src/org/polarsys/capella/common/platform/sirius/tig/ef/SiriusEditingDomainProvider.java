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
package org.polarsys.capella.common.platform.sirius.tig.ef;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import org.polarsys.capella.common.tig.efprovider.TigEfProvider;
import org.polarsys.capella.common.tig.ef.ExecutionManager;
import org.polarsys.capella.common.tig.ef.registry.ExecutionManagerRegistry;

import org.eclipse.sirius.common.tools.api.editing.IEditingDomainFactory;


/**
 * Provides Sirius with the semantic {@link TransactionalEditingDomain}.<br>
 * An {@link ExecutionManager} is used to retrieve it.
 */
public class SiriusEditingDomainProvider implements IEditingDomainFactory {
  /**
   * The returned editing domain is always retrieved from the execution manager
   */
  public TransactionalEditingDomain getEditingDomain() {
    TransactionalEditingDomain result = null;
    // Get the execution manager from its id.
    ExecutionManager capellaExecutionManager = ExecutionManagerRegistry.getInstance().getExecutionManager(TigEfProvider.getExecutionManagerName());
    if (null != capellaExecutionManager) {
      // Get the editing domain.
      result = capellaExecutionManager.getEditingDomain();
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  public TransactionalEditingDomain createEditingDomain(ResourceSet arg0_p) {
    return getEditingDomain();
  }

  /**
   * {@inheritDoc}
   */
  public TransactionalEditingDomain getEditingDomain(ResourceSet arg0_p) {
    return getEditingDomain();
  }

  /**
   * {@inheritDoc}
   */
  public TransactionalEditingDomain createEditingDomain() {
    return getEditingDomain();
  }
}
