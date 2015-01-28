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
package org.polarsys.capella.core.ui.reportlog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.LightMarkerRegistry;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.core.model.handler.post.commit.listener.DeleteElementListener;

public class DeleteMarkerListener extends DeleteElementListener {

  @Override
  protected void handleDelete(Set<? extends EObject> deleted_p) {
    Set<EObject> deleteObjects = (Set) deleted_p;
    Iterator<EObject> itDelete = (Iterator<EObject>) deleted_p.iterator();
    while (itDelete.hasNext()) {
      for (Iterator<EObject> it = itDelete.next().eAllContents(); it.hasNext();) {
        deleteObjects.add(it.next());
      }
    }

    Collection<IMarker> markers = LightMarkerRegistry.getInstance().getMarkers();
    List<IMarker> toDelete = new ArrayList<IMarker>();

    for (IMarker deleted : markers) {
      boolean isDeletable = false;
      Collection<EObject> referencedElements = MarkerViewHelper.getModelElementsFromMarker(deleted);

      // if marker references one of deleted element, we remove it
      for (EObject deleteObject : deleteObjects) {
        if (referencedElements.contains(deleteObject)) {
          isDeletable = true;
          break;
        }
      }
      if (isDeletable) {
        toDelete.add(deleted);
      }
    }

    for (IMarker marker : toDelete) {
      try {
        marker.delete();
      } catch (CoreException exception) {
        ReportLogActivator.getDefault().log(IStatus.ERROR, exception.getMessage(), exception);
      }
    }
  }
}
