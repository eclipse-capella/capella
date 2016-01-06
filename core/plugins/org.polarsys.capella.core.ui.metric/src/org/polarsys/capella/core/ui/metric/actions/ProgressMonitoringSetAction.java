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
package org.polarsys.capella.core.ui.metric.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyType;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;
import org.polarsys.capella.core.ui.metric.IImageKeys;
import org.polarsys.capella.core.ui.metric.MetricActivator;
import org.polarsys.capella.core.ui.metric.MetricMessages;
import org.polarsys.capella.core.ui.metric.utils.ProgressMonitoringPropagator;
import org.polarsys.capella.core.ui.metric.utils.SetProgressRunSetup;
import org.polarsys.capella.core.ui.metric.utils.Utils;
import org.polarsys.capella.core.ui.properties.annotations.RepresentationAnnotationHelper;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

public class ProgressMonitoringSetAction extends BaseSelectionListenerAction {
  
  private static final Logger logger = ReportManagerRegistry.getInstance().subscribe("Progress Monitoring"); //$NON-NLS-1$

  private class ProgressSetDialog extends Dialog {
    
    private Label dlgMessage;
    private Label dlgImage;
    
    private Collection<EObject> affectedObjects;

    /** Selected enumeration */
    private EnumerationPropertyLiteral selectedEnumLiteral = null;
    
    private boolean propagateWithoutFiltering = false;
    
    private boolean propagateToRepresentation = false;

    /**
     * Constructor
     */
    protected ProgressSetDialog(Shell parentShell, Collection<EObject> affectedObjects) {
      super(parentShell);
      this.affectedObjects = affectedObjects;
    }

    /**
     * Accessor for selected enumeration literal
     */
    public EnumerationPropertyLiteral getSelectedEnum() {
      return selectedEnumLiteral;
    }
    
    /**
     * 
     * @return Indicates whether to apply filtering when setting the status for selected Capella elements.
     */
    public boolean isPropagateWithoutFiltering() {
      return propagateWithoutFiltering;
    }
    
    /**
     * 
     * @return Indicates whether to set the propagate the status for referenced graphical representations.
     */
    public boolean isPropagateToRepresentations(){
      return propagateToRepresentation;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("synthetic-access")
    @Override
    protected Control createContents(Composite parent) {

      getShell().setText(MetricMessages.progressMonitoring_setAction_dialog_title);
      getShell().setMinimumSize(300, 200);

      Composite composite = new Composite(parent, SWT.NONE);
      composite.setLayout(new GridLayout(2, false));
      composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

      Composite header = new Composite(composite, SWT.NONE);
      header.setLayout(new GridLayout(2, false));
      header.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true, 2, 1));
      
      dlgImage = new Label(header, SWT.NONE);
      dlgImage.setLayoutData(new GridData(GridData.BEGINNING, GridData.BEGINNING, false, false, 1, 1));
      
