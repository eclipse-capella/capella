/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
  private static QueryAdapter _instance = null;

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
    if (_instance == null) {
      _instance = new QueryAdapter();
    }
    return _instance;
  }

  /**
   * Compute a query and return its result.
   * @param currentElement_p context of the query.
   */
  @SuppressWarnings("unchecked")
  public List<Object> compute(Object currentElement_p, Object query_p) {
    List<Object> result = Collections.emptyList();
    if (currentElement_p instanceof EObject) {
      ExecutionManager executionManager = TransactionHelper.getExecutionManager((EObject) currentElement_p);
      if (executionManager != null) {
        QueryComputeCommand queryComputeCommand = new QueryComputeCommand(currentElement_p, query_p);
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
    public QueryComputeCommand(Object currentElement_p, Object query_p) {
      internalResult = new ArrayList<Object>(0);
      currentElement = currentElement_p;
      query = query_p;
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
