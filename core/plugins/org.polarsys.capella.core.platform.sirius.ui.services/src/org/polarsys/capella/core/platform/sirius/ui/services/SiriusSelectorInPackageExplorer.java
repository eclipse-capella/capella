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

package org.polarsys.capella.core.platform.sirius.ui.services;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonNavigator;

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
  /**
   * The runnable which select the specified model element.
   */
  public class SelectionRunnable implements Runnable {
    // The model element to select.
    private EObject _element;

    /**
     * Constructs the runnable which select the specified model element.
     * @param element_p the model element to select.
     */
    public SelectionRunnable(EObject element_p) {
      _element = element_p;
    }

    /**
     * @see java.lang.Runnable#run()
     */
    @SuppressWarnings("synthetic-access")
    public void run() {
      if (null == _element) {
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
        if ((null != viewPart) && (viewPart instanceof CommonNavigator)) {
          CommonNavigator navigator = (CommonNavigator) viewPart;
          ISelection selection = new StructuredSelection(_element);
          navigator.getCommonViewer().setSelection(selection);
        }
      } catch (PartInitException pie) {
        __logger.debug(new EmbeddedMessage(pie.getMessage(), IReportManagerDefaultComponents.UI));
      }
    }
  }

  // Log4j reference logger.
  private Logger __logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.UI);

  /**
   * 
   */
  public SiriusSelectorInPackageExplorer() {
    // Do nothing.
  }

  /**
   * @see org.polarsys.capella.common.ui.services.ISelectorInPackageExplorer#selectInPackageExplorer(org.polarsys.capella.common.model.IModelElement)
   */
  public void selectInPackageExplorer(EObject element_p) {
    if (null == element_p) {
      return;
    }

    Display display = PlatformUI.getWorkbench().getDisplay();

    // when a Capella element is contained in an AspectPkg hierarchy, this AspectPkg is forced to be shown
    if (EcoreUtil2.isContainedBy(element_p, CapellacommonPackage.Literals.ABSTRACT_CAPABILITY_PKG)) {
      AbstractCapabilityPkg aspectPkg = (AbstractCapabilityPkg) EcoreUtil2.getFirstContainer(element_p, CapellacommonPackage.Literals.ABSTRACT_CAPABILITY_PKG);
      if (aspectPkg != null) {
        SelectionRunnable selectionRunnable = new SelectionRunnable(aspectPkg.eContainer());
        display.asyncExec(selectionRunnable);
      }
    }

    SelectionRunnable selectionRunnable = new SelectionRunnable(element_p);
    display.asyncExec(selectionRunnable);
  }
}