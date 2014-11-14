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
package org.polarsys.capella.core.diagram.helpers;

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DDiagram;
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

  public static DiagramHelper _instance;

  protected DiagramHelper() {
    //Nothing here
  }

  public static DiagramHelper getService() {
    if (_instance == null) {
      _instance = new DiagramHelper();
    }
    return _instance;
  }

  public RepresentationDescription getDescription(DRepresentation representation_p) {
    return DialectManager.INSTANCE.getDescription(representation_p);
  }

  /**
   * Returns whether the given diagram use the given description
   * @param diagram_p current diagram
   * @param diagramDescriptionId_p a DiagramDescriptionConstants
   */
  public boolean isA(DRepresentation diagram_p, String descriptionId_p) {
    if (diagram_p != null) {
      RepresentationDescription description = getDescription(diagram_p);
      return isA(description, descriptionId_p);
    }
    return false;
  }

  /**
   * Returns whether the given diagram description is the given description
   * @param diagram_p current diagram
   * @param diagramDescriptionId_p a DiagramDescriptionConstants
   */
  public boolean isA(RepresentationDescription description_p, String diagramDescriptionId_p) {
    if (description_p != null) {
      if (diagramDescriptionId_p == null) {
        return description_p == null;
      }
      return diagramDescriptionId_p.equals(description_p.getName());
    }
    return false;
  }

  public boolean isArchitectureBlank(DRepresentation diagram_p) {
    return hasKind(diagram_p, DiagramDescriptionConstants.ARCHITECTURE_BLANK_DIAGRAM_NAME)
           || hasKind(diagram_p, DiagramDescriptionConstants.ENTITY_BLANK_DIAGRAM_NAME);
  }

  public boolean isBreakdown(DRepresentation diagram_p) {
    return hasKind(diagram_p, DiagramDescriptionConstants.BREAKDOWN_DIAGRAM_NAME);
  }

  private boolean hasKind(DRepresentation diagram_p, String diagramDescriptionId_p) {
    if (diagram_p != null) {
      RepresentationDescription description = getDescription(diagram_p);
      return hasKind(description, diagramDescriptionId_p);
    }
    return false;
  }

  private boolean hasKind(RepresentationDescription description_p, String diagramDescriptionId_p) {
    if (description_p != null) {
      if (diagramDescriptionId_p == null) {
        return description_p == null;
      }
      return description_p.getName().contains(diagramDescriptionId_p);
    }
    return false;
  }

  /**
   * Returns the DDiagram owning the given element
   * @param current_p
   * @return
   */
  public DDiagram getDiagramContainer(EObject current_p) {
    if (current_p instanceof DDiagram) {
      return ((DDiagram) current_p);
    }
    return (DDiagram) EcoreUtil2.getFirstContainer(current_p, ViewpointPackage.Literals.DDIAGRAM);
  }

  public RepresentationDescription getDescription(Session session_p, String descriptionId_p) {
    Collection<Viewpoint> viewpoints = session_p.getSelectedViewpoints(true);
    for (Viewpoint viewpoint : viewpoints) {
      for (RepresentationDescription description : viewpoint.getOwnedRepresentations()) {
        if (descriptionId_p.equals(description.getName())) {
          return description;
        }
      }
    }
    return null;
  }

  /**
   * Returns the current session for the given diagram_p
   * @param diagram_p
   * @return
   */
  public Session getSession(DRepresentation diagram_p) {
    if (diagram_p instanceof DSemanticDecorator) {
      EObject target = ((DSemanticDecorator) diagram_p).getTarget();
      if (target == null) {
        return null;
      }
      return SessionManager.INSTANCE.getSession(target);
    }
    return null;
  }

  public DRepresentation createDRepresentation(String name, EObject semantic_p, RepresentationDescription description_p, Session session_p,
      IProgressMonitor monitor_p) {
    DRepresentation diagram = DialectManager.INSTANCE.createRepresentation(name, semantic_p, description_p, session_p, monitor_p);
    return diagram;
  }

  /**
   * @param decorator_p
   * @return
   */
  public DRepresentation getRepresentation(DSemanticDecorator decorator_p) {
    if (decorator_p instanceof DRepresentation) {
      return ((DRepresentation) decorator_p);
    }
    return (DRepresentation) EcoreUtil2.getFirstContainer(decorator_p, ViewpointPackage.Literals.DREPRESENTATION);
  }

}
