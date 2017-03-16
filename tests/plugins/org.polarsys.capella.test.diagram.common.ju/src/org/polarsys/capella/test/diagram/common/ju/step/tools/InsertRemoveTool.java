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

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

public class InsertRemoveTool extends AbstractToolStep {

  boolean initialized = false;

  boolean insertAll = false;

  boolean removeAll = false;

  boolean autoRefresh = true;

  String containerId;
  protected String[] toInsert;
  protected String[] toRemove;
  protected String[] insertedElements;
  protected String[] removedElements;

  public InsertRemoveTool(DiagramContext context, String toolName) {
    this(context, toolName, context.getDiagramId());
  }

  public InsertRemoveTool(DiagramContext context, String[] toolIdentifier) {
    this(context, toolIdentifier, context.getDiagramId());
  }

  public InsertRemoveTool(DiagramContext context, String toolName, String containerId) {
    super(context, toolName);
    this.containerId = containerId;
  }

  public InsertRemoveTool(DiagramContext context, String[] toolIdentifier, String containerId) {
    super(context, toolIdentifier[0], toolIdentifier[1]);
    this.containerId = containerId;
  }

  public InsertRemoveTool(DiagramContext context, String toolName, String containerId, boolean autoRefresh) {
    this(context, toolName, containerId);
    this.autoRefresh = autoRefresh;
  }

  protected void initialize(boolean insertAll, boolean removeAll) {
    this.insertAll = insertAll;
    this.removeAll = removeAll;
    initialize(null, null, null, null);
  }

  protected void initialize(String[] toInsert, String[] toRemove, String[] insertedElements, String[] removedElements) {
    this.toInsert = toInsert;
    if (toInsert == null) {
      this.toInsert = new String[0];
    }
    this.toRemove = toRemove;
    if (toRemove == null) {
      this.toRemove = new String[0];
    }
    this.insertedElements = insertedElements;
    if (insertedElements == null) {
      this.insertedElements = new String[0];
    }
    this.removedElements = removedElements;
    if (removedElements == null) {
      this.removedElements = new String[0];
    }
    initialized = true;
  }

  @Override
  public Object run() {
    if (!initialized) {
      fail("Please use insert/remove methods instead of run.");
    }
    return super.run();
  }

  public void insertAll() {
    initialize(true, false);
    run();
  }

  public void insert(String... toInsert) {
    initialize(toInsert, null, toInsert, null);
    run();
  }

  public void insert(String[] toInsert, String[] insertedElements, String[] removedElements) {
    initialize(toInsert, null, insertedElements, removedElements);
    run();
  }

  public void remove(String... toRemove) {
    initialize(null, toRemove, null, toRemove);
    run();
  }

  public void remove(String[] toRemove, String[] insertedElements, String[] removedElements) {
    initialize(null, toRemove, insertedElements, removedElements);
    run();
  }

  public void insertRemove(String[] toInsert, String[] toRemove, String[] insertedElements, String[] removedElements) {
    initialize(null, toRemove, insertedElements, removedElements);
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
      @SuppressWarnings({ "unchecked", "synthetic-access", "rawtypes" })
      public Object getResult(java.util.Collection<? extends EObject> selections, Map<String, Object> parameters) {
        if (insertAll) {
          return AbstractExternalJavaAction.getScope(parameters);

        } else if (removeAll) {
          return Collections.emptyList();
        }

        Collection<EObject> objects = new HashSet<EObject>();
        DiagramContext context = getExecutionContext();
        Collection<EObject> inserted = context.adaptTool(InsertRemoveTool.this, parameters,
            context.getSemanticElements(insertedElements));
        Collection<EObject> removed = context.adaptTool(InsertRemoveTool.this, parameters,
            context.getSemanticElements(removedElements));
        objects.addAll(AbstractExternalJavaAction.getInitialSelection(parameters));
        objects.addAll(inserted);
        objects.removeAll(removed);
        return new ArrayList<EObject>(objects);

      }
    };
  }

  /**
   * @see org.polarsys.capella.test.diagram.common.ju.steps.AbstractExecuteToolCmdStep.tool.AbstractExecuteToolCmdTest#initToolArguments()
   */
  @Override
  protected void initToolArguments() {
    DSemanticDecorator containerView = getExecutionContext().getView(this.containerId);
    _toolWrapper.setArgumentValue(ArgumentType.CONTAINER, containerView.getTarget());
    _toolWrapper.setArgumentValue(ArgumentType.CONTAINER_VIEW, containerView);
  }

  /**
   * @see org.polarsys.capella.test.common.AbstractExtendedTest#postTestRun()
   */
  @SuppressWarnings("synthetic-access")
  @Override
  protected void postRunTest() {
    super.postRunTest();
    if (autoRefresh)
      DiagramHelper.refreshDiagram(getExecutionContext().getDiagram());

    for (String identifier : insertedElements) {
      getExecutionContext().hasView(identifier);
    }
    for (String identifier : removedElements) {
      getExecutionContext().hasntView(identifier);
    }
  }

  @Override
  public Object getResult() {
    return null;
  }

}
