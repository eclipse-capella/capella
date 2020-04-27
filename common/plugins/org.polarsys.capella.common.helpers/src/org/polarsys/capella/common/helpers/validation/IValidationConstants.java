/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.helpers.validation;

/**
 * Shared validation constants.
 */
public interface IValidationConstants {

  /**
   * Alternative tag to set an arbitrary validation rule id. Markers that result from constraint validation should
   * always use the diagnostic tag below for rich semantics. This attribute should be used only for access to
   * attribute's filtering of org.eclipse.ui.ide.markerResolution extension point
   */
  public static final String TAG_RULE_ID = "ruleId"; //$NON-NLS-1$

  public static final String TAG_PREFERENCE_EPF_FILE = "preferenceFile"; //$NON-NLS-1$

  /**
   * Marker attribute to retrieve an EMF diagnostic from a marker. Capella attaches a special Diagnostic,
   * ConstraintStatusDiagnostic, which clients can use to get back to the corresponding Constraint descriptor.
   * 
   * @deprecated use marker.getAdapter(Diagnostic.class) instead
   */
  // @Deprecated
  //public static final String TAG_DIAGNOSTIC = "diagnostic"; //$NON-NLS-1$

}
