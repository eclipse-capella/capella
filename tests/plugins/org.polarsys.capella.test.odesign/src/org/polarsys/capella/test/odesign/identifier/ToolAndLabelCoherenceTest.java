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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.PropertyResourceBundle;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelection;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.platform.sirius.ui.services.IElementIdentifierService;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;
import org.polarsys.capella.core.sirius.analysis.activator.SiriusViewActivator;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class ToolAndLabelCoherenceTest extends BasicTestCase {

  private static final String PLUGIN_PROPERTIES = "plugin.properties";

  @Override
  public void test() {
    PropertyResourceBundle pluginProperties = getPluginProperties();

    Collection<String> errors = new ArrayList<>();
    IElementIdentifierService elementIdentifier = PlatformUI.getWorkbench().getService(IElementIdentifierService.class);

    for (Viewpoint viewpoint : ViewpointSelection.getViewpoints(CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION)) {
      EList<RepresentationDescription> descriptions = viewpoint.getOwnedRepresentations();
      for (RepresentationDescription description : descriptions) {
        if (description instanceof DiagramDescription) {
          DiagramDescription diagramDescription = (DiagramDescription) description;
          IdentifierHelper.getTools(diagramDescription).forEach(element -> {
            String toolIdentifier = elementIdentifier.getIdentifier(diagramDescription, element);
            String toolLabel = element.getLabel();
            if (toolLabel == null) {
              errors.add(NLS.bind("Element {0} doesn't have a label.", element.getName()));

            } else {
              String[] tokens = toolLabel.split("%");
              if (tokens.length != 2) {
                errors.add(NLS.bind("Element {0} is not internationalized.", element.getName()));
              } else {
                String label = tokens[1];
                if (!isCommonTool(element) && !label.equals(toolIdentifier)) {
                  errors.add(NLS.bind("Element {0} doesn't use the correct label identifier. {1} instead of {2}",
                      new String[] { viewpoint.getName() + "::" + element.getLabel(), label, toolIdentifier }));
                }
                if (!pluginProperties.containsKey(label)) {
                  errors.add(NLS.bind("Element {0} doesn't use a label identifier defined in the properties file {1}.",
                      new String[] { viewpoint.getName() + "::" + element.getLabel(), PLUGIN_PROPERTIES }));
                }
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

  /**
   * 
   * @param element
   * @return if this is a common tool for many diagrams. In this case, no need to prefix the tool with
   *         viewpoint.diagramName.
   */
  private boolean isCommonTool(IdentifiedElement element) {
    return Arrays.asList(IMappingNameConstants.HIDE_OVERLAPPED_FUNCTIONAL_CHAINS_ICON,
        IMappingNameConstants.HIDE_OVERLAPPED_FUNCTIONAL_CHAINS_LABEL,
        IMappingNameConstants.HIDE_OVERLAPPED_PHYSICAL_PATHS_ICON,
        IMappingNameConstants.HIDE_OVERLAPPED_PHYSICAL_PATHS_LABEL).contains(element.getName());
  }

  private PropertyResourceBundle getPluginProperties() {
    PropertyResourceBundle propertyBundle = null;
    try {
      propertyBundle = new PropertyResourceBundle(FileLocator.openStream(SiriusViewActivator.getInstance().getBundle(), new Path(PLUGIN_PROPERTIES), false));
    } catch (IOException e) {
      fail(NLS.bind("Property file {0} doesn't exist.", new String[] { PLUGIN_PROPERTIES }));
    }
    return propertyBundle;
  }
}
