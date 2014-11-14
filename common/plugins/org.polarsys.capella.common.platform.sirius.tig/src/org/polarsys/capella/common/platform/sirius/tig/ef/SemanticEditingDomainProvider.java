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

import org.eclipse.emf.transaction.TransactionalEditingDomain;

import org.polarsys.capella.common.tig.efprovider.TigEfProvider;
import org.polarsys.capella.common.tig.ef.ExecutionManager;
import org.polarsys.capella.common.tig.ef.IEditingDomainProvider;
import org.polarsys.capella.common.tig.ef.registry.ExecutionManagerRegistry;

/**
 * Provides a way to get the semantic Editing Domain implementation from the {@link ExecutionManagerRegistry}.<br>
 * Use {@link TigEfProvider#getExecutionManagerName()} to request the {@link ExecutionManagerRegistry} that returns an {@link ExecutionManager} pointing to the
 * {@link TransactionalEditingDomain}.
 */
public class SemanticEditingDomainProvider implements IEditingDomainProvider {
  /**
   * @see org.polarsys.capella.common.tig.ef.IEditingDomainProvider#getEditingDomain()
   */
  public TransactionalEditingDomain getEditingDomain() {
    // Ask the EMF transactional registry the capella editing domain from its id.
    return TransactionalEditingDomain.Registry.INSTANCE.getEditingDomain(SemanticEditingDomainFactory.EDITING_DOMAIN_ID);
  }
}