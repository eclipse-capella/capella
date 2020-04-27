/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.diagram.handlers;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.transition.diagram.helpers.TraceabilityHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public abstract class AbstractDiagramHandler implements IDiagramHandler {

  @Override
  public boolean handles(IContext context_p, RepresentationDescription representation_p) {
    return true;
  }

  @Override
  public boolean covers(IContext context_p, DRepresentationDescriptor representation_p) {
    return true;
  }

  @Override
  public boolean backCovers(IContext context_p, DRepresentationDescriptor representation_p) {
    return true;
  }

  public FilterDescription getTargetFilterDescription(IContext context_p, DiagramDescription description_p,
      DiagramDescription description2_p, FilterDescription description3_p) {
    String sourceLabel = description3_p.getName();
    if (description3_p.getLabel() != null) {
      sourceLabel = description3_p.getLabel();
    }

    for (FilterDescription description : description2_p.getFilters()) {
      String targetLabel = description.getName();
      if (description.getLabel() != null) {
        targetLabel = description.getLabel();
      }
      if (sourceLabel.equals(targetLabel)) {
        return description;
      }
    }
    return null;
  }

  /**
   * @param edgeTarget_p
   * @param sourceNode_p
   * @param targetNode_p
   * @return
   */
  public boolean isReconciliable(IContext context_p, RepresentationDescription sourceDescription_p, DEdge edgeTarget_p,
      DSemanticDecorator sourceNode_p, DSemanticDecorator targetNode_p) {
    return sourceNode_p.equals(edgeTarget_p.getSourceNode()) && targetNode_p.equals(edgeTarget_p.getTargetNode());
  }

  /**
   * @param containerNode_p
   * @param eContainer_p
   * @return
   */
  public boolean isReconciliable(IContext context_p, RepresentationDescription sourceDescription_p,
      AbstractDNode currentNode_p, DSemanticDecorator eContainer_p) {
    return currentNode_p.eContainer().equals(eContainer_p);
  }

  /**
   * {@inheritDoc}
   */
  public IStatus init(IContext context_p) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus dispose(IContext context_p) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public DSemanticDecorator showNode(IContext context_p, RepresentationDescription sourceDescription_p,
      DDiagramContents targetContents_p, AbstractNodeMapping mapping_p, DSemanticDecorator containerNode_p,
      EObject targetSemantic_p) {

    Collection<DDiagramElement> nodes = targetContents_p.getDiagramElements(targetSemantic_p, mapping_p,
        containerNode_p);
    if (nodes.size() > 0) {
      for (DSemanticDecorator target : nodes) {
        if (target instanceof AbstractDNode) {
          if (isReconciliable(context_p, sourceDescription_p, (AbstractDNode) target, containerNode_p)) {
            return target;
          }
        }
      }
    }

    if (DiagramServices.getDiagramServices().isValidMapping(mapping_p, containerNode_p)) {
      if (DiagramServices.getDiagramServices().evaluateNodePrecondition(mapping_p, targetContents_p.getDDiagram(), containerNode_p, targetSemantic_p)) {
        DDiagramElement targetView = DiagramServices.getDiagramServices().createAbstractDNode(mapping_p,
            targetSemantic_p, (DragAndDropTarget) containerNode_p, targetContents_p.getDDiagram());
        return targetView;
      }
    }

    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public DDiagramElement showEdge(IContext context_p, RepresentationDescription sourceDescription_p,
      DDiagramContents targetContents_p, EdgeMapping mapping_p, DSemanticDecorator sourceNode_p,
      DSemanticDecorator targetNode_p, EObject targetSemantic_p) {

    for (DSemanticDecorator target : targetContents_p.getDiagramElements(targetSemantic_p, mapping_p)) {
      if (target instanceof DEdge) {
        DEdge edgeTarget = (DEdge) target;
        if (DiagramDescriptionHelper.getService(context_p).isReconciliable(context_p, sourceDescription_p, edgeTarget,
            sourceNode_p, targetNode_p)) {
          return (DEdge) target;
        }
      }
    }

    DDiagramElement targetView = DiagramServices.getDiagramServices().createEdge(mapping_p, (EdgeTarget) sourceNode_p,
        (EdgeTarget) targetNode_p, targetSemantic_p);
    return targetView;
  }

  public Collection<EObject> getTargetSemantics(IContext context_p, EObject sourceSemantic_p,
      RepresentationDescription sourceDescription_p, RepresentationDescription targetDescription_p) {
    return TraceabilityHelper.getService(context_p).getAllocatingElements(context_p, sourceSemantic_p);
  }

  @Override
  public EObject getTargetSemantic(IContext context_p, EObject sourceSemantic_p,
      RepresentationDescription sourceDescription_p, RepresentationDescription targetDescription_p) {

    EObject target = _getTargetSemantic(context_p, sourceSemantic_p, sourceDescription_p, targetDescription_p);
    if (target != null) {
      return target;
    }

    TraceableElement architecture = BlockArchitectureExt.getRootBlockArchitecture(sourceSemantic_p);
    target = _getTargetSemantic(context_p, architecture, sourceDescription_p, targetDescription_p);

    if (target != null) {
      EObject targetArchitecture = target;
      if (targetArchitecture instanceof BlockArchitecture) {
        return DiagramDescriptionHelper.getService(context_p).getTargetDefaultLocation(context_p,
            (BlockArchitecture) targetArchitecture, targetDescription_p);
      }
    }
    return null;
  }

  /**
   * @param sourceSemantic_p
   * @param sourceDescription_p
   * @param targetDescription_p
   * @return
   */
  protected EObject _getTargetSemantic(IContext context_p, EObject sourceSemantic_p,
      RepresentationDescription sourceDescription_p, RepresentationDescription targetDescription_p) {
    Collection<EObject> result = getTargetSemantics(context_p, sourceSemantic_p, sourceDescription_p,
        targetDescription_p);
    if (!result.isEmpty()) {
      return result.iterator().next();
    }
    return null;
  }

}
