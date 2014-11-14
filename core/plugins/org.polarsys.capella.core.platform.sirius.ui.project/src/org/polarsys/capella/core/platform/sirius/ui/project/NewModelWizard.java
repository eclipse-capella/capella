/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.platform.sirius.ui.project;

import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;

import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.model.skeleton.EngineeringDomain;

/**
 * The wizard allowing to initialize a new Capella model.
 */
public class NewModelWizard extends BasicNewResourceWizard {
  /**
   * Step tick count
   */
  private static final int STEP_TICK_COUNT = 100;
  // Log4j reference logger.
  private static final Logger __logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.UI);
  // The main page.
  private NewModelWizardPage _mainPage;

  /**
   * Constructs the wizard allowing to initialize a new Capella model.
   */
  public NewModelWizard() {
    // Do nothing.
  }

  /**
   * @see org.eclipse.ui.wizards.newresource.BasicNewResourceWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
   */
  @Override
  public void init(IWorkbench workbench_p, IStructuredSelection currentSelection_p) {
    super.init(workbench_p, currentSelection_p);
    setWindowTitle(Messages.getString("NewModelWizard.window.title")); //$NON-NLS-1$
    setNeedsProgressMonitor(true);
  }

  /**
   * Add the one page to the wizard, the reused page <code>NewModelWizardPage</code>. This page provides basic model name validation and allows for.
   * @see org.eclipse.jface.wizard.Wizard#addPages()
   */
  @Override
  public void addPages() {
    _mainPage = new NewModelWizardPage("model.creation.page", getSelection()); //$NON-NLS-1$
    _mainPage.setTitle(Messages.getString("NewModelWizard.title")); //$NON-NLS-1$
    _mainPage.setDescription(Messages.getString("NewModelWizard.description")); //$NON-NLS-1$
    addPage(_mainPage);
  }

  /**
   * @see org.eclipse.jface.wizard.Wizard#performFinish()
   */
  @Override
  public boolean performFinish() {
    try {
      getContainer().run(false, false, new IRunnableWithProgress() {
        /**
         * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
         */
        @SuppressWarnings("synthetic-access")
        public void run(IProgressMonitor monitor_p) throws InvocationTargetException, InterruptedException {
          try {
            int stepCount = 2;
            SubMonitor progress =
                SubMonitor.convert(monitor_p, Messages.getString("NewModelWizard.title") + _mainPage.getModelName(), STEP_TICK_COUNT * stepCount); //$NON-NLS-1$
            // Step 1
            EngineeringDomain domain = EngineeringDomain.Software;
            if (_mainPage.isOpaSelected()) {
              domain = EngineeringDomain.System;
            }
            progress.worked(STEP_TICK_COUNT);

            // Step 2 : Create the new model.
            _mainPage.createNewModel(domain, _mainPage.getModelName(), progress.newChild(STEP_TICK_COUNT));
            progress.worked(STEP_TICK_COUNT);
          } finally {
            monitor_p.done();
          }
        }
      });
    } catch (InvocationTargetException exception_p) {
      StringBuilder loggerMessage = new StringBuilder("NewModelWizard.performFinish(..) _ "); //$NON-NLS-1$
      loggerMessage.append(exception_p.getMessage());
      __logger.warn(new EmbeddedMessage(loggerMessage.toString(), IReportManagerDefaultComponents.UI), exception_p);
    } catch (InterruptedException exception_p) {
      StringBuilder loggerMessage = new StringBuilder("NewModelWizard.performFinish(..) _ "); //$NON-NLS-1$
      loggerMessage.append(exception_p.getMessage());
      __logger.warn(new EmbeddedMessage(loggerMessage.toString(), IReportManagerDefaultComponents.UI), exception_p);
    }
    return true;
  }
}
