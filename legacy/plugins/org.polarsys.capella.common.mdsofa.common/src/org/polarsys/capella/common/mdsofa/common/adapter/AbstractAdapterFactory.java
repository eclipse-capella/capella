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

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.Platform;

/**
 * Base class to implement {@link IAdapterFactory} class.
 */
public abstract class AbstractAdapterFactory implements IAdapterFactory {

  /**
   * @see org.eclipse.core.runtime.IAdapterFactory#getAdapter(java.lang.Object, java.lang.Class)
   */
  public Object getAdapter(Object adaptableObject_p, Class adapterType_p) {
    Object adapter = null;
    // Looking for the related adapter for given model object.
    Iterator<Entry<Class<?>, AdapterDescriptor>> iterator = getAdapters().entrySet().iterator();
    while (iterator.hasNext() && null == adapter) {
      Entry<Class<?>, AdapterDescriptor> entry = iterator.next();
      Class<?> modelObject = entry.getKey();
      // Checking if the retrieved adaptable is a compatible type for given adaptable object.
      if (modelObject.isInstance(adaptableObject_p)) {
        AdapterDescriptor adapterDescriptor = entry.getValue();
        adapter = adapterDescriptor.getAdapter();
      }
    }
    return adapter;
  }

  /**
   * Return the map <Object class, AdapterDescriptor> of registered adapters
   * @return
   */
  protected abstract Map<Class<?>, AdapterDescriptor> getAdapters();

  /**
   * Returns an object which is an instance of the given class associated with the given object. Returns <code>null</code> if no such object can be found.
   * <p>
   * Note that this method will never cause plug-ins to be loaded. If the only suitable factory is not yet loaded, this method will return <code>null</code>.
   * @param adaptable_p
   *          the adaptable object being queried (usually an instance of <code>IAdaptable</code>)
   * @param adapterType_p
   *          the type of adapter to look up
   * @return an object castable to the given adapter type, or <code>null</code> if the given adaptable object does not have an available adapter of the given
   *         type
   */
  public static Object getPlatformAdapter(Object adaptable_p, Class<?> adapterType_p) {
    return Platform.getAdapterManager().getAdapter(adaptable_p, adapterType_p);
  }
}
