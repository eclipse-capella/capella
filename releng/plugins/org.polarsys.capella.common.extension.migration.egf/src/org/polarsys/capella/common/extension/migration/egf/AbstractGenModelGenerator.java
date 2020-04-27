/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.extension.migration.egf;

import org.polarsys.capella.common.extension.migration.egf.ICommonConstants;
import static org.polarsys.capella.common.extension.migration.egf.ICommonConstants.EMPTY_STRING;
import static org.polarsys.capella.common.extension.migration.egf.ICommonConstants.SLASH_CHARACTER;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenResourceKind;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.converter.util.ConverterUtil;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.importer.ModelImporter;
import org.eclipse.emf.importer.ui.contribution.ModelImporterDescriptor;
import org.eclipse.emf.importer.ui.contribution.ModelImporterManager;

/**
 * Provides services to generate an EMF genmodel from an ecore model.<br>
 * Classes that inherit from this one must provide a default constructor.
 */
public abstract class AbstractGenModelGenerator extends AbstractGenerator {
  /**
   * Log4j reference logger.
   */
  private static final Logger _logger = Logger.getLogger(AbstractGenModelGenerator.class.getPackage().getName());
  /**
   * Define model folder where genmodel is generated.
   */
  protected static final String MODEL_FOLDER = "/model/"; //$NON-NLS-1$
  /**
   * Ecore model importer.
   */
  private ModelImporter _modelImporter;
  /**
   * Input path that a genmodel is generated for.
   */
  private IPath _inputPath;

  /**
   * Model project name ie plug-in id that hosts the model file, generated java code...
   */
  private String _modelPluginId;
  /**
   * Base package used when java code is generated.
   */
  private String _basePackage;
  /**
   * Genmodel path.
   */
  private IPath _genModelPath;
  /**
   * Model container path.
   */
  private IPath _genModelContainerPath;

  /**
   * Interface declared as root interface in genmodel.
   */
  private String _rootExtendsInterface;

  /**
   * Class declared as root class in genmodel.
   */
  private String _rootExtendsClass;

  /**
   * JDK compliance declared in genmodel.
   */
  private GenJDKLevel _jdkComplianceLevel;
  /**
   * Model directory location ie the folder where generated files are output.<br>
   * For instance, it can be <code>src</code>.
   */
  private String _modelDirectory;
  /**
   * Resource type is : None, Basic, XML, XMI.
   * @see GenResourceKind enum.
   */
  private GenResourceKind _resourcetype;
  /**
   * Implementation package suffix used for model implementation classes
   */
  private String _implementationPackageSuffix;
  /**
   * Interface package suffix used for model interfaces and enumerations.
   */
  private String _interfacePackageSuffix;
  /**
   * Metadata package suffix used for package and factory classes.
   */
  private String _metadataPackageSuffix;
  /**
   * Utility package suffix is used for switch, validator, resource, and adapter factory classes.
   */
  private String _utilityPackageSuffix;
  /**
   * 
   */
  private boolean _operationReflection;
  
  /**
   * Create a model importer.
   * @return an {@link ModelImporter} instance.
   */
  protected abstract ModelImporter createModelImporter();

  /**
   * Get model directory from provided parameters.
   * @return
   */
  private String getModelDirectory() {
    StringBuffer projectLocationPath = new StringBuffer(SLASH_CHARACTER);
    projectLocationPath.append(_modelPluginId).append(SLASH_CHARACTER).append(_modelDirectory).append(SLASH_CHARACTER);
    return projectLocationPath.toString();
  }

