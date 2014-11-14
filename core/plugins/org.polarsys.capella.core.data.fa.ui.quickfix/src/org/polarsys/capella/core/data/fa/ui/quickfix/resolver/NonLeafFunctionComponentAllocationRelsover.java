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
import org.eclipse.ui.statushandlers.StatusManager;

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;
import org.polarsys.capella.core.validation.ui.ide.PluginActivator;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.tig.ef.command.AbstractReadWriteCommand;

/**
 * Remove unwanted function allocation
 * 
 */
public class NonLeafFunctionComponentAllocationRelsover extends
		AbstractCapellaMarkerResolution {

	/**
	 * {@inheritDoc}
	 */
	public void run(IMarker marker_p) {
		final Object obj = getModelElements(marker_p).get(0);
		final List<AbstractTrace> traceToRemove = new ArrayList<AbstractTrace>(
				0);

		if (obj instanceof AbstractFunction) {
			final AbstractFunction abstractFunction = (AbstractFunction) obj;
			for (AbstractTrace trace : abstractFunction.getIncomingTraces()) {
				if (((trace instanceof ComponentFunctionalAllocation) || (trace instanceof ActivityAllocation))) {
					traceToRemove.add(trace);
				}
			}
		}
		final boolean flag[] = { false };
		AbstractReadWriteCommand abstrctCommand = new AbstractReadWriteCommand() {
			public void run() {
				// remove component allocation or activity allocation
				if (!traceToRemove.isEmpty()) {
					// execute the command
					boolean confirmDeletion = CapellaDeleteCommand
							.confirmDeletion(
									MDEAdapterFactory.getExecutionManager(),
									traceToRemove);
					if (confirmDeletion) {
						CapellaDeleteCommand command = new CapellaDeleteCommand(
								MDEAdapterFactory.getExecutionManager(),
								traceToRemove, false, false, true);
						if (command.canExecute()) {
							command.execute();
							// flag element deletion
							flag[0] = true;
						}
					}
				}
			}
		};
		MDEAdapterFactory.getExecutionManager().execute(abstrctCommand);

		// remove the marker if the element is deleted
		if (flag[0] == true) {
			try {
				marker_p.delete();
			} catch (CoreException exception_p) {
				// no nothing
			}
		}
	}

	@Override
	public void run(IMarker[] markers, IProgressMonitor monitor) {
		final List<AbstractTrace> traceToRemove = new ArrayList<AbstractTrace>(
				0);

		for (IMarker marker : markers) {
			// Get ModelElement associated to the marker.
			List<EObject> modelElements = getModelElements(marker);
			if (modelElements.isEmpty()) {
				return;
			}
			final Object obj = getModelElements(marker).get(0);

			if (obj instanceof AbstractFunction) {
				final AbstractFunction abstractFunction = (AbstractFunction) obj;
				for (AbstractTrace trace : abstractFunction.getIncomingTraces()) {
					if (((trace instanceof ComponentFunctionalAllocation) || (trace instanceof ActivityAllocation))) {
						traceToRemove.add(trace);
					}
				}
			}

		}

		 final AtomicReference<Boolean> mustDeleteMarker = new AtomicReference<Boolean>(Boolean.FALSE);
		AbstractReadWriteCommand abstrctCommand = new AbstractReadWriteCommand() {
			public void run() {
				// remove component allocation or activity allocation
				if (!traceToRemove.isEmpty()) {
					// execute the command
					boolean confirmDeletion = CapellaDeleteCommand
							.confirmDeletion(
									MDEAdapterFactory.getExecutionManager(),
									traceToRemove);
					if (confirmDeletion) {
						CapellaDeleteCommand command = new CapellaDeleteCommand(
								MDEAdapterFactory.getExecutionManager(),
								traceToRemove, false, false, true);
						if (command.canExecute()) {
							command.execute();
				              // Element (s) deleted -> delete maker too.
				              mustDeleteMarker.set(Boolean.TRUE);
						}
					}
				}
			}
		};
		MDEAdapterFactory.getExecutionManager().execute(abstrctCommand);

	    // Remove the marker if the element is deleted.
	    if (mustDeleteMarker.get().booleanValue()) {
	      for (IMarker marker : markers){
	        if (marker.exists()){
	          try {
	            marker.delete();
	          } catch (CoreException exception_p) {
	            StatusManager.getManager().handle(new Status(IStatus.ERROR, PluginActivator.getDefault().getPluginId(), exception_p.getMessage(), exception_p));
	          }
	        }
	      }
	    }

	}
}
