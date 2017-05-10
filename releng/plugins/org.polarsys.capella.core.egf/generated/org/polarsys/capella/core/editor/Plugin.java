//Generated with EGF 1.4.1.v20161010-1511
package org.polarsys.capella.core.editor;

import org.eclipse.egf.common.helper.*;
import java.util.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.egf.model.pattern.*;
import org.eclipse.egf.pattern.execution.*;
import org.eclipse.egf.pattern.query.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class Plugin extends org.eclipse.egf.emf.pattern.editor.Plugin {
  protected static String nl;

  public static synchronized Plugin create(String lineSeparator) {
    nl = lineSeparator;
    Plugin result = new Plugin();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "package ";
  protected final String TEXT_3 = ";" + NL;
  protected final String TEXT_4 = NL + NL + "/**" + NL + " * This is the central singleton for the ";
  protected final String TEXT_5 = " editor plugin." + NL + " * <!-- begin-user-doc -->" + NL
      + " * <!-- end-user-doc -->" + NL + " * @generated" + NL + " */" + NL + "public final class ";
  protected final String TEXT_6 = " extends EMFPlugin" + NL + "{";
  protected final String TEXT_7 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->"
      + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_8 = " copyright = ";
  protected final String TEXT_9 = ";";
  protected final String TEXT_10 = NL + "\t";
  protected final String TEXT_11 = NL + "\t/**" + NL + "\t * Keep track of the singleton." + NL
      + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL
      + "\tpublic static final ";
  protected final String TEXT_12 = " INSTANCE = new ";
  protected final String TEXT_13 = "();" + NL + "\t" + NL + "\t/**" + NL + "\t * Keep track of the singleton." + NL
      + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL
      + "\tprivate static Implementation plugin;" + NL + "" + NL + "\t/**" + NL + "\t * Create the instance." + NL
      + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL
      + "\tpublic ";
  protected final String TEXT_14 = "()" + NL + "\t{" + NL + "\t\tsuper" + NL + "\t\t\t(new ResourceLocator [] " + NL
      + "\t\t\t{";
  protected final String TEXT_15 = NL + "\t\t\t\t";
  protected final String TEXT_16 = ".INSTANCE,";
  protected final String TEXT_17 = NL + "\t\t\t});" + NL + "\t}" + NL + "" + NL + "\t/**" + NL
      + "\t * Returns the singleton instance of the Eclipse plugin." + NL + "\t * <!-- begin-user-doc -->" + NL
      + "\t * <!-- end-user-doc -->" + NL + "\t * @return the singleton instance." + NL + "\t * @generated" + NL
      + "\t */";
  protected final String TEXT_18 = NL + "\t@Override";
  protected final String TEXT_19 = NL + "\tpublic ResourceLocator getPluginResourceLocator()" + NL + "\t{" + NL
      + "\t\treturn plugin;" + NL + "\t}" + NL + "\t" + NL + "\t/**" + NL
      + "\t * Returns the singleton instance of the Eclipse plugin." + NL + "\t * <!-- begin-user-doc -->" + NL
      + "\t * <!-- end-user-doc -->" + NL + "\t * @return the singleton instance." + NL + "\t * @generated" + NL
      + "\t */" + NL + "\tpublic static Implementation getPlugin()" + NL + "\t{" + NL + "\t\treturn plugin;" + NL
      + "\t}" + NL + "\t" + NL + "\t/**" + NL + "\t * The actual implementation of the Eclipse <b>Plugin</b>." + NL
      + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL
      + "\tpublic static class Implementation extends ";
  protected final String TEXT_20 = NL + "\t{" + NL + "\t\t/**" + NL + "\t\t * Creates an instance." + NL
      + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->";
  protected final String TEXT_21 = NL + "\t\t * @param descriptor the description of the plugin.";
  protected final String TEXT_22 = NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\tpublic Implementation(";
  protected final String TEXT_23 = " descriptor";
  protected final String TEXT_24 = ")" + NL + "\t\t{" + NL + "\t\t\tsuper(";
  protected final String TEXT_25 = "descriptor";
  protected final String TEXT_26 = ");" + NL + "\t" + NL + "\t\t\t// Remember the static instance." + NL + "\t\t\t//"
      + NL + "\t\t\tplugin = this;" + NL + "\t\t}" + NL + "" + NL + "\t\t//begin-capella-code" + NL + "\t\t/**" + NL
      + "\t\t * Returns the object's graphical representation." + NL
      + "\t\t * (This method comes from a customization of the standard EMF generator)" + NL + "\t\t *" + NL
      + "\t\t * @param key the identifier of the object." + NL + "\t\t * @generated" + NL + "\t\t */" + NL
      + "\t\t@Override" + NL + "\t\tpublic Object getImage(String key) {" + NL + "\t\t\treturn ";
  protected final String TEXT_27 = ".find(this.getBundle(), new ";
  protected final String TEXT_28 = "(\"icons/\" + key + \".gif\"), null);" + NL + "\t\t}" + NL
      + "\t\t//end-capella-code" + NL + "\t}" + NL + "" + NL + "}";
  protected final String TEXT_29 = NL;
  protected final String TEXT_30 = NL;

  public Plugin() {
    //Here is the constructor
    StringBuffer stringBuffer = new StringBuffer();

    // add initialisation of the pattern variables (declaration has been already done).

  }

  public String generate(Object argument) throws Exception {
    final StringBuffer stringBuffer = new StringBuffer();

    InternalPatternContext ctx = (InternalPatternContext) argument;
    Map<String, String> queryCtx = null;
    IQuery.ParameterDescription paramDesc = null;
    Node.Container currentNode = ctx.getNode();

    List<Object> parameterList = null;
    //this pattern can only be called by another (i.e. it's not an entry point in execution)

    for (Object parameterParameter : parameterList) {

      this.parameter = (org.eclipse.emf.codegen.ecore.genmodel.GenModel) parameterParameter;

      if (preCondition(ctx)) {
        ctx.setNode(new Node.Container(currentNode, getClass()));
        orchestration(ctx);
      }

    }
    ctx.setNode(currentNode);
    if (ctx.useReporter()) {
      ctx.getReporter().executionFinished(OutputManager.computeExecutionOutput(ctx), ctx);
    }

    stringBuffer.append(TEXT_29);
    stringBuffer.append(TEXT_30);
    return stringBuffer.toString();
  }

  public String orchestration(PatternContext ctx) throws Exception {
    InternalPatternContext ictx = (InternalPatternContext) ctx;

    super.orchestration(new SuperOrchestrationContext(ictx));

    if (ictx.useReporter()) {
      Map<String, Object> parameterValues = new HashMap<String, Object>();
      parameterValues.put("parameter", this.parameter);
      String outputWithCallBack = OutputManager.computeLoopOutput(ictx);
      String loop = OutputManager.computeLoopOutputWithoutCallback(ictx);
      ictx.getReporter().loopFinished(loop, outputWithCallBack, ictx, parameterValues);
    }
    return null;
  }

  public Map<String, Object> getParameters() {
    final Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("parameter", this.parameter);
    return parameters;
  }

  protected void method_doGenerate(final StringBuffer stringBuffer, final PatternContext ctx) throws Exception {

    /**
     * <copyright>
     *
     * Copyright (c) 2002-2004 IBM Corporation and others.
     * All rights reserved.   This program and the accompanying materials
     * are made available under the terms of the Eclipse Public License v1.0
     * which accompanies this distribution, and is available at
     * http://www.eclipse.org/legal/epl-v10.html
     *
     * Contributors:
     *   IBM - Initial API and implementation
     *
     * </copyright>
     */

    GenModel genModel = (GenModel) argument;
    /* Trick to import java.util.* without warnings */Iterator.class.getName();
    stringBuffer.append(TEXT_1);
    {
      //<%@ egf:patternCall patternId="platform:/plugin/org.eclipse.egf.emf.pattern.base/egf/EMF_Pattern_Base.fcore#LogicalName=org.eclipse.egf.emf.pattern.base.HeaderJava" args="parameter:argument"%>

      InternalPatternContext ictx = (InternalPatternContext) ctx;
      new Node.DataLeaf(ictx.getNode(), getClass(), null, stringBuffer.toString());
      stringBuffer.setLength(0);

      final Map<String, Object> callParameters = new HashMap<String, Object>();
      callParameters.put("argument", parameter);
      CallHelper.executeWithParameterInjection(
          "platform:/plugin/org.eclipse.egf.emf.pattern.base/egf/EMF_Pattern_Base.fcore#_XHLrsCwtEd-jc5T-XaRJlg",
          new ExecutionContext((InternalPatternContext) ctx), callParameters);
      stringBuffer.setLength(0);
    }

    stringBuffer.append(TEXT_2);
    stringBuffer.append(genModel.getEditorPluginPackageName());
    stringBuffer.append(TEXT_3);
    genModel.addImport("org.eclipse.emf.common.EMFPlugin");
    genModel.addImport("org.eclipse.emf.common.util.ResourceLocator");
    genModel.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(genModel.getModelName());
    stringBuffer.append(TEXT_5);
    stringBuffer.append(genModel.getEditorPluginClassName());
    stringBuffer.append(TEXT_6);
    if (genModel.hasCopyrightField()) {
      stringBuffer.append(TEXT_7);
      stringBuffer.append(genModel.getImportedName("java.lang.String"));
      stringBuffer.append(TEXT_8);
      stringBuffer.append(genModel.getCopyrightFieldLiteral());
      stringBuffer.append(TEXT_9);
      stringBuffer.append(genModel.getNonNLS());
      stringBuffer.append(TEXT_10);
    }
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genModel.getEditorPluginClassName());
    stringBuffer.append(TEXT_12);
    stringBuffer.append(genModel.getEditorPluginClassName());
    stringBuffer.append(TEXT_13);
    stringBuffer.append(genModel.getEditorPluginClassName());
    stringBuffer.append(TEXT_14);
    for (String pluginClassName : genModel.getEditResourceDelegateImportedPluginClassNames()) {
      stringBuffer.append(TEXT_15);
      stringBuffer.append(pluginClassName);
      stringBuffer.append(TEXT_16);
    }
    stringBuffer.append(TEXT_17);
    if (genModel.useClassOverrideAnnotation()) {
      stringBuffer.append(TEXT_18);
    }
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.ui.EclipseUIPlugin"));
    stringBuffer.append(TEXT_20);
    if (genModel.needsRuntimeCompatibility()) {
      stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_22);
    if (genModel.needsRuntimeCompatibility()) {
      stringBuffer.append(genModel.getImportedName("org.eclipse.core.runtime.IPluginDescriptor"));
      stringBuffer.append(TEXT_23);
    }
    stringBuffer.append(TEXT_24);
    if (genModel.needsRuntimeCompatibility()) {
      stringBuffer.append(TEXT_25);
    }
    stringBuffer.append(TEXT_26);
    stringBuffer.append(genModel.getImportedName("org.eclipse.core.runtime.FileLocator"));
    stringBuffer.append(TEXT_27);
    stringBuffer.append(genModel.getImportedName("org.eclipse.core.runtime.Path"));
    stringBuffer.append(TEXT_28);
    genModel.emitSortedImports();
    InternalPatternContext ictx = (InternalPatternContext) ctx;
    new Node.DataLeaf(ictx.getNode(), getClass(), "doGenerate", stringBuffer.toString());
  }
}