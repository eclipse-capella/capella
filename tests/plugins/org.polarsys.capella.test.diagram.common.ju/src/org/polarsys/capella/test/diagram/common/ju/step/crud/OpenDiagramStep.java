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
package org.polarsys.capella.test.diagram.common.ju.step.crud;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.diagram.DDiagram;
import org.junit.Assert;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.Messages;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.api.step.AbstractTestStep;
import org.polarsys.capella.test.framework.context.SessionContext;

/**
 * Test case that open a diagram i.e its editor. choice about refresh included, could be really useful default behavior
 * of this test kept via the default constructor.
 */
public class OpenDiagramStep extends AbstractTestStep<DiagramContext> {

  /** Is refresh operation included during the test? */
  private boolean _mustBeRefreshed;

  protected DiagramContext diagramContext;
  protected DDiagram diagram;

  public OpenDiagramStep(DiagramContext diagramContext_p) {
    this(diagramContext_p, false);
  }

  public OpenDiagramStep(DiagramContext diagramContext, boolean mustBeRefreshed) {
    super(diagramContext.getSessionContext());

    this._mustBeRefreshed = mustBeRefreshed;
    diagram = diagramContext.getDiagram();
  }

  public OpenDiagramStep(SessionContext context_p, String diagramName, boolean mustBeRefreshed) {
    super(context_p);

    this.diagram = (DDiagram) DiagramHelper.getDRepresentation(context_p.getSession(), diagramName);
    _mustBeRefreshed = mustBeRefreshed;
  }

  public OpenDiagramStep(SessionContext context_p, String diagramName) {
    this(context_p, diagramName, false);
  }

  /**
   * @see org.polarsys.capella.test.common.AbstractExtendedTest#preTestRun()
   */
  @Override
  protected void preRunTest() {
    super.preRunTest();
    Assert.assertNotNull(Messages.nullDiagram, diagram);
  }

  /**
   * Implement a diagram open operation.
   */
  @Override
  protected void runTest() {
    DiagramHelper.opendiagramEditor(getExecutionContext().getSession(), diagram);
    if (_mustBeRefreshed || diagram.getOwnedRepresentationElements().isEmpty()) {
      boolean ret = DiagramHelper.refreshDiagram(diagram);
      Assert.assertTrue(NLS.bind(Messages.failToRefreshDiagram, new Object[] { EObjectExt.getText(diagram) }), ret);
    }
  }

  /**
   * @see org.polarsys.capella.test.common.AbstractExtendedTest#getResult()
   */
  @Override
  public DiagramContext getResult() {
    String diagramIdentifier = EObjectExt.getText(diagram);
    Map<String, EObject> semanticMap = getExecutionContext().getSemanticObjectMap();

    if (semanticMap.containsKey(diagramIdentifier) && this.diagramContext != null) {
      return this.diagramContext;
    }

    DiagramContext diagramContext = new DiagramContext(getExecutionContext(), diagram);
    getExecutionContext().putSemanticElement(diagramIdentifier, diagram);

    return diagramContext;
  }
}
