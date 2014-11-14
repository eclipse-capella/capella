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
	   * @param label_p
	   * @param linkGraphicalRepresentation_p
	   * @param linkType_p
	   * @param linkRefInSource_p
	   */
	  public AddModeStateToFunctionCommand(EClass linkType_p, EReference linkRefInSource_p) {
	    super("Add Mode or State to Abstract Function", LinkStyle.LINE_SOLID, linkType_p, linkRefInSource_p);
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
