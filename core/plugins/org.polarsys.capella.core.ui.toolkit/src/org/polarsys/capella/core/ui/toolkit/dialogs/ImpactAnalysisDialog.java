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
package org.polarsys.capella.core.ui.toolkit.dialogs;

import java.text.Collator;
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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.ui.toolkit.dialogs.AbstractViewerDialog;
import org.polarsys.capella.common.ui.toolkit.viewers.AbstractContextMenuFiller;
import org.polarsys.capella.common.ui.toolkit.viewers.IViewerStyle;
import org.polarsys.capella.common.ui.toolkit.viewers.TreeAndListViewer;
import org.polarsys.capella.common.ui.toolkit.viewers.data.DataContentProvider;
import org.polarsys.capella.common.ui.toolkit.viewers.data.DataLabelProvider;
import org.polarsys.capella.common.ui.toolkit.viewers.data.TreeData;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

/**
 * Impact Analysis Tool dialog.
 */
public class ImpactAnalysisDialog extends AbstractViewerDialog {

  public static final String IMPACT_ANALYSIS_DIALOG = "org.polarsys.capella.core.ui.toolkit.dialogs.impactAnalysis";

  /**
   * Impact analysis label provider.
   */
  protected class ImpactAnalysisLabelProvider extends DataLabelProvider implements IColorProvider {

    /**
     * Foreground color for referencing elements.
     */
    private int _foregroundColor;

    /**
     * Constructor.
     * 
     * @param adapterFactory_p
     * @param foregroundColorForReferencingElements_p
     *          must be a {@link SWT#COLOR} constant.
     */
    public ImpactAnalysisLabelProvider(TreeViewer viewer_p, int foregroundColorForReferencingElements_p) {
      super();
      _foregroundColor = foregroundColorForReferencingElements_p;
      setViewer(viewer_p);
    }

