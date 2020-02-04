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
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.DecorationNode;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.gmf.runtime.notation.TextStyle;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.LightMarkerRegistry;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.LightMarkerRegistry.LightMarker;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.LocateInCapellaExplorerAction;
import org.polarsys.capella.core.ui.semantic.browser.sirius.helpers.SiriusSelectionHelper;
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
  public static String LA__LOGICAL_COMPONENT = "964d71a7-9199-4afc-8773-95be6c65e450"; //$NON-NLS-1$
  public static String LA__LOGICAL_COMPONENT_PART = "fecd48b1-03f8-4321-bdac-1776f2f7f311"; //$NON-NLS-1$
  
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

    DSemanticDecorator partView = diagramContext.getView(LA__LOGICAL_COMPONENT_PART);
    Part labPart = (Part) IdManager.getInstance().getEObject(LA__LOGICAL_COMPONENT_PART, scope);
    
    assertEquals("Semantic element of the view of a function should be the function itself", function,
        CapellaAdapterHelper.resolveSemanticObject(functionView));
    
    assertEquals("Semantic element of a Function should be itself", function,
        CapellaAdapterHelper.resolveSemanticObject(function));

    assertEquals("Semantic element of a diagram should be its target.", labDiagram.getTarget(),
        CapellaAdapterHelper.resolveSemanticObject(labDiagram));
    
    assertEquals("Semantic element of a Part should be the part.", labPart,
        CapellaAdapterHelper.resolveSemanticObject(labPart));

    assertEquals("Business element of a Part should be its component.", labPart.getAbstractType(),
        CapellaAdapterHelper.resolveBusinessObject(labPart));

    assertEquals("Semantic element of a diagram element of a Part should be the part.", labPart,
        CapellaAdapterHelper.resolveSemanticObject(partView));

    assertEquals("Business element of a diagram element of a Part should be its component.", labPart.getAbstractType(),
        CapellaAdapterHelper.resolveBusinessObject(partView));
    
    
    //Test on filter aird element
    DecorationNode decorationNode = NotationFactory.eINSTANCE.createDecorationNode();
    
    assertEquals("Semantic element of a DecorationNode should be null", null,
        CapellaAdapterHelper.resolveSemanticObject(decorationNode));
    
    assertEquals("Business element of a DecorationNode should be null", null,
        CapellaAdapterHelper.resolveBusinessObject(decorationNode));
    
    assertEquals("DescriptorOrBusinessObject of a DecorationNode element should be null", null,
        CapellaAdapterHelper.resolveDescriptorOrBusinessObject(decorationNode));
    
    Node node = NotationFactory.eINSTANCE.createNode();
    assertEquals("Semantic element of a Node should be null", null,
        CapellaAdapterHelper.resolveSemanticObject(node));
    
    assertEquals("Business element of a Node should be null", null,
        CapellaAdapterHelper.resolveBusinessObject(node));
    
    assertEquals("DescriptorOrBusinessObject of a Node element should be null", null,
        CapellaAdapterHelper.resolveDescriptorOrBusinessObject(node));
    
    // Legacy helper
    assertEquals("Legacy Semantic object (onlySemantic=false) of a diagram should be the descriptor.", labDiagramDescriptor,
        CapellaAdapterHelper.resolveSemanticObject(labDiagram, false));

    assertEquals("Legacy Semantic object (onlySemantic=true) of a diagram should be the target.", labDiagram.getTarget(),
        CapellaAdapterHelper.resolveSemanticObject(labDiagram, true));

    assertEquals("Legacy Semantic object (onlySemantic=false) of a diagram element should be the business element.", labPart.getAbstractType(),
        CapellaAdapterHelper.resolveSemanticObject(partView, false));
    
    assertEquals("Legacy Semantic object (onlySemantic=true) of a diagram element should be the business element.", labPart.getAbstractType(),
        CapellaAdapterHelper.resolveSemanticObject(partView, true));

    // Descriptor-Business Helper
    assertEquals("DescriptorOrBusinessObject of a diagram should be the descriptor.", labDiagramDescriptor,
        CapellaAdapterHelper.resolveDescriptorOrBusinessObject(labDiagram));

    assertEquals("DescriptorOrBusinessObject of a diagram element should be the business element.", labPart.getAbstractType(),
        CapellaAdapterHelper.resolveDescriptorOrBusinessObject(partView));

    // Semantic browser helper (DRD or business element)
    assertEquals("Semantic browser element of a diagram element should be the business element.", labPart.getAbstractType(),
        SiriusSelectionHelper.handleSelection(null, new StructuredSelection(partView)));
    
    assertEquals("Semantic browser element of a diagram should be the descriptor.", labDiagramDescriptor,
        SiriusSelectionHelper.handleSelection(null, new StructuredSelection(labDiagram)));

    assertEquals("Semantic browser element of a function should be the function.", function,
        SiriusSelectionHelper.handleSelection(null, new StructuredSelection(function)));

    assertEquals("Semantic browser element of a part should be the component.", labPart.getAbstractType(),
        SiriusSelectionHelper.handleSelection(null, new StructuredSelection(labPart)));
    
    // Show in Project Explorer (use DRD or Business element)
    assertEquals("Navigation of a diagram element should be the business element.", labPart.getAbstractType(),
        LocateInCapellaExplorerAction.getElement(partView));
    
    assertEquals("Navigation of a diagram should be the descriptor.", labDiagramDescriptor,
        LocateInCapellaExplorerAction.getElement(labDiagram));
    
    assertEquals("Navigation of a function should be the function.", function,
        LocateInCapellaExplorerAction.getElement(function));

    assertEquals("Navigation of a part should be the component.", labPart.getAbstractType(),
        LocateInCapellaExplorerAction.getElement(labPart));
    
    // Test with Information view
    Diagnostic fakeDiagnostic = new Diagnostic() {
      @Override
      public String getSource() {
        return null;
      }
      @Override
      public int getSeverity() {
        return 0;
      }
      @Override
      public String getMessage() {
        return null;
      }
      @Override
      public Throwable getException() {
        return null;
      }
      @Override
      public List<?> getData() {
        return Collections.singletonList(labPart.getAbstractType());
      }
      @Override
      public int getCode() {
        return 0;
      }
      @Override
      public List<Diagnostic> getChildren() {
        return null;
      }
    };
    LightMarkerRegistry registry = new LightMarkerRegistry();
    LightMarker fakeMarker = registry.new LightMarker(null, null, fakeDiagnostic);
    // Show in Project Explorer
    assertEquals("Navigation of a component to the Project Explorer from Information view should be the component.",
        labPart.getAbstractType(), LocateInCapellaExplorerAction.getElement(fakeMarker));
    // Show in Semantic Browser
    assertEquals("Navigation of a component to the Semantic Browser from Information view should be the component.\"",
        labPart.getAbstractType(), SiriusSelectionHelper.handleSelection(null, new StructuredSelection(fakeMarker)));
  }
}
