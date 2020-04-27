/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.validation.ui.ide.messages.QuickfixMessages;

public class EObjectNavigatorDialog extends AbstractMessageDialogWithViewer {

  public static final String EOBJECT_NAVIGATOR_DIALOG = "org.polarsys.capella.core.validation.ui.ide.quickfix.objectNavigator";
  
  /**
   * Label for our combo box
   */
  protected String comboLabel;

  /**
   * Prefix for each element
   */
  protected String elementPrefix;

  /**
   * @see Impact analysis label provider.
   */
  protected class ImpactAnalysisLabelProvider extends MDEAdapterFactoryLabelProvider implements IColorProvider {
    /**
     * Foreground color for referencing elements.
     */
    private int foregroundColor;
    /**
     * Viewer that uses this label provider.
     */
    private TreeViewer viewer;

    /**
     * Constructor.
     * @param adapterFactory
     * @param foregroundColorForReferencingElements must be a {@link SWT#COLOR} constant.
     */
    public ImpactAnalysisLabelProvider(TreeViewer viewer, int foregroundColorForReferencingElements) {
      super();
      foregroundColor = foregroundColorForReferencingElements;
      this.viewer = viewer;
    }

    /**
     * @see org.eclipse.jface.viewers.IColorProvider#getBackground(java.lang.Object)
     */
    @Override
    public Color getBackground(Object element) {
      return null;
    }

    /**
     * @see org.eclipse.jface.viewers.IColorProvider#getForeground(java.lang.Object)
     */
    @Override
    public Color getForeground(Object element) {
      // Select the foreground color for elements that reference the selected one.
      Object input = viewer.getInput();
      if ((input instanceof TreeData) && (((TreeData) input).isValid(element))) {
        Display display = PlatformUI.getWorkbench().getDisplay();
        return display.getSystemColor(foregroundColor);
      }
      return null;
    }

    /**
     * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(Object object) {
      String text = super.getText(object);
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
     * @param collator
     */
    public ImpactAnalysisSorter(Collator collator) {
      super(collator);
    }

    /**
     * @see org.eclipse.jface.viewers.ViewerComparator#compare(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(Viewer viewer, Object object1, Object object2) {
      // Sorter Capella resource before capella fragment.
      if ((object1 instanceof Resource) && (object2 instanceof Resource)) {
        Resource resource1 = (Resource) object1;
        Resource resource2 = (Resource) object2;

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
      return super.compare(viewer, object1, object2);
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
  protected int foregroundColorForRelevantElements;

  /**
   * Elements that reference the selected one.
   */
  protected List<? extends EObject> revelantElements;

  /**
   * Cycles
   */
  protected Collection<List<EObject>> cycles;

  protected Combo combo;

  public void setCycles(Collection<List<EObject>> cycles) {
    this.cycles = cycles;
  }

  public EObjectNavigatorDialog(List<? extends EObject> elements, String dialogTitle, String dialogMessage, String dialogComboLabel,
      String elementPrefix) {
    this(elements, dialogTitle, dialogMessage, dialogComboLabel, elementPrefix, MessageDialog.INFORMATION,
         new String[] { org.polarsys.capella.common.ui.toolkit.dialogs.Messages.AbstractViewerDialog_OK_Title }, DEFAULT_COLOR_FOR_REVELANT_ELEMENTS);
  }

  public EObjectNavigatorDialog(List<? extends EObject> elements, String dialogTitle, String dialogMessage, String dialogComboLabel,
      String elementPrefix, int dialogImageType, String[] dialogButtonLabels, int foregroundColorForReferencingElements) {
    super(PlatformUI.getWorkbench().getDisplay().getActiveShell(), dialogTitle, null, dialogMessage, dialogImageType, dialogButtonLabels, 0);
    comboLabel = dialogComboLabel;
    this.elementPrefix = elementPrefix;
    revelantElements = elements;
    foregroundColorForRelevantElements = foregroundColorForReferencingElements;
  }

  public EObjectNavigatorDialog(List<? extends EObject> elements, String dialogTitle, String dialogMessage, int dialogImageType,
      String[] dialogButtonLabels, int foregroundColorForReferencingElements) {
    this(elements, dialogTitle, dialogMessage, QuickfixMessages.eobjectnavigator_dialog_combo_lbl,
         QuickfixMessages.eobjectnavigator_dialog_combo_element_prefix, dialogImageType, dialogButtonLabels, foregroundColorForReferencingElements);
  }

  @Override
  protected Control createCustomArea(Composite parent) {
    // Create a composing composite.
    Composite containingComposite = new Composite(parent, SWT.NONE);
    GridLayout layout = new GridLayout(1, true);

    containingComposite.setLayout(layout);
    containingComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

    if (cycles.size() > 1) {
      createSelectCycleArea(containingComposite);
    }

    // Create the viewer area.
    createViewerArea(containingComposite);
    return containingComposite;
  }

  protected void createSelectCycleArea(Composite parent) {

    final int initialSelection = 0;

    Composite comp = new Composite(parent, SWT.NONE);

    comp.setLayout(new GridLayout(2, false));
    comp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    Label lbl = new Label(comp, SWT.NONE);
    lbl.setText(comboLabel);

    combo = new Combo(comp, SWT.NONE | SWT.READ_ONLY | SWT.BORDER | SWT.COLOR_WIDGET_BACKGROUND);

    GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
    combo.setLayoutData(gd);

    combo.setData(String.valueOf(initialSelection), revelantElements);
    Iterator<List<EObject>> it = cycles.iterator();
    for (int i = 0; i < cycles.size(); i++) {
      combo.add(elementPrefix + ICommonConstants.WHITE_SPACE_CHARACTER + (i + 1));
      combo.setData(String.valueOf(i), it.next());
    }

    combo.select(initialSelection);
    combo.addSelectionListener(new SelectionAdapter() {
      @SuppressWarnings({ "synthetic-access", "unchecked" })
      @Override
      public void widgetSelected(SelectionEvent e) {
        int idx = combo.getSelectionIndex();
        List<EObject> list = (List<EObject>) combo.getData(String.valueOf(idx));
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
  protected TreeViewer createViewer(Composite parent) {
    // Create tree viewer.
    // Don't use the status bar of the viewer b
    TreeAndListViewer treeViewer = new TreeAndListViewer(parent, false, IViewerStyle.SHOW_STATUS_BAR) {
      
      @Override
      public String getContextMenuLocation() {
        return EOBJECT_NAVIGATOR_DIALOG;
      }
      
      /**
       * @see org.polarsys.capella.common.ui.toolkit.viewers.AbstractRegExpViewer#createControl(org.eclipse.swt.widgets.Composite)
       */
      @Override
      protected void createControl(Composite parent_) {
        super.createControl(parent_);
      }

    };

    // Install a context menu manager filler if any.

    if (null != _contextMenuManagerFiller) {
      treeViewer.setContextMenuManagerFiller(_contextMenuManagerFiller);
    }

    TreeViewer viewer = treeViewer.getClientViewer();
    viewer.setContentProvider(new DataContentProvider());
    viewer.setLabelProvider(new ImpactAnalysisLabelProvider(viewer, foregroundColorForRelevantElements));
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
    return new TreeData(revelantElements, null);
  }

  /**
   * Set a context menu manager filler.
   * @param filler
   */
  public void setContextMenuManagerFiller(AbstractContextMenuFiller filler) {
    _contextMenuManagerFiller = filler;
  }

}
