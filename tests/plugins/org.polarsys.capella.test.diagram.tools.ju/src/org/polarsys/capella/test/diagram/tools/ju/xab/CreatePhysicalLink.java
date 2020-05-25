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
package org.polarsys.capella.test.diagram.tools.ju.xab;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public class CreatePhysicalLink extends XABDiagramsProject {

  private class LinkInfo {
    String source;
    String target;
    String expectedContainer;
    public LinkInfo(String source, String target, String expectedContainer) {
      this.source = source;
      this.target = target;
      this.expectedContainer = expectedContainer;
    }
  }
  
  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    
    testOnXAB(context, SA__SAB_DIAGRAM, BlockArchitectureExt.Type.SA, getValidLinks(BlockArchitectureExt.Type.SA),
        getInvalidLinks(BlockArchitectureExt.Type.SA));
    testOnXAB(context, LA__LAB_DIAGRAM, BlockArchitectureExt.Type.LA,
        getValidLinks(BlockArchitectureExt.Type.LA), getInvalidLinks(BlockArchitectureExt.Type.LA));
    testOnXAB(context, PA__PAB_DIAGRAM, BlockArchitectureExt.Type.PA, getValidLinks(BlockArchitectureExt.Type.PA),
        getInvalidLinks(BlockArchitectureExt.Type.PA));
  }

  public void testOnXAB(SessionContext context, String diagramName, BlockArchitectureExt.Type type,
      List<LinkInfo> validLinks, List<LinkInfo> invalidLinks) {
    XABDiagram diagram = XABDiagram.openDiagram(context, diagramName, type);

    for (LinkInfo linkInfo : validLinks) {
      String linkId = GenericModel.PHYSICAL_LINK_1 + linkInfo.source;
      diagram.createPhysicalLink(linkInfo.source, linkInfo.target, linkId);
      DSemanticDecorator link = diagram.getView(linkId);
      EObject expected = diagram.getSessionContext().getSemanticElement(linkInfo.expectedContainer);
      EObject actual = link.getTarget().eContainer();
      assertEquals(expected, actual);
    }

    for (LinkInfo linkInfo : invalidLinks) {
      String linkId = GenericModel.PHYSICAL_LINK_1 + linkInfo.source;
      diagram.cannotCreatePhysicalLink(linkInfo.source, linkInfo.target, linkId);
    }
  }
  
  protected List<LinkInfo> getValidLinks(BlockArchitectureExt.Type type) {
    List<LinkInfo> validLinks = new ArrayList<>();
    switch (type) {
    case SA:
      validLinks.add(new LinkInfo(SA__SAB_A1, SA__SAB_A2, SA_STRUCTURE));
      validLinks.add(new LinkInfo(SA__SAB_A1, SA__SAB_SYSTEM_PART, SA_STRUCTURE));
      break;
    case LA:
      validLinks.add(new LinkInfo(LA__LAB_A1, LA__LAB_A2, LA_STRUCTURE));
      validLinks.add(new LinkInfo(LA__LAB_LOGICAL_SYSTEM_PART, LA__LAB_A1, LA_STRUCTURE));
      validLinks.add(new LinkInfo(LA__LAB_A2, LA__LAB_LOGICAL_SYSTEM_PART, LA_STRUCTURE));
      validLinks.add(new LinkInfo(LA__LAB_A2, LA_7_1_PART, LA_STRUCTURE));
      validLinks.add(new LinkInfo(LA_7_1_PART, LA__LAB_A2, LA_STRUCTURE));
      validLinks.add(new LinkInfo(LA_7_1_PART, LA__LAB_LOGICAL_SYSTEM_PART, LA_STRUCTURE));
      validLinks.add(new LinkInfo(LA__LAB_LOGICAL_SYSTEM_PART, LA_7_1_PART, LA_STRUCTURE));
      validLinks.add(new LinkInfo(LA_7_1_PART, LA__LAB_A1, LA_STRUCTURE));
      validLinks.add(new LinkInfo(LA_LA_7_1_1_PART, LA__LAB_A1, LA_STRUCTURE));
      validLinks.add(new LinkInfo(LA_LA_7_1_1_PART, LA_7_1_PART, LA_7_1));
      validLinks.add(new LinkInfo(LA_LA_7_1_1_PART, LA_7_PART, LA_7));
      break;
    case PA:
      validLinks.add(new LinkInfo(PA__PAB_COMPONENT_PC1, PA__PAB_COMPONENT_PC2, PA__PAB_PHYSICAL_SYSTEM));
      break;
    default:
      break;
    }
    return validLinks;
  }

  protected List<LinkInfo> getInvalidLinks(BlockArchitectureExt.Type type) {
    List<LinkInfo> invalidLinks = new ArrayList<>();
    switch (type) {
    case LA:
      invalidLinks.add(new LinkInfo(LA__LAB_LOGICAL_SYSTEM_PART, LA__LAB_LC1_PART, null));
      invalidLinks.add(new LinkInfo(LA__LAB_LC1_PART, LA__LAB_LOGICAL_SYSTEM_PART, null));
      invalidLinks.add(new LinkInfo(LA__LAB_LC1_PART, LA__LAB_A1, null));
      invalidLinks.add(new LinkInfo(LA__LAB_A2, LA__LAB_LC1_PART, null));
      invalidLinks.add(new LinkInfo(LA_7_1_PART, LA__LAB_LC1_PART, null));
      invalidLinks.add(new LinkInfo(LA_LC_1_1, LA__LAB_A2, null));
      invalidLinks.add(new LinkInfo(LA_LC_1_1, LA__LAB_LC1_PART, null));
      invalidLinks.add(new LinkInfo(LA__LAB_LOGICAL_SYSTEM_PART, LA_LC_1_1, null));
      break;
    default:
      break;
    }
    return invalidLinks;
  }
}
