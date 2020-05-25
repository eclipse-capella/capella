/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.tb;

import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.test.diagram.common.ju.context.CommonDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;

public abstract class XABTitleBlockTestCase extends AbstractTitleBlockTestCase {
  protected String diagramName;
  protected String structurePKG;
  protected BlockArchitectureExt.Type type;

  public XABTitleBlockTestCase() {
    initData();
  }

  @Override
  protected CommonDiagram initDiagram() {
    return XABDiagram.openDiagram(context, diagramName, type);
  }

  @Override
  protected CommonDiagram createDiagram() {
    return XABDiagram.createDiagram(context, structurePKG);
  }

  public abstract void initData();

}
