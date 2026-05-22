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
package org.polarsys.capella.core.data.fa.validation.functionalExchange;

import org.eclipse.osgi.util.NLS;

/**
 *
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.data.fa.validation.functionalExchange.messages"; //$NON-NLS-1$
  public static String exchangeItemsOnFunctionPorts_allocated;
  public static String FunctionalExchange_Delegation_0;
  public static String FunctionalExchange_Delegation_1;
  public static String FunctionalExchange_Delegation_2;
  public static String FunctionalExchange_Delegation_3;
  public static String FunctionalExchange_loopsOnSameFunction_0;
  public static String FunctionalExchange_loopsOnSameFunction_1;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
}
