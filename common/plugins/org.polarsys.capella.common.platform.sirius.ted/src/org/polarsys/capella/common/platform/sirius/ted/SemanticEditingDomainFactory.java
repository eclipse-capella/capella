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
package org.polarsys.capella.common.platform.sirius.ted;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.commands.operations.UndoContext;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.AbstractCommand.NonDirtying;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory.Registry;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.InternalTransaction;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionChangeRecorder;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.emf.workspace.ResourceUndoContext;
import org.eclipse.emf.workspace.WorkspaceEditingDomainFactory;
import org.polarsys.capella.common.ef.domain.IEditingDomainListener;
import org.polarsys.capella.common.ef.internal.command.WorkspaceCommandStackImpl;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;

/**
 * Capella editing domain factory.<br>
 */
public class SemanticEditingDomainFactory extends WorkspaceEditingDomainFactory {
  /**
   * Hidden resource where a new element is stored.<br>
   * This allows the {@link ECrossReferenceAdapter} to find pending elements during a command, that are not yet attached to a valid model tree (ie another
   * semantic resource).
   */
  //  public class HoldingResource extends ResourceImpl {
  //    /**
  //     * Constructor.
  //     */
  //    public HoldingResource() {
  //      // Non-sense URI that is interpreted as a Capella one.
  //      super(URI.createURI("capella://crossreference.melodymodeller")); //$NON-NLS-1$
  //    }
  //
  //    /**
  //     * @see org.eclipse.emf.ecore.resource.impl.ResourceImpl#doSave(java.io.OutputStream, java.util.Map)
  //     */
  //    @Override
  //    protected void doSave(OutputStream outputStream_p, Map<?, ?> options_p) throws IOException {
  //      // Do nothing, this resource can not be saved.
  //    }
  //
  //    /**
  //     * @see org.eclipse.emf.ecore.resource.impl.ResourceImpl#isLoaded()
  //     */
  //    @Override
  //    public boolean isLoaded() {
  //      return true;
  //    }
  //  }

  /**
   * Adapter factories provider, that creates an adapter factory specific to the semantic model in use.<br>
   */
  public interface IAdapterFactoryProvider {
    /**
     * @return the adapter factory
     */
    public AdapterFactory getAdapterFactory();
  }

  /**
   * Cross referencers provider, that creates a cross referencer specific to the semantic model in use.<br>
   * Also creates a cross referencer specific to semantic derived features computation (with a semantic model scope).
   */
  public interface ICrossReferencerProvider {
    /**
     * Get general purpose cross referencer.<br>
     * This one must limit its scope to semantic models.
     * @return
     */
    public ECrossReferenceAdapter getCrossReferencer(EditingDomain editingDomain);

    /**
     * Get cross referencer for derived features computation.<br>
     * This one also must limit its scope to semantic models.
     * @return
     */
    public ECrossReferenceAdapter getDerivedCrossReferencer(EditingDomain editingDomain);
  }

  /**
   * Pre-commit listener provider, that creates a pre-commit listener specific to the semantic editing domain.<br>
   */
  //  public interface IPreCommitListenerProvider {
  //    /**
  //     * Get the instance of pre-commit listener.
  //     * @return must be a not <code>null</code> instance.
  //     */
  //    public ResourceSetListener getPreCommitListener();
  //  }

  public interface IReadOnlyDelegationHandler {
    /**
     * Is given resource in RO ?
     * @param resource_p
     * @return <code>true</code> means read only.
     */
    public boolean isReadOnly(Resource resource_p);
  }

  /**
   * Implementor has the opportunity to customize the {@link SemanticEditingDomain} behavior.
   */
  public interface ISemanticEditingDomainProviders {
    /**
     * Get a cross referencer provider.
     * @return <code>null</code> if not implemented.
     */
    public ICrossReferencerProvider getCrossReferencerProvider();

    /**
     * Get an adapter factory provider.
     * @return <code>null</code> if not implemented.
     */
    public IAdapterFactoryProvider getAdapterFactoryProvider();

    /**
     * Get a Pre commit listener provider.
     * @return <code>null</code> if not implemented.
     */
    //    public IPreCommitListenerProvider getPreCommitListenerProvider();

    /**
     * Get read only delegation handler.
     * @return <code>null</code> if not implemented.
     */
    public IReadOnlyDelegationHandler getReadOnlyDelegationHandler();

    /**
     * Get transaction change recorder provider.
     * @return <code>null</code> if not implemented.
     */
    public ITransactionChangeRecorderProvider getTransactionChangeRecorderProvider();

