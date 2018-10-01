/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui.massactions.visualization.commands;

import org.polarsys.capella.common.ui.massactions.activator.MACapellaActivator;
import org.polarsys.capella.common.ui.massactions.shared.menu.AbstractSentToCommandHandler;

/**
 * A command handler for the 'Send To Mass Visualization' action.
 * 
 * @author Sandu Postaru
 *
 */
public class SentToMVCommandHandler extends AbstractSentToCommandHandler {

  @Override
  protected String getCommandParameterPrimaryID() {
    return MACapellaActivator.SEND_TO_MV_VIEW_COMMAND_PARAMETER_PRIMARY_ID;
  }

  @Override
  protected String getCommandParameterSecondaryID() {
    return MACapellaActivator.SEND_TO_MV_VIEW_COMMAND_PARAMETER_SECONDARY_ID;
  }
}
