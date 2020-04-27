/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.fastlinker.listeners;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.helpers.operations.ILongRunningListener;
import org.polarsys.capella.core.model.handler.command.BasicCapellaDeleteCommand;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.ui.fastlinker.FastLinkerActivator;
import org.polarsys.capella.core.ui.fastlinker.FastLinkerState;
import org.polarsys.capella.core.ui.fastlinker.view.FastLinkerView;

/**
 * A long running operations listener dedicated to the FastLinker.
 */
public class FastLinkerLongRunningOperationListener implements ILongRunningListener{

	/**
	 * Get FastLinker viewer.
	 * @return
	 */
	protected FastLinkerView getCapellaFastLinkerView() {
		final FastLinkerView[] result = new FastLinkerView[] { null };
		final IWorkbench workbench = PlatformUI.getWorkbench();
		
		// Precondition.
		if (null == workbench) {
			return result[0];
		}
		// Navigator finder.
		Runnable navigatorFinder = new Runnable() {
			/**
			 * @see java.lang.Runnable#run()
			 */
			public void run() {
				// Get active page view references.
				IViewReference[] viewReferences = workbench.getActiveWorkbenchWindow().getActivePage().getViewReferences();
				for (IViewReference viewReference : viewReferences) {
					if (FastLinkerView.VIEW_ID.equals(viewReference.getId())) {
						try {
							result[0] = FastLinkerView.class.cast(viewReference.getView(false));
						} catch (Exception e) {
							// Oups, can't get the navigator.
							// Either it is not open, or it is no longer a navigator implementation.
						}
						// Found it, stop here.
						break;
					}
				}
			}
		};
		// Ensure execution in UI thread.
		if (null == Display.getCurrent()) {
			workbench.getDisplay().syncExec(navigatorFinder);
		} else {
			// Already in UI thread.
			navigatorFinder.run();
		}
		return result[0];
	}

	@Override
	public boolean isListenerFor(Class<?> longRunningOperationClass) {
		return true;
	}

	@Override
	public void operationStarting(final Class<?> operationClass) {

	}

	@Override
	public void operationAborted(Class<?> operationClass) {
	}

	@Override
	public void operationEnded(final Class<?> operationClass) {
		final FastLinkerView view = getCapellaFastLinkerView();

		// Precondition.
		// There is nothing that can be done here.
		if (null == view) {
			return;
		}
		/**
		 * Disable redraw runnable.
		 */
		Runnable runnable = new Runnable() {
			/**
			 * @see java.lang.Runnable#run()
			 */
			public void run() {
				FastLinkerState currentState = FastLinkerActivator.getDefault().getFastLinkerManager().getCurrentState();
				ArrayList<Object> firstElementToDelete = new ArrayList<Object>();
				ArrayList<Object> secondElementToDelete = new ArrayList<Object>();

				if (BasicCapellaDeleteCommand.class.equals(operationClass)) {
					if(currentState.getFirstElement() != null){
						for(Object obj : currentState.getFirstElement()){
							if(SystemEngineeringExt.findArchitecture((EObject) obj) == null)
							{
								firstElementToDelete.add(obj);
							}
						}
						currentState.getFirstElement().removeAll(firstElementToDelete);
						if(currentState.getFirstElement().size()<= 0){
							FastLinkerActivator.getDefault().getFastLinkerManager().updateCurrentState(null, currentState.getSecondElement(), currentState.getPinnedElement());
						}
					}
					if(currentState.getSecondElement() != null){
						for(Object obj : currentState.getSecondElement()){
							if(SystemEngineeringExt.findArchitecture((EObject) obj) == null)
							{
								secondElementToDelete.add(obj);
							}
						}
						currentState.getSecondElement().removeAll(secondElementToDelete);
						if(currentState.getSecondElement().size()<= 0){
							FastLinkerActivator.getDefault().getFastLinkerManager().updateCurrentState(currentState.getFirstElement(), null, currentState.getPinnedElement());
						}
					}
					view.update();
				}
			}
		};
		// Ensure execution in UI thread.
		if (null == Display.getCurrent()) {
			PlatformUI.getWorkbench().getDisplay().syncExec(runnable);
		} else {
			// Already in UI thread.
			runnable.run();
		}
	}

}
