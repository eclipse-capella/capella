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
package org.polarsys.capella.core.transition.system.topdown.handlers.attachment;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.capellaHelpers.HashMapSet;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class ActivityAllocationAttachmentHelper implements IHandler {

  protected static final String ATTACHMENT_MAP = "AAAttachmentMap"; //$NON-NLS-1$

  public static ActivityAllocationAttachmentHelper getInstance(IContext context_p) {
    ActivityAllocationAttachmentHelper handler = (ActivityAllocationAttachmentHelper) context_p.get(ITopDownConstants.ACTIVITY_ALLOCATION_ATTACHMENT_HANDLER);
    if (handler == null) {
      handler = new ActivityAllocationAttachmentHelper();
      handler.init(context_p);
      context_p.put(ITopDownConstants.ACTIVITY_ALLOCATION_ATTACHMENT_HANDLER, handler);
    }
    return handler;
  }

  @SuppressWarnings("unchecked")
  protected HashMapSet<ComponentFunctionalAllocation, Entity> getMap(IContext context_p) {
    if (context_p.get(ATTACHMENT_MAP) == null) {
      context_p.put(ATTACHMENT_MAP, new HashMapSet<ComponentFunctionalAllocation, Entity>());
    }
    return (HashMapSet<ComponentFunctionalAllocation, Entity>) context_p.get(ATTACHMENT_MAP);
  }

  public void addEntity(IContext context_p, ComponentFunctionalAllocation allocation, Entity entity) {
    getMap(context_p).put(allocation, entity);
  }

  public void removeEntity(IContext context_p, ComponentFunctionalAllocation allocation, Entity entity) {
    getMap(context_p).remove(allocation, entity);
  }

  public Collection<Entity> getEntities(IContext context_p, ComponentFunctionalAllocation allocation) {
    return getMap(context_p).get(allocation);
  }

  /**
   * {@inheritDoc}
   */
  public IStatus init(IContext context_p) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus dispose(IContext context_p) {
    return Status.OK_STATUS;
  }

}
