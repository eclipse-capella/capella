/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis.showhide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.tools.api.ui.ExternalJavaActionProvider;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.sirius.analysis.CapellaServices;
import org.polarsys.capella.core.sirius.analysis.ContextServices;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;
import org.polarsys.capella.core.sirius.analysis.actions.extensions.SelectElementsFromTransferWizard;
import org.polarsys.capella.core.sirius.analysis.showhide.AbstractShowHide.DiagramContext;

/**
 */
public class ShowHideService {

  protected static final String CONTEXT = "context"; //$NON-NLS-1$
  protected static final String SCOPE = "scope"; //$NON-NLS-1$
  protected static final String WIZARD_MESSAGE = "wizardMessage"; //$NON-NLS-1$
  protected static final String WIZARD_TITLE = "wizardTitle"; //$NON-NLS-1$
  protected static final String RESULT_VARIABLE = "resultVariable"; //$NON-NLS-1$
  protected static final String INITIAL_SELECTION = "initialSelection"; //$NON-NLS-1$
  protected static final String SEMANTIC_CANDIDATE = "semanticCandidate"; //$NON-NLS-1$

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
  @Deprecated
  public DSemanticDecorator applyShowHide(DSemanticDecorator view, List<EObject> selectedElements,
      List<EObject> visibleElments, List<DDiagramElement> visibleElementViews, AbstractShowHide showHideHandler) {
    return applyShowHide(view, selectedElements, visibleElementViews, showHideHandler);
  }

  public DSemanticDecorator applyShowHide(DSemanticDecorator view, List<EObject> selectedElements,
      Iterable<DDiagramElement> visibleElementViews, AbstractShowHide showHideHandler) {

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

    String eClassName = (String) InterpreterUtil.getInterpreter(view).getVariable(SEMANTIC_CANDIDATE);
    EClass eClass = getEClassFromName(eClassName);

    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put(CONTEXT, view);

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(view);
    DDiagramContents content = new DDiagramContents(diagram);
    DiagramElementMapping mapping = ShowHideService.getService().getMapping(eClass, diagram);

    List<EObject> scope = getShowHideAvailableElements(diagram, view, eClass);
    if (scope == null) {
      scope = Collections.emptyList();
    }
    parameters.put(SCOPE, scope);
    parameters.put(WIZARD_MESSAGE, Messages.ShowHideService_Select_element_wizard_message);

    Collection<EObject> initialSelection = content.asSemantic(content.getDiagramElements(view, mapping));
    initialSelection.retainAll(scope);

    parameters.put(INITIAL_SELECTION, initialSelection);
    parameters.put(RESULT_VARIABLE, RESULT_VARIABLE);

    IExternalJavaAction action = ExternalJavaActionProvider.INSTANCE.getJavaActionById(getJavaActionId());
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
    if (variable instanceof Object[]) {
      selectedElements = (List) Arrays.asList((Object[]) variable);
    } else {
      selectedElements = (List<EObject>) variable;
    }
    if (selectedElements == null) {
      selectedElements = new ArrayList<EObject>();
    }

    String eClassName = (String) InterpreterUtil.getInterpreter(view).getVariable(SEMANTIC_CANDIDATE);
    EClass eClass = getEClassFromName(eClassName);

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(view);
    DDiagramContents content = new DDiagramContents(diagram);
    DiagramElementMapping mapping = ShowHideService.getService().getMapping(eClass, diagram);

    AbstractShowHide shHide = ShowHideFactory.createShowHide(eClass, view.getTarget(), content);

    ShowHideService.getService().applyShowHide(view, selectedElements, content.getDiagramElements(view, mapping),
        shHide);
  }

  /**
   * @param eClass
   * @return
   */
  private EClass getEClassFromName(String eClass) {

    if (InteractionPackage.Literals.ABSTRACT_CAPABILITY.getName().equals(eClass)) {
      return InteractionPackage.Literals.ABSTRACT_CAPABILITY;

    } else if (LaPackage.Literals.CAPABILITY_REALIZATION.getName().equals(eClass)) {
      return LaPackage.Literals.CAPABILITY_REALIZATION;

    } else if (CtxPackage.Literals.MISSION.getName().equals(eClass)) {
      return CtxPackage.Literals.MISSION;

    } else if (OaPackage.Literals.ENTITY.getName().equals(eClass)) {
      return OaPackage.Literals.ENTITY;

    } else if (CsPackage.Literals.COMPONENT.getName().equals(eClass)) {
      return CsPackage.Literals.COMPONENT;

    }

    return null;
  }

