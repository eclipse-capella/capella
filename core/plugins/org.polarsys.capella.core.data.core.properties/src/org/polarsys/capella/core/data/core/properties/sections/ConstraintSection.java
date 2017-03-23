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
package org.polarsys.capella.core.data.core.properties.sections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.ui.menu.dynamic.DynamicCreateChildAction;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.core.properties.Messages;
import org.polarsys.capella.core.linkedtext.ui.CapellaEmbeddedLinkedTextEditorInput;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.core.ui.properties.controllers.AbstractMultipleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;
import org.polarsys.capella.core.ui.toolkit.ToolkitPlugin;

/**
 * The Constraint section.
 */
public class ConstraintSection extends NamedElementSection {

  private MultipleSemanticField _constrainedElementsField;
  private Text _specificationText;
  private Button _editSpecificationButton;
  private Button _deleteSpecificationButton;
  private MenuManager _createSpecificationMenu;

  private Constraint _constraint;

  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    _constrainedElementsField = new MultipleSemanticField(getReferencesGroup(), Messages.getString("Constraint.ConstrainedElements.Label"), //$NON-NLS-1$
        getWidgetFactory(), new AbstractMultipleSemanticFieldController() {
          @Override
          protected IBusinessQuery getReadOpenValuesQuery(EObject semanticElement) {
            return BusinessQueriesProvider.getInstance().getContribution(semanticElement.eClass(),
                ModellingcorePackage.eINSTANCE.getAbstractConstraint_ConstrainedElements());
          }
        });
    _constrainedElementsField.setDisplayedInWizard(displayedInWizard);

    Group group = getWidgetFactory().createGroup(rootParentComposite, ""); //$NON-NLS-1$
    GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
    gd.horizontalSpan = 2;
    group.setLayoutData(gd);

    GridLayout gl = new GridLayout(4, false);
    group.setLayout(gl);

    CLabel label = getWidgetFactory().createCLabel(group, Messages.getString("Constraint.OwnedSpecification.Label")); //$NON-NLS-1$
    label.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
    _specificationText = getWidgetFactory().createText(group, "", SWT.MULTI | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL | SWT.WRAP); //$NON-NLS-1$
    _specificationText.setEnabled(false);

    gd = new GridData(SWT.FILL, SWT.FILL, true, true);
    _specificationText.setLayoutData(gd);

    ImageRegistry imgRegistry = ToolkitPlugin.getDefault().getImageRegistry();
    _editSpecificationButton = getWidgetFactory().createButton(group, null, SWT.PUSH);
    _deleteSpecificationButton = getWidgetFactory().createButton(group, null, SWT.PUSH);
    _editSpecificationButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
    _deleteSpecificationButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
    _editSpecificationButton.setImage(imgRegistry.get(ToolkitPlugin.EDIT_IMAGE_ITEM_ID));
    _deleteSpecificationButton.setImage(imgRegistry.get(ToolkitPlugin.REMOVE_IMAGE_ITEM_ID));
    _createSpecificationMenu = new MenuManager();
    _createSpecificationMenu.setRemoveAllWhenShown(true);
    _createSpecificationMenu.addMenuListener(new IMenuListener() {

      public void menuAboutToShow(IMenuManager manager) {
        EditingDomain domain = AdapterFactoryEditingDomain.getEditingDomainFor(getSelectedConstraint());
        ISelection selection = new StructuredSelection(getSelectedConstraint());
        Collection<?> newChildDescriptors = domain.getNewChildDescriptors(getSelectedConstraint(), null);
        for (Object o : newChildDescriptors) {
          if ((o instanceof CommandParameter)
              && (((CommandParameter) o).getFeature() == ModellingcorePackage.Literals.ABSTRACT_CONSTRAINT__OWNED_SPECIFICATION)) {
            CreateAndEditAction action = new CreateAndEditAction(domain, selection, o);
            if (action.isEnabled()) {
              _createSpecificationMenu.add(action);
            }
          }
        }
      }
    });
    _createSpecificationMenu.createContextMenu(_editSpecificationButton);
    _editSpecificationButton.addSelectionListener(new SelectionListener() {
      public void widgetSelected(SelectionEvent e) {
        if (getSelectedConstraint().getOwnedSpecification() != null) {
          if (CapellaUIPropertiesPlugin.getDefault().openWizard(getSelectedConstraint().getOwnedSpecification())) {
            loadData(getSelectedConstraint()); // refresh
          }
        } else {
          _createSpecificationMenu.getMenu().setVisible(true);
        }
      }

      public void widgetDefaultSelected(SelectionEvent e) {
        /* nop */
      }
    });

    _deleteSpecificationButton.addSelectionListener(new SelectionListener() {
      public void widgetSelected(SelectionEvent e) {
        if (getSelectedConstraint().getOwnedSpecification() != null) {
          CapellaDeleteCommand c =
              new CapellaDeleteCommand(TransactionHelper.getExecutionManager(getSelectedConstraint()), Collections.singletonList(getSelectedConstraint()
                  .getOwnedSpecification()), true, !isDisplayedInWizard(), true);
          if (c.canExecute()) {
            c.execute();
            if (c.getAffectedObjects().size() > 0) {
              _specificationText.setText("");
            }
          }
        }
      }

      public void widgetDefaultSelected(SelectionEvent e) {
        /* nop */
      }
    });

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);
    _constraint = (Constraint) capellaElement;
    _constrainedElementsField.loadData(_constraint, ModellingcorePackage.eINSTANCE.getAbstractConstraint_ConstrainedElements());
    _specificationText.setText(CapellaEmbeddedLinkedTextEditorInput.getDefaultText(_constraint, null));
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == CapellacorePackage.eINSTANCE.getConstraint()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();
    fields.addAll(super.getSemanticFields());
    fields.add(_constrainedElementsField);
    return fields;
  }

  @Override
  public void dispose() {
    super.dispose();
    if (_createSpecificationMenu != null) {
      // see https://bugs.eclipse.org/bugs/show_bug.cgi?id=447600
      _createSpecificationMenu.dispose();
    }
  }

  /*
   * Chains a new child creation action and an open wizard action together
   */
  private final static class CreateAndEditAction extends DynamicCreateChildAction {
    public CreateAndEditAction(EditingDomain editingDomain, ISelection selection, Object descriptor) {
      super(editingDomain, selection, descriptor);
    }

    @Override
    public void run() {
      super.run();
      if (descriptor instanceof CommandParameter) {
        CapellaUIPropertiesPlugin.getDefault().openWizard((EObject) ((CommandParameter) descriptor).value);
      }
    }
  }

  private Constraint getSelectedConstraint() {
    return _constraint;
  }

}
