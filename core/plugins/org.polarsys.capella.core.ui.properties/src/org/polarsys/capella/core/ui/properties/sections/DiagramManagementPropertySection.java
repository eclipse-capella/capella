/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.properties.sections;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.ui.properties.annotations.IRepresentationAnnotationConstants;
import org.polarsys.capella.core.ui.properties.annotations.RepresentationAnnotationHelper;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.BooleanValueGroup;
import org.polarsys.capella.core.ui.properties.fields.EnumerationValueGroup;
import org.polarsys.capella.core.ui.properties.fields.TextAreaValueGroup;

/**
 *
 */
public class DiagramManagementPropertySection extends AbstractSection {

  private WeakReference<DRepresentation> _representation;
  private BooleanValueGroup _visibleInDocGroup;
  private BooleanValueGroup _visibleInLMGroup;
  private EnumerationValueGroup _status;
  private TextAreaValueGroup _review;

  /**
   * @see org.eclipse.ui.views.properties.tabbed.ISection#createControls(org.eclipse.swt.widgets.Composite,
   *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
   */
  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    _rootParentComposite.setLayout(new GridLayout());
    _rootParentComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

    boolean displayedInWizard = isDisplayedInWizard();

    _visibleInDocGroup = new BooleanValueGroup(_rootParentComposite, Messages.VisibleInDocGroup_Label, getWidgetFactory()) {
      /**
       * {@inheritDoc}
       */
      @SuppressWarnings("synthetic-access")
      @Override
      public void loadComboValue() {
        _valueField.select(RepresentationAnnotationHelper.isVisibleInDoc(_representation.get()) ? 0 : 1);
      }

      /**
       * {@inheritDoc}
       */
      @Override
      protected void fillComboField(CCombo comboField) {
        if (comboField.equals(_valueField)) {
          updateAnnotation(IRepresentationAnnotationConstants.NotVisibleInDoc, Boolean.valueOf(_comboItems[_valueField.getSelectionIndex()]));
        }
      }
    };
    _visibleInDocGroup.setDisplayedInWizard(displayedInWizard);

    _visibleInLMGroup = new BooleanValueGroup(_rootParentComposite, Messages.VisibleForTraceabilityGroup_Label, getWidgetFactory()) {
      /**
       * {@inheritDoc}
       */
      @SuppressWarnings("synthetic-access")
      @Override
      public void loadComboValue() {
        _valueField.select(RepresentationAnnotationHelper.isVisibleInLM(_representation.get()) ? 0 : 1);
      }

      /**
       * {@inheritDoc}
       */
      @Override
      protected void fillComboField(CCombo comboField) {
        if (comboField.equals(_valueField)) {
          updateAnnotation(IRepresentationAnnotationConstants.NotVisibleInLM, Boolean.valueOf(_comboItems[_valueField.getSelectionIndex()]));
        }
      }
    };
    _visibleInLMGroup.setDisplayedInWizard(displayedInWizard);

    _status = new EnumerationValueGroup(_rootParentComposite, Messages.ProgressStatus_Label, getWidgetFactory()) {
      /**
       * {@inheritDoc}
       */
      @Override
      protected void setDataValue(EObject object, EStructuralFeature feature, Object value) {
        String val = ICommonConstants.EMPTY_STRING;
        if (value instanceof AbstractNamedElement) {
          val = ((AbstractNamedElement) value).getName();
        }
        updateAnnotation(IRepresentationAnnotationConstants.ProgressStatus, IRepresentationAnnotationConstants.PROGRESS_VALUE_KEYVALUE, val);
      }

      /**
       * {@inheritDoc}
       */
      @SuppressWarnings("synthetic-access")
      @Override
      protected List<CapellaElement> getAvailableValues() {
        List<CapellaElement> result = new ArrayList<CapellaElement>(0);
        IBusinessQuery query =
            BusinessQueriesProvider.getInstance().getContribution(CapellacorePackage.Literals.CAPELLA_ELEMENT,
                CapellacorePackage.Literals.CAPELLA_ELEMENT__STATUS);
        if (null != query) {
          result.addAll(query.getAvailableElements((CapellaElement) CapellaAdapterHelper.resolveSemanticObject(_representation.get(), true)));
        }
        return result;
      }

      /**
       * {@inheritDoc}
       */
      @SuppressWarnings("synthetic-access")
      @Override
      protected List<CapellaElement> getCurrentValues() {
        List<CapellaElement> result = new ArrayList<CapellaElement>(0);

        String value = RepresentationAnnotationHelper.getProgressStatus(_representation.get());
        if (null != value) {
          for (CapellaElement element : getAvailableValues()) {
            if (value.equals(((AbstractNamedElement) element).getName())) {
              result.add(element);
            }
          }
        }
        return result;
      }
    };
    _status.setDisplayedInWizard(displayedInWizard);

