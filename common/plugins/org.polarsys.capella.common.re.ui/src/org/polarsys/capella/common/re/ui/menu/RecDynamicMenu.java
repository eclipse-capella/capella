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
import org.polarsys.capella.common.re.ui.queries.CatalogElementOrigin;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.ShowInDiagramAction;

public class RecDynamicMenu extends ContributionItem {
  public RecDynamicMenu() {
  }

  public RecDynamicMenu(String id) {
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
    Collection<CatalogElement> commonRecs = getCommonRecs(selection);

    for (CatalogElement commonRec : commonRecs) {
      EList<EObject> referencedElements = commonRec.getReferencedElements();
      IStructuredSelection newSelection = new StructuredSelection(referencedElements);
      if (!referencedElements.isEmpty()) {
        MenuItem menuItem = new MenuItem(menu, SWT.CHECK, index);
        menuItem.setText(commonRec.getName());
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

  public static Collection<CatalogElement> getCommonRecs(IStructuredSelection selection) {
    LinkedHashSet<EObject> selectedElements = new LinkedHashSet<EObject>();
    for (Object aSelectedElement : selection.toArray()) {
      if (aSelectedElement instanceof AbstractGraphicalEditPart) {
        EObject semantic = CapellaAdapterHelper.resolveBusinessObject(((AbstractGraphicalEditPart) aSelectedElement));
        selectedElements.add(semantic);
      } else {
        return new ArrayList<CatalogElement>();
      }
    }

    HashMap<EObject, Collection<CatalogElement>> mapOfRecs = new HashMap<EObject, Collection<CatalogElement>>();
    for (EObject anElement : selectedElements) {

      Collection<CatalogElement> replicablesElements = new CatalogElementOrigin().compute(anElement).stream()
          .map(CatalogElement.class::cast).collect(Collectors.toList());
      mapOfRecs.put(anElement, replicablesElements);
    }


    Iterator<Collection<CatalogElement>> recIterator = mapOfRecs.values().iterator();
    Set<CatalogElement> commonRecs = new HashSet<CatalogElement>(recIterator.next());
    while (recIterator.hasNext()) {
      commonRecs.retainAll(recIterator.next());
    }
    return commonRecs;
  }

  public static boolean hasCommonRecs(IStructuredSelection selection) {
    return !getCommonRecs(selection).isEmpty();
  }

}
