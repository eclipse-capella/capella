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
package org.polarsys.capella.common.helpers.adapters;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.polarsys.capella.common.platform.sirius.tig.ef.DataNotifier;
import org.polarsys.capella.common.platform.sirius.tig.ef.SemanticEditingDomainFactory.SemanticEditingDomain;
import org.polarsys.capella.common.tig.efprovider.TigEfProvider;
import org.polarsys.capella.common.tig.ef.ExecutionManager;
import org.polarsys.capella.common.tig.ef.registry.ExecutionManagerRegistry;

/**
 * The MDE adapter factory.
 * 
 */
public class MDEAdapterFactory {
	/**
	 * The adapter factory.
	 */
	private static AdapterFactory _adapterFactory;

	/**
	 * Gets the Capella adapter factory singleton.
	 * 
	 * @return The Capella adapter factory.
	 */
	public final static AdapterFactory getAdapterFactory() {
		if (null == _adapterFactory) {
			_adapterFactory = createAdapterFactory();
		}
		return _adapterFactory;
	}

	/**
	 * Creates the composed adapter factory.
	 */
	private static AdapterFactory createAdapterFactory() {
		return ((AdapterFactoryEditingDomain) getEditingDomain()).getAdapterFactory();
	}

  /**
   * Gets the resource set.
   * @return the resource set
   */
  public static ResourceSet getResourceSet() {
    return getEditingDomain().getResourceSet();
  }

  /**
   * @return the data notifier provided by the semantic editing domain
   */
  public static DataNotifier getDataNotifier() {
    return ((SemanticEditingDomain) getEditingDomain()).getDataNotifier();
  }

  /**
   * Gets the transactional editing domain.
   * @return the editing domain
   */
  public static TransactionalEditingDomain getEditingDomain() {
    return getExecutionManager().getEditingDomain();
  }

  /**
   * Gets the transactional editing domain from the given object.
   * @param obj_p the object from which the editing domain will be retrieved
   * @return the editing domain
   */
  public static TransactionalEditingDomain getEditingDomain(EObject obj_p) {
    return TransactionUtil.getEditingDomain(obj_p);
  }

  /**
   * Gets the execution manager.
   * @return the execution manager
   */
  public static ExecutionManager getExecutionManager() {
    return ExecutionManagerRegistry.getInstance().getExecutionManager(TigEfProvider.getExecutionManagerName());
  }
}