  /**
   * Create the genmodel file.
   * @param monitor
   * @return a GenModel instance or null if an error occurs
   */
  private GenModel createGenModel(Monitor monitor) {
    GenModel genModel = null;
    try {
      ModelImporter modelImporter = getModelImporter();
      // Create and set the project location.
      if (null != _modelDirectory) {
        modelImporter.setModelPluginDirectory(getModelDirectory());
      }
      // Prepare gemodel and its content.
      modelImporter.prepareGenModelAndEPackages(monitor);
      // Get the genmodel instance.
      genModel = modelImporter.getGenModel();
      // Set genmodel parameters
      setGenModelParameters(genModel);
      try {
        modelImporter.saveGenModelAndEPackages(monitor);
      } catch (Exception exception) {
        StringBuffer loggerMessage = new StringBuffer("AbstractGenModelGenerator.generateGenModelWithImporter(..) _ "); //$NON-NLS-1$
        _logger.warn(loggerMessage.toString(), exception);
        genModel = null;
      }
    } catch (Exception exception) {
      genModel = null;
      Diagnostic errorDiagnostic = ConverterUtil.createErrorDiagnostic(exception, true);
      if (errorDiagnostic != null) {
        handleDiagnostic(errorDiagnostic, "Error in genmodel creation"); //$NON-NLS-1$
      }
    } finally {
      monitor.done();
    }
    return genModel;
  }

  /**
   * Handle referenced packages.
   * @param modelImporter
   * @param monitor
   */
  private void handleReferencedPackages(ModelImporter modelImporter, Monitor monitor) {
    // Get external genModels.
    List<GenModel> externalGenModels = new ArrayList<GenModel>(0);
    addExternalGenModels(externalGenModels);
    // Get all declared EPackages in modelImporter (ie from its underlying model).
    List<EPackage> packages = modelImporter.getEPackages();

    // Iterate over packages to know if they are coming from external models or from our underlying model.
    Iterator<EPackage> iteratorOverPackages = packages.iterator();
    while (iteratorOverPackages.hasNext()) {
      EPackage currentPackage = iteratorOverPackages.next();
      GenPackage externalGenPackage = null;
      // Iterate over external genModels to retrieve whether or not, the current EPackage is owned by one of these external genModels.
      Iterator<GenModel> iteratorOverExternalGenModels = externalGenModels.iterator();
      while (iteratorOverExternalGenModels.hasNext() && (null == externalGenPackage)) {
        GenModel currentExternalGenModel = iteratorOverExternalGenModels.next();
        // Iterate over current external genModel packages.
        Iterator<GenPackage> externalGenPackages = currentExternalGenModel.getGenPackages().iterator();
        while (externalGenPackages.hasNext() && (null == externalGenPackage)) {
          GenPackage currentGenPackage = externalGenPackages.next();
          // Check if EPackage contained by the current GenPackage is the current EPackage ?
          EPackage ecorePackage = currentGenPackage.getEcorePackage();
          if (currentPackage == ecorePackage || currentPackage.getNsURI().equals(ecorePackage.getNsURI())) {
            externalGenPackage = currentGenPackage;
          }
        }
      }
      // External genPackage was retrieved for current EPackage.
      // Register this GenPackage as...
      if (null != externalGenPackage) {
        modelImporter.getReferencedGenPackages().add(externalGenPackage);
        // TODO There is probably a problem here. Need more complete analysis
        modelImporter.getReferenceGenPackageConvertInfo(externalGenPackage).setValidReference(true);
      } else {
        // Set the current EPackage to be converted ie it is owned by the underlying model.
        modelImporter.getEPackageConvertInfo(currentPackage).setConvert(true);
      }
    }
  }

