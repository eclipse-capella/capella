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
package org.polarsys.capella.core.ui.properties.wizards;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.PropertySheet;

import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;

/**
 * Open a property wizard for a ModelElement inside a command.
 * 
 */
public class OpenCustomWizardCommand extends AbstractReadWriteCommand {

  public static final String PROPERTIES_SHEET_VIEW_ID = "org.eclipse.ui.views.PropertySheet"; //$NON-NLS-1$
  protected EObject object;
  private boolean canceled;

  public OpenCustomWizardCommand(EObject object_p) {
    this.object = object_p;
  }

  public void run() {
    canceled = false;
    if (createCustomWizardHandler().openWizard(object) == false) {
      canceled = true;
      throw new OperationCanceledException(); // rollback
    }
    refreshPropertiesView(object);
  }

  @Override
  public String getName() {
    return Messages.CustomWizardHandler_Command_Title;
  }

  @Override
  public Collection<?> getAffectedObjects() {
    return Collections.singletonList(object);
  }

  @Override
  public void commandRolledBack() {
    canceled = true;
  }

  public boolean isCanceled() {
    return canceled;
  }

  protected ICustomWizardHandler createCustomWizardHandler() {
    return new CustomWizardHandler();
  }

  // FIXME why do we have to call this explicitly?
  private final void refreshPropertiesView(EObject object_p) {
    IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
    PropertySheet propertyView = (PropertySheet) activePage.findView(PROPERTIES_SHEET_VIEW_ID);
    if (null != propertyView) {
      propertyView.selectionChanged(activePage.getActivePart(), new StructuredSelection(object_p));
    }
  }

}
