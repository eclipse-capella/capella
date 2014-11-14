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
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.internal.databinding.observable.DelayedObservableValue;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.databinding.swt.WidgetProperties;
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
import org.polarsys.capella.core.data.capellacore.CapellaElement;
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

  private OpaqueExpression _opaqueExpression;
  private Composite _bodyEditorComposite;
  private TableViewer languagesViewer;
  private IObservableList _elements = new WritableList(new ArrayList<OpaqueExpressionElement>(), OpaqueExpressionElement.class); 
  private final PropertyChangeListener bodyListener = new PropertyChangeListener() {
    @Override
    public void propertyChange(PropertyChangeEvent evt_p) {
      syncToModel();
    }
  };
  
  static class OpaqueExpressionElement {
    
    private final PropertyChangeSupport _propertyChangeSupport;
    private final OpaqueExpression _opaqueExpression;
    private final String _language;
    private String _body;

    OpaqueExpressionElement(OpaqueExpression opaqueExpression_p, String language_p, String body_p){
      _language = language_p;
      _body = body_p;
      _propertyChangeSupport = new PropertyChangeSupport(this);
      _opaqueExpression = opaqueExpression_p;
    }

    public void addPropertyChangeListener(String propertyName_p, PropertyChangeListener listener_p) {
      _propertyChangeSupport.addPropertyChangeListener(propertyName_p, listener_p);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
      _propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public OpaqueExpression getOpaqueExpression(){
      return _opaqueExpression;
    }
    
    public String getLanguage(){
      return _language;
    }
    
    public String getDisplayLanguage(){
      int colon = getLanguage().indexOf(':');
      return colon == -1 ? getLanguage() : StringUtils.capitalize(getLanguage().substring(colon + 1));
    }

    /**
     * The language body. Never null.
     */
    public String getBody(){
      return _body;
    }

    /**
     * Set the language body. Never null.
     * @param body_p
     */
    public void setBody(String body_p){
      if (!Objects.equal(body_p, _body)){
        _propertyChangeSupport.firePropertyChange("body", _body, _body = body_p); //$NON-NLS-1$
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean select(Object toTest_p) {
    return true;
  }
  
  private OpaqueExpression getOpaqueExpression(){
    return _opaqueExpression;
  }
  
  private void setOpaqueExpression(OpaqueExpression expression_p){
    _opaqueExpression = expression_p;
  }
  
  
  private IObservableList getElements(){
    return _elements;
  }

  @Override
  public void loadData(CapellaElement element_p){
    super.loadData(element_p);

    if (getOpaqueExpression() != element_p){
      setOpaqueExpression((OpaqueExpression) element_p);
      getElements().clear();
      for (int i = 0; i < Math.min(
          getOpaqueExpression().getLanguages().size(),
          getOpaqueExpression().getBodies().size()); i++){
        getElements().add(new OpaqueExpressionElement(
            getOpaqueExpression(),
            getOpaqueExpression().getLanguages().get(i),
            getOpaqueExpression().getBodies().get(i)));
      }

      for (Object element : getElements()){
        ((OpaqueExpressionElement)element).addPropertyChangeListener("body", bodyListener); //$NON-NLS-1$
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
        while (_elements.size() > _opaqueExpression.getLanguages().size()){
          _elements.remove(_opaqueExpression.getLanguages().size());
        }
      }

    }
  }

  private void syncToModel() {
    TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(_opaqueExpression);
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
  public void createControls(Composite parent_p, TabbedPropertySheetPage aTabbedPropertySheetPage_p) {
    super.createControls(parent_p, aTabbedPropertySheetPage_p);

    Group opaqueExpressionGroup = getWidgetFactory().createGroup(_rootParentComposite, ""); //$NON-NLS-1$

    GridLayout layout = new GridLayout(5, false);
    layout.marginBottom = 2;
    layout.marginLeft = 2;
    layout.marginRight = 2;
    layout.marginTop = 2;
    
    opaqueExpressionGroup.setLayout(layout);
    GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
    gd.horizontalSpan = 2;
    gd.heightHint = 200;
    opaqueExpressionGroup.setLayoutData(gd);
    
    Table table = getWidgetFactory().createTable(opaqueExpressionGroup, SWT.SINGLE);
    languagesViewer = new TableViewer(table);
    languagesViewer.addSelectionChangedListener(new ISelectionChangedListener() {
      @Override
      public void selectionChanged(SelectionChangedEvent event_p) {
        OpaqueExpressionElement newElement = (OpaqueExpressionElement) ((IStructuredSelection)event_p.getSelection()).getFirstElement();
        updateTextArea(newElement);
      }
    });
    
    GridData data = new GridData(SWT.FILL, SWT.FILL, false, true);
    data.minimumHeight=140;
    data.horizontalSpan=4;
    table.setLayoutData(data);
    
    _bodyEditorComposite = getWidgetFactory().createComposite(opaqueExpressionGroup);
    data = new GridData(SWT.FILL, SWT.FILL, true, true);
    _bodyEditorComposite.setLayoutData(data);
    
    Button add = getWidgetFactory().createButton(opaqueExpressionGroup, null, SWT.PUSH);
    add.setImage(ToolkitPlugin.getDefault().getImageRegistry().get(ToolkitPlugin.ADD_ITEM_IMAGE_ID));
    data = new GridData(30, SWT.DEFAULT);
    add.setLayoutData(data);
    
    Button remove = getWidgetFactory().createButton(opaqueExpressionGroup, null, SWT.PUSH);
    remove.setImage(ToolkitPlugin.getDefault().getImageRegistry().get(ToolkitPlugin.REMOVE_IMAGE_ITEM_ID));
    data = new GridData(30, SWT.DEFAULT);
    remove.setLayoutData(data);
    
    remove.addSelectionListener(new SelectionListener() {
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        Object selected = ((IStructuredSelection) languagesViewer.getSelection()).getFirstElement();
        if (selected != null){
          getElements().remove(selected);
          syncToModel();
        }
      }
      @Override
      public void widgetDefaultSelected(SelectionEvent e_p) {
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
      public void widgetSelected(SelectionEvent e_p) {
        IStructuredSelection sel = (IStructuredSelection) languagesViewer.getSelection();
        OpaqueExpressionElement element = (OpaqueExpressionElement) sel.getFirstElement();
        if (element != null){          
          WritableList l =  (WritableList) languagesViewer.getInput();
          int currentIndex = l.indexOf(element);
          if (e_p.getSource() == up){
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
      public void widgetSelected(SelectionEvent e_p) {
        
        InputDialog i = new InputDialog(e_p.widget.getDisplay().getActiveShell(), "Add OpaqueExpression element", "Enter language name", null, new IInputValidator() {
          @Override
          public String isValid(String newText_p) {
            if (newText_p != null && newText_p.trim().isEmpty()){
              return ""; //$NON-NLS-1$
            }
            for (Object o : getElements()){
              if (((OpaqueExpressionElement) o).getDisplayLanguage().trim().equals(newText_p.trim())){
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
          getElements().add(oe);
          languagesViewer.setSelection(new StructuredSelection(oe));
          syncToModel();
        }
      }

      @Override
      public void widgetDefaultSelected(SelectionEvent e_p) {
      }

    });

    ViewerSupport.bind(languagesViewer, _elements, BeanProperties.value("displayLanguage"));

  }

  /**
   * @param firstElement_p
   */
  protected void updateTextArea(OpaqueExpressionElement firstElement_p) {
    for (Control c : _bodyEditorComposite.getChildren()){
      c.dispose();
    }
    _bodyEditorComposite.setLayout(null);
    if (firstElement_p != null){
      LanguageProvider.Registry.INSTANCE.getProviderFor(firstElement_p.getOpaqueExpression(), firstElement_p).createControl(_bodyEditorComposite, getWidgetFactory());
    }
    _bodyEditorComposite.layout(true, true);
  }

  public static abstract class LanguageProvider {
    
    /**
     * Sets a layout on the parent and create the control that allows editing 
     * the body of an opaque expression in a specific language.
     * 
     * @param parent_p
     * @param widgetFactory_p
     */
    public abstract void createControl(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p);
    
    static interface Registry {
      
      public LanguageProvider getProviderFor(OpaqueExpression expression_p, OpaqueExpressionElement element);

      final static Registry INSTANCE = new Registry(){
        
        @Override
        public LanguageProvider getProviderFor(OpaqueExpression expression_p, OpaqueExpressionElement element_p) {
          LanguageProvider result = null;
          if (CapellaLinkedTextConstants.OPAQUE_EXPRESSION_LINKED_TEXT.equals(element_p.getLanguage())){
            result = new LinkedTextLanguageProvider(expression_p, element_p);
          } else {
            result = new DefaultLanguageProvider(element_p);
          }
          return result;
        }
      };
    }
  }
  
  static class DefaultLanguageProvider extends LanguageProvider {

    final OpaqueExpressionElement _element;
    
    public DefaultLanguageProvider(OpaqueExpressionElement element_p){
      _element = element_p;
    }

    @Override
    public void createControl(Composite parent_p, TabbedPropertySheetWidgetFactory factory_p) {
      parent_p.setLayout(new FillLayout());
      
      StyledText text = new StyledText(parent_p, SWT.MULTI | factory_p.getBorderStyle());
      text.setAlwaysShowScrollBars(false);
      text.setText(_element.getBody() == null ? "" : _element.getBody()); //$NON-NLS-1$

      final DataBindingContext context = new DataBindingContext();
      IObservableValue widgetValue = new DelayedObservableValue(100, WidgetProperties.text(SWT.Modify).observe(text));
      IObservableValue modelValue = BeanProperties.value(OpaqueExpressionElement.class, "body").observe(_element); //$NON-NLS-1$
      context.bindValue(widgetValue, modelValue, new UpdateValueStrategy(), null);
      text.addDisposeListener(new DisposeListener() {
        @Override
        public void widgetDisposed(DisposeEvent e_p) {
          context.dispose();
        }
      });
    }
  }

  static class LinkedTextLanguageProvider extends LanguageProvider {
    private final OpaqueExpressionElement _element;
    public LinkedTextLanguageProvider(OpaqueExpression expression_p, OpaqueExpressionElement element_p) {
      _element = element_p;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createControl(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p) {
      parent_p.setLayout(new FillLayout());
      CapellaEmbeddedLinkedTextEditor editor = new CapellaEmbeddedLinkedTextEditor(parent_p, SWT.H_SCROLL | SWT.V_SCROLL | widgetFactory_p.getBorderStyle());
      final CapellaEmbeddedLinkedTextEditorInput input = new CapellaEmbeddedLinkedTextEditorInput(_element.getOpaqueExpression()){
        @Override
        public String getText() {
          return _element.getBody() == null ? "" : _element.getBody(); //$NON-NLS-1$
        }
        @Override
        public void setText(String linkedText_p) {
          _element.setBody(linkedText_p.isEmpty() ? null : linkedText_p);
        }
      };
      editor.getSourceViewer().getTextWidget().addDisposeListener(new DisposeListener() {
        @Override
        public void widgetDisposed(DisposeEvent e_p) {
          input.dispose();
        }
      });
      editor.setInput(input);
    }
  }
}
