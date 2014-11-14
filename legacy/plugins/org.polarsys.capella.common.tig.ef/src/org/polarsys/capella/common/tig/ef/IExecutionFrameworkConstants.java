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
package org.polarsys.capella.common.tig.ef;

/**
 * Execution framework constants.<br>
 * Includes plug-in id.
 */
public interface IExecutionFrameworkConstants {
  /**
   * Plug-in id.
   */
  static final String PLUGIN_ID = "org.polarsys.capella.common.tig.ef"; //$NON-NLS-1$

  /**
   * Editing domain provider extension point short id.
   */
  static final String EXTENSION_POINT_ID_EDITING_DOMAIN_PROVIDER = "editingDomainProvider"; //$NON-NLS-1$

  /**
   * Editing domain provider extension point editor IDs attribute.
   */
  static final String EXTENSION_POINT_ATTRIBUTE_EDITOR_IDS = "editorIds"; //$NON-NLS-1$
}
