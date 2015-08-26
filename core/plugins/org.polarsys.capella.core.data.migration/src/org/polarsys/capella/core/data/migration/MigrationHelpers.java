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
package org.polarsys.capella.core.data.migration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMIException;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.data.migration.contribution.IMigrationContribution;
import org.polarsys.capella.core.data.migration.contributor.AbstractMigrationContributor;
import org.xml.sax.Attributes;

/**
 * 
 */
public class MigrationHelpers implements IMigrationContribution {

  private static MigrationHelpers INSTANCE = null;

  private ArrayList<AbstractMigrationContributor> contributors = null;

  private ArrayList<IMigrationContribution> migrations = null;

  static final String MIGRATION_CONTRIBUTIONS_ID = "org.polarsys.capella.core.data.migration.contributions"; //$NON-NLS-1$
  static final String MIGRATION_CONTRIBUTORS_ID = "org.polarsys.capella.core.data.migration.contributors"; //$NON-NLS-1$

  public MigrationHelpers() {
    migrations = new ArrayList<IMigrationContribution>();
    for (IConfigurationElement configElement : ExtensionPointHelper.getConfigurationElements(
        "org.polarsys.capella.core.data.migration", "migrationContributions")) {
      IMigrationContribution contribution = (IMigrationContribution) ExtensionPointHelper.createInstance(configElement,
          ExtensionPointHelper.ATT_CLASS);
      migrations.add(contribution);
    }

    contributors = new ArrayList<AbstractMigrationContributor>();
    for (IConfigurationElement configElement : ExtensionPointHelper.getConfigurationElements(
        "org.polarsys.capella.core.data.migration", "migrationContributors")) {
      AbstractMigrationContributor contributor = (AbstractMigrationContributor) ExtensionPointHelper.createInstance(
          configElement, ExtensionPointHelper.ATT_CLASS);
      contributors.add(contributor);
    }
  }

  public void trigger(IResource resource, Shell shell, boolean skipConfirmation, boolean checkVersion, String[] kinds) {
    trigger(resource, shell, true, skipConfirmation, checkVersion, kinds);
  }

  public void trigger(IResource resource, Shell shell, boolean runInJob, boolean skipConfirmation,
      boolean checkVersion, String[] kinds) {
    LinkedHashSet<IResource> files = new LinkedHashSet<>();
    Collection<AbstractMigrationContributor> currentContributors = new LinkedList<AbstractMigrationContributor>();

    // Retrieve all extensions for the selected kind of migrations
    for (AbstractMigrationContributor contributor : contributors) {
      for (String kind : kinds) {
        if (contributor.getKind().equals(kind)) {
          currentContributors.add(contributor);
        }
      }
    }

    // Retrieve all files that will need to be migrated
    for (AbstractMigrationContributor contributor : currentContributors) {
      files.addAll(contributor.getMigrableFiles(resource));
    }

    MigrationContext context = new MigrationContext();
    context.setName(Messages.MigrationAction_Title);
    context.setShell(shell);
    context.setSkipConfirmation(skipConfirmation);

    // Retrieve all commands that will need to be run to perform the
    // migration
    LinkedList<AbstractMigrationRunnable> runnables = new LinkedList<AbstractMigrationRunnable>();
    for (IResource file : files) {
      for (AbstractMigrationContributor contributor : currentContributors) {
        if (contributor.isValidResource(file)) {
          AbstractMigrationRunnable runnable = contributor.getRunnable((IFile) file);
          runnables.add(runnable);
        }
      }
    }

    // Run the commands in jobs or not
    new MigrationJobScheduler().run(runnables, context, runInJob, checkVersion);
  }

