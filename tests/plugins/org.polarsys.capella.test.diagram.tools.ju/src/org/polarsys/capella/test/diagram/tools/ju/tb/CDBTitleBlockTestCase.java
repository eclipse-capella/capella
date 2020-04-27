/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.tb;

import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.CommonDiagram;

public abstract class CDBTitleBlockTestCase extends AbstractTitleBlockTestCase {
  protected String classId;
  protected String diagramName;

  public CDBTitleBlockTestCase() {
    initData();
  }

  @Override
  public void testTitleBlocks() {
    // create TB tools
    DAnnotation diagramTB = diagram.createDiagramTitleBlock();
    DAnnotation elementTB = diagram.createElementTitleBlock(classId);

    // show/hide tool + create TB after hide it

    diagram.removeElementTitleBlock(elementTB.getUid());
    diagram.checkCreateElementTitleBlock(classId);
    diagram.insertElementTitleBlock(elementTB.getUid());

    diagram.removeDiagramTitleBlock(diagramTB.getUid());
    diagram.checkCreateDiagramTitleBlock();
    diagram.insertDiagramTitleBlock(diagramTB.getUid());

    // insert line/column in TB element
    diagram.insertLineInTitleBlock(elementTB, 0);
    diagram.insertColumnInTitleBlock(elementTB, 0);

    // remove line/column from TB element
    diagram.removeLineFromTitleBlock(elementTB, 0);
    diagram.removeColumnFromTitleBlock(elementTB, 0);

  }

  @Override
  protected CommonDiagram initDiagram() {
    return CDBDiagram.openDiagram(context, diagramName);
  }

  public abstract void initData();
}
