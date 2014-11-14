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
package org.polarsys.capella.core.flexibility.commands.menus.provider;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.commands.Category;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.jface.viewers.ICheckStateProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;

import org.polarsys.capella.core.flexibility.commands.menus.ui.PerspectiveOverrides;

/**
 *
 */
public class CommandsContentProvider implements ITreeContentProvider, ICheckStateProvider {

  /**
   * {@inheritDoc}
   */
  public void dispose() {
  }

  protected MenuInput currentInput = null;

  /**
   * {@inheritDoc}
   */
  public void inputChanged(Viewer viewer_p, Object oldInput_p, Object newInput_p) {
    if (newInput_p instanceof MenuInput) {
      currentInput = (MenuInput) newInput_p;
    }
  }

  /**
   * {@inheritDoc}
   */
  public Object[] getElements(Object inputElement_p) {
    Object input = inputElement_p;

    String parentId = null;

    ArrayList<Object> contexts = new ArrayList<Object>();

    if ((inputElement_p instanceof MenuInput)) {
      contexts.addAll(getChildCategories(null));
      contexts.addAll(getChildCommands(null));
    }
    if ((inputElement_p instanceof Command)) {
      parentId = ((Command) input).getId();
    }
    if ((inputElement_p instanceof Category)) {
      parentId = ((Category) input).getId();
      contexts.addAll(getChildCategories(parentId));
      contexts.addAll(getChildCommands(parentId));
    }

    return contexts.toArray();
  }

  protected boolean isFilteredCategory(Category category) {
    if (category.getId().startsWith("org.polarsys.capella.core.flexibility")) {
      return false;
    }
    return true;
  }

  protected boolean isFilteredCommand(Command command) {
    return false;
  }

  /**
   * @param object_p
   * @return
   */
  protected ArrayList<Object> getChildCommands(String parentId_p) {

    ArrayList<Object> contexts = new ArrayList<Object>();
    ICommandService cmdService = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);

    for (Command command : cmdService.getDefinedCommands()) {
      if (isFilteredCommand(command)) {
        continue;
      }
      try {
        Category category = command.getCategory();
        if ((category == null) && (parentId_p == null)) {
          contexts.add(command);
        } else if ((category != null) && category.getId().equals(parentId_p)) {
          contexts.add(command);
        }
      } catch (NotDefinedException exception_p) {
        if (parentId_p == null) {
          contexts.add(command);
        }
      }
    }

    return contexts;
  }

  /**
   * @param object_p
   * @return
   */
  protected ArrayList<Category> getChildCategories(String parentId_p) {

    ArrayList<Category> contexts = new ArrayList<Category>();
    ICommandService cmdService = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);

    for (Category category : cmdService.getDefinedCategories()) {
      boolean shouldAdd = false;

      if (parentId_p == null) {
        shouldAdd = true;
        for (Category category2 : cmdService.getDefinedCategories()) {
          if ((category.getId() != null) && !(category.getId().equals(category2.getId())) && (category.getId().startsWith(category2.getId()))) {
            shouldAdd = false;
            break;
          }
        }
      } else if (!(category.getId().equals(parentId_p)) && (category.getId().startsWith(parentId_p))) {
        shouldAdd = true;
      }

      if (shouldAdd) {
        if (!isFilteredCategory(category)) {
          contexts.add(category);
        }
      }
    }

    return contexts;
  }

  /**
   * {@inheritDoc}
   */
  public Object[] getChildren(Object parentElement_p) {
    return getElements(parentElement_p);
  }

  /**
   * {@inheritDoc}
   */
  public Object getParent(Object element_p) {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  public boolean hasChildren(Object element_p) {
    if (element_p == null) {
      return true;
    }
    if (element_p instanceof Category) {
      return true;
    }
    return false;
  }

  /**
   * @return
   */
  public Object[] getCheckedElements(Object root) {
    return getChildCheckedElements(root).toArray();
  }

  public Collection<Object> getChildCheckedElements(Object root) {
    ArrayList<Object> contexts = new ArrayList<Object>();
    boolean isChecked = true;

    for (Object object : getElements(root)) {

      Collection<Object> childs = getChildCheckedElements(object);
      if (!childs.contains(object)) {
        isChecked = false;
      }
      contexts.addAll(childs);
    }

    if (isChecked) {
      contexts.add(root);
    }
    return contexts;
  }

  /**
   * {@inheritDoc}
   */
  public boolean isChecked(Object element_p) {
    if (element_p instanceof Category) {
      for (Object child : getElements(element_p)) {
        if (isChecked(child)) {
          return true;
        }
      }
      return false;
    }

    if (element_p instanceof Command) {
      String id = ((Command) element_p).getId();

      return !PerspectiveOverrides.isHiddenMenu(id);
    }
    return false;
  }

  /**
   * {@inheritDoc}
   */
  public boolean isGrayed(Object element_p) {

    if (element_p instanceof Category) {
      boolean haveChildChecked = false;
      boolean haveChildUnchecked = false;
      for (Object child : getElements(element_p)) {
        if (isChecked(child)) {
          haveChildChecked = true;
        }
        if (!isChecked(child)) {
          haveChildUnchecked = true;
        }
      }
      return haveChildChecked && haveChildUnchecked;
    }

    return false;
  }

}
