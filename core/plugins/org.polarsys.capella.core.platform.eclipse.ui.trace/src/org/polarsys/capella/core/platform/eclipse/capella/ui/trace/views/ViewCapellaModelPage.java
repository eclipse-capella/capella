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
package org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.messages.Messages;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.components.CapellaModelTreeViewer;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

/**
 * @deprecated
 */
public class ViewCapellaModelPage extends WizardPage {

  /** Parent wizard */
  public AddTraceWizard parentWizard;
  /** model to display */
  private SystemEngineering system;
  /** capella model viewer */
  CapellaModelTreeViewer capellaModelViewer;

  /**
   * @param pageName
   */
  public ViewCapellaModelPage(String pageName, SystemEngineering sysEng, AddTraceWizard parentWizard) {
    super(pageName);
    this.system = sysEng;
    this.parentWizard = parentWizard;
  }

  protected Composite createInternalComposite(Composite parent) {
    Composite composite = new Composite(parent, SWT.NONE);
    // Install a layout manager, all widgets are displayed on 2 columns.
    composite.setLayout(new GridLayout(1, true));
    // Set its layout.
    GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
    composite.setLayoutData(gridData);
    return composite;
  }

  /**
   * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
   */
  public void createControl(Composite parent) {
    // **Layout definition
    FillLayout horizontalFillLayout = new FillLayout(SWT.HORIZONTAL);
    Composite composite = createInternalComposite(parent);

    // **Presentation of trace elements(source and target)
    Composite capellaTreeComposite = new Composite(composite, SWT.NONE);
    capellaTreeComposite.setLayout(horizontalFillLayout);
    capellaTreeComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

    capellaModelViewer = new CapellaModelTreeViewer(system);
    capellaModelViewer._currentNamedElement = parentWizard.getWorkingNamedElement();
    capellaModelViewer.getControl(capellaTreeComposite);

    // **Add listener
    capellaModelViewer._treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

      public void selectionChanged(SelectionChangedEvent event) {
        ISelection selection = event.getSelection();
        if (selection instanceof IStructuredSelection && ((IStructuredSelection) selection).size() == 1) {
          Object elem = ((IStructuredSelection) selection).getFirstElement();
          if (elem instanceof NamedElement) {
            if (checkElement((NamedElement) elem)) {
              setPageComplete(true);
              parentWizard.setCurrentNamedElement((NamedElement) elem);
              setMessage(null);
            } else {
              setPageComplete(false);
              setMessage(Messages.getString("AddTraceWizard.warning_element_already_exists"), IMessageProvider.WARNING); //$NON-NLS-1$
            }
          }
        }

      }

    });

    setControl(composite);
  }

  public boolean checkElement(NamedElement element) {
    NamedElement currentElt = parentWizard.getWorkingNamedElement();
    if (currentElt.equals(element)) {
      return false;
    }
    for (AbstractTrace trace : currentElt.getIncomingTraces()) {
      if (trace.getSourceElement() == element)
        return false;
      if (trace.getTargetElement() == element)
        return false;
    }
    for (AbstractTrace trace : currentElt.getOutgoingTraces()) {
      if (trace.getSourceElement() == element)
        return false;
      if (trace.getTargetElement() == element)
        return false;
    }
    return true;
  }

}
