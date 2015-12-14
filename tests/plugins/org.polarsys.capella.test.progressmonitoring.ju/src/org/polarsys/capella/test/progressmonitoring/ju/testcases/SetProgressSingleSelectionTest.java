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
package org.polarsys.capella.test.progressmonitoring.ju.testcases;

import java.util.Iterator;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.ui.metric.actions.ProgressMonitoringSetAction;
import org.polarsys.capella.core.ui.properties.annotations.RepresentationAnnotationHelper;
import static  org.polarsys.capella.test.progressmonitoring.ju.util.SetProgressTestContants.DRAFT;

public class SetProgressSingleSelectionTest extends AbstractSetProgressTest {
  
  @Override
  public void doTest() throws Exception {
    
    StructuredSelection selection = new StructuredSelection(rootSysFunc);
    
    ProgressMonitoringSetAction action = createSetProgressAction(createRunSetup(getDraftPropertyLiteral(rootSysFunc), false, false));
    action.selectionChanged(selection);
    assertTrue(action.isEnabled());
    
    // Run the action
    action.run();
    
    // Assert statuses are set 
    assertNotNull(rootSysFunc.getStatus());
    assertEquals(DRAFT, rootSysFunc.getStatus().getLabel());
    
    assertNotNull(sf1.getStatus());
    assertEquals(DRAFT, sf1.getStatus().getLabel());
   
    assertNotNull(sf11.getStatus());
    assertEquals(DRAFT, sf11.getStatus().getLabel());
    
    assertNotNull(sf2.getStatus());
    assertEquals(DRAFT, sf2.getStatus().getLabel());
    
    // Assert statuses are not set for diagrams
    Iterator<DRepresentation> iterator = representations.iterator();
    // First diagram
    assertEquals(ICommonConstants.EMPTY_STRING, RepresentationAnnotationHelper.getProgressStatus(iterator.next()));
    // Second diagram
    assertEquals(ICommonConstants.EMPTY_STRING, RepresentationAnnotationHelper.getProgressStatus(iterator.next()));
  }
}
