/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

import static org.junit.Assert.assertFalse;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.headless.HeadlessResultOpProvider;
import org.polarsys.capella.test.diagram.common.ju.headless.IHeadlessResult;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;

public class SwitchTool extends AbstractToolStep {

  boolean initialized = false;

  String[] toInsert;
  String[] toRemove;
  String[] insertedElements;
  String[] removedElements;

  public SwitchTool(DiagramContext context, String toolName) {
    super(context, toolName);
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
      public Object getResult(java.util.Collection<? extends EObject> selections, Map<String, Object> parameters) {
        return getExecutionContext().getSemanticElements(insertedElements);
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
    DDiagram container = getDiagramContext().getDiagram();
    _toolWrapper.setArgumentValue(ArgumentType.CONTAINER_VIEW, container);
  }

  /**
   * @see org.polarsys.capella.test.common.AbstractExtendedTest#postTestRun()
   */
  @SuppressWarnings("synthetic-access")
  @Override
  protected void postRunTest() {
    super.postRunTest();

    for (String identifier : insertedElements) {
      getDiagramContext().hasView(identifier);
    }
    for (String identifier : removedElements) {
      getDiagramContext().hasntView(identifier);
    }
  }

  @Override
  public Object getResult() {
    return null;
  }

}
