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
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.model.handler.provider.CapellaReadOnlyHelper;
import org.polarsys.capella.core.model.handler.provider.IReadOnlyListener;
import org.polarsys.capella.core.model.handler.provider.IReadOnlySectionHandler;
import org.polarsys.capella.core.ui.properties.CapellalEditingDomainListenerForPropertySections;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.wizards.Messages;

/**
 * The NamedElement customized section class.
 */
public abstract class AbstractSection extends AbstractPropertySection implements IAbstractSection, IOperationHistoryListener, IReadOnlyListener {
  /**
   * Whether or not the field is displayed in a wizard.
   */
  private boolean _displayedInWizard;
  /**
   * Capella element displayed by this section.
   */
  private CapellaElement _capellaElement;
  /**
   * Parent background color.
   */
  private Color _parentBackgroundColor;
  /**
   * Main composite displayed in this section.
   */
  protected Composite _rootParentComposite;
  /**
   * Widget factory used when displaying in a wizard.
   */
  private TabbedPropertySheetWidgetFactory _widgetFactory;
  /**
   * Group that will be shared for all the reference fields
   */
  private Group _referencesGroup;
  /**
   * Group that will be shared for all the check boxes
   */
  private Group _checkGroup;
  /**
   * 
   */
  private TabbedPropertySheetPage _propertySheetPage;

