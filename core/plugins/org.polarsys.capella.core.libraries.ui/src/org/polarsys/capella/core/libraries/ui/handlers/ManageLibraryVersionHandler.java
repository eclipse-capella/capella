/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.libraries.ui.handlers;

import java.util.Collection;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.resources.IProject;

public class ManageLibraryVersionHandler extends AbstractHandler {

  @Override
  public void setEnabled(Object evaluationContext) {
    super.setEnabled(evaluationContext);
    IEvaluationContext context = (IEvaluationContext) evaluationContext;
    Collection<Object> selectedElements = (Collection<Object>) context.getDefaultVariable();
    boolean isVisible = (selectedElements.size() == 1) && (selectedElements.iterator().next() instanceof IProject);
    setBaseEnabled(isVisible);
  }

  @Override
  public boolean isEnabled() {
    return super.isEnabled();
  }

  @Override
  public boolean isHandled() {
    return true;
  }

  @Override
  public void removeHandlerListener(IHandlerListener handlerListener_p) {
    super.removeHandlerListener(handlerListener_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object execute(ExecutionEvent event_p) throws ExecutionException {
    System.out.println("Execute Version"); //$NON-NLS-1$
    System.out.println("TODO"); //$NON-NLS-1$
    return null;
  }
}
