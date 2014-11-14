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

import org.polarsys.capella.common.ui.providers.MDEAdapterFactoryLabelProvider;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;

/**
 * The Capella element label provider.
 * 
 */
public class CapellaElementLabelProvider extends MDEAdapterFactoryLabelProvider {

	/**
	 * Constructs the Capella element label provider.
	 */
	public CapellaElementLabelProvider() {
		super(MDEAdapterFactory.getEditingDomain(), MDEAdapterFactory.getAdapterFactory());
	}
}
