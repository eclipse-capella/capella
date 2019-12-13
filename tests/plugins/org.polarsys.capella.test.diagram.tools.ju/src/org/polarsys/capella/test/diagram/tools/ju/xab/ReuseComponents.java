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

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.sirius.analysis.queries.QueryIdentifierConstants;
import org.polarsys.capella.test.diagram.common.ju.context.PABDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

public class ReuseComponents extends XABDiagramsProject {

  private static final String NODE = "NODE";
  private static final String BEHAVIOUR = "BEHAVIOUR";

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    String[] laReuseIds = { LA__LAB_LC1 };

    testOnXAB(context, LA__LAB_DIAGRAM, BlockArchitectureExt.Type.LA, LA__LAB_LOGICAL_SYSTEM_PART, laReuseIds);
    testOnPAB(context, PA__PAB_DIAGRAM, NODE, PA__PAB_DIAGRAM, PA__PAB_COMPONENT_PC6);
    testOnPAB(context, PA__PAB_DIAGRAM, BEHAVIOUR, PA__PAB_DIAGRAM, PA__PAB_COMPONENT_PC7);

    testOnComponent(context, LA__LAB_DIAGRAM, LC_1_PART, BlockArchitectureExt.Type.LA, LC_1_1);
    testOnComponent(context, PA__PAB_DIAGRAM, PC_3_PC_1_PART, BlockArchitectureExt.Type.PA, PC_4, PC_4_PC_1, PC_3_1,
        PC_7, PC_10);
  }

  public void testOnXAB(SessionContext context, String diagramName, BlockArchitectureExt.Type type, String containerId,
      String... reuseIds) {
    XABDiagram diagram = XABDiagram.openDiagram(context, diagramName, type);

    context.setReusableComponents(PROJECT_APPROACH_ID);

    diagram.reuseComponent(containerId, reuseIds);

    context.setSingletonComponents(PROJECT_APPROACH_ID);
  }

  public void testOnPAB(SessionContext context, String diagramName, String type, String containerId,
      String... reuseIds) {
    PABDiagram diagram = PABDiagram.openDiagram(context, diagramName);

    context.setReusableComponents(PROJECT_APPROACH_ID);

    if (NODE.equals(type)) {
      diagram.reuseNodeComponent(containerId, reuseIds);
    } else if (BEHAVIOUR.equals(type)) {
      diagram.reuseBehaviourComponent(containerId, reuseIds);
    }

    context.setSingletonComponents(PROJECT_APPROACH_ID);
  }

  public void testOnComponent(SessionContext context, String diagramName, String partId, BlockArchitectureExt.Type type,
      String... reuseIds) {
    Part part = context.getSemanticElement(partId);
    List<Object> componentsToInsert = QueryInterpretor
        .executeQuery(QueryIdentifierConstants.GET_AB_INSERT_COMPONENT_FOR_LIB, part.getAbstractType());
    assertTrue(componentsToInsert.size() == reuseIds.length);
    List<String> componentIds = componentsToInsert.stream().map(Component.class::cast)
        .map(component -> component.getId()).collect(Collectors.toList());
    for (String id : reuseIds) {
      assertTrue(componentIds.contains(id));
    }

    XABDiagram diagram = XABDiagram.openDiagram(context, diagramName, type);
    context.setReusableComponents(PROJECT_APPROACH_ID);

    diagram.reuseComponent(partId, reuseIds);

    context.setSingletonComponents(PROJECT_APPROACH_ID);
  }

}
