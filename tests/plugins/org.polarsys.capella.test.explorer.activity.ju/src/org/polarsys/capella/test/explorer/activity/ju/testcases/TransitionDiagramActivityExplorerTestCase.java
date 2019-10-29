/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.test.explorer.activity.ju.testcases;

import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.AbstractCapellaHyperlinkAdapter;
import org.polarsys.capella.test.diagram.common.ju.availableXDFBDiagramTools.XDFBCreateContainerTools;
import org.polarsys.capella.test.diagram.common.ju.context.XDFBDiagram;

public abstract class TransitionDiagramActivityExplorerTestCase extends ActivityExplorerTestCase {
  AbstractCapellaHyperlinkAdapter link;
  CapellaElement container;
  AbstractFunction originalFunction;

  @Override
  public void initialize() {
    super.initialize();

    link = createLink();
    container = getContainer();
    XDFBDiagram xdfb = XDFBDiagram.createDiagram(context, container.getId());
    String id = xdfb.createContainer(xdfb.getDiagramId(), XDFBCreateContainerTools.CREATE_FUNCTION);
    originalFunction = context.getSemanticElement(id);
  }
  
  protected abstract AbstractCapellaHyperlinkAdapter createLink();

  public abstract CapellaElement getContainer();

  public abstract AbstractFunction getRoot();

  public abstract void executeTransition();

  @Override
  protected void doTest() {
    executeTransition();

    // check that the created function (originalFunction) was transitioned
    AbstractFunction root = getRoot();
    AbstractFunction lastFunction = root.getOwnedFunctions().get(root.getOwnedFunctions().size() - 1);
    assertEquals(originalFunction, lastFunction.getOutFunctionRealizations().get(0).getAllocatedFunction());
  }

  @Override
  protected void preTest() {
    ModelElement modelElement = getTestModelElement();
    assertEquals(modelElement, container);
  }

}
