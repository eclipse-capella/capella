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
package org.polarsys.capella.common.tig.ef.workspace;

import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.WorkspaceEditingDomainFactory;

import org.polarsys.capella.common.tig.ef.command.TigCommandStack;

/**
 * Creates a transactional editing domain that uses a {@link TigCommandStack}.
 */
public class TigTransactionalEditingDomainFactory extends WorkspaceEditingDomainFactory {
  /**
   * @see org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl.FactoryImpl#createEditingDomain()
   */
  @Override
  public synchronized TransactionalEditingDomain createEditingDomain() {
    // Create a command stack TIG-ready.
    TransactionalCommandStack stack = doCreateCommandStack();
    // Create an editing domain TIG-ready.
    TransactionalEditingDomain result = doCreateEditingDomain(stack);
    // Map resource set to editing domain.
    mapResourceSet(result);
    return result;
  }

  /**
   * Do create a custom transactional command stack.
   * @return
   */
  protected TransactionalCommandStack doCreateCommandStack() {
    return new TigCommandStack(OperationHistoryFactory.getOperationHistory());
  }

  /**
   * Do create a custom transactional editing domain using newly created custom custom stack.
   * @param stack_p
   * @return
   */
  protected TransactionalEditingDomain doCreateEditingDomain(TransactionalCommandStack stack_p) {
    return new TigTransactionalEditingDomain(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE), stack_p);
  }
}
