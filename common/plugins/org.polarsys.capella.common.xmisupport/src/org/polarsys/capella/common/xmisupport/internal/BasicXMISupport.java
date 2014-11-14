/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.xmisupport.internal;

import java.util.Map;
import java.util.WeakHashMap;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIResource;

/**
 */
public class BasicXMISupport implements XMISupport {

  // FIXME if the adapter references the resource (which is likely) we have cyclic reference that can't be collected,
  // i.e. a memory leak.
  private Map<Resource, XMIResourceAdapter> _loadedBackends = new WeakHashMap<Resource, XMIResourceAdapter>();

  /**
   * {@inheritDoc}
   */
  public String getID(EObject e_p) {
    String result = null;
    Resource resource = e_p.eResource();

    if (resource instanceof XMIResource) {
      result = ((XMIResource) e_p.eResource()).getID(e_p);
    } else if (resource != null) {
      XMIResourceAdapter adapter = _loadedBackends.get(resource);
      if (adapter == null) {
        if (resource instanceof XMIResourceAdapter) {
          adapter = (XMIResourceAdapter) resource;
        } else if (resource instanceof IAdaptable) {
          adapter = (XMIResourceAdapter) ((IAdaptable) resource).getAdapter(XMIResourceAdapter.class);
        }
        if (adapter == null) {
          String name = XMIResourceAdapter.class.getName();
          adapter = (XMIResourceAdapter) Platform.getAdapterManager().loadAdapter(resource, name);
        }
        if (adapter != null) {
          _loadedBackends.put(resource, adapter);
        }
      }
      if (adapter != null) {
        result = adapter.get(e_p);
        if (result == null) {
          result = EcoreUtil.generateUUID();
          adapter.put(e_p, result);
        }
      }
    }
    return result;
  }

  /**
   * Adapts a resource to support XMI ID conservation beyond XMI Resources.
   */
  public interface XMIResourceAdapter {

    /**
     * Associates the given ID with the object.
     * @param e the object for which to store an ID
     * @param xmiId the ID to store, may be null to indicate that the ID should be removed from adapter's persistent storage.
     */
    public void put(EObject e, String xmiId);

    /**
     * Returns the ID associated with the given object, or null if the Object is not associated with an ID.
     * @param e the object for which to retrieve the ID.
     * @return
     */
    public String get(EObject e);
  }

}
