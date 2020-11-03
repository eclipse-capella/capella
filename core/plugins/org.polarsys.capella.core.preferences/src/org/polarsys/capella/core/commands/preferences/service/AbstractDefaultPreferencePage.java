/*******************************************************************************
 *  Copyright (c) 2007, 2020 LCELB
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
package org.polarsys.capella.core.commands.preferences.service;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.eclipse.jface.preference.IPersistentPreferenceStore;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.preferences.Activator;

/**
 * Abstract default preference page for account manager UI.
 */
public abstract class AbstractDefaultPreferencePage extends ConfigurableFieldEditorPreferencePage implements IAbstractDefaultPreferencePage {

  /*
   * 
   */
  private static final Logger __logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.UI);

  /**
   * Constructor.
   */
  public AbstractDefaultPreferencePage() {
    super(GRID);
    setTitle(getPageTitle());
    setDescription(getPageDescription());
  }

  /**
   * Constructor.
   */
  public AbstractDefaultPreferencePage(int style) {
    super(style);
    setTitle(getPageTitle());
    setDescription(getPageDescription());
  }

  /**
   * Constructor.
   */
  public AbstractDefaultPreferencePage(String pageId) {
    super(pageId);
    setTitle(getPageTitle());
    setDescription(getPageDescription());
  }
  
  /**
   * Get the title of this page
   * @return
   */
  protected abstract String getPageTitle();

  /**
   * Get the description of this page
   * @return
   */
  protected abstract String getPageDescription();
  
  public boolean performCancel() {
	  return true;
  }

  @Override
  protected IPreferenceStore doGetPreferenceStore() {
    return Activator.getDefault().getPreferenceStore();
  }
  
  /**
   * Create a new group in the page.
   * @param label_p
   * @param tooltip_p
   * @param parent_p
   * @return
   */
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

}
