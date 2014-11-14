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
package org.polarsys.capella.core.sirius.analysis.refresh.extension;

import org.eclipse.sirius.business.api.refresh.IRefreshExtension;
import org.eclipse.sirius.business.api.refresh.IRefreshExtensionProvider;
import org.eclipse.sirius.viewpoint.DDiagram;

import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;

public class ContextualMissionRefreshExtensionProvider implements
		IRefreshExtensionProvider {

	private static final ContextualMissionRefreshExtension REFRESH_EXTENSION = new ContextualMissionRefreshExtension();

	public IRefreshExtension getRefreshExtension(DDiagram viewPoint) {
		return REFRESH_EXTENSION;
	}

	public boolean provides(DDiagram viewPoint) {
		return IDiagramNameConstants.CONTEXTUAL_MISSION_DIAGRAM_NAME.equals(viewPoint.getDescription().getName());
	}

}
