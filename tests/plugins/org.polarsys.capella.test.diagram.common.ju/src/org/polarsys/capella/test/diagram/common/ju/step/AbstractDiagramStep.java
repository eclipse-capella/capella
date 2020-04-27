/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.common.ju.step;

import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.framework.api.step.AbstractTestStep;

/**
 * Step case that create a DescriptionTool and execute it.
 */
public abstract class AbstractDiagramStep<A> extends AbstractTestStep<A> {

  private DiagramContext diagramContext;

  protected DiagramContext getDiagramContext() {
    return this.diagramContext;
  }

  public AbstractDiagramStep(DiagramContext diagramContext) {
    super(diagramContext.getSessionContext());

    this.diagramContext = diagramContext;
  }
}
