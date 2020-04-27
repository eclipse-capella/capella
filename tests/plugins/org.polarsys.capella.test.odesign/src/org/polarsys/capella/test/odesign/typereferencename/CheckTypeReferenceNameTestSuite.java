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
package org.polarsys.capella.test.odesign.typereferencename;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.table.metamodel.table.description.CrossTableDescription;
import org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping;
import org.eclipse.sirius.table.metamodel.table.description.LineMapping;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelection;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

public class CheckTypeReferenceNameTestSuite extends BasicTestSuite {

  @Override
  protected List<BasicTestArtefact> getTests() {
    ArrayList<BasicTestArtefact> tests = new ArrayList<>();

    Set<Viewpoint> viewpoints = ViewpointSelection.getViewpoints(CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION);
    for (Viewpoint viewpoint : viewpoints) {

      EList<RepresentationDescription> ownedRepresentations = viewpoint.getOwnedRepresentations();
      for (final RepresentationDescription _representationDescription : ownedRepresentations) {

        // look for CreateInstance in diagrams
        if (_representationDescription instanceof DiagramDescription) {
          DiagramDescription des = (DiagramDescription) _representationDescription;
          EList<AbstractToolDescription> desTools = des.getAllTools();

          // for every tool of every diagram, find instances of CreateInstance in operation and sub operations
          for (AbstractToolDescription adesTool : desTools) {
            ModelOperation fistOperation = TypeReferenceNameUtils.getFistOperation(adesTool);
            TypeReferenceNameUtils.findCreateInstance(_representationDescription, adesTool, fistOperation, tests);
          }
        }

        // Table case
        if (_representationDescription instanceof TableDescription) {
          TableDescription des = (TableDescription) _representationDescription;
          EList<LineMapping> allLineMappings = des.getAllLineMappings();
          TypeReferenceNameUtils.findCreateInstanceInLineMappings(des, allLineMappings, tests);
          // CrossTable case
          if (des instanceof CrossTableDescription) {
            CrossTableDescription crossDes = (CrossTableDescription) des;
            EList<IntersectionMapping> intersections = crossDes.getIntersection();
            TypeReferenceNameUtils.findCreateInstanceInIntersectionMappings(des, intersections, tests);
          }

        }
      }
    }

    return tests;

  }
}
