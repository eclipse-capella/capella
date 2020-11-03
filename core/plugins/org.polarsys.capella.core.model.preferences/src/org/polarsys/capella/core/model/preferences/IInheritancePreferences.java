/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
   * Is non actor inheritance allowed or not
   */
  public static final String PREFS_ALLOW_COMPONENT_NON_ACTOR_INHERITANCE = "componentNonActorInheritance.allowed"; //$NON-NLS-1$

  /**
   * Default value for non actor inheritance preference
   */
  public static final Boolean PREFS_ALLOW_COMPONENT_NON_ACTOR_INHERITANCE_DEFAULT = Boolean.FALSE;

}
