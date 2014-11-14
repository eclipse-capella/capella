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

/**
 * Preferences related to Architecture dashboard {@link AbstractCapellaArchitectureDashboardPreferencePage}.
 */
public interface ITransversePreferences {
  /**
   * Preferences related to hide / show sections related to activities related to Functional Analysis.
   */
  public static final String PREFERENCE_FUNCTIONAL_ANALYSIS_ACTIVITIES_SECTIONS = "Architecture_FunctionalAnalysisActivitiesSections"; //$NON-NLS-1$
  /**
   * Preferences related to hide / show sections related to activities related to Interface Engineering.
   */
  public static final String PREFERENCE_INTERFACE_ENGINEERING_ACTIVITIES_SECTIONS = "Architecture_InterfaceEngineeringActivitiesSections"; //$NON-NLS-1$
}
