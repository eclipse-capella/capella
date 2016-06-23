/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.model.links.helpers.commands;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.core.model.links.helpers.LinkInfo.LinkStyle;

public class CreateFunctionalAllocationCommand extends CreateTraceCommand {
  public CreateFunctionalAllocationCommand(EClass linkType, EReference linkRefInSource) {
    super("Allocation", LinkStyle.LINE_DASHED, linkType, linkRefInSource);
  }
}
