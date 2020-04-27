/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.validation.export;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.validation.model.Category;
import org.eclipse.emf.validation.service.IConstraintDescriptor;

import org.polarsys.capella.common.helpers.export.DataExporter;
import org.polarsys.capella.core.validation.CapellaValidationActivator;
import org.polarsys.capella.core.validation.filter.CapellaConstraintFilter;
import org.polarsys.capella.core.validation.prefs.IExportValidationConstants;
import org.polarsys.capella.core.validation.utils.ValidationHelper;

/**
 * Utility class in order to export validation rules
 * 
 */
public class ExportUtils {

	private static final int COMPARATOR_IDX = 0;
	
	private ExportUtils() {
		// Do nothing
	}
	

	/**
	 * Export validation rules.
	 * 
	 * @param dataExporter
	 * @param filename
	 * @return <code>true</code> whether job was done, <code>false</code>
	 *         otherwise.
	 */
	public static boolean exportValidationRules(DataExporter dataExporter,
			String filename) {

		// TODO implements switch with selected Exporter...

		boolean result = false;

		List<String[]> data = new ArrayList<>();

		IEclipsePreferences preferences = new InstanceScope()
				.getNode(CapellaValidationActivator.getDefault().getPluginId());

		boolean onlyActiveRules = preferences
				.getBoolean(
						IExportValidationConstants.EXPORT_ONLY_ACTIVE_PREF_ID,
						IExportValidationConstants.EXPORT_ONLY_ACTIVE_PREF_DEFAULT_VALUE);

		boolean onlyCapellaRules = preferences
				.getBoolean(
						IExportValidationConstants.EXPORT_ONLY_CAPELLA_PREF_ID,
						IExportValidationConstants.EXPORT_ONLY_CAPELLA_PREF_DEFAULT_VALUE);

		//
		// Prepare data
		//

		boolean addit = false;
		for (IConstraintDescriptor icd : ValidationHelper
				.getAllConstraintDescriptors()) {
			addit = (!onlyActiveRules || (icd.isEnabled() == true))
					&& (!onlyCapellaRules || (getCategory(icd).getPath()
							.startsWith(
									CapellaConstraintFilter.CAPELLA_CONSTRAINT_CATEGORY_PATH)));

			if (addit) {
				data.add(prepareCSVTXT(icd));
			}
		}

		Comparator<String[]> comparator = new Comparator<String[]>() {

			public int compare(String[] o1, String[] o2) {
				return o1[COMPARATOR_IDX].compareTo(o2[COMPARATOR_IDX]);
			}

		};

		Collections.sort(data, comparator);

		// Header
		final String[] header = new String[] {
				ExportMessages.exportHeaderCategoryPath,
				ExportMessages.exportHeaderCategoryName,
				ExportMessages.exportHeaderRuleId,
				ExportMessages.exportHeaderRuleSeverity,
				ExportMessages.exportHeaderIsRuleEnabled,
				ExportMessages.exportHeaderRuleMode,
				ExportMessages.exportHeaderRuleDescription };
		data.add(0, header);

		//
		// Perform the job
		//

		result = dataExporter.exportToFile(filename, data);

		return result;
	}

	private static String[] prepareCSVTXT(IConstraintDescriptor icd) {
		Category category = getCategory(icd);
		return new String[] { category.getPath(),
				category.getName(), icd.getId(),
				icd.getSeverity().getLocalizedName(),
				String.valueOf(icd.isEnabled()),
				icd.getEvaluationMode().getLocalizedName(),
				icd.getDescription() };
	}


	private static IConfigurationElement[] getAllContributions() {
		String point = "org.polarsys.capella.core.validation.ui.ide.capellaQuickFix";
		return Platform
				.getExtensionRegistry().getConfigurationElementsFor(point);
	}

	private static Category getCategory(IConstraintDescriptor icd) {
		return icd.getCategories().iterator().next();
	}
}
