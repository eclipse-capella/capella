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

	private final static int COMPARATOR_IDX = 0;
	private static IConfigurationElement[] quickFixContributions = getAllContributions();
	

	/**
	 * Export validation rules.
	 * 
	 * @param dataExporter_p
	 * @param filename_p
	 * @return <code>true</code> whether job was done, <code>false</code>
	 *         otherwise.
	 */
	public static boolean exportValidationRules(DataExporter dataExporter_p,
			String filename_p) {

		// TODO implements switch with selected Exporter...

		boolean result = false;

		List<String[]> data = new ArrayList<String[]>();

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
			addit = (!onlyActiveRules || (onlyActiveRules && icd.isEnabled() == true))
					&& (!onlyCapellaRules || (onlyCapellaRules && getCategory(icd).getPath()
							.startsWith(
									CapellaConstraintFilter.CAPELLA_CONSTRAINT_CATEGORY_PATH)));

			if (addit) {
				data.add(prepareCSVTXT(icd));
			}
		}

		Comparator<String[]> comparator = new Comparator<String[]>() {

			public int compare(String[] o1_p, String[] o2_p) {
				return o1_p[COMPARATOR_IDX].compareTo(o2_p[COMPARATOR_IDX]);
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

		result = dataExporter_p.exportToFile(filename_p, data);

		return result;
	}

	static private String[] prepareCSVTXT(IConstraintDescriptor icd_p) {

		Category category = getCategory(icd_p);

		String[] result = new String[] { category.getPath(),
				category.getName(), icd_p.getId(),
				icd_p.getSeverity().getLocalizedName(),
				String.valueOf(icd_p.isEnabled()),
				icd_p.getEvaluationMode().getLocalizedName(),
				icd_p.getDescription() };

		return result;
	}


	static private IConfigurationElement[] getAllContributions() {
		String point = "org.polarsys.capella.core.validation.ui.ide.capellaQuickFix";
		IConfigurationElement[] configurationElements = Platform
				.getExtensionRegistry().getConfigurationElementsFor(point);
		return configurationElements;
	}

	static private Category getCategory(IConstraintDescriptor icd_p) {
		return icd_p.getCategories().iterator().next();
	}


}
