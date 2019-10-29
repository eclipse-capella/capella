/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.explorer.activity.ju.testcases;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.AbstractCapellaNewDiagramHyperlinkAdapter;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.test.framework.helpers.GuiActions;

public abstract class DiagramActivityExplorerTestCase extends ActivityExplorerTestCase {
  AbstractCapellaNewDiagramHyperlinkAdapter link;
  EObject structure;
  
  protected abstract AbstractCapellaNewDiagramHyperlinkAdapter createLink();

  protected abstract EObject getStructure();

  protected abstract String getDefaultName();
  
  private int getNrOfDiagrams() {
    Collection<DRepresentationDescriptor> myList = RepresentationHelper
        .getAllRepresentationDescriptorsTargetedBy(Arrays.asList(structure));
    List<DRepresentationDescriptor> filteredDiags = myList.stream().filter(x -> x.getName().equals(getDefaultName()))
        .collect(Collectors.toList());
    
    return filteredDiags.size();
  }
  
  protected boolean getResultOfCreateDiagram() {
    return link.createDiagram(structure, session);
  }
  
  @Override
  protected void initialize() {
    super.initialize();
    link = createLink();
    structure = getStructure();
  }


  @Override
  protected void doTest() {
    int nrOfExistingDiagrams = getNrOfDiagrams();
    boolean result = getResultOfCreateDiagram();
    assertTrue(result);

    assertEquals(getNrOfDiagrams(), nrOfExistingDiagrams + 1);
    GuiActions.closeSession(session, false);

  }

  @Override
  protected void preTest() {
    ModelElement modelElement = getTestModelElement();
    assertEquals(modelElement, structure);
  }

}
