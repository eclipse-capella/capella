/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.la.provider;

import org.eclipse.emf.edit.provider.IItemProviderDecorator;
import org.polarsys.capella.core.data.gen.edit.decorators.CustomDecoratorAdapterFactory;
import org.polarsys.capella.core.data.gen.edit.decorators.ForwardingItemProviderAdapterDecorator;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.la.LogicalFunction;

public class LaItemProviderDecoratorAdapterFactory extends CustomDecoratorAdapterFactory {

  public LaItemProviderDecoratorAdapterFactory() {
    super(new LaItemProviderAdapterFactory());
  }

  @Override
  protected IItemProviderDecorator createItemProviderDecorator(Object target, Object Type) {
    if (target instanceof LogicalFunction) {
      return new LogicalFunctionItemProviderDecorator(this);
    } else if (target instanceof LogicalComponentPkg) {
      return new LogicalComponentPkgItemProviderDecorator(this);
    } else if (target instanceof LogicalComponent) {
      return new LogicalComponentItemProviderDecorator(this);
    }
    return new ForwardingItemProviderAdapterDecorator(this);
  }
}
