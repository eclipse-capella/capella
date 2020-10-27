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

import org.eclipse.core.resources.ProjectScope;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.swt.widgets.Composite;
import org.polarsys.capella.common.ui.toolkit.fields.SpacerFieldEditor;
import org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage;
import org.polarsys.capella.core.commands.preferences.service.UserProfileModeEnum;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

/**
 * Main Capella preferences page.
 */
public class CapellaPreferencePage extends AbstractDefaultPreferencePage {

  /*
   * 
   */
  public static final String PROPERTY_PAGE_ID = "org.polarsys.capella.core.platform.sirius.ui.actions.Capella.page"; //$NON-NLS-1$

  /**
   * 
   */
  public CapellaPreferencePage() {
    super(PROPERTY_PAGE_ID);
  }

  /**
   * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
   */
  @Override
  protected void createFieldEditors() {
    Composite fieldEditorParent = getFieldEditorParent();
    // Detection mechanism preference.
    addField(new BooleanFieldEditor(ICapellaPreferences.PREFERENCE_DETECTION_VERSION, Messages.CapellaPreferencePage_DetectionVersion_Title,
        fieldEditorParent), UserProfileModeEnum.Expert, fieldEditorParent);

    // Spacer.
    addField(new SpacerFieldEditor(fieldEditorParent));

    // Capella Fragment file extension preferences.
    RadioGroupFieldEditor group =
        new RadioGroupFieldEditor(ICapellaPreferences.PREFERENCE_CAPELLA_AIRD_FRAGMENT_FILE_EXTENSION,
            Messages.CapellaPreferencePage_AirdFragmentFileExtension_Title, 1,
            new String[][] { { "*." + CapellaResourceHelper.AIRD_FRAGMENT_FILE_EXTENSION, CapellaResourceHelper.AIRD_FRAGMENT_FILE_EXTENSION }, //$NON-NLS-1$
                            { "*." + CapellaResourceHelper.AIRD_FILE_EXTENSION, CapellaResourceHelper.AIRD_FILE_EXTENSION } }, fieldEditorParent, true); //$NON-NLS-1$
    addField(group, UserProfileModeEnum.Expert, fieldEditorParent, ProjectScope.class);
  }

  /**
   * @see org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage#getPageDescription()
   */
  @Override
  protected String getPageDescription() {
    return Messages.CapellaPreferencePage_Title;
  }

  /**
   * @see org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage#getPageTitle()
   */
  @Override
  protected String getPageTitle() {
    return Messages.CapellaPreferencePage_Description;
  }
}
