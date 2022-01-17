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
package org.polarsys.capella.core.data.interaction.ui.quickfix.resolver;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

/**
 * 
 * 
 */
public class DWF_DS_08_Resolver extends AbstractCapellaMarkerResolution {

	/**
	 * Synchronize invoked operation according to sequence message kind
	 * 
	 * {@inheritDoc}
	 */
	public void run(IMarker marker) {
		final List<EObject> modelElements = getModelElements(marker);

		if (!modelElements.isEmpty()) {

			final AtomicReference<Boolean> mustDeleteMarker = new AtomicReference<Boolean>(
					Boolean.FALSE);
			// read write command
			AbstractReadWriteCommand collectElementsCommand = new AbstractReadWriteCommand() {

				public void run() {
					for (EObject object : modelElements) {
						if (object instanceof Scenario) {
							Scenario scenario = (Scenario) object;
							ScenarioExt.reorderTimeLapseFragments(scenario);

						}

					}

				}

				/**
				 * {@inheritDoc}
				 */
				@Override
				public void commandRolledBack() {
					super.commandRolledBack();

				}
			};
			// execute the command
			TransactionHelper.getExecutionManager(modelElements).execute(
					collectElementsCommand);
			mustDeleteMarker.set(Boolean.TRUE);
			if (mustDeleteMarker.get().booleanValue()) {
				try {
					marker.delete();
				} catch (CoreException exception) {
					StatusManager.getManager().handle(
							new Status(IStatus.ERROR, FrameworkUtil.getBundle(this.getClass()).getSymbolicName(), exception
									.getMessage(), exception));

				}
			}

		}

	}

}
