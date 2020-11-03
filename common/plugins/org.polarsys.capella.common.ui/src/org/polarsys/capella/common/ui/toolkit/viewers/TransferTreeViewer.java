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

package org.polarsys.capella.common.ui.toolkit.viewers;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import org.polarsys.capella.common.ui.toolkit.viewers.transfer.AbstractTransferViewer2;

/**
 * Implementation of an {@link AbstractTransferViewer} where both left and right viewers are {@link TreeViewer}.<br>
 * Implementors must have to provide {@link ITreeContentProvider} and {@link ILabelProvider} according to {@link TreeViewer}.
 */
public class TransferTreeViewer extends AbstractTransferViewer2 {
  /**
   * Default TreeViewer style bits constant.
   */
  private final static int DEFAULT_TREE_VIEWER_STYLE = SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER;

  /**
   * Constructs the transfer tree viewer. TreeViewers used as left and right viewers are instantiated with default {@link TreeViewer} style bits.
   * @param parent parent composite of this viewer.
   * @param style style of this viewer.
   */
  public TransferTreeViewer(Composite parent, int style) {
    this(parent, style, DEFAULT_TREE_VIEWER_STYLE, DEFAULT_TREE_VIEWER_STYLE);
  }

  /**
   * Constructs the transfer tree viewer.
   * @param parent parent composite of this viewer.
   * @param style style of this viewer.
   * @param leftViewerStyleBits style bits used for the left tree viewer.
   * @param rightViewerStyleBits style bits used for the right tree viewer.
   */
  public TransferTreeViewer(Composite parent, int style, int leftViewerStyleBits, int rightViewerStyleBits) {
    super(parent, style, leftViewerStyleBits, rightViewerStyleBits);
  }

  /**
   * @see AbstractTransferViewer#doLeftViewer(AbstractTransferViewer)
   */
  @Override
  protected StructuredViewer doLeftViewer(Composite parent) {
    return new TreeViewer(parent, getLeftViewerStyleBits());
  }

  /**
   * @see AbstractTransferViewer#doRightViewer(AbstractTransferViewer)
   */
  @Override
  protected StructuredViewer doRightViewer(Composite parent) {
    return new TreeViewer(parent, getRightViewerStyleBits());
  }
}
