/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.libraries.provider;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.manager.LibraryManager;
import org.polarsys.capella.common.libraries.manager.LibraryManagerExt;
import org.polarsys.capella.common.platform.sirius.ted.IDerivedSemanticResourceProvider;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.libraries.model.ICapellaModel;

/**
 *
 */
public class LibrariesDerivedSemanticResourceProvider implements IDerivedSemanticResourceProvider {

  /**
   * @see org.polarsys.capella.common.platform.sirius.ted.IDerivedSemanticResourceProvider#getDerivedSemanticResources(org.eclipse.emf.transaction.TransactionalEditingDomain)
   */
  @Override
  public Collection<Resource> getDerivedSemanticResources(TransactionalEditingDomain editingDomain) {
    Collection<Resource> derivedSemanticResources = new ArrayList<Resource>();
    IModel model = LibraryManager.INSTANCE.getModel(editingDomain);
    if (model != null) {
      for (IModel referencedModel : LibraryManagerExt.getAllReferences(model)) {
        if (referencedModel instanceof ICapellaModel) {
          Project project = ((ICapellaModel) referencedModel).getProject(editingDomain);
          if (project != null) {
            Resource res = project.eResource();
            if (!derivedSemanticResources.contains(res)) {
              derivedSemanticResources.add(res);
            }
          }
        }
      }
    }
    return derivedSemanticResources;
  }
}
