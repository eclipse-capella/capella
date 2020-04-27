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

package org.polarsys.capella.common.ui.toolkit.browser.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadOnlyCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;
import org.polarsys.kitalpha.emde.extension.ModelExtensionManager;

/**
 */
public class QueryAdapter {
  /**
   * Singleton.
   */
  private static QueryAdapter instance = null;

  /**
   * Singleton constructor.
   */
  private QueryAdapter() {
    // nothing to do.
  }

  /**
   * Singleton accessor.
   * @return
   */
  public static QueryAdapter getInstance() {
    if (instance == null) {
      instance = new QueryAdapter();
    }
    return instance;
  }

  /**
   * Compute a query and return its result.
   * @param currentElement context of the query.
   */
  @SuppressWarnings("unchecked")
  public List<Object> compute(Object currentElement, Object query) {
    List<Object> result = Collections.emptyList();
    if (currentElement instanceof EObject) {
      ExecutionManager executionManager = TransactionHelper.getExecutionManager((EObject) currentElement);
      if (executionManager != null) {
        QueryComputeCommand queryComputeCommand = new QueryComputeCommand(currentElement, query);
        executionManager.execute(queryComputeCommand);
        result = new ArrayList<Object>((List<Object>) queryComputeCommand.getResult());
        // Trim result from useless 'null' values.
        for (Iterator<Object> iterator = result.iterator(); iterator.hasNext();) {
          if (null == iterator.next()) {
            iterator.remove();
          }
        }
      }
    }
    return result;
  }

  /**
   * Command for computing query.
   */
  protected class QueryComputeCommand extends AbstractReadOnlyCommand {
    protected List<Object> internalResult = null;
    protected Object currentElement = null;
    protected Object query = null;

    /**
     * Constructor
     */
    public QueryComputeCommand(Object currentElement, Object query) {
      internalResult = new ArrayList<Object>(0);
      this.currentElement = currentElement;
      this.query = query;
    }

    /**
     * @see java.lang.Runnable#run()
     */
    @SuppressWarnings("unchecked")
    public void run() {
      if (query instanceof IQuery) {
          ModelExtensionManager mgr = null;
          if (currentElement instanceof EObject)
            mgr = ModelExtensionHelper.getInstance((EObject) currentElement);
          for (Object o : ((IQuery) query).compute(currentElement)) {
            if (mgr == null || !mgr.isExtensionModelDisabled(o))
              internalResult.add(o);
          }
          setResult(internalResult);
      }
    }
  }
}
