/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.fa.ui.quickfix.resolver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.fa.ui.quickfix.generator.GenerateInterfacesResolutionGenerator;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.model.helpers.AllocateExchangeItemCommand;
import org.polarsys.capella.core.ui.toolkit.helpers.SelectionDialogHelper;

public class GenerateInterfacesAllocateResolver extends GenerateInterfacesResolver {

  @Override
  protected boolean run(ExchangeItem exchangeItem, Interface iface, List<EObject> exchanges, int statusCode,
      IMarker marker) {

    List<EObject> selected = null;

    if (statusCode == GenerateInterfacesResolutionGenerator.MISSING_EI_ON_INTERFACE) {
      selected = Collections.<EObject> singletonList(iface);
    } else if (statusCode == GenerateInterfacesResolutionGenerator.UNKNOWN_EI_ON_INTERFACE) {
      // the exchange item is on the interface, but not on any related exchange.
      // open a dialog to have user select exchanges for allocation
      Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
      selected = SelectionDialogHelper.openOrderedTransferDialog(exchanges, new ArrayList<EObject>(), shell,
          Messages.GenerateInterfacesAllocateResolver_dialogLabel,
          NLS.bind(Messages.GenerateInterfacesAllocateResolver_dialogText,
              EObjectLabelProviderHelper.getText(exchangeItem)));
    }

    if (selected != null && selected.size() > 0) {
      TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(selected.get(0));
      domain.getCommandStack().execute(new AllocateExchangeItemCommand(exchangeItem, selected));
      return true;
    }

    return false;
  }

}
