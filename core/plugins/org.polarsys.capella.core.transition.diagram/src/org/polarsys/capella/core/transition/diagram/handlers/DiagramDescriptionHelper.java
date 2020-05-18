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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
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
import org.polarsys.capella.core.diagram.helpers.DiagramHelper;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.transition.diagram.constants.IDiagramConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class DiagramDescriptionHelper implements IDiagramHandler {

  public static IDiagramHandler getService(IContext context_p) {
    IDiagramHandler handler = (IDiagramHandler) context_p.get(IDiagramConstants.DIAGRAM_HANDLER);
    if (handler == null) {
      handler = new DiagramDescriptionHelper();
      context_p.put(IDiagramConstants.DIAGRAM_HANDLER, handler);
    }
    return handler;
  }

  /**
   * @param context_p
   */
  protected void initHandlers(IContext context_p) {
    Collection<IDiagramHandler> handlers = getHandlers(context_p);
    handlers.add(new ArchitectureHandler());
    handlers.add(new DataflowHandler());
    handlers.add(new CapabilitiesHandler());
    handlers.add(new CommonHandler());
    handlers.add(new FunctionalChainHandler());
    handlers.add(new PhysicalPathHandler());
  }

  public Collection<IDiagramHandler> getHandlers(IContext context_p) {
    Collection<IDiagramHandler> handler = (Collection<IDiagramHandler>) context_p.get(IDiagramConstants.DIAGRAM_SUB_HANDLERS);
    if (handler == null) {
      handler = new ArrayList<IDiagramHandler>();
      context_p.put(IDiagramConstants.DIAGRAM_SUB_HANDLERS, handler);
      initHandlers(context_p);
    }
    return handler;
  }

  protected IDiagramHandler getHandler(IContext context_p, RepresentationDescription description_p) {
    HashMap<RepresentationDescription, IDiagramHandler> handlers =
        (HashMap<RepresentationDescription, IDiagramHandler>) context_p.get(IDiagramConstants.DIAGRAM_HANDLER_BY_REPRESENTATION);

    if (handlers == null) {
      handlers = new HashMap<RepresentationDescription, IDiagramHandler>();
      context_p.put(IDiagramConstants.DIAGRAM_HANDLER_BY_REPRESENTATION, handlers);
    }

    if (description_p != null) {
      for (IDiagramHandler handler : getHandlers(context_p)) {
        if (handler.handles(context_p, description_p)) {
          handlers.put(description_p, handler);
          break;
        }
      }
    }
    return handlers.get(description_p);
  }

  @Override
  public boolean covers(IContext context_p, RepresentationDescription description_p) {
    IDiagramHandler handler = getHandler(context_p, description_p);
    if (handler != null) {
      return handler.covers(context_p, description_p);
    }
    return false;
  }

  @Override
  public boolean backCovers(IContext context_p, RepresentationDescription description_p) {
    IDiagramHandler handler = getHandler(context_p, description_p);
    if (handler != null) {
      return handler.backCovers(context_p, description_p);
    }
    return false;
  }

  @Override
  public boolean handles(IContext context_p, RepresentationDescription representation_p) {
    return getHandler(context_p, representation_p) != null;
  }

  @Override
  public boolean covers(IContext context_p, DRepresentationDescriptor representation_p) {
    RepresentationDescription description = representation_p.getDescription();
    IDiagramHandler handler = getHandler(context_p, description);
    if (handler != null) {
      return handler.covers(context_p, representation_p);
    }
    return false;
  }

  public boolean backCovers(IContext context_p, DRepresentationDescriptor representation_p) {
    RepresentationDescription description = representation_p.getDescription();
    IDiagramHandler handler = getHandler(context_p, description);
    if (handler != null) {
      return handler.backCovers(context_p, representation_p);
    }
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public DiagramElementMapping getTargetMapping(IContext context_p, RepresentationDescription sourceDescription_p,
      RepresentationDescription targetDescription_p, DiagramElementMapping sourceMapping_p, EObject source_p, EObject target_p) {
    IDiagramHandler handler = getHandler(context_p, sourceDescription_p);
    if (handler != null) {
      return handler.getTargetMapping(context_p, sourceDescription_p, targetDescription_p, sourceMapping_p, source_p, target_p);
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public RepresentationDescription getTargetDescription(IContext context_p, Session session_p, RepresentationDescription description_p) {
    IDiagramHandler handler = getHandler(context_p, description_p);
    if (handler != null) {
      return handler.getTargetDescription(context_p, session_p, description_p);
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getTargetName(IContext context_p, DRepresentation diagram_p, RepresentationDescription targetDescription_p) {
    RepresentationDescription description = DiagramHelper.getService().getDescription(diagram_p);
    IDiagramHandler handler = getHandler(context_p, description);
    if (handler != null) {

      //Add a timestamp
      String name = handler.getTargetName(context_p, diagram_p, targetDescription_p);
      String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date()); //$NON-NLS-1$
      name = name + NLS.bind(" [{0}]", timestamp);
      return name;

    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EObject getTargetDefaultLocation(IContext context_p, BlockArchitecture root_p, RepresentationDescription description_p) {
    IDiagramHandler handler = getHandler(context_p, description_p);
    if (handler != null) {
      return handler.getTargetDefaultLocation(context_p, root_p, description_p);
    }

    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public FilterDescription getTargetFilterDescription(IContext context_p, DiagramDescription description_p, DiagramDescription description2_p,
      FilterDescription description3_p) {
    IDiagramHandler handler = getHandler(context_p, description_p);
    if (handler != null) {
      return handler.getTargetFilterDescription(context_p, description_p, description2_p, description3_p);
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isReconciliable(IContext context_p, RepresentationDescription sourceDescription_p, AbstractDNode target_p, DSemanticDecorator containerNode_p) {
    IDiagramHandler handler = getHandler(context_p, sourceDescription_p);
    if (handler != null) {
      return handler.isReconciliable(context_p, sourceDescription_p, target_p, containerNode_p);
    }
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isReconciliable(IContext context_p, RepresentationDescription sourceDescription_p, DEdge edgeTarget_p, DSemanticDecorator sourceNode_p,
      DSemanticDecorator targetNode_p) {
    IDiagramHandler handler = getHandler(context_p, sourceDescription_p);
    if (handler != null) {
      return handler.isReconciliable(context_p, sourceDescription_p, edgeTarget_p, sourceNode_p, targetNode_p);
    }
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public DSemanticDecorator showNode(IContext context_p, RepresentationDescription sourceDescription_p, DDiagramContents targetContents_p,
      AbstractNodeMapping mapping_p, DSemanticDecorator containerNode_p, EObject targetSemantic_p) {
    IDiagramHandler handler = getHandler(context_p, sourceDescription_p);
    if (handler != null) {
      return handler.showNode(context_p, sourceDescription_p, targetContents_p, mapping_p, containerNode_p, targetSemantic_p);
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public DDiagramElement showEdge(IContext context_p, RepresentationDescription sourceDescription_p, DDiagramContents targetContents_p, EdgeMapping mapping_p,
      DSemanticDecorator sourceNode_p, DSemanticDecorator targetNode_p, EObject targetSemantic_p) {
    IDiagramHandler handler = getHandler(context_p, sourceDescription_p);
    if (handler != null) {
      return handler.showEdge(context_p, sourceDescription_p, targetContents_p, mapping_p, sourceNode_p, targetNode_p, targetSemantic_p);
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<EObject> getTargetSemantics(IContext context_p, EObject sourceSemantic_p, RepresentationDescription sourceDescription_p,
      RepresentationDescription targetDescription_p) {
    IDiagramHandler handler = getHandler(context_p, sourceDescription_p);
    if (handler != null) {
      return handler.getTargetSemantics(context_p, sourceSemantic_p, sourceDescription_p, targetDescription_p);
    }
    return Collections.emptyList();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus init(IContext context_p) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus dispose(IContext context_p) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EObject getTargetSemantic(IContext context_p, EObject sourceSemantic_p, RepresentationDescription sourceDescription_p,
      RepresentationDescription targetDescription_p) {
    IDiagramHandler handler = getHandler(context_p, sourceDescription_p);
    if (handler != null) {
      return handler.getTargetSemantic(context_p, sourceSemantic_p, sourceDescription_p, targetDescription_p);
    }
    return null;
  }

}
