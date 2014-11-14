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
package org.polarsys.capella.core.refinement.preferences;

/**
 */
public interface IRefinementPreferences {
  /**
   * Is refined diagram creation allowed or not
   */
  public static final String PREFS_ALLOW_REFINED_SCENARIO_DIAGRAM_CREATION = "refinement.diagram.creation"; //$NON-NLS-1$

  /**
   * Is refined diagram opening allowed or not
   */
  public static final String PREFS_ALLOW_REFINED_SCENARIO_DIAGRAM_OPENING = "refinement.diagram.opening"; //$NON-NLS-1$

  /**
   * Pre-validation before the merge step preference.
   */
  public static final String PREFS_MERGE_PRE_VALIDATION_ACTIVATION = "refinement.merge.pre.validation"; //$NON-NLS-1$

  /**
   * Is error during pre-validation before the merge step stop refinement preference.
   */
  public static final String PREFS_MERGE_STOP_ON_ERROR_DURING_PRE_VALIDATION = "refinement.merge.pre.validation.stop"; //$NON-NLS-1$
  
  /**
   * Default value for diagram creation preference
   */
  public static final boolean PREFS_ALLOW_REFINED_SCENARIO_DIAGRAM_CREATION_DEFAULT = false;

  /**
   * Default value for diagram opening preference
   */
  public static final boolean PREFS_ALLOW_REFINED_SCENARIO_DIAGRAM_OPENING_DEFAULT = false;
  
  /**
   * Default value for the {@link #PREFS_MERGE_PRE_VALIDATION_ACTIVATION} preference.
   */
  public static final boolean PREFS_MERGE_PRE_VALIDATION_ACTIVATION_DEFAULT = false;
  
  /**
   * Default value for the {@link #PREFS_MERGE_STOP_ON_ERROR_DURING_PRE_VALIDATION} preference.
   */
  public static final boolean PREFS_MERGE_STOP_ON_ERROR_DURING_PRE_VALIDATION_DEFAULT = true;

  
}
