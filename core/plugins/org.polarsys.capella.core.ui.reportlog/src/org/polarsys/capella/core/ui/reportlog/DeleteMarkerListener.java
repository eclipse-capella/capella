/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.polarsys.capella.common.ef.domain.AbstractEditingDomainResourceSetListenerImpl;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.LightMarkerRegistry;

/**
 * Upon deletion of a model element, this listener deletes all markers that reference the deleted model element.
 */
public class DeleteMarkerListener extends AbstractEditingDomainResourceSetListenerImpl {

  public DeleteMarkerListener() {
    super(NotificationFilter.NOT_TOUCH.and(NotificationFilter.READ.negated().and(new NotificationFilter.Custom() {
      @Override
      public boolean matches(Notification notif) {

        // we look only at remove and set notifications ...
        switch (notif.getEventType()) {
        case Notification.REMOVE:
        case Notification.REMOVE_MANY:
          break;
        default:
          return false;
        }

        // ... on containment references
        if (notif.getFeature() instanceof EReference && ((EReference) notif.getFeature()).isContainment()) {
          return true;
        }

        // ... or direct resource contents
        if (notif.getNotifier() instanceof Resource
            && notif.getFeatureID(Resource.class) == Resource.RESOURCE__CONTENTS) {
          return true;
        }

        return false;
      }
    })));
  }
  
  public void resourceSetChanged(ResourceSetChangeEvent event) {
    LightMarkerRegistry.getInstance().purgeMarkers();
  }
}