    @Override
    protected Color getValidElementColor() {
      return PlatformUI.getWorkbench().getDisplay().getSystemColor(_foregroundColor);
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
     * 
     * @param collator_p
     */
    public ImpactAnalysisSorter(Collator collator_p) {
      super(collator_p);
    }

    /**
     * @see org.eclipse.jface.viewers.ViewerComparator#compare(org.eclipse.jface.viewers.Viewer, java.lang.Object,
     *      java.lang.Object)
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

  /**
   * Default color for relevant elements i.e the ones specified as input in the dialog.
   */
  protected static final int DEFAULT_COLOR_FOR_RELEVANT_ELEMENTS = SWT.COLOR_BLUE;

  private AbstractContextMenuFiller _contextMenuManagerFiller;
  /**
   * Foreground color for referencing elements.
   */
  private int _foregroundColorForReferencingElements;

  /**
   * Elements that reference the selected one.
   */
  private List<?> _referencingElements;
  /**
   * Is Single selection or not ?
   */
  private boolean _isMultiSelection;

  /**
   * Internal viewer displayed in the custom area.
   */
  private Viewer _viewer;

  /**
   * Constructor.<br>
   * Instantiate a {@link MessageDialog#INFORMATION} message dialog with one OK button and {@link SWT#COLOR_BLUE} to
   * underline referencing elements.
   * 
   * @param referencingElements_p
   * @param dialogTitle_p
   * @param dialogMessage_p
   */
  public ImpactAnalysisDialog(List<?> referencingElements_p, String dialogTitle_p, String dialogMessage_p) {
    this(referencingElements_p, dialogTitle_p, dialogMessage_p, MessageDialog.INFORMATION,
        new String[] { org.polarsys.capella.common.ui.toolkit.dialogs.Messages.AbstractViewerDialog_OK_Title },
        DEFAULT_COLOR_FOR_RELEVANT_ELEMENTS, false);
  }

  /**
   * Constructor.
   * 
   * @param referencingElements_p
   * @param dialogTitle_p
   * @param dialogMessage_p
   * @param dialogImageType_p
   * @param dialogButtonLabels_p
   * @param foregroundColorForReferencingElements_p
   *          must be a {@link SWT#COLOR} constant.
   * @param isMultiSelection_p
   */
  public ImpactAnalysisDialog(List<?> referencingElements_p, String dialogTitle_p, String dialogMessage_p,
      int dialogImageType_p, String[] dialogButtonLabels_p, int foregroundColorForReferencingElements_p,
      boolean isMultiSelection_p) {
    super(PlatformUI.getWorkbench().getDisplay().getActiveShell(), dialogTitle_p, dialogMessage_p, dialogTitle_p);
    setHelpAvailable(false);
    _referencingElements = referencingElements_p;
    _foregroundColorForReferencingElements = foregroundColorForReferencingElements_p;
    _isMultiSelection = isMultiSelection_p;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.dialogs.AbstractMessageDialogWithViewer#createViewer(org.eclipse.swt.widgets.Composite)
   */
  protected TreeViewer createViewer(Composite parent_p) {
    // Create tree viewer.
    // Don't use the status bar of the viewer b
    TreeAndListViewer treeViewer = new TreeAndListViewer(parent_p, _isMultiSelection, IViewerStyle.SHOW_STATUS_BAR) {
      /**
       * @see org.polarsys.capella.common.ui.toolkit.viewers.AbstractRegExpViewer#createControl(org.eclipse.swt.widgets.Composite)
       */
      @Override
      protected void createControl(Composite parent__p) {
        super.createControl(parent__p);
        // Add a button to display EMF resource as root nodes.
        createResourceCheckButton(parent__p);
      }

      @Override
      public String getContextMenuLocation() {
        return IMPACT_ANALYSIS_DIALOG;
      }

      /**
       * Create a check button to display (or not) the resource.
       * 
       * @param parent_p
       */
      protected void createResourceCheckButton(Composite parent__p) {
        // Add a check button to enable the viewer to represent elements by their containing resource.
        Button resourceCheckButton = new Button(parent__p, SWT.CHECK);
        resourceCheckButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        resourceCheckButton.setText(Messages.ImpactAnalysisDialog_ResourceButton_Title);
        resourceCheckButton.setSelection(false);
        resourceCheckButton.addSelectionListener(new SelectionAdapter() {
          /**
           * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
           */
          @Override
          public void widgetSelected(SelectionEvent event_p) {

            handleResourceCheckButtonClicked(((Button) event_p.widget).getSelection());
          }
        });
      }

      /**
       * Handle resource check button click.
       * 
       * @param selection_p
       */
      protected void handleResourceCheckButtonClicked(boolean isChecked_p) {
        // New input data.
        TreeData input = null;
        // Get the 'real' viewer.
        TreeViewer clientViewer = getClientViewer();

        List<Object> referencingElements = ((TreeData) clientViewer.getInput()).getValidElements();
        input = getTreeViewerItems(isChecked_p, referencingElements);
        // Set the new input.
        clientViewer.setInput(input);

      }
    };
    // Install a context menu manager filler if any.
    if (null != _contextMenuManagerFiller) {
      treeViewer.setContextMenuManagerFiller(_contextMenuManagerFiller);
    }
    TreeViewer viewer = treeViewer.getClientViewer();
    viewer.setContentProvider(new DataContentProvider());
    viewer.setLabelProvider(new ImpactAnalysisLabelProvider(viewer, _foregroundColorForReferencingElements));
    // Set layout data.
    viewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
    viewer.setSorter(new ImpactAnalysisSorter());
    return viewer;
  }

  /**
   * 
   * @param isCheckedShowResourceButton
   * @param referencingElements
   * @return
   */
  protected TreeData getTreeViewerItems(boolean isCheckedShowResourceButton, List<Object> referencingElements) {
    TreeData input;
    if (isCheckedShowResourceButton) {
      // Change the viewer's input to take into account resource as parent.
      input = new TreeData(referencingElements, null) {
        /**
         * @see org.polarsys.capella.common.ui.toolkit.viewers.data.TreeData#filterComputedParent(java.lang.Object,
         *      java.lang.Object)
         */
        @Override
        protected Object filterComputedParent(Object parent__p, Object element_p) {
          // Check if computed parent and its child (i.e specified element) have the same resource.
          if (!(parent__p instanceof EObject) || !(element_p instanceof EObject)) {
            return parent__p;
          }
          Object parent = parent__p;
          // Get the resource that hosts the parent object.
          Resource parentResource = ((EObject) parent__p).eResource();
          // Get the resource that hosts the child object.
          Resource childResource = ((EObject) element_p).eResource();
          // Check if both resources are equals.
          if (!parentResource.equals(childResource)) {
            // The child object is not hosts by the parent resource.
            // Since we want to display resources in the tree viewer, the parent for this child is its resource.
            parent = childResource;
          }
          return parent;
        }
      };
    } else {
      // Change the viewer's input to classic data input.
      input = new TreeData(referencingElements, null);
    }
    return input;
  }

  @Override
  protected void doCreateDialogArea(Composite dialogAreaComposite) {
    Composite containingComposite = new Composite(dialogAreaComposite, SWT.NONE);
    GridLayout layout = new GridLayout(1, true);
    containingComposite.setLayout(layout);
    containingComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
    // Create the viewer area.
    createViewerArea(containingComposite);
  }

  protected void createViewerArea(Composite parent) {
    // Create the viewer.
    _viewer = createViewer(parent);
    // Set Input data.
    _viewer.setInput(getInitialInputData());
  }

  @Override
  protected Object getResult() {
    return null;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.dialogs.AbstractMessageDialogWithViewer#getInitialInputData()
   */
  protected TreeData getInitialInputData() {
    return new TreeData(_referencingElements, null);
  }

  /**
   * Set a context menu manager filler.
   * 
   * @param filler
   */
  public void setContextMenuManagerFiller(AbstractContextMenuFiller filler) {
    _contextMenuManagerFiller = filler;
  }
}
