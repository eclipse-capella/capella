/*******************************************************************************
 * Copyright (c) 2017, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.emf.diffmerge.patterns.capella.quickfix;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;
import org.polarsys.kitalpha.patterns.emde.gen.emdepatternsupport.EmdePatternInstanceSet;


/**
 * A base quick fix for pattern-related issues.
 */
public class AbstractPatternCapellaMarkerResolution extends AbstractCapellaMarkerResolution {

  /**
   * @see org.eclipse.ui.IMarkerResolution#run(org.eclipse.core.resources.IMarker)
   */
  @Override
  public void run(IMarker marker) {
    // Do nothing
  }

  /**
   * Delete the given element if it is a useless pattern storage
   * @param patternStorage a potentially null element 
   */
  protected void deletePatternStorage(EObject patternStorage) {
    // If pattern storage does not contain any pattern instance, delete it as well
    if (patternStorage instanceof EmdePatternInstanceSet
        && ((EmdePatternInstanceSet) patternStorage).getOwnedInstances().isEmpty()) {
      EObject project = patternStorage.eContainer();
      if (project instanceof Project) {
        ((Project) project).getOwnedExtensions().remove(patternStorage);
      }
    }
  }
}
