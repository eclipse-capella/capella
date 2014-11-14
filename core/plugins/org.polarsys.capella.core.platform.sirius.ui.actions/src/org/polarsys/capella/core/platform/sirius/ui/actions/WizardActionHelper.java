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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;

public class WizardActionHelper {
  /**
   * Cover to EObject list
   * @param elements
   * @return
   */
  public static List<EObject> converToEObjectList(List<? extends EObject> elements) {
    List<EObject> result = new ArrayList<EObject>(0);
    for (Object object : elements) {
      result.add((EObject) object);
    }
    return result;
  }

  /**
   * return true if all he element in the list are of type FunctionalExchange, false otherwise
   * @param elements_p
   * @return
   */
  public static boolean areAllElementFunctionalExchange(List<EObject> elements_p) {
    boolean flag = false;
    for (EObject object : elements_p) {
      if (!(object instanceof FunctionalExchange)) {
        return false;
      } else if (object instanceof FunctionalExchange) {
        flag = true;
      }
    }

    return flag;
  }

  /**
   * return true if all he element in the list are of type FunctionalExchange, false otherwise
   * @param elements_p
   * @return
   */
  public static boolean areAllElementsComponentExchanges(List<EObject> elements_p) {
    boolean flag = false;
    for (EObject object : elements_p) {
      if (!(object instanceof ComponentExchange)) {
        return false;
      } else if (object instanceof ComponentExchange) {
        flag = true;
      }
    }

    return flag;
  }

  /**
   * @param selection_p
   * @return
   */
  public static boolean areAllElementsPhysicalLinks(List<EObject> elements_p) {
    boolean flag = false;
    for (EObject object : elements_p) {
      if (!(object instanceof PhysicalLink)) {
        return false;
      } else if (object instanceof PhysicalLink) {
        flag = true;
      }
    }

    return flag;
  }

  /**
   * 
   */
  public static void deleteCreatedCategory(CapellaElement cElt) {
    new CapellaDeleteCommand(MDEAdapterFactory.getExecutionManager(), Collections.singleton(cElt), true, false, false).execute();

  }

  /**
   * Create message box
   * @param message_p
   * @param icon_status
   */
  public static void createMessageBox(Shell shell_p, String message_p, int icon_status) {
    MessageBox messageBox = new MessageBox(shell_p, icon_status);
    messageBox.setText(Messages.AllocationManagementWizardAction_Title);
    messageBox.setMessage(message_p);
    messageBox.open();
  }
}
