/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.ui.toolkit.browser.action;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

/**
 * History services for browser.
 */
public class BrowserHistory {
  /**
   * Browser navigation history entry.
   */
  public class BrowserNavigationHistoryEntry {
    /**
     * Weak reference on a model element object.
     */
    private WeakReference<Object> _referencedObject;

    /**
     * Flag to explicitly mark the entry invalid
     */
    private boolean valid = true;
    
    /**
     * Constructor.
     * @param realObject The underlying object to navigate to.
     */
    protected BrowserNavigationHistoryEntry(Object realObject) {
      _referencedObject = new WeakReference<Object>(realObject);
    }

    /**
     * Get the underlying object to navigate to.
     * @return can be <code>null</code> if object is unloaded.
     */
    public Object getRealObject() {
      return _referencedObject.get();
    }

    /**
     * A history entry is invalid if it is either not referencing any object, if
     * it references an EObject which is a proxy, or if the entry was
     * explicitly marked invalid via {@link #invalidate()}.
     * 
     * @return whether the entry is valid or not
     */
    public boolean isValid() {
      boolean result = valid;
      if (result) {
        Object realObject = getRealObject();
        if ((null == realObject) || ((realObject instanceof EObject) && ((EObject) realObject).eIsProxy())) {
          result = false;
        }
      }
      return result;
    }
    
    /**
     * Explicitly marks this entry as invalid so that it will be removed during the next
     * {@link BrowserHistory#update(Object) update}.
     */
    public void invalidate(){
      valid = false;
    }
  }

  private static final int MAX_SIZE = 10;

  /**
   * Actions to notify when the history is updated.
   */
  private List<SemanticBrowserHistoryAction> _actionAsListeners;

  /**
   * Current index. When its Zero that means there is no element in history.
   */
  private int _currentEntryIndex;
  /**
   * Internal navigation history.
   */
  private List<BrowserNavigationHistoryEntry> _entries;

  /**
   * do update when update doesn't come from an update.
   */
  private boolean _shouldDoUpdate;

  /**
   * Constructor.
   */
  public BrowserHistory() {
    _currentEntryIndex = -1;
    _shouldDoUpdate = true;
    _entries = new ArrayList<BrowserNavigationHistoryEntry>(MAX_SIZE);
    _actionAsListeners = new ArrayList<SemanticBrowserHistoryAction>(2);
  }

  /**
   * Add specified action as browser history listener.
   * @param action
   */
  public void addActionAsListener(SemanticBrowserHistoryAction action) {
    _actionAsListeners.add(action);
  }

  /**
   * Returns a read-only collection view over all navigation entries in the history
   */
  public Collection<BrowserNavigationHistoryEntry> getAllNavigationEntries(){
    return Collections.unmodifiableCollection(_entries);
  }

  /**
   * Clean invalid entries i.e entries that reference objects which are no longer loaded.
   */
  private void cleanInvalidEntries() {
    Iterator<BrowserNavigationHistoryEntry> iterator = _entries.iterator();
    int i = 0;
    while (iterator.hasNext()) {
      BrowserHistory.BrowserNavigationHistoryEntry historyEntry = iterator.next();
      if (!historyEntry.isValid()) {
        iterator.remove();
        // Shift back the current entry index.
        if (_currentEntryIndex == i) {
          _currentEntryIndex--;
        }
      }
      i++;
    }
    // Handle out of bounds.
    if (_currentEntryIndex >= _entries.size()) {
      _currentEntryIndex = _entries.size() - 1;
    } else if (_currentEntryIndex < 0) {
      _currentEntryIndex = 0;
    }
  }

  /**
   * Dispose internal entries.
   */
  public void dispose() {
    if (null != _entries) {
      _entries.clear();
      _entries = null;
    }
    if (null != _actionAsListeners) {
      _actionAsListeners.clear();
      _actionAsListeners = null;
    }
    _currentEntryIndex = -1;
  }

  /**
   * Get backward navigation entries.
   * @return
   */
  public List<BrowserNavigationHistoryEntry> getBackwardNavigationEntries() {
    List<BrowserNavigationHistoryEntry> result = Collections.emptyList();
    if (!_entries.isEmpty()) {
      result = new ArrayList<BrowserNavigationHistoryEntry>(_entries.subList(0, _currentEntryIndex));
    }
    return result;
  }

  /**
   * Get forward navigation entries.
   * @return
   */
  public List<BrowserNavigationHistoryEntry> getForwardNavigationEntries() {
    List<BrowserNavigationHistoryEntry> result = Collections.emptyList();
    if (_currentEntryIndex < (_entries.size() - 1)) {
      result = new ArrayList<BrowserNavigationHistoryEntry>(_entries.subList(_currentEntryIndex + 1, _entries.size()));
    }
    return result;
  }

  /**
   * Go to specified navigation entry.
   * @param navigationEntry
   */
  public void goTo(BrowserNavigationHistoryEntry navigationEntry) {
    _currentEntryIndex = _entries.indexOf(navigationEntry);
  }

  /**
   * Notify registered actions to update themselves.
   */
  protected void notifyActionListeners() {
    for (SemanticBrowserHistoryAction action : _actionAsListeners) {
      action.updateControlState();
    }
  }

  /**
   * Enable / Disable the update method to avoid re-entrance.
   * @param doUpdate the _shouldDoUpdate to set
   * @see #update(Object).
   */
  public void setDoUpdate(boolean doUpdate) {
    _shouldDoUpdate = doUpdate;
  }

  /**
   * Remove all entries after the active entry.
   */
  private void removeForwardEntries() {
      int length = _entries.size();
      for (int i = _currentEntryIndex + 1; i < length; i++) {
          _entries.remove(_currentEntryIndex + 1);
      }
  }
  
  /**
   * Update is called when an new input is set. Add element to the history. Update index. retain backward history.
   * @param realObject
   */
  public void update(Object realObject) {
    cleanInvalidEntries();
    if (_shouldDoUpdate && (null != realObject)) {
      // retain backward history only.
      if (_entries.size() == MAX_SIZE) {
        _entries.remove(0); // remove the oldest entry.
      }
      // Add new entry.
      removeForwardEntries();
      _entries.add(new BrowserNavigationHistoryEntry(realObject));
      _currentEntryIndex = _entries.size() - 1;
    }
    // Notify actions to update them.
    notifyActionListeners();
  }
}
