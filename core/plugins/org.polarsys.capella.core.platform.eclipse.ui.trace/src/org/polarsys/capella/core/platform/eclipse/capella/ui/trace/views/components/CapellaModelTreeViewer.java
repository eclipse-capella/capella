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
package org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.components;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import org.polarsys.capella.common.ui.toolkit.viewers.AbstractRegExpViewer;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.providers.CapellaModelFilter;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.providers.CapellaModelLabelProvider;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.providers.CapellaModelTreeContentProvider;

/**
 */
public class CapellaModelTreeViewer {

  /** model to display */
  private SystemEngineering _system;
  /** UI - Treeviewer */
  public TreeViewer _treeViewer;

  public NamedElement _currentNamedElement;

  /**
   * @param system_p
   */
  public CapellaModelTreeViewer(SystemEngineering system_p) {
    _system = system_p;
  }

  /**
   * <code>getControl</code> render a composite displaying src or target trace elements
   * @param parent_p parent composite
   * @return composite
   */
  public Composite getControl(Composite parent_p) {
    Composite compo = new Composite(parent_p, SWT.BORDER);

    // Create the desired layout for this wizard page
    GridLayout layout = new GridLayout();
    layout.numColumns = 1;
    layout.verticalSpacing = 2;
    layout.marginWidth = 0;
    layout.marginHeight = 2;
    compo.setLayout(layout);

    GridData layoutData = new GridData();
    layoutData.grabExcessHorizontalSpace = true;
    layoutData.grabExcessVerticalSpace = true;
    layoutData.horizontalAlignment = GridData.FILL;
    layoutData.verticalAlignment = GridData.FILL;

    RegExpTreeViewer regExpViewer = new RegExpTreeViewer(compo);
    regExpViewer.getControl().setLayoutData(layoutData);

    _treeViewer = regExpViewer.getClientViewer();
    _treeViewer.getControl().setLayoutData(layoutData);
    _treeViewer.setInput(_system);
    _treeViewer.expandToLevel(1);
    return compo;
  }

  private class RegExpTreeViewer extends AbstractRegExpViewer {

    /**
     * @param parent_p
     */
    public RegExpTreeViewer(Composite parent_p) {
      super(parent_p);
    }

    /**
     * @see org.polarsys.capella.common.ui.toolkit.viewers.AbstractRegExpViewer#doClientViewer(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected TreeViewer doClientViewer(Composite parent_p) {
      TreeViewer treeViewer = new TreeViewer(parent_p, SWT.LEFT | SWT.READ_ONLY | SWT.BORDER);
      treeViewer.setUseHashlookup(true);

      // layout the tree viewer below the text field
      GridData layoutData = new GridData();
      layoutData.grabExcessHorizontalSpace = true;
      layoutData.grabExcessVerticalSpace = true;
      layoutData.horizontalAlignment = GridData.FILL;
      layoutData.verticalAlignment = GridData.FILL;

      treeViewer.getControl().setLayoutData(layoutData);
      treeViewer.setContentProvider(new CapellaModelTreeContentProvider());
      treeViewer.setLabelProvider(new CapellaModelLabelProvider(_currentNamedElement));
      return treeViewer;
    }

    /**
     * Get the client viewer as a {@link TreeViewer}.
     * @return must be not <code>null</code>.
     */
    @Override
    public TreeViewer getClientViewer() {
      return (TreeViewer) super.getClientViewer();
    }

    /**
     * @see org.polarsys.capella.common.ui.toolkit.viewers.AbstractRegExpViewer#handlePatternApplied(org.eclipse.jface.viewers.Viewer)
     */
    @Override
    protected void handlePatternApplied(Viewer viewer_p) {
      super.handlePatternApplied(viewer_p);
    }

    /**
     * @see org.polarsys.capella.common.ui.toolkit.viewers.AbstractRegExpViewer#handleViewerFilterAttachment(org.eclipse.jface.viewers.ViewerFilter)
     */
    @Override
    protected void handleViewerFilterAttachment(ViewerFilter filter_p) {
      super.handleViewerFilterAttachment(filter_p);
      getClientViewer().addFilter(new CapellaModelFilter());
    }

  }

  /**
   * @param currentNamedElement_p the currentNamedElement to set
   */
  public void setCurrentNamedElement(NamedElement currentNamedElement_p) {
    _currentNamedElement = currentNamedElement_p;
  }
}
