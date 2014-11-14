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
package org.polarsys.capella.common.tig.ef.ui.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.ui.provider.TransactionalAdapterFactoryContentProvider;
import org.eclipse.ui.views.properties.IPropertySource;

import org.polarsys.capella.common.tig.ef.ExecutionManager;
import org.polarsys.capella.common.tig.ef.command.AbstractNonDirtyingCommand;
import org.polarsys.capella.common.tig.ef.registry.ExecutionManagerRegistry;

/**
 */
public class TigTransactionalAdapterFactoryContentProvider extends TransactionalAdapterFactoryContentProvider {
  /**
   * Execution framework id.
   */
  private String _executionFrameworkId;

  /**
   * Constructor.
   * @param domain_p
   * @param adapterFactory_p
   */
  public TigTransactionalAdapterFactoryContentProvider(TransactionalEditingDomain domain_p, AdapterFactory adapterFactory_p, String executionFrameworkId_p) {
    super(domain_p, adapterFactory_p);
    _executionFrameworkId = executionFrameworkId_p;
  }

  /**
   * @see org.eclipse.emf.transaction.ui.provider.TransactionalAdapterFactoryContentProvider#run(org.eclipse.emf.transaction.RunnableWithResult)
   */
  @Override
  protected Object run(final RunnableWithResult run_p) {
    final Object[] result = new Object[] { null };
    ExecutionManager executionManager = ExecutionManagerRegistry.getInstance().getExecutionManager(_executionFrameworkId);
    if (null != executionManager) {
      executionManager.execute(new AbstractNonDirtyingCommand() {
        public void run() {
          // Run command.
          run_p.run();
          // Get the result.
          result[0] = run_p.getResult();
        }
      });
    } else {
      result[0] = super.run(run_p);
    }
    return result[0];
  }

  /**
   * @see org.eclipse.emf.transaction.ui.provider.TransactionalAdapterFactoryContentProvider#wrap(org.eclipse.ui.views.properties.IPropertySource)
   */
  @Override
  protected IPropertySource wrap(IPropertySource propertySource_p) {
    return propertySource_p;
  }
}
