/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.IDDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.settings.IDProjectSettings;
import org.polarsys.capella.test.diagram.tools.ju.model.settings.LA_IDProjectSettings;
import org.polarsys.capella.test.diagram.tools.ju.model.settings.OA_IDProjectSettings;
import org.polarsys.capella.test.diagram.tools.ju.model.settings.PA_IDProjectSettings;
import org.polarsys.capella.test.diagram.tools.ju.model.settings.SA_IDProjectSettings;
import org.polarsys.capella.test.framework.context.SessionContext;

public abstract class IDProject extends EmptyProject {
  protected Session session;
  protected SessionContext context;
  protected IDDiagram id;
  protected IDProjectSettings settings;
  protected String interfaceId;
  protected String interfaceId2;

  public IDProject(IDProjectSettings settings) {
    this.settings = settings;
  }

  protected void initTest() {
    session = getSession(getRequiredTestModel());
    context = new SessionContext(session);

    CDBDiagram cdb = CDBDiagram.createDiagram(context, settings.DATAPKG);
    interfaceId = cdb.createInterface();
    interfaceId2 = cdb.createInterface();
    cdb.close();

    id = IDDiagram.createDiagram(context, interfaceId);
  }

  @Override
  public void test() throws Exception {
    initTest();
    testID();
    id.close();
    session.close(new NullProgressMonitor());
  }

  protected abstract void testID();

  @Override
  public String getName() {
    String architecture = "";
    if (settings instanceof OA_IDProjectSettings) {
      architecture = "OA";
    } else if (settings instanceof SA_IDProjectSettings) {
      architecture = "SA";
    } else if (settings instanceof LA_IDProjectSettings) {
      architecture = "LA";
    } else if (settings instanceof PA_IDProjectSettings) {
      architecture = "PA";
    }
    return getClass().getSimpleName() + architecture;
  }

}
