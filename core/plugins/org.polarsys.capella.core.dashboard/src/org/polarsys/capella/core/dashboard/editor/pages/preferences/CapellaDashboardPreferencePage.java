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
package org.polarsys.capella.core.dashboard.editor.pages.preferences;

import org.eclipse.core.resources.ProjectScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IWorkbenchPreferencePage;

import org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage;
import org.polarsys.capella.core.commands.preferences.service.PreferenceField;
import org.polarsys.capella.core.commands.preferences.service.UserProfileModeEnum;
import org.polarsys.capella.core.dashboard.editor.pages.Messages;
import org.polarsys.capella.core.preferences.Activator;

/**
 * Main preferences page for Capella dashboard.
 */
public class CapellaDashboardPreferencePage extends AbstractDefaultPreferencePage implements IWorkbenchPreferencePage {
  /*
   * 
   */

  private IPropertyChangeListener _preferenceListener;

  /*
   * 
   */
  public static final String PROPERTY_PAGE_ID = "org.polarsys.capella.core.dashboard.capellaDashboard.page"; //$NON-NLS-1$

  /**
   * 
   */
  public CapellaDashboardPreferencePage() {
    super(PROPERTY_PAGE_ID);
  }

  /**
   * @see org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage#getPageDescription()
   */
  @Override
  protected String getPageDescription() {
    return Messages.CapellaDashboardPreferencePage_Description;
  }

  /**
   * @see org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage#getPageTitle()
   */
  @Override
  protected String getPageTitle() {
    return Messages.CapellaDashboardPreferencePage_Title;
  }

  /**
   * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
   */
  @Override
  protected void createFieldEditors() {

    addField(new PreferenceField(ITransversePreferences.PREFERENCE_FUNCTIONAL_ANALYSIS_ACTIVITIES_SECTIONS,
        Messages.CapellaDashboardPreferencePage_FunctionalAnalysisActivitiesSections_Title, getFieldEditorParent()), UserProfileModeEnum.Expert,
        getFieldEditorParent(), ProjectScope.class);

    addField(new PreferenceField(ITransversePreferences.PREFERENCE_INTERFACE_ENGINEERING_ACTIVITIES_SECTIONS,
        Messages.CapellaDashboardPreferencePage_InterfaceEngineeringActivitiesSections_Title, getFieldEditorParent()), UserProfileModeEnum.Expert,
        getFieldEditorParent(), ProjectScope.class);
    _preferenceListener = new IPropertyChangeListener() {
      /**
       * {@inheritDoc}
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        String property = event_p.getProperty();
        if (ITransversePreferences.PREFERENCE_FUNCTIONAL_ANALYSIS_ACTIVITIES_SECTIONS.equals(property)) {
          handleFunctionalAnalysisPropertyChangeEvent(event_p);
        } else if (ITransversePreferences.PREFERENCE_INTERFACE_ENGINEERING_ACTIVITIES_SECTIONS.equals(property)) {
          handleInterfaceEngineeringPropertyChangeEvent(event_p);
        }
      }
    };
    doGetPreferenceStore().addPropertyChangeListener(_preferenceListener);
  }

  /**
   * Handle Interface Engineering property change event.
   * @param event_p
   */
  protected void handleInterfaceEngineeringPropertyChangeEvent(PropertyChangeEvent event_p) {
    boolean value =
        (event_p.getNewValue() instanceof String) ? Boolean.valueOf((String) event_p.getNewValue()).booleanValue() : ((Boolean) event_p.getNewValue())
            .booleanValue();

    // Get the preference store.
    IPreferenceStore preferenceStore = doGetPreferenceStore();
    // Update accordingly SA overview.
    preferenceStore.setValue(ISystemAnalysisPreferences.PREFERENCE_SECTION_DEFINE_INTERFACES_DESCRIBE_SCENARIOS, value);
    // Update accordingly LA overview.
    preferenceStore.setValue(ILogicalArchitecturePreferences.PREFERENCE_SECTION_DELEGATE_SYSTEM_INTERFACES_AND_CREATE_LOGICAL_INTERFACES, value);
    preferenceStore.setValue(ILogicalArchitecturePreferences.PREFERENCE_SECTION_ENRICH_LOGICAL_SCENARIO, value);
    // Update accordingly PA overview.
    preferenceStore.setValue(IPhysicalArchitecturePreferences.PREFERENCE_SECTION_DELEGATE_LOGICAL_INTERFACES_AND_CREATE_PHYSICAL_INTERFACES, value);
    preferenceStore.setValue(IPhysicalArchitecturePreferences.PREFERENCE_SECTION_ENRICH_PHYSICAL_SCENARIO, value);
  }

  /**
   * Handle Functional Analysis property change event.
   * @param event_p
   */
  protected void handleFunctionalAnalysisPropertyChangeEvent(PropertyChangeEvent event_p) {
    boolean value =
        (event_p.getNewValue() instanceof String) ? Boolean.valueOf((String) event_p.getNewValue()).booleanValue() : ((Boolean) event_p.getNewValue())
            .booleanValue();
    // Get the preference store.
    IPreferenceStore preferenceStore = doGetPreferenceStore();
    // Update accordingly SA overview.
    preferenceStore.setValue(ISystemAnalysisPreferences.PREFERENCE_SECTION_TRANSITION_FROM_OA, value);
    preferenceStore.setValue(ISystemAnalysisPreferences.PREFERENCE_SECTION_REFINE_SYSTEM_FUNCTIONS_DESCRIBE_FE, value);
    preferenceStore.setValue(ISystemAnalysisPreferences.PREFERENCE_SECTION_ALLOCATE_SYSTEM_FUNCTION_TO_SYSTEM_AND_ACTORS, value);
    // Update accordingly LA overview.
    preferenceStore.setValue(ILogicalArchitecturePreferences.PREFERENCE_SECTION_TRANSITION_FROM_SF, value);
    preferenceStore.setValue(ILogicalArchitecturePreferences.PREFERENCE_SECTION_REFINE_LOGICAL_FUNCTIONS_DESCRIBE_FE, value);
    preferenceStore.setValue(ILogicalArchitecturePreferences.PREFERENCE_SECTION_ALLOCATE_LOGICAL_FUNCTION_TO_LOGICAL_COMPONENTS, value);
    // Update accordingly PA overview.
    preferenceStore.setValue(IPhysicalArchitecturePreferences.PREFERENCE_SECTION_TRANSITION_FROM_LF, value);
    preferenceStore.setValue(IPhysicalArchitecturePreferences.PREFERENCE_SECTION_REFINE_PHYSICAL_FUNCTIONS_DESCRIBE_FE, value);
    preferenceStore.setValue(IPhysicalArchitecturePreferences.PREFERENCE_SECTION_ALLOCATE_PHYSICAL_FUNCTION_TO_PHYSICAL_COMPONENTS, value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void dispose() {
    doGetPreferenceStore().removePropertyChangeListener(_preferenceListener);
    super.dispose();
  }

  /**
   * @see org.eclipse.jface.preference.PreferencePage#doGetPreferenceStore()
   */
  @Override
  protected IPreferenceStore doGetPreferenceStore() {
    return Activator.getDefault().getPreferenceStore();
  }
}
