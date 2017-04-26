/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
public class IProjectionPreferences {

  public static final String PREFS_PROJECTION_ID = "org.polarsys.capella.core.projection.preferences"; //$NON-NLS-1$

  /**
   * Interface projection mode
   */
  public static final String PREFS_INTERFACE_PROJECTION = "interface.mode"; //$NON-NLS-1$

  /**
   * Default value for interface projection mode
   */
  public static final Boolean DEFAULT_INTERFACE_PROJECTION = Boolean.FALSE;

  /**
   * Preference name for LC2PC transition
   */
  public static final String PREFS_LCPC_PROJECTION_MODE = "projection.lcpc.mode"; //$NON-NLS-1$

  /**
   * Old preference name for LC2PC transition (capella 1.4-)
   */
  public static final String PREFS_OLD_LCPC_PROJECTION_MODE = "projection.mode"; //$NON-NLS-1$

  /**
   * Preference value for Leaf Strategy
   */
  public static final String LC2PC_LEAF_PROJECTION_MODE = "1"; //$NON-NLS-1$

  /**
   * Preference value for Breakdown Strategy
   */
  public static final String LC2PC_BREAKDOWN_PROJECTION_MODE = "2"; //$NON-NLS-1$

  /**
   * default value for LC2PC Projection mode
   */
  public static final String DEFAULT_PROJECTION_MODE = LC2PC_BREAKDOWN_PROJECTION_MODE;

  /**
   * Preference value for exchange item transition
   */
  public static final String EXCHANGE_ITEM_PROJECTION = "projection.exchangeItems"; //$NON-NLS-1$

  /**
   * default value for exchange item transition
   */
  public static final Boolean DEFAULT_EXCHANGE_ITEM_PROJECTION = Boolean.FALSE;

  /**
   * Preference value for dataType transition
   */
  public static final String DATATYPE_PROJECTION = "projection.dataType"; //$NON-NLS-1$

  /**
   * default value for dataType transition
   */
  public static final Boolean DEFAULT_DATATYPE_PROJECTION = Boolean.FALSE;

  /**
   * Preference value for stateMachine transition while component transition
   */
  public static final String STATE_MACHINE_PROJECTION = "projection.component.stateMachine"; //$NON-NLS-1$

  /**
   * Preference value for functional transition while component transition
   */
  public static String FUNCTIONAL_ELEMENT_PROJECTION = "projection.functional"; //$NON-NLS-1$

  /**
   * default value for stateMachine transition while component transition
   */
  public static final Boolean DEFAULT_STATE_MACHINE_PROJECTION = Boolean.FALSE;

  public static final String PREFS_INTERFACEGEN_PROPAGATE_EXCHANGE_ITEMS = "projection.interfaces.propagateEI"; //$NON-NLS-1$

  public static final String PREFS_INTERFACEGEN_CREATE_COMPONENT_EXCHANGE = "projection.interfaces.createCE"; //$NON-NLS-1$
  
  public static Boolean DEFAULT_FUNCTIONAL_ELEMENT_PROJECTION = Boolean.TRUE;

  @Deprecated
  public static final String PREFS_USE_STANDARDPORT_INSTEAD_FLOWPORT = "projection.useStandardPortInsteadFlowPort"; //$NON-NLS-1$

  @Deprecated
  public static String PREFS_GENERATE_COMPONENTPORT = "projection.interfaces.linkToComponentPort"; //$NON-NLS-1$

  @Deprecated
  public static Boolean DEFAULT_USE_STANDARDPORT_INSTEAD_FLOWPORT = Boolean.FALSE;

  @Deprecated
  public static Boolean DEFAULT_GENERATE_COMPONENTPORT = Boolean.TRUE;
  
}
