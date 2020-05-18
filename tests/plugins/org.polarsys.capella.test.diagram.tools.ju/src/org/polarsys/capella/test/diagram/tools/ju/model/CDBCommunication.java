/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.model;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.settings.CDBProjectSettings;
import org.polarsys.capella.test.diagram.tools.ju.model.settings.LA_CDBProjectSettings;
import org.polarsys.capella.test.diagram.tools.ju.model.settings.OA_CDBProjectSettings;
import org.polarsys.capella.test.diagram.tools.ju.model.settings.PA_CDBProjectSettings;
import org.polarsys.capella.test.diagram.tools.ju.model.settings.SA_CDBProjectSettings;
import org.polarsys.capella.test.framework.context.SessionContext;

public abstract class CDBCommunication extends AbstractDiagramTestCase {
  protected Session session;
  protected SessionContext context;
  protected CDBDiagram cdb;
  protected CDBProjectSettings settings;

  public CDBCommunication(CDBProjectSettings settings) {
    this.settings = settings;
  }

  protected void initTest() {
    session = getSession(getRequiredTestModel());
    context = new SessionContext(session);
  }

  @Override
  public void test() throws Exception {
    initTest();
    testCDB();
    if (cdb != null) {
      cdb.close();
    }
    session.close(new NullProgressMonitor());
  }

  protected abstract void testCDB();

  @Override
  public String getName() {
    String architecture = "";
    if (settings instanceof OA_CDBProjectSettings) {
      architecture = "OA";
    } else if (settings instanceof SA_CDBProjectSettings) {
      architecture = "SA";
    } else if (settings instanceof LA_CDBProjectSettings) {
      architecture = "LA";
    } else if (settings instanceof PA_CDBProjectSettings) {
      architecture = "PA";
    }
    return getClass().getSimpleName() + architecture;
  }

  @Override
  public String getRequiredTestModel() {
    return CDBCommunication.class.getSimpleName();
  }
}
