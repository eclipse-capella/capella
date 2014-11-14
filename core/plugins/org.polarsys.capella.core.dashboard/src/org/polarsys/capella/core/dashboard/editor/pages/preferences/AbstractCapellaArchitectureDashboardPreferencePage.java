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

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Group;

import org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage;
import org.polarsys.capella.core.dashboard.editor.pages.Messages;
import org.polarsys.capella.core.preferences.Activator;

/**
 * Base class to implement Capella architecture preference page.
 */
public abstract class AbstractCapellaArchitectureDashboardPreferencePage extends AbstractDefaultPreferencePage {
  /**
   * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
   */
  @Override
  protected void createFieldEditors() {
    Group sectionsGroup =
        createGroup(Messages.AbstractCapellaArchitectureDashboardPreferencePage_SectionsGroup_Title,
            Messages.AbstractCapellaArchitectureDashboardPreferencePage_SectionsGroup_Tooltip, getFieldEditorParent());
    createFieldEditors(sectionsGroup);
  }

  /**
   * Creates the page's field editors.
   * @param sectionsGroup_p
   */
  protected abstract void createFieldEditors(Group sectionsGroup_p);

  /**
   * @see org.eclipse.jface.preference.PreferencePage#doGetPreferenceStore()
   */
  @Override
  protected IPreferenceStore doGetPreferenceStore() {
    return Activator.getDefault().getPreferenceStore();
  }
}
