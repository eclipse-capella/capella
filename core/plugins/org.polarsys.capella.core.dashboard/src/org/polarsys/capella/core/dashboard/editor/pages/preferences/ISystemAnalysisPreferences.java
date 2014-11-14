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

import org.polarsys.capella.core.dashboard.editor.pages.SystemAnalysisDashboardPage;

/**
 * Preferences related to System Analysis dashboard {@link SystemAnalysisDashboardPage}.
 */
public interface ISystemAnalysisPreferences {
  /**
   * Preferences related to System Analysis - Transition From Operational Analysis Section.
   */
  public static final String PREFERENCE_SECTION_TRANSITION_FROM_OA = "SystemAnalysis_TransitionFromOASection"; //$NON-NLS-1$
  /**
   * Preferences related to System Analysis - Create Define Actors, Missions and Capabilities Section.
   */
  public static final String PREFERENCE_SECTION_DEFINE_ACTORS_MISSIONS_CAPABILITIES = "SystemAnalysis_DefineActorsMissionsCapabilities"; //$NON-NLS-1$
  /**
   * Preferences related to System Analysis - Create Refine system functions, describe Functional Exchanges Section.
   */
  public static final String PREFERENCE_SECTION_REFINE_SYSTEM_FUNCTIONS_DESCRIBE_FE = "SystemAnalysis_RefineSystemFunctionsDescribeDataflows"; //$NON-NLS-1$
  /**
   * Preferences related to System Analysis - Create Allocate system function to system and actors Section.
   */
  public static final String PREFERENCE_SECTION_ALLOCATE_SYSTEM_FUNCTION_TO_SYSTEM_AND_ACTORS = "SystemAnalysis_AllocateSystemFunctionToSystemAndActors"; //$NON-NLS-1$
  /**
   * Preferences related to System Analysis - Define Interfaces and describe scenarios Section.
   */
  public static final String PREFERENCE_SECTION_DEFINE_INTERFACES_DESCRIBE_SCENARIOS = "SystemAnalysis_DefineInterfacesDescribeScenarios"; //$NON-NLS-1$
  /**
   * Preferences related to System Analysis - Create Transverse Modeling Section.
   */
  public static final String PREFERENCE_SECTION_TRANSVERSE_MODELING = "SystemAnalysis_TransverseModeling"; //$NON-NLS-1$

}
