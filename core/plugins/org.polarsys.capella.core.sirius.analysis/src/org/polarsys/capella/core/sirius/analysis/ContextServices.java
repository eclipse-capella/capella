/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
   * @param current
   * @return
   */
  public List<EObject> getMCBCapabilities(DSemanticDecorator current) {
    List<EObject> result = new ArrayList<EObject>();

    EObject target = current.getTarget();
    if (current instanceof DDiagram) {
      if (target instanceof CapabilityPkg) {
        result.addAll(CapabilityPkgExt.getAllCapabilities((CapabilityPkg) target));
      }
    }

    return result;
  }

  /**
   * @param current
   * @return
   */
  public List<EObject> getMCBMissions(DSemanticDecorator current) {
    List<EObject> result = new ArrayList<EObject>();

    EObject target = current.getTarget();
    if (current instanceof DDiagram) {
      if (target instanceof CapabilityPkg) {
        SystemEngineering sysEng = SystemEngineeringExt.getSystemEngineering((CapellaElement) target);
        SystemAnalysis sysAnalysis = SystemEngineeringExt.getOwnedSystemAnalysis(sysEng);

        result.addAll(MissionPkgExt.getAllMissions(sysAnalysis.getOwnedMissionPkg()));
      }
    }

    return result;
  }

  public List<EObject> getAvailableActorsToInsertInMCB2(DSemanticDecorator current) {

    List<EObject> actors = new ArrayList<EObject>();

    EObject target = current.getTarget();
    if (current instanceof DDiagram) {
      if (target instanceof CapabilityPkg) {
        actors.addAll(SystemEngineeringExt.getAllActors((CapellaElement) target));
      }
    }
    if ((null != target) && (current instanceof DNodeContainer) && (target instanceof Actor)) {
      actors.addAll(SystemEngineeringExt.getAllActors((CapellaElement) target));
    }

    return actors;
  }

  public List<Actor> getAvailableActorsToInsertInMCB(DSemanticDecorator current) {

    List<Actor> actors = new ArrayList<Actor>();

    EObject target = current.getTarget();
    if (current instanceof DDiagram) {
      if (target instanceof CapabilityPkg) {
        actors.addAll(SystemEngineeringExt.getAllActors((CapellaElement) target));
      }
    }
    if ((null != target) && (current instanceof DNodeContainer) && (target instanceof Actor)) {
      actors.addAll(SystemEngineeringExt.getAllActors((CapellaElement) target));
    }

    return actors;
  }

  /**
   * @param view
   * @param selectedElements
   * @param visibleElements
   * @param visibleElementViews
   * @param showHideHandler
   */
  public EObject showHideMCBActors2(DSemanticDecorator view, List<EObject> selectedElements, List<EObject> visibleElements,
      List<DDiagramElement> visibleElementViews, AbstractShowHide showHideHandler) {

    DiagramContext diagramContext = showHideHandler.new DiagramContext();

    // show
    for (EObject actor : selectedElements) {
      showHideHandler.show(actor, diagramContext);
    }

    // hide
    Set<EObject> toBeRemoved = new HashSet<EObject>();
    for (DDiagramElement node : visibleElementViews) {
      if (!selectedElements.contains(node.getTarget())) {
        toBeRemoved.add(node.getTarget());
      }
    }
    for (EObject aView : toBeRemoved) {
      showHideHandler.hide(aView, diagramContext);
    }

    return view;
  }
}
