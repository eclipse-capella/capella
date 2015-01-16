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
package org.polarsys.capella.core.validation.ui.ide.quickfix;

import java.text.Collator;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.ui.providers.MDEAdapterFactoryLabelProvider;
import org.polarsys.capella.common.ui.toolkit.dialogs.AbstractMessageDialogWithViewer;
import org.polarsys.capella.common.ui.toolkit.viewers.AbstractContextMenuFiller;
import org.polarsys.capella.common.ui.toolkit.viewers.IViewerStyle;
import org.polarsys.capella.common.ui.toolkit.viewers.TreeAndListViewer;
import org.polarsys.capella.common.ui.toolkit.viewers.data.DataContentProvider;
import org.polarsys.capella.common.ui.toolkit.viewers.data.TreeData;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;
import org.polarsys.capella.core.validation.ui.ide.messages.QuickfixMessages;

public class EObjectNavigatorDialog extends AbstractMessageDialogWithViewer {

  /**
   * Label for our combo box
   */
  protected String _comboLabel;

  /**
   * Prefix for each element
   */
  protected String _elementPrefix;

  /**
   * @see Impact analysis label provider.
   */
  protected class ImpactAnalysisLabelProvider extends MDEAdapterFactoryLabelProvider implements IColorProvider {
    /**
     * Foreground color for referencing elements.
     */
    private int _foregroundColor;
    /**
     * Viewer that uses this label provider.
     */
    private TreeViewer _viewer;

    /**
     * Constructor.
     * @param adapterFactory_p
     * @param foregroundColorForReferencingElements_p must be a {@link SWT#COLOR} constant.
     */
    public ImpactAnalysisLabelProvider(TreeViewer viewer_p, int foregroundColorForReferencingElements_p) {
      super(CapellaAdapterFactoryProvider.getInstance().getAdapterFactory());
      _foregroundColor = foregroundColorForReferencingElements_p;
      _viewer = viewer_p;
    }

    /**
     * @see org.eclipse.jface.viewers.IColorProvider#getBackground(java.lang.Object)
     */
    @Override
    public Color getBackground(Object element_p) {
      return null;
    }

    /**
     * @see org.eclipse.jface.viewers.IColorProvider#getForeground(java.lang.Object)
     */
    @Override
    public Color getForeground(Object element_p) {
      // Select the foreground color for elements that reference the selected one.
      Object input = _viewer.getInput();
      if ((input instanceof TreeData) && (((TreeData) input).isValid(element_p))) {
        Display display = PlatformUI.getWorkbench().getDisplay();
        return display.getSystemColor(_foregroundColor);
      }
      return null;
    }

    /**
     * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(Object object_p) {
      String text = super.getText(object_p);
      return text.replace("%20", ICommonConstants.EMPTY_STRING + ICommonConstants.WHITE_SPACE_CHARACTER); //$NON-NLS-1$
    }
  }

  /**
   * Impact Analysis viewer sorter.
   */
  protected class ImpactAnalysisSorter extends ViewerSorter {
    /**
     * Constructor.
     */
    public ImpactAnalysisSorter() {
      super();
    }

    /**
     * Constructor.
     * @param collator_p
     */
    public ImpactAnalysisSorter(Collator collator_p) {
      super(collator_p);
    }

    /**
     * @see org.eclipse.jface.viewers.ViewerComparator#compare(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(Viewer viewer_p, Object object1_p, Object object2_p) {
      // Sorter Capella resource before capella fragment.
      if ((object1_p instanceof Resource) && (object2_p instanceof Resource)) {
        Resource resource1 = (Resource) object1_p;
        Resource resource2 = (Resource) object2_p;

        // Preconditions : must be capella resources.
        if (CapellaResourceHelper.isCapellaResource(resource1) && CapellaResourceHelper.isCapellaResource(resource2)) {
          int result = 0;
          boolean fragment1 = CapellaResourceHelper.isCapellaFragment(resource1.getURI());
          boolean fragment2 = CapellaResourceHelper.isCapellaFragment(resource2.getURI());
          if (fragment1 && !fragment2) {
            result = 1;
          } else if (!fragment1 && fragment2) {
            result = -1;
          }
          return result;
        }
      }
      return super.compare(viewer_p, object1_p, object2_p);
    }
  }

  private AbstractContextMenuFiller _contextMenuManagerFiller;

  /**
   * Default color for relevant elements i.e the ones specified as input in the dialog.
   */
  protected static final int DEFAULT_COLOR_FOR_REVELANT_ELEMENTS = SWT.COLOR_BLUE;

  /**
   * Foreground color for referencing elements.
   */
  protected int _foregroundColorForRelevantElements;

  /**
   * Elements that reference the selected one.
   */
  protected List<? extends EObject> _revelantElements;

  /**
   * Cycles
   */
  protected Collection<List<EObject>> _cycles;

  protected Combo _combo;

  public void setCycles(Collection<List<EObject>> cycles_p) {
    _cycles = cycles_p;

    return;
  }