  /**
   * Set GenModel parameters.
   * @param genModel
   */
  public void setGenModelParameters(GenModel genModel) {
    // Force to enable containment proxies, if forced.
//    if (MDSoFaGeneratorActivator.getDefault().isContainementProxiesEnabled()) {
      genModel.setContainmentProxies(true);
//    }
    // Set generation parameters
    if (null != _rootExtendsInterface) {
      genModel.setRootExtendsInterface(_rootExtendsInterface);
    }
    if (null != _rootExtendsClass) {
      genModel.setRootExtendsClass(_rootExtendsClass);
    }
    if (null != _jdkComplianceLevel) {
      genModel.setComplianceLevel(_jdkComplianceLevel);
    }
    genModel.setModelPluginID(_modelPluginId);
    genModel.setOperationReflection(_operationReflection);
    genModel.setNonNLSMarkers(true);
    //genModel.setImportOrganizing(true);
    
    // Set model directory based on provided project name and source folder name.
    if (null != _modelDirectory) {
      genModel.setModelDirectory(getModelDirectory());
    }
    // Get base GenPackages.
    List<GenPackage> genPackages = genModel.getAllGenPackagesWithClassifiers();
    // Evolution for multiple Ecores being handled by the same GenModel.
    // Use base package value for all GenPackages.
    for (GenPackage genPackage : genPackages) {
      // Set the resource type.
      if (null != _resourcetype) {
        genPackage.setResource(_resourcetype);
      }
      EPackage ePackage = genPackage.getEcorePackage();
      EPackage container = ePackage.getESuperPackage();
      // Base GenPackage.
      String suffix = ICommonConstants.EMPTY_STRING;
      if (null == container) {
        setPackageParameters(genPackage, _basePackage);
      } else { // Sub-package. Reconstruct base package.
        while (null != container) {
          suffix = ICommonConstants.POINT_CHARACTER + container.getName() + suffix;
          container = container.getESuperPackage();
        }
      }
      // Set base package value.
      setPackageParameters(genPackage, _basePackage + suffix);
    }
  }

  /**
   * Set package parameters.
   * @param genPackage
   */
  private void setPackageParameters(GenPackage genPackage, String basePackage) {
    genPackage.setBasePackage(basePackage);
    if (null != _implementationPackageSuffix) {
      genPackage.setClassPackageSuffix(_implementationPackageSuffix);
    }
    if (null != _interfacePackageSuffix) {
      genPackage.setInterfacePackageSuffix(_interfacePackageSuffix);
    }
    if (null != _metadataPackageSuffix) {
      genPackage.setMetaDataPackageSuffix(_metadataPackageSuffix);
    }
    if (null != _utilityPackageSuffix) {
      genPackage.setUtilityPackageSuffix(_utilityPackageSuffix);
    }
  }

  /**
   * Generate the genmodel file.
   * @param monitor
   * @return a {@link GenModel} instance or null if generation failed.
   */
  public GenModel execute(Monitor monitor) {
    GenModel genModel = null;
    try {
      // Create an EcoreImporter.
      ModelImporter modelImporter = getModelImporter();
      // Set internal fields.
      boolean result = adjustAttributes(CodeGenUtil.createMonitor(monitor, 1));
      if (result) {
        // Set the model importer.
        adjustModelImporter(CodeGenUtil.createMonitor(monitor, 1));
        // Check the genmodel file name.
        Diagnostic diagnostic = modelImporter.checkGenModelFileName();
        result = handleDiagnostic(diagnostic, "Check genmodel file name failed"); //$NON-NLS-1$
        if (result) {
          //Initialize the resource set
          modelImporter.getGenModelResourceSet();
          
          // Compute and adjust packages.
          result = handleEPackages(CodeGenUtil.createMonitor(monitor, 1));
          if (result) {
            // Finally, create genmodel
            genModel = createGenModel(CodeGenUtil.createMonitor(monitor, 1));
          }
        }
      }
    } catch (RuntimeException exception) {
      StringBuffer loggerMessage = new StringBuffer("AbstractGenModelGenerator.execute(..) _ "); //$NON-NLS-1$
      _logger.error(loggerMessage.toString(), exception);
      genModel = null;
    }
    return genModel;
  }

  /**
   * Get the model importer.
   * @return a {@link ModelImporter} instance or null if not instanciated in constructor.
   */
  protected ModelImporter getModelImporter() {
    if (null == _modelImporter) {
      _modelImporter = createModelImporter();
    }
    return _modelImporter;
  }

  /**
   * Adjust internal field from settings.
   * @param monitor
   */
  protected boolean adjustAttributes(Monitor monitor) {
    try {
      monitor.beginTask(EMPTY_STRING, 1);
      // Create genModel container
      _genModelContainerPath = getGenModelContainer();
      // Create the genmodel file location from input path.
      _genModelPath = getGenModelPath();
    } finally {
      monitor.done();
    }
    return true;
  }

