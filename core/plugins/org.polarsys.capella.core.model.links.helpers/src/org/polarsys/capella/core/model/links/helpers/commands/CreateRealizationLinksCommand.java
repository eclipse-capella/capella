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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.core.model.links.helpers.LinkInfo.LinkStyle;

/**
 */
public class CreateRealizationLinksCommand extends CreateTraceCommand {
  /**
   * 
   */
  public CreateRealizationLinksCommand(EClass linkType_p, EReference linkRefInSource_p) {
    super("Realization", LinkStyle.LINE_DASHED, linkType_p, linkRefInSource_p);
  }
}
