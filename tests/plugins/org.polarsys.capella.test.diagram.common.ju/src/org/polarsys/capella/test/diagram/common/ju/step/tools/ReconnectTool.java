/*******************************************************************************
 * Copyright (c) 2016, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.common.ju.step.tools;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.junit.Assert;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;

public class ReconnectTool extends AbstractToolStep<EObject> {

  protected String edgeViewIdentifier;
  protected String oldTargetViewIdentifier;
  protected String newTargetViewIdentifier;

  public ReconnectTool(DiagramContext context, String toolName, String edgeViewIdentifier,
      String oldTargetViewIdentifier, String newTargetViewIdentifier) {
    
    super(context, toolName);
    
    this.edgeViewIdentifier = edgeViewIdentifier;
    this.oldTargetViewIdentifier = oldTargetViewIdentifier;
    this.newTargetViewIdentifier = newTargetViewIdentifier;
  }

  @Override
  protected void initToolArguments() {
    DSemanticDecorator edgeView = getDiagramContext().getView(edgeViewIdentifier);
    DSemanticDecorator oldTargetView = getDiagramContext().getView(oldTargetViewIdentifier);
    DSemanticDecorator newTargetView = getDiagramContext().getView(newTargetViewIdentifier);

    _toolWrapper.setArgumentValue(ArgumentType.EDGE, edgeView);
    _toolWrapper.setArgumentValue(ArgumentType.SOURCE, oldTargetView);
    _toolWrapper.setArgumentValue(ArgumentType.TARGET, newTargetView);
  }

  @Override
  public EObject getResult() {
    return null;
  }

  @Override
  protected void preRunTest() {
    super.preRunTest();
    DSemanticDecorator edgeView = getDiagramContext().getView(edgeViewIdentifier);
    DSemanticDecorator oldTargetView = getDiagramContext().getView(oldTargetViewIdentifier);

    // Before execution of the tool: check that oldTargetView is one extremity of the given edge
    Assert.assertTrue(
        ((DEdge) edgeView).getSourceNode() == oldTargetView || ((DEdge) edgeView).getTargetNode() == oldTargetView);
  }

  @Override
  protected void postRunTest() {
    super.postRunTest();
    DSemanticDecorator edgeView = getDiagramContext().getView(edgeViewIdentifier);
    DSemanticDecorator newTargetView = getDiagramContext().getView(newTargetViewIdentifier);

    // After execution of the tool: check that newTargetView is one extremity of the given edge
    Assert.assertTrue(
        ((DEdge) edgeView).getSourceNode() == newTargetView || ((DEdge) edgeView).getTargetNode() == newTargetView);
  }
}