  /**
   * @param eClass
   * @param diagram
   * @return
   */
  public DiagramElementMapping getMapping(EClass eClass, DDiagram diagram) {
    if (IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME.equals(diagram.getDescription().getName())) {
      if (InteractionPackage.Literals.ABSTRACT_CAPABILITY.isSuperTypeOf(eClass)) {
        return DiagramServices.getDiagramServices().getNodeMapping(diagram,
            IMappingNameConstants.MCB_CAPABILITY_MAPPING_NAME);
      }
      if (CtxPackage.Literals.MISSION.isSuperTypeOf(eClass)) {
        return DiagramServices.getDiagramServices().getNodeMapping(diagram,
            IMappingNameConstants.MCB_MISSION_MAPPING_NAME);
      }

    } else if (IDiagramNameConstants.MISSIONS_BLANK_DIAGRAM_NAME.equals(diagram.getDescription().getName())) {
      if (InteractionPackage.Literals.ABSTRACT_CAPABILITY.isSuperTypeOf(eClass)) {
        return DiagramServices.getDiagramServices().getNodeMapping(diagram,
            IMappingNameConstants.MB_CAPABILITY_MAPPING_NAME);
      }
      if (CtxPackage.Literals.MISSION.isSuperTypeOf(eClass)) {
        return DiagramServices.getDiagramServices().getNodeMapping(diagram,
            IMappingNameConstants.MB_MISSION_MAPPING_NAME);
      }

    } else if (IDiagramNameConstants.CONTEXTUAL_CAPABILITY_DIAGRAM_NAME.equals(diagram.getDescription().getName())) {
      if (InteractionPackage.Literals.ABSTRACT_CAPABILITY.isSuperTypeOf(eClass)) {
        return DiagramServices.getDiagramServices().getNodeMapping(diagram,
            IMappingNameConstants.CC_CAPABILITY_MAPPING_NAME);
      }
      if (CtxPackage.Literals.MISSION.isSuperTypeOf(eClass)) {
        return DiagramServices.getDiagramServices().getNodeMapping(diagram,
            IMappingNameConstants.CC_MISSION_MAPPING_NAME);
      }

    } else if (IDiagramNameConstants.CONTEXTUAL_MISSION_DIAGRAM_NAME.equals(diagram.getDescription().getName())) {
      if (InteractionPackage.Literals.ABSTRACT_CAPABILITY.isSuperTypeOf(eClass)) {
        return DiagramServices.getDiagramServices().getNodeMapping(diagram,
            IMappingNameConstants.CM_CAPABILITY_MAPPING_NAME);
      }

    } else if (IDiagramNameConstants.CONTEXTUAL_OPERATIONAL_CAPABILITIES__DIAGRAM_NAME
        .equals(diagram.getDescription().getName())) {
      if (InteractionPackage.Literals.ABSTRACT_CAPABILITY.isSuperTypeOf(eClass)) {
        return DiagramServices.getDiagramServices().getNodeMapping(diagram, IMappingNameConstants.COC_OC_MAPPING_NAME);
      }
      if (OaPackage.Literals.ENTITY.isSuperTypeOf(eClass)) {
        return DiagramServices.getDiagramServices().getContainerMapping(diagram,
            IMappingNameConstants.COC_ENTITY_MAPPING_NAME);
      }

    } else if (IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME
        .equals(diagram.getDescription().getName())) {
      if (InteractionPackage.Literals.ABSTRACT_CAPABILITY.isSuperTypeOf(eClass)) {
        return DiagramServices.getDiagramServices().getNodeMapping(diagram,
            IMappingNameConstants.OCB_OPERATIONAL_CAPABILITY_MAPPING_NAME);
      }
      if (OaPackage.Literals.ENTITY.isSuperTypeOf(eClass)) {
        return DiagramServices.getDiagramServices().getContainerMapping(diagram,
            IMappingNameConstants.OCB_OPERATIONAL_ENTITY_MAPPING_NAME);
      }

    } else if (IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK.equals(diagram.getDescription().getName())) {
      if (InteractionPackage.Literals.ABSTRACT_CAPABILITY.isSuperTypeOf(eClass)) {
        return DiagramServices.getDiagramServices().getNodeMapping(diagram,
            IMappingNameConstants.CRB_CAPABILITY_REALIZATION_MAPPING);
      }
      if (CsPackage.Literals.COMPONENT.isSuperTypeOf(eClass)) {
        return DiagramServices.getDiagramServices().getContainerMapping(diagram,
            IMappingNameConstants.CRB_COMPONENT_MAPPING);
      }

    } else if (IDiagramNameConstants.CONTEXTUAL_CAPABILITY_REALIZATION_INVOLVEMENT
        .equals(diagram.getDescription().getName())) {
      if (CsPackage.Literals.COMPONENT.isSuperTypeOf(eClass)) {
        return DiagramServices.getDiagramServices().getNodeMapping(diagram, IMappingNameConstants.CCRI_COMPONENT);

      } else if (LaPackage.Literals.CAPABILITY_REALIZATION.isSuperTypeOf(eClass)) {
        return DiagramServices.getDiagramServices().getNodeMapping(diagram,
            IMappingNameConstants.CCRI_CAPABILITY_REALIZATION);
      }
    }
    return null;
  }

