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
package org.polarsys.capella.test.diagram.tools.ju.xab;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.sirius.analysis.queries.QueryIdentifierConstants;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

public class ReuseActors extends XABDiagramsProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    String [] saReuseIds = {A_1, A_2};
    String [] laReuseIds = {LA_1, LA_2};
    
    testOnXAB(context, SA__SAB_DIAGRAM, BlockArchitectureExt.Type.SA, saReuseIds);
    testOnXAB(context, LA__LAB_DIAGRAM, BlockArchitectureExt.Type.LA, laReuseIds);

    testOnComponent(context, SA__SAB_DIAGRAM, SA__SAB_A1, BlockArchitectureExt.Type.SA, A_2, A_3, A_4);
    testOnComponent(context, LA__LAB_DIAGRAM, LA__LAB_A1, BlockArchitectureExt.Type.LA, LA_2, LA_3, LA_4, LA_5, LA_7, LA_7_1, LA_7_1_1);
  }

  public void testOnXAB(SessionContext context, String diagramName, BlockArchitectureExt.Type type, String ...reuseIds) {
    XABDiagram diagram = XABDiagram.openDiagram(context, diagramName, type);
    context.setReusableComponents(PROJECT_APPROACH_ID);
    
    diagram.reuseActor(diagram.getDiagramId(), reuseIds);
    
    context.setSingletonComponents(PROJECT_APPROACH_ID);
  }
  
  public void testOnComponent(SessionContext context, String diagramName, String partId, BlockArchitectureExt.Type type, String ...reuseIds) {
    Part part = context.getSemanticElement(partId);
    List<Object> actorsToInsert = QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_AB_INSERT_ACTOR_FOR_LIB, part.getAbstractType());
    assertTrue(actorsToInsert.size() == reuseIds.length);
    List<String> actorIds = actorsToInsert.stream().map(Component.class::cast).map(actor -> actor.getId()).collect(Collectors.toList());
    for (String id : reuseIds) {
      assertTrue(actorIds.contains(id));
    }
    
    XABDiagram diagram = XABDiagram.openDiagram(context, diagramName, type);
    context.setReusableComponents(PROJECT_APPROACH_ID);
    
    diagram.reuseActor(partId, reuseIds);
    
    context.setSingletonComponents(PROJECT_APPROACH_ID);
  }
}
