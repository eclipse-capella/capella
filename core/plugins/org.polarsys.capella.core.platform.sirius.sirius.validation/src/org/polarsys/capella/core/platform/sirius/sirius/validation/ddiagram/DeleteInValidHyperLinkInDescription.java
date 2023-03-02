/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.diagram.helpers.DiagramHelper;
import org.polarsys.capella.core.platform.sirius.sirius.validation.parser.helper.DescriptionLinkModifierHandler;
import org.polarsys.capella.core.platform.sirius.sirius.validation.parser.helper.HyperLinkRemoverHandler;

/**
 * Using SAX parser delete capella element or diagram hyperLink from description
 */
public class DeleteInValidHyperLinkInDescription {

  public boolean updateDescription(List<EObject> modelElements, String linkId) {
    Iterator<EObject> iterator = modelElements.iterator();
    while (iterator.hasNext()) {
      EObject object = iterator.next();
      if (object instanceof CapellaElement || object instanceof DRepresentationDescriptor) {
        String description = DiagramHelper.getElementDescription(object);
        HyperLinkRemoverHandler linkRemoverHandler = new HyperLinkRemoverHandler(linkId);
        DescriptionLinkModifierHandler descModifHandler = new DescriptionLinkModifierHandler(object,
            linkRemoverHandler);
        String result = descModifHandler.process(description);
        DiagramHelper.setElementDescription(object, result);
      }
    }
    return true;
  }
}
