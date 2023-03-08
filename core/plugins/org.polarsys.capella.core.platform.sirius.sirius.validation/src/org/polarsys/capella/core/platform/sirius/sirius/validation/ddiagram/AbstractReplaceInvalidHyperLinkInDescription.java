/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.sirius.validation.ddiagram;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.diagram.helpers.DiagramHelper;
import org.polarsys.capella.core.platform.sirius.sirius.validation.parser.helper.DescriptionParserHelper;
import org.polarsys.kitalpha.richtext.widget.tools.dialogs.IEncodedURLHandler;
import org.polarsys.kitalpha.richtext.widget.tools.utils.Constants;

/**
 * Using SAX parser replace invalid link with another element in description
 */
public abstract class AbstractReplaceInvalidHyperLinkInDescription {

  public boolean updateDescription(EObject modelElement, String linkId, String label,
      IEncodedURLHandler encodedURLProvider) {

    if (modelElement instanceof CapellaElement || modelElement instanceof DRepresentationDescriptor) {
      boolean result = encodedURLProvider.handle();
      if (!result) {
        return false;
      }

      String encodedURL = encodedURLProvider.getEncodedURL();
      String oldDescription = DiagramHelper.getElementDescription(modelElement);

      String newDescription = oldDescription;
      List<String> linksToReplace = DescriptionParserHelper.getLinksWithId(oldDescription, linkId);
      for (String linkToReplace : linksToReplace) {
        newDescription = newDescription.replace(linkToReplace, encodedURL);
      }

      DiagramHelper.setElementDescription(modelElement, newDescription);
      return true;
    }
    return false;

  }

  public List<String> getLinkLabels() {
    List<String> result = new ArrayList<String>();
    result.add(Constants.URL_LABEL);
    result.add(Constants.FILE_LABEL);
    result.add(Constants.FILE_LOCAL_LABEL);
    result.add(Constants.MODEL_ELEMENT_LABEL);
    result.add(Constants.DIAGRAM_ELEMENT_LABEL);
    return result;
  }
}

