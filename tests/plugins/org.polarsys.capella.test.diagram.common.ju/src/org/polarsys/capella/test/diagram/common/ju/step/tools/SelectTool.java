/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
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

import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.ui.IEditorPart;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.wrapper.OperationActionToolDescriptionWrapper;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ToolHelper;

public class SelectTool extends AbstractToolStep<Collection<DSemanticDecorator>> {

  boolean initialized = false;

  String containerId = null;

  boolean ensurePrecondition = false;

  /**
   * Set value to true if the test shall not be run if the precondition of tool is not valid.
   */
  public SelectTool ensurePrecondition(boolean v) {
    ensurePrecondition = v;
    return this;
  }

  @Override
  protected void runTest() {
    if (!ensurePrecondition || ((OperationActionToolDescriptionWrapper) _toolWrapper).isPreconditionOk()) {
      super.runTest();
    }
  }

  public SelectTool(DiagramContext context, String toolName) {
    super(context, toolName);
  }

  @Override
  public Collection<DSemanticDecorator> run() {
    if (!initialized) {
      fail("Please use select method instead of run.");
    }
    return super.run();
  }

  public void cannotRun(String id) {
    containerId = id;
    super.cannotRun();
  }

  public void select(String id) {
    initialized = true;
    containerId = id;
    run();
  }

  @Override
  protected void initToolArguments() {
    DSemanticDecorator containerView = getDiagramContext().getView(this.containerId);
    ArrayList<DSemanticDecorator> a = new ArrayList<DSemanticDecorator>();
    a.add(containerView);
    _toolWrapper.setArgumentValue(ArgumentType.COLLECTION, a);
  }

  @Override
  protected void postRunTest() {
    super.postRunTest();
    ToolHelper.checkSelectionOk((OperationActionToolDescriptionWrapper) _toolWrapper);
  }

  @Override
  public Collection<DSemanticDecorator> getResult() {
    IEditorPart part = DiagramHelper.getDiagramEditor(getExecutionContext().getSession(),
        getDiagramContext().getDiagram());
    Collection<DSemanticDecorator> selectedViews = DialectUIManager.INSTANCE.getSelection((DialectEditor) part);
    return selectedViews;
  }
}
