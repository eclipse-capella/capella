/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
		final boolean[] editElement = {false};
		final EObject value = getModelElements(marker).get(0);
		
		try {		
			if ((null != value)){
					AbstractReadWriteCommand cmd = new AbstractReadWriteCommand() {
						@Override
						public void run() {
							if(value instanceof ExchangeItemElement){
								final ExchangeItemElement eie = (ExchangeItemElement) value;
								final ExchangeItem ei = (ExchangeItem) eie.eContainer();
								if (ei.getExchangeMechanism() == ExchangeMechanism.OPERATION) {
									eie.setKind(ElementKind.MEMBER);
									editElement[0] = CapellaUIPropertiesPlugin
										.getDefault().openWizard(eie);
								} else if (eie.getKind() == ElementKind.MEMBER) {
									eie.setKind(ElementKind.TYPE);
								} else {
									eie.setDirection(ParameterDirection.UNSET);
								}
							} else if (value instanceof ExchangeItem){
								editElement[0] = CapellaUIPropertiesPlugin
										.getDefault().openWizard(value);
							}
						}
					};
					
					try {
						ExecutionManagerRegistry
								.getInstance()
								.getExecutionManager(
										TransactionHelper.getEditingDomain(value))
								.execute(cmd);
					} catch (Exception e) {
							// do nothing
					}
			}
			if(editElement[0]){
				marker.delete();
			}
		} catch (CoreException exception) {
			// do nothing
		}
	}
}