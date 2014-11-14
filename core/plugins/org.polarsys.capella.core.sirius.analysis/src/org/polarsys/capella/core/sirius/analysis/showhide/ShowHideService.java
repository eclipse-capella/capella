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
package org.polarsys.capella.core.sirius.analysis.showhide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.sirius.analysis.CapellaServices;
import org.polarsys.capella.core.sirius.analysis.ContextServices;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.actions.extensions.SelectElementsFromTransferWizard;
import org.polarsys.capella.core.sirius.analysis.showhide.AbstractShowHide.DiagramContext;

/**
 */
public class ShowHideService {

  private static ShowHideService singleton = null;

  public static ShowHideService getService() {
    if (null == singleton) {
      singleton = new ShowHideService();
    }
    return singleton;
  }

  /**
   * @param view_p
   * @param selectedElements_p
   * @param visibleElments_p
   * @param visibleElementViews_p
   * @param showHideHandler_p
   * @return
   */
  public DSemanticDecorator applyShowHide(DSemanticDecorator view_p, List<EObject> selectedElements_p, List<EObject> visibleElments_p,
      List<DDiagramElement> visibleElementViews_p, AbstractShowHide showHideHandler_p) {

    DiagramContext diagramContext = showHideHandler_p.new DiagramContext();

    // show
    for (EObject eObj : selectedElements_p) {
      showHideHandler_p.show(eObj, diagramContext);
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

  public void genericSelectElements(DSemanticDecorator view_p) {

    String eClass = (String) InterpreterUtil.getInterpreter(view_p).getVariable(SEMANTIC_CANDIDATE);
    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put(CONTEXT, view_p);

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(view_p);

    List<EObject> scope_p = getShowHideAvailableElements(eClass, diagram);
    parameters.put(SCOPE, scope_p);

    parameters.put(WIZARD_MESSAGE, Messages.ShowHideService_Select_element_wizard_message);

    List<EObject> initialSelection = getAllDisplayedElements(eClass, diagram);
    parameters.put(INITIAL_SELECTION, initialSelection);
    parameters.put(RESULT_VARIABLE, RESULT_VARIABLE);

    new SelectElementsFromTransferWizard().execute(null, parameters);
    return;
  }

  public void genericShowHideElements(DSemanticDecorator view_p) {
    @SuppressWarnings("unchecked")
    List<EObject> selectedElements = (List<EObject>) InterpreterUtil.getInterpreter(view_p).getVariable(RESULT_VARIABLE);
    String eClass = (String) InterpreterUtil.getInterpreter(view_p).getVariable(SEMANTIC_CANDIDATE);

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(view_p);
    DiagramElementMapping mapping = DiagramServices.getDiagramServices().getMapping(eClass, diagram);

    List<EObject> visibleElments = (List<EObject>) getAllDisplayedElementsForMapping(view_p, mapping);

    List<DDiagramElement> visibleElementViews = (List<DDiagramElement>) getAllDisplayedViewsForMapping(view_p, mapping);

    if (selectedElements == null) {
      selectedElements = new ArrayList<EObject>();
    }
    if (visibleElments == null) {
      visibleElments = new ArrayList<EObject>();
    }
    if (visibleElementViews == null) {
      visibleElementViews = new ArrayList<DDiagramElement>();
    }

    DDiagramContents content = new DDiagramContents(diagram);

    AbstractShowHide shHide = ShowHideFactory.createShowHide(eClass, view_p.getTarget(), content);

    ShowHideService.getService().applyShowHide(view_p, selectedElements, visibleElments, visibleElementViews, shHide);

  }

  /**
   * @param eClass_p
   * @param diagram_p
   * @return
   */
  private List<EObject> getAllDisplayedElements(String eClass_p, DDiagram diagram_p) {
    List<EObject> result = new ArrayList<EObject>();
    for (DDiagramElement dElt : diagram_p.getDiagramElements()) {
      if (dElt.getTarget().eClass().getName().equals(eClass_p)) {
        result.add(dElt.getTarget());
      }
    }
    return result;
  }

  private List<EObject> getShowHideAvailableElements(String type, DDiagram diagram_p) {
    if (IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME.equals(diagram_p.getDescription().getName())) {
      if (CtxPackage.Literals.CAPABILITY.getName().equals(type)) {
        return ContextServices.getServices().getMCBCapabilities((DSemanticDecorator) diagram_p);
      } else if (CtxPackage.Literals.ACTOR.getName().equals(type)) {
        return ContextServices.getServices().getAvailableActorsToInsertInMCB2((DSemanticDecorator) diagram_p);
      } else if (CtxPackage.Literals.MISSION.getName().equals(type)) {
        return ContextServices.getServices().getMCBMissions((DSemanticDecorator) diagram_p);
      }
    }

    return null;
  }

  public Collection<DDiagramElement> getAllDisplayedViewsForMapping(DSemanticDecorator diagramElt_p, DiagramElementMapping mapping_p) {
    return getAllDisplayedViewsForMappings(diagramElt_p, Arrays.asList(mapping_p));
  }

  public Collection<DDiagramElement> getAllDisplayedViewsForMappings(DSemanticDecorator diagramElt_p, List<DiagramElementMapping> mappings_p) {
    List<DDiagramElement> diagElements = new ArrayList<DDiagramElement>();
    if (diagramElt_p instanceof DDiagram) {
      DDiagram diagram = (DDiagram) diagramElt_p;
      DDiagramContents diagContents = new DDiagramContents(diagram);
      for (DiagramElementMapping mapping : mappings_p) {
        for (DDiagramElement element : diagContents.getDiagramElements(mapping)) {
          diagElements.add(element);
        }
      }
    }
    return diagElements;
  }

  public Collection<EObject> getAllDisplayedElementsForMapping(DSemanticDecorator diagramElt_p, DiagramElementMapping mapping_p) {
    return getAllDisplayedElementsForMappings(diagramElt_p, Arrays.asList(mapping_p));
  }

  public Collection<EObject> getAllDisplayedElementsForMappings(DSemanticDecorator diagramElt_p, List<DiagramElementMapping> mappings_p) {
    List<EObject> diagElements = new ArrayList<EObject>();
    for (DDiagramElement diagElt : getAllDisplayedViewsForMappings(diagramElt_p, mappings_p)) {
      diagElements.add(diagElt.getTarget());
    }
    return diagElements;

  }

  protected static final String CONTEXT = "context"; //$NON-NLS-1$
  protected static final String SCOPE = "scope"; //$NON-NLS-1$
  protected static final String WIZARD_MESSAGE = "wizardMessage"; //$NON-NLS-1$
  protected static final String WIZARD_TITLE = "wizardTitle"; //$NON-NLS-1$  
  protected static final String RESULT_VARIABLE = "resultVariable"; //$NON-NLS-1$
  protected static final String INITIAL_SELECTION = "initialSelection"; //$NON-NLS-1$
  protected static final String SEMANTIC_CANDIDATE = "semanticCandidate"; //$NON-NLS-1$
}
