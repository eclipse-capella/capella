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

import java.util.Collection;
import java.util.LinkedHashSet;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.business.api.session.ModelChangeTrigger;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

import com.google.common.collect.Sets;

/**
 * This ModelChangeTrigger looks for the parent representations of the notifiers and creates a
 * {@link RefreshRepresentationsCommand} for them.
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 */
public class DAnnotationChangeTrigger implements ModelChangeTrigger {

    private Session session;

    public DAnnotationChangeTrigger(Session session) {
        super();
        this.session = session;
    }

    @Override
    public Option<Command> localChangesAboutToCommit(Collection<Notification> notifications) {
        LinkedHashSet<DRepresentation> representationsToRefresh = collectRepresentationsToRefresh(notifications);
        return Options.newSome(new RefreshRepresentationsCommand(session.getTransactionalEditingDomain(), new NullProgressMonitor(), representationsToRefresh));
    }

    protected LinkedHashSet<DRepresentation> collectRepresentationsToRefresh(Collection<Notification> notifications) {
        LinkedHashSet<DRepresentation> representationsToRefresh = Sets.newLinkedHashSet();
        for (Notification notification : notifications) {
            if (notification.getNotifier() instanceof EObject) {
                Option<DRepresentation> representationOption = getParentRepresentation((EObject) notification.getNotifier());
                if (representationOption.some()) {
                    representationsToRefresh.add(representationOption.get());
                }
            }
        }
        return representationsToRefresh;
    }

    private Option<DRepresentation> getParentRepresentation(EObject eObject) {
        EObject current = eObject;
        while (current != null) {
            if (current instanceof DRepresentationDescriptor && ((DRepresentationDescriptor) current).isLoadedRepresentation()) {
                current = ((DRepresentationDescriptor) current).getRepresentation();
            }
            if (current instanceof DRepresentation) {
                return Options.newSome((DRepresentation) current);
            }
            current = current.eContainer();
        }
        return Options.newNone();
    }

    @Override
    public int priority() {
        return 0;
    }

}
