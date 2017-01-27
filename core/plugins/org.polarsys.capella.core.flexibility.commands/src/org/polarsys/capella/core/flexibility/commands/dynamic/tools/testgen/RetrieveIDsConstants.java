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
package org.polarsys.capella.core.flexibility.commands.dynamic.tools.testgen;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.widgets.Shell;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.flexibility.commands.dynamic.DefaultCommandHandler;
import org.polarsys.capella.core.flexibility.commands.dynamic.tools.DefaultCategories;
import org.polarsys.capella.core.flexibility.commands.dynamic.tools.annotations.Command;
import org.polarsys.capella.core.flexibility.commands.dynamic.tools.annotations.CommandImage;
import org.polarsys.capella.core.flexibility.commands.helpers.EObjectHelper;

@CommandImage("process.gif")
@Command(name = "Retrieve static ID", description = "Retrieve static ID")
public class RetrieveIDsConstants extends DefaultCommandHandler {

  public RetrieveIDsConstants(Shell shell, ISelectionProvider selectionProvider) {
    super(shell, selectionProvider);
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
   * @param object
   * @param string
   */
  private void setName(EObject object, String string) {
    System.out.println("public static String " + EObjectHelper.getInstance().getQualifiedIdentifier(object).toUpperCase() + " = \""
                       + EObjectHelper.getInstance().getIDValue(object) + "\";  //$NON-NLS-1$ ");
  }
}
