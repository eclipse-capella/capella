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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.sirius.diagram.description.tool.DirectEditLabel;
import org.eclipse.sirius.diagram.tools.internal.command.UndoRedoCapableEMFCommandFactory;
import org.eclipse.sirius.diagram.ui.tools.api.command.GMFCommandWrapper;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.AbstractDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ToolHelper;

/**
 * This class performs a rename on an element in a diagram
 */
@SuppressWarnings({ "restriction", "rawtypes" })
public class RenameTool extends AbstractDiagramStep {
  UndoRedoCapableEMFCommandFactory commandFactory;
  private static String EDIT_LABEL_TOOL_NAME;
  private static String NEW_NAME;
  EObject renamedElement;

  public RenameTool(DiagramContext context) {
    super(context);
  }

  /**
   * 
   * @param context
   * @param editLabelToolName
   *          name of direct edit label tool to apply
   * @param renamedElement
   *          element whose name will be changed
   * @param newName
   *          new name to change to
   */
  public RenameTool(DiagramContext context, String editLabelToolName, EObject renamedElement, String newName) {
    super(context);
    EDIT_LABEL_TOOL_NAME = editLabelToolName;
    NEW_NAME = newName;
    this.renamedElement = renamedElement;
  }

  @Override
  public EObject getResult() {
    return null;
  }

  @Override
  protected void runTest() {
    SemanticEditingDomain editingDomain = (SemanticEditingDomain) getExecutionContext().getExecutionManager()
        .getEditingDomain();
    UndoRedoCapableEMFCommandFactory commandFactory = new UndoRedoCapableEMFCommandFactory(editingDomain);
    DRepresentationElement repElement = null;
    for (EObject obj : getDiagramContext().getDiagram().eContents()) {
      if (obj instanceof DRepresentationElement) {
        if (((DRepresentationElement) obj).getTarget() == renamedElement)
          repElement = (DRepresentationElement) obj;
      }
    }

    AbstractToolDescription tool = getRenameTool();

    final org.eclipse.emf.common.command.Command command = commandFactory.buildDirectEditLabelFromTool(repElement,
        (DirectEditLabel) tool, NEW_NAME);
    final CompoundCommand cc = new CompoundCommand();
    cc.add(new ICommandProxy(new GMFCommandWrapper(editingDomain, command)));

    cc.execute();
  }

  protected AbstractToolDescription getRenameTool() {
    ToolHelper toolhelper = new ToolHelper(getExecutionContext().getSession(), getDiagramContext().getDiagram());
    AbstractToolDescription tool = toolhelper.getTool(EDIT_LABEL_TOOL_NAME);
    return tool;
  }
}
