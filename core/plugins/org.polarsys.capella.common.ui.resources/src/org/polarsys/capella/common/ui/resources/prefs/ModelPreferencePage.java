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
package org.polarsys.capella.common.ui.resources.prefs;

import org.eclipse.core.resources.ProjectScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.polarsys.capella.common.ui.toolkit.fields.SpacerFieldEditor;
import org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage;
import org.polarsys.capella.core.commands.preferences.service.PreferenceField;
import org.polarsys.capella.core.commands.preferences.service.UserProfileModeEnum;
import org.polarsys.capella.core.model.preferences.IDataPreferences;
import org.polarsys.capella.core.model.preferences.IDeploymentPreferences;
import org.polarsys.capella.core.model.preferences.IInheritancePreferences;
import org.polarsys.capella.core.model.preferences.IInterModelIntegrityPreferences;
import org.polarsys.capella.core.model.preferences.IReuseComponentsPreferences;
import org.polarsys.capella.core.model.preferences.ISynchronizationPreferences;
import org.polarsys.capella.core.preferences.Activator;

/**
 */
public class ModelPreferencePage extends AbstractDefaultPreferencePage {

  public static final String PROPERTY_PAGE_ID = "org.polarsys.capella.common.ui.resources.prefs.property.modelPage"; //$NON-NLS-1$

  /**
   * 
   */
  public ModelPreferencePage() {
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
    return Messages.ModelPreferencePage_Description;
  }

  /**
   * @see org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage#getPageTitle()
   */
  @Override
  protected String getPageTitle() {
    return Messages.ModelPreferencePage_Title;
  }

  /**
   * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
   */
  @Override
  protected void createFieldEditors() {
    Composite fieldEditorParent = getFieldEditorParent();

    Group group0 = createGroup(Messages.ReuseOfComponentsPreferencePage_Group_Title,
        Messages.ReuseOfComponentsPreferencePage_Group_Title, fieldEditorParent);
    addField(new PreferenceField(IReuseComponentsPreferences.PREFS_ALLOW_REUSE_COMPONENTS,
        Messages.ReuseOfComponentsPreferencePage_Allowed_Title, group0), UserProfileModeEnum.Expert, group0,
        ProjectScope.class);

    addField(new SpacerFieldEditor(fieldEditorParent));
    Group group1 = createGroup(Messages.InheritancePreferencePage_Group_Title,
        Messages.InheritancePreferencePage_Group_Title, fieldEditorParent);
    addField(new PreferenceField(IInheritancePreferences.PREFS_ALLOW_MULTIPLE_INHERITANCE,
        Messages.MultipleInheritancePreferencePage_Allowed_Title, group1), UserProfileModeEnum.Expert, group1,
        ProjectScope.class);
    addField(new PreferenceField(IInheritancePreferences.PREFS_ALLOW_COMPONENT_INHERITANCE,
        Messages.ComponentInheritancePreferencePage_Allowed_Title, group1), UserProfileModeEnum.Expert, group1,
        ProjectScope.class);

    addField(new SpacerFieldEditor(fieldEditorParent));
    Group group2 = createGroup(Messages.DeploymentPreferencePage_Group_Title,
        Messages.DeploymentPreferencePage_Group_Title, fieldEditorParent);
    addField(new PreferenceField(IDeploymentPreferences.PREFS_ALLOW_MULTIPLE_DEPLOYMENT,
        Messages.DeploymentPreferencePage_Allowed_Title, group2), UserProfileModeEnum.Expert, group2,
        ProjectScope.class);

    addField(new SpacerFieldEditor(fieldEditorParent));
    Group group3 = createGroup(Messages.DataPreferencePage_Group_Title, Messages.DataPreferencePage_Group_Title,
        fieldEditorParent);
    addField(new PreferenceField(IDataPreferences.PREFS_ALLOW_PRIMITIVE_SYNCHRONIZATION,
        Messages.DataPreferencePage_PrimitiveSynchroAllowed_Title, group3), UserProfileModeEnum.Expert, group3,
        ProjectScope.class);

    addField(new SpacerFieldEditor(fieldEditorParent));
    Group group4 = createGroup(Messages.SyncPreferencePage_Group_Title, Messages.SyncPreferencePage_Group_Title,
        fieldEditorParent);
    addField(new PreferenceField(ISynchronizationPreferences.PREFS_ALLOW_SYNC_COMPONENTPORT_TO_FUNCTIONPORT,
        Messages.SyncPreferencePage_SyncComponentPort2FunctionPortAllowed_Title, group4), UserProfileModeEnum.Expert,
        group4, ProjectScope.class);
    addField(new PreferenceField(
        ISynchronizationPreferences.PREFS_ALLOW_SYNC_PHYSICALPORT_TO_COMPONENTPORT_ON_PHYSICALLINK,
        Messages.SyncPreferencePage_SyncPhysicalPort2FunctionPortOnPhysicalLinkAllowed_Title, group4),
        UserProfileModeEnum.Expert, group4, ProjectScope.class);
    addField(new PreferenceField(
        ISynchronizationPreferences.PREFS_ALLOW_SYNC_PHYSICALPORT_TO_COMPONENTPORT_ON_PHYSICALPATH,
        Messages.SyncPreferencePage_SyncPhysicalPort2FunctionPortOnPhysicalPathAllowed_Title, group4),
        UserProfileModeEnum.Expert, group4, ProjectScope.class);

    addField(new SpacerFieldEditor(fieldEditorParent));
    Group group5 = createGroup(Messages.interModelIntegrity_Group_Title, Messages.interModelIntegrity_Group_Title,
        fieldEditorParent);
    addField(new PreferenceField(IInterModelIntegrityPreferences.PREFS_PREVENT_ON_THE_FLY_DEPENDENCY_VIOLATION,
        Messages.interModelIntegrity_PreventInterModelDependencyViolation_Title, group5), UserProfileModeEnum.Expert,
        group5, ProjectScope.class);
  }
}
