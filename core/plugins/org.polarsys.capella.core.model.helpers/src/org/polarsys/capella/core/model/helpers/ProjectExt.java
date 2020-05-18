/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.model.helpers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

public class ProjectExt {

	/** @return the project of the model containing the given object. */
	public static Project getProject(EObject object) {
		while (object != null) {
			if (object instanceof Project) {
				return (Project) object;
			}
			object = object.eContainer();
		}
		return null;
	}

  /**
   * Returns the root project of the resource
   */
  public static Project getProject(Resource resource) {
    if ((resource == null) || !(CapellaResourceHelper.isCapellaResource(resource))) {
      return null;
    }
    if (resource.getContents().isEmpty()) {
      return null;
    }

    EObject root = resource.getContents().get(0);
    if (root instanceof Project) {
      return (Project) root;
    }
    root = EcoreUtil.getRootContainer(root);
    if (root instanceof Project) {
      return (Project) root;
    }

    return null;
  }
}
