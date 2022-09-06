/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.eclipse.core.resources.ProjectScope;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.polarsys.capella.core.commands.preferences.preferences.CapellaDiagramPreferences;
import org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage;
import org.polarsys.capella.core.commands.preferences.service.PreferenceField;
import org.polarsys.capella.core.commands.preferences.service.UserProfileModeEnum;
import org.polarsys.capella.core.preferences.Activator;

/**
 * This preference page is used to enable navigation towards related diagrams on double click
 * 
 * @author etraisnel
 */
public class CapellaDiagramPreferencePage extends AbstractDefaultPreferencePage {

  private Label previewValueLabel;
  private StringFieldEditor timeZoneFormatFieldEditor;
  private StringFieldEditor dateFormatFieldEditor;
  private Group dateGroup;

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

        PreferenceField fieldEditorOP1 = new PreferenceField(CapellaDiagramPreferences.PREF_DISPLAY_NAVIGATE_ON_DOUBLE_CLICK, Messages.LabelPrefDisplayNavigateOnDoubleClick, treeContentGroup);

        addField(fieldEditorOP1, UserProfileModeEnum.User, treeContentGroup, ProjectScope.class);

        fieldEditorOP1.getChangeControl(treeContentGroup).setToolTipText(Messages.TooltipPrefDisplayNavigateOnDoubleClick);

        createDateGroup(fieldEditorParent);
    }
    private void createDateGroup(Composite fieldEditorParent) {
      dateGroup = createGroup(Messages.DateGroupLabel, Messages.DateGroupLabel, fieldEditorParent);

      dateFormatFieldEditor = new StringFieldEditor(CapellaDiagramPreferences.PREF_DATE_FORMAT,
          Messages.DateFormatLabel, dateGroup);
      dateFormatFieldEditor.getLabelControl(dateGroup).setToolTipText(Messages.DateFormatTooltip);
      addField(dateFormatFieldEditor, UserProfileModeEnum.Expert, dateGroup, null);
      dateGroup.setLayout(new GridLayout(3, false));
      addHelpIcon(dateGroup, Messages.DateFormatTooltip);
      dateFormatFieldEditor.getTextControl(dateGroup).addModifyListener(new ModifyListener() {
        @Override
        public void modifyText(ModifyEvent e) {
          previewValueLabel.setText(computePreview());
        }
      });

      timeZoneFormatFieldEditor = new StringFieldEditor(CapellaDiagramPreferences.PREF_DATE_TIMEZONE,
          Messages.DateTimeZoneLabel, dateGroup);
      timeZoneFormatFieldEditor.getLabelControl(dateGroup).setToolTipText(Messages.DateTimeZoneTooltip);
      addField(timeZoneFormatFieldEditor, UserProfileModeEnum.Expert, dateGroup, null);
      dateGroup.setLayout(new GridLayout(3, false));
      addHelpIcon(dateGroup, Messages.DateTimeZoneTooltip);

      timeZoneFormatFieldEditor.getTextControl(dateGroup).addModifyListener(new ModifyListener() {
        @Override
        public void modifyText(ModifyEvent e) {
          previewValueLabel.setText(computePreview());
        }
      });

      // create the preview group
      dateGroup.setLayout(new GridLayout(3, false));

      Label previewLabel = new Label(dateGroup, NONE);
      previewLabel.setText(Messages.DatePreviewLabel);
      previewValueLabel = new Label(dateGroup, NONE);
      previewValueLabel.setText(computePreview());
      GridData gd = new GridData();
      gd.horizontalSpan = 1;
      gd.grabExcessHorizontalSpace = true;
      gd.widthHint = 200;
      previewValueLabel.setLayoutData(gd);

    }

    private void addHelpIcon(Composite parent, String tooltip) {
      Label image = new Label(parent, SWT.NONE);
      image.setImage(Activator.getImageDescriptor("prefshelp.gif").createImage());
      image.setToolTipText(tooltip);
      GridData gd = new GridData();
      gd.horizontalSpan = 1;
      gd.grabExcessHorizontalSpace = true;
      image.setLayoutData(gd);
    }

    String computePreview() {
      Date date = new Date(System.currentTimeMillis());
      SimpleDateFormat sdf = new SimpleDateFormat(dateFormatFieldEditor.getTextControl(dateGroup).getText());
      String timeZoneForDateFormatting = timeZoneFormatFieldEditor.getTextControl(dateGroup).getText();
      if (CapellaDiagramPreferences.PREF_DATE_TIMEZONE_SYSTEM.equals(timeZoneForDateFormatting)) {
        timeZoneForDateFormatting = TimeZone.getDefault().getID();
      }
      sdf.setTimeZone(TimeZone.getTimeZone(timeZoneForDateFormatting));
      return sdf.format(date);
    }

}
