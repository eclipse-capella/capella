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
package org.polarsys.capella.core.platform.sirius.refinement.processor;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DDiagram;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.interaction.RefinementLink;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.refinement.preferences.services.RefinementPrefServices;
import org.polarsys.capella.core.refinement.scenarios.core.exceptions.ProcessorException;
import org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor;
import org.polarsys.capella.core.sirius.ui.actions.NewRepresentationAction;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 */
public class DiagramCreation implements IProcessor {

  protected ModelElement contextElement;
  protected NamedElement targetElement;

  /**
   * 
   */
  public DiagramCreation() {
    // do nothing
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#execute(org.eclipse.core.runtime.IProgressMonitor)
   */
  public void execute(IProgressMonitor progressMonitor_p) throws ProcessorException {
    boolean createDiagram = RefinementPrefServices.isRefinedDiagramCreationAllowed();
    if (createDiagram && (contextElement != null) && (targetElement != null)) {
      ModelElement tgtElt = null;
      for (AbstractTrace trace : ((CapellaElement) contextElement).getIncomingTraces()) {
        if (trace instanceof RefinementLink) {
          TraceableElement srcElt = trace.getSourceElement();
          if (EcoreUtil2.isContainedBy(srcElt, targetElement)) {
            tgtElt = srcElt;
          }
        }
      }
      if (tgtElt != null) {
        Session session = SessionManager.INSTANCE.getSession(tgtElt);
        if (session != null) {
          RepresentationDescription diagramRepresentation = getDiagramRepresentation(session, tgtElt);
          if ((null != diagramRepresentation) && DialectManager.INSTANCE.canCreate(tgtElt, diagramRepresentation)) {
            // if a scenario diagram already exists, do nothing
            for (DRepresentation representation : DialectManager.INSTANCE.getRepresentations(tgtElt, session)) {
              if (representation instanceof DDiagram) {
                if (diagramRepresentation.equals(((DDiagram) representation).getDescription())) {
                  return;
                }
              }
            }
            boolean openDiagram = RefinementPrefServices.isRefinedDiagramOpeningAllowed();
            // otherwise, creates a new scenario diagram
            NewRepresentationAction newDiagramAction = new NewRepresentationAction(diagramRepresentation, tgtElt, session, true, openDiagram);
            newDiagramAction.run();
          }
        }
      }
    }
  }

  /**
   * Get the diagram representation specified parameters.
   * @return <code>null</code> if not found.
   */
  protected RepresentationDescription getDiagramRepresentation(Session session_p, ModelElement modelElement_p) {
    Collection<Viewpoint> activeViewpoints = session_p.getSelectedViewpoints(false);
    Collection<RepresentationDescription> diagramDescriptions =
        DialectManager.INSTANCE.getAvailableRepresentationDescriptions(activeViewpoints, modelElement_p);
    // Loop over retrieved diagram descriptions to search one matching search diagram name.
    for (RepresentationDescription diagramDescription : diagramDescriptions) {
      if (IDiagramNameConstants.INTERFACE_SCENARIO_DIAGRAM_NAME.equals(diagramDescription.getName())) {
        return diagramDescription;
      }
    }
    return null;
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#getResult()
   */
  public Object getResult() {
    return null;
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#setContext(org.polarsys.capella.common.data.modellingcore.ModelElement)
   */
  public void setContext(ModelElement context_p) {
    contextElement = context_p;
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#setContext(java.util.List)
   */
  public void setContext(List<ModelElement> context_p) {
    // do nothing
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#setTarget(org.polarsys.capella.core.data.capellacore.NamedElement)
   */
  public void setTarget(NamedElement target_p) {
    targetElement = target_p;
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IRefinementPlug#getName()
   */
  public Object getName() {
    return "Diagram Creation"; //$NON-NLS-1$
  }
}
