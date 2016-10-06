/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.misc.ju.testcases;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.SiriusElementHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.SessionHelper;

public class OpenAllAvailableDDiagramsTestCase extends BasicTestCase {

  private static final String projectTestName = "EOLE_AF_UC";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(projectTestName);
  }

  /** The test itself */
  @SuppressWarnings("synthetic-access")
  public void test() {
    Session session = getSession(projectTestName);
    assertNotNull(session);
    
    Collection<DRepresentation> allDSemanticDiagrams = SiriusElementHelper.getAllDSemanticDiagrams(session);

    for (DRepresentation diagram : allDSemanticDiagrams) {
      DiagramHelper.opendiagramEditor(session, diagram);

      SessionHelper.saveSession(session);
      SessionHelper.closeEditors(session, false);
    }

    // In case of...
    SessionHelper.closeEditors(session, false);
    SessionHelper.saveSession(session);
  }
}
