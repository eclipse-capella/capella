/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.common.ju.step.crud;

import junit.framework.Assert;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.polarsys.capella.common.ef.command.AbstractCommand;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractTestStep;
import org.polarsys.capella.test.diagram.common.ju.context.BasicExecutionContext;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramCreationExecutionContext;
import org.polarsys.capella.test.diagram.common.ju.step.Messages;

/**
 * Test case that creates a diagram based on a semantic element.
 */
public class CreateDiagramStep extends AbstractTestStep {

  /**
   * Created diagram.
   */
  protected DRepresentation _diagram;

  /**
   * @see DRepresentation
   */
  protected boolean _isSynchronized;

  /**
   * Default constructor.
   */
  public CreateDiagramStep(BasicExecutionContext executionContext) {
    super(executionContext);
    _isSynchronized = true;
  }

  /**
   * Constructor
   * @param isSynchronized will the created Diagram be synchronized?
   * @see 
   */
  public CreateDiagramStep(DiagramCreationExecutionContext executionContext, boolean isSynchronized) {
    super(executionContext);
    _isSynchronized = isSynchronized;
  }

  /**
   * @see org.polarsys.capella.test.common.AbstractExtendedTest#preTestRun()
   */
  protected void preRunTest() {
    super.preRunTest();
    Assert.assertNotNull(Messages.nullSession, getExecutionContext().getSession());
    Assert.assertNotNull(Messages.nullSemanticObject, ((DiagramCreationExecutionContext) getExecutionContext()).getSemanticElement());
    Assert.assertNotNull(Messages.nullRepresentationDesc, ((DiagramCreationExecutionContext) getExecutionContext()).getRepresentationDescription());
  }

  /**
   * Implement a diagram creation.
   */
  protected void runTest() {
    final AbstractCommand cmd = new AbstractReadWriteCommand() {
      public void run() {
        String name = ((DiagramCreationExecutionContext) getExecutionContext()).getRepresentationDescription().getName();
        _diagram = DialectManager.INSTANCE.createRepresentation(name,
            ((DiagramCreationExecutionContext) getExecutionContext()).getSemanticElement(),
            ((DiagramCreationExecutionContext) getExecutionContext()).getRepresentationDescription(),
            getExecutionContext().getSession(), new NullProgressMonitor());

        // synchronization of this diagram
        if ((null != _diagram) && (_diagram instanceof DDiagram)) {
          ((DDiagram) _diagram).setSynchronized(_isSynchronized);
        }
      }
    };

    //Let's perform the job
    getExecutionContext().getExecutionManager().execute(cmd);

    Assert.assertNotNull(
        NLS.bind(Messages.failToCreateDriagram, new Object[] {
            ((DiagramCreationExecutionContext) getExecutionContext()).getRepresentationDescription().getName(),
            ((DiagramCreationExecutionContext) getExecutionContext()).getSemanticElement().eClass().getName() }),
        _diagram);

    // If the diagram was created, we notify the session manager about it.
    if (null != _diagram) {
      SessionManager.INSTANCE.notifyRepresentationCreated(getExecutionContext().getSession());
    }
  }

  /**
   * @see org.polarsys.capella.test.common.AbstractExtendedTest#getResult()
   */
  @Override
  public Object getResult() {
    return _diagram;
  }
}
