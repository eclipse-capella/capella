/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.filters.ju.xab;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.DiagramObjectFilterTestCase;

public class HideComputedComponentExchangesForLAB extends DiagramObjectFilterTestCase {

	@Override
	protected String getTestProjectName() {
		return "HideSimplifiedLinksFilter"; //$NON-NLS-1$
	}

	@Override
	protected String getDiagramName() {
		return "[LAB] Test Computed CE"; //$NON-NLS-1$
	}

	@Override
	protected String getFilterName() {		
		return IFilterNameConstants.FILTER_LAB_HIDE_COMPUTED_CE;
	}

	@Override
	protected List<String> getFilteredObjetIDs() {	
		return Arrays.asList(new String [] {
      "1a09fd09-7e49-4e77-bc0c-d575f39bf4ce", //$NON-NLS-1$
      "df63336c-cc03-4d17-8251-3b135fddcfdc" //$NON-NLS-1$
		});
	}
}
