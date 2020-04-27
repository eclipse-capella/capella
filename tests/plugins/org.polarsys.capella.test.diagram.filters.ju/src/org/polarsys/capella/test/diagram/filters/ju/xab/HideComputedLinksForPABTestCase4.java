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
package org.polarsys.capella.test.diagram.filters.ju.xab;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.CountBasedDiagramFilterTestCase;


public class HideComputedLinksForPABTestCase4 extends CountBasedDiagramFilterTestCase {
  
	protected String getTestProjectName(){
	  return "PB8";
	}
	
	protected String getDiagramName(){
	  return "PB8";
	}
	protected String getFilterName(){
	  return IFilterNameConstants.FILTER_PAB_HIDE_COMPUTED_PL;
	}
	protected int getBeforeFilterDiagramElementCount(){
	  return 12;
	}
	protected int getAfterFilterDiagramElementCount(){
	  return 11;
	}
}