  /**
   * Get the gen model path.
   * @return an {@link IPath} instance
   */
  protected abstract IPath getGenModelPath();

  /**
   * Adjust model importer settings.
   * @param monitor
   */
  protected void adjustModelImporter(Monitor monitor) {
    try {
      monitor.beginTask(EMPTY_STRING, 1);
      ModelImporter modelImporter = getModelImporter();
      // Set the handled file extensions
      ModelImporterDescriptor modelImporterDescriptor = ModelImporterManager.INSTANCE.getModelImporterDescriptor(getModelImporter().getID());
      modelImporter.getFileExtensions().addAll(modelImporterDescriptor.getExtensions());
      // Set project name ie plug-in id.
      modelImporter.setModelProjectName(_modelPluginId);
      // Set the model plug-in id ie where model API are generated.
      modelImporter.setModelPluginID(_modelPluginId);
      // Handle genmodel path
      handleGenModelPath(_genModelPath);
    } finally {
      monitor.done();
    }
  }

  /**
   * Handle genmodel path settings.
   * @param genModelPath
   */
  private void handleGenModelPath(IPath genModelPath) {
    ModelImporter modelImporter = getModelImporter();
    // Set the genmodel container.
    modelImporter.setGenModelContainerPath(_genModelContainerPath);
    // Set the genmodel file name.
    modelImporter.setGenModelFileName(genModelPath.toString());
  }

  /**
   * Return the genmodel container path.
   * @return
   */
  protected IPath getGenModelContainer() {
    IPath result = new Path(getModelPluginId());
    File physicalFile = getInputPath().toFile();
    if (physicalFile.isDirectory()) {
      // The model is defined by annotated java code, simply create the ecore model in the model folder.
      result = result.append(MODEL_FOLDER);
    } else {
      // The model is defined by an ecore one.
      // Try and get its relative path in the containing fc.
      IPath relativePath = getInputPath().removeFirstSegments(1).removeLastSegments(1).removeFileExtension();
      // If this path makes a sense, use it in target fc.
      if (relativePath.segmentCount() > 0) {
        result = result.append(relativePath);
      } else {
        // Make sure it ends up in the model folder (at least).
        result = result.append(MODEL_FOLDER);
      }
    }
    return result;
  }

  /**
   * Compute and adjust EPackages.
   * @param modelImporter
   * @return false if an error occurs; true otherwise.
   */
  private boolean handleEPackages(Monitor monitor) {
    boolean result = true;
    try {
      try {
        ModelImporter modelImporter = getModelImporter();
        // Compute packages
        Diagnostic diagnostic = modelImporter.computeEPackages(monitor);
        result = handleDiagnostic(diagnostic, "Computes EPackages error"); //$NON-NLS-1$
        if (result) {
          // Handle referenced genPackages.
          handleReferencedPackages(modelImporter, monitor);
          // Adjust packages
          modelImporter.adjustEPackages(monitor);
        }
      } catch (Exception exception) {
        StringBuffer loggerMessage = new StringBuffer("AbstractGenModelGenerator.handleEPackages(..) _ "); //$NON-NLS-1$
        _logger.warn(loggerMessage.toString(), exception);
      }
    } finally {
      monitor.done();
    }
    return result;
  }

