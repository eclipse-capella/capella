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
package org.polarsys.capella.core.model.detachement;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.IModelIdentifier;
import org.polarsys.capella.common.libraries.manager.LibraryManager;
import org.polarsys.kitalpha.model.common.scrutiny.interfaces.IScrutinize;

/**
 *
 */
public class CapellaLibrariesScrutinize implements IScrutinize<Map<IModel, Boolean>, String> {

  private Map<IModel, Boolean> referencedLibs;

  /**
   * 
   */
  public CapellaLibrariesScrutinize() {
    referencedLibs = new HashMap<IModel, Boolean>();
  }

  /**
   * 
   */
  public void dispose() {
    referencedLibs.clear();
    referencedLibs = null;
  }

  /**
   * @see org.polarsys.kitalpha.model.common.scrutiny.interfaces.IScrutinize#findIn(org.eclipse.emf.ecore.EObject)
   */
  @Override
  public void findIn(EObject eobject) {
    // nothing to do
  }

  /**
   * @see org.polarsys.kitalpha.model.common.scrutiny.interfaces.IScrutinize#findIn(org.eclipse.emf.ecore.resource.Resource)
   */
  @Override
  public void findIn(Resource resource) {
    IModelIdentifier resourceId = LibraryManager.INSTANCE.getModelIdentifier(resource.getURI());
    IModel resourceModel = LibraryManager.INSTANCE.getModel(ILibraryManager.DEFAULT_EDITING_DOMAIN, resourceId);
    if (resourceModel != null) {
      Collection<IModelIdentifier> allReferencedLibs = resourceModel.getReferences();
      for (IModelIdentifier id : allReferencedLibs) {
        IModel model = LibraryManager.INSTANCE.getModel(ILibraryManager.DEFAULT_EDITING_DOMAIN, id);
        if (!referencedLibs.containsKey(model)) {
          referencedLibs.put(model, Boolean.FALSE);
        }
      }
    }
  }

  /**
   * @see org.polarsys.kitalpha.model.common.scrutiny.interfaces.IScrutinize#getAnalysisResult()
   */
  @Override
  public Map<IModel, Boolean> getAnalysisResult() {
    return referencedLibs;
  }

  /**
   * @see org.polarsys.kitalpha.model.common.scrutiny.interfaces.IScrutinize#getFeedbackAnalysisMessages()
   */
  @Override
  public String getFeedbackAnalysisMessages() {
    return null;
  }
}
