/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.test.model.ju.activityExplorer;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.oa.OperationalCapabilityPkg;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.AbstractCapellaNewDiagramHyperlinkAdapter;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.framework.helpers.TestHelper;
import org.polarsys.capella.test.model.ju.model.MiscModel;

public abstract class FunctionalScenarioActivityExplorerTestCase extends MiscModel {
  Session session;
  SessionContext context;
  CapellaModel model;
  Project project;
  AbstractCapabilityPkg structure;
  AbstractCapellaNewDiagramHyperlinkAdapter link;
  Scenario scenario;
  EList<? extends AbstractCapability> listOfCapabilities;

  public void initialize() {

    session = getSessionForTestModel(getRequiredTestModels().get(0));
    context = new SessionContext(session);
    model = getTestModel(getRequiredTestModels().get(0));
    project = model.getProject(session.getTransactionalEditingDomain());
  }

  public abstract void initLink();

  public abstract ModelElement getTestModelElement();

  public abstract AbstractCapabilityPkg getStructure();

  public abstract String getDefaultName();

  public abstract String getDiagramName();

  public abstract boolean getResultOfCreateDiagram();

  public int getNrOfExistingDiagrams() {
    return 0;
  }

  @Override
  public void test() throws Exception {
    initialize();
    initLink();
    structure = getStructure();

    if (structure instanceof OperationalCapabilityPkg) {
      TestHelper.getExecutionManager(context.getSession()).execute(new AbstractReadWriteCommand() {

        @Override

        public void run() {

          listOfCapabilities = getOwnedCapabilities(structure);
          listOfCapabilities.clear();
        }

      });
    }
    ModelElement modelElement = getTestModelElement();
    listOfCapabilities = getOwnedCapabilities(structure);
    EList<Scenario> listOfScenarios = listOfCapabilities.get(0).getOwnedScenarios();
    String scenarioID = listOfScenarios.get(0).getId();
    scenario = context.getSemanticElement(scenarioID);
    assertEquals((Scenario) modelElement, scenario);

    String defaultName = getDefaultName();
    String diagramName = getDiagramName();
    DiagramDescription description = (DiagramDescription) org.polarsys.capella.core.diagram.helpers.DiagramHelper
        .getService().getDescription(context.getSession(), diagramName);

    boolean result = getResultOfCreateDiagram();
    assertTrue(result);

    Collection<DRepresentationDescriptor> myList = RepresentationHelper
        .getAllRepresentationDescriptorsTargetedBy(Arrays.asList(structure));
    List<DRepresentationDescriptor> filteredDiags = myList.stream().filter(x -> x.getName().equals(defaultName))
        .collect(Collectors.toList());
    int nrOfDiagrams = getNrOfExistingDiagrams() + 1;
    assertEquals(filteredDiags.size(), nrOfDiagrams);
    GuiActions.closeSession(session, false);
  }

  public EList<? extends AbstractCapability> getOwnedCapabilities(AbstractCapabilityPkg capPkg) {
    if (capPkg instanceof OperationalCapabilityPkg) {
      OperationalCapabilityPkg oc = (OperationalCapabilityPkg) structure;
      return oc.getOwnedOperationalCapabilities();
    }
    if (capPkg instanceof CapabilityRealizationPkg) {
      CapabilityRealizationPkg cr = (CapabilityRealizationPkg) structure;
      return cr.getOwnedCapabilityRealizations();
    }
    if (capPkg instanceof CapabilityPkg) {
      CapabilityPkg cp = (CapabilityPkg) structure;
      return cp.getOwnedCapabilities();
    }

    return null;
  }
}