  public static MigrationHelpers getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new MigrationHelpers();
    }
    return INSTANCE;
  }

  @Override
  public boolean isValidURI(URI uri, MigrationContext context) {
    for (IMigrationContribution migration : migrations) {
      return migration.isValidURI(uri, context);
    }
    return false;
  }

  @Override
  public void dispose(ExecutionManager manager, ResourceSet resourceSet, MigrationContext context) {
    for (IMigrationContribution migration : migrations) {
      try {
        migration.dispose(manager, resourceSet, context);
      } catch (Exception e) {
        e.printStackTrace();
        // nothing here
      }
    }
  }

  @Override
  public void dispose() {
    for (IMigrationContribution migration : migrations) {
      try {
        migration.dispose();
      } catch (Exception e) {
        e.printStackTrace();
        // nothing here
      }
    }
  }

  @Override
  public String getFeatureName(String prefix, String name, boolean isElement, EObject peekObject, String value,
      Resource resource, MigrationContext context) {
    for (IMigrationContribution migration : migrations) {
      String newFeatureName = migration.getFeatureName(prefix, name, isElement, peekObject, value, resource, context);
      if (newFeatureName != null) {
        return newFeatureName;
      }
    }
    return null;
  }

  @Override
  public EStructuralFeature getFeature(EObject peekObject, EStructuralFeature feature, Resource resource,
      MigrationContext context) {
    for (IMigrationContribution migration : migrations) {
      EStructuralFeature newFeature = migration.getFeature(peekObject, feature, resource, context);
      if (newFeature != null) {
        return newFeature;
      }
    }

    return null;
  }

  @Override
  public String getQName(EObject peekObject, String typeQName, EStructuralFeature feature, Resource resource,
      XMLHelper helper, MigrationContext context) {
    for (IMigrationContribution migration : migrations) {
      String qName = migration.getQName(peekObject, typeQName, feature, resource, helper, context);
      if (qName != null) {
        return qName;
      }
    }
    return null;
  }

  @Override
  public EFactory getEFactory(String prefix, Resource resource, MigrationContext context) {
    for (IMigrationContribution migration : migrations) {
      EFactory newEFactory = migration.getEFactory(prefix, resource, context);
      if (newEFactory != null) {
        return newEFactory;
      }
    }
    return null;
  }

  @Override
  public void updateElement(EObject peekObject, String typeName, EObject result, EStructuralFeature feature,
      Resource resource, MigrationContext context) {
    for (IMigrationContribution migration : migrations) {
      migration.updateElement(peekObject, typeName, result, feature, resource, context);
    }
  }

  @Override
  public IStatus preMigrationExecute(IResource fileToMigrate, MigrationContext context, boolean checkVersion) {
    for (IMigrationContribution migration : migrations) {
      IStatus status = migration.preMigrationExecute(fileToMigrate, context, checkVersion);
      if (!status.isOK()) {
        return status;
      }
    }
    return Status.OK_STATUS;
  }

  @Override
  public void postMigrationExecute(ExecutionManager executionManager, ResourceSet resourceSet, MigrationContext context) {

    // Browse once each resources and migrate them through contributions
    for (Resource resource : resourceSet.getResources()) {
      Collection<IMigrationContribution> enabledContributions = new LinkedHashSet<IMigrationContribution>();

      for (IMigrationContribution migration : migrations) {
        if (migration.isValidURI(resource.getURI(), context)) {
          enabledContributions.add(migration);
        }
      }

      TreeIterator<Notifier> iterator = resourceSet.getAllContents();
      while (iterator.hasNext()) {
        Notifier currentElement = iterator.next();

        for (IMigrationContribution migration : migrations) {
          migration.unaryPostMigrationExecute(currentElement, context);
        }
      }

      for (IMigrationContribution migration : enabledContributions) {
        migration.unaryEndPostMigrationExecute(executionManager, resource, context);
      }

    }

    // Trigger a postMigrationExecute on all contributions
    for (IMigrationContribution migration : migrations) {
      migration.postMigrationExecute(executionManager, resourceSet, context);
    }
  }

  @Override
  public void postMigrationExecuteCommands(ExecutionManager executionManager, ResourceSet resourceSet,
      MigrationContext context) {
    for (IMigrationContribution migration : migrations) {
      migration.postMigrationExecuteCommands(executionManager, resourceSet, context);
    }
  }

  /**
   * @param attrib
   * @param value
   * @return
   */
  @Override
  public String getNSPrefix(String prefix, MigrationContext context) {
    for (IMigrationContribution migration : migrations) {
      String newPrefix = migration.getNSPrefix(prefix, context);
      if (newPrefix != null) {
        return newPrefix;
      }
    }

    return null;
  }

  @Override
  public String getNSURI(String prefix, String nsUri, MigrationContext context) {
    for (IMigrationContribution migration : migrations) {
      String newPrefix = migration.getNSURI(prefix, nsUri, context);
      if (newPrefix != null) {
        return newPrefix;
      }
    }
    return null;
  }

  @Override
  public Object getValue(EObject peekObject, EStructuralFeature feature, Object value, int position, Resource resource,
      MigrationContext context) {
    for (IMigrationContribution migration : migrations) {
      Object newValue = migration.getValue(peekObject, feature, value, position, resource, context);
      if (newValue != null) {
        return newValue;
      }
    }
    return null;
  }

  /**
   * @param packageRegistry
   */
  @Override
  public void contributePackageRegistry(org.eclipse.emf.ecore.EPackage.Registry packageRegistry,
      MigrationContext context) {
    for (IMigrationContribution migration : migrations) {
      migration.contributePackageRegistry(packageRegistry, context);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.polarsys.capella.core.data.migration.contribution.IMigrationContribution
   * #isValidResource(org.eclipse.core.resources .IResource)
   */
  @Override
  public boolean isValidResource(IResource uri, MigrationContext context) {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * @param executionManager
   * @param resource
   * @param monitor
   */
  @Override
  public void preSaveResource(ExecutionManager executionManager, Resource resource, MigrationContext context) {
    for (IMigrationContribution migration : migrations) {
      migration.preSaveResource(executionManager, resource, context);
    }
  }

  /**
   * @param error
   * @param context
   */
  public void onOutOfMemoryError(OutOfMemoryError error, final MigrationContext context) {

    if (!context.isSkipConfirmation()) {
      Shell shell = context.getShell();
      if ((shell != null) && !(shell.isDisposed())) {
        Display display = shell.getDisplay();
        if ((display != null) && !(display.isDisposed())) {
          display.syncExec(new Runnable() {
            public void run() {
              MessageDialog.openError(context.getShell(),
                  Messages.ECore2ECoreMigrationAction_Migration_OutOfMemoryError_Title,
                  Messages.ECore2ECoreMigrationAction_Migration_OutOfMemoryError_Description);
            }
          });
        }
      }
    }
  }

  @Override
  public void unaryPostMigrationExecute(Notifier currentElement, MigrationContext context) {
    // Nothing here
  }

  @Override
  public void unaryEndPostMigrationExecute(ExecutionManager executionManager, Resource resource,
      MigrationContext context) {
    // Nothing here
  }

  /**
   * @param resource
   * @param context
   */
  public void newResource(Resource resource, MigrationContext context) {
    for (IMigrationContribution migration : migrations) {
      migration.newResource(resource, context);
    }
  }

  public String getHandleProxy(InternalEObject proxy, String uriLiteral, Resource resource, MigrationContext context) {
    for (IMigrationContribution migration : migrations) {
      String result = migration.getHandleProxy(proxy, uriLiteral, resource, context);
      if (result != null) {
        return result;
      }
    }
    return null;
  }

  /**
   * @param e
   * @param resource
   * @param context
   * @return
   */
  public IStatus handleError(XMIException e, Resource resource, MigrationContext context) {
    for (IMigrationContribution migration : migrations) {
      IStatus result = migration.handleError(e, resource, context);
      if (result != null) {
        return result;
      }
    }
    return null;
  }

  /**
   * @param peekEObject
   * @param attribs
   * @param uri
   * @param localName
   * @param name
   * @param resource
   * @param context
   */
  public void endElement(EObject peekEObject, Attributes attribs, String uri, String localName, String name,
      Resource resource, MigrationContext context) {
    for (IMigrationContribution migration : migrations) {
      migration.endElement(peekEObject, attribs, uri, localName, name, resource, context);
    }
  }

  /**
   * @param peekObject
   * @param feature
   * @param value
   * @param position
   * @param resource
   * @param context
   * @return
   */
  public boolean ignoreSetFeatureValue(EObject peekObject, EStructuralFeature feature, Object value, int position,
      XMLResource resource, MigrationContext context) {
    for (IMigrationContribution migration : migrations) {
      if (migration.ignoreSetFeatureValue(peekObject, feature, value, position, resource, context)) {
        return true;
      }
    }

    return false;
  }

  /**
   * @param prefix
   * @param name
   * @param isElement
   * @param peekObject
   * @param value
   * @param resource
   * @param context
   * @return
   */
  public boolean ignoreUnknownFeature(String prefix, String name, boolean isElement, EObject peekObject, String value,
      XMLResource resource, MigrationContext context) {
    for (IMigrationContribution migration : migrations) {
      if (migration.ignoreUnknownFeature(prefix, name, isElement, peekObject, value, resource, context)) {
        return true;
      }
    }
    return false;
  }

  /**
   * @param resource
   * @param result
   */
  public void createdXMLHelper(XMLResource resource, XMLHelper result) {
    for (IMigrationContribution migration : migrations) {
      migration.createdXMLHelper(resource, result);
    }

  }

}
