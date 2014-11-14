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
  public AddTraceWizard _parentWizard;
  /** model to display */
  private SystemEngineering _system;
  /** capella model viewer */
  CapellaModelTreeViewer _capellaModelViewer;

  /**
   * @param pageName_p
   */
  public ViewCapellaModelPage(String pageName_p, SystemEngineering sysEng_p, AddTraceWizard parentWizard_p) {
    super(pageName_p);
    _system = sysEng_p;
    _parentWizard = parentWizard_p;
  }

  protected Composite createInternalComposite(Composite parent_p) {
    Composite composite = new Composite(parent_p, SWT.NONE);
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
  public void createControl(Composite parent_p) {
    // **Layout definition
    FillLayout horizontalFillLayout = new FillLayout(SWT.HORIZONTAL);
    Composite composite = createInternalComposite(parent_p);

    // **Presentation of trace elements(source and target)
    Composite capellaTreeComposite = new Composite(composite, SWT.NONE);
    capellaTreeComposite.setLayout(horizontalFillLayout);
    capellaTreeComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

    _capellaModelViewer = new CapellaModelTreeViewer(_system);
    _capellaModelViewer._currentNamedElement = _parentWizard.getWorkingNamedElement();
    _capellaModelViewer.getControl(capellaTreeComposite);

    // **Add listener
    _capellaModelViewer._treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

      public void selectionChanged(SelectionChangedEvent event_p) {
        ISelection selection = event_p.getSelection();
        if (selection instanceof IStructuredSelection && ((IStructuredSelection) selection).size() == 1) {
          Object elem = ((IStructuredSelection) selection).getFirstElement();
          if (elem instanceof NamedElement) {
            if (checkElement((NamedElement) elem)) {
              setPageComplete(true);
              _parentWizard.setCurrentNamedElement((NamedElement) elem);
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

  public boolean checkElement(NamedElement element_p) {
    NamedElement currentElt = _parentWizard.getWorkingNamedElement();
    if (currentElt.equals(element_p)) {
      return false;
    }
    for (AbstractTrace trace : currentElt.getIncomingTraces()) {
      if (trace.getSourceElement() == element_p)
        return false;
      if (trace.getTargetElement() == element_p)
        return false;
    }
    for (AbstractTrace trace : currentElt.getOutgoingTraces()) {
      if (trace.getSourceElement() == element_p)
        return false;
      if (trace.getTargetElement() == element_p)
        return false;
    }
    return true;
  }

}
