/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.capellamodeller.provider;

import org.eclipse.emf.edit.provider.IItemProviderDecorator;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.gen.edit.decorators.CustomDecoratorAdapterFactory;
import org.polarsys.capella.core.data.gen.edit.decorators.ForwardingItemProviderAdapterDecorator;

public class CapellamodellerItemProviderDecoratorAdapterFactory extends CustomDecoratorAdapterFactory {

	public CapellamodellerItemProviderDecoratorAdapterFactory() {
		super(new CapellamodellerItemProviderAdapterFactory());
	}

	@Override
	protected IItemProviderDecorator createItemProviderDecorator(Object target, Object Type) {
		if (target instanceof SystemEngineering) {
			return new SystemEngineeringItemProviderDecorator(this);
		}
		return new ForwardingItemProviderAdapterDecorator(this);
	}
}
