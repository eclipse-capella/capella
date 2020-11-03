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
package org.polarsys.capella.core.data.core.ui.quickfix.resolver;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.intermodelInconsistencyDetection.InterModelInconsistency;
import org.polarsys.capella.core.model.helpers.intermodelInconsistencyDetection.InterModelInconsistencyDetector;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;
import org.polarsys.capella.core.validation.ui.ide.quickfix.InterModelErrorNavigatorDialog;

/**
 * Show details for inter-model inconsistencies.
 */
public class I_34_Resolver extends AbstractCapellaMarkerResolution {

	/**
	 * {@inheritDoc}
	 */
	public void run(IMarker marker) {
		List<EObject> tgts = getModelElements(marker);
		SystemEngineering se = (SystemEngineering) tgts.get(0);
		List<InterModelInconsistency> inconsistencies = new InterModelInconsistencyDetector().getInterModelInconsistencies(se);

		// show the dialog
		if (inconsistencies.size() > 0) {
			InterModelErrorNavigatorDialog dialog = new InterModelErrorNavigatorDialog(inconsistencies.get(0).getInvolvedObjects(), "Inter-model inconsistency details", "Capella Element(s) involved in detected inter-model inconsistencies (inter-model cycles and dependency violations).", "Select inter-model inconsistency:"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			dialog.setCycles(inconsistencies);
			dialog.open();
		}
		return;
	}

	@Override
	protected String[] getResolvableRuleIds() {
		return noRuleIds;
	}

}