  public EObjectNavigatorDialog(List<? extends EObject> elements_p, String dialogTitle_p, String dialogMessage_p, String dialogComboLabel_p,
      String elementPrefix_p) {
    this(elements_p, dialogTitle_p, dialogMessage_p, dialogComboLabel_p, elementPrefix_p, MessageDialog.INFORMATION,
         new String[] { org.polarsys.capella.common.ui.toolkit.dialogs.Messages.AbstractViewerDialog_OK_Title }, DEFAULT_COLOR_FOR_REVELANT_ELEMENTS);
  }

  public EObjectNavigatorDialog(List<? extends EObject> elements_p, String dialogTitle_p, String dialogMessage_p, String dialogComboLabel_p,
      String elementPrefix_p, int dialogImageType_p, String[] dialogButtonLabels_p, int foregroundColorForReferencingElements_p) {
    super(PlatformUI.getWorkbench().getDisplay().getActiveShell(), dialogTitle_p, null, dialogMessage_p, dialogImageType_p, dialogButtonLabels_p, 0);
    _comboLabel = dialogComboLabel_p;
    _elementPrefix = elementPrefix_p;
    _revelantElements = elements_p;
    _foregroundColorForRelevantElements = foregroundColorForReferencingElements_p;
  }

  public EObjectNavigatorDialog(List<? extends EObject> elements_p, String dialogTitle_p, String dialogMessage_p, int dialogImageType_p,
      String[] dialogButtonLabels_p, int foregroundColorForReferencingElements_p) {
    this(elements_p, dialogTitle_p, dialogMessage_p, QuickfixMessages.eobjectnavigator_dialog_combo_lbl,
         QuickfixMessages.eobjectnavigator_dialog_combo_element_prefix, dialogImageType_p, dialogButtonLabels_p, foregroundColorForReferencingElements_p);
  }

  @Override
  protected Control createCustomArea(Composite parent_p) {
    // Create a composing composite.
    Composite containingComposite = new Composite(parent_p, SWT.NONE);
    GridLayout layout = new GridLayout(1, true);

    containingComposite.setLayout(layout);
    containingComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

    createSelectCycleArea(containingComposite);

    // Create the viewer area.
    createViewerArea(containingComposite);
    return containingComposite;
  }

  protected void createSelectCycleArea(Composite parent_p) {

    final int initialSelection = 0;

    Composite comp = new Composite(parent_p, SWT.NONE);

    comp.setLayout(new GridLayout(2, false));
    comp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    Label lbl = new Label(comp, SWT.NONE);
    lbl.setText(_comboLabel);

    _combo = new Combo(comp, SWT.NONE | SWT.READ_ONLY | SWT.BORDER | SWT.COLOR_WIDGET_BACKGROUND);

    GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
    _combo.setLayoutData(gd);

    _combo.setData(String.valueOf(initialSelection), _revelantElements);
    Iterator<List<EObject>> it = _cycles.iterator();
    for (int i = 0; i < _cycles.size(); i++) {
      _combo.add(_elementPrefix + ICommonConstants.WHITE_SPACE_CHARACTER + (i + 1));
      _combo.setData(String.valueOf(i), it.next());
    }

    _combo.select(initialSelection);
    _combo.addSelectionListener(new SelectionAdapter() {
      @SuppressWarnings({ "synthetic-access", "unchecked" })
      @Override
      public void widgetSelected(SelectionEvent e) {
        int idx = _combo.getSelectionIndex();
        List<EObject> list = (List<EObject>) _combo.getData(String.valueOf(idx));
        getViewer().setInput(new TreeData(list, null));

        return;
      }
    });

    return;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.dialogs.AbstractMessageDialogWithViewer#createViewer(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected TreeViewer createViewer(Composite parent_p) {
    // Create tree viewer.
    // Don't use the status bar of the viewer b
    TreeAndListViewer treeViewer = new TreeAndListViewer(parent_p, false, IViewerStyle.SHOW_STATUS_BAR) {
      /**
       * @see org.polarsys.capella.common.ui.toolkit.viewers.AbstractRegExpViewer#createControl(org.eclipse.swt.widgets.Composite)
       */
      @Override
      protected void createControl(Composite parent__p) {
        super.createControl(parent__p);
      }

    };

    // Install a context menu manager filler if any.

    if (null != _contextMenuManagerFiller) {
      treeViewer.setContextMenuManagerFiller(_contextMenuManagerFiller);
    }

    TreeViewer viewer = treeViewer.getClientViewer();
    viewer.setContentProvider(new DataContentProvider());
    viewer.setLabelProvider(new ImpactAnalysisLabelProvider(viewer, _foregroundColorForRelevantElements));
    // Set layout data.
    viewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
    viewer.setSorter(new ImpactAnalysisSorter());
    return viewer;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.dialogs.AbstractMessageDialogWithViewer#getInitialInputData()
   */
  @Override
  protected TreeData getInitialInputData() {
    return new TreeData(_revelantElements, null);
  }

  /**
   * Set a context menu manager filler.
   * @param filler_p
   */
  public void setContextMenuManagerFiller(AbstractContextMenuFiller filler_p) {
    _contextMenuManagerFiller = filler_p;
  }

}
