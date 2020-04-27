/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.pd;

import java.util.function.BiConsumer;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.diagram.common.ju.context.PDDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.DiagramToolsModel;
import org.polarsys.capella.test.framework.context.SessionContext;

abstract class AbstractPDInsertRemovePackages extends DiagramToolsModel {

  // Child classes will give values to these variables
  protected String diagramName;

  protected String pkg1ID;
  protected String pkg2ID;
  protected String childPkg1_1ID;
  protected String childPkg1_2ID;
  protected String childPkg2_1ID;
  protected String childPkg2_1_1ID;

  protected String packageType;

  @Override
  public void test() throws Exception {

    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    PDDiagram pd = PDDiagram.openDiagram(context, diagramName);
    String diagramId = pd.getDiagramId();

    // Method references
    BiConsumer<String, String[]> insertMethod = null;
    BiConsumer<String, String[]> removeMethod = null;

    // Depending on package type (data or interface) we will store the appropriate method
    switch (packageType) {

    case "DATA":
      insertMethod = (containerId, elementsToInsert) -> pd.insertDataPackages(containerId, elementsToInsert);
      removeMethod = (containerId, elementsToRemove) -> pd.removeDataPackages(containerId, elementsToRemove);
      break;

    case "INTERFACE":
      insertMethod = (containerId, elementsToInsert) -> pd.insertInterfacePackages(containerId, elementsToInsert);
      removeMethod = (containerId, elementsToRemove) -> pd.removeInterfacePackages(containerId, elementsToRemove);
      break;
    }

    // The accept method calls the stored method
    if (null != insertMethod && null != removeMethod) {

      removeMethod.accept(diagramId, new String[] { childPkg2_1_1ID });

      removeMethod.accept(diagramId, new String[] { childPkg1_1ID, childPkg1_2ID, childPkg2_1ID });

      removeMethod.accept(diagramId, new String[] { pkg1ID, pkg2ID });

      insertMethod.accept(diagramId, new String[] { pkg1ID, pkg2ID });

      insertMethod.accept(diagramId, new String[] { childPkg1_1ID, childPkg1_2ID, childPkg2_1ID });

      insertMethod.accept(diagramId, new String[] { childPkg2_1_1ID });
    }

    else {
      fail("Package type field is not set or is incorrect!");
    }
  }
}
