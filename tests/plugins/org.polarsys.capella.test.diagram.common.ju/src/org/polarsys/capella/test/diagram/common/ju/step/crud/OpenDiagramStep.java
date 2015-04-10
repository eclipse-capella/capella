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

import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.diagram.DDiagram;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractTestStep;
import org.polarsys.capella.test.diagram.common.ju.context.BasicExecutionContext;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramOpenExecutionContext;
import org.polarsys.capella.test.diagram.common.ju.step.Messages;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;

/**
 * Test case that open a diagram i.e its editor.
 * choice about refresh included, could be really useful
 * default behavior of this test kept via the default constructor.
 */
public class OpenDiagramStep extends AbstractTestStep {

  /** Is refresh operation included during the test? */
  private boolean _mustBeRefreshed;
  
  /**
   * Default constructor, does not include refresh operation during the test
   */
  public OpenDiagramStep(BasicExecutionContext executionContext) {
    super(executionContext);
	  _mustBeRefreshed = false;
  }
  
  /**
   * Constructor that allows to include refresh op. during the test
   * @param shouldBeRefreshed
   */
  public OpenDiagramStep(DiagramOpenExecutionContext executionContext, boolean mustBeRefreshed) {
    super(executionContext);
	  _mustBeRefreshed = mustBeRefreshed;
  }

  /**
   * @see org.polarsys.capella.test.common.AbstractExtendedTest#preTestRun()
   */
  protected void preRunTest() {
    super.preRunTest();
    Assert.assertNotNull(Messages.nullDiagram, ((DiagramOpenExecutionContext) getExecutionContext()).getDiagram());
  }

  /**
   * @see org.polarsys.capella.test.common.AbstractExtendedTest#getResult()
   */
  @Override
  public Object getResult() {
	  return ((DiagramOpenExecutionContext) getExecutionContext()).getDiagram();
  }  

  /**
   * Implement a diagram open operation.
   */
  protected void runTest() {
    DiagramHelper.opendiagramEditor(getExecutionContext().getSession(), ((DiagramOpenExecutionContext) getExecutionContext()).getDiagram());
    if (_mustBeRefreshed || ((DiagramOpenExecutionContext) getExecutionContext()).getDiagram().getOwnedRepresentationElements().isEmpty() ) {
    	boolean ret = DiagramHelper.refreshDiagram((DDiagram) ((DiagramOpenExecutionContext) getExecutionContext()).getDiagram());
 	  	Assert.assertTrue(
 	  		NLS.bind(
 	  			Messages.failToRefreshDiagram,
 	  			new Object[] { ((DiagramOpenExecutionContext) getExecutionContext()).getDiagram().getName() }
 	  		),
 	  		ret
 	  	);
    }
  }
}
