/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.core.ui.quickfix.resolver;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.polarsys.capella.core.libraries.ui.handlers.ManageReferencedLibrariesHandler;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

public class I_03_Resolver extends AbstractCapellaMarkerResolution {
  @Override
  public void run(IMarker marker) {
    final EObject element = getModelElements(marker).get(0);
    Session session = SessionManager.INSTANCE.getSession(element);
    if (session != null) {
      ManageReferencedLibrariesHandler.openManageReferencesWizard(session);
      try {
        // delete marker
        marker.delete();
      } catch (CoreException exception) {
        // no nothing
      }
    }
  }
}