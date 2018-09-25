/*******************************************************************************
 * Copyright (c) 2017, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.vp.ms.ui.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.polarsys.capella.vp.ms.access_Type;
/**
 * Preference page for Capella Modes and States Addon
 */
public class MsPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

  public MsPreferencePage() {
    super(FieldEditorPreferencePage.GRID);
  }

  @Override
  protected void createFieldEditors() {

    FieldEditor editor = new RadioGroupFieldEditor(MsPreferenceConstants.PREF_DEFAULT_CONFIGURATION_ACCESS,
        Messages.InitializeConfigurationAccessDialog_label, 1, InitializeConfigurationAccessDialog.labelsAndValues,
        getFieldEditorParent(), false);
    editor.fillIntoGrid(getFieldEditorParent(), 1);
    addField(editor);
  
    editor = new BooleanFieldEditor(MsPreferenceConstants.PREF_DEFAULT_CONSISTEN_INCLUDE_REQUIRED, "Consistent element inclusion", getFieldEditorParent());
    editor.fillIntoGrid(getFieldEditorParent(), 1);
    addField(editor);

    editor = new BooleanFieldEditor(MsPreferenceConstants.PREF_DEFAULT_MARK_CONFLICTS, "Mark elements with conflict", getFieldEditorParent());
    editor.fillIntoGrid(getFieldEditorParent(), 1);
    addField(editor);
  }

  @Override
  protected IPreferenceStore doGetPreferenceStore() {
    return Activator.getDefault().getPreferenceStore();
  }

  public static class Initializer extends AbstractPreferenceInitializer {
    @Override
    public void initializeDefaultPreferences() {
      DefaultScope.INSTANCE.getNode(Activator.PLUGIN_ID).put(MsPreferenceConstants.PREF_DEFAULT_CONFIGURATION_ACCESS,
          access_Type.FULL.getLiteral());
      DefaultScope.INSTANCE.getNode(Activator.PLUGIN_ID).putBoolean(MsPreferenceConstants.PREF_DEFAULT_CONSISTEN_INCLUDE_REQUIRED, false);
      DefaultScope.INSTANCE.getNode(Activator.PLUGIN_ID).putBoolean(MsPreferenceConstants.PREF_DEFAULT_MARK_CONFLICTS, false);
    }
  }

  @Override
  public void init(IWorkbench workbench) {
  }

}