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
package org.polarsys.capella.test.odesign.identifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.filter.CompositeFilterDescription;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelection;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.tool.PopupMenu;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.platform.sirius.ui.services.IElementIdentifierService;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class ToolIdentifierConsistencyTest extends BasicTestCase {

  @Override
  public void test() {
    Collection<String> errors = new ArrayList<>();

    IElementIdentifierService elementIdentifier = PlatformUI.getWorkbench().getService(IElementIdentifierService.class);

    for (Viewpoint viewpoint : ViewpointSelection.getViewpoints(CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION)) {
      EList<RepresentationDescription> descriptions = viewpoint.getOwnedRepresentations();
      for (RepresentationDescription description : descriptions) {
        if (description instanceof DiagramDescription) {
          DiagramDescription diagramDescription = (DiagramDescription) description;
          IdentifierHelper.getTools(diagramDescription).forEach(element -> {
            String toolIdentifier = elementIdentifier.getIdentifier(diagramDescription, element);

            if (element instanceof PopupMenu && !(toolIdentifier.endsWith(".menu"))) {
              errors.add(NLS.bind("Menu {0} doens't ends with '.menu'.", element.getName()));

            } else if (element instanceof CompositeFilterDescription && !(toolIdentifier.endsWith(".filter"))) {
              if (!"ModelExtensionFilter".equals(element.getName())) {
                errors.add(NLS.bind("Filter {0} doens't ends with '.filter'.", element.getName()));
              }
            }

          });
        }
      }
    }

    if (!errors.isEmpty()) {
      assertTrue(errors.stream().collect(Collectors.joining("\n")), errors.isEmpty());
    }

  }
}
