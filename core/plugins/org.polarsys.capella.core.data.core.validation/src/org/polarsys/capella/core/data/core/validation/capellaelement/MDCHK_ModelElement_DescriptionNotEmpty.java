/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.core.validation.capellaelement;

import org.polarsys.capella.core.data.capellacore.CapellaElement;

public class MDCHK_ModelElement_DescriptionNotEmpty extends
		Abstract_MDCHK_NoteNotEmpty {

	@Override
	protected String getNoteContent(CapellaElement me) {
		return me.getDescription();
	}



}
