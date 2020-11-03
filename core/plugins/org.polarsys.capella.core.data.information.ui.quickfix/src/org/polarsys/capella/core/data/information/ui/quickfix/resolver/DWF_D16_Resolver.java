/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.information.ui.quickfix.resolver;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.polarsys.capella.common.mdsofa.common.misc.Couple;
import org.polarsys.capella.common.ui.toolkit.viewers.AbstractContextMenuFiller;
import org.polarsys.capella.common.utils.graph.IDirectedGraph;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.ui.quickfix.messages.InformationQuickFixMessages;
import org.polarsys.capella.core.data.information.validation.class_.MDCHK_DWF_D16;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.LocateInCapellaExplorerAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.Messages;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;
import org.polarsys.capella.core.validation.ui.ide.quickfix.EObjectNavigatorDialog;

/**
 */
public class DWF_D16_Resolver extends AbstractCapellaMarkerResolution {

  /**
   * {@inheritDoc}
   */
  public void run(IMarker marker) {

    DataPkg pkg = (DataPkg) getModelElements(marker).get(0);

    MDCHK_DWF_D16 validator = new MDCHK_DWF_D16();
    Couple<IDirectedGraph<EObject>, List<List<EObject>>> result = validator.findSCC(pkg);

    final IDirectedGraph<EObject> graph = result.getKey();
    List<List<EObject>> sccs = result.getValue();

    if ((sccs == null) || sccs.isEmpty()) {
      // user may have fixed the cycles in the meantime...
      return;
    }

    EObjectNavigatorDialog dialog =
        new EObjectNavigatorDialog(sccs.iterator().next(), InformationQuickFixMessages.cycle_details_dialog_title,
            InformationQuickFixMessages.cycle_details_dialog_message, InformationQuickFixMessages.cycle_details_dialog_combo_lbl,
            InformationQuickFixMessages.cycle_details_dialog_combo_cycle_prefix);
 
    dialog.setCycles(sccs);

    dialog.setContextMenuManagerFiller(new AbstractContextMenuFiller() {
     
      @SuppressWarnings("synthetic-access")
      @Override
      public void fillMenuManager(IMenuManager contextMenuManager, final ISelection selection) {
        //
        // dependencies (whether it exists)
        //
        final EObject eObject = (EObject) ((TreeSelection) selection).iterator().next();

        if (InformationPackage.Literals.CLASS.isSuperTypeOf(eObject.eClass())) {

          Set<EObject> successors = new HashSet<EObject>();
          for (Iterator<EObject> it = graph.getSucessors(eObject); it.hasNext();) {
            EObject referenced = it.next();
            successors.add(referenced);
          }

          if (!successors.isEmpty()) {
            for (final EObject referenced : successors) {
              IAction action = LocateInCapellaExplorerAction.createLocateTowards(referenced, Messages.LocateInCapellaExplorerAction_GoToReferencedElement, false);
              if (action.isEnabled()) {
                contextMenuManager.add(action);
              }
            }
          }
        }
      }
    });

    dialog.open();
  }
  
  @Override
	protected String[] getResolvableRuleIds() {
		return noRuleIds;
	}

}
