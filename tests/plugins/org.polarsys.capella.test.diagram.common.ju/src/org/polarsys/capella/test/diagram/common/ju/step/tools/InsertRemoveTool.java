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
package org.polarsys.capella.test.diagram.common.ju.step.tools;

import static org.junit.Assert.assertFalse;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.headless.HeadlessResultOpProvider;
import org.polarsys.capella.test.diagram.common.ju.headless.IHeadlessResult;
import org.polarsys.capella.test.diagram.common.ju.headless.ITransfertWizardResult;
import org.polarsys.capella.test.diagram.common.ju.wrapper.AbstractToolWrapper.ArgumentData;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;

public class InsertRemoveTool extends AbstractToolStep {

  boolean initialized = false;

  protected String container;
  protected String[] toInsert;
  protected String[] toRemove;
  protected String[] insertedElements;
  protected String[] removedElements;

  public InsertRemoveTool(DiagramContext context, String toolName) {
    super(context, toolName);
  }

  public InsertRemoveTool(DiagramContext context, String toolName, String container) {
    super(context, toolName);
    this.container = container;
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
    HeadlessResultOpProvider.INSTANCE.setCurrentOp(createOperation());
    super.preRunTest();
  }

  /**
   * @return
   */
  protected IHeadlessResult createOperation() {
    return new ITransfertWizardResult() {

      @Override
      @SuppressWarnings({ "unchecked", "synthetic-access", "rawtypes" })
      public Object getResult(java.util.Collection<? extends EObject> selections, Map<String, Object> parameters) {
        return getExecutionContext().getSemanticElements(insertedElements);
      }
    };
  }

  /**
   * @see org.polarsys.capella.test.diagram.common.ju.steps.AbstractExecuteToolCmdStep.tool.AbstractExecuteToolCmdTest#initToolArguments()
   */
  @Override
  protected void initToolArguments() {
    DSemanticDecorator containerView = (DSemanticDecorator) getExecutionContext().getDiagram();
    if (this.container != null) {
      containerView = getExecutionContext().getView(this.container);
    }

    for (ArgumentData data : _toolWrapper.getArgumentTypes()) {
      if (data.getType().equals(ArgumentType.CONTAINER)) {
        _toolWrapper.setArgumentValue(ArgumentType.CONTAINER, containerView.getTarget());
      }
      if (data.getType().equals(ArgumentType.CONTAINER_VIEW)) {
        _toolWrapper.setArgumentValue(ArgumentType.CONTAINER_VIEW, containerView);
      }
    }
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
