/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES. All rights reserved. 
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
package org.polarsys.capella.core.sirius.ui.listener;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.eclipse.sirius.business.internal.session.SessionEventBrokerImpl;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.base.Predicate;

/**
 * A {@link SessionManagerListener} listening to notification concerning DAnnotations and triggering
 * {@link DAnnotationChangeTrigger}.
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 */
public class DAnnotationPrecommitListener implements SessionManagerListener {

    private DAnnotationChangeTrigger dAnnotationChangeTrigger;

    public Predicate<Notification> considerDAnnotationForAutomaticRefreshPredicate = notification -> {
        if (notification != null) {
            if (notification.getNotifier() instanceof DAnnotation) {
                // A DAnnotation has been updated
                return true;
            } else if ((notification.getNotifier() instanceof DRepresentation || notification.getNotifier() instanceof DRepresentationDescriptor)
                    && (notification.getOldValue() instanceof DAnnotation || notification.getNewValue() instanceof DAnnotation)) {
                // A DAnnotation has been added or removed from a representation
                return true;
            }
        }
        return false;
    };

    @Override
    public void notifyAddSession(Session newSession) {
        dAnnotationChangeTrigger = new DAnnotationChangeTrigger(newSession);
        newSession.getEventBroker().addLocalTrigger(SessionEventBrokerImpl.asFilter(considerDAnnotationForAutomaticRefreshPredicate), dAnnotationChangeTrigger);
    }

    @Override
    public void notifyRemoveSession(Session removedSession) {
        removedSession.getEventBroker().removeLocalTrigger(dAnnotationChangeTrigger);
    }

    @Override
    public void viewpointSelected(Viewpoint selectedSirius) {
        // Nothing to process here
    }

    @Override
    public void viewpointDeselected(Viewpoint deselectedSirius) {
        // Nothing to process here
    }

    @Override
    public void notify(Session updated, int notification) {
        // Nothing to process here
    }

}
