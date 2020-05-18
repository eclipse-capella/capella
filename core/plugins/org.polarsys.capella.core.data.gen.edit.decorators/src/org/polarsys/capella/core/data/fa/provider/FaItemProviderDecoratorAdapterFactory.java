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
package org.polarsys.capella.core.data.fa.provider;

import org.eclipse.emf.edit.provider.IItemProviderDecorator;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.ControlNode;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;
import org.polarsys.capella.core.data.fa.FunctionalChainReference;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.SequenceLink;
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
    } else if (target instanceof SequenceLink) {
      return new SequenceLinkProviderDecorator(this);
    }
    return new ForwardingItemProviderAdapterDecorator(this);
  }
}
