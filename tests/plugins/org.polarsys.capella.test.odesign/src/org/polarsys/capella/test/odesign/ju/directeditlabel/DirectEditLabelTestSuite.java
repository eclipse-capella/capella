/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.odesign.ju.directeditlabel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.tool.DirectEditLabel;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ODesignHelper;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

public class DirectEditLabelTestSuite extends BasicTestSuite {
  @SuppressWarnings("unchecked")
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<DiagramElementMapping> diagramElementMappings = new ArrayList<DiagramElementMapping>();
    Set<Viewpoint> viewpoints = ViewpointRegistry.getInstance().getViewpoints();
    List<DirectEditLabel> directEditLabels = new ArrayList<DirectEditLabel>();

    for (Viewpoint viewpoint : viewpoints) {
      directEditLabels.addAll((Collection<? extends DirectEditLabel>) StreamSupport
          .stream(Spliterators.spliteratorUnknownSize(viewpoint.eResource().getAllContents(), 0), false)
          .filter(DirectEditLabel.class::isInstance).collect(Collectors.toList()));

      diagramElementMappings.addAll((Collection<? extends DiagramElementMapping>) StreamSupport
          .stream(Spliterators.spliteratorUnknownSize(viewpoint.eResource().getAllContents(), 0), false)
          .filter(DiagramElementMapping.class::isInstance).map(x -> (DiagramElementMapping) x)
          .filter(ODesignHelper::isNotDeprecatedMapping).collect(Collectors.toList()));
    }
    List<BasicTestArtefact> tests = new ArrayList<>();
    tests.add(new CheckDirectEditLabelHasMappingsTest(directEditLabels));
    tests.add(new CheckMappingsHasDirectEditLabelTest(diagramElementMappings));
    return tests;
  }
}
