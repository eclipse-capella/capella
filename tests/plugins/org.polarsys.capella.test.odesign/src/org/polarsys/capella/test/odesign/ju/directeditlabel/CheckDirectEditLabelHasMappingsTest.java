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
package org.polarsys.capella.test.odesign.ju.directeditlabel;

import java.util.List;

import org.eclipse.sirius.diagram.description.tool.DirectEditLabel;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class CheckDirectEditLabelHasMappingsTest extends BasicTestCase {
  private List<DirectEditLabel> dirEditLab;

  public CheckDirectEditLabelHasMappingsTest(List<DirectEditLabel> dirEditLab) {
    this.dirEditLab = dirEditLab;
  }

  @Override
  public void test() throws Exception {
    checkEditLabelsHasMappings(dirEditLab);
  }

  protected void checkEditLabelsHasMappings(List<DirectEditLabel> currentDirectEditLabels) {
    if (!currentDirectEditLabels.isEmpty()) {
      for (DirectEditLabel directEditLabel : currentDirectEditLabels) {
        if (directEditLabel.getMapping().isEmpty()) {
          System.err.println("Mapping list empty of direct edit label" + directEditLabel.getName());
        }
      }
    }
  }
}
