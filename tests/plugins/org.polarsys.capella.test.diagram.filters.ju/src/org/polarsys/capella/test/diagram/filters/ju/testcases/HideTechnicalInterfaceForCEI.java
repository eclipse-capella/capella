/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.filters.ju.testcases;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.diagram.filters.ju.DiagramObjectFilterTestCase;

public class HideTechnicalInterfaceForCEI extends DiagramObjectFilterTestCase {

	@Override
	protected String getTestProjectName() {
		return "Project_validation_hideTechnicalInterfaceFilter"; //$NON-NLS-1$
	}

	@Override
	protected String getDiagramName() {
		return "[CEI] TEST"; //$NON-NLS-1$
	}

	@Override
	protected String getFilterName() {		
		return "Hide Technical Interfaces"; //$NON-NLS-1$
	}

	@Override
	protected List<String> getFilteredObjetIDs() {		
		return Arrays.asList(new String [] {
			"5ba0acc2-c711-48b3-954d-7826cc0fc5c7",	 //$NON-NLS-1$
		});
	}
}
