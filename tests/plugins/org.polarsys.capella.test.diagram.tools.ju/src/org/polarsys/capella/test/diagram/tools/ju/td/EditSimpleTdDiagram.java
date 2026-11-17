/*******************************************************************************
 * Copyright (c) 2026 Obeo.
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
package org.polarsys.capella.test.diagram.tools.ju.td;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.TDDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

/**
 * Tests the creation of a Traceability diagram.
 * 
 * @author nicolas.peransin@obeo.fr
 */
public class EditSimpleTdDiagram extends AbstractDiagramTestCase {

  /**
   * Name of model used by Traces tests.
   */
  public static final String REQUIRED_TEST_MODEL = "TraceabilityModel"; //$NON-NLS-1$
  
  /** Indicates if the Trace diagram contains hierarchy blocks */
  protected boolean hierarchical = false;
  
  protected SessionContext context;
  
  protected Project root;
  
  /** Tested diagram. */
  protected TDDiagram diagram;

  private static final String PROJECT_ID = "d01a1235-0b3b-4e9c-95c6-2f6c09bc3d00"; //$NON-NLS-1$

  @Override
  public String getRequiredTestModel() {
    return REQUIRED_TEST_MODEL;
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();

    TDDiagram.enableTraceabilityCapacity(true);
    context = new SessionContext(getSession(getRequiredTestModel()));
    root = context.getSemanticElement(PROJECT_ID);  
  }


  @Override
  public void test() throws Exception {
    diagram = TDDiagram.openNewDiagram(context, ModelQueryHelper.getLogicalSystem(root), hierarchical);
    try {
      editDiagram();
    } finally {
      diagram.close();
    }
  }
  
  /**
   * Modifies the diagram.
   */
  protected void editDiagram() {
    // Show a PV-Group
    PropertyValueGroup displayedElement = (PropertyValueGroup) diagram.dragAndDropFromExplorer(ModelQueryHelper.getRootSystemFunction(root));

    // Direct links
    var oa = ModelQueryHelper.getRootOperationalActivity(root);
    var oaGroup = oa.getAppliedPropertyValueGroups().get(0);
    var oaConstraint = (Constraint) oa.getOwnedConstraints().get(0);
    var loPv = ModelQueryHelper.getRootLogicalFunction(root).getAppliedPropertyValues().get(0);

    showRelatedElements(displayedElement, false, oaGroup, oaConstraint, loPv);

    // Indirect links
    var phPv = ModelQueryHelper.getRootPhysicalFunction(root).getAppliedPropertyValues().get(0);
    showRelatedElements(displayedElement, true, phPv);
    
    // Show elements applicable to 'Root Logical Function'
    var loFunction = ModelQueryHelper.getRootLogicalFunction(root);
    diagram.dragAndDropFromExplorer(loFunction);
    
    List<ModelElement> relatedElements = Stream.of(
        loFunction.getAppliedPropertyValueGroups(), 
        loFunction.getAppliedPropertyValues(), 
        loFunction.getConstraints())
      .flatMap(List::stream)
      .collect(Collectors.toList());
    
    assertEquals(3, relatedElements.size());
    relatedElements.forEach(this::assertViewExists);
    
    // Creates some traces
    var loPvGroup = loFunction.getAppliedPropertyValueGroups().get(0);
    diagram.trace(loPvGroup, oaConstraint);
    diagram.trace(loPvGroup, oaGroup);
    var loConstraint = loFunction.getConstraints().get(0);
    diagram.trace(loConstraint, oaGroup);
    diagram.trace(loConstraint, oaConstraint);
  }

  /**
   * Assert an element has a diagram view displaying it.
   * 
   * @param element element to test
   * @return {@link DDiagramElement}
   */
  protected DDiagramElement assertViewExists(ModelElement element) {
    String label = element instanceof AbstractNamedElement named ? '\'' +  named.getName() + '\'' : ""; //$NON-NLS-1$
    DDiagramElement view = diagram.getView(element);
    assertNotNull(NLS.bind("View of {0} ''{1}'' is missing", //$NON-NLS-1$
        element.eClass().getName(), label), view);
    return view;
  }
  
  /**
   * Uses tool to display related elements.
   * 
   * @param target end of {@link Trace}
   * @param indirectTraces display indirect relations
   * @param expectedElements list of elements that should be visible after the tool
   */
  protected void showRelatedElements(NamedElement target, boolean indirectTraces, NamedElement... expectedElements) {
    diagram.showRelatedElements(target, indirectTraces, expectedElements);
  }
  
}
