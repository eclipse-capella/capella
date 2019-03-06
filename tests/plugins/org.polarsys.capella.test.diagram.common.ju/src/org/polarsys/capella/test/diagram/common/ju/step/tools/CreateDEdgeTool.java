/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.common.ju.step.tools;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;

public class CreateDEdgeTool extends AbstractToolStep<DEdge> {

  protected String _sourceView;
  protected String _targetView;
  protected String _newIdentifier;
  protected String _newSourceIdentifier;
  protected String _newTargetIdentifier;

  protected Collection<DDiagramElement> _sourceElements;
  protected Collection<DDiagramElement> _targetElements;
  protected Collection<DDiagramElement> _newSourceElements;
  protected Collection<DDiagramElement> _newTargetElements;
  protected Collection<DDiagramElement> _edgesElements;
  protected Collection<DDiagramElement> _newEdgesElements;

  public CreateDEdgeTool(DiagramContext context, String toolName, String sourceView, String targetView) {
    super(context, toolName);
    _sourceView = sourceView;
    _targetView = targetView;
  }

  public CreateDEdgeTool(DiagramContext context, String toolName, String sourceView, String targetView,
      String newIdentifier) {
    this(context, toolName, sourceView, targetView);
    _newIdentifier = newIdentifier;
  }

  public CreateDEdgeTool(DiagramContext context, String toolName, String sourceView, String targetView,
      String newIdentifier, String newSourceIdentifier, String newTargetIdentifier) {
    this(context, toolName, sourceView, targetView);
    _newIdentifier = newIdentifier;
    _newSourceIdentifier = newSourceIdentifier;
    _newTargetIdentifier = newTargetIdentifier;
  }

  @Override
  protected void preRunTest() {
    super.preRunTest();
    _sourceElements = DiagramHelper.getOwnedElements(getExecutionContext().getView(_sourceView));
    _targetElements = DiagramHelper.getOwnedElements(getExecutionContext().getView(_targetView));
    _edgesElements = new ArrayList(getExecutionContext().getDiagram().getEdges());
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

    _newSourceElements = DiagramHelper.getOwnedElements(getExecutionContext().getView(_sourceView));
    _newSourceElements.removeAll(_sourceElements);

    _newTargetElements = DiagramHelper.getOwnedElements(getExecutionContext().getView(_targetView));
    _newTargetElements.removeAll(_targetElements);

    _newEdgesElements = new ArrayList(getExecutionContext().getDiagram().getEdges());
    _newEdgesElements.removeAll(_edgesElements);
  }

  @Override
  public DEdge getResult() {
    DEdge view = (DEdge) _newEdgesElements.iterator().next();
    if (_newIdentifier != null) {
      getExecutionContext().putSemanticElement(_newIdentifier, view.getTarget());
      getExecutionContext().putView(_newIdentifier, view);
    }
    if ((_newSourceIdentifier != null) && !_newSourceElements.isEmpty()) {
      DDiagramElement sView = _newSourceElements.iterator().next();
      getExecutionContext().putSemanticElement(_newSourceIdentifier, sView.getTarget());
      getExecutionContext().putView(_newSourceIdentifier, sView);
    }
    if ((_newTargetIdentifier != null) && !_newTargetElements.isEmpty()) {
      DDiagramElement tView = _newTargetElements.iterator().next();
      getExecutionContext().putSemanticElement(_newTargetIdentifier, tView.getTarget());
      getExecutionContext().putView(_newTargetIdentifier, tView);
    }
    return view;
  }

  @Override
  protected void initToolArguments() {
    DSemanticDecorator source = getExecutionContext().getView(_sourceView);
    _toolWrapper.setArgumentValue(ArgumentType.SOURCE, source);

    DSemanticDecorator target = getExecutionContext().getView(_targetView);
    _toolWrapper.setArgumentValue(ArgumentType.TARGET, target);
  }

}
