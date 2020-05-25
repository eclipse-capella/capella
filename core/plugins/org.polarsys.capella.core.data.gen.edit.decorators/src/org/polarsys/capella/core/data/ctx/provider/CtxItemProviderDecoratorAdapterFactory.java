/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.ctx.provider;

import org.eclipse.emf.edit.provider.IItemProviderDecorator;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.ctx.SystemComponentPkg;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.gen.edit.decorators.CustomDecoratorAdapterFactory;
import org.polarsys.capella.core.data.gen.edit.decorators.ForwardingItemProviderAdapterDecorator;

public class CtxItemProviderDecoratorAdapterFactory extends CustomDecoratorAdapterFactory {

  public CtxItemProviderDecoratorAdapterFactory() {
    super(new CtxItemProviderAdapterFactory());
  }

  @Override
  protected IItemProviderDecorator createItemProviderDecorator(Object target, Object Type) {
    if (target instanceof SystemFunction) {
      return new SystemFunctionItemProviderDecorator(this);
    } else if (target instanceof SystemComponentPkg) {
      return new SystemComponentPkgItemProviderDecorator(this);
    } else if (target instanceof SystemComponent) {
      return new SystemComponentItemProviderDecorator(this);
    }

    return new ForwardingItemProviderAdapterDecorator(this);
  }
}
