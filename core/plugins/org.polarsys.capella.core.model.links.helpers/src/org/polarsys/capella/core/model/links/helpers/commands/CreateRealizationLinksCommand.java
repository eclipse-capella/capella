/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.core.model.links.helpers.LinkInfo.LinkStyle;

/**
 */
public class CreateRealizationLinksCommand extends CreateTraceCommand {
  /**
   * 
   */
  public CreateRealizationLinksCommand(EClass linkType, EReference linkRefInSource) {
    super("Realization", LinkStyle.LINE_DASHED, linkType, linkRefInSource);
  }
}
