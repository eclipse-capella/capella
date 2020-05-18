/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.projection.scenario.helpers;

import java.util.HashMap;

import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.tiger.IFinalizer;
import org.polarsys.capella.core.tiger.ITransfo;

/**
 */
public class InstanceRoles implements IFinalizer {

  /** List of instance roles */
  private static HashMap<AbstractInstance, InstanceRole> _instanceRoles = null;

  /**
   * @see org.polarsys.capella.core.tiger.IFinalizer#finalize(org.polarsys.capella.core.tiger.ITransfo)
   */
  public void finalize(ITransfo transfo_p) {
    try {
      // Nothing yet
    } finally {
      clean();
    }
  }

  /**
   * Cleanup the finalizer
   */
  private void clean() {

    if (_instanceRoles != null) {
      _instanceRoles.clear();
      _instanceRoles = null;
    }

  }

  private static HashMap<AbstractInstance, InstanceRole> getInstanceRoles() {
    if (_instanceRoles == null) {
      _instanceRoles = new HashMap<AbstractInstance, InstanceRole>();
    }
    return _instanceRoles;
  }

  public static InstanceRole get(AbstractInstance ir_p) {
    return getInstanceRoles().get(ir_p);
  }

  public static void add(AbstractInstance instance, InstanceRole role) {
    getInstanceRoles().put(instance, role);
  }

}
