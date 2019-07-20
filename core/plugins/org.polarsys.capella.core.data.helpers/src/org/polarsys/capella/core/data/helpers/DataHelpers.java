/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.helpers;

import org.polarsys.capella.core.data.helpers.cache.CacheInvovationHandler;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.helpers.fa.services.IFunctionExt;

public class DataHelpers {

	public final static IFunctionExt FunctionExt;

	static {
		FunctionExt = CacheInvovationHandler.load(FunctionExt.class);
	}
}