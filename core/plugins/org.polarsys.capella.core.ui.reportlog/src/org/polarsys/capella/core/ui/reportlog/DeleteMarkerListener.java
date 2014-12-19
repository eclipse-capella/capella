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

/**
 * Upon deletion of a model element, this listener deletes all markers 
 * that reference the deleted model element.
 */
public class DeleteMarkerListener extends DeleteElementListener {

    private void findAndAppend(Collection<IMarker> haystack, List<IMarker> result, EObject deleted) {
      for (IMarker marker : haystack) {  
        if (MarkerViewHelper.getModelElementsFromMarker(marker).contains(deleted)){
          result.add(marker);
        }
      }
    }

    @Override
    protected void handleDelete(Set<? extends EObject> deleted_p) {
      Collection<IMarker> allMarkers = LightMarkerRegistry.getInstance().getMarkers();
      List<IMarker> toDelete = new ArrayList<IMarker>();
      for (EObject deleted : deleted_p) {
        findAndAppend(allMarkers, toDelete, deleted);
        for (Iterator<EObject> it = deleted.eAllContents(); it.hasNext();) {
          findAndAppend(allMarkers, toDelete, it.next());
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
