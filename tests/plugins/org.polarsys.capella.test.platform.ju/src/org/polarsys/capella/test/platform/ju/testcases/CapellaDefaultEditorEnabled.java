/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.platform.ju.testcases;

import java.net.URL;
import java.util.HashMap;

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
    
    //Retrieve default editors for checked extensions
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

    //Check default editors are the expected ones
    for (String extension: expectedEditors.keySet()) {
      String expectedEditor = expectedEditors.get(extension);
      String currentEditor = currentEditors.get(extension);

      if (!expectedEditor.equals(currentEditor)) {
        assertFalse(NLS.bind("Default editor for ''{0}'' is not the expected one ''{1}'' instead of ''{2}''",
            new String[] { extension, currentEditor, expectedEditor }), true);
      }
    }
    
    //For all editors, check that icon exist
    for (IConfigurationElement element : configurationElements) {
      String url = MenuHelper.getIconURI(element, "icon");
      String icon = element.getAttribute("icon");
      String editorId = element.getAttribute("id");
      if (url != null) {
        ImageDescriptor s = ImageDescriptor.createFromURL(new URL(url));
        Image image = s.createImage(false);
        if (image == null) {
          assertFalse(NLS.bind("Image ''{0}'' for editor ''{1}'' is invalid",
              new String[] { icon, editorId }), true);
        } else {
          image.dispose();
        } 
      } else {
        assertFalse(NLS.bind("Image ''{0}'' for editor ''{1}'' is not set",
            new String[] { icon, editorId }), true);
      }
    }
    
  }

}
