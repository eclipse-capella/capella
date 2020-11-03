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

package org.polarsys.capella.core.platform.sirius.ui.project;

import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.platform.sirius.ui.project.operations.ProjectSessionCreationHelper;

/**
 * The wizard allowing to initialize a new Capella model.
 */
public class NewModelWizard extends BasicNewResourceWizard {

  // Log4j reference logger.
  private static final Logger __logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.UI);
  // The main page.
  private NewModelWizardPage _mainPage;

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
    _mainPage = new NewModelWizardPage("model.creation.page"); //$NON-NLS-1$
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
            SubMonitor progress = SubMonitor.convert(monitor_p, Messages.getString("NewModelWizard.title") + _mainPage.getModelName(), 1); //$NON-NLS-1$

            Object selectedObject = null;
            if (null != getSelection()) {
              selectedObject = getSelection().getFirstElement();
              if (selectedObject instanceof Project) {

                ProjectSessionCreationHelper helper = createSessionCreationHelper();
                helper.createNewSystemEngineering(((EObject) selectedObject).eResource(), _mainPage.getModelName(), progress);

                //_mainPage.createNewModel(_mainPage.getModelName(), progress.newChild(100));
              }
            }

            //
            progress.worked(1);
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

  protected ProjectSessionCreationHelper createSessionCreationHelper() {
    return new ProjectSessionCreationHelper(_mainPage.isEpbsSelected(), _mainPage.isOpaSelected());
  }
}
