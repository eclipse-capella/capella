/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.tiger.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.common.data.helpers.modellingcore.utils.HoldingResourceFilter;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.capellacommon.GenericTrace;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.handler.helpers.HoldingResourceHelper;
import org.polarsys.capella.core.tiger.Activator;
import org.polarsys.capella.core.tiger.IFinalizer;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.ITransfoEngine;
import org.polarsys.capella.core.tiger.ITransfoRule;
import org.polarsys.capella.core.tiger.Messages;
import org.polarsys.capella.core.tiger.TransfoException;
import org.polarsys.capella.core.tiger.extension.ITransfoEngineExecuteExt;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;

/**
 * The abstract transformation engine. This abstraction contains the transformation algorithm chosen for the bridge.
 */
@SuppressWarnings("unchecked")
public abstract class TransfoEngine extends ITransfoEngine {

  public static final String HOLDING_RESOURCE = "HOLDING_RESOURCE"; //$NON-NLS-1$

  /**
   * The transformation engine state
   */
  public enum State {
    CREATED, FINISH, INITIALIZED, RUNNING
  }

  /**
   * carriage return
   */
  public final static String __cr = System.getProperty("line.separator");

  /**
   * Extension name
   */
  public static final String TRANSFO_EXTENSION = "transfoExecuteExtension"; //$NON-NLS-1$

  /**
   * BOOTSTRAP tag's property
   */
  public static final String BOOTSTRAP = "bootstrap"; //$NON-NLS-1$

  /**
   * NEW_LINKS tag's property
   */
  public static final String NEW_LINKS = "newLinks"; //$NON-NLS-1$

  /**
   * TRANSFO_SOURCE tag's property
   */
  public static final String TRANSFO_SOURCE = "transfoSource"; //$NON-NLS-1$

  /**
   * TRANSFO_TARGET tag's property
   */
  public static final String TRANSFO_TARGET = "transfoTarget"; //$NON-NLS-1$

  /**
   * TRANSFORMED_ELEMENTS tag's property
   */
  public static final String TRANSFORMED_ELEMENTS = "transformedElements"; //$NON-NLS-1$

  /**
   * The agenda
   */
  protected List<EObject> _agenda;

  /**
   * The depending models of the model to be transformed
   */
  protected List<EObject> _dependingModels;

  /**
   * The Report Manager logger
   */
  protected Logger _logger = null;

  /**
   * The current state
   */
  protected State _state = State.CREATED;

  /**
   * Transformation to be launched asynchronously
   */
  protected ITransfo _transfo;

  /**
   * The transformed elements
   */
  protected List<EObject> _transformedElements;

  private String _transitionStartTemplate = "----- Perform transition of ''{0}'' -----"; //$NON-NLS-1$

  private String _alreadyTransitionedTemplate = "''{0}'' is already transitioned."; //$NON-NLS-1$

