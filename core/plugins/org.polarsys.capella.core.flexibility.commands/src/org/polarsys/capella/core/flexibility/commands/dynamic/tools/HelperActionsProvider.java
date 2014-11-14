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
package org.polarsys.capella.core.flexibility.commands.dynamic.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.widgets.Shell;

import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.flexibility.commands.actions.DefaultAction;
import org.polarsys.capella.core.flexibility.commands.dynamic.IActionsProvider;

/**
 */
public class HelperActionsProvider implements IActionsProvider {

  /**
   * @see org.polarsys.capella.core.flexibility.commands.dynamic.IActionsProvider#getActions()
   */
  public Collection<DefaultAction> getActions(Shell shell_p, ISelectionProvider selectionProvider_p) {
    List<DefaultAction> list = new ArrayList<DefaultAction>();

    list.add(new FunctionalChainInformations(shell_p, selectionProvider_p));
    list.add(new FunctionalChainInvolvementInformations(shell_p, selectionProvider_p));

    return list;
  }

  @SuppressWarnings("nls")
  public class FunctionalChainInvolvementInformations extends DefaultAction {

    String SEP = "  ";

    public FunctionalChainInvolvementInformations(Shell shell_p, ISelectionProvider selectionProvider_p) {
      super(shell_p, selectionProvider_p);
    }

    @Override
    public boolean isSelectionCompatible() {
      return getSelection(FunctionalChainInvolvement.class).size() > 0;
    }

    @Override
    protected String getIconFile() {
      return "process.gif";
    }

    @Override
    public String getText() {
      return "FunctionalChainInvolvementInformations";
    }

    @Override
    public String getCategory() {
      return "org.polarsys.capella.core.flexibility.tools.access";
    }

    @Override
    public void execute() {
      for (EObject object : getEObjectContents()) {
        if (object instanceof FunctionalChainInvolvement) {
          FunctionalChainInvolvement chain = (FunctionalChainInvolvement) object;
        }
      }
    }

    /**
     * @param chain_p
     * @param flatFirstFunctionalChainInvolvments_p
     */
    private Collection<Object> getCollection(Object a, Object b) {
      Collection<Object> list = new LinkedList<Object>();
      if (a instanceof Collection) {
        list.addAll((Collection) a);
      } else {
        list.add(a);
      }
      if (b instanceof Collection) {
        list.addAll((Collection) b);
      } else {
        list.add(a);

      }
      return list;
    }
  }

  @SuppressWarnings("nls")
  public class FunctionalChainInformations extends DefaultAction {

    String SEP = "  ";

    public FunctionalChainInformations(Shell shell_p, ISelectionProvider selectionProvider_p) {
      super(shell_p, selectionProvider_p);
    }

    @Override
    public boolean isSelectionCompatible() {
      return getSelection(FunctionalChain.class).size() > 0;
    }

    @Override
    protected String getIconFile() {
      return "process.gif";
    }

    @Override
    public String getText() {
      return "FunctionalChainInformations";
    }

    @Override
    public String getCategory() {
      return "org.polarsys.capella.core.flexibility.tools.access";
    }

    @Override
    public void execute() {
      for (EObject object : getEObjectContents()) {
        if (object instanceof FunctionalChain) {
          FunctionalChain chain = (FunctionalChain) object;
        }
      }
    }

    /**
     * @param chain_p
     * @param flatFirstFunctionalChainInvolvments_p
     */
    private Collection<Object> getCollection(Object a, Object b) {
      Collection<Object> list = new LinkedList<Object>();
      if (a instanceof Collection) {
        list.addAll((Collection) a);
      } else {
        list.add(a);
      }
      if (b instanceof Collection) {
        list.addAll((Collection) b);
      } else {
        list.add(b);

      }
      return list;
    }
  }

}
