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
package org.polarsys.capella.core.data.information.communication.provider;

import org.eclipse.emf.edit.provider.IItemProviderDecorator;
import org.polarsys.capella.core.data.gen.edit.decorators.CustomDecoratorAdapterFactory;
import org.polarsys.capella.core.data.gen.edit.decorators.ForwardingItemProviderAdapterDecorator;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;

public class CommunicationItemProviderDecoratorAdapterFactory extends CustomDecoratorAdapterFactory {

	public CommunicationItemProviderDecoratorAdapterFactory() {
		super(new CommunicationItemProviderAdapterFactory());
	}

	@Override
	protected IItemProviderDecorator createItemProviderDecorator(Object target, Object Type) {
		if (target instanceof CommunicationLink) {
			return new CommunicationLinkItemProviderDecorator(this);
		}
		return new ForwardingItemProviderAdapterDecorator(this);
	}
}
