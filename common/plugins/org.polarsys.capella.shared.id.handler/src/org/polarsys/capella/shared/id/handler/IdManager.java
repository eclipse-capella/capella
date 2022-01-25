/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.shared.id.handler;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;

/**
 */
public class IdManager {

  // The singleton instance
  private static IdManager __instance;

  // The cache to store all contributors
  private static List<IIdHandler> __contributedIdHandlers;

  private IIdHandler defaultIdHandler;
  /**
   * 
   */
  private IdManager() {
    defaultIdHandler = new XMLResourceIdHandler();
  }

  /**
   * 
   */
  public static IdManager getInstance() {
    if (null == __instance) {
      __instance = new IdManager();
    }
    return __instance;
  }

  /**
   * 
   */
  public EObject getEObject(String id, IScope scope) {
    for (IIdHandler handler : getContributedIdHandlers()) {
      EObject object = handler.getEObject(id, scope);
      if (null != object) return object;
    }
    return null;
  }

  /**
   * 
   */
  public String getId(EObject object) {
    String id = null;
    for (IIdHandler handler : getContributedIdHandlers()) {
      id = handler.getId(object);
      if (null != id) return id;
    }
    id = defaultIdHandler.getId(object);
    return id;
  }

  /**
   * 
   */
  private List<IIdHandler> getContributedIdHandlers() {
    if (null == __contributedIdHandlers) {
      __contributedIdHandlers = new ArrayList<IIdHandler>();
      IConfigurationElement[] element = ExtensionPointHelper.getConfigurationElements(IdHandlerPlugin.getDefault().getBundle().getSymbolicName(), "IdHandler"); //$NON-NLS-1$
      for (IConfigurationElement ce : element) {
        IIdHandler extension = (IIdHandler) ExtensionPointHelper.createInstance(ce, "handler"); //$NON-NLS-1$
        if (extension != null) {
          __contributedIdHandlers.add(extension);
        }
      }
    }
    return __contributedIdHandlers;
  }
}
