/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.helpers.argumentparser;

import org.eclipse.osgi.util.NLS;

/**
 * I18n support.
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.common.helpers.argumentparser.messages"; //$NON-NLS-1$
  
  
  public static String opsNotSupported;
  public static String inconsistentOptions;
  public static String duplicatedId;
  public static String unexpectedFlag;
  public static String mandatoryFlagNotFound; 
  public static String flagNotFound;
  public static String expectedDataDoesNotMatch;
  public static String expectedDataDoesNotMatchBasicCase;
  public static String duplicatedNameOnlyAllowedWithStrictMode;
  public static String unknownFlagFoundButNotAllowed;
  public static String genericError;
  public static String strictModeParseFailed;
  
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // Do nothing.
  }
}
