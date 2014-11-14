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
package org.polarsys.capella.common.helpers.validation;

/**
 * Shared validation constants.
 */
public interface IValidationConstants {

  /**
   * Alternative tag to set an arbitraty validation rule id. Markers that result from constraint validation should always use the diagnostic tag below for rich
   * semantics.
   */
  public static final String TAG_RULE_ID = "ruleId"; //$NON-NLS-1$

  public static final String TAG_PREFERENCE_EPF_FILE = "preferenceFile"; //$NON-NLS-1$

  /**
   * Marker attribute to retrieve an EMF diagnostic from a marker. Capella attaches a special Diagnostic, ConstraintStatusDiagnostic, which clients can use to
   * get back to the corresponding Constraint descriptor.
   */
  public static final String TAG_DIAGNOSTIC = "diagnostic"; //$NON-NLS-1$
}
