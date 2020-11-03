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

package org.polarsys.capella.core.platform.sirius.ui.services;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.IShowInTarget;
import org.eclipse.ui.part.ShowInContext;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.ui.services.ISelectorInPackageExplorer;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.platform.sirius.ui.perspective.CapellaPerspective;

/**
 * The selection manager for the RSx platform.
 */
public class SiriusSelectorInPackageExplorer implements ISelectorInPackageExplorer {
  
  // Log4j reference logger.
  private Logger logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.UI);
  
  /**
   * The runnable which select the specified model element.
   */
  public class SelectionRunnable implements Runnable {
    // The model element to select.
    private EObject element;

    /**
     * Constructs the runnable which select the specified model element.
     * @param element the model element to select.
     */
    public SelectionRunnable(EObject element) {
      this.element = element;
    }

    /**
     * @see java.lang.Runnable#run()
     */
    @SuppressWarnings("synthetic-access")
    public void run() {
      if (null == element) {
        return;
      }

      IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
      if (null == window) {
        return;
      }

      IWorkbenchPage page = window.getActivePage();
      if (null == page) {
        return;
      }

      try {
        // Gets the right explorer identifier.
        String viewId = CapellaPerspective.CAPELLA_PROJECT_EXPLORER_ID;
        IViewPart viewPart = page.showView(viewId);
        if (null != viewPart) {
          IStructuredSelection selection = new StructuredSelection(element);
          IShowInTarget showInTarget = (IShowInTarget) viewPart.getAdapter(IShowInTarget.class);
          if (showInTarget != null) {
            showInTarget.show(new ShowInContext(null, selection));
          }
        }
      } catch (PartInitException pie) {
        logger.debug(new EmbeddedMessage(pie.getMessage(), IReportManagerDefaultComponents.UI));
      }
    }
  }

  /**
   * 
   */
  public SiriusSelectorInPackageExplorer() {
    // Do nothing.
  }

  /**
   * @see org.polarsys.capella.common.ui.services.ISelectorInPackageExplorer#selectInPackageExplorer(org.polarsys.capella.common.model.IModelElement)
   */
  public void selectInPackageExplorer(EObject element) {
    if (null == element) {
      return;
    }

    Display display = PlatformUI.getWorkbench().getDisplay();

    // When a Capella element is contained in an AspectPkg hierarchy, this AspectPkg is forced to be shown
    if (EcoreUtil2.isContainedBy(element, CapellacommonPackage.Literals.ABSTRACT_CAPABILITY_PKG)) {
      AbstractCapabilityPkg aspectPkg = (AbstractCapabilityPkg) EcoreUtil2.getFirstContainer(element, CapellacommonPackage.Literals.ABSTRACT_CAPABILITY_PKG);
      if (aspectPkg != null) {
        SelectionRunnable selectionRunnable = new SelectionRunnable(aspectPkg.eContainer());
        display.asyncExec(selectionRunnable);
      }
    }

    SelectionRunnable selectionRunnable = new SelectionRunnable(element);
    display.asyncExec(selectionRunnable);
  }
}