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

import org.polarsys.capella.core.dashboard.editor.pages.LogicalArchitectureDashboardPage;

/**
 * Preferences related to Logical Architecture dashboard {@link LogicalArchitectureDashboardPage}.
 */
public interface ILogicalArchitecturePreferences {
  /**
   * Preferences related to Logical Architecture - Transition from System Functions Section.
   */
  public static final String PREFERENCE_SECTION_TRANSITION_FROM_SF = "LogicalArchitecture_TransitionFromSF"; //$NON-NLS-1$
  /**
   * Preferences related to Logical Architecture - Refine logical functions, describe Functional Exchanges Section.
   */
  public static final String PREFERENCE_SECTION_REFINE_LOGICAL_FUNCTIONS_DESCRIBE_FE = "LogicalArchitecture_RefineLogicalFunctionsDescribeDataflows"; //$NON-NLS-1$
  /**
   * Preferences related to Logical Architecture - Define Logical Components and Actors Section.
   */
  public static final String PREFERENCE_SECTION_DEFINE_LOGICAL_COMPONENTS_ACTORS = "LogicalArchitecture_DefineLogicalComponentsActors"; //$NON-NLS-1$
  /**
   * Preferences related to Logical Architecture - Allocate logical function to logical components Section.
   */
  public static final String PREFERENCE_SECTION_ALLOCATE_LOGICAL_FUNCTION_TO_LOGICAL_COMPONENTS =
      "LogicalArchitecture_AllocateLogicalFunctionToLogicalComponents"; //$NON-NLS-1$
  /**
   * Preferences related to Logical Architecture - Delegate System Interfaces and create Logical Interfaces Section.
   */
  public static final String PREFERENCE_SECTION_DELEGATE_SYSTEM_INTERFACES_AND_CREATE_LOGICAL_INTERFACES =
      "LogicalArchitecture_DelegateSystemInterfacesAndCreateLogicalInterfaces"; //$NON-NLS-1$
  /**
   * Preferences related to Logical Architecture - Enrich logical scenario Section.
   */
  public static final String PREFERENCE_SECTION_ENRICH_LOGICAL_SCENARIO = "LogicalArchitecture_EnrichLogicalScenario"; //$NON-NLS-1$
  /**
   * Preferences related to Logical Architecture - Create Transverse Modeling Section.
   */
  public static final String PREFERENCE_SECTION_TRANSVERSE_MODELING = "LogicalArchitecture_TransverseModeling"; //$NON-NLS-1$
}
