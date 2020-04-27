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
package org.polarsys.capella.core.ui.properties.preferences;

/**
 */
public interface ITransfertViewerPreferences {
  /**
   * Whether or not automatically expand left viewer tree content
   */
  public static final String PREFS_EXPAND_LEFT_VIEWER_CONTENT = "expand.left.viewer.content"; //$NON-NLS-1$

  /**
   * Whether or not automatically expand right viewer tree content
   */
  public static final String PREFS_EXPAND_RIGHT_VIEWER_CONTENT = "expand.right.viewer.content"; //$NON-NLS-1$

  /**
   * Whether or not automatically expand single viewer tree content
   */
  public static final String PREFS_EXPAND_SINGLE_VIEWER_CONTENT = "expand.single.viewer.content"; //$NON-NLS-1$
  
  /**
   * Either to calculate a custom label for the items in the viewer or to use element name as label. 
   */
  public static final String PREFS_DISABLE_LABEL_COMPUTATION = "disable.label.computation"; //$NON-NLS-1$

  /**
   * Default value for automatically expand left viewer tree content preference
   */
  public static final Boolean PREFS_EXPAND_LEFT_VIEWER_CONTENT_DEFAULT = Boolean.TRUE;

  /**
   * Default value for automatically expand right viewer tree content preference
   */
  public static final Boolean PREFS_EXPAND_RIGHT_VIEWER_CONTENT_DEFAULT = Boolean.TRUE;

  /**
   * Default value for automatically expand single viewer tree content preference
   */
  public static final Boolean PREFS_EXPAND_SINGLE_VIEWER_CONTENT_DEFAULT = Boolean.TRUE;
  
  /**
   * Default value for disabling labels computation preference
   */
  public static final Boolean PREFS_DISABLE_LABEL_COMPUTATION_DEFAULT = Boolean.FALSE;
}
