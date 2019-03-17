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
package org.polarsys.capella.core.flexibility.commands.dynamic.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.polarsys.capella.common.data.activity.ActivityEdge;
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.flexibility.commands.actions.DefaultAction;
import org.polarsys.capella.core.flexibility.commands.dynamic.IActionsProvider;
import org.polarsys.capella.core.flexibility.commands.helpers.EObjectHelper;

/**
 */
public class InformationActionsProvider implements IActionsProvider {

  /**
   * @see org.polarsys.capella.core.flexibility.commands.dynamic.IActionsProvider#getActions()
   */
  @Override
  public Collection<DefaultAction> getActions(Shell shell, ISelectionProvider selectionProvider) {
    List<DefaultAction> list = new ArrayList<>();

    list.add(new RetrieveIDs(shell, selectionProvider));
    list.add(new MustBeTransitioned(shell, selectionProvider));
    list.add(new ShouldNotBeTransitioned(shell, selectionProvider));
    list.add(new Descriptions(shell, selectionProvider));
    list.add(new RetrieveFromIdentifierTransitioned(shell, selectionProvider));
    list.add(new RetrieveHelpers(shell, selectionProvider));

    return list;
  }

  @SuppressWarnings("nls")
  public class MustBeTransitioned extends DefaultAction {

    public MustBeTransitioned(Shell shell, ISelectionProvider selectionProvider) {
      super(shell, selectionProvider);
    }

    @Override
    public boolean isSelectionCompatible() {
      return getSelection(ModelElement.class).size() > 0;
    }

    @Override
    protected String getIconFile() {
      return "process.gif";
    }

    @Override
    public String getText() {
      return "MustBeTransitioned";
    }

    @Override
    public String getCategory() {
      return DefaultCategories.INFORMATION_CATEGORY;
    }

    @Override
    public void execute() {

      for (EObject object : getSelectedEObjects()) {
        setName(object, "");
      }

    }

    /**
     * @param object
     * @param string
     */
    private void setName(EObject object, String string) {
      System.out.println("mustBeTransitioned(" + EObjectHelper.getInstance().getID(object) + ");  //$NON-NLS-1$ ");
    }
  }

  private class GetText {

    Shell shell;
    String value;

    GetText(Shell shell) {
      this.shell = shell;
    }

    public void setValue(String value) {
      this.value = value;
    }

