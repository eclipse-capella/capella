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

public class HideControlNodesForSFBD extends DiagramObjectFilterTestCase {

	@Override
	protected String getTestProjectName() {
		return "Project_validation_hideControlNodesFilter"; //$NON-NLS-1$
	}

	@Override
	protected String getDiagramName() {
		return "[SFBD] TEST"; //$NON-NLS-1$
	}

	@Override
	protected String getFilterName() {		
		return IFilterNameConstants.FILTER_SFBD_HIDE_CONTROL_NODES;
	}

	@Override
	protected List<String> getFilteredObjetIDs() {		
		return Arrays.asList(new String [] {
			"0fe1432b-6c90-4d22-a1dd-9c067a89c32d",	//$NON-NLS-1$
			"30e83de5-2b21-459a-9d84-b830ecb7171f", //$NON-NLS-1$
			"cd6e0cca-36fa-4456-bec7-606f031d2900", //$NON-NLS-1$
			"7c88db0e-4013-419c-85ee-40cefa5a4fbc", //$NON-NLS-1$
			"1dd748a7-0ced-422a-a129-b4f02ece41e7"  //$NON-NLS-1$
		});
	}
}
