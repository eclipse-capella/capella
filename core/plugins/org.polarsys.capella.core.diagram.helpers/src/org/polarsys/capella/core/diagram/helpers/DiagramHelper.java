/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.diagram.helpers;

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.diagram.helpers.naming.DiagramDescriptionConstants;

/**
 *
 */
public class DiagramHelper {

  private static DiagramHelper instance;

  protected DiagramHelper() {
  }

  public static DiagramHelper getService() {
    if (instance == null) {
      instance = new DiagramHelper();
    }
    return instance;
  }

  public RepresentationDescription getDescription(DRepresentation representation) {
    return DialectManager.INSTANCE.getDescription(representation);
  }

  /**
   * Returns whether the given diagram use the given description
   * 
   * @param diagram
   *          current diagram
   * @param diagramDescriptionId
   *          a DiagramDescriptionConstants
   */
  public boolean isA(DRepresentation diagram, String descriptionId) {
    if (diagram != null) {
      RepresentationDescription description = getDescription(diagram);
      return isA(description, descriptionId);
    }
    return false;
  }

  /**
   * Returns whether the given diagram description is the given description
   * 
   * @param diagram
   *          current diagram
   * @param diagramDescriptionId
   *          a DiagramDescriptionConstants
   */
  public boolean isA(RepresentationDescription description, String diagramDescriptionId) {
    if (description != null) {
      if (diagramDescriptionId == null) {
        return true;
      }
      return diagramDescriptionId.equals(description.getName());
    }
    return false;
  }

  public boolean isArchitectureBlank(DRepresentation diagram) {
    return hasKind(diagram, DiagramDescriptionConstants.ARCHITECTURE_BLANK_DIAGRAM_NAME) || hasKind(diagram, DiagramDescriptionConstants.ENTITY_BLANK_DIAGRAM_NAME);
  }

  public boolean isBreakdown(DRepresentation diagram) {
    return hasKind(diagram, DiagramDescriptionConstants.BREAKDOWN_DIAGRAM_NAME);
  }

  public boolean hasKind(DRepresentation diagram, String diagramDescriptionId) {
    if (diagram != null) {
      RepresentationDescription description = getDescription(diagram);
      return hasKind(description, diagramDescriptionId);
    }
    return false;
  }

  private boolean hasKind(RepresentationDescription description, String diagramDescriptionId) {
    if (description != null) {
      if (diagramDescriptionId == null) {
        return true;
      }
      return description.getName().contains(diagramDescriptionId);
    }
    return false;
  }

  /**
   * Returns the DDiagram owning the given element
   * 
   * @param current
   * @return
   */
  public DDiagram getDiagramContainer(EObject current) {
    if (current instanceof DDiagram) {
      return ((DDiagram) current);
    }
    return (DDiagram) EcoreUtil2.getFirstContainer(current, DiagramPackage.Literals.DDIAGRAM);
  }

  public RepresentationDescription getDescription(Session session, String descriptionId) {
    Collection<Viewpoint> viewpoints = session.getSelectedViewpoints(true);
    for (Viewpoint viewpoint : viewpoints) {
      for (RepresentationDescription description : viewpoint.getOwnedRepresentations()) {
        if (descriptionId.equals(description.getName())) {
          return description;
        }
      }
    }
    return null;
  }

  /**
   * Returns the current session for the given diagram
   * 
   * @param diagram
   * @return
   */
  public Session getSession(DRepresentation diagram) {
    if (diagram instanceof DSemanticDecorator) {
      EObject target = ((DSemanticDecorator) diagram).getTarget();
      if (target == null) {
        return null;
      }
      return SessionManager.INSTANCE.getSession(target);
    }
    return null;
  }

  public DRepresentation createDRepresentation(String name, EObject semantic, RepresentationDescription description, Session session, IProgressMonitor monitor) {
    return DialectManager.INSTANCE.createRepresentation(name, semantic, description, session, monitor);
  }

  /**
   * @param decorator
   * @return
   */
  public DRepresentation getRepresentation(DSemanticDecorator decorator) {
    if (decorator instanceof DRepresentation) {
      return ((DRepresentation) decorator);
    }
    return (DRepresentation) EcoreUtil2.getFirstContainer(decorator, ViewpointPackage.Literals.DREPRESENTATION);
  }

}
