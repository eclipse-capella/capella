/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package ms.design;

import java.util.Collection;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;

class DiagramScope extends Scope {

  public DiagramScope(DSemanticDiagram diagram) {
    super(null, diagram);
  }
  
  @Override
  Collection<DDiagramElement> getChildren() {
    return ((DDiagram) getElement()).getOwnedDiagramElements();
  }

  @Override
  public <T> T accept(ScopeVisitor<T> visitor) {
    return visitor.visitDiagramScope(this);
  }

}