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
package org.polarsys.capella.core.model.handler.helpers;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.ef.command.AbstractNonDirtyingCommand;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

public class HoldingResourceHelper {

  /**
   * URI for the holding resource
   * Extension should be the same as we use in the semantic resource since we store here semantic elements, and crossreferencer doens't work on other resources
   */
  protected static URI uri = URI.createURI("capella://holdingResource." + CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION);

  /**
   * Return the holding resource for the given domain, create one if no holding resource exists
   * @param domain_p
   * @return
   */
  public static Resource getHoldingResource(TransactionalEditingDomain domain_p) {
    return getHoldingResource(domain_p, true);
  }

  protected static Resource getHoldingResource(final TransactionalEditingDomain domain_p, boolean create_p) {
    Resource hresource = domain_p.getResourceSet().getResource(uri, false);
    if (hresource == null) {
      ExecutionManagerRegistry.getInstance().getExecutionManager(domain_p).execute(new AbstractNonDirtyingCommand() {
        public void run() {
          HoldingResource resource = new HoldingResource(uri);
          domain_p.getResourceSet().getResources().add(resource);
        }
      });
    }

    hresource = domain_p.getResourceSet().getResource(uri, false);
    return hresource;
  }

  public static boolean isHoldByHoldingResource(EObject object) {
    return object.eResource() instanceof HoldingResource;
  }

  /**
   * Remove the holding resource and its content if a holding resource is registered on the given domain_p
   * @param domain_p
   */
  public static void flushHoldingResource(TransactionalEditingDomain domain_p) {
    Resource resource = getHoldingResource(domain_p, false);
    if (resource != null) {
      Collection<EObject> all = new HashSet<EObject>();
      Iterator<EObject> child = resource.getAllContents();
      while (child.hasNext()) {
        all.add(child.next());
      }
      for (EObject obj : all) {
        EcoreUtil.remove(obj);
      }
      resource.getContents().clear();
      resource.unload();
      domain_p.getResourceSet().getResources().remove(resource);
    }
  }

  /**
   * @param element_p
   * @param resource_p
   */
  public static void attachToHoldingResource(EObject element_p, Resource resource_p) {
    if (resource_p != null) {
      resource_p.getContents().add(element_p);
    }
  }

  /**
   * @param trace_p
   * @param linkSrc_p
   */
  public static void ensureMoveElement(EObject element_p, EObject newContainer_p) {
    if ((newContainer_p != null) && (newContainer_p.eResource() != null) && (element_p != null) && (element_p.eResource() != null)) {
      if (HoldingResourceHelper.isHoldByHoldingResource(element_p)) {
        if (!HoldingResourceHelper.isHoldByHoldingResource(newContainer_p)) {
          ((ResourceImpl) element_p.eResource()).getContents().remove(element_p);
          ((ResourceImpl) newContainer_p.eResource()).attached(element_p);
        }
      }
    }
  }

}
