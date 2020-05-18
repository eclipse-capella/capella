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
package org.polarsys.capella.core.commands.preferences.ui.sirius;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.ui.tools.internal.preference.SiriusPreferencePage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.polarsys.capella.core.commands.preferences.preferences.ConfigurabilityPreferences;
import org.polarsys.capella.core.commands.preferences.service.IAbstractDefaultPreferencePage;
import org.polarsys.capella.core.commands.preferences.service.UserProfileModeEnum;
import org.polarsys.capella.core.commands.preferences.util.XmlPreferencesConfig;
import org.polarsys.capella.core.preferences.Activator;

public class ViewPointPropertyPreferencePage extends SiriusPreferencePage implements IAbstractDefaultPreferencePage, IWorkbenchPropertyPage {

  public static final String PROPERTY_PAGE_ID = "org.eclipse.sirius.ui.tools.internal.preference.viewpoint.page"; //$NON-NLS-1$

  private UserProfileModeEnum userProfileModeEnum_p = UserProfileModeEnum.Expert;

  public ViewPointPropertyPreferencePage() {
    setPreferenceStore(Activator.getDefault().getPreferenceStore());

  }

  protected String getPageId() {
    return PROPERTY_PAGE_ID;
  }

  @Override
  protected Control createContents(Composite parent_p) {
    Control page = super.createContents(parent_p);
    setPageEnablement(parent_p);
    return page;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IAdaptable getElement() {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setElement(IAdaptable element_p) {

  }

  @Override
  protected void createFieldEditors() {
    super.createFieldEditors();

  }

  @Override
  protected IPreferenceStore doGetPreferenceStore() {
    return Activator.getDefault().getPreferenceStore();

  };

  @Override
  protected void addField(org.eclipse.jface.preference.FieldEditor editor_p) {

    super.addField(editor_p);

  }

  protected void setPageEnablement(Composite parent_p) {
    if (UserProfileModeEnum.Expert.equals(userProfileModeEnum_p) && (parent_p != null) && !parent_p.isDisposed()) {

      Control[] childrens = parent_p.getChildren();
      for (Control children : childrens) {
        children.setEnabled(ConfigurabilityPreferences.isInstanceScopePreferenceItemEnabled(XmlPreferencesConfig.USER_PROFILE_MODE_ID));
      }
    }

  }

}
