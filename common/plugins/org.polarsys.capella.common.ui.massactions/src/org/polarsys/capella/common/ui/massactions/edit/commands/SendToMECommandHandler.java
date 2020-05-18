/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui.massactions.edit.commands;

import org.polarsys.capella.common.ui.massactions.activator.MACapellaActivator;
import org.polarsys.capella.common.ui.massactions.shared.menu.AbstractSendToCommandHandler;

/**
 * A command handler for the 'Send To Mass Edition' action.
 * 
 * @author Sandu Postaru
 *
 */
public class SendToMECommandHandler extends AbstractSendToCommandHandler {

  @Override
  protected String getCommandParameterPrimaryId() {
    return MACapellaActivator.SEND_TO_ME_VIEW_COMMAND_PARAMETER_PRIMARY_ID;
  }

  @Override
  protected String getCommandParameterSecondaryId() {
    return MACapellaActivator.SEND_TO_ME_VIEW_COMMAND_PARAMETER_SECONDARY_ID;
  }

  @Override
  protected String getCommandParameterShouldCreateViewId() {
    return MACapellaActivator.SEND_TO_ME_VIEW_COMMAND_PARAMTER_SHOULD_CREATE_VIEW_ID;
  }
}
