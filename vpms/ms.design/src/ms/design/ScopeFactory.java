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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.util.DiagramSwitch;

class ScopeFactory extends DiagramSwitch<Scope> {

  protected Scope parent;

  public void setParent(Scope parentScope) {
    this.parent = parentScope;
  }

  @Override
  public Scope caseDSemanticDiagram(DSemanticDiagram diagram) {
    return new DiagramScope(diagram);
  }

  @Override
  public Scope caseDNodeContainer(DNodeContainer object) {
    return new DNodeContainerScope(parent, object);
  }

  public Scope caseAbstractDNode(AbstractDNode node) {
    return new AbstractDNodeScope(parent, node);
  }

  public Scope caseDNode(DNode node) {
    return new DNodeScope(parent, node);
  }

  public Scope defaultCase(EObject e) {
    return new DefaultScope(parent, e);
  }

}