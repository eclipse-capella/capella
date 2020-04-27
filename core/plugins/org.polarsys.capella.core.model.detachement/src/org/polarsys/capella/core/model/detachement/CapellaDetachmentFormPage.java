/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.model.detachement;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.core.libraries.ui.views.libraryManager.LibraryContentProvider;
import org.polarsys.capella.core.libraries.ui.views.libraryManager.referencesManager.ReferencesLabelProvider;
import org.polarsys.kitalpha.model.common.scrutiny.analyzer.ModelScrutinyException;
import org.polarsys.kitalpha.model.common.scrutiny.interfaces.IScrutinize;
import org.polarsys.kitalpha.model.common.scrutiny.registry.ModelScrutinyRegistry.RegistryElement;
import org.polarsys.kitalpha.model.detachment.ui.page.AbstractDetachmentFormPage;

/**
 *
 */
public class CapellaDetachmentFormPage extends AbstractDetachmentFormPage {

  protected TableViewer tableViewer;
  private Map<IModel, Boolean> model;

  public CapellaDetachmentFormPage(FormEditor editor, String id, String title) {
    super(editor, id, title);
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  protected void createFormContent(IManagedForm managedForm) {
    final FormToolkit tk = managedForm.getToolkit();
    final ScrolledForm scrolledForm = managedForm.getForm();
    scrolledForm.setText(Messages.CAPELLA_LIBRARIES);
    scrolledForm.setExpandHorizontal(true);

    Composite composite = scrolledForm.getBody();
    composite.setLayout(new FillLayout());

    Section section = tk.createSection(composite, Section.TITLE_BAR | Section.DESCRIPTION);
    section.setText(Messages.CAPELLA_LIBRARIES);
    section.setDescription(Messages.CAPELLA_LIBRARIES_DESC);
    Composite sectionClient = tk.createComposite(section);
    GridLayoutFactory.fillDefaults().margins(10, 5).numColumns(1).applyTo(sectionClient);
    GridDataFactory.fillDefaults().grab(true, true).applyTo(sectionClient);
    GridLayoutFactory.fillDefaults().applyTo(sectionClient);

    tableViewer = new TableViewer(sectionClient, SWT.CHECK | SWT.BORDER);
    tableViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    tableViewer.setLabelProvider(new ReferencesLabelProvider());
    tableViewer.setContentProvider(new LibraryContentProvider());
    tableViewer.getTable().setLinesVisible(false);
    tableViewer.getTable().addListener(SWT.Selection, new Listener() {
      public void handleEvent(Event event) {
        if (event.detail == SWT.CHECK && model != null) {
          IModel library = (IModel) event.item.getData();
          if (((TableItem) event.item).getChecked()) {
            model.put(library, Boolean.TRUE);
          } else {
            model.put(library, Boolean.FALSE);
          }
        }
      }
    });

    section.setClient(sectionClient);

    try {
      RegistryElement regElt = getScrutinyAnalysis().getRegistryElement(getFinderID());
      if (regElt != null) {
        Iterator<IScrutinize> it = regElt.getFinders().iterator();
        while (it.hasNext()) {
          IScrutinize<Map<IModel, Boolean>, String> next = it.next();
          model = next.getAnalysisResult();
          tableViewer.setInput(model.keySet().toArray());
        }
      }
    } catch (ModelScrutinyException e) {
      e.printStackTrace();
    }
  }
}