  /**
   * Default constructor
   */
  public TransfoEngine() {
    this(ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.REFINEMENT));
  }

  public TransfoEngine(Logger logger) {
    _logger = logger;
    _agenda = new ArrayList<EObject>();
    _dependingModels = new ArrayList<EObject>();
    _transformedElements = new ArrayList<EObject>();
  }

  /**
   * Attaches transformed elements in the transformed model
   * @throws TransfoException When a transformation fails
   */
  private void attach() throws TransfoException {
    if (_logger.isDebugEnabled()) {
      _logger.debug("===================================="); //$NON-NLS-1$
      _logger.debug("Attach");
      _logger.debug("===================================="); //$NON-NLS-1$
    }
    int step = 0;
    for (EObject sourceElement : _agenda) {
      if (_logger.isDebugEnabled()) {
        _logger.debug(" + Step " + step);
        _logger.debug("   - Current element is : " + EObjectExt.getText(sourceElement));
      }
      ITransfoRule rule = _transfo.findCachedMatchingRule(sourceElement);
      if (rule != null) {
        rule.attach(sourceElement, _transfo);
      }
      step++;
    }
  }

  /**
   * Checks integrity of the transformed elements
   * @throws TransfoException when integrity check fails
   */
  private void check(ITransfo transfo) throws TransfoException {
    List<EObject> troubleList = new ArrayList<EObject>();

    // 1- Checks against null containment
    for (EObject object : _transformedElements) {
      if (object.eContainer() == null) {
        if (HoldingResourceFilter.getInstance().isHoldByHoldingResource(object) || !object.eResource().getContents().contains(object)) {
          troubleList.add(object);
        }
      }
    }

    // 2- Report the error if the list is not empty
    if (troubleList.size() > 0) {
      StringBuilder builder = new StringBuilder("No containment for :"); //$NON-NLS-1$
      builder.append(System.getProperty("line.separator")); //$NON-NLS-1$
      for (EObject troubleObject : troubleList) {
        builder.append(" - ");
        builder.append(EObjectExt.getText(troubleObject));
        builder.append(System.getProperty("line.separator")); //$NON-NLS-1$

        CapellaElement element = (CapellaElement) troubleObject;
        cleanUp(element);
      }

      // Throws the exception
      throw new TransfoException(builder.toString(), (EObject) transfo.get(TRANSFO_SOURCE));
    }
  }

  public abstract void doProcessDependingModels(List<EObject> dependingModels) throws TransfoException;

  /**
   * @throws TransfoException
   * @see org.polarsys.capella.common.tiger.ITransfoEngine#execute(org.polarsys.capella.core.bridges.transfo.impl.Transfo)
   */
  @Override
  public void execute(ITransfo transfo) throws TransfoException {

    try {

      preExecute(transfo);

      // Initializes the engine
      if (_state != State.INITIALIZED) {
        initialize(transfo);
      }

      _state = State.RUNNING;

      if (_logger.isDebugEnabled()) {
        _logger.debug("====================================");
        _logger.debug("Starting transformation '" + transfo.getUid() + "'");
        _logger.debug("====================================");
      }

      // 1- Find depending models (shared packages for instance)
      processDependingModels();

      // 2- Transforms and/or updates the elements
      transformUpdate();

      // 3- Attaches the transformed elements to each others
      attach();

      // 4- Finalizes the process (root attachment, report, ...)
      finalize_();
      List<IFinalizer> finalizers = _transfo.getFinalizers();
      for (IFinalizer iFinalizer : finalizers) {
        iFinalizer.finalize(transfo);
      }

      // 5- Display traces
      // _logger.debug(new EmbeddedMessage(trace,
      // IReportManagerDefaultComponents.DEFAULT, null));

      // 6- Checks the transformed model
      check(transfo);

      _state = State.FINISH;

    } catch (OperationCanceledException exception) {
      String msg = exception.getMessage();
      _logger.error(msg, exception);
      throw exception;
    } catch (TransfoException exception) {
      // /////////////////////////////////////////
      // 7.1- Transformation exception raised
      exception.printStackTrace();
      String msg = NLS.bind(Messages.TransfoEngine_ErrorWhileTransformation, exception.getMessage());
      _logger.error(msg, exception);

      throw exception;

    } catch (Exception exception) {
      // /////////////////////////////////////////
      // 7.2- Unexpected exception raised
      exception.printStackTrace();
      String msg = NLS.bind(Messages.TransfoEngine_ErrorWhileTransformationDetailled + exception.getMessage(), exception.getClass().getSimpleName());
      _logger.error(msg, exception);

      throw new TransfoException(exception.getMessage() + "(" + exception.getClass().getSimpleName() + ")",
          _transfo != null ? (EObject) _transfo.get(TRANSFO_SOURCE) : null, exception);
    } finally {
      postExecute(transfo);
    }

  }

  /**
   * @param transfo
   * @throws CoreException
   * @throws Exception
   */
  public void preExecute(ITransfo transfo) throws CoreException, Exception {
    IExtensionRegistry registry = Platform.getExtensionRegistry();
    IConfigurationElement[] elements = registry.getConfigurationElementsFor(FrameworkUtil.getBundle(Activator.class).getSymbolicName());
    for (final IConfigurationElement element : elements) {
      if (TRANSFO_EXTENSION.equals(element.getName())) {
        ITransfoEngineExecuteExt extension = (ITransfoEngineExecuteExt) element.createExecutableExtension("class"); //$NON-NLS-1$
        extension.preExecute(transfo);
      }
    }
  }

  /**
   * @param transfo
   * @throws CoreException
   * @throws Exception
   */
  public void postExecute(ITransfo transfo) {

    //We remove the holding resource at the end of the transition
    // EObject transfoSource = (EObject) _transfo.get(TRANSFO_SOURCE);
    // new HoldingResourceHelper().flushHoldingResource(MDEAdapterFactory.getEditingDomain(transfoSource));

    try {
      IExtensionRegistry registry = Platform.getExtensionRegistry();
      IConfigurationElement[] elements = registry.getConfigurationElementsFor(FrameworkUtil.getBundle(Activator.class).getSymbolicName());
      for (final IConfigurationElement element : elements) {
        if (TRANSFO_EXTENSION.equals(element.getName())) {
          ITransfoEngineExecuteExt extension = (ITransfoEngineExecuteExt) element.createExecutableExtension("class"); //$NON-NLS-1$
          extension.postExecute(transfo);
        }
      }
    } catch (Exception exception) {
      // /////////////////////////////////////////
      // Unexpected exception raised
      exception.printStackTrace();
      String msg = NLS.bind(Messages.TransfoEngine_ErrorWhileTransformationDetailled + exception.getMessage(), exception.getClass().getSimpleName());
      _logger.error(msg, exception);
    }
  }

  /**
   * Finalizes the process
   */
  protected abstract void finalize_();

  /**
   * 
   */
  @Override
  public String generateReport() {
    StringBuilder builder = new StringBuilder();
    if (_state == State.INITIALIZED) {
      builder.append("The transformation " + _transfo.getUid() + " is about to be launched."); //$NON-NLS-1$ //$NON-NLS-2$
      builder.append(__cr);
      builder.append("It requires " + _agenda.size() + " transformations."); //$NON-NLS-1$ //$NON-NLS-2$
      builder.append(__cr);
    } else if (_state == State.FINISH) {
      builder.append(_agenda.size() + " transformations has been done."); //$NON-NLS-1$
      builder.append(__cr);
    }
    return builder.toString();
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoEngine#generateUid()
   */
  @Override
  public void generateUid() {
    // Nothing to be done...
  }

  public List<EObject> getAgenda() {
    return _agenda;
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoEngine#getName()
   */
  @Override
  public String getName() {
    return "Tiger";
  }

  /**
   * Should returns one of the IReportManagerDefaultComponents constants
   */
  protected String getReportComponent() {
    return IReportManagerDefaultComponents.REFINEMENT;
  }

  /**
   * Initializes the engine.<br/>
   * Clears the scheduler.<br/>
   * Clears the depending models list.<br/>
   * Reinitializes the list of created links during the transformation process.<br/>
   * It calls the {@link #initialize_()} method that is intended to be overridden.<br/>
   * @see #NEW_LINKS
   * @see #initialize_()
   */
  protected void initialize() {
    _agenda.clear();
    _dependingModels.clear();

    // Links
    List<GenericTrace> links = new ArrayList<GenericTrace>();
    _transfo.put(NEW_LINKS, links);
    _transfo.put(TRANSFORMED_ELEMENTS, _transformedElements);

    initialize_();
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoEngine#initialize(org.polarsys.capella.common.tiger.ITransfo)
   */
  @Override
  public void initialize(ITransfo transfo) throws TransfoException {

    _state = State.CREATED;

    setTransfo(transfo);
    generateUid();

    if (_logger.isDebugEnabled()) {
      _logger.debug("===================================="); //$NON-NLS-1$
      _logger.debug("Initializing transformation '" + transfo.getUid() + "'"); //$NON-NLS-1$
      _logger.debug("===================================="); //$NON-NLS-1$
    }

    initialize();
    retrieveElements();
    retrieveDependingModels();

    _state = State.INITIALIZED;

    if (_logger.isDebugEnabled()) {
      _logger.debug("===================================="); //$NON-NLS-1$
      _logger.debug("Report"); //$NON-NLS-1$
      _logger.debug("===================================="); //$NON-NLS-1$
      _logger.debug(generateReport());
    }
  }

  /**
   * Abstract initialization method intended to be overridden by specific engine<br/>
   */
  protected abstract void initialize_();

  /**
   * Finds the depending models (ex: shared packages).
   * @throws TransfoException When transformation fails
   */
  private void processDependingModels() throws TransfoException {
    if (_logger.isDebugEnabled()) {
      _logger.debug("===================================="); //$NON-NLS-1$
      _logger.debug("Process depending models"); //$NON-NLS-1$
      _logger.debug("===================================="); //$NON-NLS-1$
    }
    doProcessDependingModels(_dependingModels);
  }

  /**
   * @param sourceElement
   * @param targetElement
   * @throws TransfoException
   */
  private void registerTargetElement(EObject sourceElement, EObject targetElement) throws TransfoException {
    if (targetElement != null) { // Allow transformation one to nothing

      if (targetElement.eResource() == null) {
        HoldingResourceHelper.attachToHoldingResource(targetElement, getHoldingResource(_transfo));
      }

      _transformedElements.add(targetElement);

      // Links
      AbstractTrace newLink = TigerRelationshipHelper.createTransfoLink(sourceElement, targetElement, _transfo);
      if (newLink != null) {
        if (newLink.eContainer() == null) {
          HoldingResourceHelper.attachToHoldingResource(newLink, getHoldingResource(_transfo));
        }
        List<AbstractTrace> links = (List<AbstractTrace>) _transfo.get(NEW_LINKS);
        links.add(newLink);
      }
    }
  }

  /**
   * Retrieves the models which the transformation model depends on.
   */
  private void retrieveDependingModels() {

    if (_logger.isDebugEnabled()) {
      _logger.debug("===================================="); //$NON-NLS-1$
      _logger.debug("Retrieving depending models"); //$NON-NLS-1$
      _logger.debug("===================================="); //$NON-NLS-1$
    }

    _dependingModels.clear();

    for (EObject element : _agenda) {
      EObject object = Query.retrieveModelRoot(element);
      if (!_dependingModels.contains(object)) {
        _dependingModels.add(object);
      }
    }

    if (_logger.isDebugEnabled()) {
      _logger.debug(_dependingModels.size() + " depending models had been detected."); //$NON-NLS-1$
    }
  }

  /**
   * Retrieves the elements to be transformed. An agenda is used to find all the elements to be transformed.<br>
   * - The agenda is a list of elements currently parsed.<br>
   * - While this list is not empty, the algorithm continues.<br>
   * - The first element of the agenda is parsed<br>
   * - Each time an element is parsed it is removed from the agenda.<br>
   * - When an element is parsed, the depending elements are added to the agenda.<br>
   * It avoids the recursive version which could cause a {@link StackOverflowError} rise.<br>
   * No twice the same element in the agenda (infinite loop). No twice the same element in the scheduler (transformation process could occur twice). This method
   * constructs the {@link #_agenda} list.<br>
   * @throws TransfoException If null is found in the {@link #_agenda}
   */
  protected void retrieveElements() throws TransfoException {

    if (_logger.isDebugEnabled()) {
      _logger.debug("===================================="); //$NON-NLS-1$
      _logger.debug("Retrieving elements to be transformed"); //$NON-NLS-1$
      _logger.debug("===================================="); //$NON-NLS-1$
    }

    LinkedList<EObject> agenda = new LinkedList<EObject>();
    HashSet<EObject> visited = new HashSet<EObject>();

    // Add the bootstrap in the agenda
    List<EObject> bootstrap = (List<EObject>) _transfo.get(BOOTSTRAP);
    agenda.addAll(bootstrap);

    if (!agenda.isEmpty() && _logger.isInfoEnabled()) {
      String startMessage = formatTransitionStartMessage(agenda.get(0));
      if (startMessage != null) {
        _logger.info(new EmbeddedMessage(startMessage, _logger.getName(), agenda.get(0)));
      }
    }

    int step = 0;
    // While the agenda is not empty
    while (!agenda.isEmpty()) {
      EObject currentElement = agenda.removeFirst();

      if (_logger.isDebugEnabled()) {
        _logger.debug(" + Step " + step); //$NON-NLS-1$
        _logger.debug("   - The agenda size is : " + agenda.size()); //$NON-NLS-1$
        _logger.debug("   - The current element is : " + EObjectExt.getText(currentElement)); //$NON-NLS-1$
      }

      // No 'null' element in the agenda
      if (currentElement == null) {
        throw new TransfoException("The parsed element is 'null'", currentElement); //$NON-NLS-1$
      }

      if (visited.contains(currentElement)) {
        continue;
      }

      visited.add(currentElement);
      _agenda.add(currentElement);

      ITransfoRule rule = _transfo.findCachedMatchingRule(currentElement);

      if (rule == null) {
        if (_logger.isDebugEnabled()) {
          // New version : just a warning
          _logger.debug("      -> Warning no rule found for " + EObjectExt.getText(currentElement)); //$NON-NLS-1$
        }
        agenda.remove(currentElement);

        // ////////////////////////////////
        // Old version (stop here)
        // throw new TransfoException("No rule matching",
        // currentElement);
        // ////////////////////////////////
      } else {
        if (_logger.isDebugEnabled()) {
          _logger.debug("   - Matching rule is : " + rule.getName()); //$NON-NLS-1$
        }

        List<EObject> relatedElements = rule.retrieveRelatedElements(currentElement, _transfo);

        if ((relatedElements != null) && relatedElements.contains(null)) {
          throw new TransfoException("One related element is 'null'", currentElement, rule); //$NON-NLS-1$
        }

        if (relatedElements != null) {
          agenda.addAll(relatedElements);
          if (_logger.isDebugEnabled()) {
            for (EObject relatedElement : relatedElements) {
              _logger.debug("   - Re-injecting element : " + EObjectExt.getText(relatedElement)); //$NON-NLS-1$
            }
          }
        }
      }

      step++;
    }
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoEngine#run()
   */
  @Override
  public void run() {
    try {
      execute(_transfo);
    } catch (OperationCanceledException e) {
      throw e;
    } catch (Exception e) {
      System.out.println(generateReport());
      throw new RuntimeException(e);
    }
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoEngine#setTransfo(org.polarsys.capella.core.bridges.transfo.impl.Transfo)
   */
  @Override
  public void setTransfo(ITransfo transfo) {
    _transfo = transfo;
  }

  protected Resource getHoldingResource(ITransfo transfo) {
    if (!transfo.containsKey(HOLDING_RESOURCE)) {
      EObject transfoSource = (EObject) _transfo.get(TRANSFO_SOURCE);
      transfo.put(HOLDING_RESOURCE, HoldingResourceHelper.getHoldingResource(TransactionHelper.getEditingDomain(transfoSource)));
    }
    return (Resource) transfo.get(HOLDING_RESOURCE);
  }

  /**
   * Transform or Transform/update the elements
   * @throws TransfoException When a transformation fails
   */
  private void transformUpdate() throws TransfoException {
    if (_logger.isDebugEnabled()) {
      _logger.debug("===================================="); //$NON-NLS-1$
      _logger.debug("Transform/Update");
      _logger.debug("===================================="); //$NON-NLS-1$
      _logger.debug("Nb of element to be transformed: " + _agenda.size()); //$NON-NLS-1$
    }
    int step = 0;
    for (EObject sourceElement : _agenda) {
      if (_logger.isDebugEnabled()) {
        _logger.debug(" + Step " + step); //$NON-NLS-1$
        _logger.debug("   - Current element is : " + EObjectExt.getText(sourceElement)); //$NON-NLS-1$
      }
      ITransfoRule rule = _transfo.findCachedMatchingRule(sourceElement);

      if (rule != null) {

        if (rule.requireTransformation(sourceElement, _transfo)) {

          Object elements = rule.transform(sourceElement, _transfo);

          if (elements instanceof Collection) {
            Collection<EObject> objects = (Collection<EObject>) elements;
            for (EObject targetElement : objects) {
              registerTargetElement(sourceElement, targetElement);
            }
          } else {
            EObject targetElement = (EObject) elements;
            registerTargetElement(sourceElement, targetElement);
          }

        } else {
          Object transformedElement = Query.retrieveTransformedElement(sourceElement, _transfo);
          if (_logger.isInfoEnabled()) {
            String alreadyTransitionedMessage = formatAlreadyTransitionedMessage(sourceElement, transformedElement);
            if (alreadyTransitionedMessage != null){
              _logger.info(new EmbeddedMessage(alreadyTransitionedMessage, _logger.getName(), new Object[] { sourceElement, transformedElement }));
            }
          }
        }

        rule.update(sourceElement, _transfo);
      }
      step++;
    }
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoEngine#update(java.util.Observable, java.lang.Object)
   */
  @Override
  public void update(Observable o, Object arg) {
    // Notification received from the progress observable (IU)
    // FIXME this is strange
  }

  /**
   * Cleans up the element in parameter (Destroys it and remove links from and to it)
   * @param element The element to be cleaned up
   */
  private static void cleanUp(CapellaElement element) {

    // 1- Clean up the incoming traces
    List<AbstractTrace> traces = new ArrayList<AbstractTrace>(element.getIncomingTraces());
    for (AbstractTrace trace : traces) {
      trace.destroy();
    }

    // 2- Clean up the outgoing traces
    traces = new ArrayList<AbstractTrace>(element.getOutgoingTraces());
    for (AbstractTrace trace : traces) {
      trace.destroy();
    }

    // 3- Destroy the element itself
    element.destroy();
  }


  /**
   * Set a custom NLS template string to show at beginning of the transition.
   * @param template the string to be formatted
   */
  public void setTransitionStartTemplate(String template){
    _transitionStartTemplate = template;
  }


  /**
   * Set a custom NLS template string to show for already transitioned elements
   * @param template the string to be formatted
   */
  public void setAlreadyTransitionedTemplate(String template){
    _alreadyTransitionedTemplate = template;
  }


  private String formatTransitionStartMessage(EObject first) {
    String result = null;
    if (_transitionStartTemplate != null) {
      result = NLS.bind(_transitionStartTemplate, EObjectLabelProviderHelper.getText(first));
    } 
    return result;
  }


  private String formatAlreadyTransitionedMessage(EObject transitioned, Object transformedElement) {
    String result = null;
    if (_alreadyTransitionedTemplate != null) {
      result = NLS.bind(_alreadyTransitionedTemplate, EObjectLabelProviderHelper.getText(transitioned), EObjectLabelProviderHelper.getText(transformedElement));
    }
    return result;
  }
}
