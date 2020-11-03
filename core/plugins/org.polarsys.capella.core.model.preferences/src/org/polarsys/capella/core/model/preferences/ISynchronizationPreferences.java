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
public interface ISynchronizationPreferences {
  /**
   * Is synchronization of component port to function port allocation allowed or not
   */
  public static final String PREFS_ALLOW_SYNC_COMPONENTPORT_TO_FUNCTIONPORT = "sync.componentport2functionport.allowed"; //$NON-NLS-1$
  
  /**
   * Is synchronization of physical port to component port allocation on physical links allowed or not
   */
  public static final String PREFS_ALLOW_SYNC_PHYSICALPORT_TO_COMPONENTPORT_ON_PHYSICALLINK = "sync.physicalport2componentport.physicallink.allowed"; //$NON-NLS-1$
  
  /**
   * Is synchronization of physical port to component port allocation on physical paths allowed or not
   */
  public static final String PREFS_ALLOW_SYNC_PHYSICALPORT_TO_COMPONENTPORT_ON_PHYSICALPATH = "sync.physicalport2componentport.physicalpath.allowed"; //$NON-NLS-1$

  /**
   * Default value for synchronization of component port to function port allocation preference
   */
  public static final Boolean PREFS_ALLOW_SYNC_COMPONENTPORT_TO_FUNCTIONPORT_DEFAULT = Boolean.TRUE;

  /**
   * Default value for synchronization of physical port to component port allocation on physical links preference
   */
  public static final Boolean PREFS_ALLOW_SYNC_PHYSICALPORT_TO_COMPONENTPORT_ON_PHYSICALLINK_DEFAULT = Boolean.TRUE;

  /**
   * Default value for synchronization of physical port to component port allocation on physical paths preference
   */
  public static final Boolean PREFS_ALLOW_SYNC_PHYSICALPORT_TO_COMPONENTPORT_ON_PHYSICALPATH_DEFAULT = Boolean.TRUE;
}
