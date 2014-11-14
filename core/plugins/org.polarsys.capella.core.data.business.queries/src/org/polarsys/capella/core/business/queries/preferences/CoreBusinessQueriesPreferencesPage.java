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
package org.polarsys.capella.core.business.queries.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;

import org.polarsys.capella.core.business.queries.BusinessQueriesPlugin;
import org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage;

/**
 * This is the core business queries preferences page.
 * @see AbstractDefaultPreferencePage
 */
public class CoreBusinessQueriesPreferencesPage extends AbstractDefaultPreferencePage {
  /**
   * @see org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage#getPageDescription()
   */
  @Override
  protected String getPageDescription() {
    return Messages.CoreBusinessQueriesPreferencesPage_PreferencesRegardingBusinessQueries;
  }

  /**
   * @see org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage#getPageTitle()
   */
  @Override
  protected String getPageTitle() {
    return Messages.CoreBusinessQueriesPreferencesPage_CoreBusinessQueriesPreferencesPage;
  }

  /**
   * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
   */
  @Override
  protected void createFieldEditors() {
    addField(new BooleanFieldEditor(ICoreBusinessQueriesPreferences.PREF_SKIP_OWNED_MIN_MAX_NULL_DEFAULT_VALUES, Messages.CoreBusinessQueriesPreferencesPage_SkipAlreadyOwnedMinMaxDefaulNullValues, getFieldEditorParent()));
  }

  /**
   * @see org.eclipse.jface.preference.PreferencePage#doGetPreferenceStore()
   */
  @Override
  protected IPreferenceStore doGetPreferenceStore() {
    return BusinessQueriesPlugin.getDefault().getPreferenceStore();
  }
}
