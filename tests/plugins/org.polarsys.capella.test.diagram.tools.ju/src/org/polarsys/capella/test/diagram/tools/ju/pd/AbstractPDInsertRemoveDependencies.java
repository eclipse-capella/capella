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

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.diagram.common.ju.context.PDDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.DiagramToolsModel;
import org.polarsys.capella.test.framework.context.SessionContext;

public class AbstractPDInsertRemoveDependencies extends DiagramToolsModel {

  protected String diagramName;
  
  protected String superPackage;
  protected String subPackage1;
  protected String subPackage2;

  @Override
  public void test() throws Exception {

    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    
    PDDiagram pd = PDDiagram.openDiagram(context, diagramName);
    
    pd.removeDependencies(superPackage, subPackage1, subPackage2);    
    pd.insertDependencies(superPackage, subPackage1, subPackage2);
    
    pd.removeDependencies(subPackage1, superPackage); 
    pd.insertDependencies(subPackage1, superPackage);
    
    pd.removeDependencies(subPackage2, superPackage); 
    pd.insertDependencies(subPackage2, superPackage);  
  }
}

