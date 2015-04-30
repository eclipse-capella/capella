/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
public interface IInheritancePreferences {
  /**
   * Is multiple inheritance allowed or not
   */
  public static final String PREFS_ALLOW_MULTIPLE_INHERITANCE = "inheritance.allowed"; //$NON-NLS-1$

  /**
   * Default value for multiple inheritance preference
   */
  public static final Boolean PREFS_ALLOW_MULTIPLE_INHERITANCE_DEFAULT = Boolean.FALSE;

  /**
   * Is multiple inheritance allowed or not
   */
  public static final String PREFS_ALLOW_COMPONENT_INHERITANCE = "componentInheritance.allowed"; //$NON-NLS-1$

  /**
   * Default value for multiple inheritance preference
   */
  public static final Boolean PREFS_ALLOW_COMPONENT_INHERITANCE_DEFAULT = Boolean.FALSE;
}
