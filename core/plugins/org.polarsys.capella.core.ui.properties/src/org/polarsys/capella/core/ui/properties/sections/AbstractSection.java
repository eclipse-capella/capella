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
package org.polarsys.capella.core.ui.properties.sections;

import java.lang.reflect.Method;
import java.util.List;

import org.eclipse.core.commands.operations.IOperationHistoryListener;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.commands.operations.OperationHistoryEvent;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.workspace.EMFCommandOperation;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.model.handler.provider.CapellaReadOnlyHelper;
import org.polarsys.capella.core.model.handler.provider.IReadOnlyListener;
import org.polarsys.capella.core.model.handler.provider.IReadOnlySectionHandler;
import org.polarsys.capella.core.ui.properties.CapellalEditingDomainListenerForPropertySections;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;

/**
 * The NamedElement customized section class.
 */
public abstract class AbstractSection extends AbstractPropertySection implements IAbstractSection, IOperationHistoryListener, IReadOnlyListener {
  /**
   * Whether or not the field is displayed in a wizard.
   */
  private boolean displayedInWizard;
  /**
   * Capella element displayed by this section.
   */
  private EObject _element;
  /**
   * Parent background color.
   */
  private Color parentBackgroundColor;
  /**
   * Main composite displayed in this section.
   * @deprecated Subclasses should no longer use this and override {@link #createContents(Composite, TabbedPropertySheetPage)} instead
   */
  @Deprecated
  protected Composite rootParentComposite;

  /**
   * Widget factory used when displaying in a wizard.
   */
  private TabbedPropertySheetWidgetFactory widgetFactory;
  /**
   * Group that will be shared for all the reference fields
   */
  private Group referencesGroup;
  /**
   * Group that will be shared for all the check boxes
   */
  private Group checkGroup;
  /**
   * 
   */
  private TabbedPropertySheetPage propertySheetPage;

