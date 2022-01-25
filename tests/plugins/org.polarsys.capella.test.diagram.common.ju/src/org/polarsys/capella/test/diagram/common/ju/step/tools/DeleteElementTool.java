/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.helper.delete.DeleteHookHelper;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.junit.Assert;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.commands.preferences.service.ScopedCapellaPreferencesStore;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;
import org.polarsys.capella.core.model.preferences.IDeletePreferences;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.wrapper.Messages;
import org.polarsys.capella.test.diagram.common.ju.wrapper.factory.ToolWrapperFactory;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;
import org.polarsys.capella.test.framework.helpers.TestHelper;

public class DeleteElementTool extends AbstractToolStep<EObject> {

  protected DDiagramElement _elementToDelete;

  /**
   * @param context
   *          The diagram context in which the tool is used
   * @param containingToolDiagramContext
   *          The diagram context in which the tool is defined
   * @param toolName
   */
  public DeleteElementTool(DiagramContext context) {
    super(context, null);
  }

  /**
   * @param toolName
   *          is deprecated, please use without name of tool (for a mapping, there is at most one delete tool)
   */
  @Deprecated
  public DeleteElementTool(DiagramContext context, DiagramContext containingToolDiagramContext, String toolName) {
    super(context, toolName);
  }

  public void delete(DDiagramElement element) {
    disableConfirmation();
    
    _elementToDelete = element;
    final DeleteHookHelper deleteHookHelper = new DeleteHookHelper(Arrays.asList(element));
    if (!deleteHookHelper.checkDeleteHook()) {
      assertTrue(false);
    }
    run();
  }

  private void disableConfirmation() {
    // ScopedCapellaPreferencesStore.setValue(boolean) doesn't work if setDefault has same value
    ScopedCapellaPreferencesStore.getInstance(CapellaModelPreferencesPlugin.getDefault().getBundle().getSymbolicName()).setDefault(IDeletePreferences.PREFERENCE_CONFIRM_DELETE, true);
    ScopedCapellaPreferencesStore.getInstance(CapellaModelPreferencesPlugin.getDefault().getBundle().getSymbolicName()).setValue(IDeletePreferences.PREFERENCE_CONFIRM_DELETE, false);
  }

  public void deleteAll() {
    disableConfirmation();
    
    TestHelper.getExecutionManager(getExecutionContext().getSession()).execute(new AbstractReadWriteCommand() {
      public void run() {
        Collection<DDiagramElement> deletableElements = getDiagramContext().getDiagram().getDiagramElements().stream()
            .filter(x -> x.getDiagramElementMapping().getDeletionDescription() != null).collect(Collectors.toList());
        Collection<Command> commands = new ArrayList<Command>();

        final DeleteHookHelper deleteHookHelper = new DeleteHookHelper((Collection) deletableElements);
        if (!deleteHookHelper.checkDeleteHook()) {
          assertTrue(false);
        }

        for (DDiagramElement element : deletableElements) {
          _elementToDelete = element;
          preRunTest();
          Command cmd = _toolWrapper.createCommand();
          if (!UnexecutableCommand.INSTANCE.equals(cmd)) {
            commands.add(cmd);
          }
        }

        for (Command command : commands) {
          command.execute();
        }
      }
    });
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
    toolName = _elementToDelete.getDiagramElementMapping().getDeletionDescription().getName();
    AbstractToolDescription tool = _elementToDelete.getDiagramElementMapping().getDeletionDescription();
    Assert.assertNotNull(NLS.bind(Messages.toolDoesNotExist, toolName, getDiagramContext().getDiagram().getName()),
        tool);
    Assert.assertTrue(toolName != null && toolName == tool.getName());

    _toolWrapper = ToolWrapperFactory.INSTANCE.createToolCommandWrapper(tool);
    Assert.assertNotNull(NLS.bind(Messages.toolWrapperNotAvailable, tool.getName()), _toolWrapper);

    initToolArguments();

    boolean isArgumentOk = _toolWrapper.isArgumentsAreSet();
    Assert.assertTrue(Messages.toolWrapperArgumentErr, isArgumentOk);

    boolean isContextOk = _toolWrapper.isContextOk();
    Assert.assertTrue(NLS.bind(Messages.toolWrapperArgumentValueErr, tool.getName()), isContextOk);
  }

  @Override
  protected void dispose() {
    super.dispose();
    _elementToDelete = null;
  }

}
