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
package org.polarsys.capella.core.projection.data.rules.datavalue;

import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;

public class Rule_LiteralBooleanValue extends Rule_DataValue {

	public Rule_LiteralBooleanValue() {
		super(DatavaluePackage.Literals.LITERAL_BOOLEAN_VALUE, DatavaluePackage.Literals.LITERAL_BOOLEAN_VALUE);
	  _updatedAttributes.add(DatavaluePackage.Literals.LITERAL_BOOLEAN_VALUE__VALUE.getName());
	}

}
