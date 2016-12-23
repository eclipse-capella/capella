/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.sdfb;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.XDFBDiagram;
import org.polarsys.capella.test.diagram.common.ju.step.tools.DragAndDropTool;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public class DnDWithInternalFE extends EmptyProject {
  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    final SessionContext context = new SessionContext(session);

    // Create a SDFB diagram
    XDFBDiagram sdfbDiagram = XDFBDiagram.createDiagram(context, SA__ROOT_SF);
    
    // Create 3 SFs
    sdfbDiagram.createFunction(GenericModel.FUNCTION_1);
    sdfbDiagram.createFunction(GenericModel.FUNCTION_2);
    sdfbDiagram.createFunction(GenericModel.FUNCTION_3);

    // Create a FE SF1 -> SF2
    sdfbDiagram.createFunctionalExchange(GenericModel.FUNCTIONAL_EXCHANGE_1, GenericModel.FUNCTION_1, GenericModel.FUNCTION_2);
    // Create a FE SF1 -> SF3
    sdfbDiagram.createFunctionalExchange(GenericModel.FUNCTIONAL_EXCHANGE_2, GenericModel.FUNCTION_1, GenericModel.FUNCTION_3);
    
    assertNotNull("FE expected between Function 1 and Function 2", sdfbDiagram.getView(GenericModel.FUNCTIONAL_EXCHANGE_1));
    assertNotNull("FE expected between Function 1 and Function 3", sdfbDiagram.getView(GenericModel.FUNCTIONAL_EXCHANGE_2));
    
    // DnD SF2 in SF1 so we have an internal FE between them
    new DragAndDropTool(sdfbDiagram, IToolNameConstants.TOOL_SDFB_DND_SYSTEM_FUNCTION, GenericModel.FUNCTION_2, GenericModel.FUNCTION_1).run();

    assertNull("No FE expected between Function 1 and Function 2 (internal exchange)", sdfbDiagram.getView(GenericModel.FUNCTIONAL_EXCHANGE_1));
    assertNotNull("FE expected between Function 1 and Function 3", sdfbDiagram.getView(GenericModel.FUNCTIONAL_EXCHANGE_2));
  }
}
