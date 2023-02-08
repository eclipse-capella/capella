/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.re.ui.menu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.ui.queries.ReferencingReplicas;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.ShowInDiagramAction;

public class RplDynamicMenu extends ContributionItem {
  public RplDynamicMenu() {
  }

  public RplDynamicMenu(String id) {
    super(id);
  }

  @Override
  public void fill(Menu menu, int index) {
    ISelectionService selectionService = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();
    ISelection theSelection = selectionService.getSelection();

    if (!(theSelection instanceof IStructuredSelection)) {
      return;
    }
    IStructuredSelection selection = (IStructuredSelection) theSelection;
    Collection<CatalogElement> commonRpls = getCommonRpls(selection);

    for (CatalogElement commonRpl : commonRpls) {
      EList<EObject> referencedElements = commonRpl.getReferencedElements();
      IStructuredSelection newSelection = new StructuredSelection(referencedElements);
      if (!referencedElements.isEmpty()) {
        MenuItem menuItem = new MenuItem(menu, SWT.CHECK, index);
        menuItem.setText(commonRpl.getName());
        menuItem.addSelectionListener(new SelectionListener() {
          @Override
          public void widgetSelected(SelectionEvent e) {
            ShowInDiagramAction action = new ShowInDiagramAction();
            action.selectionChanged(newSelection);
            action.run();
          }

          @Override
          public void widgetDefaultSelected(SelectionEvent e) {
          }
        });
      }
    }

  }

  public static Collection<CatalogElement> getCommonRpls(IStructuredSelection selection) {
    LinkedHashSet<EObject> selectedElements = new LinkedHashSet<EObject>();
    for (Object aSelectedElement : selection.toArray()) {
      if (aSelectedElement instanceof AbstractGraphicalEditPart) {
        EObject semantic = CapellaAdapterHelper.resolveBusinessObject(((AbstractGraphicalEditPart) aSelectedElement));
        selectedElements.add(semantic);
      } else {
        return new ArrayList<CatalogElement>();
      }
    }

    HashMap<EObject, Collection<CatalogElement>> mapOfRpls = new HashMap<EObject, Collection<CatalogElement>>();
    for (EObject anElement : selectedElements) {
      Collection<CatalogElement> replicas = new ReferencingReplicas().compute(anElement).stream()
          .map(CatalogElement.class::cast).collect(Collectors.toList());
      mapOfRpls.put(anElement, replicas);
    }

    Iterator<Collection<CatalogElement>> rplIterator = mapOfRpls.values().iterator();
    Set<CatalogElement> commonRpls = new HashSet<CatalogElement>(rplIterator.next());
    while (rplIterator.hasNext()) {
      commonRpls.retainAll(rplIterator.next());
    }
    return commonRpls;
  }

  public static boolean hasCommonRpls(IStructuredSelection selection) {
    return !getCommonRpls(selection).isEmpty();
  }

}
