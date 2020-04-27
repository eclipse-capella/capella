/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.model.handler.helpers;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.ef.command.AbstractNonDirtyingCommand;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

public class HoldingResourceHelper {

  /**
   * URI for the holding resource Extension should be the same as we use in the semantic resource since we store here semantic elements, and crossreferencer
   * doens't work on other resources
   */
  protected static URI uri = URI.createURI("capella://capella/holdingResource." + CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION);

  /**
   * Return the holding resource for the given domain, create one if no holding resource exists
   * @param domain
   * @return
   */
  public static Resource getHoldingResource(TransactionalEditingDomain domain) {
    return getHoldingResource(domain, true);
  }

  protected static Resource getHoldingResource(final TransactionalEditingDomain domain, boolean create) {
    Resource hresource = domain.getResourceSet().getResource(uri, false);
    if (hresource == null && create) {
      ExecutionManagerRegistry.getInstance().getExecutionManager(domain).execute(new AbstractNonDirtyingCommand() {
        public void run() {
          HoldingResource resource = new HoldingResource(uri);
          domain.getResourceSet().getResources().add(resource);
        }
      });
    }

    hresource = domain.getResourceSet().getResource(uri, false);
    return hresource;
  }

  public static boolean isHoldByHoldingResource(EObject object) {
    return object.eResource() instanceof HoldingResource;
  }

  /**
   * Remove the holding resource and its content if a holding resource is registered on the given domain
   * @param domain
   */
  public static void flushHoldingResource(TransactionalEditingDomain domain) {
    Resource resource = getHoldingResource(domain, false);
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
      domain.getResourceSet().getResources().remove(resource);
    }
  }

  /**
   * @param element
   * @param resource
   */
  public static void attachToHoldingResource(EObject element, Resource resource) {
    if (resource != null) {
      resource.getContents().add(element);
    }
  }

  /**
   * @param element
   * @param newContainer
   */
  public static void ensureMoveElement(EObject element, EObject newContainer) {
    Resource newContainerResource;
    Resource elementResource;
    if ((newContainer != null) && ((newContainerResource = newContainer.eResource()) != null) && (element != null) && ((elementResource = element.eResource())!= null)) {
      if (HoldingResourceHelper.isHoldByHoldingResource(element)) {
        if (!HoldingResourceHelper.isHoldByHoldingResource(newContainer)) {
          ((Resource.Internal) elementResource).getContents().remove(element);
          ((Resource.Internal) newContainerResource).attached(element);

          // the elements of the sub-tree shall also be moved
          for (EObject o : element.eContents()) {
            ensureMoveElement(o, newContainer);
          }
        }
      }
    }
  }
}
