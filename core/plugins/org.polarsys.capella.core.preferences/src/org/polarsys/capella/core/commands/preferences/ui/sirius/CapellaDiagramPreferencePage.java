/*******************************************************************************
 * Copyright (c) 2006, 2022 THALES GLOBAL SERVICES.
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

import org.eclipse.core.resources.ProjectScope;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage;
import org.polarsys.capella.core.commands.preferences.service.PreferenceField;
import org.polarsys.capella.core.commands.preferences.service.UserProfileModeEnum;

/**
 * This preference page is used to enable navigation towards related diagrams on double click
 * 
 * @author etraisnel
 */
public class CapellaDiagramPreferencePage extends AbstractDefaultPreferencePage {


  public CapellaDiagramPreferencePage() {
    super(Messages.PropertyPageId);
  }


  @Override
  protected String getPageTitle() {
    return Messages.PropertyPageTitle;
  }

  @Override
  protected String getPageDescription() {
    return null;
  }

  @Override
  protected void createFieldEditors() {
    final Composite fieldEditorParent = getFieldEditorParent();

    Group treeContentGroup = createGroup(Messages.GroupLabel, Messages.GroupLabel, fieldEditorParent);

    PreferenceField fieldEditorOP1 = new PreferenceField(Messages.NamePrefDisplayNavigateOnDoubleClick,
        Messages.LabelPrefDisplayNavigateOnDoubleClick, treeContentGroup);

    addField(fieldEditorOP1, UserProfileModeEnum.User, treeContentGroup, ProjectScope.class);

    fieldEditorOP1.getChangeControl(treeContentGroup)
    .setToolTipText(Messages.TooltipPrefDisplayNavigateOnDoubleClick);
  }

}
