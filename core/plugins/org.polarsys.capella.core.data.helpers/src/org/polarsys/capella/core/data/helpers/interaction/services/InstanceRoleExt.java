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

package org.polarsys.capella.core.data.helpers.interaction.services;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

/**
 */
public class InstanceRoleExt {
  /**
   * Get AbstractFunction from InstanceRole.
   * @param instanceRole
   * @return the associated AbstractFunction or <code>null</code>
   */
  public static AbstractFunction getAbstractFunction(InstanceRole instanceRole) {
    if (null == instanceRole) {
      return null;
    }
    AbstractInstance abstractInstance = instanceRole.getRepresentedInstance();
    if (abstractInstance instanceof AbstractFunction) {
      return (AbstractFunction) abstractInstance;
    }
    return null;
  }

  /**
   * Get Component from InstanceRole.
   * @param instanceRole
   * @return the associated Component or <code>null</code>
   */
  public static Component getComponent(InstanceRole instanceRole) {
    if (null == instanceRole) {
      return null;
    }
    AbstractInstance abstractInstance = instanceRole.getRepresentedInstance();
    if (null == abstractInstance) {
      return null;
    }
    AbstractType abstractType = abstractInstance.getAbstractType();
    if (abstractType instanceof Component) {
      return (Component) abstractType;
    }
    return null;
  }
}
