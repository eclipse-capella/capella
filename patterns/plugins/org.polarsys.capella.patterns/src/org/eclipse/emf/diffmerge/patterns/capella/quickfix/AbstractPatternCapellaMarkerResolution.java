/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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

public class AbstractPatternCapellaMarkerResolution extends AbstractCapellaMarkerResolution {

  @Override
  public void run(IMarker marker) {
    // Do nothing
  }

  protected void deletePatternStorage(EObject patternStorage) {
    // If pattern storage does not contain any patter instance, delete it as well
    if (patternStorage instanceof EmdePatternInstanceSet
        && ((EmdePatternInstanceSet) patternStorage).getOwnedInstances().isEmpty()) {
      EObject project = patternStorage.eContainer();
      if (project instanceof Project) {
        ((Project) project).getOwnedExtensions().remove(patternStorage);
      }
    }
  }
}
