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

package org.polarsys.capella.core.platform.sirius.ui.actions;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.model.copypaste.SharedCutPasteClipboard;
import org.polarsys.capella.core.model.helpers.move.MoveHelper;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaPasteCommand;
import org.polarsys.capella.core.ui.toolkit.AbstractCommandActionHandler;

/**
 * The action allowing to paste Capella elements. it generates a new identifier each time the action is called.
 */
public class CapellaPasteAction extends AbstractCommandActionHandler {
  /**
   * Constructs the Capella action allowing to paste Capella elements.
   * @param domain_p The editing domain.
   */
  public CapellaPasteAction() {
    super(EMFEditUIPlugin.INSTANCE.getString("_UI_Paste_menu_item"));
  }

  /**
   * @see org.eclipse.emf.edit.ui.action.PasteAction#createCommand(java.util.Collection)
   */
  @Override
  public Command createCommand(Collection<Object> selection_p) {
    if (1 == selection_p.size()) {
      return new CapellaPasteCommand(TransactionHelper.getEditingDomain(filterSelection(selection_p)), getStructuredSelection().iterator().next(), null,
          CommandParameter.NO_INDEX);
    }
    return UnexecutableCommand.INSTANCE;
  }

  /**
   * Depending on use cases we have to override this methods to call canDelete.
   * @see org.eclipse.emf.edit.ui.action.CommandActionHandler#updateSelection(org.eclipse.jface.viewers.IStructuredSelection)
   */
  @SuppressWarnings("unchecked")
  @Override
  public boolean updateSelection(IStructuredSelection selection_p) {
    if (!selection_p.isEmpty()) {
      Object obj = selection_p.iterator().next();
      if (obj instanceof EObject) {
        Collection<?> clipboard = null;
        SharedCutPasteClipboard cutClipboard = SharedCutPasteClipboard.getCutClipboard();
        if ((null != cutClipboard) && cutClipboard.hasCut()) {
          clipboard = cutClipboard.getClipboard();
        } else {
          EditingDomain domain = TransactionHelper.getEditingDomain((EObject) obj);
          if (domain != null) {
            clipboard = domain.getClipboard();
          }
        }

        if ((clipboard != null) && !clipboard.isEmpty() && !checkSemanticRules((List<EObject>) clipboard, (EObject) obj)) {
          return false;
        }
      }
    }
    return super.updateSelection(selection_p);
  }

  /**
   * @param selectedModelElements_p
   * @param targetElement_p
   * @return
   * Moved. use instead MoveHelper.checkSemanticRules
   */
  @Deprecated
  public static boolean checkSemanticRules(List<EObject> selectedElements_p, EObject targetElement_p) {
    return MoveHelper.getInstance().checkSemanticRules(selectedElements_p, targetElement_p).isOK();
  }

}
