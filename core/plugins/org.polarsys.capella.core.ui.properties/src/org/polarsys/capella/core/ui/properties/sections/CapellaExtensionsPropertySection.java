/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import org.polarsys.capella.common.ui.services.helper.EObjectImageProviderHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.capellacore.BooleanPropertyValue;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyValue;
import org.polarsys.capella.core.data.capellacore.FloatPropertyValue;
import org.polarsys.capella.core.data.capellacore.IntegerPropertyValue;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.core.data.capellacore.StringPropertyValue;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.core.ui.properties.controllers.CapellaElement_AppliedPropertyValueGroups;
import org.polarsys.capella.core.ui.properties.controllers.CapellaElement_AppliedPropertyValues;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.ReferenceTableField;
import org.polarsys.capella.core.ui.properties.viewers.AbstractPropertyValueCellEditorProvider;
import org.polarsys.capella.core.ui.properties.viewers.TableDelegatedViewer;
import org.polarsys.capella.core.ui.properties.viewers.TreeDelegatedViewer;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;

/**
 */
public class CapellaExtensionsPropertySection extends AbstractSection implements IFilter {

  private ReferenceTableField _appliedPropertyValuesTableField;
  private ReferenceTableField _appliedPropertyValueGroupsTableField;

  /**
   */
  protected class NameColumnLabelProvider extends ColumnLabelProvider {
    /**
     * {@inheritDoc}
     */
    @Override
    public String getText(Object element) {
      if (element instanceof AbstractNamedElement) {
        return ((AbstractNamedElement) element).getName();
      }
      return ICommonConstants.EMPTY_STRING;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Image getImage(Object element) {
      if (element instanceof EObject) {
        return EObjectImageProviderHelper.getImage((EObject) element);
      }
      return super.getImage(element);
    }
  }

  /**
   */
  protected class SummaryColumnLabelProvider extends ColumnLabelProvider {
    /**
     * {@inheritDoc}
     */
    @Override
    public String getText(Object element) {
      if (element instanceof CapellaElement) {
        return ((CapellaElement) element).getSummary();
      }
      return ICommonConstants.EMPTY_STRING;
    }
  }

  /**
   */
  protected class ValueColumnLabelProvider extends ColumnLabelProvider {
    /**
     * {@inheritDoc}
     */
    @Override
    public String getText(Object element) {
      if (element instanceof BooleanPropertyValue) {
        return Boolean.toString(((BooleanPropertyValue) element).isValue());
      } else if (element instanceof EnumerationPropertyValue) {
        EnumerationPropertyLiteral literal = ((EnumerationPropertyValue) element).getValue();
        return (null != literal) ? literal.getName() : Messages.UndefinedValue;
      } else if (element instanceof FloatPropertyValue) {
        return Float.toString(((FloatPropertyValue) element).getValue());
      } else if (element instanceof IntegerPropertyValue) {
        return Integer.toString(((IntegerPropertyValue) element).getValue());
      } else if (element instanceof StringPropertyValue) {
        return ((StringPropertyValue) element).getValue();
      }
      return ICommonConstants.EMPTY_STRING;
    }
  }

  protected final String[] _columnProperties = {
    Messages.Name_ColumnViewer_Label,
    Messages.Value_ColumnViewer_Label,
    Messages.Summary_ColumnViewer_Label
  };

  /**
   * @see org.eclipse.ui.views.properties.tabbed.ISection#createControls(org.eclipse.swt.widgets.Composite,
   *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
   */
  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    rootParentComposite.setLayout(new GridLayout());
    rootParentComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

    boolean displayedInWizard = isDisplayedInWizard();

    Group grp = getWidgetFactory().createGroup(rootParentComposite, ICommonConstants.EMPTY_STRING);
    grp.setLayout(new GridLayout(1, false));
    GridData layoutData = new GridData(GridData.FILL, GridData.FILL, true, true);
    layoutData.horizontalSpan = 2;
    grp.setLayoutData(layoutData);

