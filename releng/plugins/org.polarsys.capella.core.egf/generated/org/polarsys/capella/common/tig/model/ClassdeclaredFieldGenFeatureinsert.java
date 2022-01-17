//Generated with EGF 1.6.3.202110291409
package org.polarsys.capella.common.tig.model;

import org.eclipse.egf.common.helper.*;
import java.util.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.egf.model.pattern.*;
import org.eclipse.egf.pattern.execution.*;
import org.eclipse.egf.pattern.query.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class ClassdeclaredFieldGenFeatureinsert extends org.eclipse.egf.emf.pattern.model.call.Class.ClassdeclaredFieldGenFeatureinsert {
    protected static String nl;

    public static synchronized ClassdeclaredFieldGenFeatureinsert create(String lineSeparator) {
        nl = lineSeparator;
        ClassdeclaredFieldGenFeatureinsert result = new ClassdeclaredFieldGenFeatureinsert();
        nl = null;
        return result;
    }

    public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;

    protected final String TEXT_1 = "\t/**" + NL + "\t * The parsed OCL expression for the body of the '{@link #";

    protected final String TEXT_2 = " <em>";

    protected final String TEXT_3 = "</em>}' operation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";

    protected final String TEXT_4 = NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static ";

    protected final String TEXT_5 = "<";

    protected final String TEXT_6 = "> ";

    protected final String TEXT_7 = "BodyOCL;" + NL + "\t";

    protected final String TEXT_8 = NL + "\t/**" + NL + "\t * The parsed OCL expression for the derivation of '{@link #";

    protected final String TEXT_9 = "</em>}' property." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";

    protected final String TEXT_10 = "DeriveOCL;" + NL + "\t";

    protected final String TEXT_11 = NL + "\tprivate static final String OCL_ANNOTATION_SOURCE = \"";

    protected final String TEXT_12 = "\";";

    protected final String TEXT_13 = NL + "\t" + NL + "\tprivate static final ";

    protected final String TEXT_14 = " OCL_ENV = ";

    protected final String TEXT_15 = ".newInstance();";

    protected final String TEXT_16 = NL;

    public ClassdeclaredFieldGenFeatureinsert() {
        //Here is the constructor
        StringBuffer stringBuffer = new StringBuffer();

        // add initialisation of the pattern variables (declaration has been already done).
        oclNsURI = org.polarsys.capella.common.model.helpers.IModelConstants.OCL_ANNOTATION_SOURCE;

    }

    public String generate(Object argument) throws Exception {
        final StringBuffer stringBuffer = new StringBuffer();

        InternalPatternContext ctx = (InternalPatternContext) argument;
        Map<String, String> queryCtx = null;
        IQuery.ParameterDescription paramDesc = null;
        Node.Container currentNode = ctx.getNode();

        List<Object> genFeatureList = null;
        //this pattern can only be called by another (i.e. it's not an entry point in execution)
        List<Object> genClassList = null;
        //this pattern can only be called by another (i.e. it's not an entry point in execution)
        List<Object> genPackageList = null;
        //this pattern can only be called by another (i.e. it's not an entry point in execution)
        List<Object> genModelList = null;
        //this pattern can only be called by another (i.e. it's not an entry point in execution)
        List<Object> isInterfaceList = null;
        //this pattern can only be called by another (i.e. it's not an entry point in execution)
        List<Object> isImplementationList = null;
        //this pattern can only be called by another (i.e. it's not an entry point in execution)
        List<Object> publicStaticFinalFlagList = null;
        //this pattern can only be called by another (i.e. it's not an entry point in execution)
        List<Object> singleWildcardList = null;
        //this pattern can only be called by another (i.e. it's not an entry point in execution)
        List<Object> negativeOffsetCorrectionList = null;
        //this pattern can only be called by another (i.e. it's not an entry point in execution)
        List<Object> positiveOffsetCorrectionList = null;
        //this pattern can only be called by another (i.e. it's not an entry point in execution)

        for (Object genFeatureParameter : genFeatureList) {
            for (Object genClassParameter : genClassList) {
                for (Object genPackageParameter : genPackageList) {
                    for (Object genModelParameter : genModelList) {
                        for (Object isInterfaceParameter : isInterfaceList) {
                            for (Object isImplementationParameter : isImplementationList) {
                                for (Object publicStaticFinalFlagParameter : publicStaticFinalFlagList) {
                                    for (Object singleWildcardParameter : singleWildcardList) {
                                        for (Object negativeOffsetCorrectionParameter : negativeOffsetCorrectionList) {
                                            for (Object positiveOffsetCorrectionParameter : positiveOffsetCorrectionList) {

                                                this.genFeature = (org.eclipse.emf.codegen.ecore.genmodel.GenFeature) genFeatureParameter;
                                                this.genClass = (org.eclipse.emf.codegen.ecore.genmodel.GenClass) genClassParameter;
                                                this.genPackage = (org.eclipse.emf.codegen.ecore.genmodel.GenPackage) genPackageParameter;
                                                this.genModel = (org.eclipse.emf.codegen.ecore.genmodel.GenModel) genModelParameter;
                                                this.isInterface = (java.lang.Boolean) isInterfaceParameter;
                                                this.isImplementation = (java.lang.Boolean) isImplementationParameter;
                                                this.publicStaticFinalFlag = (java.lang.String) publicStaticFinalFlagParameter;
                                                this.singleWildcard = (java.lang.String) singleWildcardParameter;
                                                this.negativeOffsetCorrection = (java.lang.String) negativeOffsetCorrectionParameter;
                                                this.positiveOffsetCorrection = (java.lang.String) positiveOffsetCorrectionParameter;

                                                if (preCondition(ctx)) {
                                                    ctx.setNode(new Node.Container(currentNode, getClass()));
                                                    orchestration(ctx);
                                                }

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        ctx.setNode(currentNode);
        if (ctx.useReporter()) {
            ctx.getReporter().executionFinished(OutputManager.computeExecutionOutput(ctx), ctx);
        }

        stringBuffer.append(TEXT_16);
        stringBuffer.append(TEXT_16);
        return stringBuffer.toString();
    }

    public String orchestration(PatternContext ctx) throws Exception {
        InternalPatternContext ictx = (InternalPatternContext) ctx;

        super.orchestration(new SuperOrchestrationContext(ictx));

        if (ictx.useReporter()) {
            Map<String, Object> parameterValues = new HashMap<String, Object>();
            parameterValues.put("genFeature", this.genFeature);
            parameterValues.put("genClass", this.genClass);
            parameterValues.put("genPackage", this.genPackage);
            parameterValues.put("genModel", this.genModel);
            parameterValues.put("isInterface", this.isInterface);
            parameterValues.put("isImplementation", this.isImplementation);
            parameterValues.put("publicStaticFinalFlag", this.publicStaticFinalFlag);
            parameterValues.put("singleWildcard", this.singleWildcard);
            parameterValues.put("negativeOffsetCorrection", this.negativeOffsetCorrection);
            parameterValues.put("positiveOffsetCorrection", this.positiveOffsetCorrection);
            String outputWithCallBack = OutputManager.computeLoopOutput(ictx);
            String loop = OutputManager.computeLoopOutputWithoutCallback(ictx);
            ictx.getReporter().loopFinished(loop, outputWithCallBack, ictx, parameterValues);
        }
        return null;
    }

    protected java.lang.String oclNsURI = null;

    public void set_oclNsURI(java.lang.String object) {
        this.oclNsURI = object;
    }

    public Map<String, Object> getParameters() {
        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("genFeature", this.genFeature);
        parameters.put("genClass", this.genClass);
        parameters.put("genPackage", this.genPackage);
        parameters.put("genModel", this.genModel);
        parameters.put("isInterface", this.isInterface);
        parameters.put("isImplementation", this.isImplementation);
        parameters.put("publicStaticFinalFlag", this.publicStaticFinalFlag);
        parameters.put("singleWildcard", this.singleWildcard);
        parameters.put("negativeOffsetCorrection", this.negativeOffsetCorrection);
        parameters.put("positiveOffsetCorrection", this.positiveOffsetCorrection);
        return parameters;
    }

    protected void method_doGenerate(final StringBuffer stringBuffer, final PatternContext ctx) throws Exception {

        if (isImplementation) {
            boolean hasOCL = false;
            for (GenOperation genOperation : genClass.getImplementedGenOperations()) {
                String body = null;
                EAnnotation ocl = genOperation.getEcoreOperation().getEAnnotation(oclNsURI);
                if (ocl != null)
                    body = ocl.getDetails().get("body");
                if (body != null) {
                    hasOCL = true;
                    stringBuffer.append(TEXT_1);
                    stringBuffer.append(genOperation.getName());
                    stringBuffer.append(TEXT_2);
                    stringBuffer.append(genOperation.getFormattedName());
                    stringBuffer.append(TEXT_3);
                    stringBuffer.append(genOperation.getName());
                    stringBuffer.append(TEXT_4);
                    stringBuffer.append(genModel.getImportedName("org.eclipse.ocl.expressions.OCLExpression"));
                    stringBuffer.append(TEXT_5);
                    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClassifier"));
                    stringBuffer.append(TEXT_6);
                    stringBuffer.append(genOperation.getName());
                    stringBuffer.append(TEXT_7);
                }
            }

            for (GenFeature genFeature : genClass.getImplementedGenFeatures()) {
                String derive = null;
                EAnnotation ocl = genFeature.getEcoreFeature().getEAnnotation(oclNsURI);
                if (ocl != null)
                    derive = ocl.getDetails().get("derive");
                if (derive != null) {
                    hasOCL = true;
                    stringBuffer.append(TEXT_8);
                    stringBuffer.append(genFeature.getGetAccessor());
                    stringBuffer.append(TEXT_2);
                    stringBuffer.append(genFeature.getFormattedName());
                    stringBuffer.append(TEXT_9);
                    stringBuffer.append(genFeature.getGetAccessor());
                    stringBuffer.append(TEXT_4);
                    stringBuffer.append(genModel.getImportedName("org.eclipse.ocl.expressions.OCLExpression"));
                    stringBuffer.append(TEXT_5);
                    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClassifier"));
                    stringBuffer.append(TEXT_6);
                    stringBuffer.append(genFeature.getSafeName());
                    stringBuffer.append(TEXT_10);
                }
            }

            if (hasOCL) {
                stringBuffer.append(TEXT_11);
                stringBuffer.append(oclNsURI);
                stringBuffer.append(TEXT_12);
                stringBuffer.append(genModel.getNonNLS());
                stringBuffer.append(TEXT_13);
                stringBuffer.append(genModel.getImportedName("org.eclipse.ocl.ecore.OCL"));
                stringBuffer.append(TEXT_14);
                stringBuffer.append(genModel.getImportedName("org.eclipse.ocl.ecore.OCL"));
                stringBuffer.append(TEXT_15);
            }
        }
        stringBuffer.append(TEXT_16);
        InternalPatternContext ictx = (InternalPatternContext) ctx;
        new Node.DataLeaf(ictx.getNode(), getClass(), "doGenerate", stringBuffer.toString());
    }
}
