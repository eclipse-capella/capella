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
package org.polarsys.capella.common.tools.report.appenders.reportlogview.handler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;

import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewPlugin;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.Messages;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.actions.SelectElementAction;


/**
 */
public class GotoHandler extends AbstractDynamicContributionItem {

  private final String menuImage = "capella_16.png"; //$NON-NLS-1$
  private final String menuId    = "org.polarsys.capella.common.tools.report.appenders.reportlogview.logview.goto"; //$NON-NLS-1$

  
  /**
   * @see org.eclipse.ui.actions.CompoundContributionItem#getContributionItems()
   */
  @Override
  public IContributionItem[] getContributionItems() {
    
    IMarker marker = getCurrentMarker();

    if (marker != null) {
      
      List<EObject> map = new ArrayList<EObject>(MarkerViewHelper.getModelElementsFromMarker(marker));
      
      // don't show any metamodel elements (can happen for markers of type org.eclipse.emf.ecore.1, where the second element is the feature for which multiplicity is violated)
      for (Iterator<EObject> it = map.iterator(); it.hasNext();){
        EObject next = it.next();
        if (next instanceof EModelElement){
          it.remove();
        }
      }
      
      IContributionItem[] items = new IContributionItem[map.size() <= 1 ? map.size() : map.size() + 1];

      int iResult = 0;
      if (map.size() > 0) {
        items[iResult++] = createContributionItem(map.get(0));
      }
      if (map.size() > 1) {
        items[iResult++] = new Separator();
        for (int i = 1; i < map.size(); i++) {
          items[iResult++] = createContributionItem(map.get(i));
        }
      }
      
      ImageDescriptor image = MarkerViewPlugin.getDefault().getImageDescriptor(menuImage);
      
      MenuManager manager = new MenuManager(Messages.MarkerView_Goto_Command_Name, image, menuId);  
      for (IContributionItem item : items){
        manager.add(item);
      }
      
      return new IContributionItem[] { manager };
    }

    return NO_CONTRIBUTION_ITEM;
  }

  /**
   * @param eObject_p
   * @return
   */
  protected IContributionItem createContributionItem(EObject eObject_p) {
    return new ActionContributionItem(new SelectElementAction(eObject_p));
  }

  @Override
  protected boolean hasContributionItems() {
    return getContributionItems().length > 0;
  }
}
