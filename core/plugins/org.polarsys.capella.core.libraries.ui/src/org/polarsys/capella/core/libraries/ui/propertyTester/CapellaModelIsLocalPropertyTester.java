/*******************************************************************************
 * Copyright (c) 2006, 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.libraries.ui.propertyTester;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

/**
 */
public class CapellaModelIsLocalPropertyTester extends PropertyTester {

  @Override
  // This implementation is for patch capella team 3.0.1
  public boolean test(Object receiver_p, String property_p, Object[] args_p, Object expectedValue_p) {
    if (receiver_p instanceof StructuredSelection) {
      StructuredSelection s = (StructuredSelection) receiver_p;
      Object selection = s.getFirstElement();
      // raw assumption (this is only a patch, behavior will be modified) :
      // the project is exported if there is no semantic model file at the same level of the aird file
      if ((selection != null) && (selection instanceof IFile)) {
        IFile airdFile = (IFile) selection;
        Session session = SessionManager.INSTANCE.getExistingSession(URI.createPlatformResourceURI(airdFile.getFullPath().toString(), true));
        // Session is not null if it is opened
        if (session != null) {
            // Use the session to check that at least one semantic resource is a Capella model
            return session.getSemanticResources().stream().anyMatch(res -> CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION.equals(res.getURI().fileExtension()));
        }
      }
    }
    return false;
  }

}
