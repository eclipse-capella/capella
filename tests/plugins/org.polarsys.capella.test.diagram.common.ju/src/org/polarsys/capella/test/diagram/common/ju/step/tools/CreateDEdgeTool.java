/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;

public class CreateDEdgeTool extends AbstractToolStep<DEdge> {

  protected String _sourceView;
  protected String _targetView;

  @Deprecated
  String _newSourceIdentifier;
  @Deprecated
  String _newTargetIdentifier;
  @Deprecated
  protected String _newIdentifier;

  Collection<DDiagramElement> _sourceElements;
  Collection<DDiagramElement> _targetElements;
  Collection<DDiagramElement> _newSourceElements;
  Collection<DDiagramElement> _newTargetElements;
  Collection<DDiagramElement> _edgesElements;

  protected Collection<DDiagramElement> _newEdgesElements;
  protected int expectedNumberOfNewEdges;

  // The creation of some types of edges (eg. FunctionalExchange), will also add Ports on the source and target elements
  protected int expectedNumberOfNewElementsOnSource;
  protected int expectedNumberOfNewElementsOnTarget;

  public CreateDEdgeTool(DiagramContext context, String toolName, String sourceView, String targetView,
      int expectedNumberOfNewEdges, int expectedNumberOfNewElementsOnSource, int expectedNumberOfNewElementsOnTarget) {

    super(context, toolName);

    _sourceView = sourceView;
    _targetView = targetView;
    this.expectedNumberOfNewEdges = expectedNumberOfNewEdges;
    this.expectedNumberOfNewElementsOnSource = expectedNumberOfNewElementsOnSource;
    this.expectedNumberOfNewElementsOnTarget = expectedNumberOfNewElementsOnTarget;
  }

  public CreateDEdgeTool(DiagramContext context, String toolName, String sourceView, String targetView) {

    super(context, toolName);

    _sourceView = sourceView;
    _targetView = targetView;
    this.expectedNumberOfNewEdges = -1;
    this.expectedNumberOfNewElementsOnSource = -1;
    this.expectedNumberOfNewElementsOnTarget = -1;
  }

  @Deprecated
  public CreateDEdgeTool(DiagramContext context, String toolName, String sourceView, String targetView,
      String newIdentifier) {
    this(context, toolName, sourceView, targetView, -1, -1, -1);
    _newIdentifier = newIdentifier;
  }

  @Deprecated
  public CreateDEdgeTool(DiagramContext context, String toolName, String sourceView, String targetView,
      String newIdentifier, String newSourceIdentifier, String newTargetIdentifier) {
    this(context, toolName, sourceView, targetView, -1, -1, -1);
    _newIdentifier = newIdentifier;
    _newSourceIdentifier = newSourceIdentifier;
    _newTargetIdentifier = newTargetIdentifier;
  }

  @Override
  protected void preRunTest() {

    super.preRunTest();

    _sourceElements = DiagramHelper.getOwnedElements(getDiagramContext().getView(_sourceView));
    _targetElements = DiagramHelper.getOwnedElements(getDiagramContext().getView(_targetView));
    _edgesElements = new ArrayList<>(getDiagramContext().getDiagram().getEdges());
  }

  @Override
  protected void dispose() {
    super.dispose();
    _sourceElements = null;
    _targetElements = null;
  }

  @Override
  protected void postRunTest() {
    super.postRunTest();

    _newSourceElements = DiagramHelper.getOwnedElements(getDiagramContext().getView(_sourceView));
    _newSourceElements.removeAll(_sourceElements);

    _newTargetElements = DiagramHelper.getOwnedElements(getDiagramContext().getView(_targetView));
    _newTargetElements.removeAll(_targetElements);

    _newEdgesElements = new ArrayList<>(getDiagramContext().getDiagram().getEdges());
    _newEdgesElements.removeAll(_edgesElements);

    if (!(_newEdgesElements.iterator().next() instanceof DEdge)) {
      fail("The crated element is not of type DEdge");
    }

    if (expectedNumberOfNewEdges != -1 && _newEdgesElements.size() != expectedNumberOfNewEdges) {
      fail("The number of created edges is not equal to " + expectedNumberOfNewEdges);
    }

    if (expectedNumberOfNewElementsOnSource != -1 && _newSourceElements.size() != expectedNumberOfNewElementsOnSource) {
      fail("The number of created edges is not equal to " + expectedNumberOfNewElementsOnSource);
    }

    if (expectedNumberOfNewElementsOnTarget != -1 && _newTargetElements.size() != expectedNumberOfNewElementsOnTarget) {
      fail("The number of created edges is not equal to " + expectedNumberOfNewElementsOnTarget);
    }
  }

  @Override
  public DEdge getResult() {

    DEdge createdEdgeView = (DEdge) _newEdgesElements.iterator().next();
    String edgeId = EcoreUtil.getID(createdEdgeView.getTarget());

    if (_newIdentifier != null) {
      getExecutionContext().putSemanticElement(_newIdentifier, createdEdgeView.getTarget());
      getDiagramContext().putView(_newIdentifier, createdEdgeView);
    }
    if ((_newSourceIdentifier != null) && !_newSourceElements.isEmpty()) {
      DDiagramElement sView = _newSourceElements.iterator().next();
      getExecutionContext().putSemanticElement(_newSourceIdentifier, sView.getTarget());
      getDiagramContext().putView(_newSourceIdentifier, sView);
    }
    if ((_newTargetIdentifier != null) && !_newTargetElements.isEmpty()) {
      DDiagramElement tView = _newTargetElements.iterator().next();
      getExecutionContext().putSemanticElement(_newTargetIdentifier, tView.getTarget());
      getDiagramContext().putView(_newTargetIdentifier, tView);
    }

    if (null == _newIdentifier && null == _newSourceIdentifier && null == _newTargetIdentifier) {
      getExecutionContext().putSemanticElement(edgeId, createdEdgeView.getTarget());
      getDiagramContext().putView(edgeId, createdEdgeView);
    }

    return createdEdgeView;
  }

  @Override
  protected void initToolArguments() {

    DSemanticDecorator edgeSourceView = getDiagramContext().getView(_sourceView);
    _toolWrapper.setArgumentValue(ArgumentType.SOURCE, edgeSourceView);

    DSemanticDecorator edgeTargetView = getDiagramContext().getView(_targetView);
    _toolWrapper.setArgumentValue(ArgumentType.TARGET, edgeTargetView);
  }
}
