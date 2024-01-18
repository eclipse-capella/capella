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

package org.polarsys.capella.common.helpers;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;

/**
 * This class contains convenient static methods for working with Editing Domains and Execution Managers.
 */
public class TransactionHelper {

  /**
   * Gets the transactional editing domain from the given object.
   * @param eobject the object from which the editing domain will be retrieved
   * @return the editing domain
   */
  public static TransactionalEditingDomain getEditingDomain(EObject eobject) {
	  for(Adapter adapter : eobject.eAdapters()){
		  if(adapter instanceof IEditingDomainProvider){
			  return (TransactionalEditingDomain)((IEditingDomainProvider)adapter).getEditingDomain();
		  }
	  }
    return TransactionUtil.getEditingDomain(eobject);
  }

  /**
   * Gets the transactional editing domain from the given sirius session.
   * @param session the sirius session from which the editing domain will be retrieved
   * @return the editing domain
   */
  public static TransactionalEditingDomain getEditingDomain(Session session) {
    return session.getTransactionalEditingDomain();
  }

  /**
   * Gets the transactional editing domain from the given resource.
   * @param resource the resource from which the editing domain will be retrieved
   * @return the editing domain
   */
  public static TransactionalEditingDomain getEditingDomain(Resource resource) {
    return TransactionUtil.getEditingDomain(resource);
  }

  /**
   * Gets the transactional editing domain from the given collection of eobjects.
   * @param eobjects a collection of eobjects from which the editing domain will be retrieved
   * @return if all eobjects belong to the same resource set, the related editing domain is returned. Otherwise null is returned.
   */
  public static TransactionalEditingDomain getEditingDomain(Collection<? extends EObject> eobjects) {
    ResourceSet rs = EObjectExt.getCommonResourceSet(eobjects);
    if (null != rs) {
      return TransactionUtil.getEditingDomain(rs);
    }
    return null;
  }

  /**
   * Gets the transactional editing domain from the given collection of resources.
   * @param resources a collection of resources from which the editing domain will be retrieved
   * @return if all resources belong to the same resource set, the related editing domain is returned. Otherwise null is returned.
   */
  public static TransactionalEditingDomain getEditingDomain(List<Resource> resources) {
    ResourceSet rs = EObjectExt.getCommonResourceSet(resources);
    if (null != rs) {
      return TransactionUtil.getEditingDomain(rs);
    }
    return null;
  }

  /**
   * Gets the execution manager from the given object.
   * @param eobject the object from which the execution manager will be retrieved
   * @return the execution manager
   */
  public static ExecutionManager getExecutionManager(EObject eobject) {
    return ExecutionManagerRegistry.getInstance().getExecutionManager(getEditingDomain(eobject));
  }

  /**
   * Gets the execution manager domain from the given resource.
   * @param resource the resource from which the execution manager will be retrieved
   * @return the execution manager
   */
  public static ExecutionManager getExecutionManager(Resource resource) {
    return ExecutionManagerRegistry.getInstance().getExecutionManager(TransactionUtil.getEditingDomain(resource));
  }

  /**
   * Gets the execution manager from the given sirius session.
   * @param session the sirius session from which the execution manager will be retrieved
   * @return the execution manager
   */
  public static ExecutionManager getExecutionManager(Session session) {
    return ExecutionManagerRegistry.getInstance().getExecutionManager(session.getTransactionalEditingDomain());
  }

  /**
   * Gets the execution manager from the given collection of eobjects.
   * @param eobjects a collection of eobjects from which the execution manager will be retrieved
   * @return if all eobjects belong to the same resource set, the related execution manager is returned. Otherwise null is returned.
   */
  public static ExecutionManager getExecutionManager(Collection<? extends EObject> eobjects) {
    ResourceSet rs = EObjectExt.getCommonResourceSet(eobjects);
    if (null != rs) {
      return ExecutionManagerRegistry.getInstance().getExecutionManager(TransactionUtil.getEditingDomain(rs));
    }
    return null;
  }
}
