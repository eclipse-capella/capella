/*******************************************************************************
 * Copyright (c) 2017, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.re.project.handlers;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.helpers.operations.LongRunningListenersRegistry;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.scope.DefaultDependenciesHandler;
import org.polarsys.capella.common.re.ui.handlers.uihead.UIHeadHandler;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.re.commands.CreateRecCommand;
import org.polarsys.capella.core.re.commands.UpdateCurCommand;
import org.polarsys.capella.core.re.project.ReProjectScope;
import org.polarsys.capella.core.transition.common.commands.CommandHandler;
import org.polarsys.capella.core.transition.common.commands.DefaultCommand;
import org.polarsys.capella.core.transition.common.commands.LauncherCommand;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.transposer.SharedWorkflowActivityParameter;
import org.polarsys.kitalpha.cadence.core.api.parameter.GenericParameter;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public abstract class ProjectRecHandler extends CommandHandler {

  @Override
  public Object execute(Collection<?> selection, String name) throws ExecutionException {
    try {
      LongRunningListenersRegistry.getInstance().operationStarting(getClass());

      final AtomicReference<ICommand> cmdRef = new AtomicReference<ICommand>();
      IRunnableWithProgress progress = new IRunnableWithProgress() {
        @Override
        public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
          cmdRef.set(createInterruptableCommand(selection, monitor));
        }
      };
      try {
        new ProgressMonitorDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell()).run(true, true, progress);
      } catch (InvocationTargetException e) {
        throw new ExecutionException(e.getLocalizedMessage(), e);
      } catch (InterruptedException e) {
        // user canceled
      }

      ICommand cmd = cmdRef.get();
      if (cmd instanceof LauncherCommand) {
        ((LauncherCommand) cmd).setName(name);
      }
      if (cmd != null) {
        TransactionHelper.getExecutionManager(CapellaAdapterHelper.resolveSemanticsObjects(selection)).execute(cmd);
      } else {
        handleNullCommand();
      }
    } finally {
      LongRunningListenersRegistry.getInstance().operationEnded(getClass());
    }

    return null;
  }

  protected void handleNullCommand() {
    IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
    if (window != null) {
      MessageDialog.openInformation(window.getShell(),
          Messages.ProjectRecHandler_emptyLibraryDialogTitle, Messages.ProjectRecHandler_emtpyLibraryDialogMessage);
    }
  }

  protected ICommand createInterruptableCommand(Collection<?> selection, IProgressMonitor progressMonitor) throws InterruptedException {
    Collection<EObject> scope = ReProjectScope.getScope((EObject) selection.iterator().next(), progressMonitor);
    ICommand result = null;
    if (!scope.isEmpty()) {
      result = createRecProjectCommand(scope);
    }
    return result;
  }

  @Override
  protected ICommand createCommand(Collection<?> selection, IProgressMonitor progressMonitor) {
    try {
      return createInterruptableCommand(selection, progressMonitor);
    } catch (InterruptedException e) {
    }
    return null;
  }

  protected abstract ICommand createRecProjectCommand(Collection<EObject> elementsForRec);

  public static class UICreate extends ProjectRecHandler {
    @Override
    protected ICommand createRecProjectCommand(Collection<EObject> elementsForRec) {
      return customizeParameters(new CreateRecCommand(elementsForRec, new NullProgressMonitor()), true, false);
    }
  }
  
  public static class Create extends ProjectRecHandler {
    @Override
    protected ICommand createRecProjectCommand(Collection<EObject> elementsForRec) {
      return customizeParameters(new CreateRecCommand(elementsForRec, new NullProgressMonitor()), false, false);
    }
  }

  public static class UIUpdate extends ProjectRecHandler {
    @Override
    protected ICommand createRecProjectCommand(Collection<EObject> elementsForRec) {
      return customizeParameters(new UpdateCurCommand(elementsForRec, new NullProgressMonitor()), true, true);
    }
  }
  
  public static class Update extends ProjectRecHandler {
    @Override
    protected ICommand createRecProjectCommand(Collection<EObject> elementsForRec) {
      return customizeParameters(new UpdateCurCommand(elementsForRec, new NullProgressMonitor()), false, true);
    }
  }

  /**
   * - Show the wizard.
   * - During update also show diffmerge.
   * - Override dependencies handler to not include any other elements, since we already calculated all required elements
   */
  protected final DefaultCommand customizeParameters(DefaultCommand command, boolean showOptions, boolean showDiffmerge) {
    SharedWorkflowActivityParameter param = new UIHeadHandler(showOptions, showDiffmerge);
    param.addSharedParameter(
        new GenericParameter<IHandler>(IReConstants.DEPENDENCIES_HANDLER, new DefaultDependenciesHandler() {
          @Override
          public Collection<EObject> getScopeElements(Collection<EObject> initialScopeElements, Collection<EObject> scopeElements, IContext context) {
            return new ArrayList<>(initialScopeElements);
          }

          @Override
          public Collection<EObject> getComplementaryScopeElements(Collection<EObject> initialScopeElements, Collection<EObject> scopeElements, IContext context) {
            return new ArrayList<>(initialScopeElements);
          }

    }, Messages.ProjectRecHandler_OptionsHandler));
    command.addParameters(param);
    return command;
  }

}
