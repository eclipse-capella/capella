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

import java.util.stream.Stream;

import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.sirius.analysis.CapellaServices;

/**
 * Creates a Traceability diagram.
 * 
 * @author nicolas.peransin@obeo.fr
 */
public class EditHierarchicalTdDiagram extends EditSimpleTdDiagram {

  
  /**
   * Default constructor.
   */
  public EditHierarchicalTdDiagram() {
    hierarchical = true;
  }
  
  /**
   * Uses tool to display related elements.
   * <p>
   * Checks the {@code expectedElements} are displayed in the right 
   * </p>
   * 
   * @param target end of {@link Trace}
   * @param indirectTraces display indirect relations
   * @param expectedElements list of elements that should be visible after the tool
   */
  @Override
  protected void showRelatedElements(NamedElement target, boolean indirectTraces, NamedElement... expectedElements) {
    super.showRelatedElements(target, indirectTraces, expectedElements);
    
    // Verify the containment of elements: Elements are dispatched by BlockArchitecture.
    Stream.of(expectedElements).forEach(element -> {
      DDiagramElement view = assertViewExists(element);
      DSemanticDecorator viewContainer = (DSemanticDecorator) view.eContainer();
      
      BlockArchitecture block = CapellaServices.getAncestor(element, BlockArchitecture.class);
      assertEquals(NLS.bind("{0} ''{1}'' is displayed in the wrong block", //$NON-NLS-1$
          element.eClass().getName(), element.getName()), block, viewContainer.getTarget());
    });
  }
  
  
}
