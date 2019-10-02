/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.filters.ju.xab;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.CountBasedDiagramFilterTestCase;


public class HideComputedPLForPABTestCase1 extends CountBasedDiagramFilterTestCase {
  
	protected String getTestProjectName(){
	  return "model2";
	}
	
	protected String getDiagramName(){
	  return "PB4";
	}
	protected String getFilterName(){
	  return IFilterNameConstants.FILTER_PAB_HIDE_COMPUTED_PL;
	}
	protected int getBeforeFilterDiagramElementCount(){
	  return 28;
	}
	protected int getAfterFilterDiagramElementCount(){
	  return 25;
	}
}
