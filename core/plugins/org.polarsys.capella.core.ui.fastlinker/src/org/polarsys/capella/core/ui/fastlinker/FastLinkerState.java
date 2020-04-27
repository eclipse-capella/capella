/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.fastlinker;

import java.util.Collection;

import org.polarsys.capella.core.model.links.helpers.LinkInfo;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 * This class manage the FastLinker state.
 */
public class FastLinkerState {
  /**
   * The first element put in the FastLinker (can be <code>null</code> if the FastLinker is empty).
   */
  protected Collection firstElement;
  /**
   * The second element put in the FastLinker (can be <code>null</code> if the FastLinker is empty or if it contains only one element).
   */
  protected Collection secondElement;
  /**
   * The currently pinned element (can contain a reference to the first element, to the second element or can be <code>null</code> if no element is pinned).
   */
  protected Collection pinnedElement;
  /**
   * Info on the created link (or <code>null</code> if no link is created).
   */
  protected LinkInfo linkCreated;

  /**
   * Constructor. Initialize the state as empty.
   */
  protected FastLinkerState() {
    firstElement = null;
    secondElement = null;
    pinnedElement = null;
    linkCreated = null;
  }

  /**
   * Constructor. Initialize the state with given parameters.
   * @param firstElement
   * @param secondElement
   * @param pinnedElement
   */
  protected FastLinkerState(Collection firstElement, Collection secondElement, Collection pinnedElement) {
		this.firstElement = firstElement;
		this.secondElement = secondElement;
		if ((null == pinnedElement) || (firstElement == pinnedElement) || (secondElement == pinnedElement)) {
		  this.pinnedElement = pinnedElement;
		}
		this.linkCreated = null;
	}

  /**
   * Clear state. Set it as empty.
   */
  protected void clear() {
    firstElement = null;
    secondElement = null;
    pinnedElement = null;
    linkCreated = null;
  }

  /**
   * Get a preview of this state with the given element added. <code>this</code> state is not changed.
   * @param elementToPut
   * @return
   */
  protected FastLinkerState getPreviewState(Collection elementToPut) {
    FastLinkerState previewState = new FastLinkerState(firstElement, secondElement, pinnedElement);
    return previewState.updateState(elementToPut);
  }

  /**
   * Update <code>this</code> state by adding given element.
   * @param elementToPut
   * @return
   */
  protected FastLinkerState updateState(Collection elementToPut) {
    if (null == firstElement) {
      firstElement = elementToPut;
    } else if (null == secondElement) {
      secondElement = elementToPut;
    } else {
      if (null == pinnedElement) {
        firstElement = elementToPut;
        secondElement = null;
      } else if (firstElement == pinnedElement) {
        secondElement = elementToPut;
      } else if (secondElement == pinnedElement) {
        firstElement = elementToPut;
      }
    }
    linkCreated = null;
    return this;
  }
  
  /**
   * * Update <code>this</code> state by adding given elements.
   * @param firstElement
   * @param secondElement
   * @param pinnedElement
   */
  protected FastLinkerState updateState(Collection firstElement,
			Collection secondElement, Collection pinnedElement) {
	  
	  this.firstElement = firstElement;
	  this.secondElement = secondElement;
	  this.pinnedElement = pinnedElement;
	  return this;
  }

  /**
   * Set link created info.
   * @param linkCreated
   */
  public void setLinkCreated(LinkInfo linkCreated) {
    this.linkCreated = linkCreated;
  }

  /**
   * Get link created info.
   * @return
   */
  public LinkInfo getLinkCreated() {
    return linkCreated;
  }

  /**
   * Get pinned element.
   * @return
   */
  public Collection getPinnedElement() {
    return pinnedElement;
  }

  /**
   * Get first element.
   * @return
   */
  public Collection getFirstElement() {
    return firstElement;
  }

  /**
   * Get second element.
   * @return
   */
  public Collection getSecondElement() {
    return secondElement;
  }

  /**
   * Pin/unpin a model element.
   * @param elementToPin <ul>
   *          <li>If given element is <code>null</code>, eventually pinned element is unpinned,</li>
   *          <li>If given element is not pinned, it is pinned,</li>
   *          <li>If given element is pinned, it is unpinned,</li>
   *          <li>If given element is not present in FastLinker, nothing happens.
   *          </ul>
   */
 protected void pinModelElement(Collection elementToPin) {
		if ((null == elementToPin) || (pinnedElement == elementToPin)
				|| elementToPin.isEmpty()) {
			pinnedElement = null;
		} else if ((firstElement == elementToPin)
				|| (secondElement == elementToPin)) {
			pinnedElement = elementToPin;
		}
	}
}
