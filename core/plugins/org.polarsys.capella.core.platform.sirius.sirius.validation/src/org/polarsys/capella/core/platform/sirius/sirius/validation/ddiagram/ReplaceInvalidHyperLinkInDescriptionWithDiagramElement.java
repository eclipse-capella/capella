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

import org.polarsys.kitalpha.richtext.widget.tools.utils.Constants;

/**
 * Using SAX parser replace invalid link with diagram element in description
 */
public class ReplaceInvalidHyperLinkInDescriptionWithDiagramElement
    extends AbstractReplaceInvalidHyperLinkInDescription {

  public ReplaceInvalidHyperLinkInDescriptionWithDiagramElement() {
  }

  @Override
  public List<String> getLinkLabels() {
    List<String> result = new ArrayList<String>();
    result.add(Constants.DIAGRAM_ELEMENT_LABEL);
    result.add(Constants.MODEL_ELEMENT_LABEL);
    return result;
  }
}
