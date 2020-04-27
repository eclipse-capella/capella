/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.diagram.helpers.traceability;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.sirius.viewpoint.DRepresentation;

import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;

/**
 * Returns the traceability manager for the given id of traceability.
 * (this allows for diagrams to have more than one traceability manager, selection is made by an identifier)
 */
public class DiagramTraceabilityHelper {

  private static DiagramTraceabilityHelper _instance;

  protected DiagramTraceabilityHelper() {
    //Nothing here
  }

  public static DiagramTraceabilityHelper getService() {
    if (_instance == null) {
      _instance = new DiagramTraceabilityHelper();
    }
    return _instance;
  }

  public IDiagramTraceability getTraceabilityHandler(DRepresentation representation, String idTraceability) {
    IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();

    IConfigurationElement configurationElements[] = extensionRegistry.getConfigurationElementsFor("org.polarsys.capella.core.diagram.traceability");

    // Loop over contributed DiagramTraceability providers, must be only one.
    if ((configurationElements != null) && (configurationElements.length > 0)) {
      for (IConfigurationElement element : configurationElements) {
        if (idTraceability.equals(element.getAttribute(ExtensionPointHelper.ATT_ID))) {
          IDiagramTraceability result = (IDiagramTraceability) ExtensionPointHelper.createInstance(element, ExtensionPointHelper.ATT_CLASS);
          return result;
        }
      }
    }
    return null;
  }
}
