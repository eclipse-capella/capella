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

import java.util.Collection;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;

import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.context.SessionContext;

public class ReuseActors extends XABDiagramsProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    String [] saReuseIds = {SA__SAB_A1, SA__SAB_A2};
    String [] laReuseIds = {LA__LAB_A1, LA__LAB_A2};
    
    testOnXAB(context, SA__SAB_DIAGRAM, BlockArchitectureExt.Type.SA, saReuseIds);
    testOnXAB(context, LA__LAB_DIAGRAM, BlockArchitectureExt.Type.LA, laReuseIds);
  }

  public void testOnXAB(SessionContext context, String diagramName, BlockArchitectureExt.Type type, String ...reuseIds) {
    XABDiagram diagram = XABDiagram.openDiagram(context, diagramName, type);
    Collection<DDiagramElement> elemsBefore = DiagramHelper.getDiagramElements(diagram.getDiagram());
    
    context.setReusableComponents(PROJECT_APPROACH_ID);
    
    diagram.reuseActor(diagram.getDiagramId(), reuseIds);
    
    context.setSingletonComponents(PROJECT_APPROACH_ID);
    
    Collection<DDiagramElement> elemsAfterReuse = DiagramHelper.getDiagramElements(diagram.getDiagram());
    assertTrue("Part elements must be inserted", elemsAfterReuse.size() >= (elemsBefore.size() + reuseIds.length));
  }
  
  
}
