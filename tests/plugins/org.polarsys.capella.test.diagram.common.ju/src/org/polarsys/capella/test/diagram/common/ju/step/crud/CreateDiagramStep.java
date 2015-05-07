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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.polarsys.capella.common.ef.command.AbstractCommand;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractTestStep;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.context.SessionContext;
import org.polarsys.capella.test.diagram.common.ju.step.Messages;

/**
 * Test case that creates a diagram based on a semantic element.
 */
public class CreateDiagramStep extends AbstractTestStep<DiagramContext> {

  /**
   * Created diagram.
   */
  protected DDiagram _diagram;

  protected RepresentationDescription description;

  protected EObject target;

  protected boolean _isSynchronized;

  public CreateDiagramStep(SessionContext executionContext, String targetIdentifier, String diagramDescription) {
    this(executionContext, executionContext.getSemanticElement(targetIdentifier), diagramDescription);
  }

  /**
   * Default constructor.
   */
  public CreateDiagramStep(SessionContext executionContext, EObject target_p, String diagramDescription) {
    super(executionContext);
    target = target_p;
    _isSynchronized = true;
    description = org.polarsys.capella.core.diagram.helpers.DiagramHelper.getService().getDescription(executionContext.getSession(), diagramDescription);
  }

  /**
   * @see org.polarsys.capella.test.common.AbstractExtendedTest#preTestRun()
   */
  @Override
  protected void preRunTest() {
    super.preRunTest();
    Assert.assertNotNull(Messages.nullSession, getExecutionContext().getSession());
    Assert.assertNotNull(Messages.nullSemanticObject, target);
    Assert.assertNotNull(Messages.nullRepresentationDesc, description);
  }

  /**
   * Implement a diagram creation.
   */
  @Override
  protected void runTest() {
    final AbstractCommand cmd = new AbstractReadWriteCommand() {
      public void run() {
        _diagram =
            (DDiagram) DialectManager.INSTANCE.createRepresentation(description.getName(), target, description, getExecutionContext().getSession(),
                new NullProgressMonitor());

        // synchronization of this diagram
        if ((null != _diagram) && (_diagram instanceof DDiagram)) {
          _diagram.setSynchronized(_isSynchronized);
        }
      }
    };

    // Let's perform the job
    getExecutionContext().getExecutionManager().execute(cmd);

    Assert.assertNotNull(NLS.bind(Messages.failToCreateDriagram, new Object[] { description.getName(), EObjectLabelProviderHelper.getText(target) }), _diagram);

    // If the diagram was created, we notify the session manager about it.
    SessionManager.INSTANCE.notifyRepresentationCreated(getExecutionContext().getSession());
  }

  /**
   * @see org.polarsys.capella.test.common.AbstractExtendedTest#getResult()
   */
  @Override
  public DiagramContext getResult() {
    if (getExecutionContext() instanceof DiagramContext) {
      return (DiagramContext) getExecutionContext();
    }
    return new DiagramContext(getExecutionContext(), _diagram);
  }
}
