/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.navigator.handlers;

import org.polarsys.capella.core.platform.sirius.ui.navigator.helpers.FixCustomFeaturesHelper;

/**
 *
 */
public class FixCustomFeaturesHandler extends AbstractFixDiagramsHandler {

  public FixCustomFeaturesHandler() {
    setDialogConfirmationMessage(Messages.FixCustomFeatureHandler_ConfirmFixCustomDialog_Text);
    setJobName(Messages.FixCustomFeaturesJobName);
    setFixHelper(new FixCustomFeaturesHelper());
  }
}
