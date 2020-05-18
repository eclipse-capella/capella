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
package org.polarsys.capella.common.ui.massactions.visualization.contributionitem;

import org.eclipse.ui.IViewPart;
import org.polarsys.capella.common.ui.massactions.activator.MACapellaActivator;
import org.polarsys.capella.common.ui.massactions.shared.menu.AbstractSendToMenuContributionItem;
import org.polarsys.capella.common.ui.massactions.visualization.CapellaMVView;

/**
 * A contribution item for the 'Send To Mass Visualization' menu.
 * 
 * @author Sandu Postaru
 *
 */
public class SendToMVMenuContributionItem extends AbstractSendToMenuContributionItem {

  private static final String MASS_VISUALIZATION_VIEW_NAME = "Mass Visualization";

  @Override
  protected String getViewID() {
    return MACapellaActivator.MV_VIEW_ID;
  }

  @Override
  protected String getViewName() {
    return MASS_VISUALIZATION_VIEW_NAME;
  }

  @Override
  protected String getCommandID() {
    return MACapellaActivator.SEND_TO_MV_VIEW_COMMAND_ID;
  }

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

  @Override
  protected boolean isMassView(IViewPart viewPart) {
    return viewPart instanceof CapellaMVView;
  }

  @Override
  protected String getNewViewIcon() {
    return MACapellaActivator.MV_NEW_VIEW_OBJ;
  }

  @Override
  protected String getExistingViewIcon() {
    return MACapellaActivator.MV_VIEW_OBJ;
  }
}
