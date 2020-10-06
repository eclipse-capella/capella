/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.platform.ju.testcases;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.internal.menus.MenuHelper;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class CapellaDefaultEditorEnabled extends BasicTestCase {

  @Override
  public void test() throws Exception {
    IConfigurationElement[] configurationElements = Platform.getExtensionRegistry()
        .getConfigurationElementsFor("org.eclipse.ui.editors");

    HashMap<String, String> expectedEditors = new HashMap<String, String>();

    expectedEditors.put(CapellaResourceHelper.AIRD_FILE_EXTENSION, "org.eclipse.sirius.ui.editor.session");

    expectedEditors.put(CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION,
        "org.polarsys.capella.core.data.capellamodeller.presentation.CapellamodellerEditorID");

    HashMap<String, String> currentEditors = new HashMap<String, String>();

    // Retrieve default editors for checked extensions
    for (IConfigurationElement element : configurationElements) {
      String extensions = element.getAttribute("extensions");
      if (extensions != null) {
        for (String extension : extensions.split(",")) {
          extension = extension.trim();
          if (expectedEditors.containsKey(extension)) {
            if ("true".equals(element.getAttribute("default"))) {
              String editorId = element.getAttribute("id");
              currentEditors.put(extension, editorId);
            }
          }
        }
      }
    }

    // Check default editors are the expected ones
    for (String extension : expectedEditors.keySet()) {
      String expectedEditor = expectedEditors.get(extension);
      String currentEditor = currentEditors.get(extension);

      if (!expectedEditor.equals(currentEditor)) {
        // If the test does not pass because of the default editor for "capella" resource is null,
        // please think of checking the generated "org.polarsys.capella.core.data.gen.editor/plugin.xml"
        // All tag <editor> must have the property default="true"
        // The property is not set after re-generation of meta-model.
        assertFalse(NLS.bind("Default editor for ''{0}'' is not the expected one ''{1}'' instead of ''{2}''",
            new String[] { extension, currentEditor, expectedEditor }), true);
      }
    }

    // For all editors, check that icon exist
    Collection<String> errorMessages = new ArrayList<>();

    for (IConfigurationElement element : configurationElements) {
      String iconURI = MenuHelper.getIconURI(element, "icon");

      if (shouldCheckIconURI(iconURI)) {
        String icon = element.getAttribute("icon");
        String editorId = element.getAttribute("id");

        if (iconURI != null) {
          ImageDescriptor descriptor = ImageDescriptor.createFromURL(new URL(iconURI));
          Image image = descriptor.createImage(false);
          if (image == null) {
            errorMessages.add(NLS.bind("Image ''{0}'' for editor ''{1}'' is invalid", icon, editorId));
          } else {
            image.dispose();
          }
        } else {
          errorMessages.add(NLS.bind("Image ''{0}'' for editor ''{1}'' is not set", icon, editorId));
        }
      }
    }

    assertTrue(errorMessages.stream().collect(Collectors.joining("\n")), errorMessages.isEmpty());
  }

  private boolean shouldCheckIconURI(String iconURI) {
    return iconURI != null && iconURI.startsWith("platform:/plugin/org.polarsys.capella");
  }

}
