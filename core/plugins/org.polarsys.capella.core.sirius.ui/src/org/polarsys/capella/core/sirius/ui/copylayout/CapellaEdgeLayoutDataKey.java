/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.ui.copylayout;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * Specific key for {@link DEdge}.
 */
public class CapellaEdgeLayoutDataKey extends CapellaDecoratorLayoutDataKey {

  /**
   * Default constructor.
   * 
   * @param key
   *            The key
   */
  public CapellaEdgeLayoutDataKey(DEdge decorator_p, AbstractCapellaLayoutDataKey key_p) {
    super(key_p);
    if (decorator_p.getSourceNode() != null) {
      addDecoration(decorator_p.getSourceNode());
    }
    if (decorator_p.getTargetNode() != null) {
      addDecoration(decorator_p.getTargetNode());
    }
    addDecoration(DiagramPackage.Literals.DEDGE);
  }

  /**
   * @param sourceNode_p
   */
  @Override
  protected void addDecoration(EObject object_p) {
    if ((object_p != null) && (object_p instanceof DSemanticDecorator)) {
      DSemanticDecorator sourceDecorator = (DSemanticDecorator) object_p;
      if (sourceDecorator.getTarget() != null) {
        super.addDecoration(sourceDecorator.getTarget());
      }

      //Add decoration on container if borderedNode
      if (sourceDecorator instanceof DDiagramElement) {
        DiagramElementMapping mapping = ((DDiagramElement) sourceDecorator).getDiagramElementMapping();
        EStructuralFeature reference = mapping.eContainingFeature();
        if (DescriptionPackage.Literals.ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS.equals(mapping.eContainingFeature())) {
          addDecoration(sourceDecorator.eContainer());
        }
      }

    } else {
      super.addDecoration(object_p);
    }
  }
}
