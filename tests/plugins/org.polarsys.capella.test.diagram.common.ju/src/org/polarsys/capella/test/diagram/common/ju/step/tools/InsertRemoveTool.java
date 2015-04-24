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
import org.eclipse.sirius.diagram.DDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.headless.HeadlessResultOpProvider;
import org.polarsys.capella.test.diagram.common.ju.headless.ITransfertWizardResult;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;


public class InsertRemoveTool extends AbstractToolStep {

  boolean initialized = false;

  String[] toInsert;
  String[] toRemove;
  String[] insertedElements;
  String[] removedElements;

  public InsertRemoveTool(DiagramContext context, String toolName) {
    super(context, toolName);
  }

  protected void initialize(String[] toInsert_p, String[] toRemove_p, String[] insertedElements_p, String[] removedElements_p) {
    toInsert = toInsert_p;
    if (toInsert == null) {
      toInsert = new String[0];
    }
    toRemove = toRemove_p;
    if (toRemove == null) {
      toRemove = new String[0];
    }
    insertedElements = insertedElements_p;
    if (insertedElements == null) {
      insertedElements = new String[0];
    }
    removedElements = removedElements_p;
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

  public void insert(String... toInsert_p) {
    initialize(toInsert_p, null, toInsert_p, null);
    run();
  }

  public void insert(String[] toInsert_p, String[] insertedElements_p, String[] removedElements_p) {
    initialize(toInsert_p, null, insertedElements_p, removedElements_p);
    run();
  }

  public void remove(String... toRemove_p) {
    initialize(null, toRemove_p, null, toRemove_p);
    run();
  }

  public void remove(String[] toRemove_p, String[] insertedElements_p, String[] removedElements_p) {
    initialize(null, toRemove_p, insertedElements_p, removedElements_p);
    run();
  }

  public void insertRemove(String[] toInsert_p, String[] toRemove_p, String[] insertedElements_p, String[] removedElements_p) {
    initialize(null, toRemove_p, insertedElements_p, removedElements_p);
    run();
  }

  /**
   * @see org.polarsys.capella.test.diagram.common.ju.steps.AbstractExecuteToolCmdStep.tool.AbstractExecuteToolCmdTest#preTestRun()
   */
  @Override
  protected void preRunTest() {
    ITransfertWizardResult op = new ITransfertWizardResult() {

      @Override
      @SuppressWarnings({ "unchecked", "synthetic-access", "rawtypes" })
      public Object getResult(java.util.Collection<? extends EObject> selections_p, Map<String, Object> parameters_p) {
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
    DDiagram container = getExecutionContext().getDiagram();
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
