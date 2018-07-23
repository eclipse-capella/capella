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
package org.polarsys.capella.common.ui.massactions.visualization.contributionitem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.eclipse.ui.menus.CommandContributionItem;
import org.eclipse.ui.menus.CommandContributionItemParameter;
import org.eclipse.ui.menus.IWorkbenchContribution;
import org.eclipse.ui.services.IServiceLocator;
import org.polarsys.capella.common.ui.massactions.activator.MACapellaActivator;
import org.polarsys.capella.common.ui.massactions.visualization.CapellaMVView;

/**
 * A contribution item for the 'Send To Mass Visualization' menu.
 * 
 * @author Sandu Postaru
 *
 */
public class SendToMVMenuContributionItem extends CompoundContributionItem implements IWorkbenchContribution {

  private static final String NEW_MASS_VISUALIZATION_VIEW_TEXT = "New Mass Visualization View";
  private IServiceLocator serviceLocator;

  @Override
  public void initialize(IServiceLocator serviceLocator) {
    this.serviceLocator = serviceLocator;
  }

  @Override
  protected IContributionItem[] getContributionItems() {

    IViewReference[] viewReferences = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
        .getViewReferences();

    List<IViewPart> meViewParts = Stream.of(viewReferences).map(viewRef -> viewRef.getView(false))
        .filter(viewPart -> viewPart instanceof CapellaMVView).collect(Collectors.toList());

    IContributionItem[] contributionItems;

    if (meViewParts.isEmpty()) {
      contributionItems = new IContributionItem[1];
      IContributionItem defaultViewOption = createContributionItem(NEW_MASS_VISUALIZATION_VIEW_TEXT,
          MACapellaActivator.MV_VIEW_ID, null);
      contributionItems[0] = defaultViewOption;

    } else {
      contributionItems = new IContributionItem[meViewParts.size()];

      for (int i = 0; i < meViewParts.size(); i++) {
        IViewPart viewPart = meViewParts.get(i);
        IContributionItem contributionItem = createContributionItem(viewPart.getTitle(), viewPart.getViewSite().getId(),
            viewPart.getViewSite().getSecondaryId());

        contributionItems[i] = contributionItem;
      }
    }

    return contributionItems;
  }

  protected IContributionItem createContributionItem(String itemLabel, String primaryViewId, String secondaryViewId) {

    Map<String, String> parameters = new HashMap<>();
    parameters.put(MACapellaActivator.SEND_TO_MV_VIEW_COMMAND_PARAMETER_PRIMARY_ID, primaryViewId);
    parameters.put(MACapellaActivator.SEND_TO_MV_VIEW_COMMAND_PARAMETER_SECONDARY_ID, secondaryViewId);

    CommandContributionItemParameter parameter = new CommandContributionItemParameter(serviceLocator, "",
        MACapellaActivator.SEND_TO_MV_VIEW_COMMAND_ID, CommandContributionItem.STYLE_PULLDOWN);
    parameter.label = itemLabel;
    parameter.tooltip = itemLabel;
    parameter.parameters = parameters;

    return new CommandContributionItem(parameter);
  }

}
