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
package org.polarsys.capella.common.mdsofa.common.constant;

/**
 */
public interface IGeneratedPatternExtensionConstants {
  /**
   * Generated Pattern plug-in id.
   */
  static final String GENERATED_PATTERN_PLUGIN_ID = "org.polarsys.capella.common.mdsofa.asset.pattern"; //$NON-NLS-1$
  /**
   * Generated Pattern extension point id, excluding plug-in id.
   */
  static final String GENERATED_PATTERN_EXTENSION_POINT_ID = "mdsofaGeneratedPattern"; //$NON-NLS-1$
  /**
   * Generated Pattern extension point full id, including plug-in id.
   */
  static final String GENERATED_PATTERN_EXTENSION_POINT_FULL_ID =
                                                                  GENERATED_PATTERN_PLUGIN_ID + ICommonConstants.POINT_CHARACTER
                                                                      + GENERATED_PATTERN_EXTENSION_POINT_ID;
  /**
   * Generated Pattern extension point child <code>generatedPattern</code>.
   */
  static final String GENERATED_PATTERN_EXTENSION_POINT_CHILD_PATTERN = "generatedPattern"; //$NON-NLS-1$
  /**
   * Define a constant for the <code>templateCode</code> attribute.
   */
  static final String GENERATED_PATTERN_ATT_TEMPLATE_CODE = "templateCode"; //$NON-NLS-1$
  /**
   * Define a constant for the <code>templateCode</code> attribute.
   */
  static final String GENERATED_PATTERN_ATT_TEMPLATE_ENGINE_TYPE = "templateEngineType"; //$NON-NLS-1$
}
