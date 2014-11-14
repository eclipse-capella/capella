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
package org.polarsys.capella.core.data.interaction.ui.quickfix.resolver;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.tig.ef.command.AbstractReadWriteCommand;

/**
 * Give to the SequenceMessage the name of its references element
 * (ComponentExchange, FunctionalExchange or ExchangeItem).
 * 
 */
public class DWF_DS_12_RenameResolver extends AbstractCapellaMarkerResolution {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run(IMarker marker_p) {
		List<EObject> modelElements = getModelElements(marker_p);
		EObject modelElement = modelElements.get(0);
		// Precondition.
		if (!(modelElement instanceof SequenceMessage)) {
			return;
		}
		final SequenceMessage sequenceMessage = (SequenceMessage) modelElement;
		// Get the name of the referenced element.
		final String[] referencedElementName = new String[1];
		AbstractEventOperation operation = sequenceMessage
				.getInvokedOperation();
		if (null == operation) {
			return;
		} else if (operation instanceof ExchangeItemAllocation) {
			// For ExchangeItemAllocation get the ExchangeItem.
			ExchangeItem exchangeItem = ((ExchangeItemAllocation) operation)
					.getAllocatedItem();
			if (null == exchangeItem) {
				return;
			}
			referencedElementName[0] = exchangeItem.getName();
		} else {
			// ComponentExchange or FunctionalExchange.
			referencedElementName[0] = operation.getName();
		}
		// Give new name to the SequenceMessage.
		AbstractReadWriteCommand cmd = new AbstractReadWriteCommand() {
			@Override
			public void run() {
				sequenceMessage.setName(referencedElementName[0]);
			}
		};
		// execute the command
		MDEAdapterFactory.getExecutionManager().execute(cmd);
		// Remove the associated marker.
		try {
			marker_p.delete();
		} catch (CoreException exception_p) {
			// Do nothing
		}

	}
}
