/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.common.ui.toolkit.viewers.menu;

import java.util.ArrayList;

import javax.inject.Inject;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.ISources;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.PartSite;
import org.eclipse.ui.menus.IMenuService;
import org.eclipse.ui.services.IServiceLocator;

/**
 * This class is intended to extends Eclipse menu contributions to popupMenus displayed in modal dialogs.
 * By providing a ISelectionProvider and a locationUri, menuContributions provided 
 * within org.eclipse.ui.menus extension points will be contributed to the menuManager, 
 * even if the popup is in a modal dialog.
 */
public class ModalContextMenuExtender {

  @Inject
  IEclipseContext context;

  String location;
  
  IServiceLocator locator;
  
  ISelectionProvider provider;
  
  MenuManager contributionManager;

  IEclipseContext popupContext = null;
  
  /**
   * @param contributionManager
   * @param location
   * @param provider
   * @param locator, the parent ServiceLocator (activeWorkbenchWindow for instance)
   */
  public ModalContextMenuExtender(MenuManager contributionManager, String locationUri, ISelectionProvider provider,
      IServiceLocator locator) {
    this.contributionManager = contributionManager;
    this.location = locationUri;
    if (this.location.startsWith("popup:")) {
      this.location = this.location.replaceFirst("popup:", "");
    }
    this.provider = provider;
    this.locator = locator;
  }

  public void registerContextMenu() {
    if ((location == null) || (location.isEmpty())) {
      return;
    }

    ContextMenuSelectionService newService = new ContextMenuSelectionService(provider);
    ((MenuManager) contributionManager).addMenuListener(new IMenuListener() {

      @Override
      public void menuAboutToShow(IMenuManager manager) {
        IEclipseContext currentContext = context.getActiveLeaf();
        if (popupContext == null && currentContext.toString().equals("popup:"+location)) {
          popupContext = currentContext;
        }

        if (popupContext != null) {
          // We override the selection service for the popup context.
          // All menus will be contextualized to the selection provider, not on the selection of the workbench active
          // part
          popupContext.set(ISelectionService.class.getName(), newService);
          popupContext.set(ISources.ACTIVE_CURRENT_SELECTION_NAME, newService.getSelection());
          popupContext.set(ISources.ACTIVE_MENU_SELECTION_NAME, newService.getSelection());

          // We also override the activePartId attribute. For some menu that are enabled/disabled according to the
          // partId,
          // they shall not react to it since the menu is from a modal view, not the workbench active part.
          popupContext.set(ISources.ACTIVE_PART_ID_NAME, "modal");
          popupContext.processWaiting();
        }
      }
    });

    IMenuService menuService = locator.getService(IMenuService.class);
    menuService.populateContributionManager(contributionManager, location);
    PartSite.registerContextMenu(location, (MenuManager) contributionManager, provider, false,
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart(), context,
        new ArrayList<EObject>());
  }

  /**
   * This method register the current menu to Eclipse
   */
  public static ModalContextMenuExtender registerContextMenu(MenuManager _contextMenuManager,
      String contextMenuLocation, ISelectionProvider provider) {
    IEclipseContext context = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getService(IEclipseContext.class);
    ModalContextMenuExtender popupPopulator = new ModalContextMenuExtender(_contextMenuManager, contextMenuLocation,
        provider, PlatformUI.getWorkbench().getActiveWorkbenchWindow());
    ContextInjectionFactory.inject(popupPopulator, context);
    popupPopulator.registerContextMenu();
    return popupPopulator;
  }

}