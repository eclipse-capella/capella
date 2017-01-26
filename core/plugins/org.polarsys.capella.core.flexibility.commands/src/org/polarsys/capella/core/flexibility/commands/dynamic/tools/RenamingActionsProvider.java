/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.flexibility.commands.dynamic.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.widgets.Shell;

import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.flexibility.commands.actions.DefaultAction;
import org.polarsys.capella.core.flexibility.commands.dynamic.IActionsProvider;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 */
public class RenamingActionsProvider implements IActionsProvider {

  /**
   * @see org.polarsys.capella.core.flexibility.commands.dynamic.IActionsProvider#getActions()
   */
  public Collection<DefaultAction> getActions(Shell shell, ISelectionProvider selectionProvider) {
    List<DefaultAction> list = new ArrayList<DefaultAction>();

    list.add(new SetNames(shell, selectionProvider));

    return list;
  }

  @SuppressWarnings("nls")
  public class SetNames extends DefaultAction {

    public SetNames(Shell shell, ISelectionProvider selectionProvider) {
      super(shell, selectionProvider);
    }

    @Override
    public boolean isSelectionCompatible() {
      return getSelection(ModelElement.class).size() > 0;
    }

    @Override
    protected String getIconFile() {
      return "process.gif";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
      return "This action renames all owned elements with short name.\nFor instance, a PhysicalComponent will be named PC x, with x, index of the element";
    }

    @Override
    public String getText() {
      return "Set short names";
    }

    @Override
    public String getCategory() {
      return DefaultCategories.RENAME_ELEMENT_CATEGORY;
    }

    @Override
    public void execute() {

      for (EObject object : getSelectedEObjects()) {
        setName(object, "");
      }

    }

    public void setName(EObject object, String suffix) {
      for (EObject obj : object.eContents()) {
        if (obj instanceof AbstractNamedElement) {
          String name = getPrefix(obj) + suffix + getPosition(obj);
          ((AbstractNamedElement) obj).setName(name);
          setName(obj, suffix + getPosition(obj));
        }
      }
    }

    /**
     * @param object
     * @return
     */
    private int getPosition(EObject object) {
      int i = 1;
      if (object.eContainmentFeature().isMany()) {
        for (Object o : (EList) object.eContainer().eGet(object.eContainmentFeature())) {
          if (!o.equals(object)) {
            i++;
          } else {
            break;
          }
        }
      }
      return i;
    }

    public String getPrefix(EObject object) {
      String res = "";
      EClass clazz = object.eClass();
      Pattern p = Pattern.compile("[a-z]");
      String[] result = p.split(clazz.getName());
      for (String element : result) {
        res += element;
      }
      if (object instanceof Part) {
        res = "part_" + res;
      }
      return res;
    }
  }

}
