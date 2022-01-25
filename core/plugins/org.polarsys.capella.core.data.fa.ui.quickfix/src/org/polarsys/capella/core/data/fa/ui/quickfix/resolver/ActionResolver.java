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
package org.polarsys.capella.core.data.fa.ui.quickfix.resolver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.common.ui.actions.AbstractTigAction;
import org.polarsys.capella.core.validation.ui.ide.PluginActivator;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

/**
 */
public abstract class ActionResolver extends AbstractCapellaMarkerResolution {

	protected abstract AbstractTigAction createAction();

	public void run(IMarker marker) {

		List<EObject> tgts = getModelElements(marker);
		final AtomicReference<Boolean> mustDeleteMarker = new AtomicReference<Boolean>(
				Boolean.FALSE);
		AbstractTigAction action = createAction();
		IAction uiAction = new Action(action.getClass().getSimpleName()) {
			// Nothing more
		};
		action.selectionChanged(uiAction, new StructuredSelection(tgts));
		// should use a command !
		action.run(uiAction);
		mustDeleteMarker.set(Boolean.TRUE);

		// delete marker
		if (mustDeleteMarker.get().booleanValue()) {
			try {
				marker.delete();
			} catch (CoreException e) {
				StatusManager.getManager().handle(
						new Status(IStatus.ERROR, FrameworkUtil.getBundle(PluginActivator.class).getSymbolicName(), e.getMessage(), e));
			}
		}
	}

	@Override
	public void run(IMarker[] markers, IProgressMonitor monitor) {
		List<EObject> tgts = new ArrayList<EObject>(0);
		for (IMarker marker : markers) {

			tgts.addAll(getModelElements(marker));
		}
		final AtomicReference<Boolean> mustDeleteMarkers = new AtomicReference<Boolean>(
				Boolean.FALSE);
		AbstractTigAction action = createAction();
		IAction uiAction = new Action(action.getClass().getSimpleName()) {
			// Nothing more
		};
		action.selectionChanged(uiAction, new StructuredSelection(tgts));
		action.run(uiAction);
		mustDeleteMarkers.set(Boolean.TRUE);
		
		// delete markers
		if (mustDeleteMarkers.get().booleanValue()) {
			for (IMarker marker : markers) {

				try {
					marker.delete();
				} catch (CoreException e) {
					StatusManager.getManager().handle(
							new Status(IStatus.ERROR, PluginActivator
									.getDefault().getBundle().getSymbolicName(),
									e.getMessage(), e));
				}
			}
		}
	}

}
