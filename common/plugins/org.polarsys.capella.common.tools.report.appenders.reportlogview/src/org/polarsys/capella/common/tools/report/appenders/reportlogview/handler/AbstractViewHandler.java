/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.tools.report.appenders.reportlogview.handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.ui.ISources;
import org.eclipse.ui.IWorkbenchPart;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerView;
import org.polarsys.capella.common.ui.services.commands.AbstractUiHandler;

/**
 */
abstract class AbstractViewHandler extends AbstractUiHandler {

  protected MarkerView getView(ExecutionEvent event_p) {
    IWorkbenchPart activePart = (IWorkbenchPart) getVariableValue(event_p, ISources.ACTIVE_PART_NAME);
    if (activePart != null && activePart instanceof MarkerView) {
      return (MarkerView) activePart;
    }
    return null;
  }

  protected Collection<?> getSelection(ExecutionEvent event_p) {
    IEvaluationContext context = (IEvaluationContext) event_p.getApplicationContext();
    Object value = context.getDefaultVariable();

    if (value == null) {
      return new ArrayList<Object>(0);
    } else if (value instanceof Collection<?>) {
      return (Collection<?>) value;
    }
    return Collections.singletonList(value);
  }
}
