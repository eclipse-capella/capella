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
package org.polarsys.capella.test.odesign.identifier;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelection;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.platform.sirius.ui.services.IElementIdentifierService;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class DuplicateToolsAndFiltersTest extends BasicTestCase {

  @Override
  public void test() {
    Set<String> ids = new HashSet<>();
    Set<String> duplicatedIds = new HashSet<>();
    IElementIdentifierService elementIdentifier = PlatformUI.getWorkbench().getService(IElementIdentifierService.class);

    for (Viewpoint viewpoint : ViewpointSelection.getViewpoints(CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION)) {
      EList<RepresentationDescription> descriptions = viewpoint.getOwnedRepresentations();
      for (RepresentationDescription description : descriptions) {
        if (description instanceof DiagramDescription) {
          DiagramDescription diagramDescription = (DiagramDescription) description;
          IdentifierHelper.getTools(diagramDescription).forEach(element -> {
            String toolIdentifier = elementIdentifier.getIdentifier(diagramDescription, element);
            boolean isIdDuplicated = !ids.add(toolIdentifier);
            if (isIdDuplicated) {
              duplicatedIds.add(toolIdentifier);
            }
          });
        }
      }
    }

    if (!duplicatedIds.isEmpty()) {
      assertTrue("There is some duplicated tools: " + duplicatedIds.stream().collect(Collectors.joining("\n")), duplicatedIds.isEmpty());
    }
  }
}
