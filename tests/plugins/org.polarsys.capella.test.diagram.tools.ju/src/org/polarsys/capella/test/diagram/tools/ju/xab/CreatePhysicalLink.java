/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.xab;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public class CreatePhysicalLink extends XABDiagramsProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    
    testOnXAB(context, SA__SAB_DIAGRAM, BlockArchitectureExt.Type.SA, getValidMap(BlockArchitectureExt.Type.SA),
        getInvalidMap(BlockArchitectureExt.Type.SA));
    testOnXAB(context, LA__LAB_DIAGRAM, BlockArchitectureExt.Type.LA,
        getValidMap(BlockArchitectureExt.Type.LA), getInvalidMap(BlockArchitectureExt.Type.LA));
    testOnXAB(context, PA__PAB_DIAGRAM, BlockArchitectureExt.Type.PA, getValidMap(BlockArchitectureExt.Type.PA),
        getInvalidMap(BlockArchitectureExt.Type.PA));
  }

  public void testOnXAB(SessionContext context, String diagramName, BlockArchitectureExt.Type type,
      Map<String, String> validLinks, Map<String, String> invalidLinks) {
    XABDiagram diagram = XABDiagram.openDiagram(context, diagramName, type);

    for (String key : validLinks.keySet()) {
      diagram.createPhysicalLink(key, validLinks.get(key), GenericModel.PHYSICAL_LINK_1 + key);
    }

    for (String key : invalidLinks.keySet()) {
      diagram.cannotCreatePhysicalLink(key, invalidLinks.get(key), GenericModel.PHYSICAL_LINK_1 + key);
    }
  }
  
  protected Map<String, String> getValidMap(BlockArchitectureExt.Type type) {
    Map<String, String> validLinks = new HashMap<String, String>();

    switch (type) {
    case SA:
      validLinks.put(SA__SAB_A1, SA__SAB_A2);
      break;
    case LA:
      validLinks.put(LA__LAB_A1, LA__LAB_A2);
      validLinks.put(LA__LAB_LOGICAL_SYSTEM_PART, LA__LAB_A1);
      validLinks.put(LA__LAB_A2, LA__LAB_LOGICAL_SYSTEM_PART);
      break;
    case PA:
      validLinks.put(PA__PAB_COMPONENT_PC1, PA__PAB_COMPONENT_PC2);
      break;
    default:
      break;
    }
    return validLinks;
  }

  protected Map<String, String> getInvalidMap(BlockArchitectureExt.Type type) {
    Map<String, String> invalidLinks = new HashMap<String, String>();
    switch (type) {
    case LA:
      invalidLinks.put(LA__LAB_LOGICAL_SYSTEM_PART, LA__LAB_LC1_PART);
      invalidLinks.put(LA__LAB_LC1_PART, LA__LAB_LOGICAL_SYSTEM_PART);
      invalidLinks.put(LA__LAB_LC1_PART, LA__LAB_A1);
      invalidLinks.put(LA__LAB_A2, LA__LAB_LC1_PART);
      invalidLinks.put(LA_7_1_PART, LA__LAB_A1);
      break;
    default:
      break;
    }
    return invalidLinks;
  }
}
