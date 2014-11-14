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
package org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.Util;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.ui.toolkit.editors.Editor;
import org.polarsys.capella.core.data.capellacommon.GenericTrace;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.requirement.RequirementsTrace;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.MDTrace;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.messages.Messages;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.messages.TraceUtil;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.components.TraceTreeViewer;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.components.TraceTreeViewer.TraceType;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.providers.IImageKeys;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.providers.TracePageContentProvider;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.providers.TraceStore;
import org.polarsys.capella.core.ui.resources.CapellaUIResourcesPlugin;
import org.polarsys.capella.core.ui.toolkit.dialogs.CapellaWizardDialog;

/**
 * <code>ViewEditPage</code> is the page wizard presenting the two treeviewer for targetElements and sourceElements, but also the model element selection for
 * adding and update.
 */
public class ViewEditPage extends WizardPage {

  /** the current NamedElement that has been selected by the user */
  private TraceableElement _currentElt;
  private TraceTreeViewer _leftTraceViewer;
  private TraceTreeViewer _rightTraceViewer;

  private Text _statusBarText;

  private Menu _editMenu;

  private SelectionListener _menuListener = new SelectionAdapter() {
    /**
     * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
     */
    @Override
    public void widgetSelected(SelectionEvent e_p) {
      MenuItem item = (MenuItem) e_p.widget;
      if (item.getText().equals(Messages.getString("ViewEditPage.edit_menu.text"))) { //$NON-NLS-1$
        final Trace currentTrace = (Trace) item.getData();
        Editor traceEditor = new Editor(new TracePageContentProvider(), new TraceStore(currentTrace)) {
          /**
           * @see org.eclipse.jface.wizard.Wizard#getDefaultPageImage()
           */
          @Override
          public Image getDefaultPageImage() {
            Image image = null;
            if (null != getStore()) {
              ImageDescriptor pngImageDescriptor = CapellaUIResourcesPlugin.getDefault().getPNGImage(currentTrace.eClass());
              image = (null != pngImageDescriptor) ? pngImageDescriptor.createImage() : super.getDefaultPageImage();
            }
            return image;
          }
          /**
           * @see org.eclipse.jface.wizard.Wizard#performFinish()
           */
          public boolean performFinish() {
            // the store must be done in the context of a transaction
            TransactionHelper.getExecutionManager(currentTrace).execute(new AbstractReadWriteCommand(){
              public void run() {
                _page.store();
              }
            });
            return true;
          }
        };
        CapellaWizardDialog dlg = new CapellaWizardDialog(getShell(), traceEditor);
        dlg.open();
      } else if (item.getText().equals(Messages.getString("ViewEditPage.show_in_explorer_menu.text"))) { //$NON-NLS-1$
        final CapellaElement currentSelection = (CapellaElement) item.getData();
        if (PlatformUI.isWorkbenchRunning()) {
          TraceUtil.findAndSelectElement(currentSelection);
        }
      }
    }
  };

  /**
   * @param pageName_p
   */
  public ViewEditPage(String pageName_p, TraceableElement currentElt_p) {
    super(pageName_p);
    _currentElt = currentElt_p;
    setTitle(Messages.getString("ViewEditPage.title")); //$NON-NLS-1$
    setCapellaImageDescriptor(currentElt_p);
  }

  public void setCapellaImageDescriptor(TraceableElement currentElt_p) {
    ImageDescriptor pngImageDescriptor = CapellaUIResourcesPlugin.getDefault().getPNGImage(currentElt_p.eClass());
    setImageDescriptor(pngImageDescriptor);
  }

  protected Composite createInternalComposite(Composite parent_p) {
    Composite composite = new Composite(parent_p, SWT.NONE);
    // Install a layout manager, all widgets are displayed on 2 columns.
    composite.setLayout(new GridLayout(1, true));
    // Set its layout.
    GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
    composite.setLayoutData(gridData);
    return composite;
  }

