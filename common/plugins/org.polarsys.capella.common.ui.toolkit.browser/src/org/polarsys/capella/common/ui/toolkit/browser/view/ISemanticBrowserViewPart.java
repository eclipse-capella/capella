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
package org.polarsys.capella.common.ui.toolkit.browser.view;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.IViewPart;
import org.polarsys.capella.common.ui.toolkit.browser.action.BrowserHistory;
import org.polarsys.capella.common.ui.toolkit.browser.model.ISemanticBrowserModel;

/**
 * A semantic browser view part contains multiple viewers.
 * The viewers have the same current element which is root element.
 * This interface gives an access to the root element.
 */
public interface ISemanticBrowserViewPart extends IViewPart {
  
  /**
   * Launch an action contextual to the given element.
   * Like : setting the input of every viewers referenced by the viewpart.
   * @param input_p
   */
  void setInput(Object input_p);
  
  /**
   * Retrieve element root (current element to whole viewers contained by the viewpart)
   * @return
   */
  EObject getRootElement ();
  
  /**
   * Refresh content of the viewpart.
   */
  void refresh();
  
  /**
   * Clean the content of the viewpart.
   */
  void clean();
  
  /**
   * @return the referencingViewer
   */
  public TreeViewer getReferencingViewer();

  /**
   * @return the currentViewer
   */
  public TreeViewer getCurrentViewer();

  /**
   * @return the referencedViewer
   */
  public TreeViewer getReferencedViewer();

  /**
   * @return
   */
  public BrowserHistory getHistory();
  
  public ISemanticBrowserModel getModel();
  
  public void setInputOnViewers(Object input_p);
}
