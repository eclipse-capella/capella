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

import java.util.ArrayDeque;
import java.util.Deque;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;

public class DefaultMsRefreshExtension extends AbstractMsRefreshExtension {

  DefaultMsRefreshExtension(CSSRefreshExtension css){
    super(css);
  }

  protected ScopeFactory createScopeFactory() {
    return new ScopeFactory();
  }

  protected ScopeVisitor<?> createVisitor(){
    return new ConfigurationScopeVisitor(css);
  }

  protected void refreshNodes(DDiagram dDiagram) {
    ScopeFactory scopeFactory = createScopeFactory();
    ScopeVisitor<?> visitor = createVisitor();

    Deque<Scope> remaining = new ArrayDeque<Scope>();
    remaining.push(scopeFactory.doSwitch(dDiagram));

    while (!remaining.isEmpty()) {
      Scope scope = remaining.pop();
      scope.accept(visitor);
      scopeFactory.setParent(scope);
      for (DDiagramElement child : scope.getChildren()) {
        if (!(child instanceof DEdge)) {
          remaining.push(scopeFactory.doSwitch(child));
        }
      }
    }
  }

  protected void refreshEdges(DDiagram dDiagram) {
    for (DEdge edge : dDiagram.getEdges()) {
      CSConfigurationStyle style = getCSConfigurationStyle(edge);
      style.clear();
      for (String clazz : getCSConfigurationStyle((DDiagramElement) edge.getTargetNode()).getStyle()) {
        style.addClass(clazz);
      }
      for (String clazz : getCSConfigurationStyle((DDiagramElement) edge.getSourceNode()).getStyle()) {
        style.addClass(clazz);
      } 
    }
  }
  
  @Override
  public void postRefresh(DDiagram dDiagram) {
    refreshNodes(dDiagram);
    refreshEdges(dDiagram);
  }

  @Override
  public void beforeRefresh(DDiagram dDiagram) {
  }

  

}
