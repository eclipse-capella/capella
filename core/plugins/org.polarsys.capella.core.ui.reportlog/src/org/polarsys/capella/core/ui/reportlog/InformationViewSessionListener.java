/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.reportlog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.LightMarkerRegistry;

/**
 * Handles removal of markers when a session is closed, or marked model elements are deleted.
 */
public class InformationViewSessionListener extends SessionManagerListener.Stub {

  // delete all markers on a closing session's aird file
  @Override
  public void notify(Session updated_p, int notification_p) {
    if (notification_p == SessionListener.CLOSING) {
      Resource sessionResource = updated_p.getSessionResource();
      if (sessionResource.getURI().isPlatformResource()) {
        List<IMarker> toDelete = new ArrayList<IMarker>();
        IPath path = new Path(sessionResource.getURI().toPlatformString(true));
        for (IMarker marker : LightMarkerRegistry.getInstance().getMarkers()) {
          IResource markerResource = marker.getResource();
          if ((markerResource != null) && markerResource.getFullPath().equals(path)) {
            toDelete.add(marker);
          }
        }
        for (IMarker marker : toDelete) {
          try {
            marker.delete();
          } catch (CoreException exception) {
              Bundle bundle = FrameworkUtil.getBundle(this.getClass());
              Platform.getLog(bundle).error(exception.getMessage(), exception);
          }
        }
      }
    }
  }
}
