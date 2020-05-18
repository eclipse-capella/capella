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

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramComponentizationManager;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.junit.Assert;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.wrapper.Messages;
import org.polarsys.capella.test.diagram.common.ju.wrapper.factory.ToolWrapperFactory;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;

public class DeleteElementTool extends AbstractToolStep<EObject> {

  DDiagramElement _elementToDelete;
  DDiagram _containingToolDiagram;

  /**
   * 
   * @param context
   *          The diagram context in which the tool is used
   * @param containingToolDiagramContext
   *          The diagram context in which the tool is defined
   * @param toolName
   */
  public DeleteElementTool(DiagramContext context, DiagramContext containingToolDiagramContext, String toolName) {
    super(context, toolName);
    _containingToolDiagram = containingToolDiagramContext.getDiagram();
  }

  public void delete(DDiagramElement element) {
    _elementToDelete = element;
    run();
  }

  /**
   * @see org.polarsys.capella.test.diagram.common.ju.steps.AbstractExecuteToolCmdStep.tool.AbstractExecuteToolCmdTest#initToolArguments()
   */
  @Override
  protected void initToolArguments() {
    _toolWrapper.setArgumentValue(ArgumentType.TARGET, _elementToDelete);
    _toolWrapper.setArgumentValue(ArgumentType.ON_DIAGRAM_ONLY, false);
  }

  @Override
  public EObject getResult() {
    return null;
  }

  /**
   * Override the preRunTest to find the tool defined in another diagram description
   */
  @Override
  protected void preRunTest() {
    AbstractToolDescription tool = getTool(getExecutionContext().getSession(), _containingToolDiagram, toolName);
    Assert.assertNotNull(NLS.bind(Messages.toolDoesNotExist, toolName), tool);

    _toolWrapper = ToolWrapperFactory.INSTANCE.createToolCommandWrapper(tool);
    Assert.assertNotNull(NLS.bind(Messages.toolWrapperNotAvailable, toolName), _toolWrapper);

    initToolArguments();

    boolean isArgumentOk = _toolWrapper.isArgumentsAreSet();
    Assert.assertTrue(Messages.toolWrapperArgumentErr, isArgumentOk);

    boolean isContextOk = _toolWrapper.isContextOk();
    Assert.assertTrue(NLS.bind(Messages.toolWrapperArgumentValueErr, toolName), isContextOk);
  }

  /**
   * Find the tool defined in a diagram
   * 
   * @param session
   * @param diagram
   * @param toolName
   * @return
   */
  private AbstractToolDescription getTool(Session session, DDiagram diagram, final String toolName) {
    final List<AbstractToolDescription> tools = new DiagramComponentizationManager().getAllTools(
        session.getSelectedViewpoints(true), diagram.getDescription());
    AbstractToolDescription theAbstractToolDescription = null;

    for (AbstractToolDescription current : tools) {
      if (current.getName().equals(toolName)) {
        theAbstractToolDescription = current;
        break;
      }
    }
    return theAbstractToolDescription;
  }
}
