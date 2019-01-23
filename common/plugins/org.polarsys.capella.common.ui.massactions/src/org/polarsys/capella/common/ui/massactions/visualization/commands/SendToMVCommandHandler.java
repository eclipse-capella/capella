/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.common.ui.massactions.shared.menu.AbstractSendToCommandHandler;

/**
 * A command handler for the 'Send To Mass Visualization' action.
 * 
 * @author Sandu Postaru
 *
 */
public class SendToMVCommandHandler extends AbstractSendToCommandHandler {

  @Override
  protected String getCommandParameterPrimaryId() {
    return MACapellaActivator.SEND_TO_MV_VIEW_COMMAND_PARAMETER_PRIMARY_ID;
  }

  @Override
  protected String getCommandParameterSecondaryId() {
    return MACapellaActivator.SEND_TO_MV_VIEW_COMMAND_PARAMETER_SECONDARY_ID;
  }

  @Override
  protected String getCommandParameterShouldCreateViewId() {
    return MACapellaActivator.SEND_TO_MV_VIEW_COMMAND_PARAMTER_SHOULD_CREATE_VIEW_ID;
  }
}