  /**
   * Creates the root composite for this section. Depending on whether
   * this section is displayed in the capella properties wizard or not, 
   * different composites are created. Subclasses should therefore avoid
   * to override this method, and implement
   * {@link #createContents(Composite, TabbedPropertySheetPage)}
   * which is always using the correct composite.
   * 
   * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#createControls(org.eclipse.swt.widgets.Composite,
   *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
   *
   */
  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);
    if (null != parentBackgroundColor) {
      handleParentBackground(parentBackgroundColor, parent);
    }
    if (aTabbedPropertySheetPage == null) { 
      displayedInWizard = true;
      parent.addDisposeListener(e->dispose());
    } else {
      propertySheetPage = aTabbedPropertySheetPage;
      CapellalEditingDomainListenerForPropertySections.getCapellaDataListenerForPropertySections().registerPropertySheetPage(propertySheetPage);
    }

    rootParentComposite = getWidgetFactory().createComposite(parent);
    rootParentComposite.setLayout(createLayout());

    createContents(rootParentComposite, aTabbedPropertySheetPage);
  }

  /**
   * Defines the layout that should be used on the composite passed to {@link #createContents(Composite, TabbedPropertySheetPage)}.
   * Defaults to a grid layout with equal width columns. Number of colums is defined by {@link #getColumnCount()}
   */
  protected Layout createLayout() {
    return new GridLayout(getColumnCount(), true);
  }

  /**
   * Define the number of columns used for the default grid layout.
   */
  protected int getColumnCount() {
    return 1;
  }

  /**
   * Override this method to fill the section with contents rather than overriding {@link #createControls(Composite, TabbedPropertySheetPage)}
   */
  protected void createContents(Composite rootParentComposite, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    // empty impl for compatibility with sections in 3rd party code that still overrides createControls()
  }

  /**
   * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#dispose()
   */
  @Override
  public void dispose() {
    super.dispose();
    // Unregister...
    CapellaReadOnlyHelper.unregister(_element, this);
    CapellalEditingDomainListenerForPropertySections.getCapellaDataListenerForPropertySections().unregisterPropertySheetPage(propertySheetPage);

    // Clean capella element.
    _element = null;

    if (null != widgetFactory) {
      // Clean widget factory.
      widgetFactory.dispose();
      widgetFactory = null;
    }

    // Remove as operation history listener.
    OperationHistoryFactory.getOperationHistory().removeOperationHistoryListener(this);
  }

  /**
   * @return the color
   */
  protected Color getColor() {
    return parentBackgroundColor;
  }

  /**
   * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#getWidgetFactory()
   */
  @Override
  public TabbedPropertySheetWidgetFactory getWidgetFactory() {
    TabbedPropertySheetWidgetFactory result = null;
    if (!isDisplayedInWizard()) {
      result = super.getWidgetFactory();
    } else {
      // Lazy creation pattern.
      if (null == widgetFactory) {
        widgetFactory = new TabbedPropertySheetWidgetFactory();
      }
      result = widgetFactory;
    }
    return result;
  }

  /**
   * @return the shared group that will contain all reference fields
   */
  protected Group getReferencesGroup() {
    if (null == referencesGroup) {
      referencesGroup = getWidgetFactory().createGroup(rootParentComposite, ICommonConstants.EMPTY_STRING);
      referencesGroup.setLayout(new GridLayout(6, false));
      GridData gd = new GridData(GridData.FILL_HORIZONTAL);
      gd.horizontalSpan = 2;
      referencesGroup.setLayoutData(gd);
    }
    return referencesGroup;
  }

  /**
   * @return the shared group that will contain all check boxes
   */
  protected Group getCheckGroup() {
    if (null == checkGroup) {
      checkGroup = getWidgetFactory().createGroup(rootParentComposite, ICommonConstants.EMPTY_STRING);
      checkGroup.setLayout(new GridLayout(6, true));
      GridData gd = new GridData(GridData.FILL_HORIZONTAL);
      gd.horizontalSpan = 2;
      checkGroup.setLayoutData(gd);
      checkGroup.moveAbove(getReferencesGroup());
    }
    return checkGroup;
  }

  /**
   * Handle background color.<br>
   * Default implementation set given color to specified parent.
   * @param color
   */
  protected void handleParentBackground(Color color, Composite parent) {
    parent.setBackground(color);
  }

  /**
   * @see org.eclipse.core.commands.operations.IOperationHistoryListener#historyNotification(org.eclipse.core.commands.operations.OperationHistoryEvent)
   */
  @Override
  public void historyNotification(OperationHistoryEvent event) {
    // We only handle undo & redo operations to force a refresh.
    int eventType = event.getEventType();
    if ((OperationHistoryEvent.UNDONE == eventType) || (OperationHistoryEvent.REDONE == eventType)) {
      IUndoableOperation operation = event.getOperation();
      // Take into account the EMF command operation.
      if (operation instanceof EMFCommandOperation) {
        // Get the command.
        Command command = ((EMFCommandOperation) operation).getCommand();
        // Is the current capella element involved in this command ?
        if (command.getAffectedObjects().contains(_element)) {
          // If so, let's refresh the content.
          refresh();
        }
      }
    }
  }

  /**
   * Is this field displayed in a wizard ?
   * @return the displayedInWizard
   */
  protected boolean isDisplayedInWizard() {
    return displayedInWizard;
  }
  
  /**
   * Allows to adapt the loaded element
   */
  protected EObject adaptElement(EObject element) {
    if (element instanceof DSemanticDecorator) {
      return ((DSemanticDecorator) element).getTarget();
    }
    return element;
  }
  
  /**
   * load the form data from given Capella element.<br>
   * Default implementation registers an EMF adapter to listen to model changes if displayed in a wizard.
   */
  @Override
  public void loadData(EObject object) {
    EObject element = adaptElement(object);
    
    // Register as operation history listener the first time capella element is set.
    if (null == _element) {
      // This operation history listener is used to force refreshes when undo / redo operations are performed.
      OperationHistoryFactory.getOperationHistory().addOperationHistoryListener(this);
    }
    _element = element;
    // Register....
    register(_element);

    // Disable the section if the element is read only.
    IReadOnlySectionHandler roHandler = CapellaReadOnlyHelper.getReadOnlySectionHandler();
    if ((roHandler != null) && roHandler.isLockedByOthers(_element)) {
      setInitialEnabledState(false);
    } else {
      setInitialEnabledState(true);
    }
  }

  /**
   * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#refresh()
   */
  @Override
  public void refresh() {
    // Make sure object is still available.
    if (null != _element && null != _element.eResource()) {
      loadData(_element);
    }
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public abstract boolean select(Object toTest);

  protected EObject selection(Object toTest) {
    return CapellaAdapterHelper.resolveDescriptorOrBusinessObject(toTest);
  }

  protected EObject setInputSelection(IWorkbenchPart part, ISelection selection) {
    super.setInput(part, selection);

    // FIXME MA01 - CapellaCommonNavigator is not IEditingDomainProvider anymore ... check this commented code has no other side-effect
    // if (!(selection instanceof IStructuredSelection)
    // || !((part instanceof IEditingDomainProvider) || (((IAdaptable) part).getAdapter(IEditingDomainProvider.class) != null))) {
    // return null;
    // }
    Object selectedElement = ((IStructuredSelection) selection).getFirstElement();
    return CapellaAdapterHelper.resolveDescriptorOrBusinessObject(selectedElement);
  }

  /**
   * Set parent background color.
   * @param color
   */
  @Override
  public void setParentBackgroundColor(Color color) {
    parentBackgroundColor = color;
  }

  /**
   * Set whether or not this section is enabled or not.<br>
   * Enabled means all internal widgets are set to specified <code>enabled</code> value.
   * @param enabled
   */
  @Override
  public void setEnabled(final boolean enabled) {
    // Forward enablement to internal semantic fields.
    for (AbstractSemanticField semanticField : getSemanticFields()) {
      // FIXME We should not have null Object in this list
      if (null != semanticField) {
        semanticField.setEnabled(enabled);
      }
    }
  }
  
  /**
   * Set the initial enablement state of the section
   * @param enabled
   */
  protected void setInitialEnabledState(boolean enabled) {
    setEnabled(enabled);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void refreshTitleBar() {
    if (null != propertySheetPage) {
      try {
        Method refreshTitleMethod = TabbedPropertySheetPage.class.getDeclaredMethod("refreshTitleBar", new Class[] {}); //$NON-NLS-1$
        refreshTitleMethod.setAccessible(true);
        refreshTitleMethod.invoke(propertySheetPage, new Object[] {});
      } catch (Exception exception) {
        // Catch exception silently.
      }
    }
  }

  /**
   * Execute a command that modifies the model.
   * @param command
   */
  protected void executeCommmand(ICommand command) {
    getExecutionManager().execute(command);
  }

  /**
   * Retrieve the execution manager
   */
  protected ExecutionManager getExecutionManager() {
    return TransactionHelper.getExecutionManager(_element);
  }

  /**
   * Get all semantic field in the current section.
   * @return must be not <code>null</code>.
   */
  public abstract List<AbstractSemanticField> getSemanticFields();

  /**
   * @param element
   * @return {@link IReadOnlySectionHandler}
   */
  protected IReadOnlySectionHandler register(EObject element) {
    return CapellaReadOnlyHelper.register(element, this);
  }
  
  @Override
  public void performFinish() {
    // Do nothing
  }
}
