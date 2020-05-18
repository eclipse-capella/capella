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
package org.polarsys.capella.core.business.abstractqueries;


/**
 * This class is an abstract class for data type elements related queries whose <code>getAvailableElements</code> methods shall return data values gotten the
 * same way in the current level and in the higher levels.<br>
 * For example, if the current data type element is in the Physical layer, the available elements will be searched in the Physical level, but also in the
 * logical, system analysis and operational analysis levels.
 */
public abstract class CapellaElement_CurrentAndHigherLevelsQuery extends AbstractSystemEngineeringQuery {

}
