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
package org.polarsys.capella.common.ui.massactions.shared.menu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.eclipse.ui.menus.CommandContributionItem;
import org.eclipse.ui.menus.CommandContributionItemParameter;
import org.eclipse.ui.menus.IWorkbenchContribution;
import org.eclipse.ui.services.IServiceLocator;
import org.polarsys.capella.common.ui.massactions.activator.MACapellaActivator;

/**
 * A abstract contribution item for the 'Send To' mass menu.
 * 
 * @author Ali Akar
 *
 */
public abstract class AbstractSendToMenuContributionItem extends CompoundContributionItem
    implements IWorkbenchContribution {

  private IServiceLocator serviceLocator;

  protected abstract String getViewID();

  protected abstract String getViewName();

  protected abstract String getCommandID();

  protected abstract String getNewViewIcon();

  protected abstract String getExistingViewIcon();

  protected abstract String getCommandParameterPrimaryId();

  protected abstract String getCommandParameterSecondaryId();

  protected abstract String getCommandParameterShouldCreateViewId();

  protected abstract boolean isMassView(IViewPart viewPart);

  @Override
  public void initialize(IServiceLocator serviceLocator) {
    this.serviceLocator = serviceLocator;
  }

  @Override
  protected IContributionItem[] getContributionItems() {

    IViewReference[] viewReferences = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
        .getViewReferences();

    // We put the stream in a try-with-resource to satisfy Sonar
    try (Stream<IViewReference> viewReferenceStream = Stream.of(viewReferences)) {
      List<IViewPart> maViewParts = viewReferenceStream.map(viewRef -> viewRef.getView(false)).filter(this::isMassView)
          .collect(Collectors.toList());

      Collection<IContributionItem> contributionItems = new ArrayList<>();

      // Add a contribution item to create a new view
      contributionItems.add(getNewViewContributionItem());

      // Add a contribution item for each existing view
      if (!maViewParts.isEmpty()) {
        contributionItems.add(new Separator());
        contributionItems.addAll(getExistingViewContributionItems(maViewParts));
      }

      // Return the contribution items
      return contributionItems.toArray(new IContributionItem[contributionItems.size()]);
    }
  }

  protected IContributionItem getNewViewContributionItem() {
    return createContributionItem(getNewViewText(), getNewViewIcon(), getViewID(), null, true);
  }

  protected Collection<IContributionItem> getExistingViewContributionItems(List<IViewPart> maViewParts) {
    Collection<IContributionItem> contributionItems = new ArrayList<>();

    for (IViewPart viewPart : maViewParts) {
      IContributionItem contributionItem = createContributionItem(viewPart.getTitle(), getExistingViewIcon(),
          viewPart.getViewSite().getId(), viewPart.getViewSite().getSecondaryId(), false);
      contributionItems.add(contributionItem);
    }
    return contributionItems;
  }

  protected IContributionItem createContributionItem(String itemLabel, String iconKey, String primaryViewId,
      String secondaryViewId, boolean shouldCreateViewId) {

    Map<String, String> parameters = new HashMap<>();
    parameters.put(getCommandParameterPrimaryId(), primaryViewId);
    parameters.put(getCommandParameterSecondaryId(), secondaryViewId);
    parameters.put(getCommandParameterShouldCreateViewId(), String.valueOf(shouldCreateViewId));

    CommandContributionItemParameter parameter = new CommandContributionItemParameter(serviceLocator, "",
        getCommandID(), CommandContributionItem.STYLE_PULLDOWN);
    parameter.label = itemLabel;
    parameter.tooltip = itemLabel;
    parameter.parameters = parameters;
    parameter.icon = MACapellaActivator.getDefault().getImageRegistry().getDescriptor(iconKey);

    return new CommandContributionItem(parameter);
  }

  protected String getNewViewText() {
    return "New " + getViewName() + " View";
  }
}