  /**
   * Add external genModels into given list.
   * @param genModels must be not null.
   */
  protected void addExternalGenModels(List<GenModel> genModels) {
    ModelImporter modelImporter = getModelImporter();
    List<GenModel> externalGenModels = new ArrayList<GenModel>(modelImporter.getExternalGenModels());
    if (!externalGenModels.isEmpty()) {
      GenModel exporterGenModel = modelImporter.getGenModel();
      boolean hasExporterGenModel = exporterGenModel != null && genModels.contains(exporterGenModel);
      if (!hasExporterGenModel) {
        genModels.add(exporterGenModel);
      }

      for (GenModel genModel : genModels) {
        for (Iterator<GenModel> j = externalGenModels.iterator(); j.hasNext();) {
          GenModel externalGenModel = j.next();
          if (genModel == externalGenModel) {
            j.remove();
          } else {
            URI uri = genModel.eResource() != null ? genModel.eResource().getURI() : null;

            if (uri != null) {
              URI externalURI = externalGenModel.eResource() != null ? externalGenModel.eResource().getURI() : null;

              if (uri.equals(externalURI)) {
                j.remove();
              }
            }
          }
        }
      }
      genModels.addAll(externalGenModels);

      if (!hasExporterGenModel) {
        genModels.remove(exporterGenModel);
      }
    }
  }

  /**
   * Set the plugin that hosts model file, genmodel and generated java code.
   * @param pluginId
   */
  public void setPluginId(String pluginId) {
    _modelPluginId = pluginId;
  }

  /**
   * Set the base package used as prefix.
   * @param basePackage
   */
  public void setBasePackagePrefix(String basePackage) {
    _basePackage = basePackage;
  }

  /**
   * Set the input path either a model file name (ie /myProject/model/myModel.ecore) or project location (ie /myProject)
   * @param inputPath
   */
  public void setInputPath(IPath inputPath) {
    _inputPath = inputPath;
  }

  /**
   * Get the input path.
   * @return the inputPath
   */
  protected IPath getInputPath() {
    return _inputPath;
  }

  /**
   * Return the project location where code is generated.
   * @return the modelPluginId
   */
  protected String getModelPluginId() {
    return _modelPluginId;
  }

  protected String getRootExtendsInterface() {
    return _rootExtendsInterface;
  }

  /**
   * Clear internal structure.
   */
  public void clear() {
    _modelImporter = null;
    _inputPath = null;
    _modelPluginId = null;
    _basePackage = null;
    _genModelPath = null;
    _genModelContainerPath = null;
    _rootExtendsInterface = null;
    _rootExtendsClass = null;
    _jdkComplianceLevel = null;
  }

  /**
   * Set the interface declared as root interface in genmodel.
   * @param rootExtendsInterface the rootExtendsInterface to set
   */
  public void setRootExtendsInterface(String rootExtendsInterface) {
    _rootExtendsInterface = rootExtendsInterface;
  }

  /**
   * Set the class declared as root class in genmodel.
   * @param rootExtendsClass the rootExtendsClass to set
   */
  public void setRootExtendsClass(String rootExtendsClass) {
    _rootExtendsClass = rootExtendsClass;
  }

  /**
   * Set the JDK compliance Level in genmodel.
   * @param jdkComplianceLevel the jdkComplianceLevel to set
   */
  public void setJdkComplianceLevel(GenJDKLevel jdkComplianceLevel) {
    _jdkComplianceLevel = jdkComplianceLevel;
  }

  /**
   * Set the model directory location ie the folder where the generated files are output
   * @param modelDirectory
   */
  public void setModelDirectory(String modelDirectory) {
    _modelDirectory = modelDirectory;
  }

  /**
   * @param operationReflection
   */
  public void setOperationReflection(boolean operationReflection) {
    _operationReflection = operationReflection;
  }

  /**
   * Set the resource type.
   * @param resourceType
   */
  public void setResourceType(GenResourceKind resourceType) {
    _resourcetype = resourceType;
  }

  /**
   * The package suffix defines the last segment of the Java packages into which the various types of classes are generated.
   * @param implementationPackageSuffix
   * @param interfacePackageSuffix
   * @param metadataPackageSuffix
   * @param utilityPackageSuffix
   */
  public void setPackagesSuffixes(String implementationPackageSuffix, String interfacePackageSuffix, String metadataPackageSuffix,
      String utilityPackageSuffix) {
    _implementationPackageSuffix = implementationPackageSuffix;
    _interfacePackageSuffix = interfacePackageSuffix;
    _metadataPackageSuffix = metadataPackageSuffix;
    _utilityPackageSuffix = utilityPackageSuffix;
  }
}
