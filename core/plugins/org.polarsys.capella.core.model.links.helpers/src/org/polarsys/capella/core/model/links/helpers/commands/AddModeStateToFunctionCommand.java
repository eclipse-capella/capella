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

package org.polarsys.capella.core.model.links.helpers.commands;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.core.model.links.helpers.LinkInfo.LinkStyle;

/**
 * Command for adding Mode to an Abstract Function like OperationalActivity, LogicalFunction , PhysicalFunction,SystemFunction
 *
 */
public class AddModeStateToFunctionCommand  extends AbstractQueryBasedCommand {

	  /**
	   * @param label
	   * @param linkGraphicalRepresentation
	   * @param linkType
	   * @param linkRefInSource
	   */
	  public AddModeStateToFunctionCommand(EClass linkType, EReference linkRefInSource) {
	    super("Add Mode or State to Abstract Function", LinkStyle.LINE_SOLID, linkType, linkRefInSource);
	  }

	  /**
	   * {@inheritDoc}
	   */
	  @Override
	  public void execute() {
	    ((List<EObject>) _sourceElement.eGet(_linkRefInSource)).add(_targetElement);
	  }

	  /**
	   * {@inheritDoc}
	   */
	  @Override
	  public EObject getCreatedLinkObject() {
	    return null;
	  }
	

	}
