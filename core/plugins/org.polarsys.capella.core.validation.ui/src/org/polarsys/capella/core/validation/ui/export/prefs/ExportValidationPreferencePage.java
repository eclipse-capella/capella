/*******************************************************************************
 * Copyright (c) 2006, 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.validation.ui.export.prefs;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.polarsys.capella.common.helpers.export.DataExporter;
import org.polarsys.capella.common.helpers.export.IExporterProvider;
import org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage;
import org.polarsys.capella.core.validation.CapellaValidationActivator;
import org.polarsys.capella.core.validation.export.ExportUtils;
import org.polarsys.capella.core.validation.export.RulesExporterProvider;
import org.polarsys.capella.core.validation.prefs.IExportValidationConstants;

/**
 * Preference page for exporting rules
 * 
 */
public class ExportValidationPreferencePage extends
		AbstractDefaultPreferencePage {

	/**
	 * @see org.eclipse.jface.preference.PreferencePage#doGetPreferenceStore()
	 */
	@Override
	protected IPreferenceStore doGetPreferenceStore() {

		IPreferenceStore preferences = new ScopedPreferenceStore(
				new InstanceScope(), CapellaValidationActivator.getDefault()
						.getPluginId());

		return preferences;
	}

	/**
	 * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
	 */
	@Override
	protected void createFieldEditors() {

		final Composite parent = getFieldEditorParent();

		createExportGroup(parent);

		return;
	}

	private void createExportGroup(Composite parent) {

		Group group = createGroup(PrefsMessages.exportGroupTitle,
				PrefsMessages.exportGroupToolTip, parent);

		BooleanFieldEditor bfe1 = new BooleanFieldEditor(
				IExportValidationConstants.EXPORT_ONLY_CAPELLA_PREF_ID,
				PrefsMessages.selectCapellaRulesOnlyLabel, group);
		addField(bfe1);

		BooleanFieldEditor bfe2 = new BooleanFieldEditor(
				IExportValidationConstants.EXPORT_ONLY_ACTIVE_PREF_ID,
				PrefsMessages.selectActiveRulesOnlyLabel, group);
		addField(bfe2);

		Button button = new Button(group, SWT.NONE);
		button.setText(PrefsMessages.exportButtonLabel);

		button.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				exportPressed();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				exportPressed();
			}
		});

		return;
	}

	/**
	 * The export button call-back
	 */
	protected void exportPressed() {

		IExporterProvider exporterProvider = new RulesExporterProvider();
		DataExporter dataExporter = new DataExporter(exporterProvider);

		//
		// enforce the save of the options
		//
		performApply();

		//
		// File Dialog setting
		//

		FileDialog fd = new FileDialog(getShell(), SWT.SAVE);

		fd.setText(PrefsMessages.fileBrowserDialogTitle);
		fd.setFilterExtensions(dataExporter.getSupportedExtension());
		fd.setFilterNames(dataExporter.getSupportedDescription());

		//
		// File Dialog selection
		//
		String fileName = fd.open();
		
    int filterIndex = fd.getFilterIndex();
    if (filterIndex != -1) {
      // If a filter was selected
      String selectedFilter = fd.getFilterExtensions()[filterIndex];
      String fileExtension = selectedFilter.substring(1);
      if (!fileName.endsWith(fileExtension)) {
        // If the filename doesn't already have the extension, append it
        fileName = fileName + fileExtension;
      }
    }

		//
		// The export operation itself
		//

		if (null != fileName) { // file selection was not canceled
			// boolean result = ExportUtils.exportValidationRules(dataExporter,
			// fileName);
			// new export with resolvers
			boolean result = ExportUtils.exportValidationRules(dataExporter, fileName);
			
			if (result) {
				MessageDialog.openInformation(getShell(),
						PrefsMessages.exportValidationRulesMessages,
						NLS.bind(PrefsMessages.exportOk, fileName));
			} else {
				MessageDialog.openError(getShell(),
						PrefsMessages.exportValidationRulesMessages,
						NLS.bind(PrefsMessages.exportKo, fileName));
			}
		}

		return;
	}

	/**
	 * @see org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage#getPageDescription()
	 */
	@Override
	protected String getPageDescription() {
		return PrefsMessages.exportPageDesc;
	}

	/**
	 * @see org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage#getPageTitle()
	 */
	@Override
	protected String getPageTitle() {
		return PrefsMessages.exportPageTitle;
	}

}
