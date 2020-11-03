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
package org.polarsys.capella.test.odesign.ju.domainclass;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.ContentHelper;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.table.metamodel.table.description.CrossTableDescription;
import org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.LineMapping;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelection;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

public class CheckDomainClassTestSuite extends BasicTestSuite {

  @Override
  protected List<BasicTestArtefact> getTests() {
    ArrayList<BasicTestArtefact> tests = new ArrayList<>();

    Set<Viewpoint> viewpoints = ViewpointSelection.getViewpoints(CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION);
    for (Viewpoint viewpoint : viewpoints) {

      EList<RepresentationDescription> ownedRepresentations = viewpoint.getOwnedRepresentations();
      for (final RepresentationDescription description : ownedRepresentations) {

        // Diagram case
        if (description instanceof DiagramDescription) {
          DiagramDescription des = (DiagramDescription) description;

          // check domain class of the diagram
          final String diagramDomainClass = des.getDomainClass();
          createDomainClassTest(des, diagramDomainClass, tests);

          // Container Mapping
          EList<ContainerMapping> allContainerMappings = ContentHelper.getAllContainerMappings(des, false);
          for (ContainerMapping containerMapping : allContainerMappings) {
            final String domainClass = containerMapping.getDomainClass();
            createDomainClassTest(des, domainClass, tests);
          }

          // Relation and Element based edges
          EList<EdgeMapping> allEdgeMappings = des.getAllEdgeMappings();
          for (EdgeMapping edgeMapping : allEdgeMappings) {
            final String domainClass = edgeMapping.getDomainClass();
            createDomainClassTest(des, domainClass, tests);
          }

          // Node Mapping
          EList<NodeMapping> allNodeMappings = ContentHelper.getAllNodeMappings(des, false);
          for (NodeMapping nodeMapping : allNodeMappings) {
            final String domainClass = nodeMapping.getDomainClass();
            createDomainClassTest(des, domainClass, tests);
          }
        }

        // Table case
        else if (description instanceof TableDescription) {
          TableDescription des = (TableDescription) description;
          EList<LineMapping> allLineMappings = des.getAllLineMappings();
          for (LineMapping lineMapping : allLineMappings) {
            final String domainClass = lineMapping.getDomainClass();

            createDomainClassTest(des, domainClass, tests);

          }
          // CrossTableDescription
          if (des instanceof CrossTableDescription) {
            CrossTableDescription crossDes = (CrossTableDescription) des;
            EList<ElementColumnMapping> ownedColumnMappings = crossDes.getOwnedColumnMappings();
            for (ElementColumnMapping columnMapping : ownedColumnMappings) {
              final String domainClass = columnMapping.getDomainClass();
              createDomainClassTest(crossDes, domainClass, tests);
            }
          }
        }
      }
    }

    return tests;
  }

  /**
   * Create a Domain Class Test and add it to a list of tests
   * 
   * @param des_p
   * @param domainClass_p
   * @return
   */
  private void createDomainClassTest(final RepresentationDescription des_p, final String domainClass_p,
      List<BasicTestArtefact> tests_p) {

    if (null != domainClass_p) {
      // create a test to check that the domain class exists
      CheckDomainClassTest test = new CheckDomainClassTest(domainClass_p);
      tests_p.add(test);
    }
  }

}
