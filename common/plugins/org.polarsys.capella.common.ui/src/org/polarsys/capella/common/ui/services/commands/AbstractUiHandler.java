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
package org.polarsys.capella.common.ui.services.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.expressions.EvaluationContext;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.commands.IElementUpdater;
import org.eclipse.ui.menus.UIElement;

/**
 * This class extends the {@link AbstractHandler} implementation class to provide a method to get the selected elements matching an expected type.
 */
public abstract class AbstractUiHandler extends AbstractHandler implements IElementUpdater {
  /**
   * Active Part variable.
   */
  protected static final String ACTIVE_PART_VARIABLE = "activePart"; //$NON-NLS-1$
  /**
   * Active workbench window
   */
  protected static final String ACTIVE_WORKBENCH_WINDOW = "activeWorkbenchWindow"; //$NON-NLS-1$

  /**
   * Get an adapted instance for given object in specified type.
   * @param <T>
   * @param object_p
   * @param expectedSelectionType_p
   * @return <code>null</code> if given object is not the expected type or not adaptable to expected type.
   */
  private <T> T getAdapter(Object object_p, Class<T> expectedSelectionType_p) {
    T result = null;
    // Check given object is instance of expected class.
    if (expectedSelectionType_p.isInstance(object_p)) {
      result = expectedSelectionType_p.cast(object_p);
    }
    // Given object is adaptable, try to get an instance of expected type.
    if ((null == result) && (object_p instanceof IAdaptable)) {
      result = expectedSelectionType_p.cast(((IAdaptable) object_p).getAdapter(expectedSelectionType_p));
    }
    return result;
  }

  /**
   * Return the selected objects in current selection matching given expected type.
   * @param event_p
   * @param expectedSelectionType_p
   * @return an empty list if no object is found matching expected type.
   */
  protected <T> List<T> getSelection(ExecutionEvent event_p, Class<T> expectedSelectionType_p) {
    List<T> result = new ArrayList<T>(0);
    // Pre-condition.
    if (null == event_p) {
      return result;
    }

    Object applicationContext = event_p.getApplicationContext();
    // Pre-condition.
    if (null == applicationContext) {
      return result;
    }

    if (!(applicationContext instanceof EvaluationContext)) {
      return result;
    }

    EvaluationContext evaluationContext = (EvaluationContext) event_p.getApplicationContext();

    // Get default variable as context.
    Object defaultVariable = evaluationContext.getDefaultVariable();
    if (null == defaultVariable) {
      return result;
    }

    if (defaultVariable instanceof Collection) {
      Collection<?> variables = (Collection<?>) evaluationContext.getDefaultVariable();

      // Loop over the context variables.
      for (Object currentVariable : variables) {
        T adapter = getAdapter(currentVariable, expectedSelectionType_p);
        if (null != adapter) {
          result.add(adapter);
        }
      }
    }
    return result;
  }

  /**
   * Get variable value.
   * @param event_p
   * @param variableName_p
   * @return <code>null</code> if not found.
   */
  protected Object getVariableValue(ExecutionEvent event_p, String variableName_p) {
    Object result = null;
    // Pre-condition.
    if (null == event_p) {
      return result;
    }
    EvaluationContext applicationContext = (EvaluationContext) event_p.getApplicationContext();
    // Pre-condition.
    if (null == applicationContext) {
      return result;
    }
    // Get default variable as context.
    result = applicationContext.getVariable(variableName_p);
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("rawtypes")
  public void updateElement(UIElement element_p, Map parameters_p) {
    // Do nothing.
  }
}
