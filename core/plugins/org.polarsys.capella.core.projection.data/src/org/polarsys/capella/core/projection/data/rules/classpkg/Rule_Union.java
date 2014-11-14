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
package org.polarsys.capella.core.projection.data.rules.classpkg;

import org.polarsys.capella.core.data.information.InformationPackage;

public class Rule_Union extends Rule_GeneralClass {

	public Rule_Union() {
		super(InformationPackage.Literals.UNION, InformationPackage.Literals.UNION);
	}
}
