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
import org.polarsys.capella.vp.ms.CSConfiguration;
import org.polarsys.capella.vp.ms.Comparison;
import org.polarsys.capella.vp.ms.MsFactory;
import org.polarsys.capella.vp.ms.Situation;
import org.polarsys.kitalpha.emde.model.impl.ExtensibleElementImpl;

public class AddComparison implements IObjectActionDelegate {

  private CSConfiguration destinationConfiguration1;
  private CSConfiguration destinationConfiguration2;
  private Situation destinationSituation;

  @Override
  public void run(IAction action) {

    final EObject iObj;
    if (destinationSituation == null) {
      iObj = this.destinationConfiguration2.eContainer();
    } else {
      iObj = this.destinationSituation.eContainer();
    }
    final Comparison newComparison = MsFactory.eINSTANCE.createComparison();

    if (destinationSituation != null) {
      newComparison.setName(
          "Compare " + this.destinationConfiguration1.getName() + " with " + this.destinationSituation.getName());
      newComparison.getSituation().add(destinationSituation);
    } else {
      newComparison.setName(
          "Compare " + this.destinationConfiguration1.getName() + " with " + this.destinationConfiguration2.getName());
      newComparison.getConfiguration2().add(destinationConfiguration2);
    }

    newComparison.getConfiguration1().add(destinationConfiguration1);

    TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(iObj);
    RecordingCommand myCommand = new RecordingCommand(domain) {
      @Override
      protected void doExecute() {
        try {
          ((ExtensibleElementImpl) iObj).getOwnedExtensions().add(newComparison);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    };

    TransactionalEditingDomain domain2 = TransactionUtil.getEditingDomain(destinationConfiguration1);
    RecordingCommand myCommand2 = new RecordingCommand(domain2) {

      @Override
      protected void doExecute() {
        try {
          if (destinationSituation != null) {
            destinationConfiguration1.getContext().add(destinationSituation);
          } else {
            destinationConfiguration1.getCompareTo().add(destinationConfiguration2);
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    };

    domain.getCommandStack().execute(myCommand);
    domain2.getCommandStack().execute(myCommand2);
    // situationContainer.getOwnedExtensions().add(newComparison);

    /*
     * else if(iObj instanceof PhysicalComponent){ PhysicalComponent situationContainer = (PhysicalComponent)iObj;
     * situationContainer.getOwnedExtensions().add(newComparison); } else if(iObj instanceof SystemImpl){ SystemImpl
     * situationContainer = (SystemImpl)iObj; situationContainer.getOwnedExtensions().add(newComparison); }
     */

  }

  @Override
  public void selectionChanged(IAction action, ISelection selection) {
    destinationConfiguration1 = null;
    destinationSituation = null;
    destinationConfiguration2 = null;
    if (selection instanceof IStructuredSelection) {
      IStructuredSelection ss = (IStructuredSelection) selection;
      for (Object obj : ss.toList()) {
        if (obj instanceof CSConfiguration && destinationConfiguration1 == null) {
          destinationConfiguration1 = (CSConfiguration) obj;
        } else if (obj instanceof Situation) {
          destinationSituation = (Situation) obj;
        } else if (obj instanceof CSConfiguration) {
          destinationConfiguration2 = (CSConfiguration) obj;
        }

      }

    }
  }

  @Override
  public void setActivePart(IAction action, IWorkbenchPart targetPart) {

  }

}
