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
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.AbstractCapellaNewDiagramHyperlinkAdapter;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.model.ju.model.MiscModel;

public abstract class CDBActivityExplorerTestCase extends MiscModel {

  Session session;
  SessionContext context;
  CapellaModel model;
  Project project;
  AbstractCapellaNewDiagramHyperlinkAdapter link;
  DataPkg structure;

  public void initialize() {

    session = getSessionForTestModel(getRequiredTestModels().get(0));
    context = new SessionContext(session);
    model = getTestModel(getRequiredTestModels().get(0));
    project = model.getProject(session.getTransactionalEditingDomain());
  }

  public int getNrOfExistingDiagrams() {
    return 0;
  }

  public String getDefaultName() {
    return "[CDB] Data";
  }

  public String getDiagramName() {
    return IDiagramNameConstants.CLASS_BLANK_DIAGRAM_NAME;
  }

  public abstract void initLink();

  public abstract ModelElement getTestModelElement();

  public abstract DataPkg getStructure();

  public abstract boolean getResultOfCreateDiagram();

  @Override
  public void test() throws Exception {
    initialize();
    initLink();
    ModelElement modelElement = getTestModelElement();
    structure = getStructure();

    assertEquals((DataPkg) modelElement, structure);

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

  }
}
