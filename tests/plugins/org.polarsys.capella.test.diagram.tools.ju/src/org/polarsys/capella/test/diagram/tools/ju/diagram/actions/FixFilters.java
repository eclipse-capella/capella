/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.diagram.actions;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.framework.helpers.GuiActions;

import junit.framework.Test;

public class FixFilters extends AbstractDiagramTestCase {
  
  @Override
  public String getRequiredTestModel() {
    return "DiagramAction";
  }
  
  @Override
  public void test() throws Exception {
    Session session = getSessionForTestModel(getRequiredTestModel());
    GuiActions.fixDiagrams(getAirdFileForLoadedModel(getRequiredTestModel()), session);
  }

  public static Test suite() {
    return new FixFilters();
  }
}
