/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.information.ui.quickfix.resolver;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.information.ElementKind;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.information.ParameterDirection;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

/**
 * Update Exchange Item Element information and open capella element editor to
 * edit respected values when necessary
 */
public class EditExchangeItem extends AbstractCapellaMarkerResolution {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run(final IMarker marker) {

		final EObject value = getModelElements(marker).get(0);
		try {
			if ((null != value) && (value instanceof ExchangeItemElement)) {
				final ExchangeItemElement eie = (ExchangeItemElement) value;
				final ExchangeItem ei = (ExchangeItem) eie.eContainer();
				AbstractReadWriteCommand cmd = new AbstractReadWriteCommand() {
					@Override
					public void run() {
						if (ei.getExchangeMechanism() == ExchangeMechanism.OPERATION) {
							eie.setKind(ElementKind.MEMBER);
							boolean editElement = CapellaUIPropertiesPlugin
									.getDefault().openWizard(eie);
						} else if (eie.getKind() == ElementKind.MEMBER) {
							eie.setKind(ElementKind.TYPE);
						} else {
							eie.setDirection(ParameterDirection.UNSET);
						}
					}
				};
				try {
					ExecutionManagerRegistry
							.getInstance()
							.getExecutionManager(
									TransactionHelper.getEditingDomain(eie))
							.execute(cmd);
				} catch (Exception e) {
					// nothing
				}
			}
			marker.delete();
		} catch (CoreException exception) {
			// no nothing
		}
	}
}