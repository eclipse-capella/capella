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

package org.polarsys.capella.common.re.ui.subcommands.handlers;

import java.util.Collection;

import org.eclipse.core.expressions.IEvaluationContext;

/**
 *
 */
public class BrowseScopeHandler extends SelectionCommandHandler {

  @Override
  protected String getScope() {
    return "org.polarsys.capella.common.re.retrieve.scopeElements";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(Object evaluationContext) {
    Object variable = ((IEvaluationContext) evaluationContext).getDefaultVariable();
    setBaseEnabled(((variable instanceof Collection) && (!((Collection) variable).isEmpty())));
    super.setEnabled(evaluationContext);
  }

}
