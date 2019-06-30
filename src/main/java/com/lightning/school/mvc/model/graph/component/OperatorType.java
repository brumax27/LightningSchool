package com.lightning.school.mvc.model.graph.component;

import java.util.Arrays;

public enum OperatorType {

    AND(new VerityTableMatrix(Arrays.asList(
            new VerityMatrixRow(true, true, true),
            new VerityMatrixRow(true , false, false),
            new VerityMatrixRow(false, true, false),
            new VerityMatrixRow(false, false, false)
    ))),
    OR(new VerityTableMatrix(Arrays.asList(
            new VerityMatrixRow(true, true, true),
            new VerityMatrixRow(true , false, true),
            new VerityMatrixRow(false, true, true),
            new VerityMatrixRow(false, false, false)
    ))),
    NOT(new VerityTableMatrix(Arrays.asList(
            new VerityMatrixRow(true, null, false),
            new VerityMatrixRow(false , null, true)
    )));

    private VerityTableMatrix verity;

    OperatorType(VerityTableMatrix verity) {
        this.verity = verity;
    }
}
