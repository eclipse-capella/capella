/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.common.ju.context;

import org.eclipse.sirius.diagram.DDiagram;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;

public class SA_ESDiagram extends ESDiagram {
  public SA_ESDiagram(BlockArchitectureExt.Type type, SessionContext context, DDiagram diagram) {
    super(type, context, diagram);
  }
  
  @Override
  public void createComponent(String id) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void insertComponent(String id) {
    throw new UnsupportedOperationException();
  }
  
  @Override
  public void removeComponent(String id) {
    throw new UnsupportedOperationException();
  }
}
