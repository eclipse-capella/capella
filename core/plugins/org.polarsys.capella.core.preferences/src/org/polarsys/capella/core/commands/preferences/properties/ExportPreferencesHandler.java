/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.commands.preferences.properties;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.EvaluationResult;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.ExpressionInfo;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchCommandConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.internal.IWorkbenchHelpContextIds;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.dialogs.ImportExportWizard;
import org.eclipse.ui.wizards.IWizardRegistry;

import org.polarsys.capella.core.preferences.transferer.CapellaImportExportPreferences;

/**
 */
public class ExportPreferencesHandler extends AbstractHandler {

  /*
	 * 
	 */

  private static ExportPreferencesHandler instance;

  public static ExportPreferencesHandler getInstance(Command command) {
    if (instance == null) {
      instance = new ExportPreferencesHandler(command);
    }
    return instance;
  }

  private static final int SIZING_WIZARD_WIDTH = 470;
  private static final int SIZING_WIZARD_HEIGHT = 550;

  protected String getWizardIdParameterId() {
    return IWorkbenchCommandConstants.FILE_EXPORT_PARM_WIZARDID;
  }

  protected IWizardRegistry getWizardRegistry() {
    return PlatformUI.getWorkbench().getExportWizardRegistry();
  }

  /*
	 * 
	 */
  private Command currentCommand;

  /*
	 * 
	 */
  IHandlerService handlerService = (IHandlerService) PlatformUI.getWorkbench().getService(IHandlerService.class);

  private static CapellaImportExportPreferences wizard;

  /**
	 * 
	 */
  private ExportPreferencesHandler(Command command_p) {
    this.currentCommand = command_p;
    activatePreferenceHandler();
  }

  /**
	 * 
	 */
  private void activatePreferenceHandler() {
    handlerService.activateHandler(currentCommand.getId(), this, new Expression() {

      @Override
      public EvaluationResult evaluate(IEvaluationContext context_p) throws CoreException {
        Object selection = context_p.getVariable("selection");
        boolean result = true;
        if ((selection != null) && (selection instanceof ISelection)) {
          result = true;
        }
        return EvaluationResult.valueOf(result);
      }

      @Override
      public void collectExpressionInfo(final ExpressionInfo info) {
        info.markDefaultVariableAccessed();
      }

    });

    currentCommand.setHandler(this);
  }

  /**
   * {@inheritDoc}
   */

  public Object execute(ExecutionEvent event) throws ExecutionException {

    IWorkbenchWindow activeWorkbenchWindow = HandlerUtil.getActiveWorkbenchWindow(event);
    if (activeWorkbenchWindow == null) {
      // action has been disposed
      return null;
    }

    wizard = new CapellaImportExportPreferences(ImportExportWizard.EXPORT);

    IStructuredSelection selectionToPass = getSelectionToUse(event);

    wizard.init(activeWorkbenchWindow.getWorkbench(), selectionToPass);
    IDialogSettings workbenchSettings = WorkbenchPlugin.getDefault().getDialogSettings();
    IDialogSettings wizardSettings = workbenchSettings.getSection("ImportExportAction"); //$NON-NLS-1$
    if (wizardSettings == null) {
      wizardSettings = workbenchSettings.addNewSection("ImportExportAction"); //$NON-NLS-1$
    }
    wizard.setDialogSettings(wizardSettings);
    wizard.setForcePreviousAndNextButtons(true);

    Shell parent = activeWorkbenchWindow.getShell();
    WizardDialog dialog = new WizardDialog(parent, wizard);
    dialog.create();
    dialog.getShell().setSize(Math.max(SIZING_WIZARD_WIDTH, dialog.getShell().getSize().x), SIZING_WIZARD_HEIGHT);
    activeWorkbenchWindow.getWorkbench().getHelpSystem().setHelp(dialog.getShell(), IWorkbenchHelpContextIds.EXPORT_WIZARD);
    dialog.open();
    return null;
  }

  public static CapellaImportExportPreferences getWizard() {
    return wizard;
  }

  public void setWizard(CapellaImportExportPreferences wizard_p) {
    wizard = wizard_p;
  }

  /**
   * Returns a structured selection based on the event to initialize the wizard with.
   * @param event the event object containing information about the current state of the application
   * @return the current structured selection of the application
   */
  protected IStructuredSelection getSelectionToUse(ExecutionEvent event) {
    ISelection selection = HandlerUtil.getCurrentSelection(event);
    if (selection instanceof IStructuredSelection) {
      return (IStructuredSelection) selection;
    }
    return StructuredSelection.EMPTY;
  }

}
