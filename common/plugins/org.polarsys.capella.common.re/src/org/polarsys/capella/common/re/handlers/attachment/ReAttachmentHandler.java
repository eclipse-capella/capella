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

package org.polarsys.capella.common.re.handlers.attachment;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.core.transition.common.handlers.attachment.DefaultAttachmentHandler;

/**
 */
public class ReAttachmentHandler extends DefaultAttachmentHandler {

  @Override
  protected boolean isHandlingOrdering(EObject sourceAttaching, EObject targetAttaching, EObject sourceAttached, EObject targetAttached,
      EReference sourceFeature, EReference targetFeature) {
    return false;
  }

}
