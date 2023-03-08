/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.sirius.validation.ddiagram;

public class I_23_ReplaceWithDiagramElementResolver extends I_23_AbstractReplaceElementResolver {

  @Override
  protected AbstractReplaceInvalidHyperLinkInDescription getReplaceDescription() {
    return new ReplaceInvalidHyperLinkInDescriptionWithDiagramElement();
  }
}