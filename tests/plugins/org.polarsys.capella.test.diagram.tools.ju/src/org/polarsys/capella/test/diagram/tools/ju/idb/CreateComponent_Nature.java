/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.idb;

import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.IDBDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.TestHelper;
import org.polarsys.capella.test.framework.model.GenericModel;

public class CreateComponent_Nature extends EmptyProject {

  public void test() throws Exception {

    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    init(context);

    testOn(context, IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME, PA__PHYSICAL_SYSTEM, true);
    testOnInternal(context, IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME,
        GenericModel.PC_1, true);
    testOnExternal(context, IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME, GenericModel.PC_1,
        false);

  }

  private void init(SessionContext context) {
    // Create an NODE and a BEHAVIOR
    IDBDiagram idb = IDBDiagram.createDiagram(context, IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME,
        PA__PHYSICAL_SYSTEM);
    idb.createComponent(GenericModel.BEHAVIOR_1);
    idb.createComponent(GenericModel.NODE_1);
    setNature(context, GenericModel.NODE_1, PhysicalComponentNature.NODE);
    idb.createComponent(GenericModel.NODE_1, GenericModel.NODE_1_1);
  }

  public void testOn(SessionContext context, String diagramKind, String root, boolean checkSubComponents)
      throws Exception {

    IDBDiagram idb;

    idb = IDBDiagram.createDiagram(context, diagramKind, PA__PHYSICAL_SYSTEM);

    // Create an IDB on ROOT

    // Component on blank is BEHAVIOR
    idb.createComponent(GenericModel.BEHAVIOR_2);
    checkNature(context, GenericModel.BEHAVIOR_2, PhysicalComponentNature.BEHAVIOR);

    // Component on NODE is NODE
    idb.createComponent(GenericModel.NODE_2);
    setNature(context, GenericModel.NODE_2, PhysicalComponentNature.NODE);
    idb.createComponent(GenericModel.NODE_2, GenericModel.NODE_2_1);
    checkNature(context, GenericModel.NODE_2_1, PhysicalComponentNature.NODE);

    // Component on BEHAVIOR is BEHAVIOR
    idb.createComponent(GenericModel.BEHAVIOR_2, GenericModel.BEHAVIOR_2_1);
    checkNature(context, GenericModel.BEHAVIOR_2_1, PhysicalComponentNature.BEHAVIOR);

    // Create an IDB on BEHAVIOR

    // Component on blank is BEHAVIOR
    idb = IDBDiagram.createDiagram(context, diagramKind, GenericModel.BEHAVIOR_1);
    idb.createComponent(GenericModel.BEHAVIOR_1_1);
    checkNature(context, GenericModel.BEHAVIOR_1_1, PhysicalComponentNature.BEHAVIOR);

    // Create an IDB on NODE

    // Component on blank is NODE
    idb = IDBDiagram.createDiagram(context, diagramKind, GenericModel.NODE_1);
    idb.createComponent(GenericModel.NODE_1_1);
    checkNature(context, GenericModel.NODE_1_1, PhysicalComponentNature.NODE);

  }

  public void testOnInternal(SessionContext context, String diagramKind, String root, boolean checkSubComponents)
      throws Exception {

    IDBDiagram cii;

    // Create an CII on ROOT
    cii = IDBDiagram.createDiagram(context, diagramKind, PA__PHYSICAL_SYSTEM);

    // Component on Physical System is BEHAVIOR
    cii.createComponent(PA__PHYSICAL_SYSTEM, GenericModel.BEHAVIOR_2);
    checkNature(context, GenericModel.BEHAVIOR_2, PhysicalComponentNature.BEHAVIOR);

    // Component on NODE is NODE
    cii.createComponent(PA__PHYSICAL_SYSTEM, GenericModel.NODE_2);
    setNature(context, GenericModel.NODE_2, PhysicalComponentNature.NODE);
    cii.createComponent(GenericModel.NODE_2, GenericModel.NODE_2_1);
    checkNature(context, GenericModel.NODE_2_1, PhysicalComponentNature.NODE);

    // Component on BEHAVIOR is BEHAVIOR
    cii.createComponent(GenericModel.BEHAVIOR_2, GenericModel.BEHAVIOR_2_1);
    checkNature(context, GenericModel.BEHAVIOR_2_1, PhysicalComponentNature.BEHAVIOR);

    // Create an CII on BEHAVIOR
    
    // Component on Diagram is BEHAVIOR
    cii = IDBDiagram.createDiagram(context, diagramKind, GenericModel.BEHAVIOR_1);
    cii.createComponent(GenericModel.BEHAVIOR_1_1);
    checkNature(context, GenericModel.BEHAVIOR_1_1, PhysicalComponentNature.BEHAVIOR);

    // Create an CII on NODE
    cii = IDBDiagram.createDiagram(context, diagramKind, GenericModel.NODE_1);

    // Component on Diagram is BEHAVIOR (since NODE_1 is located inside PhysicalSystem)
    cii.createComponent(GenericModel.PC_1);
    checkNature(context, GenericModel.PC_1, PhysicalComponentNature.BEHAVIOR);

    // Component on NODE is NODE
    cii.createComponent(GenericModel.NODE_1, GenericModel.PC_1);
    checkNature(context, GenericModel.PC_1, PhysicalComponentNature.NODE);

    // Create an CII on NODE inside a NODE
    cii = IDBDiagram.createDiagram(context, diagramKind, GenericModel.NODE_1_1);

    // Component on Diagram is NODE (since root is located inside NODE NODE_1)
    cii.createComponent(GenericModel.PC_1);
    checkNature(context, GenericModel.PC_1, PhysicalComponentNature.NODE);

  }

  public void testOnExternal(SessionContext context, String diagramKind, String root, boolean checkSubComponents)
      throws Exception {

    IDBDiagram cei;

    // Create an CEI on BEHAVIOR, component must be BEHAVIOR
    cei = IDBDiagram.createDiagram(context, diagramKind, GenericModel.BEHAVIOR_1);

    // Component on Diagram is BEHAVIOR
    cei.createComponent(GenericModel.BEHAVIOR_1_1);
    checkNature(context, GenericModel.BEHAVIOR_1_1, PhysicalComponentNature.BEHAVIOR);

    // Create an CEI on NODE
    cei = IDBDiagram.createDiagram(context, diagramKind, GenericModel.NODE_1);

    // Component on Diagram is BEHAVIOR (since root is located inside PhysicalSystem)
    cei.createComponent(GenericModel.BEHAVIOR_2);
    checkNature(context, GenericModel.BEHAVIOR_2, PhysicalComponentNature.BEHAVIOR);

    // Create an CEI on NODE inside a NODE
    cei = IDBDiagram.createDiagram(context, diagramKind, GenericModel.NODE_1_1);

    // Component on Diagram is BEHAVIOR (since root is located inside NODE_1)
    cei.createComponent(GenericModel.PC_1);
    checkNature(context, GenericModel.PC_1, PhysicalComponentNature.NODE);

  }

  private void checkNature(SessionContext context, String id, PhysicalComponentNature nature) {
    PhysicalComponent component = context.getSemanticElement(id);
    assertTrue(NLS.bind("Component ''{0}'' must be ''{1}''", id, nature), component.getNature() == nature);
  }

  public void setNature(final SessionContext session, final String component, final PhysicalComponentNature nature) {
    TestHelper.getExecutionManager(session.getSession()).execute(new AbstractReadWriteCommand() {
      public void run() {
        ((PhysicalComponent) (session.getSemanticElement(component))).setNature(nature);
      }
    });
  }

}
