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
package org.polarsys.capella.test.diagram.tools.ju.testcases.filters;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.diagram.tools.ju.DiagramObjectFilterTestCase;

public class HideDelegatedUseImplementationLinksForIDB extends DiagramObjectFilterTestCase {

	@Override
	protected String getTestProjectName() {
		return "Test_delegationWizard"; //$NON-NLS-1$
	}

	@Override
	protected String getDiagramName() {
		return "[IDB] TEST"; //$NON-NLS-1$
	}

	@Override
	protected String getFilterName() {		
		return "Hide Delegated Use/Implementation/Require/Provide Links"; //$NON-NLS-1$
	}

	@Override
	protected List<String> getFilteredObjetIDs() {		
		return Arrays.asList(new String [] {
			"3680cda5-d19e-46ac-9070-7620f912633e",	 //$NON-NLS-1$
			"bb14b9a7-f48c-4d91-a0a3-c6f7183d839c",	 //$NON-NLS-1$
			"bf737db3-74af-4cb7-8089-6ae0e0d9c2c5",	 //$NON-NLS-1$
			"4dca735a-ec2b-41f4-b518-c8c5cc217939",	 //$NON-NLS-1$
		});
	}
}
