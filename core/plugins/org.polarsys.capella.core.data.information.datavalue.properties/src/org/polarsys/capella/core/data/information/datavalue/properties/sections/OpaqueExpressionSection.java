/*******************************************************************************
 * Copyright (c) 2006, 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.information.datavalue.properties.sections;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.typed.BeanProperties;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.internal.databinding.observable.DelayedObservableValue;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerSupport;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.core.data.core.properties.sections.NamedElementSection;
import org.polarsys.capella.core.data.information.datavalue.OpaqueExpression;
import org.polarsys.capella.core.linkedtext.ui.CapellaEmbeddedLinkedTextEditor;
import org.polarsys.capella.core.linkedtext.ui.CapellaEmbeddedLinkedTextEditorInput;
import org.polarsys.capella.core.linkedtext.ui.CapellaLinkedTextConstants;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.core.ui.properties.IImageKeys;
import org.polarsys.capella.core.ui.toolkit.ToolkitPlugin;

import com.google.common.base.Objects;

public class OpaqueExpressionSection extends NamedElementSection {

  private OpaqueExpression opaqueExpression;
  private Composite bodyEditorComposite;
  private TableViewer languagesViewer;
  private IObservableList elements = new WritableList(new ArrayList<OpaqueExpressionElement>(), OpaqueExpressionElement.class); 
  private final PropertyChangeListener bodyListener = new PropertyChangeListener() {
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
      syncToModel();
    }
  };
  private Group opaqueExpressionGroup;
  private Button add;
  private Button remove;
  
  static class OpaqueExpressionElement {
    
    private final PropertyChangeSupport propertyChangeSupport;
    private final OpaqueExpression opaqueExpr;
    private final String language;
    private String body;

    OpaqueExpressionElement(OpaqueExpression opaqueExpression, String language, String body){
      this.language = language;
      this.body = body;
      this.propertyChangeSupport = new PropertyChangeSupport(this);
      this.opaqueExpr = opaqueExpression;
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
      propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
      propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public OpaqueExpression getOpaqueExpression(){
      return opaqueExpr;
    }
    
    public String getLanguage(){
      return language;
    }
    
    public String getDisplayLanguage(){
      int colon = getLanguage().indexOf(':');
      return colon == -1 ? getLanguage() : StringUtils.capitalize(getLanguage().substring(colon + 1));
    }

    /**
     * The language body. Never null.
     */
    public String getBody(){
      return body;
    }

    /**
     * Set the language body. Never null.
     * @param body
     */
    public void setBody(String body){
      if (!Objects.equal(body, this.body)){
        propertyChangeSupport.firePropertyChange("body", this.body, this.body = body); //$NON-NLS-1$
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean select(Object toTest) {
    return toTest instanceof OpaqueExpression;
  }
  
  private OpaqueExpression getOpaqueExpression(){
    return opaqueExpression;
  }
  
  private void setOpaqueExpression(OpaqueExpression expression){
    opaqueExpression = expression;
  }
  
  
  private IObservableList getElements(){
    return elements;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject element) {
    super.loadData(element);

    if (getOpaqueExpression() != element){
      setOpaqueExpression((OpaqueExpression) element);
      getElements().clear();
      for (int i = 0; i < Math.min(
          getOpaqueExpression().getLanguages().size(),
          getOpaqueExpression().getBodies().size()); i++){
        getElements().add(new OpaqueExpressionElement(
            getOpaqueExpression(),
            getOpaqueExpression().getLanguages().get(i),
            getOpaqueExpression().getBodies().get(i)));
      }

      for (Object elt : getElements()){
        ((OpaqueExpressionElement)elt).addPropertyChangeListener("body", bodyListener); //$NON-NLS-1$
      }

      if (getElements().size() > 0){
        languagesViewer.setSelection(new StructuredSelection(getElements().get(0)));
      }
    } else {

      // Reorder and update elements
      for (int i = 0; i < Math.min(
            getOpaqueExpression().getLanguages().size(),
            getOpaqueExpression().getBodies().size()); i++){

        final String lang = getOpaqueExpression().getLanguages().get(i);
        final String body = getOpaqueExpression().getBodies().get(i);

        int oldIndex = -1;
        for (int j = 0; j < getElements().size(); j++){
          if (((OpaqueExpressionElement) getElements().get(j)).getLanguage().equals(lang)){
            oldIndex = j;
            break;
          }
        }
        if (oldIndex == -1){
          getElements().add(i, new OpaqueExpressionElement(getOpaqueExpression(), lang, body));
        } else {
          getElements().move(oldIndex, i);
          ((OpaqueExpressionElement) getElements().get(i)).setBody(body);
        }

        // Now, the trailing elements have to be removed: These are not in the model any more
        while (elements.size() > opaqueExpression.getLanguages().size()){
          elements.remove(opaqueExpression.getLanguages().size());
        }
      }
    }
    updateRemoveButtonState(remove);
  }

  private void syncToModel() {
    TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(opaqueExpression);
    RecordingCommand c = new RecordingCommand(domain, "Edit OpaqueExpression") {
      
      final OpaqueExpression _affected = getOpaqueExpression();

      @Override
      public Collection<?> getAffectedObjects() {
        return Collections.singleton(_affected);
      }

      @Override
      protected void doExecute() {

        for (int i = 0; i < getElements().size(); i++){
          OpaqueExpressionElement element = (OpaqueExpressionElement) getElements().get(i);
          int oldIndex = -1;
          for (int j = 0; i < getOpaqueExpression().getLanguages().size(); j++){
            if (getOpaqueExpression().getLanguages().get(j).equals(element.getLanguage())){
              oldIndex = j;
              break;
            }
          }
          if (oldIndex == -1){
            getOpaqueExpression().getLanguages().add(i, element.getLanguage());
            getOpaqueExpression().getBodies().add(i, element.getBody());
          } else {
            getOpaqueExpression().getLanguages().move(i, oldIndex);
            getOpaqueExpression().getBodies().set(i, element.getBody());
          }
        }
        while (getElements().size() < getOpaqueExpression().getLanguages().size()){
          getOpaqueExpression().getLanguages().remove(getElements().size());
        }
        while (getElements().size() < getOpaqueExpression().getBodies().size()){
          getOpaqueExpression().getBodies().remove(getElements().size());
        }
      }
    };
    domain.getCommandStack().execute(c);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    GridLayout layout = new GridLayout(5, false);
    layout.marginBottom = 2;
    layout.marginLeft = 2;
    layout.marginRight = 2;
    layout.marginTop = 2;
    
    opaqueExpressionGroup = getWidgetFactory().createGroup(parent, ""); //$NON-NLS-1$
    opaqueExpressionGroup.setLayout(layout);
    GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
    gd.horizontalSpan = 2;
    gd.heightHint = 200;
    gd.widthHint = 150;
    opaqueExpressionGroup.setLayoutData(gd);
    
    Table table = getWidgetFactory().createTable(opaqueExpressionGroup, SWT.SINGLE);
    languagesViewer = new TableViewer(table);
    languagesViewer.addSelectionChangedListener(new ISelectionChangedListener() {
      @Override
      public void selectionChanged(SelectionChangedEvent event) {
        OpaqueExpressionElement newElement = (OpaqueExpressionElement) ((IStructuredSelection)event.getSelection()).getFirstElement();
        updateTextArea(newElement);
      }
    });
    
    GridData data = new GridData(SWT.FILL, SWT.FILL, false, true);
    data.minimumHeight=140;
    data.horizontalSpan=4;
    table.setLayoutData(data);
    
    bodyEditorComposite = getWidgetFactory().createComposite(opaqueExpressionGroup);
    data = new GridData(SWT.FILL, SWT.FILL, true, true);
    bodyEditorComposite.setLayoutData(data);
    
    // add button
    add = getWidgetFactory().createButton(opaqueExpressionGroup, null, SWT.PUSH);
    add.setImage(ToolkitPlugin.getDefault().getImageRegistry().get(ToolkitPlugin.ADD_ITEM_IMAGE_ID));
    data = new GridData(30, SWT.DEFAULT);
    add.setLayoutData(data);
    
    // remove button
    remove = getWidgetFactory().createButton(opaqueExpressionGroup, null, SWT.PUSH);
    remove.setImage(ToolkitPlugin.getDefault().getImageRegistry().get(ToolkitPlugin.REMOVE_IMAGE_ITEM_ID));
    data = new GridData(30, SWT.DEFAULT);
    remove.setLayoutData(data);
    updateRemoveButtonState(remove);
    
    remove.addSelectionListener(new SelectionListener() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        Object selected = ((IStructuredSelection) languagesViewer.getSelection()).getFirstElement();
        if (selected != null){
          getElements().remove(selected);
          syncToModel();
          updateRemoveButtonState(remove);
        }
      }

      @Override
      public void widgetDefaultSelected(SelectionEvent e) {
      }
    });
    
    
    final Button down = getWidgetFactory().createButton(opaqueExpressionGroup, null, SWT.PUSH);
    down.setImage(CapellaUIPropertiesPlugin.getDefault().getImage(IImageKeys.IMG_ARROW_DOWN));
    data = new GridData(30, SWT.DEFAULT);
    down.setLayoutData(data);
    
    final Button up = getWidgetFactory().createButton(opaqueExpressionGroup, null, SWT.PUSH);
    up.setImage(CapellaUIPropertiesPlugin.getDefault().getImage(IImageKeys.IMG_ARROW_UP));
    data = new GridData(30, SWT.DEFAULT);
    up.setLayoutData(data);

    SelectionListener upDown = new SelectionAdapter() {
      /**
       * {@inheritDoc}
       */
      @Override
      public void widgetSelected(SelectionEvent e) {
        IStructuredSelection sel = (IStructuredSelection) languagesViewer.getSelection();
        OpaqueExpressionElement element = (OpaqueExpressionElement) sel.getFirstElement();
        if (element != null){          
          WritableList l =  (WritableList) languagesViewer.getInput();
          int currentIndex = l.indexOf(element);
          if (e.getSource() == up){
            if (currentIndex > 0){
              l.move(currentIndex, currentIndex - 1);
            }
          } else {
            if (currentIndex < l.size() - 1){
              l.move(currentIndex, currentIndex + 1);
            }
          }
          syncToModel();
        }
      }
    };
    up.addSelectionListener(upDown);
    down.addSelectionListener(upDown);

    add.addSelectionListener(new SelectionListener(){

      @Override
      public void widgetSelected(SelectionEvent e) {
        
        InputDialog i = new InputDialog(e.widget.getDisplay().getActiveShell(), "Add OpaqueExpression element", "Enter language name", null, new IInputValidator() {
          @Override
          public String isValid(String newText) {
            if (newText != null && newText.trim().isEmpty()){
              return ""; //$NON-NLS-1$
            }
            for (Object o : getElements()){
              if (newText != null && ((OpaqueExpressionElement) o).getDisplayLanguage().trim().equals(newText.trim())){
                return "Language is already used";
              }
            }
            return null;
          }
        });

        if (i.open() == Window.OK){
          String language = i.getValue().trim();
          if (language.equals("LinkedText")){
            language = CapellaLinkedTextConstants.OPAQUE_EXPRESSION_LINKED_TEXT;
          }
          
          OpaqueExpressionElement oe = new OpaqueExpressionElement(getOpaqueExpression(), language, ""); //$NON-NLS-1$
          oe.addPropertyChangeListener("body", bodyListener);
          getElements().add(oe);
          
          languagesViewer.setSelection(new StructuredSelection(oe));
          syncToModel();
        }
        
        updateRemoveButtonState(remove);
      }

      @Override
      public void widgetDefaultSelected(SelectionEvent e) {
      }

    });

    ViewerSupport.bind(languagesViewer, elements, BeanProperties.value("displayLanguage"));

  }

  protected void updateRemoveButtonState(final Button remove) {
    if (getElements().size() > 1)
      remove.setEnabled(true);
    else
      remove.setEnabled(false);
  }
  
  /**
   * @param firstElement
   */
  protected void updateTextArea(OpaqueExpressionElement firstElement) {
    for (Control c : bodyEditorComposite.getChildren()){
      c.dispose();
    }
    bodyEditorComposite.setLayout(null);
    if (firstElement != null){
      LanguageProvider.Registry.INSTANCE.getProviderFor(firstElement.getOpaqueExpression(), firstElement).createControl(bodyEditorComposite, getWidgetFactory());
    }
    bodyEditorComposite.layout(true, true);
  }

  public static abstract class LanguageProvider {
    
    /**
     * Sets a layout on the parent and create the control that allows editing 
     * the body of an opaque expression in a specific language.
     * 
     * @param parent
     * @param widgetFactory
     */
    public abstract void createControl(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory);
    
    static interface Registry {
      
      public LanguageProvider getProviderFor(OpaqueExpression expression, OpaqueExpressionElement element);

      final static Registry INSTANCE = new Registry(){
        
        @Override
        public LanguageProvider getProviderFor(OpaqueExpression expression, OpaqueExpressionElement element) {
          LanguageProvider result = null;
          if (CapellaLinkedTextConstants.OPAQUE_EXPRESSION_LINKED_TEXT.equals(element.getLanguage())){
            result = new LinkedTextLanguageProvider(expression, element);
          } else {
            result = new DefaultLanguageProvider(element);
          }
          return result;
        }
      };
    }
  }
  
  static class DefaultLanguageProvider extends LanguageProvider {

    final OpaqueExpressionElement _element;
    
    public DefaultLanguageProvider(OpaqueExpressionElement element){
      _element = element;
    }

    @Override
    public void createControl(Composite parent, TabbedPropertySheetWidgetFactory factory) {
      parent.setLayout(new FillLayout());
      
      StyledText text = new StyledText(parent, SWT.MULTI | factory.getBorderStyle());
      text.setAlwaysShowScrollBars(false);
      text.setText(_element.getBody() == null ? "" : _element.getBody()); //$NON-NLS-1$

      final DataBindingContext context = new DataBindingContext();
      IObservableValue widgetValue = new DelayedObservableValue(100, WidgetProperties.text(SWT.Modify).observe(text));
      IObservableValue modelValue = BeanProperties.value(OpaqueExpressionElement.class, "body").observe(_element); //$NON-NLS-1$
      context.bindValue(widgetValue, modelValue, new UpdateValueStrategy(), null);
      text.addDisposeListener(new DisposeListener() {
        @Override
        public void widgetDisposed(DisposeEvent e) {
          context.dispose();
        }
      });
    }
  }

  static class LinkedTextLanguageProvider extends LanguageProvider {
    private final OpaqueExpressionElement _element;
    public LinkedTextLanguageProvider(OpaqueExpression expression, OpaqueExpressionElement element) {
      _element = element;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createControl(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
      parent.setLayout(new FillLayout());
      CapellaEmbeddedLinkedTextEditor editor = new CapellaEmbeddedLinkedTextEditor(parent, SWT.H_SCROLL | SWT.V_SCROLL | widgetFactory.getBorderStyle());
      final CapellaEmbeddedLinkedTextEditorInput input = new CapellaEmbeddedLinkedTextEditorInput(_element.getOpaqueExpression()){
        @Override
        public String getText() {
          return _element.getBody() == null ? "" : _element.getBody(); //$NON-NLS-1$
        }
        @Override
        public void setText(String linkedText) {
          _element.setBody(linkedText);
        }
      };
      editor.getSourceViewer().getTextWidget().addDisposeListener(new DisposeListener() {
        @Override
        public void widgetDisposed(DisposeEvent e) {
          input.dispose();
        }
      });
      editor.setInput(input);
    }
  }
}
