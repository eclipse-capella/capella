/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.system.ui.preferences;

import org.eclipse.ui.IWorkbenchPreferencePage;
import org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage;
import org.polarsys.capella.core.commands.preferences.service.PreferenceField;
import org.polarsys.capella.core.commands.preferences.service.UserProfileModeEnum;
import org.polarsys.capella.core.transition.system.preferences.PreferenceConstants;

/**
 * This class represents a preference page that is contributed to the Preferences dialog. By subclassing
 * <samp>FieldEditorPreferencePage</samp>, we can use the field support built into JFace that allows us to create a page
 * that is small and knows how to save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They are stored in the preference store that belongs to the main
 * plug-in class. That way, preferences can be accessed directly via the preference store.
 */

public class TransitionLogPreferencePage extends AbstractDefaultPreferencePage implements IWorkbenchPreferencePage {

  /*
   * 
   */
  public static final String PROPERTY_PAGE_ID = "org.polarsys.capella.core.transition.system.ui.preferences.TransitionLogPreferencePage"; //$NON-NLS-1$

  /*
   * 
   */
  private static final String DESCRIPTION = "Type of element to display in Transition Log :"; //$NON-NLS-1$

  public TransitionLogPreferencePage() {
    super(PROPERTY_PAGE_ID);
    setPreferenceStore(org.polarsys.capella.core.preferences.Activator.getDefault().getPreferenceStore());
    setDescription(DESCRIPTION);
  }

  @Override
  public void createFieldEditors() {
    addField(new PreferenceField(PreferenceConstants.P_F, PreferenceConstants.P_F_TEXT, getFieldEditorParent()),
        UserProfileModeEnum.User, getFieldEditorParent());
    addField(new PreferenceField(PreferenceConstants.P_BC, PreferenceConstants.P_BC_TEXT, getFieldEditorParent()),
        UserProfileModeEnum.User, getFieldEditorParent());
    addField(new PreferenceField(PreferenceConstants.P_IC, PreferenceConstants.P_IC_TEXT, getFieldEditorParent()),
        UserProfileModeEnum.User, getFieldEditorParent());
    addField(
        new PreferenceField(PreferenceConstants.P_Actor, PreferenceConstants.P_Actor_TEXT, getFieldEditorParent()),
        UserProfileModeEnum.User, getFieldEditorParent());
    addField(new PreferenceField(PreferenceConstants.P_CAPABILITY, PreferenceConstants.P_CAPABILITY_TEXT,
        getFieldEditorParent()), UserProfileModeEnum.User, getFieldEditorParent());
    addField(new PreferenceField(PreferenceConstants.P_Part, PreferenceConstants.P_Part_TEXT, getFieldEditorParent()),
        UserProfileModeEnum.User, getFieldEditorParent());
    addField(new PreferenceField(PreferenceConstants.P_FE, PreferenceConstants.P_FE_TEXT, getFieldEditorParent()),
        UserProfileModeEnum.User, getFieldEditorParent());
    addField(new PreferenceField(PreferenceConstants.P_CE, PreferenceConstants.P_CE_TEXT, getFieldEditorParent()),
        UserProfileModeEnum.User, getFieldEditorParent());
    addField(new PreferenceField(PreferenceConstants.P_PL, PreferenceConstants.P_PL_TEXT, getFieldEditorParent()),
        UserProfileModeEnum.User, getFieldEditorParent());
    addField(new PreferenceField(PreferenceConstants.P_DL, PreferenceConstants.P_DL_TEXT, getFieldEditorParent()),
        UserProfileModeEnum.User, getFieldEditorParent());
    addField(new PreferenceField(PreferenceConstants.P_FA, PreferenceConstants.P_FA_TEXT, getFieldEditorParent()),
        UserProfileModeEnum.User, getFieldEditorParent());
    addField(new PreferenceField(PreferenceConstants.P_CA, PreferenceConstants.P_CA_TEXT, getFieldEditorParent()),
        UserProfileModeEnum.User, getFieldEditorParent());
    addField(new PreferenceField(PreferenceConstants.P_FEA, PreferenceConstants.P_FEA_TEXT, getFieldEditorParent()),
        UserProfileModeEnum.User, getFieldEditorParent());
    addField(
        new PreferenceField(PreferenceConstants.P_FPort, PreferenceConstants.P_FPort_TEXT, getFieldEditorParent()),
        UserProfileModeEnum.User, getFieldEditorParent());
    addField(
        new PreferenceField(PreferenceConstants.P_CPort, PreferenceConstants.P_CPort_TEXT, getFieldEditorParent()),
        UserProfileModeEnum.User, getFieldEditorParent());
    addField(
        new PreferenceField(PreferenceConstants.P_PPort, PreferenceConstants.P_PPort_TEXT, getFieldEditorParent()),
        UserProfileModeEnum.User, getFieldEditorParent());
    addField(new PreferenceField(PreferenceConstants.P_EI, PreferenceConstants.P_EI_TEXT, getFieldEditorParent()),
        UserProfileModeEnum.User, getFieldEditorParent());
    addField(
        new PreferenceField(PreferenceConstants.P_Iface, PreferenceConstants.P_Iface_TEXT, getFieldEditorParent()),
        UserProfileModeEnum.User, getFieldEditorParent());
    addField(new PreferenceField(PreferenceConstants.P_Data, PreferenceConstants.P_Data_TEXT, getFieldEditorParent()),
        UserProfileModeEnum.User, getFieldEditorParent());
    addField(new PreferenceField(PreferenceConstants.P_MS, PreferenceConstants.P_MS_TEXT, getFieldEditorParent()),
        UserProfileModeEnum.User, getFieldEditorParent());
    addField(new PreferenceField(PreferenceConstants.P_PropertyValues, PreferenceConstants.P_PropertyValues_TEXT,
        getFieldEditorParent()), UserProfileModeEnum.User, getFieldEditorParent());
    addField(new PreferenceField(PreferenceConstants.P_Generalization, PreferenceConstants.P_Generalization_TEXT,
        getFieldEditorParent()), UserProfileModeEnum.User, getFieldEditorParent());
    addField(
        new PreferenceField(PreferenceConstants.P_Other, PreferenceConstants.P_Other_TEXT, getFieldEditorParent()),
        UserProfileModeEnum.User, getFieldEditorParent());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getPageTitle() {
    return PROPERTY_PAGE_ID;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getPageDescription() {
    return DESCRIPTION;
  }

}
