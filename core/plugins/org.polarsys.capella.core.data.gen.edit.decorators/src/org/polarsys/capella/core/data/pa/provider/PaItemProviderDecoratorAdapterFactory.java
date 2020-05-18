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
package org.polarsys.capella.core.data.pa.provider;

import org.eclipse.emf.edit.provider.IItemProviderDecorator;
import org.polarsys.capella.core.data.gen.edit.decorators.CustomDecoratorAdapterFactory;
import org.polarsys.capella.core.data.gen.edit.decorators.ForwardingItemProviderAdapterDecorator;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.data.pa.PhysicalFunction;

public class PaItemProviderDecoratorAdapterFactory extends CustomDecoratorAdapterFactory {

  public PaItemProviderDecoratorAdapterFactory() {
    super(new PaItemProviderAdapterFactory());
  }

  @Override
  protected IItemProviderDecorator createItemProviderDecorator(Object target, Object Type) {
    if (target instanceof PhysicalComponent) {
      return new PhysicalComponentItemProviderDecorator(this);
    } else if (target instanceof PhysicalFunction) {
      return new PhysicalFunctionItemProviderDecorator(this);
    } else if (target instanceof PhysicalComponentPkg) {
      return new PhysicalComponentPkgItemProviderDecorator(this);
    }
    return new ForwardingItemProviderAdapterDecorator(this);
  }
}
