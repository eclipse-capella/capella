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

package org.polarsys.capella.common.ui.services.swt.events;

import org.eclipse.jface.bindings.keys.IKeyLookup;
import org.eclipse.jface.bindings.keys.KeyLookupFactory;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.jface.bindings.keys.SWTKeySupport;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

/**
 * Ease KeyEvent handling.
 */
public class AbstractKeyAdapter extends KeyAdapter {
  /**
   * Constructor.
   */
  public AbstractKeyAdapter() {
    super();
  }

  /**
   * Is given key representation the one that raises the specified key event.
   * @param event
   * @param keyRepresentation
   * @return
   */
  protected boolean handle(KeyEvent event, String keyRepresentation) {
    boolean result = false;
    // Do not handle directly the event, as the key bindings may be different from the formal one (e.g Emacs key binding).
    KeyStroke keyStroke = SWTKeySupport.convertAcceleratorToKeyStroke(SWTKeySupport.convertEventToUnmodifiedAccelerator(event));
    try {
      result = keyStroke.equals(KeyStroke.getInstance(keyRepresentation));
    } catch (ParseException exception) {
      // Ignore errors.
    }
    return result;
  }

  /**
   * Is CTRL pressed for specified key event.
   * @param event
   * @return
   */
  protected boolean isCtrlPressed(KeyEvent event) {
    IKeyLookup keyLookup = KeyLookupFactory.getSWTKeyLookup();
    return keyLookup.getCtrl() == event.stateMask || keyLookup.getCtrl() == event.keyCode;
  }
}
