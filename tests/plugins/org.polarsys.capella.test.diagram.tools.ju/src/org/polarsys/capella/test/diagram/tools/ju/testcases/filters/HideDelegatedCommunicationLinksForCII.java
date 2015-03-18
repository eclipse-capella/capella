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

public class HideDelegatedCommunicationLinksForCII extends DiagramObjectFilterTestCase {

	@Override
	protected String getTestProjectName() {
		return "Test_delegationWizard"; //$NON-NLS-1$
	}

	@Override
	protected String getDiagramName() {
		return "[CII] TEST"; //$NON-NLS-1$
	}

	@Override
	protected String getFilterName() {		
		return "Hide Delegated Communication Links"; //$NON-NLS-1$
	}

	@Override
	protected List<String> getFilteredObjetIDs() {		
		return Arrays.asList(new String [] {
			"1a525de2-248e-4f4c-ab1b-8df83c88ce80",	 //$NON-NLS-1$
			"fa136adc-c5a1-42e0-a804-40da387d6ccb",	 //$NON-NLS-1$
		});
	}
}