      dlgMessage = new Label(header, SWT.NONE);
      dlgMessage.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, true, 1, 1));


      new Label(composite, SWT.NONE).setText(MetricMessages.progressMonitoring_setAction_dialog_combo_lbl);

      final Combo combo = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY | SWT.BORDER);
      combo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
      combo.addModifyListener(new ModifyListener() {
        
        @Override
        public void modifyText(ModifyEvent e) {
          selectedEnumLiteral = (EnumerationPropertyLiteral) combo.getData(String.valueOf(combo.getSelectionIndex()));
          if(selectedEnumLiteral == null){
            dlgImage.setImage(JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_WARNING));
            dlgMessage.setText(MetricMessages.progressMonitoring_setAction_dialog_clear_lbl);
          }else{
            dlgImage.setImage(JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_INFO));
            dlgMessage.setText(NLS.bind(MetricMessages.progressMonitoring_setAction_dialog_main_lbl, selectedEnumLiteral.getLabel()));
          }
        }
      });
      
      // Add an empty string, at index 0,  so that the user is able to unset the status
      combo.add("");
      combo.setData(String.valueOf(0), null);
      
      EnumerationPropertyType ept = getEnumerationPropertyType();
      
      // Add the literals
      int i = 1;
      for (EnumerationPropertyLiteral enumLiteral : ept.getOwnedLiterals()) {
        combo.add(enumLiteral.getLabel());
        combo.setData(String.valueOf(i++), enumLiteral);
      }
      
      // Ask for the index to select
      int index = getSelectIndex(combo);
      combo.select(index);
      
      // Propagate without filtering button
      Button propagateWithoutFilteringButton = new Button(composite, SWT.CHECK);
      propagateWithoutFilteringButton.setText(MetricMessages.progressMonitoring_dialog_propagate_button_lbl);
      propagateWithoutFilteringButton.setToolTipText(MetricMessages.progressMonitoring_dialog_propagate_button_tooltip);
      propagateWithoutFilteringButton.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 2, 1));
      propagateWithoutFilteringButton.addSelectionListener(new SelectionAdapter() {
        
        @Override
        public void widgetSelected(SelectionEvent event) {
          propagateWithoutFiltering = ((Button)event.getSource()).getSelection();
        }
      });
      
      // Propagate to representations button
      Button propagateToRepresentationButton = new Button(composite, SWT.CHECK);
      propagateToRepresentationButton.setText(MetricMessages.progressMonitoring_dialog_propagate_to_representation_button_lbl);
      propagateToRepresentationButton.setToolTipText(MetricMessages.progressMonitoring_dialog_propagate_to_representation_button_tooltip);
      propagateToRepresentationButton.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 2, 1));
      propagateToRepresentationButton.addSelectionListener(new SelectionAdapter() {
        @Override
        public void widgetSelected(SelectionEvent event) {
          propagateToRepresentation = ((Button)event.getSource()).getSelection();
        }
      });
      return super.createContents(parent);
    }

    private EnumerationPropertyType getEnumerationPropertyType() {
      EObject semanticElement = null;
      Iterator<EObject> iterator = affectedObjects.iterator();
      while(iterator.hasNext() && semanticElement == null){
        EObject next = iterator.next();
        if(next instanceof CapellaElement){
          semanticElement = (CapellaElement)next;
        }else if(next instanceof DRepresentation){
          semanticElement = Utils.getTarget((DRepresentation)next);
        }
      }
      Assert.isNotNull(semanticElement);
      EnumerationPropertyType ept = CapellaProjectHelper.getEnumerationPropertyType(semanticElement, CapellaProjectHelper.PROGRESS_STATUS_KEYWORD);
      return ept;
    }
    
    private int getSelectIndex(Combo combo) {
      // Collect of statuses from affected objects
      Set<String> labels = new HashSet<String>();
      for(EObject obj : affectedObjects){
        if(obj instanceof CapellaElement && ((CapellaElement)obj).getStatus() != null){
          labels.add(((CapellaElement)obj).getStatus().getLabel());
        }else if(obj instanceof DRepresentation && RepresentationAnnotationHelper.getProgressStatus((DRepresentation)obj) != null){
          labels.add(RepresentationAnnotationHelper.getProgressStatus((DRepresentation)obj));
        }
      }
      // If all affected objects have the same status, return its index
      if(labels.size() == 1){
        int index = combo.indexOf(labels.iterator().next());
        return index != -1 ? index : 0;
      }
      // Else return 0
      return 0;
    }

    @Override
    public boolean close() {
      affectedObjects = null;
      return super.close();
    }
  }

  /**
   * Constructor.
   */
  public ProgressMonitoringSetAction() {
    super(MetricMessages.progressMonitoring_setAction_lbl);
    setImageDescriptor(MetricActivator.getDefault().getImageDescriptor(IImageKeys.IMG_PROGRESS_MONITORING));
  }

  /**
   * @see org.eclipse.jface.action.Action#run()
   */
  @Override
  public void run() {
    // Assume that all selected objects are in the same Capella Project
    final Collection<EObject> selectedObjects = ProgressMonitoringActionsHelper
        .getSelectedEObjects(getStructuredSelection());
    if (selectedObjects.isEmpty()) {
      return;
    }
    
    final SetProgressRunSetup runSetup = getRunSetup(selectedObjects);
    if (runSetup != null) {
      TransactionHelper.getExecutionManager(selectedObjects.iterator().next()).execute(new AbstractReadWriteCommand() {
        @SuppressWarnings("synthetic-access")
        public void run() {
          Collection<EObject> result = ProgressMonitoringPropagator.getInstance().applyPropertiesOn(
              Collections.singletonList(runSetup.getEnumPropertyLiteral()), selectedObjects,
              runSetup.isPropagateWithoutFiltering(), runSetup.isPropagateToRepresentations());
          
          // Capella Elements 
          Collection<EObject> capellaElements = Collections2.filter(result, new Predicate<EObject>() {
            public boolean apply(EObject input) {
              return input instanceof CapellaElement;
            }
          });
          if(capellaElements.size() == 0){
            logger.info(NLS.bind(MetricMessages.progressMonitoring_setAction_nochanges_info, "Capella"));
          }else{
            logger.info(NLS.bind(MetricMessages.progressMonitoring_setAction_changes_info, capellaElements.size(), "Capella"));            
          }
          
          // DRepresentation Elements
          Collection<EObject> representationElements = Collections2.filter(result, new Predicate<EObject>() {
            public boolean apply(EObject input) {
              return input instanceof DRepresentation;
            }
          });
          if(representationElements.size() == 0){
            logger.info(NLS.bind(MetricMessages.progressMonitoring_setAction_nochanges_info, "Representation"));
          }else{
            logger.info(NLS.bind(MetricMessages.progressMonitoring_setAction_changes_info, capellaElements.size(), "Representation"));
          }
        }

        @Override
        public String getName() {
          return MetricMessages.progressMonitoring_setAction_cmd_lbl;
        }
      });
    }
  }
  
  protected SetProgressRunSetup getRunSetup(Collection<EObject> selectedObjects) {
    Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
    final ProgressSetDialog dialog = new ProgressSetDialog(shell, selectedObjects);
    if (dialog.open() == Window.OK) {
      return new SetProgressRunSetup(dialog.getSelectedEnum(), dialog.isPropagateWithoutFiltering(),
          dialog.isPropagateToRepresentations());
    }
    return null;
  }
  
  @Override
  protected boolean updateSelection(IStructuredSelection selection) {
    if (selection != null) {
      boolean enabled = true;
      Set<IProject> projects = new HashSet<IProject>();
      Iterator<?> iterator = selection.iterator();
      while (iterator.hasNext()) {
        Object object = iterator.next();
        enabled &= isEnabled(object);
        projects.add(getProject(object));
      }
      return enabled && projects.size() == 1;
    }
    return false;
  }

  private IProject getProject(Object object) {
    if(object instanceof EObject){
      return getProject(EcoreUtil2.getFile(((EObject)object).eResource()));
    }else if(object instanceof IFile){
      return ((IFile)object).getProject();
    }
    return null;
  }

  private boolean isEnabled(Object selection){
      if (selection instanceof IFile) {
        Session session = SessionHelper.getSession((IFile) selection);
        if ((null != session) && session.isOpen()) {
          Resource resource = session.getSessionResource();
          DAnalysis da = (DAnalysis) EcoreUtil.getObjectByType(resource.getContents(), ViewpointPackage.Literals.DANALYSIS);
          Collection<EObject> models = new ArrayList<EObject>();
          models.addAll(da.getModels());
          for (DAnalysis refDa : da.getReferencedAnalysis()) {
            models.addAll(refDa.getModels());
          }
          
          boolean showProgressAction = false;
          for (EObject model : models) {
            showProgressAction = showProgressAction || ProgressMonitoringPropagator.getInstance().isEnumerationPropertyTypeDefinedForProject(model);
          }
          return showProgressAction;
        }
      }else if(selection instanceof DRepresentation){
        return ProgressMonitoringPropagator.getInstance().isEnumerationPropertyTypeDefinedForProject(Utils.getTarget((DRepresentation)selection));
      }
      else if(selection instanceof EObject){
        return ProgressMonitoringPropagator.getInstance().isEnumerationPropertyTypeDefinedForProject((EObject) selection);
      } 
    return false;
  }
}
