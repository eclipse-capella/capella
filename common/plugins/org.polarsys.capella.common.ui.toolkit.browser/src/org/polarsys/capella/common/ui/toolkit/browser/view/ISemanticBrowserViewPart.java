/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
 * A semantic browser view part contains multiple viewers. The viewers have the same current element which is root
 * element. This interface gives an access to the root element.
 */
public interface ISemanticBrowserViewPart extends IViewPart {

  /**
   * Sets the input of the Semantic Browser and <b>ALWAYS</b> triggers a refresh of the associated queries.
   * 
   * 
   * @param input
   *          the input
   */
  void setInput(Object input);

  /**
   * Sets the input of the Semantic Browser and <b>CONDITIONALLY</b> triggers a refresh of the associated queries. The
   * refresh is triggered only if the view is listening to selection events. See
   * {@link ISemanticBrowserModel#isListeningToPageSelectionEvents()}
   * 
   * @param input
   */
  void saveInput(Object input);

  /**
   * Retrieve element root (current element to whole viewers contained by the viewpart)
   * 
   * @return
   */
  EObject getRootElement();

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

  public void setInputOnViewers(Object input);
}
