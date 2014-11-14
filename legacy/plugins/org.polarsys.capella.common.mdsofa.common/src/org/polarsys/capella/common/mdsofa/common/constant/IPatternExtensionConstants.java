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
public interface IPatternExtensionConstants {
  /**
   * Pattern plug-in id.
   */
  public static final String PATTERN_PLUGIN_ID = "org.polarsys.capella.common.mdsofa.asset.pattern"; //$NON-NLS-1$
  /**
   * Pattern extension point id, excluding plug-in id.
   */
  public static final String PATTERN_EXTENSION_POINT_ID = "mdsofaPattern"; //$NON-NLS-1$
  /**
   * Pattern extension point full id, including plug-in id.
   */
  public static final String PATTERN_EXTENSION_POINT_FULL_ID = PATTERN_PLUGIN_ID + ICommonConstants.POINT_CHARACTER + PATTERN_EXTENSION_POINT_ID;
  /**
   * Pattern extension point child type.
   */
  public static final String PATTERN_EXTENSION_POINT_CHILD_TYPE = "patternExtType"; //$NON-NLS-1$
  /**
   * Pattern extension containing plug-in id.
   */
  public static final String PATTERN_EXTENSION_CONTAINING_PLUGIN_ID = "pluginId"; //$NON-NLS-1$
  /**
   * Pattern extension containing asset name.
   */
  public static final String PATTERN_EXTENSION_CONTAINING_ASSET_NAME = "assetName"; //$NON-NLS-1$
  /**
   * Pattern extension read from the workspace or from the target platform.<br>
   * Key for a boolean value.<br>
   * True means that the extension has been loaded from the workspace, false from the target platform.
   */
  public static final String PATTERN_EXTENSION_FROM_WORKSPACE = "fromWorkspace"; //$NON-NLS-1$
  /**
   * Workspace project containing the pattern being described.<br>
   * Key for an IProject value.
   */
  public static final String PATTERN_EXTENSION_WORKSPACE_PROJECT = "wsProject"; //$NON-NLS-1$
  /**
   * Pattern extension point child 'library'.
   */
  public static final String PATTERN_EXTENSION_POINT_CHILD_LIBRARY = "library"; //$NON-NLS-1$
  /**
   * Pattern extension point child 'pattern'.
   */
  public static final String PATTERN_EXTENSION_POINT_CHILD_PATTERN = "pattern"; //$NON-NLS-1$
  /**
   * Library production orchestration attribute name as defined by the pattern extension point.
   */
  public static final String LIBRARY_PRODUCTION_ORCHESTRATION_ATTRIBUTE_NAME = "productionOrchestration"; //$NON-NLS-1$
  /**
   * Library runtime orchestration attribute name as defined by the pattern extension point.
   */
  public static final String LIBRARY_RUNTIME_ORCHESTRATION_ATTRIBUTE_NAME = "runtimeOrchestration"; //$NON-NLS-1$
  /**
   * Pattern definition attribute name as defined by the pattern extension point.
   */
  public static final String PATTERN_DEFINITION_ATTRIBUTE_NAME = "definition"; //$NON-NLS-1$
  /**
   * Library/Pattern separator in a library/pattern id.
   */
  public static final char LIBRARY_PATTERN_ID_SEPARATOR = '/';
  /**
   * Ecore uri to genModel uri mandatory prefix.
   */
  public static final String ECORE_TO_GENMODEL_URI_PREFIX = "/ecore"; //$NON-NLS-1$
}
