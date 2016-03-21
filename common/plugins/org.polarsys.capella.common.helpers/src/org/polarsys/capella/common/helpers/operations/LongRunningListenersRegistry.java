/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.common.helpers.operations;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.core.runtime.IConfigurationElement;

import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;

/**
 * Long running operations listeners registry.<br>
 * Also stands for a proxy to all existing events.
 */
public final class LongRunningListenersRegistry implements ILongRunningListener {
  /**
   * Shared instance.
   */
  private static LongRunningListenersRegistry __instance;
  /**
   * Set of registered listeners.
   */
  private Collection<ILongRunningListener> _listeners;

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
    if (null == _listeners) {
      _listeners = new HashSet<ILongRunningListener>(0);
      IConfigurationElement[] configurationElements =
          ExtensionPointHelper.getConfigurationElements("org.polarsys.capella.common.helpers", "longRunningOperationsListener"); //$NON-NLS-1$ //$NON-NLS-2$
      // Cycle through declared extensions.
      for (IConfigurationElement configurationElement : configurationElements) {
        try {
          ILongRunningListener listener = (ILongRunningListener) configurationElement.createExecutableExtension(ExtensionPointHelper.ATT_CLASS);
          // Add new listener to collection.
          _listeners.add(listener);
        } catch (Exception exception) {
          // Unable to instantiate this listener.
          // Skip it.
        }
      }
    }
    return _listeners.toArray(new ILongRunningListener[_listeners.size()]);
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
    if (null == _listeners) {
      getListeners();
    }
    // Cycle through listeners.
    for (ILongRunningListener listener : _listeners) {
      try {
        // Check listener compatibility.
        if (listener.isListenerFor(operationClass)) {
          // Operation aborted.
          listener.operationAborted(operationClass);
        }
      } catch (Throwable t) {
        // There is no way the operation will fail because of an exception.
      }
    }
  }

  /**
   * @see org.polarsys.capella.common.helpers.operations.ILongRunningListener#operationEnded(java.lang.Class)
   */
  public void operationEnded(Class<?> operationClass) {
    // No listeners resolved yet.
    if (null == _listeners) {
      getListeners();
    }
    // Cycle through listeners.
    for (ILongRunningListener listener : _listeners) {
      try {
        // Check listener compatibility.
        if (listener.isListenerFor(operationClass)) {
          // Operation Ended.
          listener.operationEnded(operationClass);
        }
      } catch (Throwable t) {
        // There is no way the operation will fail because of an exception.
      }
    }
  }

  /**
   * @see org.polarsys.capella.common.helpers.operations.ILongRunningListener#operationStarting(java.lang.Class)
   */
  public void operationStarting(Class<?> operationClass) {
    // No listeners resolved yet.
    if (null == _listeners) {
      getListeners();
    }
    // Cycle through listeners.
    for (ILongRunningListener listener : _listeners) {
      try {
        // Check listener compatibility.
        if (listener.isListenerFor(operationClass)) {
          // Operation starting.
          listener.operationStarting(operationClass);
        }
      } catch (Throwable t) {
        // There is no way the operation will fail because of an exception.
        t.printStackTrace();
      }
    }
  }

  /**
   * Get unique instance.
   * @return
   */
  public static LongRunningListenersRegistry getInstance() {
    if (null == __instance) {
      __instance = new LongRunningListenersRegistry();
    }
    return __instance;
  }
}
