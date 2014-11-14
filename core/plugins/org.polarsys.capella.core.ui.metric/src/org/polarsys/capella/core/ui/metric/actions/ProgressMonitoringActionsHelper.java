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
package org.polarsys.capella.core.ui.metric.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;

public class ProgressMonitoringActionsHelper {
  /**
   * Get {@link EObject} from given {@link IStructuredSelection} (only the first element of the selection is taken).
   * @return <code>null</code> whether selection does not fit any supported case .
   */
  public static EObject getSelectedEObject(IStructuredSelection structuredSelection_p) {
    // Precondition.
    if (null == structuredSelection_p) {
      return null;
    }

    EObject result = null;
    Object selectedObject = structuredSelection_p.getFirstElement();

    try {
      // .aird file case.
      if (selectedObject instanceof IFile) {
        IFile file = (IFile) selectedObject;
        URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
        Session session = SessionManager.INSTANCE.getSession(uri, new NullProgressMonitor());
        if ((null != session) && session.isOpen()) { // Session is open
          result = SessionHelper.getCapellaProject(session);
        }
      } else if (selectedObject instanceof EObject) {
        result = (EObject) selectedObject;
      } else {
        result = null;
      }
    } catch (Exception exception_p) { // Old models raise up exception
      result = null;
    }

    return result;
  }
}
