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
package org.polarsys.capella.test.diagram.misc.ju.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.filter.CompositeFilterDescription;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.tool.PopupMenu;
import org.eclipse.sirius.viewpoint.description.tool.ToolDescription;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.platform.sirius.ui.services.IElementIdentifierService;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ODesignHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class DiagramToolAndLabelCoherenceTest extends BasicTestCase {
  private static final String PROJECT_NAME = "EOLE_AF_UC";

  private static final Predicate<IdentifiedElement> toolFilter = tool -> tool instanceof ContainerCreationDescription
      || tool instanceof NodeCreationDescription || tool instanceof ToolDescription || tool instanceof PopupMenu
      || tool instanceof EdgeCreationDescription;

  private static final Predicate<IdentifiedElement> filterFilter = tool -> tool instanceof CompositeFilterDescription;

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(PROJECT_NAME);
  }

  @Override
  public void test() {
    Session session = getSession(PROJECT_NAME);

    ResourceSet resourceSet = TransactionHelper.getEditingDomain(session).getResourceSet();
    List<Resource> odesigns = ODesignHelper.getAvailableODesignFile(resourceSet);
    List<DiagramDescription> diagramDescriptions = ODesignHelper.getAllDiagramDescription(odesigns);
    IElementIdentifierService elementIdentifier = PlatformUI.getWorkbench().getService(IElementIdentifierService.class);

    Collection<String> errors = new ArrayList<>();
    
    for (DiagramDescription diagramDescription : diagramDescriptions) {

      Stream.concat(diagramDescription.getAllTools().stream(), diagramDescription.getFilters().stream())
          .filter(toolFilter.or(filterFilter)).forEach(element -> {

            String toolIdentifier = elementIdentifier.getIdentifier(diagramDescription, element);
            String toolLabel = element.getLabel();
            if (toolLabel == null) {
              errors.add(NLS.bind("Element {0} doens't have a label.", element.getName()));
              
            } else {
              String[] tokens = toolLabel.split("%");
              if (tokens.length != 1) {
                errors.add(NLS.bind("Element {0} is not internationalized.", element.getName()));
                
              } else {
                String label = tokens[0];
                if (!label.equals(toolIdentifier)) {
                  errors.add(NLS.bind("Element {0} doesn't use the correct identifier.", element.getName()));
                }
              }
            }
          });

    }
    
    if (!errors.isEmpty()) {
      assertTrue(errors.stream().collect(Collectors.joining("\n")), errors.isEmpty());
    }

  }
}
