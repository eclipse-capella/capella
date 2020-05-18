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
package org.polarsys.capella.core.platform.sirius.ui.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;

import org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage;
import org.polarsys.capella.core.platform.sirius.ui.actions.CapellaActionsActivator;

/**
 * This class represents a preference page that is contributed to the Preferences dialog. By subclassing <samp>FieldEditorPreferencePage</samp>, we can use the
 * field support built into JFace that allows us to create a page that is small and knows how to save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They are stored in the preference store that belongs to the main plug-in class. That way, preferences can be
 * accessed directly via the preference store.
 */

public class CapellaValidationPreferencesPage extends AbstractDefaultPreferencePage {

  /*
   * 
   */
  public static final String PROPERTY_PAGE_ID = "org.polarsys.capella.core.platform.sirius.ui.actions.preferences.CapellaValidationPreferences"; //$NON-NLS-1$

  public CapellaValidationPreferencesPage() {
    super(CapellaActionsActivator.PLUGIN_ID);
    setDescription(Messages.ModelValidationPreferencePage_Description);
  }

  /**
   * Creates the field editors. Field editors are abstractions of the common GUI blocks needed to manipulate various types of preferences. Each field editor
   * knows how to save and restore itself.
   */
  @Override
  public void createFieldEditors() {
    addField(new BooleanFieldEditor(ICapellaValidationPreferences.P_CLEAN_PREVIOUS_VALIDATION_RESULTS,
        Messages.ModelValidationPreferencePage_DeletePreviousResults_Title, getFieldEditorParent()));
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
    return Messages.ModelValidationPreferencePage_Description;
  }

}
