/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.model.preferences;

/**
 */
public interface IInterModelIntegrityPreferences {

	
	public static final String PREFS_PREVENT_ON_THE_FLY_DEPENDENCY_VIOLATION = "intermodel.preventDependencyViolation.allowed"; //$NON-NLS-1$

  /**
   * Default value for prevent on the fly inter-model dependency violation
   */
  public static final Boolean PREFS_PREVENT_ON_THE_FLY_DEPENDENCY_VIOLATION_DEFAULT = Boolean.TRUE;
}