    /**
     * @param resourceFactoryRegistry_p
     * @return
     */
    public Registry getResourceFactoryRegistry(Registry resourceFactoryRegistry_p);
  }

  public interface ITransactionChangeRecorderProvider {
    /**
     * Get the instance of transaction change recorder.
     * @param domain_p
     * @param resourceSet_p
     * @return must be a not <code>null</code> instance.
     */
    public TransactionChangeRecorder getTransactionChangeRecorder(InternalTransactionalEditingDomain domain_p, ResourceSet resourceSet_p);
  }

  /**
   * Implementation of a transactional command stack based on the one provided by org.eclipse.emf.workspace.<br>
   * This command stack takes into account non dirtying EMF operations.
   * This command stack is able to deal with GMF commands.
   */
  protected class SemanticCommandStack extends WorkspaceCommandStackImpl {
    /**
     * Constructor.
     * @param history_p
     */
    public SemanticCommandStack(IOperationHistory history_p) {
      super(history_p);
    }

    /**
     * Override this methods to avoid revealing internal {@link WorkspaceCommandStackImpl}.
     * @see org.polarsys.capella.common.ef.internal.command.WorkspaceCommandStackImpl#getUndoContext()
     */
    @Override
    protected IUndoContext getUndoContext() {
      return new UndoContext();
    }

    /**
     * {@inheritDoc}
     */
    //    @SuppressWarnings("synthetic-access")
    //    @Override
    //    public void flush() {
    //      if (null != ((SemanticEditingDomain) getDomain())._holdingResource) {
    //        getOperationHistory().dispose(new ResourceUndoContext(getDomain(), ((SemanticEditingDomain) getDomain())._holdingResource), true, true, true);
    //      }
    //      super.flush();
    //    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
      // Make sure operation history is flushed.
      flush();
      super.dispose();
    }

    /**
     * Execute non dirtying command in a transactional context.
     * @param command_p
     * @param options_p
     */
    @SuppressWarnings("rawtypes")
    protected void executeNonDirtyingCommand(Command command_p, Map options_p) throws InterruptedException, RollbackException {
      InternalTransaction tx = createTransaction(command_p, options_p);

      try {
        basicExecute(command_p);

        // commit the transaction now
        tx.commit();
      } catch (OperationCanceledException e) {
        // snuff the exception, because this is expected (user asked to
        // cancel the model change). We will rollback, below
      } finally {
        if ((tx != null) && (tx.isActive())) {
          // roll back (some exception, possibly being thrown now or
          // an operation cancel, has occurred)
          rollback(tx);
          handleRollback(command_p, null);
        } else {
          // the transaction has already incorporated the triggers
          // into its change description, so the recording command
          // doesn't need them again
          // if (!(command_p instanceof RecordingCommand)) {
          // Command triggerCommand = tx.getTriggers();
          //
          // if (triggerCommand != null) {
          // // replace the executed command by a compound of the
          // // original and the trigger commands
          // CompoundCommand compound = new ConditionalRedoCommand.Compound();
          // compound.append(mostRecentCommand);
          // compound.append(triggerCommand);
          // mostRecentCommand = compound;
          // commandList.set(top, mostRecentCommand);
          // }
          // }
        }
      }
    }

    /**
     * If executed command is a non dirtying one, DO NOT notify command stack listeners.
     * @see org.eclipse.emf.transaction.impl.AbstractTransactionalCommandStack#basicExecute(org.eclipse.emf.common.command.Command)
     */
    @Override
    protected void basicExecute(Command command_p) {
      // If the command is executable, execute it.
      if (null != command_p) {
        if (command_p.canExecute()) {
          try {
            command_p.execute();
          } catch (RuntimeException exception) {
            handleError(exception);
            command_p.dispose();
          }
          // If executed command is a non dirtying one, DO NOT notify command stack listeners.
          if (!(command_p instanceof NonDirtying)) {
            // Notify listeners if any.
            notifyListeners();
          }
        } else {
          command_p.dispose();
        }
      }
    }

