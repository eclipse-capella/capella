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
package org.polarsys.capella.core.libraries.model;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.libraries.LibrariesFactory;
import org.polarsys.capella.common.libraries.ModelInformation;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.kitalpha.emde.model.ElementExtension;

public class CapellaLibraryExt {

  /**
   * Retrieve the identifier of the given object_p
   */
  public static String getIdentifier(EObject object_p) {
    if (object_p != null) {
      return IdManager.getInstance().getId(object_p);
    }
    return ICommonConstants.EMPTY_STRING;
  }

  /**
   * Returns the root project of the resource
   */
  public static Project getProject(Resource resource_p) {
    if ((resource_p == null) || !(CapellaResourceHelper.isCapellaResource(resource_p))) {
      return null;
    }
    if (resource_p.getContents().isEmpty()) {
      return null;
    }

    EObject root = resource_p.getContents().get(0);
    if (root instanceof Project) {
      return (Project) root;
    }
    root = EcoreUtil.getRootContainer(root);
    if (root instanceof Project) {
      return (Project) root;
    }

    return null;
  }

  /**
   * Returns the modelInformation of the given resource (or create one if none)
   */
  public static ModelInformation getModelInformation(Resource resource_p, boolean create_p) {
    if (resource_p == null) {
      return null;
    }
    Project project = getProject(resource_p);
    if (project != null) {
      for (ElementExtension extension : project.getOwnedExtensions()) {
        if (extension instanceof ModelInformation) {
          return (ModelInformation) extension;
        }
      }
      if (create_p) {
        ModelInformation result = LibrariesFactory.eINSTANCE.createModelInformation();
        project.getOwnedExtensions().add(result);
        return result;
      }
    }
    return null;
  }

}
