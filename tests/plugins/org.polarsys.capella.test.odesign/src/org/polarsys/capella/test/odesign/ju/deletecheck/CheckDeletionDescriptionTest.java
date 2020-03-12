/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.odesign.ju.deletecheck;

import java.util.List;
import java.util.Set;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ODesignHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class CheckDeletionDescriptionTest extends BasicTestCase {
  StringBuilder failedTest = new StringBuilder();

  @Override
  public void test() throws Exception {

    Set<Viewpoint> test = ViewpointRegistry.getInstance().getViewpoints();
    for (Viewpoint viewpoint : test) {
      checkMappingsHasDeletionDescription(viewpoint.eResource());
    }

    if (this.failedTest.length() == 0) {
      assertTrue(true);
    } else {
      System.err.println(failedTest.toString());
      assertTrue(this.failedTest.toString(), false);
    }
  }

  protected void checkMappingsHasDeletionDescription(Resource resource) {
    List<EObject> diamap = StreamSupport
        .stream(Spliterators.spliteratorUnknownSize(resource.getAllContents(), 0), false)
        .filter(DiagramElementMapping.class::isInstance).collect(Collectors.toList());
    for (EObject diagramElementMapping : diamap) {
      checkHasDeletionDescription((DiagramElementMapping) diagramElementMapping);
    }
  }

  private void checkHasDeletionDescription(DiagramElementMapping diagramElementMapping) {
    if (diagramElementMapping instanceof AbstractNodeMapping) {
      AbstractNodeMapping nodeMapping = (AbstractNodeMapping) diagramElementMapping;
      if (nodeMapping.getDomainClass() != null) {
        if ("false".equalsIgnoreCase(nodeMapping.getPreconditionExpression())
            || nodeMapping.getPreconditionExpression().isEmpty()
            || ("EObject".equalsIgnoreCase(nodeMapping.getDomainClass()))) {
          System.err.println(
              "Deprecated, No Deletion Description for: " + ODesignHelper.computeModelPath(diagramElementMapping));

        } else {
          if (nodeMapping.getDeletionDescription() == null) {
            this.failedTest
                .append("No Deletion Description for: " + ODesignHelper.computeModelPath(diagramElementMapping) + "\n");
          }
        }

      }
    } else if (diagramElementMapping instanceof EdgeMapping) {
      EdgeMapping edgeMapping = (EdgeMapping) diagramElementMapping;
      if (edgeMapping.isUseDomainElement() && edgeMapping.getDeletionDescription() == null) {
        if ("false".equalsIgnoreCase(edgeMapping.getPreconditionExpression())) {
          System.err.println(
              "Deprecated, No Deletion Description for: " + ODesignHelper.computeModelPath(diagramElementMapping));
        } else {
          if (edgeMapping.getDeletionDescription() == null) {
            this.failedTest
                .append("No Deletion Description for: " + ODesignHelper.computeModelPath(diagramElementMapping) + "\n");
          }
        }
      }
    }
  }

}
