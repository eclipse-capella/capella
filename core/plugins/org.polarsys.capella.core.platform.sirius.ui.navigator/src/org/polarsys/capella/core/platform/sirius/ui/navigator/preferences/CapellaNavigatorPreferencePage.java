/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.navigator.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Group;

import org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage;
import org.polarsys.capella.core.commands.preferences.service.UserProfileModeEnum;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.model.preferences.helpers.PreferenceTitleHelper;
import org.polarsys.capella.core.preferences.Activator;

/**
 * Preferences page for Capella Navigator.
 */
public class CapellaNavigatorPreferencePage extends AbstractDefaultPreferencePage {
  /*
   * 
   */
  public static final String PROPERTY_PAGE_ID = "org.polarsys.capella.core.platform.sirius.ui.navigator.CapellaNavigatorPreferencePage"; //$NON-NLS-1$

  /**
   * 
   */
  public CapellaNavigatorPreferencePage() {
    super(PROPERTY_PAGE_ID);
  }

  /**
   * @see org.eclipse.jface.preference.PreferencePage#doGetPreferenceStore()
   */
  @Override
  protected IPreferenceStore doGetPreferenceStore() {
    return Activator.getDefault().getPreferenceStore();
  }

  /**
   * @see org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage#getPageDescription()
   */
  @Override
  protected String getPageDescription() {
    return Messages.CapellaNavigatorPreferencePage_Description;
  }

  /**
   * @see org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage#getPageTitle()
   */
  @Override
  protected String getPageTitle() {
    return Messages.CapellaNavigatorPreferencePage_Title;
  }

  /**
   * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
   */
  @Override
  protected void createFieldEditors() {
    Group treeContentGroup =
        createGroup(Messages.CapellaNavigatorPreferencePage_ProjectExplorer_Group_Title, Messages.CapellaNavigatorPreferencePage_ProjectExplorer_Group_Message,
            getFieldEditorParent());
    addField(
        new BooleanFieldEditor(ICapellaNavigatorPreferences.PREFERENCE_SHOW_CAPELLA_PROJECT_CONCEPT,
            new PreferenceTitleHelper().getPreferenceTitle(CapellamodellerPackage.Literals.PROJECT), treeContentGroup), UserProfileModeEnum.Expert,
        treeContentGroup);

	addField(new BooleanFieldEditor(ICapellaNavigatorPreferences.PREFERENCE_PART_EXPLICIT_VIEW,
        Messages.CapellaNavigatorPreferencePage_ProjectExplorer_PartExplicitView, treeContentGroup));
  }
}
