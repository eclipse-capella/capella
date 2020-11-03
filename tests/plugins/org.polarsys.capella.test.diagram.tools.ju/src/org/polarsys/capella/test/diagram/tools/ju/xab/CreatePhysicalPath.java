/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.test.diagram.common.ju.context.PABDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public class CreatePhysicalPath extends EmptyProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testOnActors(context, SA__SYSTEM, SA__SYSTEM_CONTEXT__PART_SYSTEM__SYSTEM);
    testOnActors(context, LA__LOGICAL_SYSTEM, LA__LOGICAL_CONTEXT__PART_LOGICAL_SYSTEM__LOGICAL_SYSTEM);
    testOnPhysicalActors(context, PA__PHYSICAL_SYSTEM);

    // Creation of a Physical Path shall be created into the common ancestor of the PhysicalLink
    // or in the Target of the diagram if possible
    // or in the Architecture.getSystem otherwise
    testOnActorsOnly(context, SA__SYSTEM_CONTEXT, SA__SYSTEM);
    testOnActorsOnly(context, LA__LOGICAL_CONTEXT, LA__LOGICAL_SYSTEM);
    testOnActorsOnly(context, PA__PHYSICAL_CONTEXT, PA__PHYSICAL_SYSTEM);
    testOnActorsOnly(context, SA__SYSTEM, SA__SYSTEM);
    testOnActorsOnly(context, LA__LOGICAL_SYSTEM, LA__LOGICAL_SYSTEM);
    testOnActorsOnly(context, PA__PHYSICAL_SYSTEM, PA__PHYSICAL_SYSTEM);

  }

  private void testOnActorsOnly(SessionContext context, String idSource, String defaultContainer) {
    EObject target = context.getSemanticElement(idSource);
    XABDiagram xab = XABDiagram.createDiagram(context, idSource);
    xab.createActor(GenericModel.LA_1);
    xab.createActor(GenericModel.LA_2);
    xab.createActor(GenericModel.LA_3);

    xab.createPhysicalLink(GenericModel.LA_1, GenericModel.LA_2, GenericModel.CL_1);
    xab.createPhysicalLink(GenericModel.LA_2, GenericModel.LA_3, GenericModel.CL_2);
    PhysicalPath path = xab.createPhysicalPath(GenericModel.PATH_1, GenericModel.CL_1, GenericModel.CL_2);
    if (target instanceof ComponentPkg) {
      assertTrue("Path shall be created into System as it can't be container into diagram target",
          path.eContainer() == context.getSemanticElement(defaultContainer));
    } else if (target instanceof Component) {
      assertTrue("Path shall be created into diagram target as it is a container",
          path.eContainer() == context.getSemanticElement(idSource));
    }

    xab.createActor(GenericModel.LA_1_1, GenericModel.LA_1);
    xab.createActor(GenericModel.LA_1_2, GenericModel.LA_1);
    xab.createActor(GenericModel.LA_1_3, GenericModel.LA_1);
    xab.createPhysicalLink(GenericModel.LA_1_1, GenericModel.LA_1_2, GenericModel.CL_3);
    xab.createPhysicalLink(GenericModel.LA_1_2, GenericModel.LA_1_3, GenericModel.CL_4);
    PhysicalPath path2 = xab.createPhysicalPath(GenericModel.PATH_2, GenericModel.CL_3, GenericModel.CL_4);
    Component actor = (Component) ((Part) context.getSemanticElement(GenericModel.LA_1)).getAbstractType();
    assertTrue("Path shall be created into Common ancestor", path2.eContainer() == actor);
  }

  protected void testOnPhysicalActors(SessionContext context, String idSource) {
    PABDiagram xab = PABDiagram.createDiagram(context, idSource);

    xab.createNodeComponent(GenericModel.NODE_1, xab.getDiagramId());
    xab.createActor(GenericModel.LA_1);
    xab.createActor(GenericModel.LA_2);

    xab.createPhysicalLink(GenericModel.NODE_1, GenericModel.LA_1, GenericModel.CL_1);
    xab.createPhysicalLink(GenericModel.LA_1, GenericModel.LA_2, GenericModel.CL_2);
    xab.hasView(GenericModel.LA_1);
    xab.hasView(GenericModel.LA_2);

    xab.createPhysicalPath(GenericModel.PATH_1, GenericModel.CL_1, GenericModel.CL_2);
  }

  protected void testOnActors(SessionContext context, String idSource, String idSystem) {
    XABDiagram xab = XABDiagram.createDiagram(context, idSource);

    if (xab.getView(idSystem) == null) {
      xab.insertComponent(idSystem, xab.getDiagramId());
    }

    xab.createActor(GenericModel.LA_1);
    xab.createActor(GenericModel.LA_2);

    xab.createPhysicalLink(idSystem, GenericModel.LA_1, GenericModel.CL_1);
    xab.createPhysicalLink(GenericModel.LA_1, GenericModel.LA_2, GenericModel.CL_2);
    xab.hasView(GenericModel.LA_1);
    xab.hasView(GenericModel.LA_2);

    xab.createPhysicalPath(GenericModel.PATH_1, GenericModel.CL_1, GenericModel.CL_2);
  }

}
