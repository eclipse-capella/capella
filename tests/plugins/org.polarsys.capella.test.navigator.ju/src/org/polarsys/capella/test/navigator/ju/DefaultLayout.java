/*******************************************************************************
 * Copyright (c) 2016 Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales Global Services S.A.S - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.navigator.ju;

import java.util.Arrays;
import java.util.List;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.test.framework.api.BasicTestCase;

/**
 * This test case checks the layout of a default project.
 * 
 *  @author Thomas Guiu
 */
public class DefaultLayout extends BasicTestCase {


  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("NavigatorEmptyProject");
  }

  @Override
  public void test() throws Exception {
    CapellaModel model = getTestModel(getRequiredTestModels().get(0));
    Session session = getSession(getRequiredTestModels().get(0));
    Project project = model.getProject(session.getTransactionalEditingDomain());

    assertTrue(project.getOwnedModelRoots().size() == 1);
    
    SystemEngineering eng = ((SystemEngineering) (project.getOwnedModelRoots().get(0)));
    assertTrue(eng.eContents().size() == 5);

  }

}
