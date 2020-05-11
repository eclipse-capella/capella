/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.common.libraries.IModelIdentifier;
import org.polarsys.capella.common.libraries.LibrariesFactory;
import org.polarsys.capella.common.libraries.ModelInformation;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.helpers.ProjectExt;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.kitalpha.emde.model.ElementExtension;

public class CapellaLibraryExt {

  /**
   * Retrieve the identifier of the given object_p
   */
  public static String getIdentifier(EObject object) {
    if (object != null) {
      return IdManager.getInstance().getId(object);
    }
    return ICommonConstants.EMPTY_STRING;
  }

  /**
   * Returns the root project of the resource
   */
  public static Project getProject(Resource resource) {
    return ProjectExt.getProject(resource);
  }

  /**
   * Returns the modelInformation of the given resource (or create one if none)
   */
  public static ModelInformation getModelInformation(Resource resource, boolean create) {
    if (resource == null) {
      return null;
    }
    Project project = getProject(resource);
    if (project != null) {
      for (ElementExtension extension : project.getOwnedExtensions()) {
        if (extension instanceof ModelInformation) {
          return (ModelInformation) extension;
        }
      }
      if (create) {
        ModelInformation result = LibrariesFactory.eINSTANCE.createModelInformation();
        project.getOwnedExtensions().add(result);
        return result;
      }
    }
    return null;
  }
  
  public static boolean isUnresolvableIdentifier(IModelIdentifier identifier) {
    if (identifier instanceof ModelIdentifier && ((ModelIdentifier) identifier).isProxy()) {
      return true;
    }
    return false;
  }

}
