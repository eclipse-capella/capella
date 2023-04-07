/*******************************************************************************
 * Copyright (c) 2006, 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.toolkit.decomposition;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.bindings.keys.IKeyLookup;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.jface.dialogs.PopupDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

public class RenameDecompositionDialog extends PopupDialog {

  private CTabItem _item;
  private Point _location;
  private DecompositionTargetViewer _viewer;
  List<Control> exclusions = new ArrayList<Control>();

  /**
   * @param parent_p
   * @param shellStyle_p
   * @param takeFocusOnOpen_p
   * @param persistBounds_p
   * @param showDialogMenu_p
   * @param showPersistAction_p
   * @param titleText_p
   * @param infoText_p
   */
  public RenameDecompositionDialog(Shell parent_p, int shellStyle_p, boolean takeFocusOnOpen_p, boolean persistBounds_p, boolean showDialogMenu_p,
      boolean showPersistAction_p, String titleText_p, String infoText_p, TabItem item_p, Point point_p, DecompositionTargetViewer viewer_p) {
    super(parent_p, shellStyle_p, takeFocusOnOpen_p, persistBounds_p,persistBounds_p, showDialogMenu_p, showPersistAction_p, titleText_p, infoText_p);
    this._location = point_p;
    this._viewer = viewer_p;
  }
  
  public RenameDecompositionDialog(Shell parent_p, int shellStyle_p, boolean takeFocusOnOpen_p, boolean persistBounds_p, boolean showDialogMenu_p,
      boolean showPersistAction_p, String titleText_p, String infoText_p, CTabItem item_p, Point point_p, DecompositionTargetViewer viewer_p) {
    super(parent_p, shellStyle_p, takeFocusOnOpen_p, persistBounds_p,persistBounds_p, showDialogMenu_p, showPersistAction_p, titleText_p, infoText_p);
    this._item = item_p;
    this._location = point_p;
    this._viewer = viewer_p;
  }

  /**
   * @see org.eclipse.jface.dialogs.PopupDialog#getInitialLocation(org.eclipse.swt.graphics.Point)
   */
  @Override
  protected Point getInitialLocation(Point initialSize_p) {
    CTabFolder folder = _item.getParent();
    Rectangle rect = folder.getBounds();
    Point pt = folder.toDisplay(rect.x + _location.x, rect.y - folder.getLocation().y + _location.y);
    return pt;
  }

  /**
   * @see org.eclipse.jface.dialogs.PopupDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected Control createDialogArea(Composite parent_p) {
    final Text text = new Text(parent_p, SWT.CENTER);
    exclusions.add(text);
    text.addKeyListener(new KeyListener() {

      public void keyPressed(KeyEvent arg0_p) {
        // do nothing
      }

      @SuppressWarnings("synthetic-access")
      public void keyReleased(KeyEvent ke_p) {
        try {
          String str = text.getText();
          if (str != null && str.trim().length() > 0) {
            DecompositionModel model = _viewer.getDecompositionModel();
            if (!model.isValidName(str.trim())) {
              _viewer.showErrorMessage(Messages.getString("LCDecompGeneralViewer.decomp.rename.errormsg")); //$NON-NLS-1$
            } else {
              _viewer.showErrorMessage(null);
              int enterKey = KeyStroke.getInstance(IKeyLookup.ENTER_NAME).getNaturalKey();
              int numpadEnterKey = KeyStroke.getInstance(IKeyLookup.NUMPAD_ENTER_NAME).getNaturalKey();
              if (ke_p.keyCode == enterKey || ke_p.keyCode == numpadEnterKey) {
                Event event = new Event();
                event.data = str.trim();
                _item.notifyListeners(SWT.MouseDoubleClick, event);
                close();
              }
            }
          }
        } catch (ParseException exception_p) {
          exception_p.printStackTrace();
        }
      }

    });
    return text;
  }

  /**
   * @see org.eclipse.jface.dialogs.PopupDialog#createInfoTextArea(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected Control createInfoTextArea(Composite parent_p) {
    Control ctrl = super.createInfoTextArea(parent_p);
    exclusions.add(ctrl);
    return ctrl;
  }

  /**
   * @see org.eclipse.jface.dialogs.PopupDialog#getBackgroundColorExclusions()
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  protected List getBackgroundColorExclusions() {
    exclusions.addAll(super.getBackgroundColorExclusions());
    return exclusions;
  }
}
