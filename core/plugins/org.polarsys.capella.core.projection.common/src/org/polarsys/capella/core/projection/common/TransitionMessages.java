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
package org.polarsys.capella.core.projection.common;

import org.eclipse.osgi.util.NLS;

/**
 */
public class TransitionMessages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.projection.common.projectionMessages"; //$NON-NLS-1$

  public static String generateComponentExchanges_label;
  public static String generateCommunicationMeans_label;
  public static String generatePhysicalLinks_label;
  public static String generatePhysicalLinksComponentExchanges_label;
  public static String generateInterfaces_label;
  public static String generateInterfaceDelegationsLA_label;
  public static String generateInterfaceDelegationsSA_label;

  public static String transitionInterface_label;
  public static String transitionFunctional_label;
  public static String transitionActor_label;
  public static String transitionData_label;
  public static String transitionPropertyValue_label;
  public static String transitionLC2PC_label;
  public static String transitionCapability_label;

  public static String transitionStateMachine_label;
  public static String transitionExchangeItem_label;
  public static String transitionOC2SC_label;
  public static String transitionOC2SM_label;
  public static String transitionOA2SC_label;
  public static String transitionOA2SM_label;
  public static String transitionOE2Actor_label;
  public static String transitionOE2System_label;

  public static String transitionES2IS_label;

  public static String transitionESF2ESB_label;

  public static String transitionFS2ES_label;
  public static String transitionFS2ES_OASA_label;
  public static String transitionFS2ES_SALAPA_label;

  public static String transitionES2ES_label;
  public static String transitionES2ES_OAtoSA_label;
  public static String transitionES2ES_SAtoLA_label;
  public static String transitionES2ES_LAtoPA_label;

  public static String transitionFS2FS_label;
  public static String transitionFS2FS_OAtoSA_label;
  public static String transitionFS2FS_SAtoLA_label;
  public static String transitionFS2FS_LAtoPA_label;

  public static String transition_title;
  public static String transition_processing;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, TransitionMessages.class);
  }

  private TransitionMessages() {
    // Nothing yet
  }
}
