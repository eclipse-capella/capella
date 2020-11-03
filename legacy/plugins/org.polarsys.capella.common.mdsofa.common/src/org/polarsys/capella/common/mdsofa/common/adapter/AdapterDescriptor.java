/*******************************************************************************
 *  Copyright (c) 2007, 2020 LCELB
 *  
 *  This program and the accompanying materials are made available under the
 *  terms of the Eclipse Public License 2.0 which is available at
 *  http://www.eclipse.org/legal/epl-2.0
 *  
 *  SPDX-License-Identifier: EPL-2.0
 * 
 *  Contributors:
 *      LCELB - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.mdsofa.common.adapter;

import org.apache.log4j.Logger;

import org.polarsys.capella.common.mdsofa.common.misc.Couple;

/**
 * Used to minimize memory consumption. (Flyweight pattern)
 */
public class AdapterDescriptor {
  /**
   * Log4j reference logger.
   */
  private static final Logger __logger = Logger.getLogger(AdapterDescriptor.class.getPackage().getName());
  // Key is the type adaptable type, value is the adapter
  private Couple<Class<?>, Object> _couple;

  public AdapterDescriptor(Class<?> objectClass_p) {
    _couple = new Couple<Class<?>, Object>(objectClass_p, null);
  }

  /**
   * Get the adapter for this descriptor.
   * @return a not null adapter
   */
  public Object getAdapter() {
    Object adapter = _couple.getValue();
    if (null == adapter) {
      try {
        adapter = _couple.getKey().newInstance();
        _couple.setValue(adapter);
      } catch (InstantiationException exception_p) {
        StringBuilder loggerMessage = new StringBuilder("AdapterDescriptor.getAdapter(..) _ "); //$NON-NLS-1$
        __logger.error(loggerMessage.toString(), exception_p);

      } catch (IllegalAccessException exception_p) {
        StringBuilder loggerMessage = new StringBuilder("AdapterDescriptor.getAdapter(..) _ "); //$NON-NLS-1$
        __logger.error(loggerMessage.toString(), exception_p);
      }
    }
    return adapter;
  }
}
