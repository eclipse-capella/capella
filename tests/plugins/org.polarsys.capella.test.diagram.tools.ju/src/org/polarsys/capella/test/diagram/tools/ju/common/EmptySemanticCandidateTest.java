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
package org.polarsys.capella.test.diagram.tools.ju.common;

import java.util.List;
import java.util.Set;

import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.junit.Assert;
import org.polarsys.capella.core.sirius.analysis.tool.StringUtil;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;

public class EmptySemanticCandidateTest extends AbstractDiagramTestCase {

  @Override
  protected String getRequiredTestModel() {
    return "In-Flight Entertainment System";
  }

  @Override
  public void test() throws Exception {
    Set<Viewpoint> viewpoints = ViewpointRegistry.getInstance().getViewpoints();
    viewpoints.forEach(this::testViewPointEmptySemanticCandidates);

  }

  private void testViewPointEmptySemanticCandidates(Viewpoint viewpoint) {
    List<RepresentationDescription> representations = viewpoint.getOwnedRepresentations();
    representations.stream().filter(DiagramDescription.class::isInstance)
        .forEach(this::testRepresentationEmptySemanticCandidates);
  }

  private void testRepresentationEmptySemanticCandidates(RepresentationDescription representation) {
    DiagramDescription representationDescription = ((DiagramDescription) representation);

    testEdgeMappingsEmptySemanticCandidates(representationDescription);
    testContainerMappingsEmptySemanticCandidates(representationDescription);
    testNodeMappingsEmptySemanticCandidates(representationDescription);

  }

  private void testEdgeMappingsEmptySemanticCandidates(DiagramDescription representationDescription) {
    representationDescription.getAllEdgeMappings().stream()
        .filter(em -> em.getDomainClass() != null && StringUtil.isEmpty(em.getSemanticCandidatesExpression()))
        .forEach(em -> Assert.fail(" the mapping " + em.getName() + " have a an Empty Semantic candidates"));

  }

  private void testContainerMappingsEmptySemanticCandidates(DiagramDescription representationDescription) {
    representationDescription.getContainerMappings().stream()
        .filter(cm -> cm.getDomainClass() != null && StringUtil.isEmpty(cm.getSemanticCandidatesExpression()))
        .forEach(cm -> Assert.fail(" the mapping " + cm.getName() + " have a an Empty Semantic candidates"));

  }

  private void testNodeMappingsEmptySemanticCandidates(DiagramDescription representationDescription) {
    representationDescription.getNodeMappings().stream()
        .filter(nm -> nm.getDomainClass() != null && StringUtil.isEmpty(nm.getSemanticCandidatesExpression()))
        .forEach(nm -> Assert.fail(" the mapping " + nm.getName() + " have a an Empty Semantic candidates"));

  }

}
