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
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;

import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.Part;

public class InterfaceDiagramMsRefreshExtension extends DefaultMsRefreshExtension {

  InterfaceDiagramMsRefreshExtension(CSSRefreshExtension css) {
    super(css);
  }

  @Override
  public void postRefresh(DDiagram dDiagram) {
    refreshNodes(dDiagram);
  }

  @Override
  protected ScopeVisitor<?> createVisitor() {
    return new ConfigurationScopeVisitor(css) {

      @Override
      protected void updateStyle(AbstractDNodeScope asd) {
        AbstractDNode node = (AbstractDNode)asd.getElement(); 
        
        if (node.getTarget() instanceof Interface) {
          CSConfigurationStyle interfaceStyle = css.getCSConfigurationStyle(node);
          for (DEdge edge : ((EdgeTarget) node).getIncomingEdges()){
            CSConfigurationStyle portStyle = css.getCSConfigurationStyle(edge.getSourceNode());
            CSConfigurationStyle edgeStyle = css.getCSConfigurationStyle(edge);
            for (String clazz : portStyle.getStyle()) {
              interfaceStyle.addClass(clazz);
              edgeStyle.addClass(clazz);
            }
          }
        } else {
          super.updateStyle(asd);
        }
      }

    };
  }

  @Override
  /**
   * Overridden to enforce a specific order for diagram scope children: We want to process components/parts
   * first and then the interfaces.
   */
  protected ScopeFactory createScopeFactory() {
    return new ScopeFactory() {
      @Override
      public Scope caseDSemanticDiagram(DSemanticDiagram diagram) {
        return new DiagramScope(diagram) {
          Collection<DDiagramElement> getChildren() {
            Deque<DDiagramElement> result = new ArrayDeque<DDiagramElement>();
            for (DDiagramElement elem : ((DSemanticDiagram)getElement()).getOwnedDiagramElements()) {              
              // the component must be last, because the children are processed in inverse order
              if (elem.getTarget() instanceof Component) {
                result.addLast(elem);
              } else if (elem.getTarget() instanceof Interface) {
                result.addFirst(elem);
              }
            }
            return result;
          }
        };
      }
    };
  }

}
