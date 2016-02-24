/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.common.ju.step.tools;

import static org.junit.Assert.assertFalse;

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

public class InsertRemoveContainerCreation extends AbstractToolStep {

  boolean initialized = false;

  String containerId;
  String[] toInsert;
  String[] toRemove;
  String[] insertedElements;
  String[] removedElements;

  public InsertRemoveContainerCreation(DiagramContext context, String toolName) {
    this(context, toolName, context.getDiagramId());
  }

  public InsertRemoveContainerCreation(DiagramContext context, String[] toolIdentifier) {
    super(context, toolIdentifier[0], toolIdentifier[1]);
    this.containerId = context.getDiagramId();
  }

  public InsertRemoveContainerCreation(DiagramContext context, String toolName, String containerId) {
    super(context, toolName);
    this.containerId = containerId;
  }

  public InsertRemoveContainerCreation(DiagramContext context, String[] toolIdentifier, String containerId) {
    super(context, toolIdentifier[0], toolIdentifier[1]);
    this.containerId = containerId;
  }

  protected void initialize(String[] toInsert_input, String[] toRemove_input, String[] insertedElements_input,
      String[] removedElements_input) {
    toInsert = toInsert_input;
    if (toInsert == null) {
      toInsert = new String[0];
    }
    toRemove = toRemove_input;
    if (toRemove == null) {
      toRemove = new String[0];
    }
    insertedElements = insertedElements_input;
    if (insertedElements == null) {
      insertedElements = new String[0];
    }
    removedElements = removedElements_input;
    if (removedElements == null) {
      removedElements = new String[0];
    }
    initialized = true;
  }

  @Override
  public Object run() {
    if (!initialized) {
      assertFalse("Please use insert/remove methods instead of run.", true);
    }
    return super.run();
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
    IHeadlessResult op = new IHeadlessResult() {

      @Override
      @SuppressWarnings({ "unchecked", "synthetic-access", "rawtypes" })
      public Object getResult(java.util.Collection<? extends EObject> selections, Map<String, Object> parameters) {
        Collection<EObject> objects = new HashSet<EObject>();
        DiagramContext context = getExecutionContext();
        Collection<EObject> inserted = context.adaptTool(InsertRemoveContainerCreation.this, parameters,
            context.getSemanticElements(insertedElements));
        Collection<EObject> removed = context.adaptTool(InsertRemoveContainerCreation.this, parameters,
            context.getSemanticElements(removedElements));
        objects.addAll(AbstractExternalJavaAction.getInitialSelection(parameters));
        objects.addAll(inserted);
        objects.removeAll(removed);
        return objects;
      }
    };

    HeadlessResultOpProvider.INSTANCE.setCurrentOp(op);
    super.preRunTest();
  }

  /**
   * @see org.polarsys.capella.test.diagram.common.ju.steps.AbstractExecuteToolCmdStep.tool.AbstractExecuteToolCmdTest#initToolArguments()
   */
  @Override
  protected void initToolArguments() {

    EObject containerView = getExecutionContext().getView(containerId);
    _toolWrapper.setArgumentValue(ArgumentType.CONTAINER_VIEW, containerView);
    _toolWrapper.setArgumentValue(ArgumentType.CONTAINER, ((DSemanticDecorator) containerView).getTarget());
  }

  /**
   * @see org.polarsys.capella.test.common.AbstractExtendedTest#postTestRun()
   */
  @SuppressWarnings("synthetic-access")
  @Override
  protected void postRunTest() {
    super.postRunTest();

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
