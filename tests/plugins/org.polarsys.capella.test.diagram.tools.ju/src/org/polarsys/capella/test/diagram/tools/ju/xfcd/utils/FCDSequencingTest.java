/*******************************************************************************
 * Copyright (c) 2019, 2020, THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.xfcd.utils;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.XFCDDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

public abstract class FCDSequencingTest extends AbstractDiagramTestCase {
  public static final String MODEL_NAME = "FunctionalChains";

  protected Session session;
  protected SessionContext context;
  protected XFCDDiagram xfcd;
  protected Settings settings;

  public FCDSequencingTest(Settings settings) {
    this.settings = settings;
  }

  protected void initTest() {
    session = getSession(getRequiredTestModel());
    context = new SessionContext(session);
  }

  @Override
  public void test() throws Exception {
    initTest();

    testLevel0();
    testLevel1();
    testLevel2();
  }

  protected abstract void testLevel0();

  protected abstract void testLevel1();

  protected abstract void testLevel2();

  @Override
  protected String getRequiredTestModel() {
    return MODEL_NAME;
  }

  @Override
  public String getName() {
    String architecture = "";
    if (settings instanceof OA_Settings) {
      architecture = "OA";
    } else if (settings instanceof SA_Settings) {
      architecture = "SA";
    } else if (settings instanceof LA_Settings) {
      architecture = "LA";
    }
    return getClass().getSimpleName() + architecture;
  }
}
