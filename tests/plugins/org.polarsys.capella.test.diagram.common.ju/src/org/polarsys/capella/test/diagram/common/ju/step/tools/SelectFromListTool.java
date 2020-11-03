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
package org.polarsys.capella.test.diagram.common.ju.step.tools;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.sirius.analysis.actions.extensions.AbstractExternalJavaAction;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.headless.HeadlessResultOpProvider;
import org.polarsys.capella.test.diagram.common.ju.headless.IHeadlessResult;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.context.SessionContext;

public class SelectFromListTool extends AbstractToolStep<Object> {

  boolean initialized = false;

  boolean selectAll = false;

  boolean autoRefresh = true;

  String containerId;
  protected String[] insertedElements;

  protected String[] selectedElements;

  public SelectFromListTool(DiagramContext context, String toolName, String containerId, String... insertedElements) {
    super(context, toolName);
    this.containerId = containerId;
    this.insertedElements = insertedElements;
    if (insertedElements == null) {
      this.insertedElements = new String[0];
    }
  }

  public SelectFromListTool(DiagramContext context, String toolName, String containerId, boolean autoRefresh,
      String... insertedElements) {
    this(context, toolName, containerId, insertedElements);
    this.autoRefresh = autoRefresh;
  }

  protected void initialize(boolean selectAll) {
    this.selectAll = selectAll;
    initialized = true;
  }

  protected void initialize(String... selectedElements) {
    this.selectedElements = selectedElements;
    if (selectedElements == null) {
      this.selectedElements = new String[0];
    }
    initialized = true;
  }

  @Override
  public Object run() {
    if (!initialized) {
      fail("Please use select methods instead of run.");
    }
    return super.run();
  }

  public void select(String... ids) {
    initialize(ids);
    run();
  }

  public void selectAll() {
    initialize(true);
    run();
  }

  /**
   * @see org.polarsys.capella.test.diagram.common.ju.steps.AbstractExecuteToolCmdStep.tool.AbstractExecuteToolCmdTest#preTestRun()
   */
  @Override
  protected void preRunTest() {
    HeadlessResultOpProvider.INSTANCE.setCurrentOp(createOperation());
    super.preRunTest();
  }

  /**
   * @return
   */
  protected IHeadlessResult createOperation() {
    return new IHeadlessResult() {

      @Override
      public Object getResult(java.util.Collection<? extends EObject> selections, Map<String, Object> parameters) {
        if (selectAll) {
          return AbstractExternalJavaAction.getScope(parameters);
        }

        Collection<EObject> objects = new HashSet<EObject>();
        DiagramContext diagramContext = getDiagramContext();
        SessionContext sessionContext = getExecutionContext();
        Collection<EObject> selected = diagramContext.adaptTool(SelectFromListTool.this, parameters,
            sessionContext.getSemanticElements(selectedElements));
        objects.addAll(AbstractExternalJavaAction.getScope(parameters));
        return new ArrayList<EObject>(objects);

      }
    };
  }

  /**
   * @see org.polarsys.capella.test.diagram.common.ju.steps.AbstractExecuteToolCmdStep.tool.AbstractExecuteToolCmdTest#initToolArguments()
   */
  @Override
  protected void initToolArguments() {
    DSemanticDecorator containerView = getDiagramContext().getView(this.containerId);
    _toolWrapper.setArgumentValue(ArgumentType.CONTAINER, containerView.getTarget());
    _toolWrapper.setArgumentValue(ArgumentType.CONTAINER_VIEW, containerView);
  }

  /**
   * @see org.polarsys.capella.test.common.AbstractExtendedTest#postTestRun()
   */
  @Override
  protected void postRunTest() {
    super.postRunTest();
    if (autoRefresh)
      DiagramHelper.refreshDiagram(getDiagramContext().getDiagram());

    for (String identifier : insertedElements) {
      getDiagramContext().hasView(identifier);
    }
  }

  @Override
  public Object getResult() {
    return null;
  }

}
