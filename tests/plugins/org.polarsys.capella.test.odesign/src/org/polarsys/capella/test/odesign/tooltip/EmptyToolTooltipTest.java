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
package org.polarsys.capella.test.odesign.tooltip;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelection;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.platform.sirius.ui.services.IElementIdentifierService;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.odesign.identifier.IdentifierHelper;

public class EmptyToolTooltipTest extends BasicTestCase {

  private static final String ERROR_MSG = "The tool {0} has an empty tooltip";

  @Override
  public void test() {
    Collection<String> errors = new ArrayList<>();
    IElementIdentifierService elementIdentifier = PlatformUI.getWorkbench().getService(IElementIdentifierService.class);

    for (Viewpoint viewpoint : ViewpointSelection.getViewpoints(CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION)) {
      List<RepresentationDescription> descriptions = viewpoint.getOwnedRepresentations();

      for (RepresentationDescription description : descriptions) {
        if (description instanceof DiagramDescription) {
          DiagramDescription diagramDescription = (DiagramDescription) description;
          IdentifierHelper.getTools(diagramDescription).filter(IdentifierHelper.toolFilter)
              .filter(DocumentedElement.class::isInstance).map(DocumentedElement.class::cast).forEach(tool -> {
                String documentation = tool.getDocumentation();
                String toolIdentifier = elementIdentifier.getIdentifier(diagramDescription, (IdentifiedElement) tool);

                if (documentation == null || documentation.isEmpty()) {
                  String msg = NLS.bind(ERROR_MSG, toolIdentifier);
                  errors.add(msg);
                }
              });
        }
      }
    }

    assertTrue(errors.stream().collect(Collectors.joining("\n")), errors.isEmpty());
  }
}
