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
package org.polarsys.capella.core.data.fa.provider;

import org.eclipse.emf.edit.provider.IItemProviderDecorator;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.ControlNode;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;
import org.polarsys.capella.core.data.fa.FunctionalChainReference;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.gen.edit.decorators.CustomDecoratorAdapterFactory;
import org.polarsys.capella.core.data.gen.edit.decorators.ForwardingItemProviderAdapterDecorator;

public class FaItemProviderDecoratorAdapterFactory extends CustomDecoratorAdapterFactory {

  public FaItemProviderDecoratorAdapterFactory() {
    super(new FaItemProviderAdapterFactory());
  }

  @Override
  protected IItemProviderDecorator createItemProviderDecorator(Object target, Object Type) {
    if (target instanceof ComponentPort) {
      return new ComponentPortItemProviderDecorator(this);
    } else if (target instanceof FunctionalExchange) {
      return new FunctionalExchangeItemProviderDecorator(this);
    } else if (target instanceof FunctionalChainReference) {
      return new FunctionalReferenceItemProviderDecorator(this);
    } else if (target instanceof FunctionalChainInvolvementFunction) {
      return new FunctionalChainInvolvementFunctionItemProviderDecorator(this);
    } else if (target instanceof FunctionalChainInvolvementLink) {
      return new FunctionalChainInvolvementLinkItemProviderDecorator(this);
    } else if (target instanceof ControlNode) {
      return new ControlNodeItemProviderDecorator(this);
    }
    return new ForwardingItemProviderAdapterDecorator(this);
  }
}
