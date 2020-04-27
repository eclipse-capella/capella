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

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.core.diagram.helpers.RepresentationAnnotationHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.BooleanValueGroup;
import org.polarsys.capella.core.ui.properties.fields.EnumerationValueGroup;
import org.polarsys.capella.core.ui.properties.fields.TextAreaValueGroup;

/**
 *
 */
public class DiagramManagementPropertySection extends AbstractSection {

  private WeakReference<DRepresentationDescriptor> _descriptor;
  private BooleanValueGroup _visibleInDocGroup;
  private BooleanValueGroup _visibleInLMGroup;
  private EnumerationValueGroup _status;
  private TextAreaValueGroup _review;


  @Override
  protected int getColumnCount() {
    return 1;
  }

  /**
   * @see org.eclipse.ui.views.properties.tabbed.ISection#createControls(org.eclipse.swt.widgets.Composite,
   *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
   */
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {

    boolean displayedInWizard = isDisplayedInWizard();

    _visibleInDocGroup = new BooleanValueGroup(parent, Messages.VisibleInDocGroup_Label, getWidgetFactory()) {
      /**
       * {@inheritDoc}
       */
      @Override
      public void loadComboValue() {
        _valueField.select(RepresentationAnnotationHelper.isVisibleInDoc(_descriptor.get()) ? 0 : 1);
      }

      /**
       * {@inheritDoc}
       */
      @Override
      protected void fillComboField(CCombo comboField) {
        if (comboField.equals(_valueField)) {
          transactional(x -> {
            RepresentationAnnotationHelper.setVisibleInDoc(x, Boolean.valueOf(_comboItems[_valueField.getSelectionIndex()]));
          });
        }
      }
    };
    _visibleInDocGroup.setDisplayedInWizard(displayedInWizard);

    _visibleInLMGroup = new BooleanValueGroup(parent, Messages.VisibleForTraceabilityGroup_Label, getWidgetFactory()) {
      /**
       * {@inheritDoc}
       */
      @Override
      public void loadComboValue() {
        _valueField.select(RepresentationAnnotationHelper.isVisibleInLM(_descriptor.get()) ? 0 : 1);
      }

      /**
       * {@inheritDoc}
       */
      @Override
      protected void fillComboField(CCombo comboField) {
        if (comboField.equals(_valueField)) {
          transactional(x -> {
            RepresentationAnnotationHelper.setVisibleInLM(x, Boolean.valueOf(_comboItems[_valueField.getSelectionIndex()]));
          });
        }
      }
    };
    _visibleInLMGroup.setDisplayedInWizard(displayedInWizard);

    _status = new EnumerationValueGroup(parent, Messages.ProgressStatus_Label, getWidgetFactory()) {
      /**
       * {@inheritDoc}
       */
      @Override
      protected void setDataValue(EObject object, EStructuralFeature feature, Object value) {
        transactional(x -> {
          RepresentationAnnotationHelper.setProgressStatus(x, (EObject)value);
        });
      }

      /**
       * {@inheritDoc}
       */
      @Override
      protected List<EObject> getAvailableValues() {
        List<EObject> result = new ArrayList<EObject>(0);
        IBusinessQuery query =
            BusinessQueriesProvider.getInstance().getContribution(CapellacorePackage.Literals.CAPELLA_ELEMENT,
                CapellacorePackage.Literals.CAPELLA_ELEMENT__STATUS);
        if (null != query) {
          result.addAll(query.getAvailableElements((CapellaElement) CapellaAdapterHelper.resolveBusinessObject(_descriptor.get())));
        }
        return result;
      }

      /**
       * {@inheritDoc}
       */
      @Override
      protected List<EObject> getCurrentValues() {
        List<EObject> result = new ArrayList<EObject>(0);
        EnumerationPropertyLiteral value = RepresentationAnnotationHelper.getProgressStatus(_descriptor.get());
        if (value != null) {
          result.add(value);
        }
        return result;
      }
    };
    
    _status.setDisplayedInWizard(displayedInWizard);

    _review = new TextAreaValueGroup(_status.getParent(), Messages.ReviewGroup_Label, getWidgetFactory(), true) {
      /**
       * {@inheritDoc}
       */
      @Override
      public void loadTextValue() {
        valueField.setText(RepresentationAnnotationHelper.getStatusReview(_descriptor.get()));
      }

      /**
       * {@inheritDoc}
       */
      @Override
      protected void setDataValue(EObject object, EStructuralFeature feature, Object value) {
        final String val = (value instanceof String) ? (String)value : ICommonConstants.EMPTY_STRING;
        transactional(x -> {
          RepresentationAnnotationHelper.setStatusReview(x, val);
        });
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

    if (null != _descriptor) {
      _descriptor.clear();
      _descriptor = null;
    }
  }

  /**
   * @param source
   * @param value
   */
  protected void transactional(Consumer<DRepresentationDescriptor> f) {
    executeCommmand(new AbstractReadWriteCommand() {
      /**
       * {@inheritDoc}
       */
      @Override
      public Collection<?> getAffectedObjects() {
        return Collections.singleton(_descriptor.get());
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
      public void run() {
        f.accept(_descriptor.get());
      }
    });
  }

  /**
   * load the form data from given capella element.<br>
   * Default implementation registers an EMF adapter to listen to model changes if displayed in a wizard.
   */
  protected void loadData() {
    super.loadData(_descriptor.get());
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
    return (toTest instanceof DRepresentationDescriptor) || (toTest instanceof DRepresentation) || (toTest instanceof IDDiagramEditPart);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setInput(IWorkbenchPart part, ISelection selection) {
    if (!selection.isEmpty()) {
      if (selection instanceof IStructuredSelection) {
        Object firstElement = ((IStructuredSelection) selection).getFirstElement();
        
        DRepresentationDescriptor descriptor = null;
        
        if (firstElement instanceof DRepresentationDescriptor) {
          descriptor = ((DRepresentationDescriptor) firstElement);
        }

        if (firstElement instanceof DRepresentation) {
          descriptor = RepresentationHelper.getRepresentationDescriptor((DRepresentation)firstElement);
          
        } else if (firstElement instanceof IDDiagramEditPart) {
          IDDiagramEditPart diagramEditPart = (IDDiagramEditPart) firstElement;
          descriptor = RepresentationHelper.getRepresentationDescriptor(((DRepresentation) ((Diagram) diagramEditPart.getModel()).getElement()));
        } 
        
        _descriptor = new WeakReference<DRepresentationDescriptor>(descriptor);
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
