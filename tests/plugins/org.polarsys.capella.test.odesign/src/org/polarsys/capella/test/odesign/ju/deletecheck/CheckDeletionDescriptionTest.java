/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.odesign.ju.deletecheck;

import java.util.List;
import java.util.Set;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.LineMapping;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ODesignHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class CheckDeletionDescriptionTest extends BasicTestCase {
  StringBuilder failedTest = new StringBuilder();

  @Override
  public void test() throws Exception {

    Set<Viewpoint> test = ViewpointRegistry.getInstance().getViewpoints();
    for (Viewpoint viewpoint : test) {
      if (CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION.equals(viewpoint.getModelFileExtension())) {
        checkMappingsHasDeletionDescription(viewpoint.eResource());
      }
    }

    if (this.failedTest.length() == 0) {
      assertTrue(true);
    } else {
      System.err.println(failedTest.toString());
      assertTrue(this.failedTest.toString(), false);
    }
  }

  protected void checkMappingsHasDeletionDescription(Resource resource) {
    List<RepresentationElementMapping> mappings = StreamSupport
        .stream(Spliterators.spliteratorUnknownSize(resource.getAllContents(), 0), false)
        .filter(RepresentationElementMapping.class::isInstance).map(x -> (RepresentationElementMapping) x)
        .filter(ODesignHelper::isNotDeprecatedMapping).collect(Collectors.toList());

    for (RepresentationElementMapping mapping : mappings) {
      checkHasDeletionDescription((RepresentationElementMapping) mapping);
    }
  }

  private void checkHasDeletionDescription(RepresentationElementMapping mapping) {
    if (mapping instanceof AbstractNodeMapping) {
      AbstractNodeMapping nodeMapping = (AbstractNodeMapping) mapping;
      if (nodeMapping.getDomainClass() != null && nodeMapping.getDeletionDescription() == null) {
        this.failedTest.append("No Deletion Description for: " + ODesignHelper.computeModelPath(mapping) + "\n");
      }

    } else if (mapping instanceof EdgeMapping) {
      EdgeMapping edgeMapping = (EdgeMapping) mapping;
      if (edgeMapping.isUseDomainElement() && edgeMapping.getDeletionDescription() == null) {
        this.failedTest.append("No Deletion Description for: " + ODesignHelper.computeModelPath(mapping) + "\n");
      }
      
    } else if (mapping instanceof LineMapping) {
      LineMapping lineMapping = (LineMapping) mapping;
      if (lineMapping.getDelete() == null) {
        this.failedTest.append("No Deletion Description for: " + ODesignHelper.computeModelPath(mapping) + "\n");
        
      } else if (!"false".equals(lineMapping.getDelete().getPrecondition())) {
        this.failedTest.append("Capella Deletion on tables is not supported. Deletion shall be disabled with a 'false' precondition for: " + ODesignHelper.computeModelPath(mapping) + "\n");
      }

    } else if (mapping instanceof ElementColumnMapping) {
      ElementColumnMapping columnMapping = (ElementColumnMapping) mapping;
      if (columnMapping.getDelete() == null) {
        this.failedTest.append("No Deletion Description for: " + ODesignHelper.computeModelPath(mapping) + "\n");
        
      } else if (!"false".equals(columnMapping.getDelete().getPrecondition())) {
        this.failedTest.append("Capella Deletion on tables is not supported. Deletion shall be disabled with a 'false' precondition for: " + ODesignHelper.computeModelPath(mapping) + "\n");
      }
    }
  }

}
