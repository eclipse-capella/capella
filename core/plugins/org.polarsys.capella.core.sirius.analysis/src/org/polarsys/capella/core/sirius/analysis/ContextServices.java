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
package org.polarsys.capella.core.sirius.analysis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.helpers.ctx.services.CapabilityPkgExt;
import org.polarsys.capella.core.data.helpers.ctx.services.MissionPkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.sirius.analysis.showhide.AbstractShowHide;
import org.polarsys.capella.core.sirius.analysis.showhide.AbstractShowHide.DiagramContext;

public class ContextServices {
  private static ContextServices singleton = null;

  public static ContextServices getServices() {
    if (singleton == null) {
      singleton = new ContextServices();
    }
    return singleton;
  }

  /**
   * @param current_p
   * @return
   */
  public List<EObject> getMCBCapabilities(DSemanticDecorator current_p) {
    List<EObject> result = new ArrayList<EObject>();

    EObject target = current_p.getTarget();
    if (current_p instanceof DDiagram) {
      if (target instanceof CapabilityPkg) {
        result.addAll(CapabilityPkgExt.getAllCapabilities((CapabilityPkg) target));
      }
    }

    return result;
  }

  /**
   * @param diagram_p
   * @return
   */
  public List<EObject> getMCBMissions(DSemanticDecorator current_p) {
    List<EObject> result = new ArrayList<EObject>();

    EObject target = current_p.getTarget();
    if (current_p instanceof DDiagram) {
      if (target instanceof CapabilityPkg) {
        SystemEngineering sysEng = SystemEngineeringExt.getSystemEngineering((CapellaElement) target);
        SystemAnalysis sysAnalysis = SystemEngineeringExt.getOwnedSystemAnalysis(sysEng);

        result.addAll(MissionPkgExt.getAllMissions(sysAnalysis.getOwnedMissionPkg()));
      }
    }

    return result;
  }

  public List<EObject> getAvailableActorsToInsertInMCB2(DSemanticDecorator current_p) {

    List<EObject> actors = new ArrayList<EObject>();

    EObject target = current_p.getTarget();
    if (current_p instanceof DDiagram) {
      if (target instanceof CapabilityPkg) {
        actors.addAll(SystemEngineeringExt.getAllActors((CapellaElement) target));
      }
    }
    if ((null != target) && (current_p instanceof DNodeContainer) && (target instanceof Actor)) {
      actors.addAll(SystemEngineeringExt.getAllActors((CapellaElement) target));
    }

    return actors;
  }

  public List<Actor> getAvailableActorsToInsertInMCB(DSemanticDecorator current_p) {

    List<Actor> actors = new ArrayList<Actor>();

    EObject target = current_p.getTarget();
    if (current_p instanceof DDiagram) {
      if (target instanceof CapabilityPkg) {
        actors.addAll(SystemEngineeringExt.getAllActors((CapellaElement) target));
      }
    }
    if ((null != target) && (current_p instanceof DNodeContainer) && (target instanceof Actor)) {
      actors.addAll(SystemEngineeringExt.getAllActors((CapellaElement) target));
    }

    return actors;
  }

  /**
   * @param view_p_p
   * @param selectedElements_p
   * @param visibleElments_p
   * @param visibleElementViews_p
   */
  public EObject showHideMCBActors2(DSemanticDecorator view_p, List<EObject> selectedElements_p, List<EObject> visibleElments_p,
      List<DDiagramElement> visibleElementViews_p, AbstractShowHide showHideHandler_p) {

    DiagramContext diagramContext = showHideHandler_p.new DiagramContext();

    // show
    for (EObject actor : selectedElements_p) {
      showHideHandler_p.show(actor, diagramContext);
    }

    // hide
    Set<EObject> toBeRemoved = new HashSet<EObject>();
    for (DDiagramElement node : visibleElementViews_p) {
      if (!selectedElements_p.contains(node.getTarget())) {
        toBeRemoved.add(node.getTarget());
      }
    }
    for (EObject aView : toBeRemoved) {
      showHideHandler_p.hide(aView, diagramContext);
    }

    return view_p;

  }

}