  protected List<EObject> getShowHideAvailableElements(DDiagram diagram, DSemanticDecorator view, EClass type) {

    if (IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME.equals(diagram.getDescription().getName())) {
      if (InteractionPackage.Literals.ABSTRACT_CAPABILITY.equals(type)) {
        return ContextServices.getServices().getMCBCapabilities((DSemanticDecorator) diagram);
      } else if (CtxPackage.Literals.MISSION.equals(type)) {
        return ContextServices.getServices().getMCBMissions((DSemanticDecorator) diagram);
      }

    } else if (IDiagramNameConstants.MISSIONS_BLANK_DIAGRAM_NAME.equals(diagram.getDescription().getName())) {
      if (InteractionPackage.Literals.ABSTRACT_CAPABILITY.equals(type)) {
        return ContextServices.getServices().getMBCapabilities((DSemanticDecorator) diagram);
      } else if (CtxPackage.Literals.MISSION.equals(type)) {
        return ContextServices.getServices().getMBMissions((DSemanticDecorator) diagram);
      }

    } else if (IDiagramNameConstants.CONTEXTUAL_CAPABILITY_DIAGRAM_NAME.equals(diagram.getDescription().getName())) {
      if (InteractionPackage.Literals.ABSTRACT_CAPABILITY.equals(type)) {
        return ContextServices.getServices().getCCCapabilities((DSemanticDecorator) diagram);
      } else if (CtxPackage.Literals.MISSION.equals(type)) {
        return ContextServices.getServices().getCCMissions((DSemanticDecorator) diagram);
      }
    } else if (IDiagramNameConstants.CONTEXTUAL_MISSION_DIAGRAM_NAME.equals(diagram.getDescription().getName())) {
      if (InteractionPackage.Literals.ABSTRACT_CAPABILITY.equals(type)) {
        return ContextServices.getServices().getCMCapabilities((DSemanticDecorator) diagram);
      }

    } else if (IDiagramNameConstants.CONTEXTUAL_OPERATIONAL_CAPABILITIES__DIAGRAM_NAME
        .equals(diagram.getDescription().getName())) {
      if (InteractionPackage.Literals.ABSTRACT_CAPABILITY.equals(type)) {
        return ContextServices.getServices().getCOCCapabilities((DSemanticDecorator) diagram);
      } else if (OaPackage.Literals.ENTITY.equals(type)) {
        return ContextServices.getServices().getCOCEntities(view);
      }

    } else if (IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME
        .equals(diagram.getDescription().getName())) {
      if (InteractionPackage.Literals.ABSTRACT_CAPABILITY.equals(type)) {
        return ContextServices.getServices().getOCBCapabilities((DSemanticDecorator) diagram);
      } else if (OaPackage.Literals.ENTITY.equals(type)) {
        return ContextServices.getServices().getOCBEntities(view);
      }

    } else if (IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK.equals(diagram.getDescription().getName())) {
      if (InteractionPackage.Literals.ABSTRACT_CAPABILITY.equals(type)) {
        return ContextServices.getServices().getCRBCapabilities((DSemanticDecorator) diagram);
      } else if (CsPackage.Literals.COMPONENT.equals(type)) {
        return new ArrayList<>(ContextServices.getServices().getCRBComponents(view));
      }

    } else if (IDiagramNameConstants.CONTEXTUAL_CAPABILITY_REALIZATION_INVOLVEMENT
        .equals(diagram.getDescription().getName())) {
      if (CsPackage.Literals.COMPONENT.equals(type)) {
        return ContextServices.getServices().getCRIComponents(view);
      } else if (LaPackage.Literals.CAPABILITY_REALIZATION.equals(type)) {
        return ContextServices.getServices().getCRICapabilityRealizations(view);
      }
    }
    return null;
  }

  @Deprecated
  public Collection<DDiagramElement> getAllDisplayedViewsForMapping(DSemanticDecorator diagramElt,
      DiagramElementMapping mapping) {
    return getAllDisplayedViewsForMappings(diagramElt, Arrays.asList(mapping));
  }

  @Deprecated
  public Collection<DDiagramElement> getAllDisplayedViewsForMappings(DSemanticDecorator diagramElt,
      List<DiagramElementMapping> mappings) {
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

  @Deprecated
  public Collection<EObject> getAllDisplayedElementsForMapping(DSemanticDecorator diagramElt,
      DiagramElementMapping mapping) {
    return getAllDisplayedElementsForMappings(diagramElt, Arrays.asList(mapping));
  }

  @Deprecated
  public Collection<EObject> getAllDisplayedElementsForMappings(DSemanticDecorator diagramElt,
      List<DiagramElementMapping> mappings) {
    List<EObject> diagElements = new ArrayList<EObject>();
    for (DDiagramElement diagElt : getAllDisplayedViewsForMappings(diagramElt, mappings)) {
      diagElements.add(diagElt.getTarget());
    }
    return diagElements;

  }
}