    _appliedPropertyValuesTableField = new ReferenceTableField(
      grp, getWidgetFactory(), null, Messages.AppliedPropertyValues_Label,
      new CapellaElement_AppliedPropertyValues(), new TableDelegatedViewer(getWidgetFactory(), new AbstractPropertyValueCellEditorProvider()) {
        /**
         * {@inheritDoc}
         */
        @Override
        protected String[] getColumnProperties() {
          return _columnProperties;
        }
        /**
         * {@inheritDoc}
         */
        @Override
        protected boolean createViewerColumns() {
          createTableViewerColumn(0, new NameColumnLabelProvider());
          createTableViewerColumn(1, new ValueColumnLabelProvider());
          createTableViewerColumn(2, new SummaryColumnLabelProvider());
          return true;
        }
        /**
         * {@inheritDoc}
         */
        @Override
        protected void modifyElement(final EObject element, final int column, final Object value) {
          executeCommmand(new AbstractReadWriteCommand() {
            public void run() {
              getCellEditorProvider().modifyElement(element, column, value);
            }
          });
        }
      });
    _appliedPropertyValuesTableField.setDisplayedInWizard(displayedInWizard);

    grp = getWidgetFactory().createGroup(rootParentComposite, ICommonConstants.EMPTY_STRING);
    grp.setLayout(new GridLayout(1, false));
    layoutData = new GridData(GridData.FILL, GridData.FILL, true, true);
    layoutData.horizontalSpan = 2;
    grp.setLayoutData(layoutData);

    _appliedPropertyValueGroupsTableField = new ReferenceTableField(
      grp, getWidgetFactory(), null, Messages.AppliedPropertyValueGroups_Label,
      new CapellaElement_AppliedPropertyValueGroups(), new TreeDelegatedViewer(getWidgetFactory(), new AbstractPropertyValueCellEditorProvider()) {
        /**
         * {@inheritDoc}
         */
        @Override
        protected String[] getColumnProperties() {
          return _columnProperties;
        }
        /**
         * {@inheritDoc}
         */
        @Override
        protected boolean createViewerColumns() {
          createTreeViewerColumn(0, new NameColumnLabelProvider());
          createTreeViewerColumn(1, new ValueColumnLabelProvider());
          createTreeViewerColumn(2, new SummaryColumnLabelProvider());
          return true;
        }
        /**
         * {@inheritDoc}
         */
        @Override
        protected void modifyElement(final EObject element, final int column, final Object value) {
          executeCommmand(new AbstractReadWriteCommand() {
            public void run() {
              getCellEditorProvider().modifyElement(element, column, value);
            }
          });
        }
      }) {
        /**
         * {@inheritDoc}
         */
        @Override
        protected boolean isSelectionValid(Object selection) {
          return (selection instanceof PropertyValueGroup);
        }
    };
    _appliedPropertyValueGroupsTableField.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    _appliedPropertyValuesTableField.loadData(capellaElement, CapellacorePackage.eINSTANCE.getCapellaElement_AppliedPropertyValues());
    _appliedPropertyValueGroupsTableField.loadData(capellaElement, CapellacorePackage.eINSTANCE.getCapellaElement_AppliedPropertyValueGroups());
  }

  /**
   * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#setInput(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
   */
  @Override
  public void setInput(IWorkbenchPart part, ISelection selection) {
    if (selection instanceof StructuredSelection) {
      EObject selectedElement = CapellaAdapterHelper.resolveSemanticObject(((StructuredSelection) selection).getFirstElement());
      if (selectedElement instanceof CapellaElement) {
        if (selectedElement.eClass().equals(CsPackage.eINSTANCE.getPart())) {
          boolean allowMultiplePart = TriStateBoolean.True.equals(CapellaProjectHelper.isReusableComponentsDriven((Part) selectedElement));
          if (!allowMultiplePart) {
            AbstractType type = ((Part) selectedElement).getAbstractType();
            if ((type != null) && !(type instanceof ConfigurationItem)) {
              super.setInput(part, new StructuredSelection(type));
              loadData((CapellaElement) type);
              return;
            }
          }
        }
        loadData((CapellaElement) selectedElement);
      }
    }
    super.setInput(part, selection);
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObj = CapellaAdapterHelper.resolveSemanticObject(toTest);
    if (eObj instanceof CapellaElement) {
      return true;
    }
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.add(_appliedPropertyValuesTableField);
    fields.add(_appliedPropertyValueGroupsTableField);

    return fields;
  }
}
