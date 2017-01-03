/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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

  @Override
  protected DiagramContext getExecutionContext() {
    return (DiagramContext) super.getExecutionContext();
  }

  public AbstractDiagramStep(DiagramContext context) {
    super(context);
  }

}
