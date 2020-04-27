/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.test.model.ju.activityExplorer;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.AbstractCapellaHyperlinkAdapter;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.test.diagram.common.ju.availableXDFBDiagramTools.XDFBCreateContainerTools;
import org.polarsys.capella.test.diagram.common.ju.context.XDFBDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.model.ju.model.MiscModel;

public abstract class TransitionDiagramActivityExplorerTestCase extends MiscModel {
  Session session;
  SessionContext context;
  CapellaModel model;
  Project project;
  AbstractCapellaHyperlinkAdapter link;
  CapellaElement container;
  AbstractFunction originalFunction;

  public void initialize() {

    session = getSessionForTestModel(getRequiredTestModels().get(0));
    context = new SessionContext(session);
    model = getTestModel(getRequiredTestModels().get(0));
    project = model.getProject(session.getTransactionalEditingDomain());

    initLink();
    container = getContainer();
    XDFBDiagram xdfb = XDFBDiagram.createDiagram(context, container.getId());
    String id = xdfb.createContainer(xdfb.getDiagramId(), XDFBCreateContainerTools.CREATE_FUNCTION);
    originalFunction = context.getSemanticElement(id);
  }

  public abstract void initLink();

  public abstract ModelElement getTestModelElement();

  public abstract CapellaElement getContainer();

  public abstract AbstractFunction getRoot();

  public abstract void executeTransition();

  @Override
  public void test() throws Exception {
    initialize();
    ModelElement modelElement = getTestModelElement();
    assertEquals(modelElement, container);

    executeTransition();

    // check that the created function (originalFunction) was transitioned
    AbstractFunction root = getRoot();
    AbstractFunction lastFunction = root.getOwnedFunctions().get(root.getOwnedFunctions().size() - 1);
    assertEquals(originalFunction, lastFunction.getOutFunctionRealizations().get(0).getAllocatedFunction());
  }

}
