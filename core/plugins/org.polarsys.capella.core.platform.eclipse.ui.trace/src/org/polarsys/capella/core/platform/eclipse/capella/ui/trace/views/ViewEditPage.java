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
package org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
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
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.ui.toolkit.viewers.menu.ModalContextMenuExtender;
import org.polarsys.capella.core.data.capellacommon.GenericTrace;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.MDTrace;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.extension.TraceExtensionManager;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.messages.Messages;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.components.TraceTreeViewer;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.components.TraceTreeViewer.TraceType;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.providers.IImageKeys;
import org.polarsys.capella.core.ui.properties.wizards.EditCapellaCustomPropertyWizard;
import org.polarsys.capella.core.ui.resources.CapellaUIResourcesPlugin;
import org.polarsys.capella.core.ui.toolkit.dialogs.CapellaWizardDialog;

/**
 * <code>ViewEditPage</code> is the page wizard presenting the two treeviewer for targetElements and sourceElements, but
 * also the model element selection for adding and update.
 */
public class ViewEditPage extends WizardPage {

  /** the current NamedElement that has been selected by the user */
  private TraceableElement _currentElt;
  private TraceTreeViewer _leftTraceViewer;
  private TraceTreeViewer _rightTraceViewer;

  private Text _statusBarText;

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

  private class EditAction extends Action {

    private boolean isLeftPane = false;

    public EditAction(boolean isLeftPane) {
      this.isLeftPane = isLeftPane;
    }

    protected ISelection getSelection() {
      if (isLeftPane) {
        return _leftTraceViewer.getClientViewer().getSelection();
      }
      return _rightTraceViewer.getClientViewer().getSelection();
    }

    @Override
    public boolean isEnabled() {
      IStructuredSelection selection = (IStructuredSelection) getSelection();
      if (!selection.isEmpty()) {
        Object a = selection.getFirstElement();
        return (a instanceof GenericTrace) || (( a instanceof EObject) && TraceExtensionManager.eINSTANCE.canEdit((EObject)a));
      }
      return false;
    }

    @Override
    public void runWithEvent(Event event) {
      super.runWithEvent(event);

      ISelection source = getSelection();

      final Trace currentTrace = (Trace) ((IStructuredSelection) source).getFirstElement();

      IWorkbenchPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();
      EditCapellaCustomPropertyWizard traceEditor = new EditCapellaCustomPropertyWizard(part, currentTrace);

      CapellaWizardDialog dlg = new CapellaWizardDialog(getShell(), traceEditor);
      dlg.open();
    }
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
    imageExplanation
        .setImage(AbstractUIPlugin.imageDescriptorFromPlugin(MDTrace.PLUGIN_ID, IImageKeys.EXPLANATION).createImage());

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

    MenuManager m = new MenuManager();
    Control c = _leftTraceViewer.getClientViewer().getControl();
    m.setRemoveAllWhenShown(true);
    m.addMenuListener(new IMenuListener() {

      @Override
      public void menuAboutToShow(IMenuManager manager) {
        IAction editAction = new EditAction(true);
        editAction.setText(Messages.getString("ViewEditPage.edit_menu.text")); //$NON-NLS-1$
        manager.add(new ActionContributionItem(editAction));

      }
    });
    ModalContextMenuExtender.registerContextMenu(m,
        "org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.leftPane",
        _leftTraceViewer.getClientViewer());
    c.setMenu(m.createContextMenu(c));

    MenuManager m2 = new MenuManager();
    Control c2 = _rightTraceViewer.getClientViewer().getControl();
    m2.setRemoveAllWhenShown(true);
    c2.setMenu(m2.createContextMenu(c2));
    m2.addMenuListener(new IMenuListener() {

      @Override
      public void menuAboutToShow(IMenuManager manager) {
        IAction editAction = new EditAction(false);
        editAction.setText(Messages.getString("ViewEditPage.edit_menu.text")); //$NON-NLS-1$
        manager.add(new ActionContributionItem(editAction));

      }
    });
    ModalContextMenuExtender.registerContextMenu(m2,
        "org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.rightPane",
        _rightTraceViewer.getClientViewer());

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
   * 
   * @param sysEng_p
   *          tghe system engineering.
   * @param target_p
   *          The target element.
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