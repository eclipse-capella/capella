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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.junit.Assert;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.Type;
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

    testOnXAB(context, SA__SAB_DIAGRAM, Type.SA, getValidLinks(Type.SA), getInvalidLinks(Type.SA),
        getFailedLinks(Type.SA));
    testOnXAB(context, LA__LAB_DIAGRAM, Type.LA, getValidLinks(Type.LA), getInvalidLinks(Type.LA),
        getFailedLinks(Type.LA));
    testOnXAB(context, PA__PAB_DIAGRAM, Type.PA, getValidLinks(Type.PA), getInvalidLinks(Type.PA),
        getFailedLinks(Type.PA));
  }

  public void testOnXAB(SessionContext context, String diagramName, Type type, List<LinkInfo> validLinks,
      List<LinkInfo> invalidLinks, List<LinkInfo> failedLinks) {
    XABDiagram diagram = XABDiagram.openDiagram(context, diagramName, type);

    for (LinkInfo linkInfo : validLinks) {
      String linkId = GenericModel.PHYSICAL_LINK_1 + linkInfo.source;
      diagram.createPhysicalLink(linkInfo.source, linkInfo.target, linkId);
      DSemanticDecorator link = diagram.getView(linkId);
      EObject expected = diagram.getSessionContext().getSemanticElement(linkInfo.expectedContainer);
      EObject actual = link.getTarget().eContainer();
      Assert.assertEquals("expected container of PL is " + linkInfo.expectedContainer, expected, actual);
    }

    for (LinkInfo linkInfo : invalidLinks) {
      String linkId = GenericModel.PHYSICAL_LINK_1 + linkInfo.source;
      diagram.cannotCreatePhysicalLink(linkInfo.source, linkInfo.target, linkId);
    }

    for (LinkInfo linkInfo : failedLinks) {
      diagram.creationPhysicalLinkFail(linkInfo.source, linkInfo.target);
    }

  }

  protected List<LinkInfo> getValidLinks(Type type) {
    List<LinkInfo> validLinks = new ArrayList<>();
    switch (type) {
    case SA:
      validLinks.add(new LinkInfo(SA__SAB_A1, SA__SAB_A2, SA_STRUCTURE));
      validLinks.add(new LinkInfo(SA__SAB_A1, SA__SAB_A1, A_1));
      validLinks.add(new LinkInfo(SA__SAB_PHYSICAL_PORT_A1_PP1, SA__SAB_A1, A_1));
      validLinks.add(new LinkInfo(SA__SAB_A1, SA__SAB_SYSTEM_PART, SA_STRUCTURE));
      validLinks.add(new LinkInfo(SA__SAB_SYSTEM_PART, SA__SAB_A1, SA_STRUCTURE));
      break;
    case LA:
      validLinks.add(new LinkInfo(LA__LAB_A1, LA__LAB_A2, LA_STRUCTURE));
      validLinks.add(new LinkInfo(LA__LAB_A1, LA__LAB_A1, LA_1));
      validLinks.add(new LinkInfo(LA__LAB_PHYSICAL_PORT_A1_PP1, LA__LAB_A1, LA_1));
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
      validLinks.add(new LinkInfo(PA__PAB_COMPONENT_PC1, PA__PAB_COMPONENT_PC1, PA__PAB_PHYSICAL_COMPONENT_PC1));
      validLinks.add(new LinkInfo(PA__PORT_PP2, PA__PAB_COMPONENT_PC1, PA__PAB_PHYSICAL_COMPONENT_PC1));
      validLinks.add(new LinkInfo(PA__PORT_PP2, PA__PORT_PP1, PA__PAB_PHYSICAL_COMPONENT_PC1));
      validLinks.add(new LinkInfo(PA__PORT_PP2, PA__PORT_PP1, PA__PAB_PHYSICAL_COMPONENT_PC1));
      validLinks.add(new LinkInfo(PA__PAB_PHYSICAL_ACTOR1, PA__PAB_COMPONENT_PC1, PA_STRUCTURE));
      break;
    default:
      break;
    }
    return validLinks;
  }

  protected List<LinkInfo> getInvalidLinks(Type type) {
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
      invalidLinks.add(new LinkInfo(LC_1_PART, LA__LAB_A2, LA_STRUCTURE));
      break;
    case PA:
      invalidLinks.add(new LinkInfo(PA__PAB_COMPONENT_PC3, PA__PAB_COMPONENT_PC2, PA__PAB_PHYSICAL_SYSTEM));
      invalidLinks.add(new LinkInfo(PA__PAB_COMPONENT_PC3, PA__PAB_COMPONENT_PC3, PA__PAB_COMPONENT_PC3));
      break;
    default:
      break;
    }
    return invalidLinks;
  }

  private List<LinkInfo> getFailedLinks(Type type) {
    List<LinkInfo> failedLinks = new ArrayList<>();
    switch (type) {
    case SA:
      failedLinks.add(new LinkInfo(SA__SAB_FUNCTION_1, SA__SAB_A2, SA_STRUCTURE));
      break;
    case LA:
      failedLinks.add(new LinkInfo(LA__LAB_FUNCTION_3, LA__LAB_A1, LA_STRUCTURE));
      break;
    default:
      break;
    }
    return failedLinks;
  }
}
