/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.model.helpers.viewpoint;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.polarsys.kitalpha.ad.common.utils.URIHelper;
import org.polarsys.kitalpha.ad.services.manager.ViewpointManager;
import org.polarsys.kitalpha.ad.viewpoint.coredomain.viewpoint.model.Viewpoint;
import org.polarsys.kitalpha.resourcereuse.model.Resource;

public class ViewpointHelper {

  /**
   * Lazy initialization.
   */
  private static Set<EPackage> viewpointPackages = null;

  public static Set<EPackage> getViewpointPackages() {
    if (viewpointPackages == null) {
      viewpointPackages = new HashSet<>();

      ResourceSet resourceSet = new ResourceSetImpl();
      for (Resource resource : ViewpointManager.getAvailableViewpoints()) {
        URI uri = URIHelper.createURI(resource);
        Viewpoint viewpoint = (Viewpoint) resourceSet.getEObject(uri, true);
        if (viewpoint.getMetamodel() != null) {
          for (EPackage ePackage : viewpoint.getMetamodel().getModels()) {
            EPackage registeredPkg = EPackage.Registry.INSTANCE.getEPackage(ePackage.getNsURI());

            viewpointPackages.add(registeredPkg);
          }
        }
      }
    }

    return viewpointPackages;
  }
}
