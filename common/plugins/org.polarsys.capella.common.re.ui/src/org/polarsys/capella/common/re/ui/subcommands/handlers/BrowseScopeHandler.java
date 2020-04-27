/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
    Collection<Object> selectedObjects = getSelectedObjects((IEvaluationContext)evaluationContext);
    setBaseEnabled(!selectedObjects.isEmpty());
    super.setEnabled(evaluationContext);
  }
}
