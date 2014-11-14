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
  protected Collection _firstElement;
  /**
   * The second element put in the FastLinker (can be <code>null</code> if the FastLinker is empty or if it contains only one element).
   */
  protected Collection _secondElement;
  /**
   * The currently pinned element (can contain a reference to the first element, to the second element or can be <code>null</code> if no element is pinned).
   */
  protected Collection _pinnedElement;
  /**
   * Info on the created link (or <code>null</code> if no link is created).
   */
  protected LinkInfo _linkCreated;

  /**
   * Constructor. Initialize the state as empty.
   */
  protected FastLinkerState() {
    _firstElement = null;
    _secondElement = null;
    _pinnedElement = null;
    _linkCreated = null;
  }

  /**
   * Constructor. Initialize the state with given parameters.
   * @param firstElement_p
   * @param secondElement_p
   * @param pinnedElement_p
   */
  protected FastLinkerState(Collection firstElement_p,
			Collection secondElement_p, Collection pinnedElement_p) {
		_firstElement = firstElement_p;
		_secondElement = secondElement_p;
		if ((null == pinnedElement_p) || (firstElement_p == pinnedElement_p)
				|| (secondElement_p == pinnedElement_p)) {
			_pinnedElement = pinnedElement_p;
		}
		_linkCreated = null;
	}

  /**
   * Clear state. Set it as empty.
   */
  protected void clear() {
    _firstElement = null;
    _secondElement = null;
    _pinnedElement = null;
    _linkCreated = null;
  }

  /**
   * Get a preview of this state with the given element added. <code>this</code> state is not changed.
   * @param elementToPut_p
   * @return
   */
  protected FastLinkerState getPreviewState(Collection elementToPut_p) {
    FastLinkerState previewState = new FastLinkerState(_firstElement, _secondElement, _pinnedElement);
    return previewState.updateState(elementToPut_p);
  }

  /**
   * Update <code>this</code> state by adding given element.
   * @param elementToPut_p
   * @return
   */
  protected FastLinkerState updateState(Collection elementToPut_p) {
    if (null == _firstElement) {
      _firstElement = elementToPut_p;
    } else if (null == _secondElement) {
      _secondElement = elementToPut_p;
    } else {
      if (null == _pinnedElement) {
        _firstElement = elementToPut_p;
        _secondElement = null;
      } else if (_firstElement == _pinnedElement) {
        _secondElement = elementToPut_p;
      } else if (_secondElement == _pinnedElement) {
        _firstElement = elementToPut_p;
      }
    }
    _linkCreated = null;
    return this;
  }
  
  /**
   * * Update <code>this</code> state by adding given elements.
   * @param firstElement_p
   * @param secondElement_p
   * @param pinnedElement_p
   */
  protected FastLinkerState updateState(Collection firstElement_p,
			Collection secondElement_p, Collection pinnedElement_p) {
	  
	  this._firstElement = firstElement_p;
	  this._secondElement = secondElement_p;
	  this._pinnedElement = pinnedElement_p;
	  return this;
  }

  /**
   * Set link created info.
   * @param linkCreated_p
   */
  public void setLinkCreated(LinkInfo linkCreated_p) {
    _linkCreated = linkCreated_p;
  }

  /**
   * Get link created info.
   * @return
   */
  public LinkInfo getLinkCreated() {
    return _linkCreated;
  }

  /**
   * Get pinned element.
   * @return
   */
  public Collection getPinnedElement() {
    return _pinnedElement;
  }

  /**
   * Get first element.
   * @return
   */
  public Collection getFirstElement() {
    return _firstElement;
  }

  /**
   * Get second element.
   * @return
   */
  public Collection getSecondElement() {
    return _secondElement;
  }

  /**
   * Pin/unpin a model element.
   * @param elementToPin_p <ul>
   *          <li>If given element is <code>null</code>, eventually pinned element is unpinned,</li>
   *          <li>If given element is not pinned, it is pinned,</li>
   *          <li>If given element is pinned, it is unpinned,</li>
   *          <li>If given element is not present in FastLinker, nothing happens.
   *          </ul>
   */
 protected void pinModelElement(Collection elementToPin_p) {
		if ((null == elementToPin_p) || (_pinnedElement == elementToPin_p)
				|| elementToPin_p.isEmpty()) {
			_pinnedElement = null;
		} else if ((_firstElement == elementToPin_p)
				|| (_secondElement == elementToPin_p)) {
			_pinnedElement = elementToPin_p;
		}
	}
}
