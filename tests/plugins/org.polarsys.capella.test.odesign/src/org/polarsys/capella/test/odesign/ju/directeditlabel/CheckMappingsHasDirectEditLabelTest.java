/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.odesign.ju.directeditlabel;

import java.util.List;

import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ODesignHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class CheckMappingsHasDirectEditLabelTest extends BasicTestCase {

  List<DiagramElementMapping> diagramMappings;

  public CheckMappingsHasDirectEditLabelTest(List<DiagramElementMapping> diagramMappings) {
    this.diagramMappings = diagramMappings;
  }

  @Override
  public void test() throws Exception {
    checkMappingsHasEditLabels(diagramMappings);
  }
  
  
  protected void checkMappingsHasEditLabels(List<DiagramElementMapping> mappingsToCheck) {
    if (mappingsToCheck != null) {
      for (DiagramElementMapping mapping : mappingsToCheck) {
        if (mapping.getLabelDirectEdit() == null) {
          System.err.println("No DirectEditLabel for: " + ODesignHelper.computeModelPath(mapping));
        }
      }
    }
  }
}
