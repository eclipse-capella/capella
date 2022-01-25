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
import org.eclipse.ui.statushandlers.StatusManager;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;
import org.polarsys.capella.core.validation.ui.ide.PluginActivator;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

/**
 * Remove unwanted function allocation
 * 
 */
public class NonLeafFunctionComponentAllocationRelsover extends
		AbstractCapellaMarkerResolution {

	/**
	 * {@inheritDoc}
	 */
	public void run(IMarker marker) {
		final Object obj = getModelElements(marker).get(0);
		final List<AbstractTrace> traceToRemove = new ArrayList<AbstractTrace>(0);

		if (obj instanceof AbstractFunction) {
			final AbstractFunction abstractFunction = (AbstractFunction) obj;
			for (AbstractTrace trace : abstractFunction.getIncomingTraces()) {
				if (((trace instanceof ComponentFunctionalAllocation) || (trace instanceof ActivityAllocation))) {
					traceToRemove.add(trace);
				}
			}
		}
		final boolean flag[] = { false };
	    final ExecutionManager em = TransactionHelper.getExecutionManager(traceToRemove);
		AbstractReadWriteCommand abstrctCommand = new AbstractReadWriteCommand() {
			public void run() {
				// remove component allocation or activity allocation
				if (!traceToRemove.isEmpty()) {
					// execute the command
					boolean confirmDeletion = CapellaDeleteCommand.confirmDeletion(em, traceToRemove);
					if (confirmDeletion) {
						CapellaDeleteCommand command = new CapellaDeleteCommand(em, traceToRemove, false, false, true);
						if (command.canExecute()) {
							command.execute();
							// flag element deletion
							flag[0] = true;
						}
					}
				}
			}
		};
		em.execute(abstrctCommand);

		// remove the marker if the element is deleted
		if (flag[0] == true) {
			try {
				marker.delete();
			} catch (CoreException exception) {
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
	    final ExecutionManager em = TransactionHelper.getExecutionManager(traceToRemove);
		AbstractReadWriteCommand abstrctCommand = new AbstractReadWriteCommand() {
			public void run() {
				// remove component allocation or activity allocation
				if (!traceToRemove.isEmpty()) {
					// execute the command
					boolean confirmDeletion = CapellaDeleteCommand.confirmDeletion(em, traceToRemove);
					if (confirmDeletion) {
						CapellaDeleteCommand command = new CapellaDeleteCommand(em, traceToRemove, false, false, true);
						if (command.canExecute()) {
							command.execute();
				              // Element (s) deleted -> delete maker too.
				              mustDeleteMarker.set(Boolean.TRUE);
						}
					}
				}
			}
		};
		em.execute(abstrctCommand);

	    // Remove the marker if the element is deleted.
	    if (mustDeleteMarker.get().booleanValue()) {
	      for (IMarker marker : markers){
	        if (marker.exists()){
	          try {
	            marker.delete();
	          } catch (CoreException exception) {
	            StatusManager.getManager().handle(new Status(IStatus.ERROR, PluginActivator.getDefault().getBundle().getSymbolicName(), exception.getMessage(), exception));
	          }
	        }
	      }
	    }

	}
}
