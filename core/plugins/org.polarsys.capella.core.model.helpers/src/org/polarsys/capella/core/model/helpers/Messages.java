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

package org.polarsys.capella.core.model.helpers;

import org.eclipse.osgi.util.NLS;

/**
 *
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.model.helpers.messages"; //$NON-NLS-1$
  public static String FunctionalChainExt_a;
  public static String FunctionalChainExt_an;
  public static String FunctionalChainExt_Function;
  public static String FunctionalChainExt_FunctionalExchange;
  public static String FunctionalChainExt_Interaction;
  public static String FunctionalChainExt_InvolvedElementNotAndNot;
  public static String FunctionalChainExt_InvolvedElementWithNoPrevious;
  public static String FunctionalChainExt_InvolvedElementWithMultiplePrevious;
  public static String FunctionalChainExt_InvolvedElementWithNoNext;
  public static String FunctionalChainExt_InvolvedElementWithMultipleNext;
  public static String Involvement_InvolvedNull;
  public static String Involvement_InvolverNull;
  public static String FunctionalChainExt_InvolvementAlone;
  public static String FunctionalChainExt_InvolverNotContainer;
  public static String FunctionalChainExt_InvolverNull;
  public static String FunctionalChainExt_IsButNextIsNotA;
  public static String FunctionalChainExt_IsNotRelatedToSourceNext;
  public static String FunctionalChainExt_IsNotRelatedToTargetNext;
  public static String FunctionalChainExt_IsNotRelatedToTargetNextFunctionalChain;
  public static String FunctionalChainExt_NextIsNot;
  public static String FunctionalChainExt_OperationalActivity;

  public static String FunctionalChainExt_OperationalProcess;
  public static String FunctionalChainExt_FunctionalChain;
  public static String FunctionalChainExt_InvolvedElementNot;

  public static String PhysicalPathExt_PhysicalComponent;
  public static String PhysicalPathExt_PhysicalLink;
  public static String PhysicalPathExt_PhysicalPath;

  public static String FunctionalChainExt_NextIsNotOrNot;
  public static String FunctionalChainExt_IsNotRelatedToOutgoingNext;

  public static String FunctionalChainInvLink_SourceNull;
  public static String FunctionalChainInvLink_TargetNull;
  public static String FunctionalChainInv_EmptyOwnedInvolvements;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // Nothing
  }
}
