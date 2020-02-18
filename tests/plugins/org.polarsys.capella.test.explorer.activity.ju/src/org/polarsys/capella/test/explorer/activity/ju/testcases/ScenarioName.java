/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.explorer.activity.ju.testcases;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.eclipse.amalgam.explorer.activity.ui.api.manager.ActivityExplorerManager;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.la.NewFunctionalScenarioAdapter;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.oa.NewActivityScenarioAdapter;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.pa.NewScenarioDiagramAdapter;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.sa.NewExchangeScenarioAdapter;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.ui.actions.NewRepresentationAction;
import org.polarsys.capella.core.sirius.ui.actions.NewScenarioRepresentationAction;
import org.polarsys.capella.test.framework.api.BasicTestCase;

/**
 * 
 * Test the name of the Scenario diagrams created from Activity Explorer is the same as the containing Scenario
 */
public class ScenarioName extends BasicTestCase {

  private static final String PROJECT_NAME = "EmptyModel";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(PROJECT_NAME);
  }

  @Override
  public void test() throws Exception {
    Session session = getSession(PROJECT_NAME);
    ActivityExplorerManager.INSTANCE.openEditor(session);

    // Test on Activity Scenario
    NewActivityScenarioAdapter newAS = new NewActivityScenarioAdapter() {
      @Override
      protected NewRepresentationAction initNewScenarioRepresentationAction(Session session, AbstractCapability capa,
          RepresentationDescription representationDescription) {
        // Set forceDefaultName = true to avoid showing the pop-up to add diagram name
        return new NewScenarioRepresentationAction(representationDescription, capa, session, true, false);
      }
    };
    newAS.linkActivated(null);
    Optional<DDiagram> activityScenarioDiagram = DialectManager.INSTANCE.getAllLoadedRepresentations(session).stream()
        .filter(DDiagram.class::isInstance).map(DDiagram.class::cast).filter(diag -> diag.getDescription().getName()
            .equals(IDiagramNameConstants.OPERATIONAL_ACTIVITY_INTERACTION_SCENARIO_DIAGRAM_NAME))
        .findFirst();
    assertTrue(activityScenarioDiagram.isPresent());
    assertTrue(activityScenarioDiagram.get().getName().equals(
        ((NamedElement) RepresentationHelper.getRepresentationDescriptor(activityScenarioDiagram.get()).getTarget())
            .getName()));

    // Test on Exchange Scenario on SA level
    NewExchangeScenarioAdapter newES = new NewExchangeScenarioAdapter() {
      @Override
      protected NewRepresentationAction initNewScenarioRepresentationAction(Session session, AbstractCapability capa,
          RepresentationDescription representationDescription) {
        return new NewScenarioRepresentationAction(representationDescription, capa, session, true, false);
      }
    };
    newES.linkActivated(null);
    Optional<DDiagram> exchangeScenarioDiagram = DialectManager.INSTANCE.getAllLoadedRepresentations(session).stream()
        .filter(DDiagram.class::isInstance).map(DDiagram.class::cast)
        .filter(diag -> diag.getDescription().getName().equals(IDiagramNameConstants.DATA_FLOW_SCENARIO_DIAGRAM_NAME))
        .findFirst();
    assertTrue(exchangeScenarioDiagram.isPresent());
    assertTrue(exchangeScenarioDiagram.get().getName().equals(
        ((NamedElement) RepresentationHelper.getRepresentationDescriptor(exchangeScenarioDiagram.get()).getTarget())
            .getName()));

    // Test on Functional Scenario on LA level
    NewFunctionalScenarioAdapter newFS = new NewFunctionalScenarioAdapter() {
      @Override
      protected NewRepresentationAction initNewScenarioRepresentationAction(Session session, AbstractCapability capa,
          RepresentationDescription representationDescription) {
        return new NewScenarioRepresentationAction(representationDescription, capa, session, true, false);
      }
    };
    newFS.linkActivated(null);
    Optional<DDiagram> functionalScenarioDiagram = DialectManager.INSTANCE.getAllLoadedRepresentations(session).stream()
        .filter(DDiagram.class::isInstance).map(DDiagram.class::cast)
        .filter(diag -> diag.getDescription().getName().equals(IDiagramNameConstants.FUNCTIONAL_SCENARIO)).findFirst();
    assertTrue(functionalScenarioDiagram.isPresent());
    assertTrue(functionalScenarioDiagram.get().getName().equals(
        ((NamedElement) RepresentationHelper.getRepresentationDescriptor(functionalScenarioDiagram.get()).getTarget())
            .getName()));

    // Test on Interface Scenario on PA level
    NewScenarioDiagramAdapter newIS = new NewScenarioDiagramAdapter() {
      @Override
      protected NewRepresentationAction initNewScenarioRepresentationAction(Session session, AbstractCapability capa,
          RepresentationDescription representationDescription) {
        return new NewScenarioRepresentationAction(representationDescription, capa, session, true, false);
      }
    };
    newIS.linkActivated(null);
    Optional<DDiagram> interfaceScenarioDiagram = DialectManager.INSTANCE.getAllLoadedRepresentations(session).stream()
        .filter(DDiagram.class::isInstance).map(DDiagram.class::cast)
        .filter(diag -> diag.getDescription().getName().equals(IDiagramNameConstants.INTERFACE_SCENARIO_DIAGRAM_NAME))
        .findFirst();
    assertTrue(interfaceScenarioDiagram.isPresent());
    assertTrue(interfaceScenarioDiagram.get().getName().equals(
        ((NamedElement) RepresentationHelper.getRepresentationDescriptor(interfaceScenarioDiagram.get()).getTarget())
            .getName()));

    ActivityExplorerManager.INSTANCE.getEditorFromSession(session).close(false);
  }
}
