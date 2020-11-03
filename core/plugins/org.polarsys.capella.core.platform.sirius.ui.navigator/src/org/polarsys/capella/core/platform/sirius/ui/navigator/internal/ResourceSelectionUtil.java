/*******************************************************************************
 * Copyright (c) 2000, 2020 Corporation and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    IBM - Initial API and implementation 
 *    Thales - Contributor
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.navigator.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;

/**
 * Copied from org.eclipse.ui.ide.ResourceSelectionUtil to be compliant with 3.5 & 3.6 Eclipse platform.
 */
public class ResourceSelectionUtil {
  /**
   * Constructor.
   */
  private ResourceSelectionUtil() {
    // Private constructor to block instantiation
  }

  /**
   * Returns whether the types of the resources in the given selection are among the specified resource types.
   * @param selection the selection
   * @param resourceMask resource mask formed by bitwise OR of resource type constants (defined on <code>IResource</code>)
   * @return <code>true</code> if all selected elements are resources of the right type, and <code>false</code> if at least one element is either a resource of
   *         some other type or a non-resource
   * @see IResource#getType()
   */
  @SuppressWarnings("rawtypes")
  public static boolean allResourcesAreOfType(IStructuredSelection selection, int resourceMask) {
    Iterator resources = selection.iterator();
    while (resources.hasNext()) {
      Object next = resources.next();
      if (!(next instanceof IResource)) {
        return false;
      }
      if (!resourceIsType((IResource) next, resourceMask)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Returns the selection adapted to IResource. Returns null if any of the entries are not adaptable.
   * @param selection the selection
   * @param resourceMask resource mask formed by bitwise OR of resource type constants (defined on <code>IResource</code>)
   * @return IStructuredSelection or null if any of the entries are not adaptable.
   * @see IResource#getType()
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public static IStructuredSelection allResources(IStructuredSelection selection, int resourceMask) {
    Iterator adaptables = selection.iterator();
    List result = new ArrayList();
    while (adaptables.hasNext()) {
      Object next = adaptables.next();
      if (next instanceof IAdaptable) {
        Object resource = ((IAdaptable) next).getAdapter(IResource.class);
        if (resource == null) {
          return null;
        } else if (resourceIsType((IResource) resource, resourceMask)) {
          result.add(resource);
        }
      } else {
        return null;
      }
    }
    return new StructuredSelection(result);

  }

  /**
   * Returns whether the type of the given resource is among the specified resource types.
   * @param resource the resource
   * @param resourceMask resource mask formed by bitwise OR of resource type constants (defined on <code>IResource</code>)
   * @return <code>true</code> if the resources has a matching type, and <code>false</code> otherwise
   * @see IResource#getType()
   */
  public static boolean resourceIsType(IResource resource, int resourceMask) {
    return (resource.getType() & resourceMask) != 0;
  }

}
