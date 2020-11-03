/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.ui.massactions.shared.menu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.eclipse.ui.menus.CommandContributionItem;
import org.eclipse.ui.menus.CommandContributionItemParameter;
import org.eclipse.ui.menus.IWorkbenchContribution;
import org.eclipse.ui.services.IServiceLocator;
import org.polarsys.capella.common.ui.massactions.activator.MACapellaActivator;
import org.polarsys.capella.common.ui.massactions.shared.helper.CapellaMASelectionHelper;
import org.polarsys.kitalpha.massactions.shared.view.MAView;

/**
 * A abstract contribution item for the 'Send To' mass menu.
 * 
 * @author Sandu Postaru
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

  protected CapellaMASelectionHelper selectionHelper;

  @Override
  public void initialize(IServiceLocator serviceLocator) {
    this.serviceLocator = serviceLocator;
    this.selectionHelper = new CapellaMASelectionHelper();
  }

  /**
   * Contribution items are created only for valid MAView targets. A valid target either has a null editing domain,
   * meaning that no elements are present yet, or has the same editing domain as the current user selection.
   */
  @Override
  protected IContributionItem[] getContributionItems() {

    IViewReference[] viewReferences = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
        .getViewReferences();

    // We put the stream in a try-with-resource to satisfy Sonar
    try (Stream<IViewReference> viewReferenceStream = Stream.of(viewReferences)) {

      // The current elements selected by the user
      ISelectionService selectionService = serviceLocator.getService(ISelectionService.class);
      TransactionalEditingDomain selectionEditingDomain = selectionHelper
          .getEditingDomainFromSelectionService(selectionService);

      List<MAView> maViewParts = Collections.emptyList();

      // The selection elements share the same editing domain
      if (selectionEditingDomain != null) {

        // Filter all the MAViews that have the same editing domain as the current selection
        maViewParts = viewReferenceStream.map(viewRef -> viewRef.getView(false)).filter(this::isMassView)
            .map(MAView.class::cast).filter(validMAViewTarget(selectionEditingDomain)).collect(Collectors.toList());
      }

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

  protected Collection<IContributionItem> getExistingViewContributionItems(List<MAView> maViewParts) {
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

  /**
   * A valid target predicate for the current MAView. The editing domain may be null, meaning that the view does not
   * contain any elements and thus any selection is valid. Otherwise the editing domain of the view must match the
   * selection editing domain.
   * 
   * @param selectionEditingDomain
   *          the selection editing domain
   * @return if the current MAView is a valid target for the selectionServiceEditing domain
   */
  private Predicate<? super MAView> validMAViewTarget(TransactionalEditingDomain selectionEditingDomain) {
    return view -> view.getEditingDomain() == null || view.getEditingDomain().equals(selectionEditingDomain);
  }

}
