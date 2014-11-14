/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.flexibility.commands.dynamic;

import java.util.ArrayList;

import org.eclipse.core.commands.Category;
import org.eclipse.core.commands.Command;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IContributionManagerOverrides;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.menus.AbstractContributionFactory;
import org.eclipse.ui.menus.CommandContributionItem;
import org.eclipse.ui.menus.CommandContributionItemParameter;
import org.eclipse.ui.menus.IContributionRoot;
import org.eclipse.ui.menus.IMenuService;
import org.eclipse.ui.services.IServiceLocator;

import org.polarsys.capella.core.flexibility.commands.menus.provider.CommandsLabelProvider;
import org.polarsys.capella.core.flexibility.commands.menus.provider.MenuInput;
import org.polarsys.capella.core.flexibility.commands.menus.ui.CompoundOverrides;
import org.polarsys.capella.core.flexibility.commands.menus.ui.PerspectiveOverrides;
import org.polarsys.capella.core.flexibility.commands.menus.ui.PolicyOverrides;

/**
 *
 */
public class MenuProvider {

  public MenuManager createMenuManager(String text_p, ImageDescriptor image_p, String id_p) {
    MenuManager manager = new MenuManager(text_p, image_p, id_p);
    manager.setOverrides(getOverrides());
    return manager;
  }

  private IContributionManagerOverrides _overrides = null;

  public IContributionManagerOverrides getOverrides() {
    if (_overrides == null) {
      CompoundOverrides compoundOverrides = new CompoundOverrides();
      compoundOverrides.add(new PerspectiveOverrides());
      compoundOverrides.add(new PolicyOverrides());
      _overrides = compoundOverrides;
    }
    return _overrides;
  }

  public static MenuProvider INSTANCE = new MenuProvider();

  private ILabelProvider labelProvider = new CommandsLabelProvider();

  ISelectionProvider selectionProvider = new ISelectionProvider() {
    ISelection selection = StructuredSelection.EMPTY;

    public void setSelection(ISelection selection_p) {
      selection = selection_p;
    }

    public void removeSelectionChangedListener(ISelectionChangedListener listener_p) {
    }

    public ISelection getSelection() {
      return selection;
    }

    public void addSelectionChangedListener(ISelectionChangedListener listener_p) {
    }
  };

  protected IContributionItem[] getMenus(final IServiceLocator serviceLocator_p, final ITreeContentProvider contentProvider_p, Object menuInput) {

    ArrayList<IContributionItem> items = new ArrayList<IContributionItem>();
    boolean previousIsCategory = false;

    if (menuInput instanceof MenuInput) {
      items.add(new Separator());
    }

    for (final Object object : contentProvider_p.getChildren(menuInput)) {

      if (object instanceof Category) {
        Category category = (Category) object;
        Object root = category;
        if (menuInput instanceof MenuInput) {
          root = menuInput;
        }

        MenuManager submenu =
            createMenuManager(labelProvider.getText(category), ImageDescriptor.createFromImage(labelProvider.getImage(root)), category.getId());
        items.add(submenu);
        for (IContributionItem item : getMenus(serviceLocator_p, contentProvider_p, object)) {
          submenu.add(item);
        }

      } else if (object instanceof Command) {
        Command action = (Command) object;
        if (previousIsCategory) {
          items.add(new Separator());
        }

        CommandContributionItemParameter parameter =
            new CommandContributionItemParameter(serviceLocator_p, action.getId(), action.getId(), CommandContributionItem.STYLE_PUSH);
        parameter.label = labelProvider.getText(action);
        parameter.icon = ImageDescriptor.createFromImage(labelProvider.getImage(action));

        //We don't want to show a disabled action.
        CommandContributionItem item = new CommandContributionItem(parameter) {
          @Override
          public boolean isVisible() {
            return super.isVisible() && isEnabled();
          }
        };
        items.add(item);
      }

      previousIsCategory = object instanceof Category;
    }

    if (menuInput instanceof MenuInput) {
      items.add(new Separator());
    }
    return items.toArray(new IContributionItem[0]);
  }

  AbstractContributionFactory viewMenuAddition = new AbstractContributionFactory("popup:capella.project.explorer#PopupMenu?after=group.goto", "") {

    @Override
    public void createContributionItems(final IServiceLocator serviceLocator_p, IContributionRoot additions_p) {
      ITreeContentProvider contentProvider = new DynamicCommandsContentProvider();
      MenuInput menuInput = new MenuInput(PlatformUI.getWorkbench(), selectionProvider);
      contentProvider.inputChanged(null, null, menuInput);

      for (IContributionItem item : getMenus(serviceLocator_p, contentProvider, menuInput)) {
        additions_p.addContributionItem(item, null);
      }
    }
  };

  public void register() {
    IMenuService menuService = (IMenuService) PlatformUI.getWorkbench().getService(IMenuService.class);
    menuService.addContributionFactory(viewMenuAddition);
  }

  public void unregister() {
    if (viewMenuAddition != null) {
      if (PlatformUI.getWorkbench() != null) {
        IMenuService menuService = (IMenuService) PlatformUI.getWorkbench().getService(IMenuService.class);
        if (menuService != null) {
          menuService.removeContributionFactory(viewMenuAddition);
        }
      }
    }
  }

}
