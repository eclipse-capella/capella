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
package org.polarsys.capella.test.odesign.identifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelection;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.activities.IActivityManager;
import org.eclipse.ui.activities.IIdentifier;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.platform.sirius.ui.services.IElementIdentifierService;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class ToolActivityCoverageTest extends BasicTestCase {

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

            IActivityManager activityManager = PlatformUI.getWorkbench().getActivitySupport().getActivityManager();
            IIdentifier id = activityManager.getIdentifier(toolIdentifier);

            if (id.getActivityIds().isEmpty()) {
              errors.add(NLS.bind("Tool {0} not covered by an activity.", element.getName()));
            }

          });
        }
      }
    }

    assertTrue(errors.stream().collect(Collectors.joining("\n")), errors.isEmpty());
  }
}
