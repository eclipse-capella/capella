/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.metric.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

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
   * 
   * @return <code>null</code> whether selection does not fit any supported case .
   */
  public static EObject getSelectedEObject(IStructuredSelection structuredSelection) {
    Collection<EObject> selectedEObjects = getSelectedEObjects(structuredSelection);
    if (!selectedEObjects.isEmpty()) {
      return selectedEObjects.iterator().next();
    }
    return null;
  }
  
  /**
   * Get all selected {@link EObject} from given {@link IStructuredSelection}.
   * @return <code>null</code> whether selection does not fit any supported case .
   */
  public static Collection<EObject> getSelectedEObjects(IStructuredSelection structuredSelection) {
    // Precondition.
    if (structuredSelection != null && !structuredSelection.isEmpty() ) {
    Collection<EObject> result = new ArrayList<EObject>();
    Iterator<?> iterator = structuredSelection.iterator();
    while (iterator.hasNext()) {
      Object selectedObj = iterator.next();
      try {
        // .aird file case.
        if (selectedObj instanceof IFile) {
          IFile file = (IFile) selectedObj;
          URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
          Session session = SessionManager.INSTANCE.getSession(uri, new NullProgressMonitor());
          if ((null != session) && session.isOpen()) { // Session is open
            result.add(SessionHelper.getCapellaProject(session));
          }
        }else if (selectedObj instanceof EObject) {
          result.add((EObject) selectedObj);
        }
      } catch (Exception exception) { // Old models raise up exception
        // Ignore exception
      }
    }
    return result;
    }
    return Collections.emptyList();
  }
}
