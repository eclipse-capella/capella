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
package org.polarsys.capella.core.explorer.activity.ui.pages;

import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.sirius.analysis.IViewpointNameConstants;

public class OperationalAnalysisPage extends AbstractCapellaPage {

  public static final String PAGE_ID = "org.polarsys.capella.core.explorer.activity.ui.page.operational.analysis";

	@Override
	public EClass getFilteringMetaClassForCommonViewpoint() {
		return OaPackage.Literals.OPERATIONAL_ANALYSIS;
	}

	@Override
	public Set<String> getHandledViewpoint() {
		if (!handledViewpoint.contains(IViewpointNameConstants.OPERATIONAL_ANALYSIS_VIEWPOINT_NAME))
			handledViewpoint.add(IViewpointNameConstants.OPERATIONAL_ANALYSIS_VIEWPOINT_NAME);
		return handledViewpoint;
	}
}
