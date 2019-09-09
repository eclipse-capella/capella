/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.model.ju.helpers;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.context.SessionContext;

public class CapellaAdapterHelperTestCase extends BasicTestCase {

  public static String MODEL_NAME = "copyPasteLayout"; //$NON-NLS-1$
  public static String LA__ROOT_LF__LOGICALFUNCTION_1 = "57405f1a-bc81-43b9-967d-4a4d954da095"; //$NON-NLS-1$
  public static String LAB_DIAGRAM = "[LAB] Logical System"; //$NON-NLS-1$

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(MODEL_NAME);
  }

  @Override
  public void test() throws Exception {
    ICapellaModel model = getTestModel(MODEL_NAME);
    IScope scope = new ScopeModelWrapper(model);

    Session session = getSessionForTestModel(getRequiredTestModels().get(0));
    SessionContext context = new SessionContext(session);
    DSemanticDiagram labDiagram = (DSemanticDiagram) DiagramHelper.getDRepresentation(session, LAB_DIAGRAM);
    DRepresentationDescriptor labDiagramDescriptor = RepresentationHelper.getRepresentationDescriptor(labDiagram);
    
    assertEquals("Semantic element of a diagram should be its target.", labDiagram.getTarget(),
        CapellaAdapterHelper.resolveSemanticObject(labDiagram));
    
    assertEquals(
        "Related EObject of a diagram should be its descriptor",
        labDiagramDescriptor, CapellaAdapterHelper.resolveEObject(labDiagram));
    
    assertEquals("Semantic element of a descriptor should be the target of its diagram", labDiagram.getTarget(),
        CapellaAdapterHelper.resolveSemanticObject(labDiagramDescriptor));

    DiagramContext diagramContext = new OpenDiagramStep(context, LAB_DIAGRAM).run();
    DSemanticDecorator functionView = diagramContext.getView(LA__ROOT_LF__LOGICALFUNCTION_1);
    EObject function = IdManager.getInstance().getEObject(LA__ROOT_LF__LOGICALFUNCTION_1, scope);
    
    assertEquals("Semantic element of the view of a function should be the function itself", function,
        CapellaAdapterHelper.resolveSemanticObject(functionView));
    
    assertEquals("Semantic element of a function should be itself", function,
        CapellaAdapterHelper.resolveSemanticObject(function));

    assertEquals("Semantic element of a diagram should be its target.", labDiagram.getTarget(),
        CapellaAdapterHelper.resolveSemanticObject(labDiagram));
    
  }
}
