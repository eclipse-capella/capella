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
package org.polarsys.capella.test.diagram.common.ju.context;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
import org.polarsys.capella.core.diagram.helpers.DiagramHelper;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.test.framework.api.CommonTestMessages;

/**
 *
 */
public class DiagramContext extends SessionContext {

  /**
   * Diagram to test.
   */
  protected DDiagram _diagram;

  protected String diagramIdentifier;

  private Map<String, DDiagramElement> _viewObjectMap;

  public Map<String, DDiagramElement> getViewObjectMap() {
    if (null == _viewObjectMap) {
      _viewObjectMap = new HashMap<String, DDiagramElement>();
    }
    return _viewObjectMap;
  }

  /**
   * @param session
   * @param diagram
   */
  public DiagramContext(DDiagram diagram) {
    super(DiagramHelper.getService().getSession(diagram));
    diagramIdentifier = diagram.getName();
    _diagram = diagram;
  }

  public DDiagram getDiagram() {
    return _diagram;
  }

  public String getDiagramId() {
    return diagramIdentifier;
  }

  public DSemanticDecorator getView(String semanticIdentifier_p) {
    if (diagramIdentifier.equals(semanticIdentifier_p)) {
      return (DSemanticDiagram) _diagram;
    }
    if (getViewObjectMap().containsKey(semanticIdentifier_p)) {
      return getViewObjectMap().get(semanticIdentifier_p);
    }
    return getView(getSemanticElement(semanticIdentifier_p));
  }

  public DDiagramElement getView(EObject semantic) {
    return org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper.getOnDiagram(_diagram, semantic);
  }

  public void putView(String newIdentifier, DDiagramElement view) {
    getViewObjectMap().put(newIdentifier, view);
  }

  public void hasView(String identifier_p) {
    EObject eObject = getSemanticElement(identifier_p);
    boolean result = getView(identifier_p) != null;
    Assert.assertTrue(NLS.bind(CommonTestMessages.objectRepresentationNotAvailableOnDiagram, EObjectLabelProviderHelper.getText(eObject)), result);
  }

  public void hasntView(String identifier_p) {
    EObject eObject = getSemanticElement(identifier_p);
    boolean result = getView(identifier_p) == null;
    Assert.assertTrue(NLS.bind(CommonTestMessages.objectRepresentationStillAvailableOnDiagram, EObjectLabelProviderHelper.getText(eObject)), result);
  }

  public void hasHiddenView(String identifier_p) {
    EObject eObject = getSemanticElement(identifier_p);
    if (getView(identifier_p) instanceof DDiagramElement) {
      boolean result = (getView(identifier_p) != null) && DiagramServices.getDiagramServices().isHidden((DDiagramElement) getView(identifier_p));
      Assert.assertTrue(NLS.bind(CommonTestMessages.objectRepresentationStillAvailableOnDiagram, EObjectLabelProviderHelper.getText(eObject)), result);
    }
    Assert.assertTrue("view is diagram", false);
  }

}
