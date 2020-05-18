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
package org.polarsys.capella.core.commands.preferences.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage;

/**
 * Preference page for user to select which constraint categories are disabled. Mandatory items are shown in disabled text colour, and inoperable (error) items
 * are flagged with delta-bang warning icons.
 */
public class ItemsPreferencePage extends AbstractDefaultPreferencePage implements IWorkbenchPreferencePage {
  /*
   * 
   */

  public static final String PROPERTY_PAGE_ID = "org.polarsys.capella.core.commands.preferences.ui.constraintsPreferencePage"; //$NON-NLS-1$

  /*
   * 
   */
  private CommandSelectionContainer itemsComposite;

  @Override
  protected Control createContents(Composite parent) {
    Composite result = new Composite(parent, SWT.NONE);

    FillLayout layout = new FillLayout();
    result.setLayout(layout);

    itemsComposite = new CommandSelectionContainer();

    itemsComposite.createComposite(result);

    applyDialogFont(result);

    return result;
  }

  @Override
  public void init(IWorkbench workbench) {
  }

  @Override
  public boolean performOk() {
    return itemsComposite.performOk();
  }

  @Override
  protected void performDefaults() {
    itemsComposite.performDefaults();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getPageTitle() {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getPageDescription() {
    return null;
  }

  @Override
  protected String getPageId() {
    return PROPERTY_PAGE_ID;
  }
}
