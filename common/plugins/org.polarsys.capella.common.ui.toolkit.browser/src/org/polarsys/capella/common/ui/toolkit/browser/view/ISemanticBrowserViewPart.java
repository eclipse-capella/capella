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
   * Refreshes the content of the semantic browser. The refresh is only performed if the semantic browser input changed
   * in regards to the last triggered refresh. See {@link ISemanticBrowserViewPart#refresh(boolean)} in order to
   * <b>ALWAYS</b> force a refresh.
   */
  void refresh();

  /**
   * Refreshes the content of the semantic browser. If true then the refresh is always performed, if false the refresh
   * is only performed if the semantic browser input changed in regards to the last triggered refresh. This method
   * should only be used if a force refresh is required, for all the other cases use the {#link
   * {@link ISemanticBrowserViewPart#refresh()} which does not force the refresh.
   */
  void refresh(boolean forceRefresh);

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
