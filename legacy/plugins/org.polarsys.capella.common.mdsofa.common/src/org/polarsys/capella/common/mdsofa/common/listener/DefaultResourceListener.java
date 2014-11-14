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
package org.polarsys.capella.common.mdsofa.common.listener;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;

/**
 * A default resource change listener that watches changes on a given resource.
 */
public abstract class DefaultResourceListener implements IResourceChangeListener {
  /**
   * Resource that should be watched.
   */
  private IResource _resourceToWatch;
  /**
   * Is listener already registered (true) or not (false) ?
   */
  private volatile boolean _alreadyRegistered;

  /**
   * Set resource to watch.<br>
   * Also register listener, if needed.
   * @param resource_p
   */
  public void setResourceToWatch(IResource resource_p) {
    _resourceToWatch = resource_p;
    if (!_alreadyRegistered) {
      ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
      _alreadyRegistered = true;
    }
  }

  /**
   * Free resources and unregister the listener.
   */
  public void dispose() {
    _resourceToWatch = null;
    if (_alreadyRegistered) {
      ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
      _alreadyRegistered = false;
    }
  }

  /**
   * @see org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org.eclipse.core.resources.IResourceChangeEvent)
   */
  public void resourceChanged(IResourceChangeEvent event_p) {
    // Watched resource has changed, do something !
    if (refersToResource(_resourceToWatch, event_p)) {
      handleResourceChanged(event_p, _resourceToWatch);
    }
  }

  /**
   * Handle a change for watched resource.
   * @param event_p
   * @param watchedResource_p
   */
  protected abstract void handleResourceChanged(IResourceChangeEvent event_p, IResource watchedResource_p);

  /**
   * Does given resource event refer to given resource ?
   * @param lookedForResource_p
   * @param event_p
   * @return
   */
  protected boolean refersToResource(IResource lookedForResource_p, IResourceChangeEvent event_p) {
    // Pre-condition.
    if ((null == lookedForResource_p) || (null == event_p)) {
      return false;
    }
    return refersToResource(lookedForResource_p, event_p.getDelta());
  }

  /**
   * Does given resource delta refer to given resource ?
   * @param lookedForResource_p
   * @param resourceDelta_p
   * @return
   */
  private boolean refersToResource(IResource lookedForResource_p, IResourceDelta resourceDelta_p) {
    IResource resource = resourceDelta_p.getResource();
    boolean result = lookedForResource_p.equals(resource);
    if (!result) {
      // Iterate over children.
      IResourceDelta[] children = resourceDelta_p.getAffectedChildren();
      for (int i = 0; (i < children.length) && !result; i++) {
        result = refersToResource(lookedForResource_p, children[i]);
      }
    }
    return result;
  }
}
