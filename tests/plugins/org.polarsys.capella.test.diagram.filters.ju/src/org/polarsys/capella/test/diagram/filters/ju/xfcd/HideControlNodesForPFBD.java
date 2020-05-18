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
package org.polarsys.capella.test.diagram.filters.ju.xfcd;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.DiagramObjectFilterTestCase;

public class HideControlNodesForPFBD extends DiagramObjectFilterTestCase {

	@Override
	protected String getTestProjectName() {
		return "Project_validation_hideControlNodesFilter"; //$NON-NLS-1$
	}

	@Override
	protected String getDiagramName() {
		return "[PFBD] TEST"; //$NON-NLS-1$
	}

	@Override
	protected String getFilterName() {		
		return IFilterNameConstants.FILTER_PFBD_HIDE_CONTROL_NODES;
	}

	@Override
	protected List<String> getFilteredObjetIDs() {		
		return Arrays.asList(new String [] {
      "58732179-8bc9-4512-83a3-f5c1aad98983", //$NON-NLS-1$
      "a9b0950a-ac31-43dc-aa52-7bf94a3018ab", //$NON-NLS-1$
      "4b72020a-b3c7-4216-9cca-60d2878ba85f", //$NON-NLS-1$
      "878eeafa-d60a-4985-baff-1ab212ee1e95", //$NON-NLS-1$
      "fe091dc6-cbb2-4baf-8500-8d8c029a14c0"  //$NON-NLS-1$
		});
	}
}
