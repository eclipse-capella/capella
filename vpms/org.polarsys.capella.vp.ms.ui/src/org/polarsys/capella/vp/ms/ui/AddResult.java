/*******************************************************************************
 * Copyright (c) 2017 ALTRAN.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Altran - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.vp.ms.ui;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.polarsys.capella.vp.ms.MsFactory;
import org.polarsys.capella.vp.ms.Result;
import org.polarsys.capella.vp.ms.Situation;
import org.polarsys.kitalpha.emde.model.ExtensibleElement;

public class AddResult implements IObjectActionDelegate {

  private Situation destinationSituation;

  @Override
  public void run(IAction action) {
    final EObject iObj = this.destinationSituation.eContainer();
    final Result newResult = MsFactory.eINSTANCE.createResult();
    newResult.setName("Result " + this.destinationSituation.getName());
    newResult.getSituation().add(destinationSituation);

    TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(iObj);

    RecordingCommand myCommand = new RecordingCommand(domain) {

      @Override
      protected void doExecute() {
        try {
          ((ExtensibleElement) iObj).getOwnedExtensions().add(newResult);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    };

    domain.getCommandStack().execute(myCommand);

  }

  @Override
  public void selectionChanged(IAction action, ISelection selection) {
    // TODO Auto-generated method stub
    if (selection instanceof IStructuredSelection) {
      IStructuredSelection ss = (IStructuredSelection) selection;
      for (Object obj : ss.toList()) {
        if (obj instanceof Situation) {
          destinationSituation = (Situation) obj;
        }
      }

    }
  }

  @Override
  public void setActivePart(IAction action, IWorkbenchPart targetPart) {

  }

}
