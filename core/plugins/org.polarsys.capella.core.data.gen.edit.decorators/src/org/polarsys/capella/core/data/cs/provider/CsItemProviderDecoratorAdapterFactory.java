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
package org.polarsys.capella.core.data.cs.provider;

import org.eclipse.emf.edit.provider.IItemProviderDecorator;
import org.polarsys.capella.core.data.cs.ComponentRealization;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.gen.edit.decorators.CustomDecoratorAdapterFactory;
import org.polarsys.capella.core.data.gen.edit.decorators.ForwardingItemProviderAdapterDecorator;

public class CsItemProviderDecoratorAdapterFactory extends CustomDecoratorAdapterFactory {

	public CsItemProviderDecoratorAdapterFactory() {
		super(new CsItemProviderAdapterFactory());
	}

	@Override
	protected IItemProviderDecorator createItemProviderDecorator(Object target, Object Type) {
		if (target instanceof ExchangeItemAllocation) {
			return new ExchangeItemAllocationItemProviderDecorator(this);
		} else if (target instanceof Part) {
      return new PartItemProviderDecorator(this);
    } else if (target instanceof ComponentRealization) {
      return new ComponentRealizationItemProviderDecorator(this);
    }
		return new ForwardingItemProviderAdapterDecorator(this);
	}
}
