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
package org.polarsys.capella.core.sirius.analysis.actions.extensions;

import org.polarsys.capella.common.ui.toolkit.viewers.data.DataLabelProvider;

/**
 * PARAMETERS EObject context -- for getting the appropriate interpreter List<EObject> scope -- the set of elements to select from, may be null (empty
 * collection) List<EObject> initialSelection -- the initial set of elements on the right side, may be null (empty collection) String wizardMessage -- the
 * message to display in the wizard, may be null (empty string) String resultVariable -- the Sirius variable to put the user selection into
 */
public class SelectElementsFromTransferWizardIDB extends SelectElementsFromTransferWizard {
 
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DataLabelProvider getCustoLabelProvider() {
		return new CapellaTransfertViewerLabelProviderIDB();
	}
}
