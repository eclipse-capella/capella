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
package org.polarsys.capella.core.data.cs.ui.quickfix.resolver;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.statushandlers.StatusManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.model.helpers.PhysicalLinkExt;
import org.polarsys.capella.core.validation.ui.ide.PluginActivator;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

/**
 * Move Physical Links to their default container
 * 
 */
public class DWF_DC_19_Resolver extends AbstractCapellaMarkerResolution {

	/**
	 * {@inheritDoc}
	 */
	public void run(IMarker marker) {
		final List<EObject> modelElements = getModelElements(marker);

		if (!modelElements.isEmpty()) {
			final AtomicReference<Boolean> mustDeleteMarker = new AtomicReference<Boolean>(
					Boolean.FALSE);
			AbstractReadWriteCommand abstrctCommand = new AbstractReadWriteCommand() {

				@Override
				public String getName() {
					return getLabel();
				}

				public void run() {
					for (EObject object : modelElements) {
						if (object instanceof PhysicalLink) {
							PhysicalLinkExt
									.attachToDefaultContainer((PhysicalLink) object);
						}
					}
				}
			};

			// execute the command
			TransactionHelper.getExecutionManager(modelElements).execute(abstrctCommand);
			mustDeleteMarker.set(Boolean.TRUE);
			// Remove the marker if the element is deleted.
			if (mustDeleteMarker.get().booleanValue()) {
				if (marker.exists()) {
					try {
						marker.delete();
					} catch (CoreException exception) {
						StatusManager.getManager().handle(
								new Status(IStatus.ERROR, PluginActivator
										.getDefault().getPluginId(),
										exception.getMessage(), exception));
					}
				}
			}
		}
	}
}
