/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.junit.Assert;
import org.polarsys.capella.common.ef.command.AbstractCommand;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.test.diagram.common.ju.headless.selector.HeadlessCapellaAnalysisSelector;
import org.polarsys.capella.test.diagram.common.ju.step.Messages;
import org.polarsys.capella.test.framework.api.step.AbstractTestStep;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.TestHelper;

/**
 * Test case that creates a diagram based on a semantic element.
 */
public class CreateDTableStep extends AbstractTestStep {

  /**
   * Created diagram.
   */
  protected DRepresentation representation;

  protected RepresentationDescription description;

  protected EObject target;

  protected boolean isSynchronized;

  protected URI selectedURI;

  /**
   * Default constructor.
   */
  public CreateDTableStep(SessionContext executionContext, EObject target, String diagramDescription, URI selectedURI) {
    super(executionContext);
    this.target = target;
    isSynchronized = true;
    description = org.polarsys.capella.core.diagram.helpers.DiagramHelper.getService()
        .getDescription(executionContext.getSession(), diagramDescription);
    this.selectedURI = selectedURI;
  }

  public CreateDTableStep(SessionContext executionContext, String targetIdentifier, String diagramDescription) {
    // Cast to EObject to avoid The constructor CreateDiagramStep(SessionContext, String, String, URI) is ambiguous
    this(executionContext, executionContext.getSemanticElement(targetIdentifier), diagramDescription,
        TestHelper.getAirdResource(executionContext.getSession()).getURI());
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

    HeadlessCapellaAnalysisSelector.INSTANCE.setSelectedURI(getExecutionContext().getSession(), selectedURI);
  }

  /**
   * Implement a diagram creation.
   */
  @Override
  protected void runTest() {
    final AbstractCommand cmd = new AbstractReadWriteCommand() {
      @Override
      public void run() {
        representation = DialectManager.INSTANCE.createRepresentation(description.getName(), target, description,
            getExecutionContext().getSession(), new NullProgressMonitor());
      }
    };

    // Let's perform the job
    getExecutionContext().getExecutionManager().execute(cmd);

    Assert.assertNotNull(NLS.bind(Messages.failToCreateDriagram,
        new Object[] { description.getName(), EObjectLabelProviderHelper.getText(target) }), representation);

    // If the diagram was created, we notify the session manager about it.
    SessionManager.INSTANCE.notifyRepresentationCreated(getExecutionContext().getSession());
  }

  @Override
  public Object getResult() {
    return representation;
  }
}
