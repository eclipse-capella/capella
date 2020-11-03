/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.msm;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.ChangeCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;
import org.polarsys.capella.test.diagram.common.ju.availableXDFBDiagramTools.XDFBCreateContainerTools;
import org.polarsys.capella.test.diagram.common.ju.context.MSMDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.XDFBDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

/**
 */
public class MSMFunctionalExchangeActionLabelTest extends EmptyProject {

  public void test() throws Exception {

    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    XDFBDiagram xdfb = XDFBDiagram.createDiagram(context, EmptyProject.SA__ROOT_SF);
    String function1Id = xdfb.createContainer(xdfb.getDiagramId(), XDFBCreateContainerTools.CREATE_FUNCTION);
    String function2Id = xdfb.createContainer(xdfb.getDiagramId(), XDFBCreateContainerTools.CREATE_FUNCTION);

    XABDiagram sabDiagram = XABDiagram.createDiagram(context, EmptyProject.SA__SYSTEM);
    sabDiagram.manageAllocatedFunction(function1Id, EmptyProject.SA__SYSTEM_CONTEXT__PART_SYSTEM__SYSTEM);
    sabDiagram.manageAllocatedFunction(function2Id, EmptyProject.SA__SYSTEM_CONTEXT__PART_SYSTEM__SYSTEM);
    sabDiagram.createFunctionalExchange(function1Id, function2Id, GenericModel.FUNCTIONAL_EXCHANGE_1);
    final FunctionalExchange fe1 = (FunctionalExchange) sabDiagram.getSessionContext()
        .getSemanticElement(GenericModel.FUNCTIONAL_EXCHANGE_1);

    MSMDiagram msmDiagram = MSMDiagram.createDiagram(context,
        EmptyProject.SA__SYSTEM__SYSTEM_STATE_MACHINE__DEFAULT_REGION);
    msmDiagram.createState(msmDiagram.getDiagramId(), GenericModel.STATE_1);
    final State state1 = (State) msmDiagram.getSessionContext().getSemanticElement(GenericModel.STATE_1);

    ChangeCommand command = new ChangeCommand(state1.eResource()) {
      @Override
      protected void doExecute() {
        state1.getDoActivity().add(fe1);
        state1.getEntry().add(fe1);
        state1.getExit().add(fe1);
      }
    };
    AdapterFactoryEditingDomain.getEditingDomainFor(state1).getCommandStack().execute(command);

    msmDiagram.refreshDiagram();

    DDiagram diagram = msmDiagram.getDiagram();
    Iterator<DDiagramElement> doIt = getDiagramElements(diagram, IMappingNameConstants.MSM_DOACTIVITY_MAPPING_NAME, fe1)
        .iterator();
    Iterator<DDiagramElement> entryIt = getDiagramElements(diagram, IMappingNameConstants.MSM_ENTRY_MAPPING_NAME, fe1)
        .iterator();
    Iterator<DDiagramElement> exitIt = getDiagramElements(diagram, IMappingNameConstants.MSM_EXIT_MAPPING_NAME, fe1)
        .iterator();

    String label = "FunctionalExchange 1 [-> SystemFunction 2]";
    assertEquals(" do / " + label, doIt.hasNext() ? doIt.next().getName() : null);
    assertEquals(" entry / " + label, entryIt.hasNext() ? entryIt.next().getName() : null);
    assertEquals(" exit / " + label, exitIt.hasNext() ? exitIt.next().getName() : null);
  }

  private Iterable<DDiagramElement> getDiagramElements(DDiagram diagram, String targetMappingName, EObject target) {
    DiagramElementMapping mapping = DiagramServices.getDiagramServices().getNodeMapping(diagram, targetMappingName);
    return new DDiagramContents(diagram).getDiagramElements(target, mapping);
  }
}