  /**
   * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
   */
  public void createControl(Composite parent_p) {

    Composite composite = createInternalComposite(parent_p);

    Label currentNamedEltValueLabel = new Label(composite, SWT.LEFT | SWT.READ_ONLY | SWT.BORDER);
    if (_currentElt instanceof AbstractNamedElement) {
      String name = ((AbstractNamedElement) _currentElt).getName();
      currentNamedEltValueLabel.setText((name == null) ? Util.ZERO_LENGTH_STRING : name);
      setTitle(Messages.getString("ViewEditPage.title") + ((AbstractNamedElement) _currentElt).getName()); //$NON-NLS-1$
      currentNamedEltValueLabel.setVisible(false);
    }

    Label imageExplanation = new Label(composite, SWT.CENTER);
    GridData gdData = new GridData();
    gdData.horizontalAlignment = SWT.FILL;
    gdData.verticalAlignment = SWT.NONE;
    gdData.grabExcessHorizontalSpace = true;
    gdData.grabExcessVerticalSpace = false;
    gdData.horizontalSpan = 2;
    imageExplanation.setLayoutData(gdData);
    imageExplanation.setImage(AbstractUIPlugin.imageDescriptorFromPlugin(MDTrace.PLUGIN_ID, IImageKeys.EXPLANATION).createImage());

    // **Presentation of trace elements(source and target)
    Composite traceTreeComposite = new Composite(composite, SWT.FILL);
    traceTreeComposite.setLayout(new FillLayout());

    GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
    traceTreeComposite.setLayoutData(data);

    _leftTraceViewer = new TraceTreeViewer(_currentElt, TraceType.SOURCE_ELEMENT);
    _leftTraceViewer.getControl(traceTreeComposite);
    _leftTraceViewer.setWizardPage(this);

    _rightTraceViewer = new TraceTreeViewer(_currentElt, TraceType.TARGET_ELEMENT);
    _rightTraceViewer.getControl(traceTreeComposite);
    _rightTraceViewer.setWizardPage(this);

    _editMenu = new Menu(traceTreeComposite.getShell());
    MenuItem editMenuItem = new MenuItem(_editMenu, SWT.PUSH);
    editMenuItem.setText(Messages.getString("ViewEditPage.edit_menu.text")); //$NON-NLS-1$
    editMenuItem.addSelectionListener(_menuListener);

    MenuItem showInExplorerItem = new MenuItem(_editMenu, SWT.PUSH);
    showInExplorerItem.setText(Messages.getString("ViewEditPage.show_in_explorer_menu.text")); //$NON-NLS-1$
    showInExplorerItem.addSelectionListener(_menuListener);
    showInExplorerItem.setImage(CapellaUIResourcesPlugin.getDefault().getImage(org.polarsys.capella.core.ui.resources.IImageKeys.CAPELLA_EXPLORER_IMG_16));

    // Adding Status Text
    createStatusTextField(composite);

    // **Adding Listeners
    _leftTraceViewer.addListener(new TraceTreeListener(_rightTraceViewer));
    _leftTraceViewer.addListener(new LabelListener(currentNamedEltValueLabel));
    _rightTraceViewer.addListener(new TraceTreeListener(_leftTraceViewer));
    _rightTraceViewer.addListener(new LabelListener(currentNamedEltValueLabel));
    setControl(composite);
  }

  /**
   * @param composite_p
   */
  private void createStatusTextField(Composite parent_p) {
    _statusBarText = new Text(parent_p, SWT.READ_ONLY | SWT.BORDER);
    _statusBarText.setEditable(false);
    GridData gdData = new GridData();
    gdData.horizontalAlignment = SWT.FILL;
    gdData.grabExcessHorizontalSpace = true;
    gdData.grabExcessVerticalSpace = false;
    gdData.horizontalSpan = 2;
    _statusBarText.setLayoutData(gdData);
  }

