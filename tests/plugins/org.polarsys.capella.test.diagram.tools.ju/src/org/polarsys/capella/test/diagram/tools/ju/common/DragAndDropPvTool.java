/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.common;

import static org.junit.Assert.assertFalse;

import java.util.Collection;

import org.eclipse.sirius.diagram.DDiagramElement;
import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.tools.DragAndDropFromProjectExplorerTool;

public class DragAndDropPvTool extends DragAndDropFromProjectExplorerTool {

  public static final String LABEL = "D&D PV From Project Explorer"; //$NON-NLS-1$

  public DragAndDropPvTool(DiagramContext context, AbstractPropertyValue pv, String containerView) {
    super(context, LABEL, pv, containerView);
  }

  public DragAndDropPvTool(DiagramContext context, PropertyValueGroup pvg, String containerView) {
    super(context, LABEL, pvg, containerView);
  }

  @Override
  /**
   * Drop may create more than one element, if dropped pv/pvg is applied on elements present
   * on the diagram.
   * TODO better, more complete verification
   */
  protected void validateNewElements(Collection<DDiagramElement> newElements) {
    assertFalse(newElements.isEmpty());
  }

}
