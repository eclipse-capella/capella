/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.xfcd;

import org.polarsys.capella.test.diagram.common.ju.context.XFCDDiagram;
import org.polarsys.capella.test.diagram.tools.ju.xfcd.utils.FCDSequencingTest;
import org.polarsys.capella.test.diagram.tools.ju.xfcd.utils.OA_Settings;
import org.polarsys.capella.test.diagram.tools.ju.xfcd.utils.Settings;

public class DragAndDropTest extends FCDSequencingTest {

  public DragAndDropTest(Settings settings) {
    super(settings);
  }

  @Override
  protected void testLevel0() {
    xfcd = XFCDDiagram.getDiagram(context, settings.SEQ_FUNC_CHAIN_DIAGRAM_LEVEL0);
    XFCDDiagram xfcdSetup = XFCDDiagram.getDiagram(context, settings.SEQ_FUNC_CHAIN_DIAGRAM_LEVEL1);

    String constraint = xfcdSetup.createConstraint("c1");
    if (settings instanceof OA_Settings)
      xfcd.dragAndDropConstraintsFromExplorer(constraint, xfcd.getDiagramId());
    else
      xfcd.dragAndDropConstraintFromExplorer(constraint, xfcd.getDiagramId());

    xfcd.close();
  }

  @Override
  protected void testLevel1() {
  }

  @Override
  protected void testLevel2() {
  }

}
