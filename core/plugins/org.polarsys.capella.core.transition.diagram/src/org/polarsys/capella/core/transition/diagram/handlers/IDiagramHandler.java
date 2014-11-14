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
package org.polarsys.capella.core.transition.diagram.handlers;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.AbstractDNode;
import org.eclipse.sirius.viewpoint.DDiagramElement;
import org.eclipse.sirius.viewpoint.DEdge;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.AbstractNodeMapping;
import org.eclipse.sirius.viewpoint.description.DiagramDescription;
import org.eclipse.sirius.viewpoint.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.description.EdgeMapping;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.filter.FilterDescription;

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
   * @param context_p
   * @param representation_p
   * @return
   */
  public boolean handles(IContext context_p, RepresentationDescription representation_p);

  /**
   * Returns whether the current handler will transform the given description to another
   * Precondition: handles(representation.getDescription()) == true
   * @param context
   * @param description_p
   * @return
   */
  public boolean covers(IContext context, RepresentationDescription description_p);

  /**
   * Returns whether the current handler will transform the given diagram to another
   * Precondition: covers(representation.getDescription()) == true
   * @param context
   * @param description_p
   * @return
   */
  public boolean covers(IContext context, DRepresentation representation_p);

  /**
   * Returns whether the current handler can transform a description to the given representation
   * @param context
   * @param description_p
   * @return
   */
  public boolean backCovers(IContext context, RepresentationDescription representation_p);

  /**
   * Returns whether the current handler can transform a description to the given representation
   * @param context
   * @param description_p
   * @return
   */
  public boolean backCovers(IContext context, DRepresentation representation_p);

  /**
   * Returns for the given mapping the mapping used in the targetDesccription
   * @param context
   * @param sourceDescription_p
   * @param targetDescription_p
   * @param sourceMapping_p
   * @param target_p
   * @return
   */
  public DiagramElementMapping getTargetMapping(IContext context, RepresentationDescription sourceDescription_p, RepresentationDescription targetDescription_p,
      DiagramElementMapping sourceMapping_p, EObject source_p, EObject target_p);

  /**
   * Returns for a description its output description
   * @param context
   * @param session_p
   * @param description_p
   * @return
   */
  public RepresentationDescription getTargetDescription(IContext context, Session session_p, RepresentationDescription description_p);

  /**
   * Returns for a given sourceDiagram the name of the output diagram
   * @param context
   * @param diagram_p
   * @param targetDescription_p
   * @return
   */
  public String getTargetName(IContext context, DRepresentation diagram_p, RepresentationDescription targetDescription_p);

  /**
   * Returns a default location for the diagram if semantic target of source diagram is not transitioned in the root_p architecture
   * @param context
   * @param root_p
   * @param description_p
   * @return
   */
  public EObject getTargetDefaultLocation(IContext context, BlockArchitecture root_p, RepresentationDescription description_p);

  /**
   * Returns the target filterDescription
   * @param context
   * @param description_p
   * @param description2_p
   * @param description3_p
   * @return
   */
  public FilterDescription getTargetFilterDescription(IContext context, DiagramDescription sourceDescription_p, DiagramDescription targetDescription_p,
      FilterDescription filterDesctiption_p);

  /**
   * @param target_p
   * @param containerNode_p
   * @return
   */
  public boolean isReconciliable(IContext context_p, RepresentationDescription sourceDescription_p, AbstractDNode target_p, DSemanticDecorator containerNode_p);

  /**
   * @param edgeTarget_p
   * @param sourceNode_p
   * @param targetNode_p
   * @return
   */
  public boolean isReconciliable(IContext context_p, RepresentationDescription sourceDescription_p, DEdge edgeTarget_p, DSemanticDecorator sourceNode_p,
      DSemanticDecorator targetNode_p);

  /**
   * @param context_p
   * @param targetContents_p
   * @param mapping_p
   * @param containerNode_p
   * @param targetSemantic_p
   * @return 
   */
  public DSemanticDecorator showNode(IContext context_p, RepresentationDescription sourceDescription_p, DDiagramContents targetContents_p,
      AbstractNodeMapping mapping_p, DSemanticDecorator containerNode_p, EObject targetSemantic_p);

  /**
   * @param context_p
   * @param targetContents_p
   * @param mapping_p
   * @param sourceNode_p
   * @param targetNode_p
   * @param targetSemantic_p
   * @return
   */
  public DDiagramElement showEdge(IContext context_p, RepresentationDescription sourceDescription_p, DDiagramContents targetContents_p, EdgeMapping mapping_p,
      DSemanticDecorator sourceNode_p, DSemanticDecorator targetNode_p, EObject targetSemantic_p);

  /**
   * @param sourceSemantic_p
   * @param allocatingDescription_p
   * @return
   */
  public Collection<EObject> getTargetSemantics(IContext context_p, EObject sourceSemantic_p, RepresentationDescription sourceDescription_p,
      RepresentationDescription allocatingDescription_p);

  /**
   * @param context_p
   * @param sourceSemantic_p
   * @param sourceDescription_p
   * @param allocatingDescription_p
   * @return
   */
  public EObject getTargetSemantic(IContext context_p, EObject sourceSemantic_p, RepresentationDescription sourceDescription_p,
      RepresentationDescription targetDescription_p);

}
