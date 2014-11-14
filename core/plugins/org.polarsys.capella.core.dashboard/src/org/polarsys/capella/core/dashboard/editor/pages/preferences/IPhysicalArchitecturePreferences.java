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

import org.polarsys.capella.core.dashboard.editor.pages.PhysicalArchitectureDashboardPage;

/**
 * Preferences related to Physical Architecture dashboard {@link PhysicalArchitectureDashboardPage}.
 */
public interface IPhysicalArchitecturePreferences {
  /**
   * Preferences related to Physical Architecture - Transition from Logical Functions Section.
   */
  public static final String PREFERENCE_SECTION_TRANSITION_FROM_LF = "PhysicalArchitecture_TransitionFromLF"; //$NON-NLS-1$
  /**
   * Preferences related to Physical Architecture - Refine Physical functions, describe Functional Exchanges Section.
   */
  public static final String PREFERENCE_SECTION_REFINE_PHYSICAL_FUNCTIONS_DESCRIBE_FE = "PhysicalArchitecture_RefinePhysicalFunctionsDescribeDataflows"; //$NON-NLS-1$
  /**
   * Preferences related to Physical Architecture - Define Physical Components and Actors Section.
   */
  public static final String PREFERENCE_SECTION_DEFINE_PHYSICAL_COMPONENTS_ACTORS = "PhysicalArchitecture_DefinePhysicalComponentsActors"; //$NON-NLS-1$
  /**
   * Preferences related to Physical Architecture - Allocate Physical functions to Physical components and manage deployments Section.
   */
  public static final String PREFERENCE_SECTION_ALLOCATE_PHYSICAL_FUNCTION_TO_PHYSICAL_COMPONENTS =
      "PhysicalArchitecture_AllocatePhysicalFunctionToPhysicalComponents"; //$NON-NLS-1$
  /**
   * Preferences related to Physical Architecture - Create Delegate Logical Interfaces and create Physical Interfaces Section.
   */
  public static final String PREFERENCE_SECTION_DELEGATE_LOGICAL_INTERFACES_AND_CREATE_PHYSICAL_INTERFACES =
      "PhysicalArchitecture_DelegateLogicalInterfacesAndCreatePhysicalInterfaces"; //$NON-NLS-1$
  /**
   * Preferences related to Physical Architecture - Enrich physical scenario Section.
   */
  public static final String PREFERENCE_SECTION_ENRICH_PHYSICAL_SCENARIO = "PhysicalArchitecture_EnrichPhysicalScenario"; //$NON-NLS-1$
  /**
   * Preferences related to Physical Architecture - Create Transverse Modeling Section.
   */
  public static final String PREFERENCE_SECTION_TRANSVERSE_MODELING = "PhysicalArchitecture_TransverseModeling"; //$NON-NLS-1$
}
