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

package org.polarsys.capella.core.model.skeleton;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * The messages strings.
 */
public class Messages {
  // The bundle name.
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.model.skeleton.messages"; //$NON-NLS-1$
  // The resource bundle.
  private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

  /**
   * Constructs the messages.
   */
  private Messages() {
    // Do nothing.
  }

  /**
   * Gets the string from the specified key.
   * @param key_p The key string.
   * @return The corresponding string.
   */
  public static String getString(String key_p) {
    try {
      return RESOURCE_BUNDLE.getString(key_p);
    } catch (MissingResourceException mre) {
      return '!' + key_p + '!';
    }
  }
}
