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
package org.polarsys.capella.common.helpers;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
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
   * @param eobject_p the object from which the editing domain will be retrieved
   * @return the editing domain
   */
  public static TransactionalEditingDomain getEditingDomain(EObject eobject_p) {
    return TransactionUtil.getEditingDomain(eobject_p);
  }

  /**
   * Gets the transactional editing domain from the given sirius session.
   * @param session_p the sirius session from which the editing domain will be retrieved
   * @return the editing domain
   */
  public static TransactionalEditingDomain getEditingDomain(Session session_p) {
    return session_p.getTransactionalEditingDomain();
  }

  /**
   * Gets the transactional editing domain from the given resource.
   * @param session_p the resource from which the editing domain will be retrieved
   * @return the editing domain
   */
  public static TransactionalEditingDomain getEditingDomain(Resource resource_p) {
    return TransactionUtil.getEditingDomain(resource_p);
  }

  /**
   * Gets the transactional editing domain from the given collection of eobjects.
   * @param eobjects_p a collection of eobjects from which the editing domain will be retrieved
   * @return if all eobjects belong to the same resource set, the related editing domain is returned. Otherwise null is returned.
   */
  public static TransactionalEditingDomain getEditingDomain(Collection<? extends EObject> eobjects_p) {
    ResourceSet rs = EObjectExt.getCommonResourceSet(eobjects_p);
    if (null != rs) {
      return TransactionUtil.getEditingDomain(rs);
    }
    return null;
  }

  /**
   * Gets the transactional editing domain from the given collection of resources.
   * @param resources_p a collection of resources from which the editing domain will be retrieved
   * @return if all resources belong to the same resource set, the related editing domain is returned. Otherwise null is returned.
   */
  public static TransactionalEditingDomain getEditingDomain(List<Resource> resources_p) {
    ResourceSet rs = EObjectExt.getCommonResourceSet(resources_p);
    if (null != rs) {
      return TransactionUtil.getEditingDomain(rs);
    }
    return null;
  }

  /**
   * Gets the execution manager from the given object.
   * @param eobject_p the object from which the execution manager will be retrieved
   * @return the execution manager
   */
  public static ExecutionManager getExecutionManager(EObject eobject_p) {
    return ExecutionManagerRegistry.getInstance().getExecutionManager(TransactionUtil.getEditingDomain(eobject_p));
  }

  /**
   * Gets the execution manager domain from the given resource.
   * @param session_p the resource from which the execution manager will be retrieved
   * @return the execution manager
   */
  public static ExecutionManager getExecutionManager(Resource resource_p) {
    return ExecutionManagerRegistry.getInstance().getExecutionManager(TransactionUtil.getEditingDomain(resource_p));
  }

  /**
   * Gets the execution manager from the given sirius session.
   * @param session_p the sirius session from which the execution manager will be retrieved
   * @return the execution manager
   */
  public static ExecutionManager getExecutionManager(Session session_p) {
    return ExecutionManagerRegistry.getInstance().getExecutionManager(session_p.getTransactionalEditingDomain());
  }

  /**
   * Gets the execution manager from the given collection of eobjects.
   * @param eobjects_p a collection of eobjects from which the execution manager will be retrieved
   * @return if all eobjects belong to the same resource set, the related execution manager is returned. Otherwise null is returned.
   */
  public static ExecutionManager getExecutionManager(Collection<? extends EObject> eobjects_p) {
    ResourceSet rs = EObjectExt.getCommonResourceSet(eobjects_p);
    if (null != rs) {
      return ExecutionManagerRegistry.getInstance().getExecutionManager(TransactionUtil.getEditingDomain(rs));
    }
    return null;
  }
}
