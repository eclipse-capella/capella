/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.flexibility.wizards.ui;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Composite;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;

/**
 * Dialog which allows to modify a list of property element
 */
public class PropertyWizard extends Wizard {

  protected IPropertyContext _context;
  protected IRendererContext _renderers;
  
  private boolean messageOnWarning = false;

  public boolean isMessageOnWarning() {
    return messageOnWarning;
  }

  public void setMessageOnWarning(boolean messageOnWarning) {
    this.messageOnWarning = messageOnWarning;
  }

  /**
   * @return
   */
  protected IRendererContext getRendererContext() {
    return _renderers;
  }

  /**
   * @return
   */
  protected IPropertyContext getContext() {
    return _context;
  }

  @Override
  public boolean needsProgressMonitor() {
    return super.needsProgressMonitor();
  }

  public PropertyWizard(IPropertyContext context, IRendererContext renderers) {
    super();
    this._context = context;
    this._renderers = renderers;
    setNeedsProgressMonitor(false);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createPageControls(Composite pageContainer) {
    super.createPageControls(pageContainer);
  }

  @Override
  public void addPages() {
    PropertyWizardPage page = new PropertyWizardPage("propertiesEditor", getContext(), getRendererContext()); //$NON-NLS-1$
    addPage(page);
  }

  @Override
  public boolean performFinish() {
    if (isValid()) {
      IPropertyContext context = getContext();
      if (context != null) {
        context.writeAll();
      }
      return true;
    }
    return false;
  }

  protected boolean isValid() {
    if (isMessageOnWarning()) {
      IWizardPage page = getContainer().getCurrentPage();
      if (page instanceof IMessageProvider) {
        String message = NLS.bind(Messages.PropertyWizard_Confirm, ((IMessageProvider) page).getMessage());
        int messageType = ((IMessageProvider) page).getMessageType();
        
        if (IMessageProvider.WARNING == messageType) {
          MessageDialog dialog = new MessageDialog(getShell(), getContainer().getCurrentPage().getTitle(), null, message,
              MessageDialog.WARNING,  new String[] { IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL }, 1);
          if (dialog.open() == org.eclipse.jface.window.Window.OK) {
            return true;
          }
          return false;
        }
      }
    }
    return true;
  }
}
