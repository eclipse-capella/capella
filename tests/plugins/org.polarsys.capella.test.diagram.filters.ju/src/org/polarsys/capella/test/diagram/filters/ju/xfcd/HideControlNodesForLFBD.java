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

public class HideControlNodesForLFBD extends DiagramObjectFilterTestCase {

	@Override
	protected String getTestProjectName() {
		return "Project_validation_hideControlNodesFilter"; //$NON-NLS-1$
	}

	@Override
	protected String getDiagramName() {
		return "[LFBD] TEST"; //$NON-NLS-1$
	}

	@Override
	protected String getFilterName() {		
		return IFilterNameConstants.FILTER_LFBD_HIDE_CONTROL_NODES;
	}

	@Override
	protected List<String> getFilteredObjetIDs() {		
		return Arrays.asList(new String [] {
      "62752d72-1668-43af-be0e-fa518d3810af", //$NON-NLS-1$
      "93164cec-1c4c-4c11-9ec4-d934e110054f", //$NON-NLS-1$
      "3a2a532a-a04d-4e13-99d9-908d1b2090f5", //$NON-NLS-1$
      "20dfac71-191c-4fc7-b2cd-3d979435b98b", //$NON-NLS-1$
      "481bc418-e2d6-4fa0-9e2e-57aa4a7f4000"  //$NON-NLS-1$
		});
	}
}
