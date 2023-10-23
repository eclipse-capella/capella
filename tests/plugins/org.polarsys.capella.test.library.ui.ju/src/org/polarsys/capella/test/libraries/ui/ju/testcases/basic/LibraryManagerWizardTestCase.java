/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.libraries.ui.ju.testcases.basic;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.flexibility.properties.loader.PropertiesLoader;
import org.polarsys.capella.common.flexibility.properties.property.PropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IProperties;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.wizards.loader.RenderersLoader;
import org.polarsys.capella.common.flexibility.wizards.renderer.RendererContext;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.flexibility.wizards.schema.IRenderers;
import org.polarsys.capella.common.flexibility.wizards.ui.PropertyDialog;
import org.polarsys.capella.common.flexibility.wizards.ui.PropertyWizard;
import org.polarsys.capella.common.flexibility.wizards.ui.PropertyWizardPage;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.core.libraries.Messages;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.libraries.properties.FlexibilityIds;
import org.polarsys.capella.core.libraries.properties.LibraryManagerModel;
import org.polarsys.capella.core.libraries.properties.ReferencesProperty;
import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.CommonDiagram;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.context.SessionContext;

/**
 * @author Erwann Traisnel
 */
public class LibraryManagerWizardTestCase extends BasicTestCase {

  private String CDB_DIAGRAM = "[CDB] Data"; //$NON-NLS-1$
  private String CLASS_LIB1 = "_AV7EwGvYEe6ny_c39ZY3KA"; //$NON-NLS-1$

  @SuppressWarnings("nls")
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("MyProject1", "MyLibrary1", "MyLibrary2");
  }

  @SuppressWarnings("nls")
  @Override
  public void test() {
    // -- SCENARIO -- //
    CapellaModel myLib1 = getTestModel("MyLibrary1");
    CapellaModel myLib2 = getTestModel("MyLibrary2");

    Session session = getSession("MyProject1");
    IModel.Edit rootModel = (IModel.Edit) ILibraryManager.INSTANCE.getModel(session.getTransactionalEditingDomain());
    assertNotNull(rootModel);
    SessionContext sessionContext = new SessionContext(session);
    CommonDiagram diagram = CDBDiagram.openDiagram(sessionContext, CDB_DIAGRAM);

    final String modelName = rootModel.getIdentifier().getName();
    IProperties properties = new PropertiesLoader().getProperties(FlexibilityIds.MANAGE_REFERENCES_PROPERTIES);
    LibraryManagerModel managerModel = new LibraryManagerModel(session.getTransactionalEditingDomain(), rootModel);
    IPropertyContext context = new PropertyContext(properties, managerModel);
    IRenderers renderers = new RenderersLoader().getRenderers(properties);
    IRendererContext rendererContext = new RendererContext(renderers, context);

    // Instantiates and initializes the wizard
    PropertyWizard wizard = new PropertyWizard(context, rendererContext) {
      @Override
      public void addPages() {
        PropertyWizardPage page = new PropertyWizardPage("propertiesEditor", getContext(), getRendererContext()); //$NON-NLS-1$
        page.setTitle(modelName + " management");
        page.setDescription(
            "This wizard helps you to define the libraries that are referenced by the current project (first tab).\nYou can also specify among these libraries which ones must be considered in capella query scopes (second tab).");
        addPage(page);
      }
    };
    // Instantiates the wizard container with the wizard and creates it
    PropertyDialog dialog = new PropertyDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), wizard);
    dialog.setHelpAvailable(false);
    dialog.setPageSize(200, 300);
    dialog.setBlockOnOpen(false);
    dialog.create();

    // Remove Library 1 from referenced library
    managerModel.removeReferencedLibrary(myLib1);
    ReferencesProperty referenceProperty = null;
    for (IProperty property : properties.getAllItems()) {
      if (property instanceof ReferencesProperty) {
        referenceProperty = (ReferencesProperty) property;
      }
      break;
    }
    // status should be a warning, telling user to read the doc
    IStatus status = referenceProperty.validate(managerModel, context);
    assertEquals(status.getSeverity(), IStatus.WARNING);
    assertEquals(status.getMessage(), Messages.warningMsg);

    // Confirm library unreferencing
    rootModel.removeReference(myLib1);
    rootModel.removeReference(myLib2);
    session.save(new NullProgressMonitor());

    // Remove the Class lib1 from the diagram
    DSemanticDecorator decorator = diagram.getView(CLASS_LIB1);
    diagram.deleteSemantic((DDiagramElement) decorator);
    properties = new PropertiesLoader().getProperties(FlexibilityIds.MANAGE_REFERENCES_PROPERTIES);
    status = referenceProperty.validate(managerModel, context);

    // Status shall be an error, has session hasn't been saved
    assertEquals(status.getSeverity(), IStatus.ERROR);
    assertEquals(status.getMessage(), Messages.dirtySessionMsg);

    session.save(new NullProgressMonitor());
    properties = new PropertiesLoader().getProperties(FlexibilityIds.MANAGE_REFERENCES_PROPERTIES);
    status = referenceProperty.validate(managerModel, context);

    // Status shall be an error, library have invalid states, please restart Capella
    assertEquals(status.getSeverity(), IStatus.ERROR);
    assertEquals(status.getMessage(), Messages.resourceSetDirtyMsg);

  }
}
