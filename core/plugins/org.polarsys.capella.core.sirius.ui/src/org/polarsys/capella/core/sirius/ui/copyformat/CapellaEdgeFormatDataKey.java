/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.ui.copyformat;

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
public class CapellaEdgeFormatDataKey extends CapellaDecoratorFormatDataKey {

  /**
   * Default constructor.
   * 
   * @param key
   *            The key
   */
  public CapellaEdgeFormatDataKey(DEdge decorator, AbstractCapellaFormatDataKey key) {
    super(key);
    if (decorator.getSourceNode() != null) {
      addDecoration(decorator.getSourceNode());
    }
    if (decorator.getTargetNode() != null) {
      addDecoration(decorator.getTargetNode());
    }
    addDecoration(DiagramPackage.Literals.DEDGE);
  }

  /**
   * @param sourceNode
   */
  @Override
  protected void addDecoration(EObject object) {
    if ((object != null) && (object instanceof DSemanticDecorator)) {
      DSemanticDecorator sourceDecorator = (DSemanticDecorator) object;
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
      super.addDecoration(object);
    }
  }
}
