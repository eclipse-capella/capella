/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

  protected MarkerView getView(ExecutionEvent event) {
    IWorkbenchPart activePart = (IWorkbenchPart) getVariableValue(event, ISources.ACTIVE_PART_NAME);
    if (activePart != null && activePart instanceof MarkerView) {
      return (MarkerView) activePart;
    }
    return null;
  }

  protected Collection<?> getSelection(ExecutionEvent event) {
    IEvaluationContext context = (IEvaluationContext) event.getApplicationContext();
    Object value = context.getDefaultVariable();

    if (value == null) {
      return new ArrayList<Object>(0);
    } else if (value instanceof Collection<?>) {
      return (Collection<?>) value;
    }
    return Collections.singletonList(value);
  }
}
