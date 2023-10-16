/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.libraries.ui.ju.testcases.basic;

import java.net.URL;
import java.util.Collection;
import java.util.stream.Collectors;

import org.eclipse.help.internal.base.BaseHelpSystem;
import org.polarsys.capella.core.libraries.ui.handlers.ManageReferencedLibrariesHandler;
import org.polarsys.capella.test.doc.ju.BrokenLinksCheckTestCase;

//This test ensures that the documentation link opened by the popup when unreferencing libraries is resolvable 
public class LibraryBrokenLinkTestCase extends BrokenLinksCheckTestCase {

  @Override
  public void test() throws Exception {
    BaseHelpSystem.ensureWebappRunning();
    String stringURL = getHelpServer()
        + ManageReferencedLibrariesHandler.LIBRARY_DETACHMENT_DOC_URI.replace(" ", "%20");
    URL rootURL = new URL(stringURL);
    Collection<String> messages = extractBrokenLinkMessages(rootURL);
    assertTrue(messages.stream().collect(Collectors.joining("\n")), messages.isEmpty());
  }
}
