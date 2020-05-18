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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public interface IDiagramHandler extends IHandler {

  /**
   * Returns whether the current handler handles the given description
   * Meaning (covers || backCovers) == true
   * @param context
   * @param representation
   * @return
   */
  boolean handles(IContext context, RepresentationDescription representation);

  /**
   * Returns whether the current handler will transform the given description to another
   * Precondition: handles(representation.getDescription()) == true
   * @param context
   * @param description
   * @return
   */
  boolean covers(IContext context, RepresentationDescription description);

  /**
   * Returns whether the current handler will transform the given diagram to another
   * Precondition: covers(representation.getDescription()) == true
   * @param context
   * @param representation
   * @return
   */
  boolean covers(IContext context, DRepresentationDescriptor representation);

  /**
   * Returns whether the current handler can transform a description to the given representation
   * @param context
   * @param representation
   * @return
   */
  boolean backCovers(IContext context, RepresentationDescription representation);

  /**
   * Returns whether the current handler can transform a description to the given representation
   * @param context
   * @param representation
   * @return
   */
  boolean backCovers(IContext context, DRepresentationDescriptor representation);

  /**
   * Returns for the given mapping the mapping used in the targetDesccription
   * @param context
   * @param sourceDescription
   * @param targetDescription
   * @param sourceMapping
   * @param target
   * @return
   */
  DiagramElementMapping getTargetMapping(IContext context, RepresentationDescription sourceDescription, RepresentationDescription targetDescription,
      DiagramElementMapping sourceMapping, EObject source, EObject target);

  /**
   * Returns for a description its output description
   * @param context
   * @param session
   * @param description
   * @return
   */
  RepresentationDescription getTargetDescription(IContext context, Session session, RepresentationDescription description);

  /**
   * Returns for a given sourceDiagram the name of the output diagram
   * @param context
   * @param diagram
   * @param targetDescription
   * @return
   */
  String getTargetName(IContext context, DRepresentation diagram, RepresentationDescription targetDescription);

  /**
   * Returns a default location for the diagram if semantic target of source diagram is not transitioned in the root_p architecture
   * @param context
   * @param root
   * @param description
   * @return
   */
  EObject getTargetDefaultLocation(IContext context, BlockArchitecture root, RepresentationDescription description);

  /**
   * Returns the target filterDescription
   * @param context
   * @param sourceDescription
   * @param targetDescription
   * @param filterDesctiption
   * @return
   */
  FilterDescription getTargetFilterDescription(IContext context, DiagramDescription sourceDescription, DiagramDescription targetDescription,
      FilterDescription filterDesctiption);

  /**
   * @param context
   * @param sourceDescription
   * @param target
   * @param containerNode
   * @return
   */
  boolean isReconciliable(IContext context, RepresentationDescription sourceDescription, AbstractDNode target, DSemanticDecorator containerNode);

  /**
   * @param context
   * @param sourceDescription
   * @param edgeTarget
   * @param sourceNode
   * @param targetNode
   * @return
   */
  boolean isReconciliable(IContext context, RepresentationDescription sourceDescription, DEdge edgeTarget, DSemanticDecorator sourceNode,
      DSemanticDecorator targetNode);

  /**
   * @param context
   * @param sourceDescription
   * @param targetContents
   * @param mapping
   * @param containerNode
   * @param targetSemantic
   * @return 
   */
  DSemanticDecorator showNode(IContext context, RepresentationDescription sourceDescription, DDiagramContents targetContents,
      AbstractNodeMapping mapping, DSemanticDecorator containerNode, EObject targetSemantic);

  /**
   * @param context
   * @param sourceDescription
   * @param targetContents
   * @param mapping
   * @param sourceNode
   * @param targetNode
   * @param targetSemantic
   * @return
   */
  DDiagramElement showEdge(IContext context, RepresentationDescription sourceDescription, DDiagramContents targetContents, EdgeMapping mapping,
      DSemanticDecorator sourceNode, DSemanticDecorator targetNode, EObject targetSemantic);

  /**
   * @param context
   * @param sourceSemantic
   * @param sourceDescription
   * @param allocatingDescription
   * @return
   */
  Collection<EObject> getTargetSemantics(IContext context, EObject sourceSemantic, RepresentationDescription sourceDescription,
      RepresentationDescription allocatingDescription);

  /**
   * @param context
   * @param sourceSemantic
   * @param sourceDescription
   * @param allocatingDescription
   * @return
   */
  EObject getTargetSemantic(IContext context, EObject sourceSemantic, RepresentationDescription sourceDescription,
      RepresentationDescription targetDescription);
}
