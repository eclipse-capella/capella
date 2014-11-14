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
package org.polarsys.capella.core.flexibility.commands.dynamic.tools.testgen;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.widgets.Shell;

import org.polarsys.capella.core.flexibility.commands.dynamic.DefaultCommandHandler;
import org.polarsys.capella.core.flexibility.commands.dynamic.tools.DefaultCategories;
import org.polarsys.capella.core.flexibility.commands.dynamic.tools.annotations.Command;
import org.polarsys.capella.core.flexibility.commands.dynamic.tools.annotations.CommandImage;
import org.polarsys.capella.core.flexibility.commands.helpers.EObjectHelper;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

@CommandImage("process.gif")
@Command(name = "Retrieve static ID", description = "Retrieve static ID")
public class RetrieveIDsConstants extends DefaultCommandHandler {

  public RetrieveIDsConstants(Shell shell_p, ISelectionProvider selectionProvider_p) {
    super(shell_p, selectionProvider_p);
  }

  @Override
  public boolean isSelectionCompatible() {
    return getSelection(ModelElement.class).size() > 0;
  }

  @Override
  public String getCategory() {
    return DefaultCategories.TEST_CATEGORY;
  }

  @Override
  public void execute() {
    for (EObject object : getSelectedEObjects()) {
      setName(object, "");
    }
  }

  /**
   * @param object_p
   * @param string_p
   */
  private void setName(EObject object_p, String string_p) {
    System.out.println("public static String " + EObjectHelper.getInstance().getID2(object_p).toUpperCase() + " = \""
                       + EObjectHelper.getInstance().getIDValue(object_p) + "\";  //$NON-NLS-1$ ");
  }
}
