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
package org.polarsys.capella.test.diagram.misc.ju.testcases;

import java.util.Arrays;
import java.util.List;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.style.FlatContainerStyleDescription;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.description.UserFixedColor;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.test.diagram.common.ju.context.PABDiagram;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.TestHelper;

public class PABStyleChecksDiagramElements extends BasicTestCase {

  private static final String PROJECT_NAME = "component-breakdown";


  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(PROJECT_NAME);
  }

  @Override
  public void test() throws Exception {
    Session session = getSession(PROJECT_NAME);
    assertNotNull(session);

    String systemId = "d09017b2-062e-4726-91e9-faeab75b6a56";

    SessionContext context = new SessionContext(session);
    PABDiagram diagram = PABDiagram.createDiagram(context, systemId);

    String comp1_id = diagram.createNodeComponent("node_c1", diagram.getDiagramId());
    String comp2_id = diagram.createNodeComponent("node_c2", comp1_id);
    String comp3_id = diagram.createDeployedBehaviorComponent("depl_behavior_c3", comp1_id);
    String comp4_id = diagram.createDeployedBehaviorComponent("depl_behavior_c4", comp1_id);
    String comp5_id = diagram.createBehaviorComponent("behavior_c5", comp3_id);
    String comp6_id = diagram.createBehaviorComponent("behavior_c6", comp3_id);

    String actor1_id = diagram.createActor("actor1", comp1_id);
    String actor2_id = diagram.createBehaviorComponent("actor2", comp3_id);
    String actor3_id = diagram.createDeployedBehaviorComponent("actor3", comp1_id);
    String actor4_id = diagram.createDeployedBehaviorComponent("actor4", comp1_id);

    Part component1 = context.getSemanticElement(comp1_id);
    Part component2 = context.getSemanticElement(comp2_id);
    Part component3 = context.getSemanticElement(comp3_id);
    Part component4 = context.getSemanticElement(comp4_id);
    Part component5 = context.getSemanticElement(comp5_id);
    Part component6 = context.getSemanticElement(comp6_id);
    Part actor1 = context.getSemanticElement(actor1_id);
    Part actor2 = context.getSemanticElement(actor2_id);
    Part actor3 = context.getSemanticElement(actor3_id);
    Part actor4 = context.getSemanticElement(actor4_id);

    checksOnComponent(component1, false, false, PhysicalComponentNature.NODE);
    checksOnComponent(component2, false, false, PhysicalComponentNature.NODE);
    checksOnComponent(component3, false, false, PhysicalComponentNature.BEHAVIOR);
    checksOnComponent(component4, false, false, PhysicalComponentNature.BEHAVIOR);
    checksOnComponent(component5, false, false, PhysicalComponentNature.BEHAVIOR);
    checksOnComponent(actor1, true, false, PhysicalComponentNature.NODE);
    checksOnComponent(actor2, false, false, PhysicalComponentNature.BEHAVIOR);
    checksOnComponent(actor3, false, false, PhysicalComponentNature.BEHAVIOR);
    checksOnComponent(actor4, false, false, PhysicalComponentNature.BEHAVIOR);

    setFlagsOnComponent(context, component2, false, false, PhysicalComponentNature.UNSET);
    setFlagsOnComponent(context, component4, false, false, PhysicalComponentNature.NODE);
    setFlagsOnComponent(context, component6, false, false, PhysicalComponentNature.NODE);

    setFlagsOnComponent(context, actor2, true, false, PhysicalComponentNature.NODE);
    setFlagsOnComponent(context, actor3, true, false, PhysicalComponentNature.NODE);

    checksStyleOnComponent(diagram, component1, false);
    checksStyleOnComponent(diagram, component2, false);
    checksStyleOnComponent(diagram, component3, true);
    checksStyleOnComponent(diagram, component4, true);
    checksStyleOnComponent(diagram, component5, false);
    checksStyleOnComponent(diagram, component6, false);

    checksStyleOnComponent(diagram, actor1, false);
    checksStyleOnComponent(diagram, actor2, false);
    checksStyleOnComponent(diagram, actor3, true);
    checksStyleOnComponent(diagram, actor4, true);
  }

  protected void setFlagsOnComponent(SessionContext context, Part part, boolean isActor,
      boolean isHuman, PhysicalComponentNature nature) {
    PhysicalComponent component = (PhysicalComponent) part.getAbstractType();
    TestHelper.getExecutionManager(context.getSession()).execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        component.setHuman(isHuman);
        component.setActor(isActor);
        component.setNature(nature);
      }
    });
  }

  protected void checksOnComponent(Part part, boolean isActor, boolean isHuman,
      PhysicalComponentNature nature) {
    PhysicalComponent component = (PhysicalComponent) part.getAbstractType();
    assertTrue("Component " + component.getName() + " isActor should be " + isActor, component.isActor() == isActor);
    assertTrue("Component " + component.getName() + " isHuman should be " + isActor, component.isHuman() == isHuman);
    assertTrue("Component " + component.getName() + " nature should be " + nature, component.getNature() == nature);
  }

  protected void checksStyleOnComponent(PABDiagram diagram, Part part, boolean isRounded) {
    PhysicalComponent component = (PhysicalComponent) part.getAbstractType();
    DDiagramElement element = diagram.getView(part);
    Style style = element.getStyle();

    FlatContainerStyleDescription desc = (FlatContainerStyleDescription) style.getDescription();
    assertTrue(desc.isRoundedCorner() == isRounded);
    UserFixedColor color = (UserFixedColor) desc.getBackgroundColor();
    String colorStr = color.getName();
    if (component.isActor()) {
      assertTrue(colorStr.equals("_CAP_Actor_Blue_min"));
    }
    else {
      if (component.getNature() == PhysicalComponentNature.NODE) {
        assertTrue(colorStr.equals("_CAP_Node_Yellow_min"));
      }
      else if (component.getNature() == PhysicalComponentNature.BEHAVIOR) {
        assertTrue(colorStr.equals("_CAP_Component_Blue_min"));
      }
      else if (component.getNature() == PhysicalComponentNature.UNSET) {
        assertTrue(colorStr.equals("_CAP_Unset_Gray_min"));
      }
    }
  }
}
