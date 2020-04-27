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

package org.polarsys.capella.common.ui.services.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.expressions.IEvaluationContext;
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
   * @param object
   * @param expectedSelectionType
   * @return <code>null</code> if given object is not the expected type or not adaptable to expected type.
   */
  private <T> T getAdapter(Object object, Class<T> expectedSelectionType) {
    T result = null;
    // Check given object is instance of expected class.
    if (expectedSelectionType.isInstance(object)) {
      result = expectedSelectionType.cast(object);
    }
    // Given object is adaptable, try to get an instance of expected type.
    if ((null == result) && (object instanceof IAdaptable)) {
      result = expectedSelectionType.cast(((IAdaptable) object).getAdapter(expectedSelectionType));
    }
    return result;
  }

  /**
   * Return the selected objects in current selection matching given expected type.
   * @param event
   * @param expectedSelectionType
   * @return an empty list if no object is found matching expected type.
   */
  protected <T> List<T> getSelection(ExecutionEvent event, Class<T> expectedSelectionType) {
    List<T> result = new ArrayList<T>(0);
    // Pre-condition.
    if (null == event) {
      return result;
    }

    Object applicationContext = event.getApplicationContext();
    // Pre-condition.
    if (null == applicationContext) {
      return result;
    }

    if (!(applicationContext instanceof IEvaluationContext)) {
      return result;
    }

    IEvaluationContext evaluationContext = (IEvaluationContext) event.getApplicationContext();

    // Get default variable as context.
    Object defaultVariable = evaluationContext.getDefaultVariable();
    if (null == defaultVariable) {
      return result;
    }

    if (defaultVariable instanceof Collection) {
      Collection<?> variables = (Collection<?>) evaluationContext.getDefaultVariable();

      // Loop over the context variables.
      for (Object currentVariable : variables) {
        T adapter = getAdapter(currentVariable, expectedSelectionType);
        if (null != adapter) {
          result.add(adapter);
        }
      }
    }
    return result;
  }

  /**
   * Get variable value.
   * @param event
   * @param variableName
   * @return <code>null</code> if not found.
   */
  protected Object getVariableValue(ExecutionEvent event, String variableName) {
    Object result = null;
    // Pre-condition.
    if (null == event) {
      return result;
    }

    if (!(event.getApplicationContext() instanceof IEvaluationContext)) {
      return result;
    }

    IEvaluationContext applicationContext = (IEvaluationContext) event.getApplicationContext();

    // Pre-condition.
    if (null == applicationContext) {
      return result;
    }
    // Get default variable as context.
    result = applicationContext.getVariable(variableName);
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("rawtypes")
  public void updateElement(UIElement element, Map parameters) {
    // Do nothing.
  }
}