    public String getValue() {
      TitleAreaDialog dlg = new TitleAreaDialog(shell) {
        Text t;

        /**
         * {@inheritDoc}
         */
        @Override
        protected Control createDialogArea(Composite parent) {
          Control control = super.createDialogArea(parent);

          // Create a multiple line text field
          t = new Text(parent, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
          t.setLayoutData(new GridData(GridData.FILL_BOTH));
          setMessage("Choose a text value"); //$NON-NLS-1$
          return control;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void okPressed() {
          setValue(t.getText());
          super.okPressed();

        }

      };
      dlg.open();
      return value;
    }
  }

  @SuppressWarnings("nls")
  public class RetrieveFromIdentifierTransitioned extends DefaultAction {

    public RetrieveFromIdentifierTransitioned(Shell shell, ISelectionProvider selectionProvider) {
      super(shell, selectionProvider);
    }

    @Override
    public boolean isSelectionCompatible() {
      return getSelection(ModelElement.class).size() > 0;
    }

    @Override
    protected String getIconFile() {
      return "process.gif";
    }

    @Override
    public String getText() {
      return "Retrieve From Identifier";
    }

    @Override
    public String getCategory() {
      return DefaultCategories.INFORMATION_CATEGORY;
    }

    @Override
    public void execute() {
      Resource resource = null;
      final List<EObject> objects = new ArrayList<>();

      for (EObject object : getSelectedEObjects()) {
        resource = object.eResource();
      }

      if (resource != null) {
        String value = new GetText(getShell()).getValue();
        if (value != null) {
          String[] values = value.split("\r\n");
          for (String val : values) {
            String[] ids = val.split("#");
            if (ids.length > 1) {
              EObject selected = resource.getEObject(ids[1]);
              if (selected != null) {
                objects.add(selected);
              }
            }
          }
        }
      }

      ISelectionProvider provider = new ISelectionProvider() {

        @Override
        public void setSelection(ISelection selection) {
          // 
        }

        @Override
        public void removeSelectionChangedListener(ISelectionChangedListener listener) {
          //
        }

        @Override
        public ISelection getSelection() {
          return new StructuredSelection(objects);
        }

        @Override
        public void addSelectionChangedListener(ISelectionChangedListener listener) {
          //
        }
      };

      new Descriptions(getShell(), provider).run();

    }

    /**
     * @param object
     * @param string
     */
    private void setName(EObject object, String string) {
      System.out.println("shouldNotBeTransitioned(" + EObjectHelper.getInstance().getID(object) + ");  //$NON-NLS-1$ ");
    }
  }

  @SuppressWarnings("nls")
  public class ShouldNotBeTransitioned extends DefaultAction {

    public ShouldNotBeTransitioned(Shell shell, ISelectionProvider selectionProvider) {
      super(shell, selectionProvider);
    }

    @Override
    public boolean isSelectionCompatible() {
      return getSelection(ModelElement.class).size() > 0;
    }

    @Override
    protected String getIconFile() {
      return "process.gif";
    }

    @Override
    public String getText() {
      return "ShouldNotBeTransitioned";
    }

    @Override
    public String getCategory() {
      return DefaultCategories.INFORMATION_CATEGORY;
    }

    @Override
    public void execute() {

      for (EObject object : getSelectedEObjects()) {
        setName(object, "");
      }

    }

    /**
     * @param object
     * @param string
     */
    private void setName(EObject object, String string) {
      System.out.println("shouldNotBeTransitioned(" + EObjectHelper.getInstance().getID(object) + ");  //$NON-NLS-1$ ");
    }
  }

  @SuppressWarnings("nls")
  public class RetrieveHelpers extends DefaultAction {

    public RetrieveHelpers(Shell shell, ISelectionProvider selectionProvider) {
      super(shell, selectionProvider);
    }

    @Override
    public boolean isSelectionCompatible() {
      return getSelection(ModelElement.class).size() > 0;
    }

    @Override
    protected String getIconFile() {
      return "process.gif";
    }

    @Override
    public String getText() {
      return "Retrieve helper";
    }

    @Override
    public String getCategory() {
      return DefaultCategories.INFORMATION_CATEGORY;
    }

    @Override
    public void execute() {

      for (EObject object : getSelectedEObjects()) {
        Collection<AbstractFunction> a = (FunctionExt.getAllLeafAbstractFunctions((BlockArchitecture) object));
        for (AbstractFunction pkg : a) {
          getLogger().info(pkg.getName() + " " + pkg.hashCode());
        }
      }
    }

    /**
     * @param object
     * @param string
     */
    private void setName(EObject object, String string) {
      System.out.println("EObject " + EObjectHelper.getInstance().getVariable(object) + " = shouldNotBeTransitioned(\""
                         + EObjectHelper.getInstance().getName(object) + "\", " + EObjectHelper.getInstance().getID(object) + ");");
    }
  }

  @SuppressWarnings("nls")
  public class RetrieveCalls extends DefaultAction {

    public RetrieveCalls(Shell shell, ISelectionProvider selectionProvider) {
      super(shell, selectionProvider);
    }

    @Override
    public boolean isSelectionCompatible() {
      return getSelection(ModelElement.class).size() > 0;
    }

    @Override
    protected String getIconFile() {
      return "process.gif";
    }

    @Override
    public String getText() {
      return "Retrieve call method";
    }

    @Override
    public String getCategory() {
      return DefaultCategories.INFORMATION_CATEGORY;
    }

    @Override
    public void execute() {

      for (EObject object : getSelectedEObjects()) {
        setName(object, "");
      }

    }

    /**
     * @param object
     * @param string
     */
    private void setName(EObject object, String string) {
      System.out.println("EObject " + EObjectHelper.getInstance().getVariable(object) + " = shouldNotBeTransitioned(\""
                         + EObjectHelper.getInstance().getName(object) + "\", " + EObjectHelper.getInstance().getID(object) + ");");
    }
  }

  @SuppressWarnings("nls")
  public class RetrieveIDs extends DefaultAction {

    public RetrieveIDs(Shell shell, ISelectionProvider selectionProvider) {
      super(shell, selectionProvider);
    }

    @Override
    public boolean isSelectionCompatible() {
      return getSelection(ModelElement.class).size() > 0;
    }

    @Override
    protected String getIconFile() {
      return "process.gif";
    }

    @Override
    public String getText() {
      return "Retrieve ID";
    }

    @Override
    public String getCategory() {
      return DefaultCategories.INFORMATION_CATEGORY;
    }

    @Override
    public void execute() {
      for (EObject object : getSelectedEObjects()) {
        setName(object, "");
      }
    }

    /**
     * @param object
     * @param string
     */
    private void setName(EObject object, String string) {
      System.out.println("private String " + EObjectHelper.getInstance().getID(object) + " = \"" + EObjectHelper.getInstance().getIDValue(object)
                         + "\";  //$NON-NLS-1$ ");
    }
  }

  @SuppressWarnings("nls")
  public class Descriptions extends DefaultAction {

    String SEP = "  ";

    public Descriptions(Shell shell, ISelectionProvider selectionProvider) {
      super(shell, selectionProvider);
    }

    @Override
    public boolean isSelectionCompatible() {
      return getSelection(ModelElement.class).size() > 0;
    }

    @Override
    protected String getIconFile() {
      return "process.gif";
    }

    @Override
    public String getText() {
      return "Description";
    }

    @Override
    public String getCategory() {
      return DefaultCategories.INFORMATION_CATEGORY;
    }

    @Override
    public void execute() {

      for (EObject object : getSelectedEObjects()) {
        identifier(object, " * ");
      }
      System.out.println();
      for (EObject object : getSelectedEObjects()) {
        browse(object, "");
      }

    }

    /**
     * @param object
     * @param string
     */
    private void identifier(EObject object, String string) {
      System.out.println(string + "identifier:name:'" + EObjectHelper.getInstance().getName(object) + "',id=#"
                         + EObjectHelper.getInstance().getIDValue(object) + "");
    }

    /**
     * @param object
     */
    private void browse(EObject object, String sep) {
      System.out.println(create(object, sep));

      for (EObject content : object.eContents()) {
        browse(content, SEP + sep);
      }
    }

    /**
     * @param object
     * @param string
     */
    private String create(EObject object, String string) {
      String result = " * " + string;
      String d = "\n" + " * " + string + SEP;

      result +=
          NLS.bind(
              "- Create ''{0}'' [{1}]",
              new Object[] { EObjectHelper.getInstance().getName(object), object.eClass().getName(),
                            EObjectHelper.getInstance().getName(object.eContainer()), object.eContainer().eClass().getName() });
      if (object instanceof AbstractTypedElement) {
        result += getLinkTo(object, new EReference[] { ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE }, d);
      }

      if (object instanceof AbstractInformationFlow) {
        result +=
            getLinkTo(object, new EReference[] { ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__SOURCE,
                                                  ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__TARGET }, d);
      }

      if (object instanceof ActivityEdge) {
        result += getLinkTo(object, new EReference[] { ActivityPackage.Literals.ACTIVITY_EDGE__SOURCE, ActivityPackage.Literals.ACTIVITY_EDGE__TARGET }, d);
      }

      if (object instanceof ComponentPort) {
        result += getSetTo(object, new EAttribute[] { FaPackage.Literals.COMPONENT_PORT__KIND, FaPackage.Literals.COMPONENT_PORT__ORIENTATION }, d);
      }

      if (object instanceof PhysicalComponent) {
        result +=
            getSetTo(object,
                new EAttribute[] { PaPackage.Literals.ABSTRACT_PHYSICAL_COMPONENT__KIND, PaPackage.Literals.ABSTRACT_PHYSICAL_COMPONENT__NATURE }, d);
      }

      if (object instanceof FunctionInputPort) {
        result += getLinkTo(object, new EReference[] { FaPackage.Literals.FUNCTION_INPUT_PORT__INCOMING_EXCHANGE_ITEMS }, d);
      }

      if (object instanceof AbstractFunction) {
        result += getLinkTo(object, new EReference[] { FaPackage.Literals.ABSTRACT_FUNCTION__AVAILABLE_IN_STATES }, d);
      }

      if (object instanceof FunctionOutputPort) {
        result += getLinkTo(object, new EReference[] { FaPackage.Literals.FUNCTION_OUTPUT_PORT__OUTGOING_EXCHANGE_ITEMS }, d);
      }

      if (object instanceof Involvement) {
        result +=
            getLinkTo(object, new EReference[] { CapellacorePackage.Literals.INVOLVEMENT__INVOLVER, CapellacorePackage.Literals.INVOLVEMENT__INVOLVED }, d);
      }

      if (object instanceof StateTransition) {
        result +=
            getLinkTo(object,
                new EReference[] { CapellacommonPackage.Literals.STATE_TRANSITION__SOURCE, CapellacommonPackage.Literals.STATE_TRANSITION__TARGET }, d);
        result +=
            getLinkTo(object, new EReference[] { CapellacommonPackage.Literals.STATE_TRANSITION__GUARD },
                d);
        result +=
            getSetTo(object, new EAttribute[] { CapellacommonPackage.Literals.STATE_TRANSITION__KIND },
                d);

      }

      if (object instanceof FunctionalExchange) {
        result +=
            getLinkTo(object,
                new EReference[] { FaPackage.Literals.FUNCTIONAL_EXCHANGE__EXCHANGED_ITEMS, FaPackage.Literals.FUNCTIONAL_EXCHANGE__CATEGORIES }, d);
      }

      if (object instanceof AbstractTrace) {
        result +=
            getLinkTo(object, new EReference[] { ModellingcorePackage.Literals.ABSTRACT_TRACE__SOURCE_ELEMENT,
                                                  ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT }, d);
      }
      if (object instanceof InterfaceUse) {
        result +=
            getLinkTo(object, new EReference[] { CsPackage.Literals.INTERFACE_USE__INTERFACE_USER, CsPackage.Literals.INTERFACE_USE__USED_INTERFACE }, d);
      }
      if (object instanceof InterfaceImplementation) {
        result +=
            getLinkTo(object, new EReference[] { CsPackage.Literals.INTERFACE_IMPLEMENTATION__INTERFACE_IMPLEMENTOR,
                                                  CsPackage.Literals.INTERFACE_IMPLEMENTATION__IMPLEMENTED_INTERFACE }, d);
      }

      return result;

    }

    /**
     * @param ce
     * @param reference
     * @return
     */
    private String getSetTo(EObject ce, EAttribute[] attributes, String sep) {

      String result = "";
      boolean isSet = false;
      int i = 0;
      for (EAttribute attribute : attributes) {
        Object source = ce.eGet(attribute);
        if ((source != null) && !((source instanceof EList) && (((EList) source).size() == 0))) {

          if (!isSet) {
            result = sep + NLS.bind("> Set ''{0}'' to ", EObjectHelper.getInstance().getName(ce));
            isSet = true;
          }
          result +=
              NLS.bind("''{1}'' [{2}]",
                  new Object[] { EObjectHelper.getInstance().getName(ce), EObjectHelper.getInstance().getName(source), attribute.getName() });
        }
        i++;
        if (i < attributes.length) {
          result += ", ";
        }
      }
      return result;
    }

    /**
     * @param ce
     * @param reference
     * @return
     */
    private String getLinkTo(EObject ce, EReference[] references, String sep) {

      String result = "";
      boolean isSet = false;
      int i = 0;
      for (EReference reference : references) {
        Object source = ce.eGet(reference);
        if ((source != null) && !((source instanceof EList) && (((EList) source).size() == 0))) {

          if (!isSet) {
            result = sep + NLS.bind("> Link ''{0}'' to ", EObjectHelper.getInstance().getName(ce));
            isSet = true;
          }
          result +=
              NLS.bind("''{1}'' [{2}]",
                  new Object[] { EObjectHelper.getInstance().getName(ce), EObjectHelper.getInstance().getName(source), reference.getName() });
        }
        i++;
        if (i < references.length) {
          result += ", ";
        }
      }
      return result;
    }
  }

}
