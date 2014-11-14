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

import org.polarsys.capella.core.dashboard.editor.pages.OperationalAnalysisDashboardPage;

/**
 * Preferences related to Operational Analysis dashboard {@link OperationalAnalysisDashboardPage}.
 */
public interface IOperationalAnalysisPreferences {
  /**
   * Preferences related to Operational Analysis - Create Define Actors, Operational Entities and Operational Capabilities Section.
   */
  public static final String PREFERENCE_SECTION_DEFINE_OPERATIONAL_ENTITIES_CAPABILITIES = "OperationalAnalysis_DefineActorsOperationalEntitiesCapabilities"; //$NON-NLS-1$
  /**
   * Preferences related to Operational Analysis - Create Define operational activities and describe interactions Section.
   */
  public static final String PREFERENCE_SECTION_DEFINE_OPERATIONAL_ACTIVITIES_INTERACTION = "OperationalAnalysis_DefineOperationalActivitiesInteraction"; //$NON-NLS-1$
  /**
   * Preferences related to Operational Analysis - Create Allocate operational activities to operational actors, entities or roles Section.
   */
  public static final String PREFERENCE_SECTION_ALLOCATE_OPERATIONAL_ACTIVITIES_TO_ACTORS_ENTITIES_ROLES =
      "OperationalAnalysis_AllocateOperationalActivitiesToActorsEntitiesRoles"; //$NON-NLS-1$
  /**
   * Preferences related to Operational Analysis - Create Transverse Modeling Section.
   */
  public static final String PREFERENCE_SECTION_TRANSVERSE_MODELING = "OperationalAnalysis_TransverseModeling"; //$NON-NLS-1$
}
