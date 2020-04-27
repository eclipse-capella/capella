/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.migration.handlers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.IAdaptable;

/**
 * 
 */
public abstract class AbstractMigrationHandler extends AbstractHandler {
  /**
   * Get an adapted instance for given object in specified type.
   * 
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
   * 
   * @param event
   * @param expectedSelectionType
   * @return an empty list if no object is found matching expected type.
   */
  protected <T> List<T> getSelection(IEvaluationContext evaluationContext, Class<T> expectedSelectionType) {
    List<T> result = new ArrayList<T>(0);

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

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.core.commands.AbstractHandler#setEnabled(java.lang.Object)
   */
  @Override
  public void setEnabled(Object evaluationContext) {

    if (isValidSelection(getSelection((IEvaluationContext) evaluationContext))) {
      if (!isEnabled()) {
        setBaseEnabled(true);
      }

    } else if (!isValidSelection(getSelection((IEvaluationContext) evaluationContext))) {
      if (isEnabled()) {
        setBaseEnabled(false);
      }

    } else {
      super.setEnabled(evaluationContext);

    }
  }

  protected boolean isValidSelection(List<Object> selection) {
    return true;
  }

  private List<Object> getSelection(IEvaluationContext evaluationContext) {
    return getSelection(evaluationContext, Object.class);
  }
}
