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
package org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.la;

import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;

/**
 * Create a new Exchange Scenario
 */
public class NewExchangeScenarioAdapter extends AbstractNewLAScenarioDiagramAdapter {
	/**
	 * Constructor.
	 * @param capellaproject
	 * @param session
	 */
	public NewExchangeScenarioAdapter() {
		super();
	}

	@Override
	public String getRepresentationName() {
		return IDiagramNameConstants.DATA_FLOW_SCENARIO_DIAGRAM_NAME;
	}
}
