/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.progressmonitoring.ju.testcases;

import static  org.polarsys.capella.test.progressmonitoring.ju.util.SetProgressTestContants.DRAFT;

import java.util.Iterator;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.diagram.helpers.RepresentationAnnotationHelper;
import org.polarsys.capella.core.ui.metric.actions.ProgressMonitoringSetAction;

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
    Iterator<DRepresentationDescriptor> iterator = representations.iterator();
    // First diagram
    assertEquals(null, RepresentationAnnotationHelper.getProgressStatus(iterator.next()));
    // Second diagram
    assertEquals(null, RepresentationAnnotationHelper.getProgressStatus(iterator.next()));
  }
}
