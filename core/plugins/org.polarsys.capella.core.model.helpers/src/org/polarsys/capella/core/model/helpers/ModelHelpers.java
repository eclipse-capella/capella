/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.model.helpers;

import org.polarsys.capella.core.data.helpers.cache.CacheInvovationHandler;

public class ModelHelpers {

  public final static IPartExt PartExt;
  public final static IComponentExt ComponentExt;
  public final static IPhysicalLinkExt PhysicalLinkExt;

  static {
    PartExt = CacheInvovationHandler.load(PartExt.class);
    ComponentExt = CacheInvovationHandler.load(ComponentExt.class);
    PhysicalLinkExt = CacheInvovationHandler.load(PhysicalLinkExt.class);
  }
}
