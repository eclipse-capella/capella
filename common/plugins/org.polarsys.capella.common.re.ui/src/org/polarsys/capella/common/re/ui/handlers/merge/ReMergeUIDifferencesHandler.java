/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.common.re.ui.handlers.merge;

import org.polarsys.capella.core.transition.common.ui.handlers.merge.MergeEMFDiffNode;
import org.polarsys.capella.core.transition.common.ui.handlers.merge.MergeUIDifferencesHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class ReMergeUIDifferencesHandler extends MergeUIDifferencesHandler {

  @Override
  protected MergeEMFDiffNode createDiffNode(IContext context) {
    MergeEMFDiffNode diffNode = super.createDiffNode(context);
    diffNode.setDefaultIncrementalMode(false);
    return diffNode;
  }
}