    /**
     * @see org.polarsys.capella.common.ef.command.TigCommandStack#doExecute(org.eclipse.emf.common.command.Command, java.util.Map)
     */
    @SuppressWarnings("rawtypes")
    @Override
    protected void doExecute(Command command_p, Map options_p) throws InterruptedException, RollbackException {
      try {
        if (command_p instanceof NonDirtying) {
          executeNonDirtyingCommand(command_p, options_p);
        } else {
          super.doExecute(command_p, options_p);
        }
      } catch (IllegalArgumentException exception_p) {
        // Illegal argument exceptions are thrown sometime in the rollback process.
        // Hopefully, the rollback is complete before this exception is thrown.
        // Log this exception as a warning.
        String message = "Error while executing a command:"; //$NON-NLS-1$
        PlatformSiriusTedActivator.getDefault().getLog()
            .log(new Status(IStatus.WARNING, PlatformSiriusTedActivator.getDefault().getPluginId(), message, exception_p));
        throw new RollbackException(new Status(IStatus.CANCEL, PlatformSiriusTedActivator.getDefault().getPluginId(), message, exception_p));
      }
    }
  }

  /**
   * A specific implementation of the composed adapter factory.<br>
   */
  //  public class SemanticAdapterFactory extends ComposedAdapterFactory {
  //
  //    public SemanticAdapterFactory() {
  //      super();
  //    }
  //
  //    /**
  //     * @param adapterFactory_p
  //     */
  //    public SemanticAdapterFactory(AdapterFactory adapterFactory_p) {
  //      super(adapterFactory_p);
  //    }
  //
  //    /**
  //     * @param adapterFactories_p
  //     */
  //    public SemanticAdapterFactory(AdapterFactory[] adapterFactories_p) {
  //      super(adapterFactories_p);
  //    }
  //
  //    /**
  //     * @param adapterFactories_p
  //     */
  //    public SemanticAdapterFactory(Collection<? extends AdapterFactory> adapterFactories_p) {
  //      super(adapterFactories_p);
  //    }
  //
  //    /**
  //     * @param adapterFactoryDescriptorRegistry_p
  //     */
  //    public SemanticAdapterFactory(org.eclipse.emf.edit.provider.ComposedAdapterFactory.Descriptor.Registry adapterFactoryDescriptorRegistry_p) {
  //      super(adapterFactoryDescriptorRegistry_p);
  //    }
  //
  //  }

  /**
   * A specific implementation of the transactional editing domain.<br>
   * It contains an {@link ECrossReferenceAdapter} that should be used whenever a cross reference is needed.<br>
   * It contains an {@link ECrossReferenceAdapter} specific to computation of derived features.<br>
   * This one should not be used outside TIG helpers.<br>
   * It automatically registers both adapters to new root elements of its resources, but only on semantic ones.<br>
   */
  public class SemanticEditingDomain extends TransactionalEditingDomainImpl {

    /**
     * Hidden resource where a new element is stored (providing it is created within a transaction).<br>
     * This allows for the cross referencer to find such elements, even if they are not yet attached to a persisted resource.<br>
     * This is used along with the derived cross referencer. If none is provided, there is no use for this resource.
     */
    //    private HoldingResource _holdingResource;

    List<IEditingDomainListener> _editingDomainListeners = null;

    /**
     * Constructor.
     * @param stack_p
     */
    public SemanticEditingDomain(AdapterFactory adapterFactory_p, TransactionalCommandStack stack_p) {
      super(adapterFactory_p, stack_p, new SemanticResourceSet());
      // The cross referencer.
      SemanticResourceSet semanticResourceSet = getResourceSet();
      // Set editing domain.+

      semanticResourceSet.setEditingDomain(this);
      semanticResourceSet.registerAdapters();
      // Notify all editing domain listeners.
      for (IEditingDomainListener listener : getEditingDomainListeners()) {
        listener.createdEditingDomain(this);
      }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
      SemanticCommandStack workspaceCommandStack = (SemanticCommandStack) getCommandStack();
      workspaceCommandStack.flush();
      // Make sure Operation History don't retain Context for resources.
      for (Resource resource : resourceSet.getResources()) {
        // ResourceUndoContext compares with underlying resource.
        workspaceCommandStack.getOperationHistory().dispose(new ResourceUndoContext(this, resource), true, true, true);
      }

      // The Sirius Session calls dispose
      // when the session is closed.
      // @see also DAnalysisSessionImpl.setDisposeEditingDomainOnClose
      //     
      // Remove all listeners
      for (ResourceSetListener listener : getPrecommitListeners()) {
        removeResourceSetListener(listener);
      }
      for (ResourceSetListener listener : getPostcommitListeners()) {
        removeResourceSetListener(listener);
      }
      for (ResourceSetListener listener : getAggregatePrecommitListeners()) {
        removeResourceSetListener(listener);
      }

      //We dispose the workspace command stack, since it was created while the editing domain creation @see createEditingDomain()
      workspaceCommandStack.dispose();

      // Notify all editing domain listeners.
      for (IEditingDomainListener listener : getEditingDomainListeners()) {
        listener.disposedEditingDomain(this);
      }
    }

    private List<IEditingDomainListener> getEditingDomainListeners() {
      if (null == _editingDomainListeners) {
        _editingDomainListeners = new ArrayList<IEditingDomainListener>();
        IConfigurationElement[] configurationElements =
            ExtensionPointHelper.getConfigurationElements("org.polarsys.capella.common.ef", "editingDomainListener");
        for (IConfigurationElement configurationElement : configurationElements) {
          _editingDomainListeners.add((IEditingDomainListener) ExtensionPointHelper.createInstance(configurationElement, "class"));
        }
      }
      return _editingDomainListeners;
    }

    /**
     * Get general purpose cross referencer, that limits its scope to semantic models.
     * @return
     */
    public ECrossReferenceAdapter getCrossReferencer() {
      SemanticResourceSet semanticResourceSet = getResourceSet();
      if (semanticResourceSet != null) {
        return semanticResourceSet.getCrossReferencer();
      }
      return null;
    }

    /**
     * Get the data notifier.
     * @return a not <code>null</code> data notifier.
     * @see DataNotifier.
     */
    public DataNotifier getDataNotifier() {
      return getResourceSet().getDataNotifier();
    }

    /**
     * Get cross referencer that should be used for TIG helpers computation.
     * @return
     */
    public ECrossReferenceAdapter getDerivedCrossReferencer() {
      SemanticResourceSet semanticResourceSet = getResourceSet();
      if (semanticResourceSet != null) {
        return semanticResourceSet.getDerivedCrossReferencer();
      }
      return null;
    }

    /**
     * Get cross holding resource.
     * @return
     */
    //    public HoldingResource getHoldingResource() {
    //      // There is no need for the holding resource, if no derived features cross referencer was found.
    //      if ((null != getDerivedCrossReferencer()) && (null == _holdingResource)) {
    //        // Create and attach cross referencer resource.
    //        _holdingResource = new HoldingResource();
    //        ExecutionManagerRegistry.getInstance().getExecutionManager(TigEfProvider.getExecutionManagerName()).execute(new AbstractNonDirtyingCommand() {
    //          @SuppressWarnings("synthetic-access")
    //          public void run() {
    //            getResourceSet().getResources().add(_holdingResource);
    //            // the 'resourceSet' attribute of the holding resource must
    //            // be set, or the cross referencer won't work correctly !
    //            _holdingResource.basicSetResourceSet(getResourceSet(), null);
    //          }
    //        });
    //      }
    //      return _holdingResource;
    //    }

    /**
     * @see org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain#getResourceSet()
     */
    @Override
    public SemanticResourceSet getResourceSet() {
      return (SemanticResourceSet) super.getResourceSet();
    }

    /**
     * Overridden to bypass the call to {@link #isReadOnly(Resource)}.<br>
     * When working in SCM context, let's Team API does its job.
     * @see org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain#isControllable(java.lang.Object)
     */
    @Override
    public boolean isControllable(Object object) {
      if (!(object instanceof EObject)) {
        return false;
      }
      EObject eObject = (EObject) object;
      EObject container = eObject.eContainer();
      return (container != null) && eObject.eContainmentFeature().isResolveProxies();
    }

    /**
     * Override default behavior to make sure isReadOnly result is computed at every method call.<br>
     * If Read Only Test is disabled, the method always returns <code>false</code>.<br>
     * See explanations for {@link #enableReadOnlyTest(boolean)} method.<br>
     * @see org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain#isReadOnly(org.eclipse.emf.ecore.resource.Resource)
     */
    @Override
    public boolean isReadOnly(Resource resource_p) {
      boolean result = true;

      // Make sure the result is always computed at every method call.
      resourceToReadOnlyMap.clear();
      ISemanticEditingDomainProviders providers = getSemanticEditingDomainProviders();
      if (providers != null) {
        IReadOnlyDelegationHandler readOnlyDelegationHandler = providers.getReadOnlyDelegationHandler();
        if (null != readOnlyDelegationHandler) {
          result = readOnlyDelegationHandler.isReadOnly(resource_p);
        }
      }
      return (result) ? super.isReadOnly(resource_p) : false;
    }

    /**
     * @see org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain#isReadOnlyURI(org.eclipse.emf.common.util.URI)
     */
    //    @Override
    //    protected boolean isReadOnlyURI(URI uri_p) {
    //      boolean readOnlyURI = super.isReadOnlyURI(uri_p);
    //
    //      HoldingResource holdingResource = getHoldingResource();
    //      if ((holdingResource != null) && uri_p.equals(holdingResource.getURI())) {
    //        // Phantom resource is not a read only one.
    //        readOnlyURI = false;
    //      }
    //
    //      return readOnlyURI;
    //    }

    /**
     * @see org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl#createChangeRecorder(org.eclipse.emf.ecore.resource.ResourceSet)
     */
    @Override
    protected TransactionChangeRecorder createChangeRecorder(ResourceSet rset_p) {
      TransactionChangeRecorder result = null;
      ITransactionChangeRecorderProvider transactionChangeRecorderProvider = getSemanticEditingDomainProviders().getTransactionChangeRecorderProvider();
      if (null != transactionChangeRecorderProvider) {
        result = transactionChangeRecorderProvider.getTransactionChangeRecorder(this, rset_p);
      } else {
        result = super.createChangeRecorder(rset_p);
      }
      return result;
    }

  }

  /**
   * A resource set specific to semantic model needs, that is having two {@link ECrossReferenceAdapter}.<br>
   * The first one is used for computation of derived features, and is contributed by semantic specific needs.<br>
   * The second one is used for computation of inverse references, limited to the semantic model (excluding all others referencing models, such as graphical
   * ones for instance).
   */
  public class SemanticResourceSet extends ResourceSetImpl implements IEditingDomainProvider {
    /**
     * Editing domain.
     */
    private EditingDomain _editingDomain;
    /**
     * Derived features cross referencer.
     */
    private ECrossReferenceAdapter _derivedCrossReferencer;
    /**
     * General purpose cross referencer.
     */
    private ECrossReferenceAdapter _crossReferencer;
    /**
     * Data notifier.
     */
    private DataNotifier _dataNotifier;
    /**
     * Is loading a resource ?
     */
    private volatile int _ResourcesLoading = 0;
    private volatile boolean _ForceResourcesLoading = false;

    /**
     * Constructor.
     * @param adapter_p
     */
    public SemanticResourceSet() {
      super();
    }

    public void registerAdapters() {
      // Load (expectedly) unique cross referencer for derived features computation.
      // Also load general purpose cross referencer.
      loadCrossReferencers(getEditingDomain());
      // Register it.
      if (null != _derivedCrossReferencer) {
        eAdapters().add(_derivedCrossReferencer);
      }
      // Add general cross referencing cross referencer.
      if ((null != _crossReferencer) && (_crossReferencer != _derivedCrossReferencer)) {
        eAdapters().add(_crossReferencer);
      }
      // Add the famous and useful data notifier.
      _dataNotifier = new DataNotifier(getEditingDomain());
      eAdapters().add(_dataNotifier);
    }

    /**
     * @see org.eclipse.emf.ecore.resource.impl.ResourceSetImpl#getResource(org.eclipse.emf.common.util.URI, boolean)
     */
    @Override
    public Resource getResource(URI uri_p, boolean loadOnDemand_p) {
      if (loadOnDemand_p) {
        _ResourcesLoading++;
      }
      try {
        return super.getResource(uri_p, loadOnDemand_p);
      } finally {
        if (loadOnDemand_p) {
          _ResourcesLoading--;
        }
      }
    }

    /**
     * Get general purpose cross referencer.
     * @return
     */
    protected ECrossReferenceAdapter getCrossReferencer() {
      return _crossReferencer;
    }

    /**
     * Get the data notifier.
     * @return the dataNotifier
     */
    DataNotifier getDataNotifier() {
      return _dataNotifier;
    }

    /**
     * Get derived features cross referencer.
     * @return
     */
    protected ECrossReferenceAdapter getDerivedCrossReferencer() {
      return _derivedCrossReferencer;
    }

    /**
     * @see org.eclipse.emf.edit.domain.IEditingDomainProvider#getEditingDomain()
     */
    public EditingDomain getEditingDomain() {
      return _editingDomain;
    }

    /**
     * Is a resource being loaded ?
     * @return <code>true</code> means yes.
     */
    public boolean isResourceLoading() {
      return _ForceResourcesLoading || (_ResourcesLoading > 0);
    }

    /**
     * Is a resource being loaded ?
     * @return <code>true</code> means yes.
     */
    public boolean setForceResourceLoading(boolean forceResourceLoading) {
      return _ForceResourcesLoading = forceResourceLoading;
    }

    /**
     * Load first encountered cross referencers (general & derived) on the platform.
     */
    protected void loadCrossReferencers(EditingDomain editingDomain) {
      ISemanticEditingDomainProviders semanticEditingDomainProviders = getSemanticEditingDomainProviders();
      if (null != semanticEditingDomainProviders) {
        ICrossReferencerProvider provider = semanticEditingDomainProviders.getCrossReferencerProvider();
        if (null != provider) {
          _derivedCrossReferencer = provider.getDerivedCrossReferencer(editingDomain);
          _crossReferencer = provider.getCrossReferencer(editingDomain);
        }
      }
    }

    /**
     * Set the editing domain reference.
     * @param editingDomain_p
     */
    protected void setEditingDomain(EditingDomain editingDomain_p) {
      _editingDomain = editingDomain_p;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Registry getResourceFactoryRegistry() {
      ISemanticEditingDomainProviders semanticEditingDomainProviders = getSemanticEditingDomainProviders();
      if (null != semanticEditingDomainProviders) {
        return semanticEditingDomainProviders.getResourceFactoryRegistry(super.getResourceFactoryRegistry());
      }
      return super.getResourceFactoryRegistry();
    }
  }

  private ISemanticEditingDomainProviders _semanticEditingDomainProviders;

  /**
   * Default constructor.
   */
  public SemanticEditingDomainFactory() {
    super();
  }

  /**
   * @see org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl.FactoryImpl#createEditingDomain()
   */
  @Override
  public synchronized TransactionalEditingDomain createEditingDomain() {
    // Load (expectedly) unique semantic editing domain providers.
    loadSemanticEditingDomainProviders();
    // Create a command stack TIG-ready.
    TransactionalCommandStack stack = doCreateCommandStack();
    // Create an editing domain TIG-ready.
    TransactionalEditingDomain result = doCreateEditingDomain(stack);
    // Map resource set to editing domain.
    mapResourceSet(result);

    return result;
  }

  /**
   * Do create a custom transactional command stack.
   */
  protected SemanticCommandStack doCreateCommandStack() {
    return new SemanticCommandStack(OperationHistoryFactory.getOperationHistory());
  }

  /**
   * Do create a custom transactional editing domain using newly created custom custom stack.
   * 
   * @param stack_p
   */
  protected TransactionalEditingDomain doCreateEditingDomain(TransactionalCommandStack stack_p) {
    AdapterFactory adapterFactory = getSemanticEditingDomainProviders().getAdapterFactoryProvider().getAdapterFactory();
    SemanticEditingDomain semanticEditingDomain = new SemanticEditingDomain(adapterFactory, stack_p);
    //    loadPreCommitListener(semanticEditingDomain);
    return semanticEditingDomain;
  }

  /**
   * Get the semantic editing domain providers (if any)
   * @return <code>null</code> if not contributed.
   */
  ISemanticEditingDomainProviders getSemanticEditingDomainProviders() {
    return _semanticEditingDomainProviders;
  }

  /**
   * Load and register pre-commit listener (if any).
   * @param semanticEditingDomain_p
   */
  //  protected void loadPreCommitListener(SemanticEditingDomain semanticEditingDomain_p) {
  //    ISemanticEditingDomainProviders semanticEditingDomainProviders = getSemanticEditingDomainProviders();
  //    if (null != semanticEditingDomainProviders) {
  //      IPreCommitListenerProvider provider = semanticEditingDomainProviders.getPreCommitListenerProvider();
  //      if (null != provider) {
  //        ResourceSetListener preCommitListener = provider.getPreCommitListener();
  //        // Add it if not null.
  //        if (null != preCommitListener) {
  //          semanticEditingDomain_p.addResourceSetListener(preCommitListener);
  //        }
  //      }
  //    }
  //  }

  /**
   * Load the unique semantic editing domain providers.
   */
  private void loadSemanticEditingDomainProviders() {
    // Load SemanticEditingDomain providers if any.
    IConfigurationElement[] configurationElements =
        ExtensionPointHelper.getConfigurationElements("org.polarsys.capella.common.platform.sirius.ted", "semanticEditingDomainProviders"); //$NON-NLS-1$ //$NON-NLS-2$
    // Loop over contributed SemanticEditingDomain providers, must be only one.
    if (configurationElements.length > 0) {
      _semanticEditingDomainProviders =
          (ISemanticEditingDomainProviders) ExtensionPointHelper.createInstance(configurationElements[0], ExtensionPointHelper.ATT_CLASS);
    }
  }
}