  /**
   * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#createControls(org.eclipse.swt.widgets.Composite,
   *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
   */
  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);
    if (null != _parentBackgroundColor) {
      handleParentBackground(_parentBackgroundColor, parent);
    }
    // Whether or not we are displayed in a wizard.
    if (null == aTabbedPropertySheetPage) {
      // Root composite is the one specified by the caller.
      _rootParentComposite = parent;
      // Change the flag to indicate we are displaying within a wizard.
      _displayedInWizard = true;
      parent.addDisposeListener(new DisposeListener() {
        /**
         * {@inheritDoc}
         */
        public void widgetDisposed(DisposeEvent e_p) {
          dispose();
        }
      });
    } else {
      _propertySheetPage = aTabbedPropertySheetPage;
      parent.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

      Section section = getWidgetFactory().createSection(parent, ExpandableComposite.TITLE_BAR | ExpandableComposite.TWISTIE | ExpandableComposite.EXPANDED);
      section.setText(Messages.CapellaElement_SectionLabel);

      _rootParentComposite = getWidgetFactory().createFlatFormComposite(section);
      _rootParentComposite.setLayout(new GridLayout(2, true)); // 2 ?

      section.setClient(_rootParentComposite);

      CapellalEditingDomainListenerForPropertySections.getCapellaDataListenerForPropertySections().registerPropertySheetPage(_propertySheetPage);
    }
  }

  /**
   * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#dispose()
   */
  @Override
  public void dispose() {
    super.dispose();
    // Unregister...
    CapellaReadOnlyHelper.unregister(_capellaElement, this);
    CapellalEditingDomainListenerForPropertySections.getCapellaDataListenerForPropertySections().unregisterPropertySheetPage(_propertySheetPage);

    // Clean capella element.
    _capellaElement = null;

    if (null != _widgetFactory) {
      // Clean widget factory.
      _widgetFactory.dispose();
      _widgetFactory = null;
    }

    // Remove as operation history listener.
    OperationHistoryFactory.getOperationHistory().removeOperationHistoryListener(this);
  }

  /**
   * @return the color
   */
  protected Color getColor() {
    return _parentBackgroundColor;
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
      if (null == _widgetFactory) {
        _widgetFactory = new TabbedPropertySheetWidgetFactory();
      }
      result = _widgetFactory;
    }
    return result;
  }

  /**
   * @return the shared group that will contain all reference fields
   */
  protected Group getReferencesGroup() {
    if (null == _referencesGroup) {
      _referencesGroup = getWidgetFactory().createGroup(_rootParentComposite, ICommonConstants.EMPTY_STRING);
      _referencesGroup.setLayout(new GridLayout(6, false));
      GridData gd = new GridData(GridData.FILL_HORIZONTAL);
      gd.horizontalSpan = 2;
      _referencesGroup.setLayoutData(gd);
    }
    return _referencesGroup;
  }

  /**
   * @return the shared group that will contain all check boxes
   */
  protected Group getCheckGroup() {
    if (null == _checkGroup) {
      _checkGroup = getWidgetFactory().createGroup(_rootParentComposite, ICommonConstants.EMPTY_STRING);
      _checkGroup.setLayout(new GridLayout(6, true));
      GridData gd = new GridData(GridData.FILL_HORIZONTAL);
      gd.horizontalSpan = 2;
      _checkGroup.setLayoutData(gd);
      _checkGroup.moveAbove(getReferencesGroup());
    }
    return _checkGroup;
  }

  /**
   * Handle background color.<br>
   * Default implementation set given color to specified parent.
   * @param color_p
   */
  protected void handleParentBackground(Color color_p, Composite parent_p) {
    parent_p.setBackground(color_p);
  }

  /**
   * @see org.eclipse.core.commands.operations.IOperationHistoryListener#historyNotification(org.eclipse.core.commands.operations.OperationHistoryEvent)
   */
  public void historyNotification(OperationHistoryEvent event_p) {
    // We only handle undo & redo operations to force a refresh.
    int eventType = event_p.getEventType();
    if ((OperationHistoryEvent.UNDONE == eventType) || (OperationHistoryEvent.REDONE == eventType)) {
      IUndoableOperation operation = event_p.getOperation();
      // Take into account the EMF command operation.
      if (operation instanceof EMFCommandOperation) {
        // Get the command.
        Command command = ((EMFCommandOperation) operation).getCommand();
        // Is the current capella element involved in this command ?
        if (command.getAffectedObjects().contains(_capellaElement)) {
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
    return _displayedInWizard;
  }

  /**
   * load the form data from given capella element.<br>
   * Default implementation registers an EMF adapter to listen to model changes if displayed in a wizard.
   */
  public void loadData(CapellaElement capellaElement_p) {
    // Register as operation history listener the first time capella element is set.
    if (null == _capellaElement) {
      // This operation history listener is used to force refreshes when undo / redo operations are performed.
      OperationHistoryFactory.getOperationHistory().addOperationHistoryListener(this);
    }
    _capellaElement = capellaElement_p;
    // Register....
    register(_capellaElement);

    // Disable the section if the element is read only.
    IReadOnlySectionHandler roHandler = CapellaReadOnlyHelper.getReadOnlySectionHandler();
    if ((roHandler != null) && roHandler.isLockedByOthers(_capellaElement)) {
      setEnabled(false);
    } else {
      setEnabled(true);
    }
  }

  /**
   * load the form data from given capella element.<br>
   * Default implementation registers an EMF adapter to listen to model changes if displayed in a wizard.
   */
  public void loadData(EObject object_p) {
    if (object_p instanceof CapellaElement) {
      loadData((CapellaElement) object_p);
    }
  }

  /**
   * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#refresh()
   */
  @Override
  public void refresh() {
    // Make sure object is still available.
    if (null != _capellaElement) {
      loadData(_capellaElement);
    }
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  public abstract boolean select(Object toTest_p);

  protected EObject selection(Object toTest) {
    return CapellaAdapterHelper.resolveSemanticObject(toTest);
  }

  protected EObject setInputSelection(IWorkbenchPart part_p, ISelection selection_p) {
    super.setInput(part_p, selection_p);

    // FIXME MA01 - CapellaCommonNavigator is not IEditingDomainProvider anymore ... check this commented code has no other side-effect
    //    if (!(selection_p instanceof IStructuredSelection)
    //        || !((part_p instanceof IEditingDomainProvider) || (((IAdaptable) part_p).getAdapter(IEditingDomainProvider.class) != null))) {
    //      return null;
    //    }
    return CapellaAdapterHelper.resolveSemanticObject(((IStructuredSelection) selection_p).getFirstElement());
  }

  /**
   * Set parent background color.
   * @param color_p
   */
  public void setParentBackgroundColor(Color color_p) {
    _parentBackgroundColor = color_p;
  }

  /**
   * Set whether or not this section is enabled or not.<br>
   * Enabled means all internal widgets are set to specified <code>enabled_p</code> value.
   * @param enabled_p
   */
  public void setEnabled(final boolean enabled_p) {
    // Forward enablement to internal semantic fields.
    for (AbstractSemanticField semanticField : getSemanticFields()) {
      // FIXME We should not have null Object in this list
      if (null != semanticField) {
        semanticField.setEnabled(enabled_p);
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  public void refreshTitleBar() {
    if (null != _propertySheetPage) {
      try {
        Method refreshTitleMethod = TabbedPropertySheetPage.class.getDeclaredMethod("refreshTitleBar", new Class[] {}); //$NON-NLS-1$
        refreshTitleMethod.setAccessible(true);
        refreshTitleMethod.invoke(_propertySheetPage, new Object[] {});
      } catch (Exception exception_p) {
        // Catch exception silently.
      }
    }
  }

  /**
   * Execute a command that modifies the model.
   * @param command_p
   */
  protected void executeCommmand(ICommand command_p) {
    getExecutionManager().execute(command_p);
  }

  /**
   * Retrieve the execution manager
   */
  protected ExecutionManager getExecutionManager() {
    return TransactionHelper.getExecutionManager(_capellaElement);
  }

  /**
   * Get all semantic field in the current section.
   * @return must be not <code>null</code>.
   */
  public abstract List<AbstractSemanticField> getSemanticFields();

  /**
   * @param element_p
   * @return {@link IReadOnlySectionHandler}
   */
  protected IReadOnlySectionHandler register(EObject element_p) {
    return CapellaReadOnlyHelper.register(element_p, this);
  }
}