    _review = new TextAreaValueGroup(_status.getParent(), Messages.ReviewGroup_Label, getWidgetFactory(), true) {
      /**
       * {@inheritDoc}
       */
      @SuppressWarnings("synthetic-access")
      @Override
      public void loadTextValue() {
        _valueField.setText(RepresentationAnnotationHelper.getStatusReview(_representation.get()));
      }

      /**
       * {@inheritDoc}
       */
      @Override
      protected void setDataValue(EObject object, EStructuralFeature feature, Object value) {
        String val = ICommonConstants.EMPTY_STRING;
        if (value instanceof String) {
          val = (String) value;
        }
        updateAnnotation(IRepresentationAnnotationConstants.StatusReview, IRepresentationAnnotationConstants.REVIEW_VALUE_KEYVALUE, val);
      }
    };
    _review.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void dispose() {
    super.dispose();

    if (null != _representation) {
      _representation.clear();
      _representation = null;
    }
  }

  /**
   * @param source
   * @param value
   */
  protected void updateAnnotation(final String source, final Boolean value) {
    executeCommmand(new AbstractReadWriteCommand() {
      /**
       * {@inheritDoc}
       */
      @SuppressWarnings("synthetic-access")
      @Override
      public Collection<?> getAffectedObjects() {
        return Collections.singleton(_representation.get());
      }

      /**
       * {@inheritDoc}
       */
      @Override
      public String getName() {
        return Messages.RepresentationSection_Command_Representation_Publication_Label;
      }

      /**
       * {@inheritDoc}
       */
      @Override
      @SuppressWarnings("synthetic-access")
      public void run() {
        RepresentationAnnotationHelper.setAnnotation(_representation.get(), source, value);
      }
    });
  }

  /**
   * @param source
   * @param value
   */
  protected void updateAnnotation(final String source, final String key, final String value) {
    executeCommmand(new AbstractReadWriteCommand() {
      /**
       * {@inheritDoc}
       */
      @SuppressWarnings("synthetic-access")
      @Override
      public Collection<?> getAffectedObjects() {
        return Collections.singleton(_representation.get());
      }

      /**
       * {@inheritDoc}
       */
      @Override
      public String getName() {
        return Messages.RepresentationSection_Command_Representation_Publication_Label;
      }

      /**
       * {@inheritDoc}
       */
      @Override
      @SuppressWarnings("synthetic-access")
      public void run() {
        RepresentationAnnotationHelper.setAnnotation(_representation.get(), source, key, value);
      }
    });
  }

  /**
   * load the form data from given capella element.<br>
   * Default implementation registers an EMF adapter to listen to model changes if displayed in a wizard.
   */
  protected void loadData() {
    super.loadData(_representation.get());
    _visibleInDocGroup.loadComboValue();
    _visibleInLMGroup.loadComboValue();
    _status.loadComboValue();
    _review.loadTextValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void refresh() {
    loadData();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean select(Object toTest) {
    return (toTest instanceof DRepresentation) || (toTest instanceof IDDiagramEditPart);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setInput(IWorkbenchPart part, ISelection selection) {
    if (!selection.isEmpty()) {
      if (selection instanceof IStructuredSelection) {
        Object firstElement = ((IStructuredSelection) selection).getFirstElement();
        if (firstElement instanceof DRepresentation) {
          _representation = new WeakReference<DRepresentation>((DRepresentation) firstElement);
        } else if (firstElement instanceof IDDiagramEditPart) {
          IDDiagramEditPart diagramEditPart = (IDDiagramEditPart) firstElement;
          _representation = new WeakReference<DRepresentation>((DRepresentation) ((Diagram) diagramEditPart.getModel()).getElement());
        } else {
          _representation = null;
        }
      }
      loadData();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.add(_visibleInDocGroup);
    fields.add(_visibleInLMGroup);
    fields.add(_status);
    fields.add(_review);

    return fields;
  }
}
