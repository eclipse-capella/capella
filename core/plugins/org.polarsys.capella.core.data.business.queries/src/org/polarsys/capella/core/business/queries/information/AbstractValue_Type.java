/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.business.queries.information;

import org.polarsys.capella.core.business.abstractqueries.CapellaElement_CurrentAndHigherLevelsQuery;

/**
 * This is an abstract base class for data values' types queries.<br>
 * Basically, it gets every Data Types corresponding to a given <code>EClass</code>.<br>
 * This <code>EClass</code> is given by concrete implementations of this class via the <code>getAvailableEClassForType()</code> method.
 */
public abstract class AbstractValue_Type extends CapellaElement_CurrentAndHigherLevelsQuery {

}
