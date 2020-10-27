/*******************************************************************************
 *  Copyright (c) 2007, 2009 LCELB
 *  
 *  This program and the accompanying materials are made available under the
 *  terms of the Eclipse Public License 2.0 which is available at
 *  http://www.eclipse.org/legal/epl-2.0
 *  
 *  SPDX-License-Identifier: EPL-2.0
 * 
 *  Contributors:
 *      LCELB - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.commands.preferences.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage;

/**
 */
public class AbstractCapellaCommandsPreferencePage extends AbstractDefaultPreferencePage {
  /**
   * Constructor.
   */
  public AbstractCapellaCommandsPreferencePage() {
    super(GRID);
  }

  /**
   * Create a new group in the page.
   * @param label_p
   * @param tooltip_p
   * @param parent_p
   * @return
   */
  @Override
  protected Group createGroup(String label_p, String tooltip_p, Composite parent_p) {
    Group group = new Group(parent_p, SWT.NONE);
    group.setText(label_p);
    group.setToolTipText(tooltip_p);
    GridData gridData = new GridData();
    gridData.horizontalAlignment = GridData.FILL;
    gridData.grabExcessHorizontalSpace = true;
    gridData.verticalAlignment = GridData.CENTER;
    gridData.grabExcessVerticalSpace = false;
    group.setLayoutData(gridData);
    return group;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void createFieldEditors() {

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

}
