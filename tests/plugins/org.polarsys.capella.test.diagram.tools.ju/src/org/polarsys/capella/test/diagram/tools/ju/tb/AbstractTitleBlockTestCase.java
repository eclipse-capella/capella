/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.tb;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.CommonDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

public abstract class AbstractTitleBlockTestCase extends AbstractDiagramTestCase {
  protected Session session;
  protected SessionContext context;
  protected CommonDiagram diagram;
  
  @Override
  protected String getRequiredTestModel() {
    return "TitleBlocksModel";
  }
  
  @Override
  public void test() throws Exception {
    initTest();
    testTitleBlocks();
  }

  public abstract void testTitleBlocks();
  protected abstract CommonDiagram initDiagram();
  
  protected void initTest() {
    session = getSession(getRequiredTestModel());
    context = new SessionContext(session);
    diagram = initDiagram();
  }
 }
