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
package org.polarsys.capella.core.business.queries.information;

import org.polarsys.capella.core.business.abstractqueries.CapellaElement_CurrentAndHigherLevelsQuery;

/**
 * This is an abstract base class for data values' types queries.<br>
 * Basically, it gets every Data Types corresponding to a given <code>EClass</code>.<br>
 * This <code>EClass</code> is given by concrete implementations of this class via the <code>getAvailableEClassForType()</code> method.
 */
public abstract class AbstractValue_Type extends CapellaElement_CurrentAndHigherLevelsQuery {

}
