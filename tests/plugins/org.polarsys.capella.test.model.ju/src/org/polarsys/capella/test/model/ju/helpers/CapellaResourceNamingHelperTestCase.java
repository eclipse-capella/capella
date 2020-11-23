/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.model.ju.helpers;

import java.text.MessageFormat;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.polarsys.capella.core.model.handler.command.CapellaResourceNamingHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class CapellaResourceNamingHelperTestCase extends BasicTestCase {
  private static final String[] INVALID_NAMES = { null, "" };

  private static final char[] INVALID_CHARS = { '#', '%', '$' };
  private static final char[] VALID_CHARS = { '-', '!', '~', '@', '^', '(', ')', '[', ']', '_' };

  private static final int[] RESOURCE_TYPES = { IResource.FILE, IResource.FOLDER, IResource.PROJECT };

  private static final String SUFFIX_NAME = "suffix01";
  private static final String PREFIX_NAME = "prefix02";

  private static final String NAME_SHOULD_BE_INVALID_MSG = "Resource name {0} should be invalid";
  private static final String NAME_SHOULD_BE_VALID_MSG = "Resource name {0} should be valid";

  @Override
  public void test() throws Exception {
    assertInvalidNames();
    assertValidNames();
  }

  private void assertValidNames() {
    for (char validChar : VALID_CHARS) {

      String simpleName = String.valueOf(validChar);
      assertValidName(simpleName);

      String prefixName = PREFIX_NAME + simpleName;
      assertValidName(prefixName);

      String suffixName = simpleName + SUFFIX_NAME;
      assertValidName(suffixName);

      String fullName = PREFIX_NAME + simpleName + SUFFIX_NAME;
      assertValidName(fullName);
    }
  }

  private void assertInvalidNames() {
    for (String invalidName : INVALID_NAMES) {
      assertInvalidName(invalidName);
    }

    for (char invalidChar : INVALID_CHARS) {
      String simpleName = String.valueOf(invalidChar);
      assertInvalidName(simpleName);

      String prefixName = PREFIX_NAME + simpleName;
      assertInvalidName(prefixName);

      String suffixName = simpleName + SUFFIX_NAME;
      assertInvalidName(suffixName);

      String fullName = PREFIX_NAME + simpleName + SUFFIX_NAME;
      assertInvalidName(fullName);

    }
  }

  private void assertInvalidName(String invalidName) {
    for (int resourceTypeMask : RESOURCE_TYPES) {
      IStatus status = CapellaResourceNamingHelper.validateName(invalidName, resourceTypeMask);
      String message = MessageFormat.format(NAME_SHOULD_BE_INVALID_MSG, invalidName);

      assertFalse(message, status.isOK());
    }
  }

  private void assertValidName(String invalidName) {
    for (int resourceTypeMask : RESOURCE_TYPES) {
      IStatus status = CapellaResourceNamingHelper.validateName(invalidName, resourceTypeMask);
      String message = MessageFormat.format(NAME_SHOULD_BE_VALID_MSG, invalidName);

      assertTrue(message, status.isOK());
    }
  }
}
