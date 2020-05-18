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
package org.polarsys.capella.common.ui.massactions.edit.contributionitem;

import org.eclipse.ui.IViewPart;
import org.polarsys.capella.common.ui.massactions.activator.MACapellaActivator;
import org.polarsys.capella.common.ui.massactions.edit.CapellaMEView;
import org.polarsys.capella.common.ui.massactions.shared.menu.AbstractSendToMenuContributionItem;

/**
 * A contribution item for the 'Send To Mass Edition' menu.
 * 
 * @author Sandu Postaru
 *
 */
public class SendToMEMenuContributionItem extends AbstractSendToMenuContributionItem {

  private static final String MASS_EDITING_VIEW_NAME = "Mass Editing";

  @Override
  protected String getViewID() {
    return MACapellaActivator.ME_VIEW_ID;
  }

  @Override
  protected String getViewName() {
    return MASS_EDITING_VIEW_NAME;
  }

  @Override
  protected String getCommandID() {
    return MACapellaActivator.SEND_TO_ME_VIEW_COMMAND_ID;
  }

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

  @Override
  protected boolean isMassView(IViewPart viewPart) {
    return viewPart instanceof CapellaMEView;
  }

  @Override
  protected String getNewViewIcon() {
    return MACapellaActivator.ME_NEW_VIEW_OBJ;
  }

  @Override
  protected String getExistingViewIcon() {
    return MACapellaActivator.ME_VIEW_OBJ;
  }
}
