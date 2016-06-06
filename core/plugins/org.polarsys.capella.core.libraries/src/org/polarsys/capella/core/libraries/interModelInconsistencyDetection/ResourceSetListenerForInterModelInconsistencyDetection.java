/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materiTals
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.libraries.interModelInconsistencyDetection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.polarsys.capella.common.libraries.Activator;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.helpers.intermodelInconsistencyDetection.DependencyChecker;
import org.polarsys.capella.core.model.helpers.intermodelInconsistencyDetection.DependencyViolation;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;

/**
 * Listener of model changes to detect inter-model inconsistencies. Only ADD, ADD_MANY, MOVE and SET notifications are treated.
 * <br/><br/>The link between the notifier and the new value of the given {@link EReference} is checked Links (see {@link DependencyChecker}).
 * Moreover, if the {@link EReference} is a containment all links from and to objects contained by the notifier are checked too.
 * 
 * @author Erwan Brottier
 */
public class ResourceSetListenerForInterModelInconsistencyDetection extends ResourceSetListenerImpl {

	protected DependencyChecker linkChecker;

	/**
	 * check all notifications in the resource set and roll back if inter-model dependency violation are detected.
	 * This check is done only if enabled in preferences.
	 */
	@Override
	public Command transactionAboutToCommit(ResourceSetChangeEvent event_p) throws RollbackException {
		if (CapellaModelPreferencesPlugin.getDefault().isInterModelDependencyViolationDetectionActivated()) {
			boolean noViolations = true;
			linkChecker = new DependencyChecker((SemanticEditingDomain) event_p.getEditingDomain());
			try {
				for (Notification notification : event_p.getNotifications())
					noViolations = noViolations && checkNotification(notification);			
				if (!noViolations) {
					rollBackAndLogError();
				}				
			} finally {
				linkChecker = null;
			}			
		}
		return null;
	}

	protected boolean checkNotification(Notification notification_p) {
		boolean res = true;
		int eventType = notification_p.getEventType();
		Object notifier = notification_p.getNotifier();
		if (CapellaResourceHelper.isSemanticElement(notifier)) {
			if (eventType == Notification.ADD || eventType == Notification.ADD_MANY || eventType == Notification.MOVE || eventType == Notification.SET) {
				EStructuralFeature feature = (EStructuralFeature) notification_p.getFeature();
				if (feature != null && feature instanceof EReference) {
					Object newValue = notification_p.getNewValue();
					if (newValue instanceof EObject) {
						if (!linkChecker.checkLink((EObject) notifier, (EObject) newValue, (EReference) feature))
							res = false;
						if (feature instanceof EReference && ((EReference) feature).isContainment())
							if (!linkChecker.checkAllLinks((EObject) newValue))
								res = false;
					}
				}
			}
		}
		return res;
	}

	protected void rollBackAndLogError() throws RollbackException {				
		List<String> violatedDependencyDescriptions = new ArrayList<String>();
		for (DependencyViolation violation : linkChecker.getDependencyViolations())
			violatedDependencyDescriptions.add(violation.getViolatedDependencyDescription());
		violatedDependencyDescriptions = new ArrayList<String>(new HashSet<String>(violatedDependencyDescriptions));
		StringBuilder b = new StringBuilder();		
		for (int i = 0; i < violatedDependencyDescriptions.size(); i++) {
			String description = violatedDependencyDescriptions.get(i);
			b.append(description);
			if (i < violatedDependencyDescriptions.size() - 1)
				b.append(", "); //$NON-NLS-1$
		}
		String message = "Operation has been aborted since it is going to add reference(s) between objects that do not conform to model dependency graph ("+b.toString()+")"; //$NON-NLS-1$ //$NON-NLS-2$
		Logger.getLogger(IReportManagerDefaultComponents.DEFAULT).error(message);
		for (DependencyViolation dependencyViolation : linkChecker.getDependencyViolations())
			Logger.getLogger(IReportManagerDefaultComponents.DEFAULT).debug(dependencyViolation.getDescription());
		throw new RollbackException(new Status(IStatus.CANCEL, Activator.PLUGIN_ID, message));
	}

}
