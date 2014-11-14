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
package org.polarsys.capella.core.platform.sirius.ui.navigator.property;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.sirius.ui.tools.api.views.common.item.ItemWrapper;
import org.eclipse.sirius.viewpoint.DRepresentation;

public class ItemWrapperPropertyTester extends PropertyTester {
	public boolean test(Object receiver, String property, Object[] args,
			Object expectedValue) {
		if (receiver instanceof ItemWrapper) {
			ItemWrapper wrapper = (ItemWrapper) receiver;
			if (wrapper.getWrappedObject() instanceof DRepresentation)
				return true;			
		}
		return false;
	}

}
