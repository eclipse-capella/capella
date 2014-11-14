/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.toolkit.viewers;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.ui.providers.MDEAdapterFactoryLabelProvider;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;

/**
 * The Capella element label provider.
 * 
 */
public class CapellaElementLabelProvider extends MDEAdapterFactoryLabelProvider {

	/**
	 * Constructs the Capella element label provider.
	 */
	public CapellaElementLabelProvider() {
		super(CapellaAdapterFactoryProvider.getInstance().getAdapterFactory());
	}

	/**
	 * Constructs the Capella element label provider.
	 * @param eobject_p
	 */
	public CapellaElementLabelProvider(EObject eobject_p) {
		super(TransactionHelper.getEditingDomain(eobject_p), CapellaAdapterFactoryProvider.getInstance().getAdapterFactory());
	}
}
