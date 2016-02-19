/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.tools.api.ui.ExternalJavaActionProvider;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.sirius.analysis.CapellaServices;
import org.polarsys.capella.core.sirius.analysis.ContextServices;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.LaServices;
import org.polarsys.capella.core.sirius.analysis.OAServices;
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
   * @param view
   * @param selectedElements
   * @param visibleElments
   * @param visibleElementViews
   * @param showHideHandler
   * @return
   */
  public DSemanticDecorator applyShowHide(DSemanticDecorator view, List<EObject> selectedElements, List<EObject> visibleElments, List<DDiagramElement> visibleElementViews,
      AbstractShowHide showHideHandler) {

    DiagramContext diagramContext = showHideHandler.new DiagramContext();

    // show
    for (EObject eObj : selectedElements) {
      showHideHandler.show(eObj, diagramContext);
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

  public void genericSelectElements(DSemanticDecorator view) {

    String eClass = (String) InterpreterUtil.getInterpreter(view).getVariable(SEMANTIC_CANDIDATE);
    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put(CONTEXT, view);

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(view);

    List<EObject> scope = getShowHideAvailableElements(diagram, view, eClass);
    parameters.put(SCOPE, scope);

    parameters.put(WIZARD_MESSAGE, Messages.ShowHideService_Select_element_wizard_message);

    List<EObject> initialSelection = getAllDisplayedElements(eClass, view);
    parameters.put(INITIAL_SELECTION, initialSelection);
    parameters.put(RESULT_VARIABLE, RESULT_VARIABLE);

    IExternalJavaAction action= ExternalJavaActionProvider.INSTANCE.getJavaActionById(getJavaActionId());
    action.execute(null, parameters);
  }
  
  protected String getJavaActionId() {
    // org.polarsys.capella.core.sirius.analysis.actions.extensions.SelectElementsFromTransferWizard
    return SelectElementsFromTransferWizard.class.getName();
  }


  @SuppressWarnings({ "unchecked", "rawtypes" })
  public void genericShowHideElements(DSemanticDecorator view) {
	  List<EObject> selectedElements = null;
    Object variable = InterpreterUtil.getInterpreter(view).getVariable(RESULT_VARIABLE);
    if(variable instanceof Object[]){
    	selectedElements = (List<EObject>) (List)Arrays.asList((Object[])variable);
    }else{
    	selectedElements = (List<EObject>) variable;
    }
    String eClass = (String) InterpreterUtil.getInterpreter(view).getVariable(SEMANTIC_CANDIDATE);

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(view);
    DiagramElementMapping mapping = DiagramServices.getDiagramServices().getMapping(eClass, diagram);

    List<EObject> visibleElments = (List<EObject>) getAllDisplayedElementsForMapping(view, mapping);

    List<DDiagramElement> visibleElementViews = (List<DDiagramElement>) getAllDisplayedViewsForMapping(view, mapping);

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

    AbstractShowHide shHide = ShowHideFactory.createShowHide(eClass, view.getTarget(), content);

    ShowHideService.getService().applyShowHide(view, selectedElements, visibleElments, visibleElementViews, shHide);

  }

  /**
   * @param eClass
   * @param diagram
   * @return
   */
  private List<EObject> getAllDisplayedElements(String eClass, DSemanticDecorator view) {
    List<EObject> result = new ArrayList<EObject>();
    Collection<DDiagramElement> allNodes = DiagramServices.getDiagramServices().getAllAbstractNodes(view);
    for (DDiagramElement dElt : allNodes) {
      if (dElt.getTarget().eClass().getName().equals(eClass)) {
        result.add(dElt.getTarget());
      }
    }
    return result;
  }

  private List<EObject> getShowHideAvailableElements(DDiagram diagram, DSemanticDecorator view, String type) {
    if (IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME.equals(diagram.getDescription().getName())) {
      if (CtxPackage.Literals.CAPABILITY.getName().equals(type)) {
        return ContextServices.getServices().getMCBCapabilities((DSemanticDecorator) diagram);
      } else if (CtxPackage.Literals.ACTOR.getName().equals(type)) {
        return ContextServices.getServices().getAvailableActorsToInsertInMCB2((DSemanticDecorator) diagram);
      } else if (CtxPackage.Literals.MISSION.getName().equals(type)) {
        return ContextServices.getServices().getMCBMissions((DSemanticDecorator) diagram);
      }
    }else if(IDiagramNameConstants.MISSIONS_BLANK_DIAGRAM_NAME.equals(diagram.getDescription().getName())){
      if (CtxPackage.Literals.CAPABILITY.getName().equals(type)) {
        return ContextServices.getServices().getMBCapabilities((DSemanticDecorator) diagram);
      } else if (CtxPackage.Literals.ACTOR.getName().equals(type)) {
        return ContextServices.getServices().getAvailableActorsToInsertInMB((DSemanticDecorator) diagram);
      } else if (CtxPackage.Literals.MISSION.getName().equals(type)) {
        return ContextServices.getServices().getMBMissions((DSemanticDecorator) diagram);
      }
    }else if(IDiagramNameConstants.CONTEXTUAL_CAPABILITY_DIAGRAM_NAME.equals(diagram.getDescription().getName())){
      if (CtxPackage.Literals.CAPABILITY.getName().equals(type)) {
        return ContextServices.getServices().getCCCapabilities((DSemanticDecorator) diagram);
      } else if (CtxPackage.Literals.ACTOR.getName().equals(type)) {
        return ContextServices.getServices().getAvailableActorsToInsertInCC((DSemanticDecorator) diagram);
      } else if (CtxPackage.Literals.MISSION.getName().equals(type)) {
        return ContextServices.getServices().getCCMissions((DSemanticDecorator) diagram);
      }
    }else if(IDiagramNameConstants.CONTEXTUAL_MISSION_DIAGRAM_NAME.equals(diagram.getDescription().getName())){
      if (CtxPackage.Literals.CAPABILITY.getName().equals(type)) {
        return ContextServices.getServices().getCMCapabilities((DSemanticDecorator) diagram);
      } else if (CtxPackage.Literals.ACTOR.getName().equals(type)) {
        return ContextServices.getServices().getAvailableActorsToInsertInCM((DSemanticDecorator) diagram);
      }
    }else if(IDiagramNameConstants.CONTEXTUAL_OPERATIONAL_CAPABILITIES__DIAGRAM_NAME.equals(diagram.getDescription().getName())){
      if (OaPackage.Literals.OPERATIONAL_CAPABILITY.getName().equals(type)) {
        return OAServices.getService().getCOCOperationalCapabilities((DSemanticDecorator) diagram);
      } else if (OaPackage.Literals.OPERATIONAL_ACTOR.getName().equals(type)) {
        return OAServices.getService().getAvailableOperationalActorsToInsertInCOC((DSemanticDecorator) diagram);
      } else if (OaPackage.Literals.ENTITY.getName().equals(type)) {
        return OAServices.getService().getCOCEntities((DSemanticDecorator) diagram);
      }
    }else if(IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME.equals(diagram.getDescription().getName())){
      if (OaPackage.Literals.OPERATIONAL_CAPABILITY.getName().equals(type)) {
        return OAServices.getService().getOCBOperationalCapabilities((DSemanticDecorator) diagram);
      } else if (OaPackage.Literals.OPERATIONAL_ACTOR.getName().equals(type)) {
        return OAServices.getService().getAvailableOperationalActorsToInsertInOCB((DSemanticDecorator) diagram);
      } else if (OaPackage.Literals.ENTITY.getName().equals(type)) {
        return OAServices.getService().getOCBEntities((DSemanticDecorator) diagram);
      }
    }else if(IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK.equals(diagram.getDescription().getName())){
      if (LaPackage.Literals.CAPABILITY_REALIZATION.getName().equals(type)) {
        return LaServices.getInstance().getCRBCapabilityRealizations((DSemanticDecorator) diagram);
      } else if (LaPackage.Literals.LOGICAL_ACTOR.getName().equals(type)) {
        return LaServices.getInstance().getAvailableLogicalActorsToInsertInCRB((DSemanticDecorator) diagram);
      } else if (LaPackage.Literals.LOGICAL_COMPONENT.getName().equals(type)) {
        return LaServices.getInstance().getCRBComponents(view);
      }
    }
    return null;
  }

  public Collection<DDiagramElement> getAllDisplayedViewsForMapping(DSemanticDecorator diagramElt, DiagramElementMapping mapping) {
    return getAllDisplayedViewsForMappings(diagramElt, Arrays.asList(mapping));
  }

  public Collection<DDiagramElement> getAllDisplayedViewsForMappings(DSemanticDecorator diagramElt, List<DiagramElementMapping> mappings) {
    List<DDiagramElement> diagElements = new ArrayList<DDiagramElement>();
    if (diagramElt instanceof DDiagram) {
      DDiagram diagram = (DDiagram) diagramElt;
      DDiagramContents diagContents = new DDiagramContents(diagram);
      for (DiagramElementMapping mapping : mappings) {
        for (DDiagramElement element : diagContents.getDiagramElements(mapping)) {
          diagElements.add(element);
        }
      }
    }
    return diagElements;
  }

  public Collection<EObject> getAllDisplayedElementsForMapping(DSemanticDecorator diagramElt, DiagramElementMapping mapping) {
    return getAllDisplayedElementsForMappings(diagramElt, Arrays.asList(mapping));
  }

  public Collection<EObject> getAllDisplayedElementsForMappings(DSemanticDecorator diagramElt, List<DiagramElementMapping> mappings) {
    List<EObject> diagElements = new ArrayList<EObject>();
    for (DDiagramElement diagElt : getAllDisplayedViewsForMappings(diagramElt, mappings)) {
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