  public String getElementPath(Object object_p) {
    if (object_p instanceof TraceableElement) {
      TraceableElement element = (TraceableElement) object_p;

      SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);

      return getPath(systemEngineering, element);
    }
    return Util.ZERO_LENGTH_STRING;
  }

  /**
   * Gets the path.
   * @param sysEng_p tghe system engineering.
   * @param target_p The target element.
   * @return The path.
   */
  public String getPath(SystemEngineering sysEng_p, TraceableElement target_p) {
    String name = (target_p instanceof AbstractNamedElement) ? ((AbstractNamedElement) target_p).getName() : ""; //$NON-NLS-1$
    StringBuffer path = new StringBuffer((name == null) ? Util.ZERO_LENGTH_STRING : name);

    EObject container = target_p.eContainer();
    if (container instanceof TraceableElement) {
      TraceableElement parent = (TraceableElement) container;
      if (parent != sysEng_p) {
        path.insert(path.indexOf(path.toString()), getPath(sysEng_p, parent) + IImageKeys.PATH_SEPARATOR);
      } else {
        String parentName = (parent instanceof AbstractNamedElement) ? ((AbstractNamedElement) parent).getName() : ""; //$NON-NLS-1$
        path.insert(path.indexOf(path.toString()), parentName + IImageKeys.PATH_SEPARATOR);
      }
    }
    return path.toString();
  }

  private void updateStatusBar(Object element_p) {
    if (null == element_p) {
      _statusBarText.setText(Util.ZERO_LENGTH_STRING);
      return;
    }
    _statusBarText.setText(getElementPath(element_p));
  }

  /**
   * Called when the currentNamedElement change in a TraceTreeViewer
   */
  protected class TraceTreeListener extends MouseAdapter implements IDoubleClickListener, ISelectionChangedListener {

    TraceTreeViewer _traceTreeViewer;

    /**
     * Default C'tor
     */
    public TraceTreeListener(TraceTreeViewer traceTreeViewer_p) {
      _traceTreeViewer = traceTreeViewer_p;
      _traceTreeViewer._treeViewer.addSelectionChangedListener(this);
      _traceTreeViewer._treeViewer.getTree().addMouseListener(this);
    }

    /**
     * @see org.eclipse.jface.viewers.IDoubleClickListener#doubleClick(org.eclipse.jface.viewers.DoubleClickEvent)
     */
    public void doubleClick(DoubleClickEvent event_p) {
      ISelection selection = event_p.getSelection();
      if ((selection instanceof IStructuredSelection) && (((IStructuredSelection) selection).size() == 1)) {
        Object elem = ((IStructuredSelection) selection).getFirstElement();
        if (elem instanceof TraceableElement) {
          _traceTreeViewer.updateComponent((TraceableElement) elem);
        }
      }
    }

    /**
     * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
     */
    @SuppressWarnings("synthetic-access")
    public void selectionChanged(SelectionChangedEvent event_p) {
      ISelection selection = event_p.getSelection();
      if ((selection instanceof IStructuredSelection) && (((IStructuredSelection) selection).size() == 1)) {
        Object elem = ((IStructuredSelection) selection).getFirstElement();
        updateStatusBar(elem);
      }
    }

    /**
     * @see org.eclipse.swt.events.MouseListener#mouseUp(org.eclipse.swt.events.MouseEvent)
     */
    @SuppressWarnings("synthetic-access")
    @Override
    public void mouseUp(MouseEvent e_p) {
      if (e_p.button == 3) {
        ISelection selection = _traceTreeViewer._treeViewer.getSelection();
        if ((selection instanceof IStructuredSelection) && (((IStructuredSelection) selection).size() == 1)) {
          Object elem = ((IStructuredSelection) selection).getFirstElement();

          Point point = new Point(e_p.x, e_p.y);

          point = Display.getCurrent().map(_traceTreeViewer._treeViewer.getControl(), null, point);
          _editMenu.setLocation(point);
          MenuItem editItem = _editMenu.getItem(0);
          editItem.setEnabled(false);

          MenuItem showItem = _editMenu.getItem(1);
          showItem.setEnabled(false);

          // only for Generic and Requirement Trace
          if ((elem instanceof GenericTrace) || (elem instanceof RequirementsTrace)) {
            editItem.setData(elem);
            editItem.setEnabled(true);
          }
          if (elem instanceof TraceableElement) {
            showItem.setData(elem);
            showItem.setEnabled(true);
          }

          _editMenu.setVisible(true);
        }
      }

    }
  }

  /**
   * Called when the currentNamedElement change in a TraceTreeViewer
   */
  protected class LabelListener implements IDoubleClickListener {

    Label _namedEltLabel;

    /**
     * 
     */
    public LabelListener(Label namedEltLabel_p) {
      _namedEltLabel = namedEltLabel_p;
    }

    /**
     * @see org.eclipse.jface.viewers.IDoubleClickListener#doubleClick(org.eclipse.jface.viewers.DoubleClickEvent)
     */
    public void doubleClick(DoubleClickEvent event_p) {
      ISelection selection = event_p.getSelection();
      if ((selection instanceof IStructuredSelection) && (((IStructuredSelection) selection).size() == 1)) {
        Object elem = ((IStructuredSelection) selection).getFirstElement();
        if (elem instanceof TraceableElement) {
          String name = (elem instanceof AbstractNamedElement) ? ((AbstractNamedElement) elem).getName() : ""; //$NON-NLS-1$
          _namedEltLabel.setText((name == null) ? Util.ZERO_LENGTH_STRING : name);
          setTitle(Messages.getString("ViewEditPage.title") + ((name == null) ? Util.ZERO_LENGTH_STRING : name)); //$NON-NLS-1$
          setCapellaImageDescriptor((TraceableElement) elem);
        }
      }
    }
  }
}
