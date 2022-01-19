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

package org.polarsys.capella.common.flexibility.wizards.renderer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;

/**
 *
 */
public class CopyTextRenderer extends TextRenderer {

  Label copyButton;

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean isImage() {
    return false;
  }

  @Override
  protected void initializeControls(final Composite parent, IRendererContext context) {

    if (isCopyButton()) {
      final String ICONS_PATH = "icons/"; //$NON-NLS-1$
      copyButton = new Label(rootTextControl, SWT.NONE);
      copyButton.setToolTipText("Copy to clipboard"); //$NON-NLS-1$
      copyButton.setImage(AbstractUIPlugin.imageDescriptorFromPlugin(FrameworkUtil.getBundle(this.getClass()).getSymbolicName(), ICONS_PATH + "full/etool16/copy.gif").createImage()); //$NON-NLS-1$
      copyButton.addMouseListener(new MouseListener() {

        @Override
        public void mouseUp(MouseEvent e) {
          proceedCopy(parent.getShell());
        }

        @Override
        public void mouseDown(MouseEvent e) {
          //Nothing here
        }

        @Override
        public void mouseDoubleClick(MouseEvent e) {
          //Nothing here
        }
      });

    }

    super.initializeControls(parent, context);

  }

  @Override
  protected void setBackgroundTextControl(Color color) {
    super.setBackgroundTextControl(color);
    if (isCopyButton()) {
      copyButton.setBackground(color);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected int getNbColumn() {
    return super.getNbColumn();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected int getNbTextColumn() {
    return super.getNbTextColumn() + 1;
  }

  protected void proceedCopy(Shell shell) {
    Clipboard cb = new Clipboard(shell.getDisplay());
    TextTransfer textTransfer = TextTransfer.getInstance();
    cb.setContents(new Object[] { textControl.getText() }, new Transfer[] { textTransfer });
  }

  protected boolean isCopyButton() {
    return true;
  }

  @Override
  public void dispose(IRendererContext context) {
    super.dispose(context);
    if (copyButton != null) {
      copyButton.dispose();
    }
  }
}
