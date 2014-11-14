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

import org.polarsys.capella.common.flexibility.wizards.Activator;
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
  protected void initializeControls(final Composite parent_p, IRendererContext context_p) {

    if (isCopyButton()) {
      copyButton = new Label(rootTextControl, SWT.NONE);
      copyButton.setToolTipText("Copy to clipboard");
      copyButton.setImage(Activator.getDefault().getImage("full/etool16/copy.gif"));
      copyButton.addMouseListener(new MouseListener() {

        @Override
        public void mouseUp(MouseEvent e_p) {
          proceedCopy(parent_p.getShell());
        }

        @Override
        public void mouseDown(MouseEvent e_p) {
          //Nothing here
        }

        @Override
        public void mouseDoubleClick(MouseEvent e_p) {
          //Nothing here
        }
      });

    }

    super.initializeControls(parent_p, context_p);

  }

  @Override
  protected void setBackgroundTextControl(Color color_p) {
    super.setBackgroundTextControl(color_p);
    if (isCopyButton()) {
      copyButton.setBackground(color_p);
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

  protected void proceedCopy(Shell shell_p) {
    Clipboard cb = new Clipboard(shell_p.getDisplay());
    TextTransfer textTransfer = TextTransfer.getInstance();
    cb.setContents(new Object[] { textControl.getText() }, new Transfer[] { textTransfer });
  }

  protected boolean isCopyButton() {
    return true;
  }

  @Override
  public void dispose(IRendererContext context_p) {
    super.dispose(context_p);
    if (copyButton != null) {
      copyButton.dispose();
    }
  }
}
