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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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

    List<String> toolsToCheck = getToolsListFromFile("src/org/polarsys/capella/test/odesign/identifier/toolsToCheck.txt");
    for (String toolIdentifier : toolsToCheck) {
      IActivityManager activityManager = PlatformUI.getWorkbench().getActivitySupport().getActivityManager();
      IIdentifier id = activityManager.getIdentifier(toolIdentifier);

      if (id.getActivityIds().isEmpty()) {
        errors.add(NLS.bind("Tool {0} not covered by an activity.", toolIdentifier));
      }
    }

    assertTrue(errors.stream().collect(Collectors.joining("\n")), errors.isEmpty());
  }
  
  /**
   * Returns all tool identifiers found in viewpoints
   * @return
   */
  private List<String> getToolsListFromViewpoint() {
    List<String> list = new ArrayList<>();
    
    IElementIdentifierService elementIdentifier = PlatformUI.getWorkbench().getService(IElementIdentifierService.class);

    for (Viewpoint viewpoint : ViewpointSelection.getViewpoints(CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION)) {
      EList<RepresentationDescription> descriptions = viewpoint.getOwnedRepresentations();
      for (RepresentationDescription description : descriptions) {
        if (description instanceof DiagramDescription) {
          DiagramDescription diagramDescription = (DiagramDescription) description;
          IdentifierHelper.getTools(diagramDescription).forEach(element -> {
            String toolIdentifier = elementIdentifier.getIdentifier(diagramDescription, element);
            list.add(toolIdentifier);
          });
        }
      }
    }
    
    return list;
  }

  /**
   * Returns all tool identifiers found in a given file
   * @param path the path of the file
   * @return
   */
  private List<String> getToolsListFromFile(String path) {
    List<String> list = new ArrayList<>();
    
    try(BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)))) {
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        list.add(line);
      }
    } catch (IOException e) {
      System.err.println("importToolsListFromFile failed!");
    }
    return list;
  }
}
