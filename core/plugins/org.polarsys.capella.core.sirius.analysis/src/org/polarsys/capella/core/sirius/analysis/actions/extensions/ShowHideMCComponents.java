/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis.actions.extensions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.sirius.analysis.CapellaServices;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.showhide.ShowHideMCComponent;
import org.polarsys.capella.core.sirius.analysis.showhide.ShowHideService;

/**
 * 
 * Java Action to show/hide components/actors for Capability diagrams
 */
public class ShowHideMCComponents extends AbstractExternalJavaAction {

  public static final String ELEMENT_VIEW = "view"; //$NON-NLS-1$
  public static final String SELECTED_COMPONENTS = "selected components"; //$NON-NLS-1$
  public static final String VISIBLE_COMPONENTS = "visible components"; //$NON-NLS-1$
  public static final String VISIBLE_COMPONENT_VIEWS = "visible component views"; //$NON-NLS-1$

  /**
   * @see org.eclipse.sirius.tools.api.ui.IExternalJavaAction#execute(java.util.Collection, java.util.Map)
   */
  @Override
  @SuppressWarnings("unchecked")
  public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {

    DSemanticDecorator view = (DSemanticDecorator) parameters.get(ELEMENT_VIEW);
    List<EObject> selectedActors = (List<EObject>) parameters.get(SELECTED_COMPONENTS);
    List<EObject> visibleActors = (List<EObject>) parameters.get(VISIBLE_COMPONENTS);
    List<DDiagramElement> visibleActorViews = (List<DDiagramElement>) parameters.get(VISIBLE_COMPONENT_VIEWS);

    if (selectedActors == null) {
      selectedActors = new ArrayList<EObject>();
    }
    if (visibleActors == null) {
      visibleActors = new ArrayList<EObject>();
    }
    if (visibleActorViews == null) {
      visibleActorViews = new ArrayList<DDiagramElement>();
    }
    
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(view);
    DDiagramContents content = new DDiagramContents(diagram);
    ShowHideService.getService().applyShowHide(view, selectedActors, visibleActorViews, new ShowHideMCComponent(content));
  }

}
