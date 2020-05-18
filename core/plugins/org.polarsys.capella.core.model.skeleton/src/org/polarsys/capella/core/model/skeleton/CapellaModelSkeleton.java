/*******************************************************************************
 * Copyright (c) 2017, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.model.skeleton;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.ProjectApproach;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.skeleton.impl.SkeletonServicesImpl;
import org.polarsys.capella.core.model.skeleton.impl.cmd.CreateCapellaProjectCmd;

/**
 * A facade to help navigating a default capella project structure a.k.a. skeleton.
 */
public class CapellaModelSkeleton extends AdapterImpl {

  /**
   * A builder which provides a fluent API to construct new capella project skeletons
   * with sensible defaults. The builders required parameters are passed in the constructor.
   * All setters denote optional parameters.
   *
   * TODO use the builder also in wizard..
   */
  public static class Builder {

    public static final URI DEFAULT_URI = URI.createURI("skeleton.melodymodeller"); //$NON-NLS-1$
    public static final String DEFAULT_NAME = "Skeleton"; //$NON-NLS-1$
    public static final EngineeringDomain DEFAULT_ENGINEERING_DOMAIN = EngineeringDomain.System;
    public static final boolean DEFAULT_INCLUDE_OPERATIONAL_ANALYSIS = true;
    public static final ProjectApproach DEFAULT_PROJECT_APPROACH = ProjectApproach.SingletonComponents;
    public static final EClass DEFAULT_ROOT_TYPE = CapellamodellerPackage.Literals.PROJECT;

    private final ExecutionManager manager;

    private URI uri = DEFAULT_URI;
    private String name = DEFAULT_NAME;
    private EngineeringDomain domain = DEFAULT_ENGINEERING_DOMAIN;
    private boolean includeOperationalAnalysis = DEFAULT_INCLUDE_OPERATIONAL_ANALYSIS;
    private ProjectApproach pa = DEFAULT_PROJECT_APPROACH;
    private EClass rootType = DEFAULT_ROOT_TYPE;

    CapellaModelSkeleton result;

    /**
     * Initialize a new builder. The builder will use the given execution manager.
     */
    public Builder(ExecutionManager manager) {
      this.manager = manager;
    }

    /**
     * Set the uri for the skeletons target resource. The default uri is 'skeleton.melodymodeller',
     * a dummy which works fine if you do not intend to serialize the model.
     * <p>
     * Note that it is currently not possible to create a skeleton without adding it to a Resource since
     * the API that the builder uses requires a resource.
     *
     * @param uri the URI in which the skeleton should be stored. If the managers resource set already has a Resource with that uri, it is used,
     * otherwise a new Resource will be created.
     * @return this builder
     */
    public Builder setURI(URI uri) {
      this.uri = uri;
      return this;
    }

    /**
     * Sets the type of the root element. Must be a subtype of Project, which is also the default.
     * @param rootType
     * @return this builder
     */
    public Builder setRootType(EClass rootType) {
      if (CapellamodellerPackage.Literals.PROJECT.isSuperTypeOf(rootType)) {
        this.rootType = rootType;
        return this;
      }
      throw new IllegalArgumentException("Argument must be a subtype of 'Project'"); //$NON-NLS-1$
    }

    /**
     * Set the skeleton name. This name will be used as the name for both the Project name and the SystemEngineering name of the new skeleton.
     * @param name
     * @return this builder
     */
    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    /**
     * Set the skeleton engineering domain. The default is 'System'
     * @param domain
     * @return this builder
     */
    public Builder setEngineeringDomain(EngineeringDomain domain) {
      this.domain = domain;
      return this;
    }

    /**
     * Sets the flag which includes OperationalAnalysis or not. The default is true
     * @param include
     * @return this builder
     */
    public Builder setIncludeOperationalAnalysis(boolean include) {
      this.includeOperationalAnalysis = include;
      return this;
    }

    /**
     * Builds the skeleton
     * @return the new skeleton.
     */
    public CapellaModelSkeleton build() {
      if (result == null) {
        Resource existing = manager.getEditingDomain().getResourceSet().getResource(uri, false);
        final Resource resource;
        if (existing == null) {
          resource = manager.getEditingDomain().getResourceSet().createResource(uri);
        } else {
          resource = existing;
        }

        final CreateCapellaProjectCmd cmd = new CreateCapellaProjectCmd(resource, name, pa) {
          @Override
          protected Project createProject(String name) {
            Project p = (Project) rootType.getEPackage().getEFactoryInstance().create(rootType);
            p.setName(name);
            return p;
          }
        };

        manager.execute(cmd);
        result = asSkeleton(cmd.getProject());

        ISkeletonServices s = new SkeletonServicesImpl();
        s.doSystemEngineering(result.getProject(), name, domain, includeOperationalAnalysis);
      }

      return result;
    }

  }

  /**
   * A helper that retrieves a skeleton adapter from the arguments root container and if
   * none is present creates and attaches one.
   *
   * @param capellaModelElement
   * @return a skeleton instance, or null if the root container object is not a capella Project
   */
  public static CapellaModelSkeleton asSkeleton(EObject capellaModelElement) {
    CapellaModelSkeleton result = null;
    EObject root = EcoreUtil.getRootContainer(capellaModelElement);
    result = (CapellaModelSkeleton) EcoreUtil.getExistingAdapter(root, CapellaModelSkeleton.class);
    if (result == null) {
      CapellaModelSkeleton sk = new CapellaModelSkeleton();
      if (sk.isAdapterForType(root)) {
        root.eAdapters().add(sk);
        result = sk;
      }
    }
    return result;
  }

  public Project getProject() {
    return (Project) getTarget();
  }

  public SystemEngineering getSystemEngineering() {
    return SystemEngineeringExt.getSystemEngineering(getProject());
  }

  public PhysicalArchitecture getPhysicalArchitecture() {
    return SystemEngineeringExt.getOwnedPhysicalArchitecture(getSystemEngineering());
  }

  public LogicalArchitecture getLogicalArchitecture() {
    return SystemEngineeringExt.getLogicalArchitecture(getSystemEngineering());
  }

  public OperationalAnalysis getOperationalAnalysis() {
    return SystemEngineeringExt.getOperationalAnalysis(getSystemEngineering());
  }

  public SystemAnalysis getSystemAnalysis() {
    return SystemEngineeringExt.getSystemAnalysis(getSystemEngineering());
  }

  public EPBSArchitecture getEPBSArchitecture() {
    return SystemEngineeringExt.getEPBSArchitecture(getSystemEngineering());
  }

  @Override
  public boolean isAdapterForType(Object type) {
    return type instanceof Project;
  }

}
