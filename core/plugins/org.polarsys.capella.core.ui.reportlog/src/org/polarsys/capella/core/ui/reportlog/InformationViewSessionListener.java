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
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManagerListener2;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.LightMarkerRegistry;
import org.polarsys.capella.core.model.handler.post.commit.listener.DeleteElementListener;

/**
 * Handles removal of markers when a session is closed, or marked model elements are deleted.
 */
public class InformationViewSessionListener extends SessionManagerListener2.Stub {

  private final AtomicInteger openSessions = new AtomicInteger();

  /* added when first session is opened, and removed when open session count reaches 0 */
  private final DeleteElementListener deleteListener = new DeleteElementListener(){
    
    // a workaround to match eobjects by their id, even if we only have the uri in the marker
    private void findAndAppend(Collection<IMarker> haystack, List<IMarker> result, EObject deleted){
      String id = EcoreUtil.getID(deleted);
      if (id != null){
        for (IMarker marker : haystack){
          String[] uris = marker.getAttribute(EmbeddedMessage.AFFECTED_OBJECTS_URI, "").split(ICommonConstants.LINE_SEPARATOR); //$NON-NLS-1$
          for (String u : uris){
            if (u.endsWith(id)){
              result.add(marker);              
            }
          }
        }
      }
    }
    
    protected void handleDelete(Set<? extends EObject> deleted_p) {
      Collection<IMarker> allMarkers = LightMarkerRegistry.getInstance().getMarkers();
      List<IMarker> toDelete = new ArrayList<IMarker>();
      for (EObject deleted : deleted_p) {
        findAndAppend(allMarkers, toDelete, deleted);
        for (Iterator<EObject> it = deleted.eAllContents(); it.hasNext();) {
          findAndAppend(allMarkers, toDelete, it.next());
        }
      }
      for (IMarker marker : toDelete){
        try {
          marker.delete();
        } catch (CoreException exception){
          ReportLogActivator.getDefault().log(IStatus.ERROR, exception.getMessage(), exception);
        }
      }
    }
  };
  
  // delete all markers on a closing session's aird file
  @Override
  public void notify(Session updated_p, int notification_p) {
    if (notification_p == SessionListener.OPENED){
      openSessions.incrementAndGet();
      updated_p.getTransactionalEditingDomain().addResourceSetListener(deleteListener);
    } else if (notification_p == SessionListener.CLOSING){
      Resource sessionResource = updated_p.getSessionResource();
      if (sessionResource.getURI().isPlatformResource()){
        List<IMarker> toDelete = new ArrayList<IMarker>();
        IPath path = new Path(sessionResource.getURI().toPlatformString(true));
        for (IMarker marker : LightMarkerRegistry.getInstance().getMarkers()){
          IResource markerResource = marker.getResource();
          if (markerResource != null && markerResource.getFullPath().equals(path)){
            toDelete.add(marker);
          }
        }
        for (IMarker marker : toDelete){
          try {
            marker.delete();
          } catch (CoreException exception){
            ReportLogActivator.getDefault().log(IStatus.ERROR, exception.getMessage(), exception);
          }
        }
      }
    } else if (notification_p == SessionListener.CLOSED){
      if (openSessions.decrementAndGet() == 0){
        updated_p.getTransactionalEditingDomain().removeResourceSetListener(deleteListener);
      }
    }
  }
}
