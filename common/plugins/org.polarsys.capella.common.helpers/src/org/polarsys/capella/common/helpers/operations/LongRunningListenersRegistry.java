/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.helpers.operations;

import java.util.Collection;
import java.util.HashSet;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IConfigurationElement;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;

/**
 * Long running operations listeners registry.<br>
 * Also stands for a proxy to all existing events.
 */
public final class LongRunningListenersRegistry implements ILongRunningListener {
	
  private static final Logger logger = Logger.getLogger(LongRunningListenersRegistry.class.getName());
  
  /**
   * Shared instance.
   */
  private static LongRunningListenersRegistry instance;
  /**
   * Set of registered listeners.
   */
  private Collection<ILongRunningListener> listeners;

  /**
   * Constructor.
   */
  private LongRunningListenersRegistry() {
    // Private constructor.
  }

  /**
   * Get all registered long running operations listeners.
   * @return A new array of {@link ILongRunningListener}.
   */
  public ILongRunningListener[] getListeners() {
    // Lazy initialization.
    if (null == listeners) {
      listeners = new HashSet<>(0);
      IConfigurationElement[] configurationElements =
          ExtensionPointHelper.getConfigurationElements("org.polarsys.capella.common.helpers", "longRunningOperationsListener"); //$NON-NLS-1$ //$NON-NLS-2$
      // Cycle through declared extensions.
      for (IConfigurationElement configurationElement : configurationElements) {
        try {
          ILongRunningListener listener = (ILongRunningListener) configurationElement.createExecutableExtension(ExtensionPointHelper.ATT_CLASS);
          // Add new listener to collection.
          listeners.add(listener);
        } catch (Exception exception) {
          // Unable to instantiate this listener.
          // Skip it.
        }
      }
    }
    return listeners.toArray(new ILongRunningListener[listeners.size()]);
  }

  /**
   * @see org.polarsys.capella.common.helpers.operations.ILongRunningListener#isListenerFor(java.lang.Class)
   */
  public boolean isListenerFor(Class<?> longRunningOperationClass) {
    // Not relevant here.
    return false;
  }

  /**
   * @see org.polarsys.capella.common.helpers.operations.ILongRunningListener#operationAborted(java.lang.Class)
   */
  public void operationAborted(Class<?> operationClass) {
    // No listeners resolved yet.
    if (null == listeners) {
      getListeners();
    }
    // Cycle through listeners.
    for (ILongRunningListener listener : listeners) {
      try {
        // Check listener compatibility.
        if (listener.isListenerFor(operationClass)) {
          // Operation aborted.
          listener.operationAborted(operationClass);
        }
      } catch (Exception ex) {
        // There is no way the operation will fail because of an exception.
      }
    }
  }

  /**
   * @see org.polarsys.capella.common.helpers.operations.ILongRunningListener#operationEnded(java.lang.Class)
   */
  public void operationEnded(Class<?> operationClass) {
    // No listeners resolved yet.
    if (null == listeners) {
      getListeners();
    }
    // Cycle through listeners.
    for (ILongRunningListener listener : listeners) {
      try {
        // Check listener compatibility.
        if (listener.isListenerFor(operationClass)) {
          // Operation Ended.
          listener.operationEnded(operationClass);
        }
      } catch (Exception ex) {
        // There is no way the operation will fail because of an exception.
      }
    }
  }

  /**
   * @see org.polarsys.capella.common.helpers.operations.ILongRunningListener#operationStarting(java.lang.Class)
   */
  public void operationStarting(Class<?> operationClass) {
    // No listeners resolved yet.
    if (null == listeners) {
      getListeners();
    }
    // Cycle through listeners.
    for (ILongRunningListener listener : listeners) {
      try {
        // Check listener compatibility.
        if (listener.isListenerFor(operationClass)) {
          // Operation starting.
          listener.operationStarting(operationClass);
        }
      } catch (Exception ex) {
        // There is no way the operation will fail because of an exception.
        logger.error(ex.getMessage(), ex);
      }
    }
  }

  /**
   * Get unique instance.
   * @return
   */
  public static LongRunningListenersRegistry getInstance() {
    if (null == instance) {
      instance = new LongRunningListenersRegistry();
    }
    return instance;
  }
}
